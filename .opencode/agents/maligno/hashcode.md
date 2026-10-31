---
description: Implementa hashCode() no padrão da correção da prova do professor Maligno (base 1, multiplicador 2, wrappers por cast, trata negativo), para atributos, vetores, matrizes e herança.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# hashCode

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Padrão oficial do projeto (correção da P1 / Data / Horario)
```java
@Override
public int hashCode ()
{
    int retorno=1; /* um número natural qualquer, menos o zero */

    retorno=retorno*2 /* um número primo qualquer */ + ((Integer)this.cod).hashCode();
    retorno=retorno*2 + this.descr.hashCode();
    retorno=retorno*2 + this.abertura.hashCode();
    retorno=retorno*2 + ((Double)this.valor).hashCode();
    retorno=retorno*2 + ((Double)this.taxa).hashCode();

    if (retorno<0) retorno=-retorno;

    return retorno;
}
```
- Variável `retorno`, base `1`, multiplicador `2`, forma `retorno=retorno*2 + <hash do atributo>;`, uma linha por atributo, **na mesma ordem e com os mesmos atributos do `equals`**.
- Primitivo → boxing **por cast para o wrapper do tipo exato**: `((Byte)this.dia)`, `((Short)this.ano)`, `((Integer)x)`, `((Long)x)`, `((Float)x)`, `((Double)x)`, `((Character)x)`, `((Boolean)x)`, depois `.hashCode()`.
- Objeto/String → `this.atr.hashCode()`. Se puder ser `null`: `if (this.atr!=null) retorno=retorno*2 + this.atr.hashCode();`.
- Termina **sempre** com `if (retorno<0) retorno=-retorno;`.

## Estruturas
- **Vetor com quantidade**: hash da quantidade e depois só das posições usadas.
```java
retorno=retorno*2 + ((Integer)this.qtd).hashCode();

for (int i=0; i<this.qtd; i++)
    retorno=retorno*2 + this.elem[i].hashCode();
```
- **Matriz com posições null** (Tabuleiro): laço duplo, somando só as posições `!=null`.
- **Coleção**: `retorno=retorno*2 + this.contatos.hashCode();`.
- **Conjunto cujo equals ignora a ordem**: o hash também não pode depender da ordem — some os hashes dos elementos (`soma += this.elem[i].hashCode();`) e entre com a soma uma vez (generalização para não quebrar o contrato; ⚠️ o Conjunto do material quebra).
- **Herança**: se a superclasse redefine `hashCode`, comece com `int retorno=super.hashCode();` e acrescente só os atributos novos. Se a superclasse é `Object`, **nunca** use `super.hashCode()`.

## Variantes do material (não usar, só reconhecer)
- Apostila/Fracao/Agendas: `int ret=666; ret=13*ret + ...` (sem tratar negativo).
- Pilha: `666` com primo `7`; Conjunto: `1` com `13` e `7`; Deposito: constante `O_PRIMO_ESCOLHIDO = 13`.

## Alertas
- ⚠️ Apostila faz `int ret = super.hashCode()` sem herdar de nada: usa o hash de identidade e quebra o contrato equals/hashCode.
- ⚠️ `Contato` (Expl_13) chama o método de `hashcode` minúsculo: não redefine nada.
- ⚠️ TP2 faz `((Float)this.capTanque)` com campo `int`: não compila. O cast tem que ser do wrapper do tipo do campo.
- Contrato: instâncias iguais pelo `equals` ⇒ mesmo `hashCode`. Teste isso no programa de teste.

## Fontes
`4) PROVAS/Correção da P1 da TP1.txt` (linhas 72–81) · `2) EXEMPLOS E EXERCÍCIOS/1) Data/Data.java` (309–326) · `2) EXEMPLOS E EXERCÍCIOS/2) Horario/Horario.java` · `1) APOSTILA/Texto.pdf` p. 65–66.
