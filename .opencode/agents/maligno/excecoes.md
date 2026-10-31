---
description: Lógica de exceções do professor Maligno — throw new Exception genérica, mensagens padronizadas com acento, ordem das validações, catch vazio justificado, System.err.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Exceções

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Regras
- **Sempre** `throw new Exception ("Mensagem");` — `Exception` genérica (checked). Nunca exceção própria, nunca `IllegalArgumentException`/`RuntimeException`.
- Método/construtor que lança declara `throws Exception`.
- **Todo** método e construtor valida seus parâmetros e o estado **antes** de alterar qualquer coisa. Nenhuma alteração parcial.
- Quem chama um método que lança deve tratar com `try/catch` **ou** declarar `throws` (apostila p. 56–58).

## Mensagens (decisão do projeto: com acento)
Português, inicial maiúscula, **sem ponto final**, curtas, com concordância de gênero:
| Situação | Mensagem |
|---|---|
| parâmetro null / String vazia | `"Nome ausente"`, `"Data ausente"`, `"Modelo ausente"`, `"Elemento ausente"`, `"2º operando ausente"` |
| fora de faixa / formato | `"Dia inválido"`, `"Data inválida"`, `"Hora inválida"`, `"Capacidade inválida"`, `"Posição inválida"`, `"Telefone inválido"` |
| estado da estrutura | `"Agenda cheia"`, `"Agenda vazia"`, `"Nada a recuperar"`, `"Nada a remover"` |
| duplicata / inexistente | `"Nome já registrado"`, `"Elemento repetido"`, `"Nome inexistente"`, `"Elemento inexistente"` |
| matemática | `"Divisão por zero"`, `"Denominador zero"` |
| limite de uso | `"Máximo de erros atingido"` (generalização para a Forca) |
Teclado mantém o estilo com exclamação: `"Int inválido!"`, `"Char inválido!"`.

## Ordem das validações (Agendas)
- Inserção: estrutura cheia → valida cada parâmetro → já existe? (`"Nome já registrado"`).
- Remoção: estrutura vazia → valida parâmetro → existe? (`"Nome inexistente"`).
- Um `if ... throw` por linha, uma linha em branco antes do corpo.

## Captura
- No `main` (e em programas de teste): tudo dentro de `try`, e
```java
catch (Exception erro)
{
    System.err.println (erro.getMessage());
}
```
- Variável do catch: `erro` (material recente; `e` no antigo).
- **Catch vazio só onde o erro é impossível**, sempre com o comentário justificando:
  - `clone()` → `{} // sei que não vai dar erro ...`
  - `IOException` do `readLine` no Teclado → `{} // sei que não vai dar erro`
- Conversão de baixo para alto nível: `catch (NumberFormatException erro) { throw new Exception ("Int inválido!"); }`.
- Laço de leitura: `catch` imprime em `System.err` e o laço repete (`continue`/`for(;;)`).
- `Error` e `RuntimeException` não precisam de `throws`; `finally` existe mas o professor praticamente não usa.

## Onde não dá para lançar
`toString`, `equals`, `hashCode`, `compareTo` e `clone` não podem declarar `throws Exception` (assinaturas herdadas). Não lance nada neles.

## Alertas
- ⚠️ `Velha.java` envolve o jogo todo num `catch (Exception e) {}` vazio, que engole um NullPointerException no empate. Catch vazio em volta de lógica real é erro.
- ⚠️ Material tem `"Valor inválida"`, `"Excepction"`, `"Ítem ... náo fornecido"`: não copie.
- Mensagens antigas `"Modelo nao fornecido"` → use `"Modelo ausente"`.

## Fontes
`4) PROVAS/Correção da P1 da TP1.txt` · `1) APOSTILA/Exemplos/Expl_11/Agenda.java` (37–41, 142–210) · `1) APOSTILA/Exemplos/Expl_07/Teclado.java` · `2) EXEMPLOS E EXERCÍCIOS/5) Pilha/Pilha.java` · apostila p. 56–58.
