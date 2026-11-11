# MDD — Maligno Driven Development

> Explicação teórica. "MDD" não é um termo oficial do material de aula do professor André Luís dos Reis Gomes de Carvalho (PUC-Campinas, POO). É uma sistematização informal das exigências que aparecem repetidamente nas aulas, nos cadernos e nas correções de prova, com um toque de humor.

## 1. A ideia central

No MDD, o código não é escrito para funcionar. Ele é escrito para **passar pelo crivo do professor**. A pergunta que guia cada linha não é "isto resolve o problema?", e sim "isto está exatamente no padrão que o Maligno cobra?".

Isso tem uma consequência prática: **a forma importa tanto quanto o conteúdo**. Um programa correto que não segue o padrão perde pontos. Um programa com a estrutura certa e um detalhe errado também.

## 2. Os pilares

### 2.1 Obediência ao padrão

Cada construção do Java tem uma forma "aprovada". Desviar dela, mesmo quando funciona, é erro.

- chaves no estilo Allman (a abertura `{` na linha de baixo);
- espaço antes do parêntese em `if (`, `for (`, `while (`;
- `this.` explícito nos atributos;
- `@Override` em todo método sobrescrito;
- constantes em `MAIUSCULAS_COM_UNDERSCORE`.

### 2.2 Validação precoce

Nenhum objeto nasce inválido. Argumentos são checados no construtor e nos setters, e a exceção é lançada assim que o problema aparece, não depois.

- mensagens de exceção padronizadas e **com acento** (`"Nome inválido"`, `"Modelo ausente"`);
- a ordem das validações segue a ordem dos parâmetros;
- `throw new Exception` genérica é erro: a exceção deve dizer o que aconteceu.

### 2.3 Cuidado com objetos mutáveis

Se a classe guarda uma referência para algo que pode mudar (um vetor, uma data, outro objeto mutável), ela precisa se proteger:

- ao **receber**, copia (deep copy, não shallow);
- ao **devolver**, também copia, para que quem recebeu não altere o interno.

Esquecer essa cópia é um dos erros graves do MDD, porque o objeto deixa de ser encapsulado de fato.

### 2.4 Métodos obrigatórios, nem a mais, nem a menos

O professor cobra um conjunto fixo de métodos de uma classe de domínio, e considera igualmente errado **faltar** um deles e **criar** um que não foi pedido sem necessidade.

- `toString`, `equals`, `hashCode`, `compareTo` e `clone` entram quando o enunciado (ou a necessidade da classe) os exige;
- o `hashCode` segue o modelo da correção da prova: base 1, multiplicador 2, wrapper por cast, tratamento de negativo;
- `equals` segue a receita: identidade, `null`, `getClass()`, cast, comparação campo a campo.

### 2.5 Clone e construtor de cópia andam juntos

Quando há `clone()`, há também um construtor de cópia, e vice-versa. Um sem o outro é incompleto.

### 2.6 Comentários e Javadoc com propósito

Comentário serve para explicar o *porquê*, não o *o quê*. Javadoc é esperado em classes e métodos públicos, com a estrutura formal (`@param`, `@return`, `@throws`).

## 3. Os sintomas de um código "não-MDD"

Se você reconhecer um dos itens abaixo, o código ainda não está no padrão:

- métodos `void` no construtor;
- atributo público sem motivo;
- `static` onde deveria ser instância (ou o contrário);
- `equals` que compara só um campo quando a classe tem vários;
- coleção devolvida diretamente pelo getter;
- `catch` vazio sem justificativa;
- `System.out.println` no lugar de `System.err` dentro de tratamento de erro.

## 4. A escala de descontos

Em uma prova, o MDD se traduz em descontos. Os erros mais caros são:

| Erro | Desconto (simulado) |
|---|---|
| Falta de validação | –1,5 |
| Deep copy ausente | –1,5 |
| Método obrigatório feito sem necessidade, ou faltando | –1,5 |

Para o aluno, a regra prática é simples: **na dúvida, siga o caderno**. Quando as fontes divergem, a prioridade é caderno > correções de prova > exemplos recentes > apostila > exemplos antigos.

## 5. Crítica ao MDD

Com seriedade: o MDD não ensina Java de forma completa. Ele ensina a **escrever código que passa numa correção específica**. Isso é útil para a prova, mas o que vale para a vida profissional está por trás das regras:

- validar entradas protege o sistema;
- copiar objetos mutáveis evita efeitos colaterais difíceis de rastrear;
- seguir um padrão de equipe facilita a leitura.

Entender o *motivo* de cada regra é o que transforma o MDD em boa engenharia. Decorar o padrão sem entender o motivo é só MDD de superfície.

## 6. Resumo em uma frase

> Escreva o código que o professor espera, valide tudo cedo, copie o que é mutável, implemente exatamente o que foi pedido e explique o porquê de cada decisão.

---

*Documento de estudo. Os exemplos e critérios foram organizados a partir do padrão exigido nas aulas e correções; em caso de dúvida, consulte o material original da disciplina.*
