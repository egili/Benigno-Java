---
description: Estilo e formatação de código do professor Maligno — chaves Allman, espaço antes do parêntese, alinhamento vertical, this. explícito, @Override, /*void*/, comentários-marca, ordem dos membros, encoding.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Estilo e formatação

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Regras
- **Chaves Allman**: `{` e `}` sempre em linha própria. Catch vazio: `{}` na linha seguinte ao `catch`, com o comentário na mesma linha do `{}`.
- Indentação de **4 espaços**, sem tab.
- **Espaço antes do parêntese** em declarações, `new`, `throw new Exception (`, `System.out.println (`, `readLine ()`. Chamadas de método do domínio podem ir sem (`Data.isValida(dia,mes,ano)`); seja consistente no arquivo.
- Operadores compactos em condições e atribuições simples (estilo recente): `if (ano<1582)`, `this.dia=dia;`, `for (int i=0; i<this.qtd; i++)`.
- **Alinhamento vertical** de declarações, atribuições, índices e concatenações:
```java
private byte  dia, mes;
private short ano;

this.cod     =c;
this.descr   =d;
this.abertura=(Data)a.clone();
```
- **`this.`** sempre para atributos e métodos de instância (`this.ondeEsta (nom)`); membros `static` qualificados pela classe (`Data.qtd`, `Agenda.valideNome (nom)`).
- **`@Override`** em toda redefinição: `toString`, `equals`, `hashCode`, `compareTo`, `clone` e métodos herdados de classe/interface (caderno p. 8; correções).
- **Guardas em uma linha**: `if (x==null) throw new Exception ("X ausente");`, depois uma linha em branco antes do corpo.
- `public /*void*/ Classe (...)` lembra que construtor não tem tipo (material recente e correção do simulado).
- Comentários-marca do professor: `// altera o this`, `// não altera o this`, `// construtor de cópia`, `// sei que não vai dar erro`, `for (;;) // forever`, `// A T E N Ç Ã O`.
- Laço infinito: `for (;;)` com `break`.
- Variável de laço declarada fora quando é usada depois do laço (`int pos; for (pos=0; ...)`).
- `main`: `public static void main (String[] args)` (também aparece `String args []`).
- Comentários: poucos, e explicam o porquê. Javadoc: veja `maligno/javadoc`.

## Ordem dos membros (Data)
atributos → atributos/métodos `static` → construtores → setters/getters → métodos de negócio → `toString` → `equals` → `hashCode` → `compareTo` → construtor de cópia → `clone`.

## Alertas
- **Encoding**: o material mistura ISO-8859-1 e UTF-8. Como as mensagens agora têm acento, salve em UTF-8 e compile com `javac -encoding UTF-8 *.java` se o sistema não for UTF-8.
- ⚠️ Arquivos do material com tabs misturados (Arvore, AgendaDesordenada): não imite.
- ⚠️ `@Override` falta na Fracao, Deposito e Agendas (estilo antigo): use sempre.
- Código antigo comentado ao lado do novo é recurso didático do professor; em trabalho, só se ajudar a explicar uma escolha.

## Fontes
`2) EXEMPLOS E EXERCÍCIOS/1) Data/Data.java` · `4) PROVAS/Correção da P1 da TP1.txt` · `1) APOSTILA/Exemplos/Expl_11/Agenda.java` · caderno p. 8.
