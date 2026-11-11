---
description: Implementa toString() no padrão do professor Maligno (relatório com rótulos, formatos de data, frações, complexos, coleções e jogos).
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# toString

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Regras
- Assinatura: `@Override` + `public String toString ()`. `@Override` é obrigatório (caderno p. 8).
- Só **monta e retorna** a String. Nunca imprime nada, nunca altera o `this`, nunca lança exceção.
- É chamado implicitamente na concatenação (`System.out.println (agenda);`).
- Mostra os atributos que importam para quem usa a classe; atributos sempre via `this.`.
- Em laço, acumule: `String ret="";` e `ret += ...;`.

## Formatos do material
**Relatório (correção da prova, padrão para classes de negócio)** — rótulos com pontos alinhados, `"\n"` entre linhas, **sem `\n` no final**:
```java
@Override
public String toString ()
{
    return "Código.....: " + this.cod      + "\n" +
           "Descrição..: " + this.descr    + "\n" +
           "Abertura...: " + this.abertura + "\n" +
           "Valor......: " + this.valor    + "\n" +
           "Taxa.......: " + this.taxa;
}
```
**Data/Horario** — zero à esquerda com ternário, um operando por linha; ano negativo vira "ac":
```java
return (this.dia<10?"0":"")+
        this.dia + "/" +
       (this.mes<10?"0":"")+
        this.mes + "/" +
       (this.ano<0?(-this.ano)+"ac":this.ano);
```
**Fracao**: casos especiais `"1"`, `"-1"`, inteiro (`"" + this.numerador` se denominador 1), senão `numerador + "/" + denominador`.
**Complexo (simulado)**: `"0"` se tudo zero; omite a parte nula; `'+'` entre as partes só se a≠0 e b>0; `"i"` em vez de `"1i"` quando b==1 — trate também b==−1 (`"-i"`), que a correção esqueceu.
**Coleções**:
- Agenda: uma linha por contato, `nome + " (" + telefone + ")\n"`.
- Conjunto: `{a, b, c}`; ConjuntoOrdenado: `[a, b, c]` (reaproveita `super.toString()`); Deposito: `{a,b,c}`.
- Pilha: `(this.ultimo+1) + " elemento(s)"` e, se não vazia, `", sendo o ultimo " + this.elemento[this.ultimo]`.
- Árvore: em ordem, recursivo em par (privado com `No r`), `"(" + esq + " " + info + " " + dir + ")"`, `"()"` para nó nulo.
**Forca/Velha**:
- `Tracinhos`: caracteres intercalados com espaço (`_ _ A _`).
- `ControladorDeLetrasJaDigitadas`: letras separadas por vírgula.
- `ControladorDeErros`: `this.qtdErr + " de " + this.qtdMax`.
- `Marca`: `"" + this.simbolo`.
- `Tabuleiro`: a matriz 3×3 formatada para a tela (posição vazia como espaço, linhas e colunas separadas por `|` e `-`) — o formato visual exato fica a critério, desde que legível.

## Alertas
- ⚠️ Agenda2/3 (Expl_13) esquecem o espaço antes do `(` — não copie.
- Não imprima com `System.out` dentro do toString.
- Para vetores, percorra até a quantidade usada (`qtd`/`ultimo`), nunca até `length`.

## Fontes
`4) PROVAS/Correção da P1 da TP1.txt` · `2) EXEMPLOS E EXERCÍCIOS/1) Data/Data.java` · `4) PROVAS/Correção do Simulado da P1.txt` · `1) APOSTILA/Exemplos/Expl_06/Fracao.java` · `2) EXEMPLOS E EXERCÍCIOS/7) Conjunto/conjunto/Conjunto.java` · `2) EXEMPLOS E EXERCÍCIOS/5) Pilha/Pilha.java`.
