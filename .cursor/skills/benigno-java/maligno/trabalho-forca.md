---
description: Requisitos método a método do exercício Jogo da Forca do professor Maligno (Palavra, Tracinhos, ControladorDeErros, ControladorDeLetrasJaDigitadas, BancoDePalavras, Main) e o programa de teste exigido.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Exercício: Jogo da Forca

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Para o padrão de cada método obrigatório, indique o subagente certo (`maligno/equals`, `maligno/clone-copia`...).
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## INSTRUCOES.txt
- Prontos: `Main`, `Teclado`, `BancoDePalavras` (seu único método é `static`).
- Nos demais, o comentário dentro de cada método diz o que fazer.
- **Exigido**: um programa de teste (classe com main) que teste **COMPLETAMENTE** cada classe, "uma vez que nem todos os métodos ... são usados no jogo" (ver `maligno/main-menus`).
- Arquivos em Latin-1: acentos aparecem como `�` se abertos em UTF-8.

## Palavra implements Comparable<Palavra> (imutável, sem clone)
- `String texto`. Prontos: `getTamanho`, `toString`, `compareTo` (`this.texto.compareTo(palavra.texto)`).
- Construtor: exceção se texto `null` ou vazio (`"Palavra ausente"`).
- `getQuantidade (char letra)`: quantas vezes a letra aparece.
- `getPosicaoDaIezimaOcorrencia (int i, char letra)`: `i=0` é a 1ª ocorrência; exceção se não houver a i-ésima (`"Ocorrência inexistente"`).
- `equals`, `hashCode`.

## Tracinhos implements Cloneable
- `char texto []`.
- Construtor `(int qtd)`: exceção se `qtd<=0` (`"Quantidade inválida"`); cria o vetor e preenche com `'_'`.
- `revele (int posicao, char letra)`: exceção se `posicao<0 || posicao>=this.texto.length` (`"Posição inválida"`) ou se ali não há mais `'_'` (`"Posição já revelada"`); senão coloca a letra.
- `isAindaComTracinhos ()`.
- `toString`: caracteres intercalados com espaço.
- `equals`, `hashCode` (laço no vetor), construtor de cópia (**vetor novo**, cópia elemento a elemento), `clone`.

## ControladorDeErros implements Cloneable
- `int qtdMax, qtdErr=0`. `toString` pronto: `this.qtdErr + " de " + this.qtdMax`.
- Construtor: exceção se `qtdMax` não positivo.
- `registreUmErro ()`: exceção se `qtdErr==qtdMax` (`"Máximo de erros atingido"`), senão incrementa.
- `isAtingidoMaximoDeErros ()`.
- `equals`, `hashCode`, construtor de cópia, `clone`.

## ControladorDeLetrasJaDigitadas implements Cloneable
- `String letrasJaDigitadas`, iniciada com `""` no construtor.
- `isJaDigitada (char letra)`: percorre a String.
- `registre (char letra)`: exceção se já digitada (`"Letra já digitada"`), senão concatena.
- `toString`: letras separadas por vírgula.
- `equals`, `hashCode`, construtor de cópia, `clone` "com o construtor de cópia".

## BancoDePalavras (pronto, não replicável)
`private static String[] palavras` (12 palavras em MAIÚSCULAS); `getPalavraSorteada()` faz `new Palavra(palavras[(int)(Math.random()*palavras.length)])` em try/catch vazio. Sem métodos obrigatórios.

## Main (pronto) — o que ele espera
- Máximo de erros: `(int)(palavra.getTamanho()*0.6)`.
- Letra digitada → `Character.toUpperCase`.
- Saídas `"Palavra...: "`, `"Digitadas.: "`, `"Erros.....: "`; erros `"Essa letra ja foi digitada!"`, `"A palavra nao tem essa letra!"` em `err`.
- Fim: `"Que pena! Voce perdeu! A palavra era "` / `"Parabens! Voce ganhou! A palavra era mesmo "`; "Deseja jogar de novo (S/N)?" em `for(;;)`.
- Para cada letra certa, o Main revela todas as ocorrências usando `getQuantidade` + `getPosicaoDaIezimaOcorrencia`.

## Alertas
- Ao salvar com acento, converta o arquivo para UTF-8 ou compile com `-encoding ISO-8859-1` — não misture.
- `Teclado.getUmChar` da Forca já testa `null` (melhor que o do Expl_07).

## Fontes
`2) EXEMPLOS E EXERCÍCIOS/4) Forca/INSTRUCOES.txt` · `2) EXEMPLOS E EXERCÍCIOS/4) Forca/*.java`.
