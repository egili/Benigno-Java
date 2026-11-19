# Videoaulas: transcrição e resumo para caderno

A partir da **v10** o Benigno transcreve videoaulas do YouTube. Basta passar o link de um vídeo **público ou não listado**. Ele entrega:

1. a **transcrição revisada**, quase palavra por palavra, com marcações de tempo;
2. o **resumo para caderno**, em seções numeradas, na ordem em que o professor falou.

Funciona no OpenCode, no Claude Code, no Antigravity, no Cursor e no Docker. Para as outras funções do Benigno, veja o [README](../README.md).

## O que precisa ter

- **Node.js** (18 ou mais novo) no PATH. As plataformas do Benigno já usam o Node; no Docker ele vem na imagem.
- **Internet.**

Mais nada: não precisa de login no YouTube nem de navegador. Na primeira vez o Benigno baixa o [`yt-dlp`](https://github.com/yt-dlp/yt-dlp) para `~/.cache/benigno/` (Linux, macOS e Windows). Se você já tem o `yt-dlp` instalado, ele usa o seu.

## Como usar

Abra o Benigno na sua plataforma e peça, por exemplo:

> *"Benigno, transcreva e resuma esta videoaula: https://youtu.be/XXXXXXXXXXX"*

> *"Benigno, esta é a parte 2 de 3 da aula de banco de dados: https://youtu.be/XXXXXXXXXXX"*

> *"Benigno, quero o resumo da aula sobre threads."* (sem link: ele procura no catálogo)

O resultado fica em `transcricoes/<id-do-vídeo>/`:

| Arquivo | Conteúdo |
|---|---|
| `bruta.txt` | A legenda baixada, sem revisão, em blocos `[hh:mm:ss]` |
| `aula.md` | `# TRANSCRIÇÃO REVISADA` e `# RESUMO PARA CADERNO` |

No chat aparece o resumo inteiro e o caminho do `aula.md`. A transcrição de uma aula longa só vai para o arquivo.

No Docker a pasta `transcricoes/` é criada dentro do seu projeto (`/benigno/projeto`), então ela continua lá depois que o container fecha.

## Como funciona

```
link ──► scripts/transcrever-video.mjs ──► bruta.txt ──► skill transcrever-aula ──► aula.md
```

1. **Download da legenda.** O script baixa a legenda em português do vídeo: a que o professor publicou, se houver; senão a automática do YouTube.
2. **Revisão.** A skill `transcrever-aula` lê o texto em blocos de uns 10 minutos. Ela corrige só os erros evidentes de reconhecimento de fala, conferindo com os nomes de classes, métodos e arquivos do material da aula em `Materias de aula/`. Exemplos de correções: "bebê" vira BD, "Kelly serve" vira SQL Server, "meu riso 7" vira `MeuResultSet`.
3. **Resumo.** Feito a partir da transcrição revisada, sem acrescentar nada que não esteja no vídeo.
4. **Partes.** Quando você diz "parte N de TOTAL", o Benigno lê os resumos das partes anteriores, continua a numeração das seções e não repete o que já foi tratado.

Regras que ele segue:
- Não inventa conteúdo. O que não dá para entender vira `[inaudível]` ou `[trecho não compreendido]`.
- Se não conseguir baixar a legenda, avisa e para. Nunca simula uma transcrição.
- Não acrescenta opiniões nem informação de fora. Bugs do material que ele notar aparecem no chat, não no resumo.

Se a aula trouxer uma regra nova do professor, o Benigno oferece atualizar o subagente `maligno-*` do tema. Ele só faz isso se você concordar.

Para rodar só o download, sem o agente:
```bash
node scripts/transcrever-video.mjs "https://youtu.be/XXXXXXXXXXX"          # grava em ./transcricoes/
node scripts/transcrever-video.mjs "https://youtu.be/XXXXXXXXXXX" saida/   # outra pasta
```

## Catálogo de videoaulas

O arquivo [`videos/catalogo.md`](../videos/catalogo.md) lista as aulas do professor que o Benigno já conhece: tema, parte, link e material relacionado. Quando você pede uma transcrição sem passar o link, ele diz que já tem acesso a essas aulas, mostra os temas e pede que você escolha um.

Para incluir uma aula, acrescente uma linha na tabela:

```
| Banco de dados em Java (JDBC) | 1 de 2 | https://youtu.be/XXXXXXXXXXX | `17) Material sobre acesso a BDs em programas feitos em Java/` |
```

## Por que legenda do YouTube e não Whisper

| | Legenda do YouTube (usada) | Whisper local |
|---|---|---|
| Instalação | Nenhuma, além do Node | Python, `ffmpeg` e um modelo de centenas de MB |
| Tempo por aula de 1h50 | Segundos | 30 a 60 minutos sem placa de vídeo (estimativa) |
| Qualidade bruta | Média, com erros em termos técnicos | Melhor |
| Qualidade final | Boa: a revisão corrige os termos com o código do material | Boa |

Como a revisão pelo modelo já corrige os erros da legenda, a diferença de qualidade final é pequena e não compensa o custo de instalação para cada aluno.

## Limitações e cuidados

- **Vídeo privado de verdade** (só para contas convidadas) não funciona: exigiria o login de quem tem acesso. Vídeos não listados funcionam.
- **Vídeo sem legenda em português**, nem automática, não é transcrito.
- **A pasta `transcricoes/` fica fora do git e da imagem Docker.** O repositório é público e as aulas são só para os alunos; não faça commit das transcrições.
- **Os links do catálogo ficam públicos** junto com o repositório.
- **Termos do YouTube:** baixar conteúdo vai contra os termos de uso. Use só para estudo pessoal e não redistribua as transcrições.

## Problemas comuns

| Sintoma | Solução |
|---|---|
| `O yt-dlp não conseguiu abrir o vídeo` | Confira o link. Abra-o numa janela anônima: se não tocar, o vídeo é privado. |
| `O vídeo não tem legenda em português` | O YouTube ainda não gerou a legenda automática (acontece em vídeos recém-publicados). Tente de novo mais tarde. |
| `Falha ao baixar o yt-dlp` | Sem acesso ao GitHub. Instale o `yt-dlp` manualmente e deixe-o no PATH. |
| `node: command not found` | Instale o Node.js 18 ou mais novo. |
| O resumo saiu curto ou genérico | Peça: *"siga a skill transcrever-aula e leia o `bruta.txt` inteiro, em blocos"*. Use um modelo mais forte. |
