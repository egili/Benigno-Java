# Log de versões do Benigno

Evolução das versões do agente Benigno, com as funcionalidades que mudaram em cada uma.

## Resumo

| Versão | Data | Principal novidade |
|---|---|---|
| **v1** | 25/10/2026 | Agente único que lê os materiais do professor e responde |
| **v2** | 31/10/2026 | 36 subagentes especialistas e um agente orquestrador |
| **v3** | 01/11/2026 | Personalidade oposta à do professor Maligno |
| **v4** | 02/11/2026 | Uso com Docker, sem instalar OpenCode, Git ou JDK |
| **v5** | 05/11/2026 | Subagentes do simulado da Fila e das classes de teste sem framework |
| **v6** | 10/11/2026 | Subagente de threads e sockets com o código do Fazedor de Continhas; contagem corrigida para 38 subagentes |
| **v7** | 11/11/2026 | Versão para o uso do agente pelo cursor |
| **v8** | 15/11/2026 | Versões para o Claude Code e o Antigravity, com os subagentes `maligno-*` em paralelo, e plugins RTK e context-mode para economizar tokens |
---

## v1: agente único com leitura dos materiais

**Data:** 25/10/2026

Um único agente, com um prompt grande que o descrevia como um arquiteto de Java que imita o rigor do professor Maligno.

### Funcionalidades
- **Consulta obrigatória aos materiais:** a cada tarefa, o agente lia `Materias de aula/` (exemplos `Expl_01` a `Expl_17`, apostila, caderno e PDFs) antes de propor uma solução.
- **Citação das fontes:** a resposta indicava o exemplo de origem ou avisava que não havia referência similar.
- **Mapa de temas para exemplos:** I/O e menus, matemática e tipos, contatos e arrays, genéricos e reflexão, enums.
- **Padrões do professor embutidos no prompt:** `BufferedReader` no lugar de `Scanner`, classe `Teclado`, estilo Allman, `this.` explícito, validação precoce, exceções em português, contrato de objetos (`toString`, `equals`, `hashCode`), cópia e `clone()` com deep copy e `ondeEsta`.
- **Plugin `ponytail`:** usado para manter a solução mínima.

### Limitações
- Cada tarefa podia ler muitos arquivos, o que gastava muitos tokens.
- Um único prompt misturava roteamento, regras técnicas e comportamento, o que dificultava manter uma regra sem afetar as outras.

---

## v2: subagentes especialistas

**Data:** 31/10/2026

O conhecimento que estava no prompt e nos materiais foi dividido em **36 subagentes especialistas**, cada um com as regras e os trechos de código do professor sobre um único tema. O agente principal virou um orquestrador, o `benigno`, que escolhe só os subagentes necessários para cada pedido.

### Funcionalidades
- **Roteamento:** o `benigno` classifica o pedido e consulta apenas os subagentes do tema, em paralelo.
- **Subagentes sem ferramentas:** respondem com o conhecimento embutido, no formato `REGRA / CÓDIGO / FONTE / ALERTAS`. Não leem arquivos.
- **Leitura pontual dos materiais:** só quando um subagente não cobre o tema, ele indica um arquivo, e o `benigno` lê apenas esse arquivo. Não há varredura de `Materias de aula/`.
- **Índice de materiais:** `.opencode/indice-materiais.md` mapeia cada tema ao arquivo de origem.
- **Revisão final com `auditor`:** para trabalhos valendo nota, um subagente revisa o código como o professor corrigiria e aponta os pontos perdidos.
- **Catálogo de erros do material (`erratas`):** bugs do material não são copiados.
- **Padrões fixados:** prioridade entre fontes (caderno, correções de prova, exemplos recentes, apostila, exemplos antigos), mensagens de exceção com acento, `hashCode` da correção da prova, constantes em maiúsculas, compatibilidade com Java 8.
- **README com passo a passo** de instalação e uso.

### Subagentes por grupo
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

