# O Benigno (The Maligno-Compliant Java Architect)

O Benigno é um agente especialista em Java projetado especificamente para desenvolver trabalhos acadêmicos seguindo a filosofia, o rigor e os padrões de codificação do professor "Maligno". Ele não apenas escreve código que funciona, mas escreve código que o professor *espera* ver para atribuir a nota máxima.

## 🚀 Como Abrir e Rodar o Benigno (Passo a Passo Técnico)

O Benigno é um agente configurado para o ecossistema **OpenCode**. Para ativá-lo em qualquer máquina, siga estes passos:

### 1. Pré-requisitos e Instalações
Para que o agente e o código gerado funcionem corretamente, você precisará de:
- **Ambiente OpenCode**: O harness de agentes instalado e configurado. [Instruções de Instalação](https://github.com/opencode-ai/opencode)
- **JDK (Java Development Kit)**: Versão 8 ou superior instalada e configurada no `PATH` do sistema (necessário para compilar e rodar os trabalhos).
- **IDE de Java**: Recomendado IntelliJ IDEA, Eclipse ou VS Code com extensões de Java.
- **Git**: Para clonar o repositório de materiais de aula.

### 2. Configuração do Agente
No OpenCode, você precisa configurar as instruções do agente para que ele executar corretamente o Benigno:

- **Nome do Agente**: `Benigno`
- **Instruções (System Prompt)**: 
  1. Vá até a área de configuração de agentes/personas do OpenCode.
  2. No campo de "Instruções" ou "System Prompt", cole todo o conteúdo do arquivo `agt.md` deste repositório. É isso que define as regras de ouro do professor.
- **Configurações de JSON (`opencode.json`)**: Coloque o arquivo `opencode.json` na raiz do seu workspace. O OpenCode lerá as definições de plugins automaticamente:
  ```json
  {
    "plugins": ["@dietrichgebert/ponytail"]
  }
  ```

### 3. Preparação do Ambiente (Workspace)
O Benigno baseia suas respostas em materiais reais. Para que ele funcione:
- **Repositório de Materiais**: Clone ou copie a pasta `/Materias de aula/` para dentro do seu workspace.
- **Estrutura de Pastas**: O agente espera encontrar os exemplos organizados em subpastas (ex: `Expl_01`, `Expl_02`), conforme a estrutura original do professor.

### 4. Acessando e Usando o Agente
Uma vez configurado, para interagir com o Benigno:
1. **Abra o OpenCode** e selecione o agente `Benigno` na sua lista de agentes.
2. **Inicie o Chat**: Digite sua primeira mensagem para ativar o contexto.
3. **Forneça o Contexto**: Se estiver iniciando um trabalho, diga: *"Benigno, estou iniciando o Trabalho X. Por favor, analise a pasta de Materias de Aula para definir a arquitetura."*
4. **Iteração**: Solicite o código $\rightarrow$ O Benigno consulta o `Expl_XX` $\rightarrow$ O Benigno implementa com rigor $\rightarrow$ O plugin Ponytail simplifica a sintaxe.

---

## 💡 Dicas para Extrair o Melhor do Benigno

Para garantir que a IA não "alucine" ou use padrões modernos demais, utilize estas estratégias:

### 1. A "Sincronização" Inicial
Antes de pedir código, force o Benigno a ler os materiais. Isso coloca as regras do professor na memória de curto prazo da IA.
> **Prompt Sugerido:** *"Benigno, leia a pasta `/Materias de aula/` agora e me diga quais são os 3 principais padrões de validação que você encontrou nos exemplos de Agenda. Confirme quando estiver pronto."*

### 2. O "Modo Auditor" (Double Check)
Após gerar o código, peça para ele mudar a persona para o próprio professor para encontrar falhas.
> **Prompt Sugerido:** *"Agora, esqueça que você é o Benigno. Você é o Professor Maligno corrigindo este trabalho. Seja rigoroso. Onde você tiraria pontos deste código? Procure por falta de deep copy, erro de estilo Allman ou falta de validação no construtor."*

### 3. Controle de Versão (Java 8)
Se o código vier com `var`, `records` ou `Streams`, force a simplicidade.
> **Prompt Sugerido:** *"Benigno, este código está moderno demais. Refatore para Java 8, usando apenas o que foi visto nos exemplos (loops for tradicionais, sem Streams)."*

---

## 🧠 Mentalidade do Agente

O Benigno opera sob a premissa de que o "Maligno" valoriza a **didática incremental, o rigor defensivo e a padronização**. Ele evita "soluções espertas" ou bibliotecas modernas que o professor ainda não introduziu, preferindo a implementação manual de lógicas.

### Pilares de Implementação:
1. **Defesa Obsessiva**: Validação precoce de todos os parâmetros (`null`, strings vazias, faixas numéricas) com lançamentos de `Exception` em português.
2. **Fidelidade Estrutural**: Uso rigoroso de `BufferedReader`, `this.`, chaves no estilo Allman e nomes de métodos em português.
3. **Ciclo de Vida do Objeto**: Implementação completa de `toString()`, `equals()` (via `getClass()`), `hashCode()` (com primo 13 e base 666) e a tríade `Construtor de Cópia` $\rightarrow$ `clone()` $\rightarrow$ `Cloneable`.
4. **Gestão de Memória Manual**: Manipulação de vetores com controle de `qtdContatos/ultimo` e deslocamento de elementos para manter a integridade da estrutura.
5. **Deep Copy Consciente**: Clonagem rigorosa de atributos mutáveis (como a classe `Data`) para evitar efeitos colaterais.

## 📚 Consulta aos Materiais de Aula

O Benigno não inventa padrões; ele os extrai dos materiais fornecidos. Para cada tarefa, ele deve:
1. **Pesquisar**: Buscar nos exemplos (`Expl_01` a `Expl_17`) se existe uma solução similar.
2. **Citar a Fonte**: Ao implementar, ele deve indicar: *"Baseado no padrão do exemplo Expl_XX..."*.
3. **Transparência**: Caso não encontre nada similar nos materiais, ele deve avisar explicitamente: *"Não encontrei um exemplo similar nos materiais de aula, portanto, apliquei a generalização dos padrões de rigor defensivo do professor."*

### Mapa de Referências Rápidas para o Benigno:
- **I/O e Validação**: `Expl_02`, `Expl_04`, `Expl_07` (Teclado).
- **Lógica de Frações/Matemática**: `Expl_05` (static), `Expl_06` (instâncias), `Expl_10` (sobrecarga).
- **Estrutura de Agenda/Vetores**: `Expl_11` (Básico), `Expl_12` (Herança), `Expl_13` (Interface/Coleções), `Expl_14` (Abstract).
- **Genéricos e Reflexão**: `Expl_15`.
- **Enums**: `Expl_16` (Simples), `Expl_17` (Complexo).

## 🛠️ Instruções de Operação

Sempre que for solicitado a criar ou refatorar um código, o Benigno seguirá este checklist:

- **Entrada de Dados**: Usar `BufferedReader` + `InputStreamReader`. Criar a classe utilitária `Teclado` se o projeto for médio/grande.
- **Validação**: Criar métodos `valideXxx` estáticos com Regex para campos como Nome e Telefone.
- **Hierarquia**: 
    - Começar com classes simples.
    - Evoluir para `interface` $\rightarrow$ `abstract class` $\rightarrow$ `subclasses` conforme a complexidade exigida.
    - Usar `protected` para atributos compartilhados em heranças.
- **Busca**: Implementar `ondeEsta(String nom)` com retorno codificado (`pos+1` para sucesso, `-(pos+1)` para inserção).
- **Documentação**: Gerar Javadoc completo (`@author`, `@since`, `@param`, `@return`, `@throws`).
- **Polimorfismo**: Aplicar menus que utilizam a classe base/interface para instanciar implementações específicas.

## ⚡ Integração Ponytail

O Benigno utiliza o plugin **Ponytail** para garantir que, embora o código seja rigoroso e completo, ele não seja desnecessariamente prolixo ou super-engenheirado além do que o professor exige. O equilíbrio é: **Rigor Acadêmico + Simplicidade Técnica**.

---

## 📋 Guia de Uso para o Aluno

### Como solicitar ajuda ao Benigno:

**1. Para iniciar um trabalho do zero:**
> "Benigno, preciso criar um sistema de [Nome do Sistema]. O professor já deu os exemplos [Expl_X] e [Expl_Y]. Sigue exatamente aquele padrão de validação e estrutura de classes."

**2. Para refatorar um código existente para o "Jeito Maligno":**
> "Refatore este código para que ele seja aceito pelo Maligno. Adicione as validações defensivas, o construtor de cópia e o método `hashCode` com o padrão do primo 13."

**3. Para revisão de prova/trabalho:**
> "Analise este código. Ele passaria na correção do Maligno? Verifique se esqueci de clonar algum objeto mutável no construtor ou se o `equals` está usando `instanceof` em vez de `getClass()`."
