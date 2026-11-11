---
description: Gestão manual de vetores no padrão do professor Maligno — quantidade (qtdContatos/qtd/ultimo), cheia/vazia, inserção e remoção com deslocamento, vetores paralelos vs vetor de objetos.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Vetores com controle manual

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Regras
- Duas formas de contar:
  - **quantidade** `qtdContatos`/`qtd` iniciada em 0 na declaração (Agendas, Conjunto): posições usadas `0..qtd-1`;
  - **último** `ultimo=-1` (vazia) (Pilha, Deposito): posições usadas `0..ultimo`.
- **Capacidade = `this.vetor.length`** (sem atributo próprio). Getter opcional: `getCapacidade () { return this.nome.length; }`.
- Cheia: `this.qtd==this.vetor.length`. Vazia: `this.qtd==0`.
- Construtor recebe a capacidade e valida: `if (cap<=0) throw new Exception ("Capacidade inválida");`.
- **Agenda não cresce**: cheia ⇒ `"Agenda cheia"`. Crescer/encolher só nas estruturas genéricas (veja `maligno/pilha`).
- Percorra sempre até a quantidade usada, nunca até `length`.
- Declaração: `Tipo[] v` ou `Tipo v[]`; `new Tipo [n]`; constante vetor `{...}` **só na inicialização**.

## Inserção com deslocamento (de trás para frente)
```java
for (int pos=this.qtdContatos-1; pos>=posicao; pos--)
{
    this.nome     [pos+1] = this.nome     [pos];
    this.telefone [pos+1] = this.telefone [pos];
}

this.nome     [posicao] = nom;
this.telefone [posicao] = tel;

this.qtdContatos++;
```

## Remoção com deslocamento (fecha o buraco e anula o último)
```java
int pos;
for (pos=posicao; pos<this.qtdContatos-1; pos++)
{
    this.nome     [pos] = this.nome     [pos+1];
    this.telefone [pos] = this.telefone [pos+1];
}

this.nome     [pos] = null;
this.telefone [pos] = null;

this.qtdContatos--;
```
- `pos` declarado fora do `for` para anular a última posição.
- A posição vem de `ondeEsta` (veja `maligno/busca-ondeesta`).

## Modelos de armazenamento
- **Vetores paralelos** `String[] nome; String[] telefone;` (Expl_11, 12, 14).
- **Vetor de objetos** `Agenda.Contato[] contato` (Expl_13/Agenda1) — com um vetor só, o `for` de deslocamento não precisa de chaves.
- Desordenada (Expl_14): insere direto no fim, sem deslocar.

## Acesso por índice
```java
if (this.qtdContatos==0) throw new Exception ("Agenda vazia");
if (pos<0 || pos>=this.qtdContatos) throw new Exception ("Posição inválida");
```

## Cópia
Construtor de cópia: `this.nome = new String [modelo.nome.length];` e copia (ou clona) elemento a elemento até `qtd` — veja `maligno/clone-copia`.

## Matriz
`Marca matriz [][] = new Marca [3][3];` (tudo null). Valide `lin`/`col` em `0..2` com exceção.

## Alertas
- ⚠️ A regra antiga "sempre redimensionar" está errada: as Agendas lançam exceção quando cheias.
- ⚠️ Expl_14 tem construtor sem parâmetros com capacidade 50 como número mágico: use constante `CAPACIDADE_PADRAO`.

## Fontes
`1) APOSTILA/Exemplos/Expl_11/Agenda.java` (142–210) · `1) APOSTILA/Exemplos/Expl_12/agenda/Agenda.java` · `1) APOSTILA/Exemplos/Expl_13/Agenda1.java` · `1) APOSTILA/Exemplos/Expl_14/agenda/` · apostila p. 130–131.