### Por que economiza tokens
- Os subagentes trazem só a regra necessária, em vez de reler os materiais a cada pedido.
- O orquestrador consulta só os subagentes do tema.
- Os materiais são lidos apenas no último caso, e só um arquivo por vez.

> O projeto não mede o consumo de tokens. A economia é a intenção do desenho, não um número medido.

### Uso
- O OpenCode precisa ser aberto **dentro** da pasta do repositório, pois só encontra `.opencode/` no diretório atual ou nos superiores.

---

## v3: personalidade oposta ao Maligno

**Data:** 01/11/2026

O agente ganhou uma personalidade. Onde o professor chama os alunos de "pestes", "maligninhos" e "criaturinhas do mal", o Benigno os trata com carinho.

### Funcionalidades
- **Apelidos carinhosos:** "benigninho"/"benigninhos", "criaturinha do bem", "anjinho", "bençãozinha".
- **Um apelido por resposta, no máximo,** e só quando couber naturalmente, no início ou no fim.
- **Nunca em código, comentários, Javadoc ou mensagens de exceção.**
- **Dose conforme o pedido:**
  - pedido leve, dúvida ou elogio: apelido à vontade;
  - revisão com erros graves: apelido gentil seguido de correção firme;
  - pedido curto ou técnico: pode omitir.
- **Conteúdo técnico sem suavização:** erro grave continua sendo chamado de erro grave.

---

## v4: uso com Docker

**Data:** 02/11/2026

Quem não quer instalar OpenCode, Git e JDK pode usar o Benigno por uma imagem Docker que já traz tudo. Só o Docker é necessário.

### Funcionalidades
- **Ambiente pronto:** a imagem traz OpenCode (versão fixada em 2.2.5), Git, JDK 17 e o plugin `ponytail` já instalado, então o primeiro uso não baixa nada extra.
- **Projeto do aluno montado no container:** os arquivos criados pelo Benigno aparecem na pasta do aluno.
- **Arquivos com o dono correto:** a opção `--user` evita que os arquivos pertençam ao root.
- **Login salvo entre execuções:** um volume guarda a conexão com o modelo, então não é preciso conectar de novo a cada uso.
- **Verificação de compilação:** o comando `verificar-java` compila os `.java` do projeto com Java 8 e mostra os erros, se houver. Sai com `OK` quando tudo compila.
- **Seção "Usar com Docker" no README**, com build, execução e problemas comuns.

### Como usar
```bash
docker run -it --rm \
  --user "$(id -u):$(id -g)" \
  -v "$PWD":/benigno/projeto \
  -v benigno-login:/home/opencode/.local/share/opencode \
  benigno
```

### Limitações
- O modelo fica sob a conta do usuário, não na imagem.
- Modelos locais como o Ollama não funcionam dentro do container, pois ele não enxerga o `127.0.0.1` do computador. Nesse modo, use modelos do OpenCode ou de um provedor em nuvem.

---

## v5: simulado da Fila e testes sem framework

**Data:** 05/11/2026

Dois subagentes novos e um ajuste no roteamento, para acompanhar o simulado da 2ª prova e para provar que cada classe implementada funciona.

### Funcionalidades
- **Subagente `simulado-fila`:** traz os requisitos do Simulado da 2ª Prova de 2026 (`PadraoDeFila`, `Fila` e `FilaConsultavel`): o que cada método faz, quando redimensionar o vetor (dobrar ao lotar, reduzir pela metade com 25% ou menos de ocupação), o que deve lançar exceção e os pontos de atenção de cópia, `equals`, `hashCode` e `clone`.
- **Subagente `testes`:** para cada classe implementada, cria uma classe de teste executável (`<Classe>Teste.java`) que prova que os métodos funcionam como deveriam.
  - Sem JUnit nem outro framework: só Java puro e `System.out`.
  - Um `main` que imprime `OK` ou `FALHOU` para cada verificação e, no fim, o total de falhas.
  - Cobre casos de sucesso, casos de exceção e a cópia profunda de `clone` e do construtor de cópia.
- **Roteamento atualizado:** o `benigno` passa a encaminhar o simulado para `simulado-fila` e os pedidos de teste para `testes`.

