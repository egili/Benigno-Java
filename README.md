# O Benigno v2 (The Maligno-Compliant Java Architect)

O Benigno é um agente do **OpenCode** que escreve e revisa trabalhos de Java no padrão exato que o professor "Maligno" cobra: não só código que funciona, mas código que o professor *espera* ver para dar a nota máxima.

## O que mudou na v2

Na v1, o agente relia a pasta `Materias de aula/` a cada pedido, gastando muitos tokens. Na v2, todo o material (apostila, caderno, exemplos, provas, trabalho) foi lido **uma vez** e destilado em **36 subagentes especialistas** (`maligno/*`), cada um com as regras e os trechos de código do professor sobre um único tema.

```
benigno (principal) ──► subagentes maligno/* ──► (último caso) 1 arquivo de Materias de aula/
```

1. O `benigno` classifica o pedido e consulta **só** os subagentes necessários, em paralelo.
2. Os subagentes não têm ferramentas: respondem com o conhecimento embutido, no formato `REGRA / CÓDIGO / FONTE / ALERTAS`.
3. Se um tema não estiver coberto, o subagente responde `NAO_COBERTO` apontando **um arquivo**; só então o `benigno` o lê.

## Como usar

### Pré-requisitos
- **OpenCode** instalado.
- **JDK 8+** no `PATH`.
- Este repositório aberto como workspace (a pasta `Materias de aula/` precisa estar nele).

### Ativação
Nada para colar: o `opencode.json` na raiz define o `benigno` como agente padrão e carrega o plugin `ponytail`; os agentes ficam em `.opencode/agents/`. Abra o OpenCode na pasta do repositório e converse normalmente.

Exemplos:
> *"Benigno, crie a classe Investimento com código, descrição, data de abertura, valor e taxa, com aplicar e resgatar."*
> *"Benigno, implemente o Tabuleiro do Jogo da Velha."*
> *"Benigno, revise este código como o professor corrigiria."*

### Compilação
Como as mensagens de exceção têm acento, compile com `javac -encoding UTF-8 *.java`.

## Subagentes

| Grupo | Subagentes (`maligno/...`) |
|---|---|
| Métodos obrigatórios | `metodos-necessidade` (decide quais fazer), `tostring`, `equals`, `hashcode`, `compareto`, `clone-copia` |
| Construção e estado | `construtores`, `getters-setters`, `operacoes-imutaveis` |
| Regras transversais | `nomenclatura`, `excecoes`, `estilo`, `encapsulamento`, `javadoc`, `tipos-wrappers` |
| Entrada e saída | `teclado`, `main-menus` |
| Estruturas | `vetores`, `busca-ondeesta`, `regex`, `genericos`, `clonador-reflexao`, `pilha`, `arvore`, `conjunto`, `colecoes` |
| Hierarquia | `heranca`, `interface`, `classe-abstrata`, `enum`, `pacotes-jar` |
| Avançado e trabalhos | `threads-sockets`, `trabalho-velha`, `trabalho-forca` |
| Qualidade | `auditor` (corrige como o professor), `erratas` (bugs do material) |

**Por que um validador único para os métodos obrigatórios?** As regras de necessidade dependem umas das outras (`toString`/`equals`/`hashCode` têm o mesmo gatilho; `clone` e construtor de cópia são inseparáveis; `compareTo` precisa concordar com `equals`). Separar em 6 validadores geraria respostas contraditórias. A implementação, ao contrário, é muito diferente de um método para outro, por isso cada um tem seu subagente.

## Decisões de padronização

O material do professor diverge entre si. A v2 adota:

- **Prioridade entre fontes:** caderno (aulas de 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos.
- **Mensagens de exceção com acento** (`"Nome inválido"`, `"Modelo ausente"`).
- **`hashCode` da correção da prova:** base 1, multiplicador 2, wrapper por cast, trata negativo.
- **Constantes em `MAIUSCULAS_COM_UNDERSCORE`** (regra do caderno e da apostila).
- **Bugs do material nunca são copiados** (catálogo em `maligno/erratas`), incluindo o NullPointerException do empate em `Velha.java`.

## Dicas

- **Auditoria:** em trabalho valendo nota, peça *"rode o auditor"* antes de entregar. Ele lista onde o professor tiraria pontos (–1,5 por método obrigatório errado, faltando ou desnecessário).
- **Java 8:** o Benigno evita `var`, `record`, streams e lambdas; se aparecer algo moderno, peça para refatorar.
- **Índice de materiais:** `.opencode/indice-materiais.md` mapeia cada tema ao arquivo de origem.
