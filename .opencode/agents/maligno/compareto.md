---
description: Implementa compareTo e Comparable<X> no padrão do professor Maligno (retorno ±666, critério de ordem total, concordância com equals).
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# compareTo / Comparable

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Quando
Só se existe um critério de ordem total natural (caderno p. 12): números, datas, horários, frações, palavras. Complexos, agendas, tabuleiros, investimentos: **não**. Na dúvida, consulte `maligno/metodos-necessidade`.

## Padrão (Data / Horario — mais recente)
```java
public class Data implements Comparable<Data>, Cloneable
...
// compara this e dat e retorna:
// um inteiro positivo se this for maior que dat;
// um inteiro negativo se this for menor que dat;
// zero se this e dat forem iguais
@Override
public int compareTo (Data dat)
{
    if (this.ano<dat.ano) return -666;
    if (this.ano>dat.ano) return  666;

    if (this.mes<dat.mes) return -666;
    if (this.mes>dat.mes) return  666;

    if (this.dia<dat.dia) return -666;
    if (this.dia>dat.dia) return  666;

    return 0;
}
```
- `implements Comparable<Classe>` (genérico) e parâmetro do tipo da classe, nunca `Object`.
- Compare do critério mais significativo para o menos (ano → mês → dia; hora → minuto → segundo).
- Retorno: `-666` / `666` / `0`. O valor exato é irrelevante: quem chama compara **sempre com 0** (`<0`, `>0`, `==0`). `-1/0/1` (Fracao) também é aceito.
- Atributo String ou outro Comparable: delegue — `return this.texto.compareTo(p.texto);` (Palavra/Forca).
- Fracao (antigo): compara `this.valorReal()` com `fracao.valorReal()` em `if/else` aninhado.

## Regras do caderno
- `compareTo` e `equals` **sempre concordam**: `compareTo==0` ⇔ `equals==true`.
- Herança: `implements` para interface; uma classe pode implementar várias (`Comparable<Data>, Cloneable`).
- Enum já tem `compareTo` (pelos ordinais): não reimplemente.

## Alertas
- `compareTo` não pode declarar `throws Exception` (a interface não permite). Não valide null com exceção checada; o material não testa null.
- ⚠️ `Horario.java` tem `@Override compareTo` mas não declara `implements Comparable<Horario>`: não compila. Sempre declare.
- ⚠️ O comentário do Horario fala em "dat" (copiado da Data): use o nome do seu parâmetro (`hor`).
- `String.compareTo` diferencia maiúsculas de minúsculas — afeta ordenação em agendas.

## Fontes
`2) EXEMPLOS E EXERCÍCIOS/1) Data/Data.java` (329–343) · `2) EXEMPLOS E EXERCÍCIOS/2) Horario/Horario.java` · `1) APOSTILA/Exemplos/Expl_06/Fracao.java` (204–216) · `2) EXEMPLOS E EXERCÍCIOS/4) Forca/Palavra.java` · caderno p. 9–11.
