---
description: Uso de Vector e ArrayList (e outras classes de java.util do plano de ensino) no padrão do professor Maligno — tipos explícitos, cópia item a item, for-each.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Coleções (Vector, ArrayList, java.util)

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Quando usar
Só quando o enunciado permitir. Nas estruturas que o professor pede para implementar (Agenda com vetor, Pilha, Conjunto, Árvore), **a gestão é manual** com vetor; coleções aparecem só como implementação alternativa (Expl_13 Agenda2/Agenda3) e no servidor das Continhas (`ArrayList<Parceiro>`).

## Padrão (Expl_13)
```java
import java.util.*;

private Vector<Agenda.Contato> contatos;

public Agenda2 (int cap) throws Exception
{
    if (cap<=0) throw new Exception ("Capacidade inválida");

    this.contatos = new Vector<Agenda.Contato> (cap);
}
```
- Tipo repetido no `new` (**sem diamond**). Nome no plural para a coleção (`contatos`), no singular para vetor (`contato`).
- **Vector**: `insertElementAt (new Agenda.Contato(nom,tel), posicao)`, `removeElementAt (posicao)`, `elementAt (i)`, `capacity ()`, `size ()`.
- **ArrayList**: `add (posicao, x)`, `remove (posicao)`, `get (i)`, `size ()`.
- Vazia: `this.contatos.size()==0` (o professor não usa `isEmpty`).
- Sem limite: não há "Agenda cheia".
- Percurso: for-each `for (Agenda.Contato c : this.contatos)`.
- `equals`: `if (!this.contatos.equals(agd.contatos)) return false;`. `hashCode`: entra com `this.contatos.hashCode()`.
- Busca: binária com `this.contatos.get(meio)` / `elementAt(meio)` e a mesma codificação `pos+1`/`-(pos+1)`.

## Cópia (apostila p. 156–157)
**Nunca `colecao.clone()`** (cópia rasa — "shall copy"). Construtor de cópia:
```java
this.contatos = new Vector<Agenda.Contato> (modelo.contatos.capacity());
for (Agenda.Contato c : modelo.contatos)
    this.contatos.add ((Agenda.Contato)c.clone());
```
(ArrayList: `new ArrayList<Agenda.Contato> (modelo.contatos.size())`.)

## Concorrência (Continhas)
Coleção compartilhada entre threads: acesse dentro de `synchronized (this.usuarios) { ... }` — veja `maligno/threads-sockets`.

## Plano de ensino (Utilities) sem exemplo no material
`Enumeration`, `BitSet`, `Date`, `Random`, `StringTokenizer`, `Stack`, `Dictionary`, `Hashtable`, `Properties`. Só `StringTokenizer` tem exemplo (Expl_10). Para os demais, avise que é generalização.

## Alertas
- ⚠️ Agenda2/3: `toString` sem o espaço antes de `(`.
- ⚠️ `Contato` do Expl_13 tem `hashcode` minúsculo: o `hashCode` da coleção fica inconsistente com o `equals`.
- Ajuste a capacidade antes de inserções em massa (apostila).

## Fontes
`1) APOSTILA/Exemplos/Expl_13/Agenda2.java` · `1) APOSTILA/Exemplos/Expl_13/Agenda3.java` · apostila p. 156–157 · `0) PLANO DE ENSINO/Plano de Ensino.pdf`.
