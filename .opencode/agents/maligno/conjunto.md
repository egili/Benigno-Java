---
description: Conjunto e ConjuntoOrdenado genéricos no padrão do professor Maligno — inclua, remova, operações uniao/interseccao/menos/contem/estaContido, herança com busca binária.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Conjunto e ConjuntoOrdenado

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Organização (EX/7 Conjunto)
- Pacotes: `clonador`, `conjunto`, `conjunto.ordenado`; o programa (`Programa.java`) no pacote padrão.
- Import explícito, com o curinga comentado acima: `// import clonador.*;` + `import clonador.Clonador;`.
- `public class Conjunto <X> implements Cloneable` com atributos **`protected`** (`X[] elem`, `int qtd=0`, `int capacidadeInicial`) para a subclasse.
- `public class ConjuntoOrdenado <X extends Comparable<X>> extends Conjunto<X>`: **redefine só** `ondeEsta` (binária, `@Override`), `toString` (`[...]` sobre `super.toString()`), construtor de cópia (`super(modelo)`) e `clone` (instancia a própria classe).
- Construtores com `/*void*/`; padrão: `Conjunto()` com 10, `ConjuntoOrdenado()` com `this(15)`.

## Métodos da base
- `protected Object[] ondeEsta (X x)`: busca sequencial devolvendo `{true,i}` ou `{false,posicaoDeInsercao}` (ver `maligno/busca-ondeesta`).
- `inclua (X x)`: `"Elemento ausente"` se null; `"Elemento repetido"` se achou; cresce (dobra) se cheio; desloca à direita a partir da posição; guarda clonando se `Cloneable` (`maligno/clonador-reflexao`); `qtd++`.
- `remova (X x)`: `"Elemento inexistente"` se não achou; desloca à esquerda; anula `elem[qtd]`; encolhe à metade se `this.qtd<=(int)(0.25*this.elem.length)` e acima da capacidade inicial.
- `getElemento (int i)`: `"Posição inválida"`; devolve clonando se `Cloneable`.
- `getQtdElementos ()`.

## Operações de conjunto (todas validam `"Conjunto ausente"` e não alteram o this)
| método | resultado |
|---|---|
| `uniao (Conjunto<X> conj)` | copia o maior e inclui do menor o que não estiver lá |
| `interseccao (conj)` | percorre o menor e mantém o que está no maior |
| `menos (conj)` | elementos do this que não estão em conj |
| `contem (conj)` | true se todos de conj estão no this |
| `estaContido (conj)` | true se todos do this estão em conj |
Cada uma tem um comentário de contrato antes ("retornar uma instância de conjunto com...").

## Métodos obrigatórios
- `toString`: `{a, b, c}`.
- `equals`: mesma `qtd` e todo elemento de um está no outro (**ignora a ordem**).
- `hashCode`: padrão da prova, **independente da ordem** (some os hashes dos elementos) — ver `maligno/hashcode`.
- Construtor de cópia + `clone`.

## Alertas
- ⚠️ O `hashCode` do material depende da ordem e o `equals` não: quebra o contrato.
- ⚠️ `ConjuntoOrdenado.toString` usa `subString` (não existe): é `substring`.
- ⚠️ As operações sempre criam `new Conjunto<X>`: o resultado de um ConjuntoOrdenado não é ordenado. Generalização: a subclasse redefine as operações ou a base instancia via `this.clone()` + limpeza.
- ⚠️ O `equals` do material compara `capacidadeInicial`: não faz parte do estado; não compare.
- Casts `(X)` redundantes em `menor.elem[i]`: dispensáveis.

## Fontes
`2) EXEMPLOS E EXERCÍCIOS/7) Conjunto/conjunto/Conjunto.java` · `2) EXEMPLOS E EXERCÍCIOS/7) Conjunto/conjunto/ordenado/ConjuntoOrdenado.java` · `2) EXEMPLOS E EXERCÍCIOS/7) Conjunto/Programa.java`.
