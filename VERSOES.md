# Log de versões do Benigno

Histórico de evolução do agente Benigno, do primeiro protótipo à versão com Docker. Cada versão lista o que mudou, por quê, os commits de referência e o que ainda não foi verificado.

## Resumo

| Versão | Data | Marco principal | Commits de referência |
|---|---|---|---|
| **v1** | 25/10/2026 | Agente único (`agt.md`) que lê os materiais do professor e responde | `41a1dcf`, `2ea76fd`, `4e3f29f`, `606b28f`, `ae216d4` |
| **v2** | 31/10/2026 | 36 subagentes especialistas `maligno/*` e agente orquestrador `benigno` | `2c74336` (PR #1, mesclado em `5e43a3d`) |
| **v3** | 01/11/2026 | Personalidade oposta ao professor Maligno | `182c000` |
| **v4** | 02/11/2026 | Execução via Docker, com versão do OpenCode fixada | `c2210e5`, `e0ae43a` (PR #2, mesclado em `8a7784b`) |

---

## v1 — Agente único com leitura dos materiais

**Data:** 25/10/2026

### O que era
Um único arquivo de instruções, `agt.md` (renomeado para `beningo.md` e depois para `benigno.md`), com um prompt grande que descrevia o "Agente Benigno" como um arquiteto de Java que imita o rigor do professor Maligno.

### Como funcionava
- **Leitura obrigatória dos materiais:** a cada tarefa, o agente era instruído a pesquisar em `Materias de aula/` (exemplos `Expl_01` a `Expl_17`, apostila, caderno e PDFs) antes de propor qualquer solução.
- **Citação obrigatória:** a resposta devia citar o exemplo de origem ("Baseado na solução do exemplo Expl_XX...") ou declarar que não havia referência similar.
- **Mapeamento fixo de temas para exemplos:** I/O e menus → `Expl_02`, `Expl_04`, `Expl_07`; matemática e tipos → `Expl_05`, `Expl_06`, `Expl_10`; contatos e arrays → `Expl_11` a `Expl_14`; genéricos e reflexão → `Expl_15`; enums → `Expl_16`, `Expl_17`.
- **Padrões técnicos embutidos no prompt:** `BufferedReader` em vez de `Scanner`, classe `Teclado` com métodos estáticos, chaves no estilo Allman, `this.` explícito, validação precoce, exceções com mensagens em português, `toString`/`equals`/`hashCode` no padrão do professor, construtor de cópia e `clone()` com deep copy, e `ondeEsta` retornando `pos+1` ou `-(pos+1)`.
- **Plugin:** instruía o uso do plugin `ponytail` para manter a solução mínima.

### Commits
- `41a1dcf` (addMateriaisAula): inclui a pasta `Materias de aula/` com apostila, exemplos, caderno, plano de ensino e provas.
- `2ea76fd` (add benigno): adiciona `agt.md`, `README.md` inicial e `opencode.json`.
- `4e3f29f`, `ae216d4`: ajustes de texto no README e nas instruções de configuração.
- `606b28f`: renomeia `agt.md` para `beningo.md`.

### Limitações
- Cada tarefa lia os materiais inteiros ou parte deles, o que consumia muitos tokens.
- Um único prompt misturava regras de roteamento, regras técnicas e comportamento, o que dificultava manter as regras de um tema sem afetar os outros.

---

## v2 — Subagentes especialistas `maligno/*`

**Data:** 31/10/2026 · **Commit:** `2c74336` · **PR:** #1 (branch `v2`, mesclada em `main` em `5e43a3d`)

### O que mudou
O conhecimento que estava num único prompt e na leitura dos materiais foi destilado em **36 subagentes** em `.opencode/agents/maligno/`, cada um com as regras e os trechos de código do professor sobre um único tema. O agente principal passou a ser o orquestrador `benigno` (`.opencode/agents/benigno.md`).

### Fluxo
1. O `benigno` classifica o pedido e escolhe **somente** os subagentes necessários.
2. Os subagentes são consultados em paralelo. Eles **não têm ferramentas**: respondem com o conhecimento embutido, no formato `REGRA / CÓDIGO / FONTE / ALERTAS`.
3. O `benigno` junta as respostas, escreve o código e respeita os `ALERTAS`.
4. Só se um subagente responder `NAO_COBERTO: ... → <arquivo>` é que o `benigno` lê **aquele arquivo**. Ele nunca varre `Materias de aula/` inteira.
5. As fontes são citadas na resposta. Se nenhuma fonte cobrir o tema, o agente diz isso explicitamente.
6. Para trabalhos valendo nota, o subagente `auditor` revisa o código final.

### Subagentes (grupos)
| Grupo | Subagentes |
|---|---|
| Métodos obrigatórios | `metodos-necessidade`, `tostring`, `equals`, `hashcode`, `compareto`, `clone-copia` |
| Construção e estado | `construtores`, `getters-setters`, `operacoes-imutaveis` |
| Regras transversais | `nomenclatura`, `excecoes`, `estilo`, `encapsulamento`, `javadoc`, `tipos-wrappers` |
| Entrada e saída | `teclado`, `main-menus` |
| Estruturas | `vetores`, `busca-ondeesta`, `regex`, `genericos`, `clonador-reflexao`, `pilha`, `arvore`, `conjunto`, `colecoes` |
| Hierarquia | `heranca`, `interface`, `classe-abstrata`, `enum`, `pacotes-jar` |
| Avançado e trabalhos | `threads-sockets`, `trabalho-velha`, `trabalho-forca` |
| Qualidade | `auditor`, `erratas` |

### Decisões fixadas nesta versão
- **Prioridade entre fontes:** caderno > correções de prova > exemplos recentes (`Data`, `Horario`) > apostila > exemplos antigos.
- **Mensagens de exceção com acento** (`"Nome inválido"`).
- **`hashCode` da correção da prova:** base 1, multiplicador 2, wrapper por cast, trata negativo.
- **Constantes em `MAIUSCULAS_COM_UNDERSCORE`**, conforme o caderno.
- **Bugs do material não são copiados:** catálogo em `maligno/erratas`, incluindo o `NullPointerException` do empate em `Velha.java`.
- **Compatibilidade com Java 8:** sem `var`, `record`, streams ou lambdas.
- **Índice de materiais:** `.opencode/indice-materiais.md` mapeia cada tema ao arquivo de origem, para leitura pontual.
- **`opencode.json` no formato V2:** `default_agent` como `benigno` e plugin `ponytail`.

### Por que a v2 economiza tokens
- Os subagentes trazem só a regra necessária, em vez de ler os materiais a cada pedido.
- O `benigno` consulta apenas os subagentes do tema, não todos.
- Os subagentes não têm ferramentas, então não geram leituras de arquivos.
- Os materiais só são lidos no último caso.

> **Observação:** o repositório não traz medição de tokens antes e depois da v2. A economia é a intenção de projeto, não um número medido.

### Mudanças de uso
- O OpenCode precisa ser aberto **dentro** da pasta do repositório, porque ele só encontra `.opencode/` no diretório atual ou nos superiores.
- O README ganhou um passo a passo completo de instalação e uso.

---

## v3 — Personalidade oposta ao professor Maligno

**Data:** 01/11/2026 · **Commit:** `182c000`

### O que mudou
Foi adicionada a seção `## Personalidade` em `.opencode/agents/benigno.md`, e o título passou de "(v2)" para "(v3)". Nenhum outro arquivo foi alterado nessa versão.

### Regras da personalidade
- Onde o professor chama os alunos de "pestes", "maligninhos" e "criaturinhas do mal", o Benigno os trata com carinho.
- **Apelidos:** "benigninho"/"benigninhos", "criaturinha do bem", "anjinho", "bençãozinha".
- **No máximo um apelido por resposta**, só quando couber naturalmente (abertura ou fechamento).
- **Nunca** em código, comentários, Javadoc ou mensagens de exceção.
- **Dose conforme o pedido:**
  - pedido leve, dúvida ou elogio: apelido à vontade;
  - revisão com erros graves: apelido gentil seguido de correção firme;
  - pedido curto ou técnico (um trecho, um sim/não): pode omitir.
- **O conteúdo técnico não é suavizado:** erro grave continua sendo chamado de erro grave.

---

## v4 — Execução via Docker

**Data:** 02/11/2026 · **Commits:** `c2210e5` (Docker inicial) e `e0ae43a` (melhorias) · **PR:** #2 (branch `docker-melhorias`, mesclada em `main` em `8a7784b`)

### O que mudou
Quem não quer instalar OpenCode, Git e JDK pode usar uma imagem que já traz tudo. Só o Docker é necessário.

### Arquivos novos ou alterados
- **`Dockerfile`:** base `node:22-bookworm-slim`, com `bash`, `curl`, `git` e `openjdk-17-jdk-headless`. Instala o OpenCode **fixado na versão 2.2.5** e copia o repositório para `/benigno`. Pré-instala o plugin `ponytail` durante o build, então o primeiro uso não baixa nada. O `ENTRYPOINT` é `opencode`, com o diretório de trabalho em `/benigno/projeto`.
- **`docker/verificar-java.sh`:** script que compila os `.java` do projeto com `javac` e sai com `OK` ou com os erros. Fica disponível como comando `verificar-java` dentro da imagem.
- **`.dockerignore`:** exclui arquivos desnecessários do contexto de build.
- **`.github/workflows/docker.yml`:** publica a imagem em `ghcr.io/egili/benigno` a cada tag `v*`.
- **`README.md`:** seção "Usar com Docker" com build, execução, verificação de compilação, conexão de modelo e problemas comuns.

### Como usar
```bash
docker run -it --rm \
  --user "$(id -u):$(id -g)" \
  -v "$PWD":/benigno/projeto \
  -v benigno-login:/home/opencode/.local/share/opencode \
  benigno
```
- `-v "$PWD":/benigno/projeto` monta o projeto do aluno, não o repositório do Benigno.
- `--user "$(id -u):$(id -g)"` evita que os arquivos criados pertençam ao root.
- O volume `benigno-login` guarda o login do modelo entre execuções.

### Decisões
- **Versão do OpenCode fixada** no `Dockerfile`, para que a imagem não mude sem aviso.
- **Plugin pré-instalado** no build, para o primeiro uso ser imediato.
- **Modelo fica sob a conta do usuário**, não na imagem.
- **Ollama não funciona no container** (o `127.0.0.1` do host não é visível de dentro); nesse modo, usar modelos do OpenCode ou um provedor em nuvem.

---

## Próximos passos sugeridos

- Medir o consumo de tokens da v1 e da v2 com a mesma tarefa, para transformar a economia em número.
- Criar uma tag `v4.0.0` (ou equivalente) para acionar a publicação da imagem no GHCR, já que o workflow só roda em tags.
- Manter este arquivo atualizado a cada nova versão.