### Como rodar um teste
```bash
javac *.java
java FilaTeste
```

---

## v6: código do Fazedor de Continhas

**Data:** 10/11/2026

O subagente de threads e sockets deixou de depender só do caderno. Agora ele usa o código do professor do Fazedor de Continhas (pastas `Cliente/` e `Servidor/`), que foi compilado para confirmar nomes, assinaturas e fluxo.

### Funcionalidades
- **Subagente `threads-sockets` reescrito com o código real:** `Parceiro` (com `proximoComunicado` e `mutEx`), `AceitadoraDeConexao`, `SupervisoraDeConexao`, `TratadoraDeComunicadoDeDesligamento`, cliente e servidor, com a ordem de criação das streams e as mensagens do professor.
- **Erros do material registrados em `maligno/erratas`:**
  - `Parceiro.espie` não libera o `mutEx` quando a leitura falha, e a próxima chamada trava;
  - `comando.toLowerCase()` dá NPE no Ctrl+D;
  - o servidor aceita divisão por zero, que o cliente barra.
- **Mensagens com acento:** o código do professor escreve sem acento; o subagente aplica a decisão do projeto.
- **Skill `cliente-servidor-continhas`:** fontes atualizadas (agora há `.java`) e checklist com os erros acima.
- **Índice de materiais:** o tema de threads aponta para os `.java` da pasta `29)`.
- **Agente `benigno`:** cabeçalho em v6 e roteamento de cliente/servidor com a skill.
- **README:** contagem corrigida para **38 subagentes** (estava em 36, e a v5 já tinha acrescentado dois) e as tabelas agora listam `simulado-fila` e `testes`.

### Fora desta versão
- Os repositórios de trabalhos de alunos (`CRUD-AOS`, `Cotuca-2-Semestre-2021`, `Cotuca-3-Semestre2022`, `Jogo-da-Forca-em-Java`, `estruturaDeDadosII-final`) estão no repositório como ponteiros de repositório aninhado. Não são material do professor, então não viraram fonte de subagentes.

### Limitações
- O `Parceiro` e o restante do código de threads do professor têm os bugs listados acima. Os subagentes orientam a versão corrigida, mas não reescrevem o código do material.

---

## v8: uso com Claude Code e Antigravity, e plugins RTK e context-mode

**Data:** 15/11/2026

