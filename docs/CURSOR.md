# Usar o Benigno com Cursor

Desde a **v7** (11/11/2026, veja o [histórico](../VERSOES.md)), o Benigno também funciona no Cursor. Ele usa as mesmas regras do OpenCode, mas sem subagentes: o próprio modelo lê os arquivos de regra e de tema quando precisa.

## Como funciona
1. Ao trabalhar em um `.java` do repositório, o Cursor aplica a regra `.cursor/rules/benigno.mdc`.
2. Essa regra manda ler a skill `.cursor/skills/benigno-java/SKILL.md`, que define o roteamento.
3. O modelo lê só os temas necessários em `.cursor/skills/benigno-java/maligno/`. Para o Jogo da Velha, a regra já aponta `trabalho-velha.md`.
4. Se nenhum tema cobrir o assunto, o `indice-materiais.md` aponta o arquivo de origem.
5. Em trabalho valendo nota, o `auditor` é lido no final.

Os arquivos em `.cursor/` espelham os de `.opencode/agents/`, então as regras são as mesmas nos dois.

## Passo a passo

### 1. Ter o Cursor e um modelo
Instale o [Cursor](https://cursor.com) e escolha o modelo no seletor do chat. Modelos mais fortes seguem melhor as regras do professor.

### 2. Ter o JDK
Confira com `javac -version` (JDK 8 ou superior). Se faltar, veja o [passo 1.3 do guia do OpenCode](OPENCODE.md#13-instalar-o-git-e-o-jdk).

### 3. Baixar o repositório
```bash
git clone https://github.com/egili/Benigno-Java.git
```
Sem Git, baixe o ZIP pelo botão **Code → Download ZIP** e extraia.

### 4. Abrir a pasta do repositório no Cursor
Em **File → Open Folder**, escolha a pasta `Benigno-Java`, a que contém `.cursor/`. Não abra a pasta que a contém: é o mesmo erro comum do OpenCode.

### 5. Pedir no chat
Exemplos:
> *"Crie a classe Investimento com código, descrição, data de abertura, valor e taxa, com aplicar e resgatar."*
>
> *"Implemente o Tabuleiro do Jogo da Velha."*
>
> *"Revise este código como o professor corrigiria."*

Para forçar a leitura da skill, peça: *"leia `.cursor/skills/benigno-java/SKILL.md` e os temas em `maligno/`"*.

## Problemas comuns

| Sintoma | Solução |
|---|---|
| A regra não parece aplicada | Confirme que a pasta aberta é a do repositório (ela contém `.cursor/`). A regra vale para arquivos `.java`. |
| O chat não segue o padrão do professor | Peça a leitura explícita da skill (passo 5) e use um modelo mais forte. |
| A pasta `.cursor/` não aparece | O clone pode estar incompleto. Rode `git checkout main && git pull` dentro da pasta. |
| Acentos quebrados ao compilar | Compile com `javac -encoding UTF-8 *.java`. |
| `javac: command not found` | Instale o JDK (passo 2) e reabra o Cursor. |

## Limitações
- Sem subagentes: a consulta aos temas acontece em uma única sessão de chat, então respostas longas podem perder algum detalhe. Confira com o `auditor`.
- A qualidade depende do modelo escolhido no Cursor.

## Outras formas de uso
- [OpenCode](OPENCODE.md): instalação local completa, com subagentes.
- [Docker](DOCKER.md): sem instalar OpenCode, Git nem JDK.
