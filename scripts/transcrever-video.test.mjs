import assert from "node:assert/strict";
import { test } from "node:test";
import { json3ParaTexto } from "./transcrever-video.mjs";

test("agrupa eventos em blocos de ~30 s e ignora eventos vazios", () => {
  const json = {
    events: [
      { tStartMs: 4000, segs: [{ utf8: "Olá" }, { utf8: " pessoal" }] },
      { tStartMs: 9000, segs: [{ utf8: "\n" }] },
      { tStartMs: 20000, segs: [{ utf8: "objetivo  da aula" }] },
      { tStartMs: 3725000, segs: [{ utf8: "fim" }] },
    ],
  };
  assert.equal(json3ParaTexto(json), "[00:00:04] Olá pessoal objetivo da aula\n\n[01:02:05] fim\n");
});
