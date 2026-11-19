---
name: transcrever-aula
description: >-
  Transcreve e resume uma videoaula do YouTube (pública ou não listada) do
  professor Maligno: transcrição revisada quase palavra por palavra e resumo
  para caderno, na ordem da aula. Use quando o usuário passar um link do
  YouTube, pedir a transcrição ou o resumo de um vídeo/videoaula, ou disser
  "parte N de TOTAL" de uma aula.
---

# Transcrever videoaula

O modelo não assiste vídeo. A fala vem da **legenda do YouTube** (a do professor, se houver; senão a automática), baixada pelo script do projeto. Você revisa esse texto e escreve o resumo.

## 1. Descobrir o vídeo
- **Sem link no pedido:** leia `videos/catalogo.md`. Se houver vídeos, diga que já tem acesso a algumas aulas, liste os temas e peça que a pessoa cite o tema. Se o catálogo estiver vazio, peça o link.
- Pergunte só o que faltar e mudar o resultado: se é "parte N de TOTAL" (o catálogo pode dizer).

## 2. Baixar a legenda
Na raiz do Benigno (a pasta que tem `scripts/`; no Docker é `/benigno`):
```bash
node scripts/transcrever-video.mjs "<link>"
```
- No Docker, passe a pasta do aluno como saída para o arquivo sobreviver ao container: `node /benigno/scripts/transcrever-video.mjs "<link>" /benigno/projeto/transcricoes`.
- Grava `transcricoes/<id>/bruta.txt` (cabeçalho com título, duração e link; texto em blocos `[hh:mm:ss]`). Na primeira vez baixa o `yt-dlp` para `~/.cache/benigno/`. Só precisa de Node e internet; não precisa de login nem de navegador.
- **Se o script falhar** (vídeo privado, link errado, sem legenda em português, sem internet): mostre a mensagem e pare. **Nunca simule a transcrição.**
- Se `transcricoes/<id>/aula.md` já existir, ofereça reaproveitá-lo em vez de refazer.

## 3. Preparar a revisão
- Descubra o tema pelo título e pelo começo do texto. Ache a pasta do material no índice (`.opencode/indice-materiais.md`) e liste nomes de classes, métodos, arquivos e mensagens com `grep` (não leia a pasta inteira). Eles corrigem os erros da legenda automática.
- Erros típicos já vistos: "bebê" = BD, "Kelly serve" = SQL Server, "mais Kelly" = MySQL, "órgão" = Oracle, "meu riso 7" = `MeuResultSet`, "comitê" = `commit`, "Roberto" = `rollback`, "John i i" = `try`.
- `bruta.txt` de uma aula de 1h passa de 50 KB: leia em blocos (offset/limit, ~10 minutos de aula por vez) e escreva em arquivo à medida que avança. Não tente responder tudo no chat.

## 4. Escrever `transcricoes/<id>/aula.md`
Exatamente nesta estrutura:
```
# TRANSCRIÇÃO REVISADA
[hh:mm:ss] parágrafo...

# RESUMO PARA CADERNO
## 1. <título>
...
```

**Regras gerais**
- Não invente conteúdo. O que não der para entender vira `[inaudível]` ou `[trecho não compreendido]`.
- Corrija só erros evidentes de reconhecimento, sobretudo nomes de classes, arquivos, comandos, bancos de dados, métodos e termos técnicos. Mantenha esses nomes em Java/SQL.
- Preserve conteúdo, sequência e lógica da aula. Não omita exemplos, comandos, nomes de arquivos, regras, alertas e observações.
- Sem opiniões, análises externas ou informação que não esteja no vídeo. Bugs do material que você notar vão para a resposta no chat, não para o arquivo.

**Transcrição revisada**
- Completa, quase palavra por palavra; não resuma.
- Parágrafos com a marcação `[hh:mm:ss]` do bloco da legenda.
- Melhore pontuação e clareza; preserve repetições e autocorreções do professor quando fizerem parte da explicação.

**Resumo para caderno**
- Português brasileiro, pronto para copiar no caderno, na **ordem** em que o professor falou.
- Completo, mas não palavra por palavra: seções numeradas com títulos claros, tópicos, listas e parágrafos curtos.
- Inclua comandos, trechos de código, nomes de classes, arquivos, métodos e regras citados.
- Destaque alertas, erros esperados, diferenças entre Java e banco de dados (ou o equivalente do tema) e observações importantes.
- Nada de resumo genérico: mantenha os detalhes técnicos e os exemplos da aula.

**Parte N de TOTAL**
- Leia o `# RESUMO PARA CADERNO` das partes anteriores (catálogo ou `transcricoes/*/aula.md`) antes de escrever.
- Transcreva e resuma **só esta parte**; continue a numeração das seções e não repita o que já foi tratado (pode remeter: "ver parte 1, seção 3").

## 5. Entregar
- No chat: o caminho de `aula.md`, o `# RESUMO PARA CADERNO` inteiro e uma linha com os trechos marcados como inaudíveis.
- `transcricoes/` fica fora do git (`.gitignore`): o repositório é público e as aulas são só para os alunos. Não faça commit dela.
- Se a aula trouxer regra nova para um subagente `maligno-*`, ofereça atualizá-lo (só com o "sim" do usuário).
