---
description: Método ondeEsta do professor Maligno — codificação pos+1 / -(pos+1), busca sequencial e binária, decodificação no chamador, variante Object[] do Conjunto.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Busca: ondeEsta

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Codificação
- Achou na posição `i` → retorna **`i+1`** (sempre positivo).
- Não achou, deveria ser inserido em `i` → retorna **`-(i+1)`** (sempre negativo).
- O 0 nunca aparece, então não há ambiguidade.
- Visibilidade: `private` (classe única), `protected` (herança), `protected abstract` (classe abstrata, cada subclasse busca do seu jeito).

## Decodificação no chamador
```java
// inserção
int posicao = this.ondeEsta (nom);
if (posicao>0)
    throw new Exception ("Nome já registrado");
posicao = (-posicao)-1;

// remoção
int posicao = this.ondeEsta (nom);
if (posicao<0)
    throw new Exception ("Nome inexistente");
posicao--;

// consulta booleana
public boolean haRegistroDoContato (String nom) throws Exception
{
    return this.ondeEsta(nom)>0;
}
```

## Sequencial (desordenada)
```java
protected int ondeEsta (String nom)
{
    int pos;
    for (pos=0; pos<this.qtdContatos; pos++)
        if (nom.equals(this.nome[pos]))
            return pos+1;

    return -(pos+1); // inserção no fim
}
```

## Binária (ordenada)
```java
protected int ondeEsta (String nom)
{
    int inicio     = 0,
        fim        = this.qtdContatos - 1,
        meio,
        comparacao;

    while (inicio <= fim)
    {
        meio       = (inicio + fim) / 2;
        comparacao = nom.compareTo (this.nome [meio]);

        if (comparacao == 0)
            return meio+1;
        else
            if (comparacao < 0)
                fim = meio-1;
            else
                inicio = meio+1;
    }

    return -(inicio+1);
}
```
- Com genérico `X extends Comparable<X>`: `x.compareTo(this.elem[meio])`.

## Variante do Conjunto (tupla)
`protected Object[] ondeEsta (X x)` devolve `{true, i}` ou `{false, posicaoDeInsercao}`; o chamador desempacota:
```java
Object[] onde    = this.ondeEsta(x);
boolean  achou   = (Boolean)onde[0];
int      posicao = (Integer)onde[1];
```
ConjuntoOrdenado redefine (`@Override`) com busca binária; `inclua`/`remova` da base servem às duas.

## Alertas
- ⚠️ Expl_11/12: o Javadoc fala em "ordem alfabética" mas a busca é sequencial e insere no fim — não fica ordenado. Ordenada ⇒ binária.
- `compareTo` de String diferencia maiúsculas/minúsculas.
- Agenda1 inicia `meio=0` e Agenda2/3 `comparacao=-1` sem necessidade.

## Fontes
`1) APOSTILA/Exemplos/Expl_11/Agenda.java` (87–120, 152–197) · `1) APOSTILA/Exemplos/Expl_14/agenda/ordenada/AgendaOrdenada.java` (39–61) · `1) APOSTILA/Exemplos/Expl_14/agenda/desordenada/AgendaDesordenada.java` · `2) EXEMPLOS E EXERCÍCIOS/7) Conjunto/`.
