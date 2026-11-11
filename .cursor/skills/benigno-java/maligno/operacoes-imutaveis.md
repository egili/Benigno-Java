---
description: Implementa classes de valor imutáveis com operações que devolvem nova instância (mais, menos, vezes, divididoPor) no padrão do professor Maligno — Fracao, Complexo e similares.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Operações imutáveis (Fracao, Complexo)

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Regras
- Operações aritméticas são **substantivos/preposições**: `mais`, `menos`, `vezes`, `divididoPor`. Recebem uma instância da mesma classe e **devolvem uma nova**; **nunca alteram o `this`**.
- Primeiro comando: validar o operando — `if (c==null) throw new Exception ("2º operando ausente");`.
- Divisão: também `if (<operando é zero>) throw new Exception ("Divisão por zero");`.
- "Quaisquer eventuais erros devem ser sinalizados com o lançamento de exceções" (simulado).
- Consequência (pegadinha do simulado): sem setters e sem alteradores ⇒ **sem `clone` e sem construtor de cópia**; `compareTo` só se houver ordem (Fracao sim, Complexo não).

## Padrão (correção do simulado)
```java
public class Complexo
{
    private double a, b;

    public /*void*/ Complexo (double a, double b)
    {
        this.a=a;
        this.b=b;
    }

    public Complexo mais (Complexo c) throws Exception
    {
        if (c==null) throw new Exception ("2º operando ausente");

        double aDoRet = this.a + c.a,
               bDoRet = this.b + c.b;

        Complexo retorno = new Complexo (aDoRet,bDoRet);
        return retorno;
    }

    public Complexo divididoPor (Complexo c) throws Exception
    {
        if (c==null) throw new Exception ("2º operando ausente");
        if (c.a==0 && c.b==0) throw new Exception ("Divisão por zero");

        double den    = c.a*c.a + c.b*c.b,
               aDoRet = (this.a*c.a + this.b*c.b)/den,
               bDoRet = (c.a*this.b - this.a*c.b)/den;

        return new Complexo (aDoRet,bDoRet);
    }
}
```
Fórmulas do simulado: produto `a=a1a2−b1b2, b=a1b2+a2b1`; divisão `a=(a1a2+b1b2)/(a2²+b2²), b=(a2b1−a1b2)/(a2²+b2²)`.

## Fracao (Expl_06 → 10)
- Atributos `long numerador, denominador`; construtor rejeita denominador zero (`"Denominador zero"`), normaliza o sinal no denominador e chama `this.simplifiqueSe ();` (`private`).
- Operações: locais com vírgula e alinhadas, `Fracao resultado = new Fracao (numerador, denominador); return resultado;`.
- Mensagens do operando nulo, por operação: `"Falta de operando em soma"`, `"... em subtração"`, `"... em multiplicação"`, `"... em divisão"`; divisão por fração zero: `"Divisão por zero"`.
- Expl_10: sobrecarga com `long` (`mais (long numero)`) e construtor de compatibilização a partir de String.
- Auxiliares (`valorReal`, `simplifiqueSe`) são `private`: fazem parte de um serviço, não são serviço completo.

## Alertas
- ⚠️ Expl_05 (Fracao toda `static` e mutável, `someSeCom`, `multipliqueSePor`) é chamado pelo próprio professor de "erro terrível de projeto". Nunca use.
- ⚠️ `menos` lança `"Falta de operando em soma"` (copia-e-cola) em todas as versões; use a mensagem certa.
- ⚠️ `menos(long)` do Expl_10 inverte o sinal (`numero - fração`).
- ⚠️ Correção do simulado usa `Math.sqr`, que não existe (é `Math.sqrt`).
- O construtor já simplifica: chamar `resultado.simplifiqueSe()` depois é redundante.

## Fontes
`4) PROVAS/Simulado da P1.pdf` · `4) PROVAS/Correção do Simulado da P1.txt` · `1) APOSTILA/Exemplos/Expl_06/Fracao.java` · `1) APOSTILA/Exemplos/Expl_10/Fracao.java` · apostila p. 76–89 (singleton/static).
