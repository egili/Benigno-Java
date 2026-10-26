# 🎓 Guia de Uso: Agente Benigno

O **Agente Benigno** não é apenas um chatbot; ele é um "espelho" do professor Maligno. Para usá-lo e tirar nota máxima, você não deve apenas pedir "faça um código", mas sim guiá-lo através dos padrões que o professor ensinou.

---

## 🛠️ 1. Como Ativar o Benigno no OpenCode

Se você já tem o OpenCode instalado, siga estes passos para "dar vida" ao agente:

1. **Crie o Agente**: No painel do OpenCode, crie um novo agente chamado `Benigno`.
2. **Instale o "Cérebro"**: No campo de **System Prompt** (Instruções), copie e cole todo o conteúdo do arquivo `agt.md` deste repositório.
3. **Ative os Plugins**: No arquivo `opencode.json` (ou nas configurações de plugins do agente), adicione:
   `{ "plugins": ["@dietrichgebert/ponytail"] }`
4. **Conecte a Base de Conhecimento**: Certifique-se de que o agente tem acesso à pasta `/Materias de aula/`. Ele usará esses arquivos para citar exemplos e não inventar padrões.

---

## 🚀 2. Como usar o Benigno em seus projetos Java

Aqui está o fluxo de trabalho para garantir que seu código seja aceito sem questionamentos pelo professor:

### Passo 1: Definição da Arquitetura
Não peça o código final de cara. Primeiro, peça para o Benigno planejar a estrutura baseando-se nos exemplos.
> **Exemplo de prompt:** *"Benigno, preciso criar um sistema de Gestão de Biblioteca. Analise a pasta de Materias de Aula e me diga qual a melhor arquitetura: deve ser como a Agenda do Expl_11 ou já devo usar a classe abstrata do Expl_14?"*

### Passo 2: Desenvolvimento por Módulos
Peça a implementação de uma classe por vez. Isso evita que a IA esqueça de algum detalhe de rigor.
> **Exemplo de prompt:** *"Agora implemente a classe `Livro`. Lembre-se do padrão de validação do Maligno, do construtor de cópia e do hashCode com base 666."*

### Passo 3: Refatoração para o "Jeito Maligno"
Se você já tem um código que funciona, mas tem medo de tirar ponto por "estilo", peça a refatoração.
> **Exemplo de prompt:** *"Este código funciona, mas não está no estilo do Maligno. Refatore-o adicionando as validações defensivas, trocando o Scanner por BufferedReader e ajustando as chaves para o estilo Allman."*

### Passo 4: Revisão Final (O "Filtro de Nota 10")
Antes de entregar, peça para o Benigno agir como o professor corrigindo a prova.
> **Exemplo de prompt:** *"Aja como o professor Maligno. Analise meu código e procure por falhas de rigor: Esqueci algum clone de objeto mutável? O equals está usando instanceof em vez de getClass()? Há algum campo sem validação no construtor?"*

---

## ⚠️ Checklist de Ouro (O que o Benigno garante)

Sempre que o Benigno gerar um código, confira se ele incluiu:
- [ ] **BufferedReader** em vez de Scanner.
- [ ] **`this.`** em todos os atributos.
- [ ] **`throw new Exception("Mensagem em Português")`** em todas as validações.
- [ ] **`equals`** comparando `getClass()` e não `instanceof`.
- [ ] **`hashCode`** usando o primo 13.
- [ ] **Construtor de cópia** + método `clone()`.
- [ ] **Citação da fonte** (ex: *"Baseado no Expl_06..."*).

---

## 🆘 Solução de Problemas Rápidos

| Problema | Solução |
| :--- | :--- |
| **O código está "moderno demais"** | Diga: *"Benigno, simplifique. Use apenas o que foi visto até o Expl_XX."* |
| **O agente esqueceu de validar** | Diga: *"Aplique o rigor defensivo do Maligno em todos os métodos."* |
| **O código está muito longo** | O plugin Ponytail já cuida disso, mas você pode dizer: *"Use o modo Ponytail para limpar a redundância."* |
