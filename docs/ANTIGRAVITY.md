# Usar o Benigno com Antigravity

A partir da **v10** o Benigno também roda no [Google Antigravity](https://antigravity.google), no aplicativo e na CLI `agy`. Como no OpenCode e no Claude Code, o agente principal age como Benigno e delega aos 38 subagentes especialistas `maligno-*`. Para as outras formas de uso, veja o [README](../README.md).

## Como funciona

Não há prompt para colar. O Antigravity descobre sozinho os plugins em `.agents/plugins/`, dentro do repositório:

- **`.agents/plugins/benigno/`**: o plugin do Benigno.
  - **`skills/benigno-java/SKILL.md`**: a skill principal. Faz o agente agir como o Benigno: classificar o pedido, rotear e escrever o código.
  - **`agents/maligno-*.md`**: os 38 subagentes especialistas (`maligno-equals`, `maligno-hashcode`, `maligno-auditor`, ...). Só têm a ferramenta de leitura, respondem com o conhecimento embutido (`REGRA / CÓDIGO / FONTE / ALERTAS`) e rodam em paralelo.
  - **`skills/cliente-servidor-continhas/`** e **`skills/criar-prova-maligno/`**: skills auxiliares (projeto de threads/sockets e criação de enunciados no estilo Maligno).
  - **`rules/AGENTS.md`**: regra sempre ativa que manda usar a skill `benigno-java` em pedidos de Java da disciplina.
- **`.agents/plugins/rtk/`**: o plugin do [RTK](https://github.com/rtk-ai/rtk), que reescreve os comandos do terminal para a versão enxuta (`git status` vira `rtk git status`) e economiza tokens.

```
Antigravity (como Benigno) ──► subagentes maligno-* (em paralelo) ──► (último caso) 1 arquivo de Materias de aula/
```

O conteúdo das regras é o mesmo do OpenCode: os subagentes são gerados a partir de `.opencode/agents/maligno/*.md` (passando por `.claude/agents/`).

## Passo a passo

### 1. Instalar o Antigravity
Baixe em <https://antigravity.google> e entre com a sua conta Google. A CLI `agy` vem junto; confira com:
```bash
agy --version
```

### 2. Ter o JDK
Confira com `javac -version` (JDK 8 ou superior). Se faltar, veja o [passo 1.3 do guia do OpenCode](OPENCODE.md#13-instalar-o-git-e-o-jdk).

### 3. Baixar o repositório
```bash
git clone https://github.com/egili/Benigno-Java.git
cd Benigno-Java
```
Já tem? `git checkout main && git pull`. Sem Git, baixe o ZIP pelo botão **Code → Download ZIP** e extraia.

### 4. Abrir o Antigravity DENTRO da pasta do repositório
No aplicativo, abra a pasta `Benigno-Java` como workspace. Na CLI:
```bash
cd ~/caminho/para/Benigno-Java
agy
```
Quando ele perguntar se confia no workspace, aceite: sem isso os plugins de `.agents/` não carregam.

⚠️ Erro mais comum. O Antigravity procura `.agents/` subindo da pasta aberta até a raiz do repositório. Aberto na pasta que *contém* o repositório, ele não encontra o Benigno.

### 5. Instalar o RTK (opcional)
O plugin `rtk` só faz a ligação com o Antigravity: o **binário** `rtk` precisa estar instalado à parte (veja <https://github.com/rtk-ai/rtk>). Confira com `rtk --version`. Sem ele, o plugin não faz nada e os comandos rodam como foram pedidos.

Se você usa listas de comandos permitidos, libere os comandos do RTK (por exemplo `command(rtk *)`): o Antigravity confere a permissão depois que o hook reescreve o comando.

### 6. Pedir no chat
Basta pedir normalmente; a skill `benigno-java` é acionada sozinha quando o pedido é de Java no padrão da disciplina:
> *"Benigno, crie a classe Investimento com código, descrição, data de abertura, valor e taxa, com aplicar e resgatar."*
>
> *"Implemente o Tabuleiro do Jogo da Velha."*
>
> *"Revise este código como o professor corrigiria."*

Para forçar, digite `/benigno-java` ou peça *"use a skill benigno-java"*.

### 7. Conferir (opcional)
```bash
ls .agents/plugins/benigno/agents | wc -l          # deve mostrar 38
agy plugin validate .agents/plugins/benigno         # skills 3 · agents 38 · rules 1
agy -p "liste os subagentes maligno-* disponíveis"  # o agente deve citar os 38
```

Para desligar um dos plugins, use a tela de plugins do aplicativo ou `agy plugin disable rtk` (ou `benigno`).

## Problemas comuns

| Sintoma | Solução |
|---|---|
| `agy: command not found` | Reinstale o Antigravity (passo 1), ou rode `agy install` pelo aplicativo para configurar o PATH. Feche e abra o terminal. |
| O Benigno não é acionado | Abra o Antigravity **dentro** da pasta do repositório e confie no workspace (passo 4). Chame com `/benigno-java` ou peça "use a skill benigno-java". |
| O agente não conhece os `maligno-*` | Mesmo caso acima. Confira `ls .agents/plugins/benigno/agents`; se estiver vazio, `git checkout main && git pull`. |
| O agente não delega aos subagentes | Peça explicitamente: *"consulte os subagentes maligno-* necessários em paralelo antes de escrever"*. |
| Comandos não ficam mais curtos | O binário `rtk` não está no PATH (passo 5), ou o plugin `rtk` está desligado. |
| Acentos quebrados ao compilar | Compile com `javac -encoding UTF-8 *.java`. |
| `javac: command not found` | Instale o JDK (passo 2) e reabra o terminal. |

## Limitações

- O **context-mode** não tem versão para o Antigravity: a v2 do context-mode tirou o suporte ao Antigravity/`agy`. Ele continua só no Claude Code ([docs/CLAUDE.md](CLAUDE.md)).
- Os subagentes rodam no modelo da sua sessão. Em trabalho valendo nota, use um modelo forte e rode o `maligno-auditor` no final.
- A regra do plugin `rtk` diz ao modelo que a saída vem condensada mesmo quando o binário não está instalado; é inofensivo, mas desligue o plugin se não for usar o RTK.

## Outras formas de uso

- [OpenCode](OPENCODE.md): instalação local completa, com subagentes.
- [Claude Code](CLAUDE.md): com subagentes e plugins RTK/context-mode.
- [Docker](DOCKER.md): sem instalar nada além do Docker.
- [Cursor](CURSOR.md): usando o Cursor como editor.
