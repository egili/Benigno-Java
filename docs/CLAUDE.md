# Usar o Benigno com Claude Code

A partir da **v8** o Benigno também roda no [Claude Code](https://claude.com/claude-code), o agente de linha de comando da Anthropic. Aqui ele recupera a arquitetura de **subagentes em paralelo** do OpenCode: o Claude principal age como Benigno e delega aos 39 especialistas `maligno-*`. Para as outras formas de uso, veja o [README](../README.md).

## Como funciona

Não há prompt para colar. Os arquivos ficam em `.claude/`, dentro do repositório:

- **`.claude/skills/benigno-java/SKILL.md`** — a skill principal. Quando você pede o Benigno (ou código no padrão Maligno), ela entra em contexto e faz o Claude agir como o Benigno: classificar o pedido, rotear e escrever o código.
- **`.claude/agents/maligno-*.md`** — os 39 subagentes especialistas (um por tema: `maligno-equals`, `maligno-hashcode`, `maligno-heranca`, `maligno-auditor`, ...). São restritos a leitura, respondem só com o conhecimento embutido (`REGRA / CÓDIGO / FONTE / ALERTAS`) e rodam em paralelo.
- **`.claude/skills/cliente-servidor-continhas/`** e **`.claude/skills/criar-prova-maligno/`** — skills auxiliares (projeto de threads/sockets e criação de enunciados no estilo Maligno).
- **`.claude/settings.json`** — habilita os plugins **RTK** (`plugins/rtk/`, deste repositório) e **context-mode**, que economizam tokens, e o **[ponytail](https://github.com/DietrichGebert/ponytail)**, que mantém a solução mínima (comandos `/ponytail lite|full|ultra`, `/ponytail-review`, `/ponytail-audit`). Eles vêm com o projeto.

```
Claude (como Benigno) ──► subagentes maligno-* (em paralelo) ──► (último caso) 1 arquivo de Materias de aula/
```

O conteúdo das regras é o mesmo do OpenCode: os subagentes `.claude/agents/maligno-*.md` são gerados a partir de `.opencode/agents/maligno/*.md`.

## Passo a passo

### 1. Instalar o Claude Code
```bash
npm install -g @anthropic-ai/claude-code   # precisa de Node.js 18+
```
Alternativas e detalhes em <https://docs.claude.com/claude-code>. Confira com:
```bash
claude --version
```

### 2. Ter uma conta/modelo
Na primeira execução, o `claude` pede login. Use uma **assinatura Claude (Pro/Max)** ou uma **chave da API Anthropic** (cobrança por uso). Siga o fluxo que aparece no terminal; depois disso o Benigno usa o modelo da sua sessão — modelos mais fortes seguem melhor as regras do professor.

### 3. Ter o JDK
Confira com `javac -version` (JDK 8 ou superior). Se faltar, veja o [passo 1.3 do guia do OpenCode](OPENCODE.md#13-instalar-o-git-e-o-jdk).

### 4. Baixar o repositório
```bash
git clone https://github.com/egili/Benigno-Java.git
cd Benigno-Java
```
Já tem? `git checkout main && git pull`. Sem Git, baixe o ZIP pelo botão **Code → Download ZIP** e extraia.

### 5. Abrir o Claude Code DENTRO da pasta do repositório
```bash
cd ~/caminho/para/Benigno-Java
claude
```
⚠️ Erro mais comum. O Claude Code descobre `.claude/` no diretório onde foi aberto (e nos acima). Aberto na pasta que *contém* o repositório, ele não encontra o Benigno.

Quando o Claude Code perguntar se confia na pasta e se aceita os plugins do projeto, aceite.

### 6. Pedir no chat
Basta pedir normalmente; a skill `benigno-java` é acionada sozinha quando o pedido é de Java no padrão da disciplina:
> *"Benigno, crie a classe Investimento com código, descrição, data de abertura, valor e taxa, com aplicar e resgatar."*
>
> *"Implemente o Tabuleiro do Jogo da Velha."*
>
> *"Revise este código como o professor corrigiria."*

Para forçar, digite `/benigno-java` (a skill aparece na lista de `/`) ou peça *"use a skill benigno-java"*.

### 7. Conferir (opcional)
```bash
ls .claude/agents | wc -l      # deve mostrar 39 (os subagentes maligno-*)
ls .claude/skills              # benigno-java, cliente-servidor-continhas, criar-prova-maligno
```
Dentro do `claude`, `/agents` lista os subagentes disponíveis, `/skills` as skills e `/plugin` os plugins instalados (`context-mode`, `rtk` e `ponytail`).

## Problemas comuns

| Sintoma | Solução |
|---|---|
| `claude: command not found` | Confirme o Node.js (`node --version`, 18+) e reinstale (passo 1). Feche e abra o terminal. |
| O Benigno não é acionado | Abra o Claude Code **dentro** da pasta do repositório (passo 5). `ls .claude/agents` deve listar os `maligno-*`. Chame com `/benigno-java` ou peça "use a skill benigno-java". |
| Os subagentes não aparecem em `/agents` | A pasta aberta não é a do repositório, ou o clone está incompleto: `git checkout main && git pull` e confira `ls .claude/agents`. |
| O Claude não delega aos subagentes | Peça explicitamente: *"consulte os subagentes maligno-* necessários em paralelo antes de escrever"*. |
| Acentos quebrados ao compilar | Compile com `javac -encoding UTF-8 *.java`. |
| `javac: command not found` | Instale o JDK (passo 3) e reabra o terminal. |

## Limitações

- Os subagentes `maligno-*` rodam no modelo padrão de subagente da sua sessão (configurado para `haiku`, e `sonnet` no `maligno-auditor`). Em trabalho valendo nota, use um modelo forte na sessão principal e rode o `maligno-auditor` no final.
- Subagentes não disparam outros subagentes: quando um responde `NAO_COBERTO` apontando outro tema, é o Claude principal (como Benigno) que chama o próximo.

## Outras formas de uso

- [OpenCode](OPENCODE.md): instalação local completa, com subagentes.
- [Antigravity](ANTIGRAVITY.md): também com subagentes em paralelo e plugin RTK.
- [Docker](DOCKER.md): sem instalar nada além do Docker.
- [Cursor](CURSOR.md): usando o Cursor como editor.
