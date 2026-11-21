# O Benigno 

O Benigno é um assistente que escreve e revisa trabalhos de Java no padrão exato que o professor Maligno cobra: não só código que funciona, mas código que o professor *espera* ver para dar a nota máxima.

Funciona no **OpenCode**, no **Claude Code**, no **Antigravity**, no **Cursor** e no **Docker**.

## Como funciona

Todo o material do professor (apostila, caderno, exemplos, provas, trabalhos e videoaulas) foi lido **uma vez** e resumido em **39 subagentes**, cada um com as regras e os exemplos de um único tema: `equals`, herança, pilha, banco de dados etc.

```
você pede ──► Benigno escolhe os subagentes ──► eles respondem ──► Benigno escreve o código
```

1. O Benigno entende o pedido e consulta **só** os subagentes daquele tema.
2. Cada subagente responde com a regra do professor, um exemplo de código e de onde tirou.
3. Se nenhum subagente souber, o Benigno abre **um único** arquivo do material para conferir.

Assim ele responde rápido e sem reler o material inteiro a cada pedido. No Cursor não há subagentes separados: o próprio modelo lê os arquivos de cada tema quando precisa, e o resultado é o mesmo.

## Escolha como usar

| Caminho | Para quem | Guia |
|---|---|---|
| **OpenCode** | Quem quer instalar no próprio computador | [docs/OPENCODE.md](docs/OPENCODE.md) |
| **Claude Code** | Quem já usa o Claude Code | [docs/CLAUDE.md](docs/CLAUDE.md) |
| **Antigravity** | Quem usa o Google Antigravity | [docs/ANTIGRAVITY.md](docs/ANTIGRAVITY.md) |
| **Docker** | Quem não quer instalar quase nada | [docs/DOCKER.md](docs/DOCKER.md) |
| **Cursor** | Quem usa o editor Cursor | [docs/CURSOR.md](docs/CURSOR.md) |

Em todos os casos, primeiro baixe o repositório:
```bash
git clone https://github.com/egili/Benigno-Java.git
```
Sem Git? Baixe o ZIP em <https://github.com/egili/Benigno-Java> (botão **Code → Download ZIP**).

## Exemplos de uso
> *"Benigno, crie a classe Investimento com código, descrição, data de abertura, valor e taxa, com aplicar e resgatar."* <br />
> *"Benigno, implemente o Tabuleiro do Jogo da Velha."* <br />
> *"Benigno, revise este código como o professor corrigiria."* <br />
> *"Benigno, crie um simulado no estilo do professor."* <br />
> *"Benigno, crie um exercício para eu treinar `equals` e `hashCode`."* <br />
> *"Benigno, transcreva e resuma esta videoaula: https://youtu.be/..."* <br />

## Provas, simulados e exercícios

Peça uma prova, um simulado ou um exercício e o Benigno escreve o enunciado do jeito que o professor escreveria: texto direto, sem entregar as respostas e sem dizer quais métodos obrigatórios a classe precisa ter. Você recebe o enunciado e um arquivo `.java` vazio para resolver. Depois, peça para ele corrigir a sua solução como o professor corrigiria.

## Videoaulas

Passe o link de uma videoaula do YouTube e o Benigno entrega a **transcrição revisada** e um **resumo para o caderno**, na ordem da aula. Sem link, ele mostra as aulas do professor que já conhece e pergunta o tema. Mais detalhes em [docs/VIDEOAULAS.md](docs/VIDEOAULAS.md).

## MDD (Maligno Driven Development)

O Benigno segue o que chamamos de **MDD**: escrever o código pensando na correção do professor, não só no funcionamento. A explicação completa está em [docs/MDD.md](docs/MDD.md).

## Dicas

- **Antes de entregar:** em trabalho valendo nota, peça *"rode o auditor"*. Ele mostra onde o professor tiraria pontos.
- **Java da disciplina:** o Benigno usa só o que o professor ensina. Se aparecer algo que ele não mostrou em aula, peça para refazer.
- **Para compilar:** como as mensagens de erro têm acento, use `javac -encoding UTF-8 *.java`.

## Como colaborar

Achou um erro, uma regra do professor mal representada ou tem uma ideia? Há três caminhos:

- **Abrir uma issue:** em <https://github.com/egili/Benigno-Java/issues>, conte o que você pediu ao Benigno e o que deu errado, ou descreva sua ideia.
- **Enviar uma alteração:** faça um *fork* do repositório, crie uma branch com a sua mudança, faça o commit e abra um *pull request* para a `main`. Não precisa ser colaborador.
- **Pedir acesso de colaborador:** se você vai contribuir com frequência, peça permissão de escrita.

Antes de enviar, confira que o código continua compilando e que as regras seguem o que o professor ensinou, não a preferência de quem contribui.

## Histórico de versões

Para ver o que mudou em cada versão, [clique aqui](VERSOES.md).
