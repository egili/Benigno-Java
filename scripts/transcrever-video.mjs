#!/usr/bin/env node
// Baixa a legenda em português de um vídeo do YouTube (público ou não listado)
// e grava transcricoes/<id>/bruta.txt com marcações de tempo.
// Uso: node scripts/transcrever-video.mjs <link> [pasta-de-saida]

import { spawnSync } from "node:child_process";
import { chmodSync, existsSync, mkdirSync, readdirSync, readFileSync, renameSync, rmSync, writeFileSync } from "node:fs";
import { homedir } from "node:os";
import { join, resolve } from "node:path";
import { fileURLToPath } from "node:url";

const BINARIOS = { "linux-x64": "yt-dlp_linux", "linux-arm64": "yt-dlp_linux_aarch64", "darwin-x64": "yt-dlp_macos", "darwin-arm64": "yt-dlp_macos", "win32-x64": "yt-dlp.exe", "win32-arm64": "yt-dlp.exe" };

async function ytDlp() {
  if (spawnSync("yt-dlp", ["--version"]).status === 0) return "yt-dlp";

  const nome = BINARIOS[`${process.platform}-${process.arch}`];
  if (!nome) throw new Error(`Plataforma sem yt-dlp pronto (${process.platform}-${process.arch}): instale o yt-dlp e rode de novo.`);

  const pasta = join(homedir(), ".cache", "benigno");
  const caminho = join(pasta, nome);
  if (existsSync(caminho)) return caminho;

  console.error("Baixando o yt-dlp (só na primeira vez)...");
  const resp = await fetch(`https://github.com/yt-dlp/yt-dlp/releases/latest/download/${nome}`);
  if (!resp.ok) throw new Error(`Falha ao baixar o yt-dlp: HTTP ${resp.status}`);
  mkdirSync(pasta, { recursive: true });
  writeFileSync(caminho + ".part", Buffer.from(await resp.arrayBuffer()));
  chmodSync(caminho + ".part", 0o755);
  renameSync(caminho + ".part", caminho);
  return caminho;
}

function hms(ms) {
  const s = Math.floor(ms / 1000);
  return [Math.floor(s / 3600), Math.floor(s / 60) % 60, s % 60].map((n) => String(n).padStart(2, "0")).join(":");
}

// json3: cada evento tem tStartMs e segs[].utf8; um bloco [hh:mm:ss] a cada ~30 s
export function json3ParaTexto(json) {
  const blocos = [];
  let inicio = -Infinity;
  for (const ev of json.events || []) {
    const texto = (ev.segs || []).map((s) => s.utf8).join("").replace(/\s+/g, " ").trim();
    if (!texto) continue;
    if (ev.tStartMs - inicio >= 30000) {
      inicio = ev.tStartMs;
      blocos.push(`[${hms(ev.tStartMs)}] ${texto}`);
    } else {
      blocos[blocos.length - 1] += " " + texto;
    }
  }
  return blocos.join("\n\n") + "\n";
}

async function main() {
  const [link, saidaArg] = process.argv.slice(2);
  if (!link || !/^https?:\/\/(www\.|m\.)?(youtube\.com|youtu\.be)\//.test(link)) {
    console.error("Uso: node scripts/transcrever-video.mjs <link do YouTube> [pasta-de-saida]");
    process.exit(1);
  }
  const saida = resolve(saidaArg || "transcricoes");

  const bin = await ytDlp();
  const tmp = join(saida, ".tmp");
  mkdirSync(tmp, { recursive: true });

  const args = [
    "--skip-download", "--no-simulate", "--no-playlist", "--js-runtimes", `node:${process.execPath}`,
    "--write-subs", "--write-auto-subs", "--sub-langs", "pt,pt-BR,pt-orig", "--sub-format", "json3",
    "--print", "%(id)s\t%(title)s\t%(duration_string)s\t%(channel)s",
    "-o", join(tmp, "%(id)s.%(ext)s"),
    link,
  ];
  let r = spawnSync(bin, args, { encoding: "utf8" });
  if (r.status !== 0 && bin !== "yt-dlp") {
    // o YouTube muda com frequência: atualiza o yt-dlp baixado e tenta de novo
    spawnSync(bin, ["-U"], { stdio: "ignore" });
    r = spawnSync(bin, args, { encoding: "utf8" });
  }
  if (r.status !== 0) {
    console.error(r.stderr.trim());
    throw new Error("O yt-dlp não conseguiu abrir o vídeo. Confira se o link está certo e se o vídeo é público ou não listado (privado exige login).");
  }

  const [id, titulo, duracao, canal] = r.stdout.trim().split("\n").pop().split("\t");
  const arquivos = readdirSync(tmp).filter((f) => f.startsWith(id + ".") && f.endsWith(".json3"));
  // legenda manual (pt/pt-BR) antes da automática original (pt-orig)
  const escolhido = ["pt", "pt-BR", "pt-orig"].map((l) => `${id}.${l}.json3`).find((f) => arquivos.includes(f));
  if (!escolhido) throw new Error("O vídeo não tem legenda em português (nem automática). Não há o que transcrever.");

  const pasta = join(saida, id);
  mkdirSync(pasta, { recursive: true });
  const texto = json3ParaTexto(JSON.parse(readFileSync(join(tmp, escolhido), "utf8")));
  rmSync(tmp, { recursive: true, force: true });
  const cabecalho = `Título: ${titulo}\nCanal: ${canal}\nDuração: ${duracao}\nLink: ${link}\nLegenda: ${escolhido.split(".")[1]}\n\n`;
  writeFileSync(join(pasta, "bruta.txt"), cabecalho + texto);
  console.log(join(pasta, "bruta.txt"));
  console.log(`${titulo} (${duracao}), ${texto.length} caracteres`);
}

if (process.argv[1] && resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  main().catch((e) => { console.error("ERRO: " + e.message); process.exit(2); });
}
