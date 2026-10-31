---
description: Catálogo de bugs, typos, código que não compila e afirmações tecnicamente erradas nos materiais do professor Maligno — o que NÃO copiar e como corrigir.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Erratas do material

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Responda em português, curto: `REGRA:` (o que fazer) · `CÓDIGO:` (correção) · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo>`. Caminhos relativos a `Materias de aula/`.

## Não compila
| Onde | Problema | Correção |
|---|---|---|
| `2) EXEMPLOS E EXERCÍCIOS/1) Data/Data.java` (188, 309) | falta `;`; `/* */` aninhado no comentário do hashCode fecha cedo; chama `adianteUmDia` (é `avanceUmDia`) | use só como referência de padrão |
| `2) EXEMPLOS E EXERCÍCIOS/2) Horario/Horario.java` | `@Override compareTo` sem `implements Comparable<Horario>`; `int resto=horario%86400` com `long`; falta `;` (:129) | declare o implements; use `long` |
| `2) EXEMPLOS E EXERCÍCIOS/3) Aluno/Aluno.java` | pseudocódigo (`...`), `throw Exception` sem `new`; Main passa `int` onde espera `byte` | |
| `1) APOSTILA/Exemplos/Expl_10/TesteDeFracao.java` (39) | chama `valorReal()` privado | |
| `1) APOSTILA/Exemplos/Expl_17/MeuPeso.java` (11) | `,` no lugar de `;` | use `Main.java` |
| `2) EXEMPLOS E EXERCÍCIOS/6) Arvore/ArvoreBinariaDeBusca.java` | remoção termina em `else // ...`; `equals(No,No)` sem return | exercício incompleto de propósito |
| `2) EXEMPLOS E EXERCÍCIOS/7) Conjunto/conjunto/ordenado/ConjuntoOrdenado.java` (55) | `subString` | `substring` |
| `2) EXEMPLOS E EXERCÍCIOS/08) Interface Agenda/Agenda.java` (15) | `protected class` dentro de interface | `public` |
| `5) TRABALHOS (valendo nota)/jogador/JogadorAutomatico.java` | `new marca('X')` | `new Marca('X')` |
| `4) PROVAS/Correção da P1 da TP2.txt` | `Excepction`; `)` sobrando; `((Float)` em campo `int`; clone sem `return` | |
| `4) PROVAS/Correção do Simulado da P1.txt` | `Math.sqr` | `Math.sqrt` |

## Compila mas está errado
- `Velha.java:58`: NPE no empate engolido pelo catch vazio — "deu velha" nunca aparece.
- `Expl_13 Contato.hashcode()` minúsculo: não redefine `hashCode`.
- Apostila/Fracao/Agendas/Deposito: `int ret = super.hashCode()` sem herança → hash de identidade, quebra equals/hashCode.
- Conjunto: `equals` ignora ordem, `hashCode` depende da ordem.
- `AgendaConsultavel`: não redefine `clone` (devolve `Agenda`).
- Fracao: `menos` lança `"Falta de operando em soma"`; `menos(long)` inverte o sinal; `setDenominador` não normaliza sinal; hash pode divergir do equals com denominador negativo.
- Expl_11/12: busca sequencial insere no fim, mas o Javadoc promete ordem alfabética.
- `descarteContato` (Expl_11/12) valida o nome duas vezes.
- Correção TP1: `(Data)d.clone()` clona a variável errada; `"Valor inválida"`; `Invetimento`.
- Data: construtor de cópia não incrementa o contador `static`.
- Data/Main: comentários de saída esperada errados (`19/1/1966` × `19/01/1966`; `contains(3) // false` dá true).
- Expl_04: mensagem concatenada sem espaço ("nao podem" + "ter parte").
- Expl_02: `Float.valueOf(...).intValue()` (deveria ser `floatValue()`).
- Deposito: aceita `null` em `guarde`; Javadoc de `jogueFora` promete exceção que não existe.
- Clonador: NPE se a classe for `Cloneable` sem `clone` público.
- Listagem do Mes no PDF sem `break` (o `.java` está certo).

## Afirmações da apostila/caderno que divergem do Java real
- "Não existe divisão inteira em Java" — `int/int` trunca.
- "Constante simbólica não pode ser local" — pode (`final int X = 13;` dentro de método).
- "Modificar uma variável duas vezes na expressão dá resultado indefinido" — em Java a ordem é definida.
- "Classe membro nunca é static mas é membro de classe" — só vale em interface.
- Caderno: comparar wrappers/Strings sem `new` com `==` — falha fora de −128..127 e com Strings lidas/concatenadas. Em código, `equals`.
- `new Integer(...)` está obsoleto.

## Inconsistências de estilo (o projeto padronizou)
hashCode (1/2 da prova × 666/13 × 666/7) → **prova**; constantes camelCase × MAIÚSCULAS → **MAIÚSCULAS**; `"Modelo nao fornecido"` × `"Modelo ausente"` → **"Modelo ausente"**; mensagens com/sem acento → **com acento**; `@Override` ausente no antigo → **sempre**; catch `e` × `erro` → **erro**.

## Encoding
Material mistura ISO-8859-1 (Agendas, Mes, Expl_09/10/15, Forca) e UTF-8, CRLF e LF; `JogadorHumano.java` tem BOM. Salve tudo em UTF-8 e compile com `-encoding UTF-8`.
