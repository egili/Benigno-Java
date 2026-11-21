# Log de versões do Benigno

O que mudou em cada versão do Benigno.

## Resumo

| Versão | Data | Principal novidade |
|---|---|---|
| **v1** | 25/10/2026 | Primeiro Benigno: um agente que lê os materiais do professor e responde |
| **v2** | 31/10/2026 | O conhecimento foi dividido em 36 subagentes, um por tema |
| **v3** | 01/11/2026 | Personalidade carinhosa, o oposto do Maligno |
| **v4** | 02/11/2026 | Uso pelo Docker, sem instalar quase nada |
| **v5** | 05/11/2026 | Simulado e criação de testes |
| **v6** | 10/11/2026 | Cliente/servidor com o código real do Fazedor de Continhas |
| **v7** | 11/11/2026 | Versão para o Cursor |
| **v8** | 15/11/2026 | Versões para o Claude Code e o Antigravity |
| **v9** | 19/11/2026 | Banco de dados, transcrição de videoaulas e as mesmas funções em todas as plataformas |

---

## v1: o primeiro Benigno

**Data:** 25/10/2026

- Um único agente que imitava o rigor do professor Maligno.
- A cada pedido, lia os materiais de aula antes de responder e dizia de qual exemplo tirou a solução.
- Já seguia os padrões do professor: leitura do teclado com a classe `Teclado`, validações, exceções em português, `toString`, `equals`, `hashCode` e `clone`.

**Problema:** ler os materiais a cada pedido era lento e caro.

---

## v2: subagentes por tema

**Data:** 31/10/2026

- Os materiais foram lidos uma vez e resumidos em **36 subagentes**, cada um sobre um tema (`equals`, herança, pilha, enum etc.).
- O Benigno passou a só escolher quais subagentes consultar e juntar as respostas. Assim responde mais rápido e gasta menos.
- Só quando nenhum subagente sabe, ele abre um arquivo do material, e só um.
- Novidades:
  - um **auditor**, que corrige o código como o professor e aponta onde perderia pontos;
  - uma lista de **erros do material**, para o Benigno não copiar bugs do professor.

| Grupo | Temas |
|---|---|
| Métodos obrigatórios | `toString`, `equals`, `hashCode`, `compareTo`, `clone` e quando cada um é necessário |
| Construção da classe | construtores, getters e setters, operações que devolvem um objeto novo |
| Regras gerais | nomes, exceções, formatação, `public`/`private`/`static`, Javadoc, tipos |
| Entrada e saída | teclado, `main` e menus |
| Estruturas | vetores, busca, regex, genéricos, pilha, árvore, conjunto, `Vector`/`ArrayList` |
| Hierarquia | herança, interface, classe abstrata, enum, pacotes e jar |
| Avançado e trabalhos | threads e sockets, Jogo da Velha, Forca |
| Qualidade | auditor, erros do material |

---

## v3: personalidade carinhosa

**Data:** 01/11/2026

- Onde o professor chama os alunos de "pestes" e "maligninhos", o Benigno chama de "benigninho", "criaturinha do bem" ou "anjinho".
- No máximo um apelido por resposta, e nunca dentro do código.
- O carinho não esconde erro: erro grave continua sendo chamado de erro grave.

---

## v4: uso pelo Docker

**Data:** 02/11/2026

- Quem não quer instalar nada além do Docker pode usar o Benigno por uma imagem pronta, que já traz o agente, o Java e o Git.
- Os arquivos criados aparecem na pasta do seu projeto.
- O login no modelo fica salvo entre um uso e outro.
- Um comando confere se todo o seu código compila no Java 8, o da disciplina.

**Limitação:** modelos que rodam no seu próprio computador (como o Ollama) não funcionam dentro do Docker.

---

## v5: simulado e testes

**Data:** 05/11/2026

- **Simulado:** um subagente com os requisitos de um simulado do professor.
- **Testes:** para cada classe, o Benigno pode criar uma classe de teste simples, sem bibliotecas extras, que mostra `OK` ou `FALHOU` em cada verificação.

---

## v6: Fazedor de Continhas

**Data:** 10/11/2026

- O subagente de cliente/servidor passou a usar o código real do professor do Fazedor de Continhas, e não só o caderno.
- Três erros desse código foram anotados para não serem copiados.

---

## v7: Cursor

**Data:** 11/11/2026

- O Benigno passou a funcionar no editor Cursor.
- Como o Cursor não tem subagentes separados, o próprio modelo lê os arquivos de cada tema quando precisa.

---

## v8: Claude Code e Antigravity

**Data:** 15/11/2026

- O Benigno passou a funcionar no Claude Code e no Google Antigravity, com os subagentes consultados ao mesmo tempo, como no OpenCode.
- Ganhou complementos que economizam gasto do modelo: o **RTK** e o **context-mode**.
- Tudo vem com o projeto: basta abrir a pasta.

**Limitação:** o context-mode não funciona no Antigravity nem no Cursor.

---

## v9: banco de dados, videoaulas e as mesmas funções em todas as plataformas

**Data:** 19/11/2026

### Banco de dados
- Novo subagente sobre banco de dados em Java, feito a partir das duas videoaulas do professor e do código da pasta de BD.
- Cobre a conexão com o banco, as classes prontas do professor e as duas classes por tabela (uma para o item, outra para a tabela), além de incluir, alterar, excluir e consultar.
- Destaca o que o professor mais repete: no banco de dados tudo é numerado a partir de 1.
- Quatro erros do material do professor foram anotados para não serem copiados.
- Agora são **39 subagentes**.

### Transcrição de videoaulas
- Passe o link de uma videoaula do YouTube (pública ou não listada) e o Benigno entrega a **transcrição revisada** e um **resumo para o caderno**, na ordem da aula.
- Se for a "parte 2 de 3", ele continua de onde a anterior parou, sem repetir.
- Já conhece 19 aulas do professor. Se você não passar o link, ele mostra a lista e pergunta o tema.
- As transcrições ficam só no seu computador e não vão para o repositório.

**Limitações:** vídeo privado e vídeo sem legenda em português não funcionam.

### Mesmas funções em todas as plataformas
- OpenCode, Claude Code, Antigravity e Cursor têm agora os mesmos subagentes, as mesmas regras e as mesmas funções extras: criar prova ou simulado, montar o cliente/servidor e transcrever videoaulas.
- O **ponytail**, que mantém o código o mais simples possível, deixou de ser só do OpenCode e foi para as outras três. No Cursor ele vem numa versão reduzida, sem os comandos extras.