O Benigno passou a rodar no [Claude Code](https://claude.com/claude-code) e no [Google Antigravity](https://antigravity.google), recuperando a arquitetura de subagentes em paralelo do OpenCode (que o Cursor, na v7, não tinha). Também passou a vir com dois plugins que reduzem o gasto de tokens: o **RTK**, que condensa a saída dos comandos do terminal, e o **context-mode**, que mantém saídas grandes (logs, arquivos longos, páginas) fora da conversa.

### Funcionalidades

#### Claude Code
- **Skill principal `.claude/skills/benigno-java/`:** aciona o Claude como Benigno, com o fluxo obrigatório, o roteamento, as decisões fixas e a personalidade. Delega aos subagentes com a ferramenta de agentes (Task), em paralelo.
- **38 subagentes `.claude/agents/maligno-*.md`:** gerados a partir de `.opencode/agents/maligno/*.md` (mesmo conteúdo de regras), com frontmatter do Claude Code — restritos a leitura, modelo `haiku` (e `sonnet` no `maligno-auditor`).
- **Skills auxiliares portadas:** `cliente-servidor-continhas` e `criar-prova-maligno`.
- **Plugins no `.claude/settings.json` do projeto:** registra os marketplaces `context-mode` (`mksglu/context-mode`) e `benigno` (este repositório) e habilita `context-mode@context-mode` e `rtk@benigno`. Ao abrir o `claude` na pasta e confiar nela, o Claude Code oferece instalar os dois.
- **Plugin próprio `plugins/rtk/` (marketplace `.claude-plugin/marketplace.json`):** o RTK não tem plugin oficial para o Claude Code. O plugin tem um hook `PreToolUse` no Bash que repassa o comando ao `rtk hook claude` (`git status` vira `rtk git status`) e um hook `SessionStart` que avisa o modelo de que a saída vem condensada e de quando usar `rtk bypass`. Sem o binário `rtk` no PATH, não faz nada.
- **Guia `docs/CLAUDE.md`:** instalação, plugins, uso, verificação e problemas comuns.

#### Antigravity
- **Plugin de workspace `.agents/plugins/benigno/`:** descoberto sozinho pelo Antigravity ao abrir a pasta do repositório.
  - **Skill `benigno-java`:** a mesma do Claude Code, com o trecho de delegação adaptado aos agentes personalizados do Antigravity.
  - **38 subagentes `agents/maligno-*.md`:** mesmo corpo de `.claude/agents/`, com frontmatter do Antigravity (`subagent: true`, `mainAgent: false`, só `read_file`).
  - **Skills auxiliares:** `cliente-servidor-continhas` e `criar-prova-maligno`.
  - **Regra `rules/AGENTS.md`:** sempre ativa, manda usar a skill `benigno-java` em pedidos de Java da disciplina.
- **Plugin `.agents/plugins/rtk/`:** gerado com `rtk init --agent antigravity`. O hook `PreToolUse` reescreve os comandos do terminal e não faz nada sem o binário `rtk`.
- **Guia `docs/ANTIGRAVITY.md`:** instalação, uso, verificação e problemas comuns.

#### OpenCode
- **context-mode no `opencode.json`:** carregado junto com o `ponytail`.
- **Plugin do RTK em `.opencode/plugins/rtk.ts`:** o plugin que o `rtk init --opencode` gera, agora dentro do projeto. Sem o binário `rtk` no PATH, não faz nada.
- **Skill `benigno-java` do Claude Code bloqueada no `opencode.json`:** o OpenCode também lê `.claude/skills/`, e essa skill manda delegar aos subagentes com os nomes do Claude Code (`maligno-equals`), que não existem no OpenCode (`maligno/equals`). Uma regra `permissions` com `deny` esconde a skill do modelo. As outras skills de `.claude/skills/` continuam: `cliente-servidor-continhas` já é sobrescrita pela cópia de `.opencode/skills/`, e `criar-prova-maligno` funciona igual.

#### Docker
- **Imagem em Node 24:** versão exigida pelo context-mode.
- **Binário `rtk` na imagem:** baixado da versão fixada no `Dockerfile` (`RTK_VERSION`), então o RTK funciona sem instalar nada.
- **Só os arquivos do OpenCode na imagem:** o `.dockerignore` deixa de fora `.claude/`, `.claude-plugin/`, `.agents/`, `.cursor/`, `plugins/` e `.github/`. O container só roda o OpenCode, que também lê skills de `.claude/skills/`; sem essa exclusão, a skill do Claude Code e uma cópia de `cliente-servidor-continhas` apareciam no Benigno do container.

#### Cursor
- Sem plugins: os hooks do RTK no Cursor só existem na configuração global, o context-mode tirou o suporte ao Cursor e o `ponytail` é só do OpenCode.

#### Documentação
- **README e tabela de versões:** novas linhas de Claude Code e Antigravity e menção aos plugins.
- **Tutoriais sem passo de instalação de plugins:** `ponytail`, `context-mode` e RTK vêm com o projeto em cada plataforma onde há suporte.

### Limitações
- Subagentes não disparam outros subagentes: quando um responde `NAO_COBERTO` apontando outro tema, é o agente principal que chama o próximo.
- Fora do Docker, o plugin do RTK só faz a ligação com o agente: ele age apenas se o binário `rtk` já estiver instalado no computador.
- O context-mode não entra no Antigravity: a v2 dele tirou o suporte ao Antigravity/`agy`.
- As regras são as mesmas do OpenCode; mudanças de conteúdo continuam partindo de `.opencode/agents/maligno/*.md` e precisam ser refeitas em `.claude/agents/` e `.agents/plugins/benigno/agents/`.
