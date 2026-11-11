---
name: criar-prova-maligno
description: >-
  Redige enunciados de prova/simulado no estilo do professor Maligno
  (PUC-Campinas POO): prosa seca, PROGRAMAR/ASSUMIR, sem colar assinaturas
  Java e sem dizer quais dos 6 métodos obrigatórios fazer. Use ao criar
  prova N, simulado, README de exercício no tom Maligno, ou quando o
  usuário pedir enunciado “como o Maligno escreveria”.
---

# Criar prova no estilo Maligno

Leia também, se for decidir o gabarito interno dos 6 métodos:
`../benigno-java/maligno/metodos-necessidade.md`

## Fluxo

1. Escolha o **tema** (não repetir a prova anterior: fila → pilha → depósito → outro).
2. Defina em silêncio o **gabarito interno** (quais dos 6 cabem) — isso NÃO vai no enunciado do aluno.
3. Escreva só o `README.md` (enunciado) + `Exercicio.java` vazio.
4. Atualize `logs.md` do projeto.

## Tom do enunciado (obrigatório)

- Português formal e seco.
- Frases do tipo **PROGRAMAR** / **ASSUMIR**.
- Descreva métodos em **prosa**, nunca cole a assinatura Java.

| Certo (Maligno) | Errado (Benigno demais) |
|---|---|
| um método chamado `guarde`, que recebe um `X` e lança `Exception` | `void guarde (X x) throws Exception;` |
| construtor que recebe a capacidade | `public Deposito (int cap) throws Exception { ... }` |
| atributos privados `elem` e `qtd` | tabela “fácil” explicando o que é atributo |

- Pode dizer: público, não estático, recebe, devolve, lança `Exception`.
- Mensagens: diga só que deve lançar `Exception` com mensagem **com acento** e **sem ponto final** — sem listar todas as strings.
- **Sem** traduções entre parênteses (“(texto)”, “(checa se está certo)”).
- **Sem** blocos `<details>` com código-gabarito no enunciado de prova.
- **Sem** tabelas de fórmula (`ultimo+1`, etc.) no enunciado seco — isso é modo estudo, não prova.

## Os 6 métodos — regra de ouro

O Maligno **não lista** `toString` / `equals` / `hashCode` / `compareTo` / `clone` / construtor de cópia no enunciado.

Ele descreve a **classe** (atributos, o que altera o `this`, se há ordem natural). O aluno decide. Errar a decisão custa ponto (−1,5 no simulado).

No enunciado, no máximo uma frase genérica do tipo:

> Implemente os métodos que se fizerem necessários.

**Proibido no README do aluno:**
- “faça `toString`, `equals` e `hashCode`”
- “não faça `compareTo`”
- “faça `clone` e construtor de cópia”
- checklist dos 6

**Obrigatório para o agente (só em `logs.md` ou comentário interno):** anotar o gabarito, ex.:

```
Gabarito 6 métodos: trio SIM; compareTo NÃO (sem ordem); clone+cópia SIM (tem guarde/remova).
```

## Estruturas típicas de prova

### A) Interface + classe completa
PROGRAMAR interface em prosa → PROGRAMAR classe que a implementa, com atributos e comportamento.

### B) Simulado com herança
PROGRAMAR interface → **ASSUMIR** superclasse → PROGRAMAR subclasse (`extends` / `super`).  
Na subclasse, não reexplicar o que já está na base.

### C) Classe de domínio
PROGRAMAR classe com atributos e operações; aluno decide os 6.

## Variação

- Mude tema e **nomes** (`elemento`/`ultimo` vs `elem`/`qtd` vs outros).
- Alterne `protected` (herança) e `private` (classe fechada).
- Com `<X>`, lembre o cast `(X[])new Object[n]` no gabarito interno; no enunciado basta falar em vetor de `X`.

## Arquivos gerados

```
provaN/
  README.md      ← só enunciado Maligno
  Exercicio.java ← comentário mínimo ou vazio
```

Não coloque gabarito no `Exercicio.java`.

## Modo estudo (só se o aluno pedir)

Se pedir “com dicas” / “modo Benigno” / “explica fácil”: aí sim pode haver `(tradução)` e `<details>`.  
Prova “como o Maligno” = **sem** isso.

## Checklist antes de entregar o enunciado

- [ ] Nenhuma assinatura Java colada (`void ... (...)`)
- [ ] Nenhum dos 6 métodos nomeado como obrigatório/proibido
- [ ] Prosa PROGRAMAR/ASSUMIR
- [ ] Tema/nomes diferentes da prova anterior
- [ ] Gabarito dos 6 só no log interno
- [ ] `Exercicio.java` vazio
