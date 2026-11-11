# O Benigno 

O Benigno é um agente que escreve e revisa trabalhos de Java no padrão exato que o professor Maligno cobra: não só código que funciona, mas código que o professor *espera* ver para dar a nota máxima.

Funciona no **OpenCode**, no **Cursor** e em **Docker**.

## Como funciona

Todo o material (apostila, caderno, exemplos, provas, trabalho) foi lido **uma vez** e destilado em **38 subagentes especialistas** (`maligno/*`), cada um com as regras e os trechos de código do professor sobre um único tema.

```
benigno (principal) ──► subagentes maligno/* ──► (último caso) 1 arquivo de Materias de aula/
```

1. O `benigno` classifica o pedido e consulta **só** os subagentes necessários, em paralelo.
2. Os subagentes não têm ferramentas: respondem com o conhecimento embutido, no formato `REGRA / CÓDIGO / FONTE / ALERTAS`.
3. Se um tema não estiver coberto, o subagente responde `NAO_COBERTO` apontando **um arquivo**; só então o `benigno` o lê.

No Cursor não há subagentes: o modelo lê os mesmos arquivos em `.cursor/skills/benigno-java/maligno/` quando precisa.

## Escolha como usar

| Caminho | Para quem | Guia |
|---|---|---|
| **OpenCode** (local) | Quem quer o agente completo, com subagentes em paralelo | [docs/OPENCODE.md](docs/OPENCODE.md) |
| **Docker** | Quem não quer instalar OpenCode, Git nem JDK | [docs/DOCKER.md](docs/DOCKER.md) |
| **Cursor** (desde a v7) | Quem usa o Cursor como editor | [docs/CURSOR.md](docs/CURSOR.md) |

Em todos os casos, primeiro baixe o repositório:
```bash
git clone https://github.com/egili/Benigno-Java.git
```
Sem Git? Baixe o ZIP em <https://github.com/egili/Benigno-Java> (botão **Code → Download ZIP**).

## Exemplos de uso
> *"Benigno, crie a classe Investimento com código, descrição, data de abertura, valor e taxa, com aplicar e resgatar."*  <br />
> *"Benigno, implemente o Tabuleiro do Jogo da Velha."* <br />
> *"Benigno, revise este código como o professor corrigiria."* <br />

## Subagentes

| Grupo | Subagentes (`maligno/...`) |
|---|---|
| Métodos obrigatórios | `metodos-necessidade` (decide quais fazer), `tostring`, `equals`, `hashcode`, `compareto`, `clone-copia` |
| Construção e estado | `construtores`, `getters-setters`, `operacoes-imutaveis` |
| Regras transversais | `nomenclatura`, `excecoes`, `estilo`, `encapsulamento`, `javadoc`, `tipos-wrappers` |
| Entrada e saída | `teclado`, `main-menus` |
| Estruturas | `vetores`, `busca-ondeesta`, `regex`, `genericos`, `clonador-reflexao`, `pilha`, `arvore`, `conjunto`, `colecoes`, `simulado-fila` |
| Hierarquia | `heranca`, `interface`, `classe-abstrata`, `enum`, `pacotes-jar` |
| Avançado e trabalhos | `threads-sockets`, `trabalho-velha`, `trabalho-forca` |
| Testes | `testes` (classe de teste sem framework, com `main` que imprime OK/FALHOU) |
| Qualidade | `auditor` (corrige como o professor), `erratas` (bugs do material) |

**Por que um validador único para os métodos obrigatórios?** As regras de necessidade dependem umas das outras (`toString`/`equals`/`hashCode` têm o mesmo gatilho; `clone` e construtor de cópia são inseparáveis; `compareTo` precisa concordar com `equals`). Separar em 6 validadores geraria respostas contraditórias. A implementação, ao contrário, é muito diferente de um método para outro, por isso cada um tem seu subagente.

## Decisões de padronização

O material do professor diverge entre si. O Benigno adota:

- **Prioridade entre fontes:** caderno passado em aula > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos.
- **Mensagens de exceção com acento** (`"Nome inválido"`, `"Modelo ausente"`).
- **`hashCode` da correção da prova:** base 1, multiplicador 2, wrapper por cast, trata negativo.
- **Constantes em `MAIUSCULAS_COM_UNDERSCORE`** (regra do caderno e da apostila).
- **Bugs do material nunca são copiados** (catálogo em `maligno/erratas`), incluindo o NullPointerException do empate em `Velha.java`.

## MDD (Maligno Driven Development)

O Benigno segue o que chamamos de **MDD**: escrever o código pensando no crivo do professor, não só no funcionamento. A explicação teórica (pilares, sintomas de código fora do padrão e escala de descontos) está em [docs/MDD.md](docs/MDD.md).

## Dicas

- **Auditoria:** em trabalho valendo nota, peça *"rode o auditor"* antes de entregar. Ele lista onde o professor tiraria pontos (–1,5 por método obrigatório errado, faltando ou desnecessário).
- **Java 8:** o Benigno evita `var`, `record`, streams e lambdas; se aparecer algo moderno, peça para refatorar.
- **Compilação:** como as mensagens de exceção têm acento, compile com `javac -encoding UTF-8 *.java`.
- **Índice de materiais:** `.opencode/indice-materiais.md` mapeia cada tema ao arquivo de origem.

## Como colaborar

Achou um erro, uma regra do professor mal representada ou tem uma ideia de melhoria? Há três caminhos:

- **Abrir uma issue:** em <https://github.com/egili/Benigno-Java/issues>, descreva o problema com o pedido que você fez ao Benigno ou sua ideia de melhoria.
- **Enviar uma alteração (pull request):** faça um *fork* do repositório, crie uma branch com a sua mudança (`git checkout -b minha-mudanca`), faça o commit e abra um *pull request* para a branch `main`. Não é preciso ser colaborador para isso.
- **Pedir acesso de colaborador:** se você vai contribuir com frequência, peça permissão de escrita no repositório.

Antes de enviar uma alteração, confira que os `.java` continuam compilando com `javac -encoding UTF-8 *.java` e que o padrão das regras (`maligno/*`) não foi quebrado. Mudanças nas regras devem seguir a fonte do professor, não a preferência de quem contribui.

## Histórico de versões

Para acessar o histórico de versões do Benigno, [clique aqui](VERSOES.md).
