---
description: Pilha genérica (e redimensionamento de vetor) no padrão do professor Maligno — guardeUmItem, recupereUmItem, removaUmItem, dobra quando enche e reduz à metade com 1/4.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Pilha e redimensionamento

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Estrutura (EX/5 Pilha)
```java
public class Pilha <X> implements Cloneable
{
    private static final int CAPACIDADE_PADRAO = 10;

    private X[]         elemento;
    private final int   tamanhoInicial;
    private int         ultimo=-1; // vazia
    private Clonador<X> clonador;

    public Pilha (int tamanho) throws Exception
    {
        if (tamanho<=0) throw new Exception ("Tamanho inválido");

        this.elemento       = (X[])new Object [tamanho];
        this.tamanhoInicial = tamanho;
        this.clonador       = new Clonador<X> ();
    }

    public Pilha ()
    {
        this.elemento       = (X[])new Object [Pilha.CAPACIDADE_PADRAO];
        this.tamanhoInicial = Pilha.CAPACIDADE_PADRAO;
        this.clonador       = new Clonador<X> ();
    }

    private void redimensioneSe (int novoTamanho)
    {
        X[] novo = (X[])new Object [novoTamanho];
        for (int i=0; i<=this.ultimo; i++)
            novo[i] = this.elemento[i];
        this.elemento = novo;
    }

    public void guardeUmItem (X x) throws Exception // LIFO
    {
        if (x==null) throw new Exception ("Falta o que guardar");

        if (this.ultimo+1==this.elemento.length) // cheia
            this.redimensioneSe (2*this.elemento.length);

        this.ultimo++;
        if (x instanceof Cloneable)
            this.elemento[this.ultimo]=this.clonador.clone(x);
        else
            this.elemento[this.ultimo]=x;
    }

    public void removaUmItem () throws Exception // LIFO
    {
        if (this.ultimo==-1) throw new Exception ("Nada a remover");

        this.elemento[this.ultimo] = null;
        this.ultimo--;

        if (this.elemento.length>this.tamanhoInicial &&
            this.ultimo+1<=this.elemento.length/4)
            this.redimensioneSe (this.elemento.length/2);
    }

    public boolean isCheia () { return this.ultimo+1==this.elemento.length; }
    public boolean isVazia () { return this.ultimo==-1; }
}
```
(Chaves Allman nos métodos curtos também; condensado aqui.)
- `recupereUmItem` **só lê** o topo (clonando se `Cloneable`, ver `maligno/clonador-reflexao`), lança `"Nada a recuperar"`; `removaUmItem` **só remove**. Não há `pop` combinado.
- **Crescimento**: dobra quando cheia. **Encolhimento**: metade quando ocupação ≤ 1/4, sem ficar abaixo do tamanho inicial.
- `toString`: `(this.ultimo+1) + " elemento(s)"` + `", sendo o ultimo " + topo` se não vazia.
- `equals`: compara `ultimo` e os elementos `0..ultimo`; **não** compara `tamanhoInicial`.
- `hashCode`: padrão da prova (`maligno/hashcode`) sobre `ultimo` e elementos.
- Construtor de cópia: vetor novo com o mesmo `length`, copia `0..ultimo`, copia `tamanhoInicial` e `clonador`; `"Modelo ausente"`.

## Variante da apostila (Anexo I)
Nomes `guardeUmItem`, `getUmItem`, `removaUmItem`, `getTamanho`; capacidade padrão 10; v2 dobra quando enche e reduz à metade com ≤25%.

## Fila
Não há fila no material. Generalização FIFO: `guardeUmItem` no fim, `recupereUmItem`/`removaUmItem` no início com deslocamento (ou índices circulares), mesmas mensagens. Avise que é generalização.

## Alertas
- `// System.gc();` comentado depois do redimensionamento: não é necessário (caderno: só com memória muito restrita).
- O redimensionamento copia `i<=this.ultimo`; no Conjunto é `i<this.qtd` — cuidado com o limite conforme o contador.

## Fontes
`2) EXEMPLOS E EXERCÍCIOS/5) Pilha/Pilha.java` · `2) EXEMPLOS E EXERCÍCIOS/5) Pilha/Main.java` · apostila Anexo I (exercício Pilha).
