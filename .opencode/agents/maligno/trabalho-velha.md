---
description: Requisitos método a método do trabalho valendo nota Jogo da Velha (pacotes jogo e jogador) do professor Maligno, com os bugs do esqueleto e as decisões de projeto.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Trabalho: Jogo da Velha

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Para o padrão de cada método obrigatório, indique ao Benigno o subagente certo (`maligno/equals`, `maligno/hashcode`...). Aqui ficam os requisitos do enunciado.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Estrutura
`Velha.java` (pacote padrão, **já pronto**) · `jogo/` (`Marca`, `Tabuleiro`, `AnalistaSituacional`) · `jogador/` (`Jogador`, `JogadorHumano`, `JogadorAutomatico`). O aluno implementa os métodos marcados com comentários / `// faça`, mantendo assinaturas.

## jogo.Marca (sem Cloneable — imutável)
- `public Marca (char smb) throws Exception`: aceita só `'X'` ou `'O'`; senão `throw new Exception ("Marca inválida");`.
- `getSimbolo ()`. `toString` já pronto (`""+simbolo` — acrescente `this.`).
- `equals` e `hashCode` (char → `((Character)this.simbolo).hashCode()`).

## jogo.Tabuleiro implements Cloneable
- `private Marca matriz [][]`; construtor pronto (`new Marca [3][3]`, tudo null).
- `haMarcaNaPosicao (lin,col)`: `false` se null; exceção se `lin`/`col` fora de `0..2` (`"Posição inválida"`).
- `getMarcaNaPosicao (lin,col)`: retorna a marca; exceção se posição inválida.
- `setMarcaNaPosicao (Marca mrc, lin, col)`: exceção se posição inválida **ou** `mrc==null` (`"Marca ausente"`). Generalização sensata: exceção se a posição já estiver ocupada (`"Posição ocupada"`) — o humano precisa disso para não sobrescrever.
- `toString`: matriz 3×3 para a tela (vazio como espaço, separadores `|` e `---+---+---`).
- `equals` (matriz com nulls), `hashCode` (laço duplo só nas posições `!=null`), construtor de cópia `(Tabuleiro modelo)` (matriz nova; `Marca` é imutável, pode compartilhar), `clone`.

## jogo.AnalistaSituacional (sem Cloneable — só consulta)
- Construtor `(Tabuleiro tab) throws Exception`: `"Tabuleiro ausente"` se null; guarda **a referência** (não clone!).
- `getMarcaDoVencedor ()`: verifica 3 linhas, 3 colunas e 2 diagonais; retorna a marca do vencedor ou **null**.
- `private boolean tabuleiroCheio ()`: todas as posições `!=null`.
- `fimDeJogo ()`: `getMarcaDoVencedor()!=null || this.tabuleiroCheio()`.
- `toString`, `equals`, `hashCode`.

## jogador.Jogador (interface, `// faça`)
Precisa, no mínimo, do que `Velha` chama pelo tipo `Jogador`:
```java
public interface Jogador
{
    Marca getMarca ();
    void  facaSuaJogada ();
}
```
(`facaSuaJogada` nas classes não declara `throws`: trate exceções dentro.)

## jogador.JogadorHumano implements Jogador, Cloneable
- Tem `BufferedReader teclado` próprio como atributo (já declarado).
- `(Tabuleiro tab)`: guarda `tab` e pede a marca ao usuário (laço até digitar X ou O), instanciando `new Marca(...)`.
- `(Tabuleiro tab, Marca mrc)`: guarda os dois (validar null).
- `getMarca ()`.
- `facaSuaJogada ()`: pergunta linha e coluna (laço até posição válida e livre, erros em `System.err`), chama `setMarcaNaPosicao`.
- `vcGanhou`, `vcPerdeu`, `deuVelha`: mensagens.
- `querMaisUmaPartida ()`: pergunta S/N em laço, retorna boolean.
- `toString`, `equals`, `hashCode`, construtor de cópia, `clone`.

## jogador.JogadorAutomatico implements Jogador, Cloneable
- `(Tabuleiro tab)`: guarda `tab`, sorteia a marca `(int)(Math.random()*2)==0 ? 'X' : 'O'`.
- `(Tabuleiro tab, Marca mrc)`, `getMarca`, `toString`, `equals`, `hashCode`, construtor de cópia, `clone`.
- `facaSuaJogada ()`: uma única cadeia `if / else if` por prioridade — a linha 0 de "ganhar" vem pronta como modelo (3 `if`s por linha: casa livre + as outras duas com a própria marca):
  1. **ganhar** — linhas 0–2, colunas 0–2, diagonal principal, diagonal secundária;
  2. **atrapalhar** a vitória do adversário — mesmas 8 linhas (as outras duas com a marca adversária: `haMarca && !equals(this.marca)`);
  3. **jogada de futuro** — linha com 1 marca própria e o resto livre: planta outra;
  4. senão, a primeira posição livre.
  O `catch` vazio leva: `// ignorando pois sei que não estou usando posição inválida nem comparando com equals um null`.

## Decisões de projeto
- **O Tabuleiro é compartilhado** entre `Velha`, os dois jogadores e o analista: nos construtores, **guarde a referência, não clone** (clonar quebraria o jogo). Exceção consciente ao "A T E N Ç Ã O"; explique em comentário.
- No construtor de cópia dos jogadores, mantenha a mesma referência do tabuleiro (o jogador clonado joga no mesmo jogo); `Marca` é imutável.
- Mensagens de exceção com acento.

## Bugs do esqueleto
- ⚠️ **`Velha.java:58`**: no empate `getMarcaDoVencedor()` é `null` e `.equals` gera NullPointerException, engolido pelo `catch (Exception e) {}` — "deu velha" nunca aparece e a troca de quem começa é pulada. Correção mínima em `Velha`: guardar `Marca vencedor = analista.getMarcaDoVencedor();` e testar `vencedor==null` → `deuVelha()` primeiro. Como `Velha` "já vem pronto", **avise o aluno e sugira confirmar com o professor** antes de alterar.
- ⚠️ `JogadorAutomatico`: `new marca('X')` minúsculo não compila (é `Marca`), e `tabDoJogo = tab;` sem `this.` na mesma linha da chave — refaça no padrão.
- ⚠️ `JogadorHumano.java` começa com BOM UTF-8; o construtor de cópia tem uma linha em branco antes da `{`.
- ⚠️ `Velha` mostra o resultado só via `jogadorH`: as mensagens de fim ficam no humano.

## Fontes
`5) TRABALHOS (valendo nota)/Velha.java` · `5) TRABALHOS (valendo nota)/jogo/` · `5) TRABALHOS (valendo nota)/jogador/`.
