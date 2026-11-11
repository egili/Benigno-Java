---
description: Javadoc no formato do professor Maligno — estrutura, ordem das tags, jargão fixo ("Constroi", "instância à qual este método for aplicado"), @author/@since, quando documentar.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Javadoc

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Quando
- Classes de domínio a partir do Expl_09 vêm documentadas (classe, atributos privados, construtores, métodos). **`Main` nunca tem Javadoc.** As correções de prova não usam Javadoc.
- Em trabalho: documente se o enunciado pedir ou se o aluno quiser o padrão completo; senão, pergunte.
- Classe documentada fica **sozinha no arquivo e é `public`**.

## Estrutura obrigatória (apostila p. 100–104)
1. linha só com `/**`
2. **uma frase** de resumo
3. parágrafos complementares
4. linha em branco
5. tags
6. linha só com `*/`
O professor **não** usa `*` no começo das linhas.

**Ordem das tags**: `@param` (um por parâmetro, na ordem) → `@return` (com as situações de cada retorno) → `@throws` (uma por exceção, com a situação) → `@author` → `@version` → `@see` → `@since`.

## Modelo
```java
/**
A classe Data representa uma data do calendário.
Instâncias desta classe permitem armazenar, avançar e retroceder
datas, validando-as segundo os calendários Juliano e Gregoriano.

@author André Luís dos Reis Gomes de Carvalho.
@since 2000.
*/
public class Data implements Comparable<Data>, Cloneable

/**
Valida o nome de um contato.
Verifica se o nome fornecido como parâmetro é um nome válido,
lançando exceções, caso incorretudes sejam detectadas.

@param nom o nome a ser validado.
@throws Exception se não for fornecido um nome, ou se o nome fornecido
                  não parecer ser um nome correto.
*/
```
- Continuação do `@throws` alinhada com o texto.
- Em trabalho de aluno, `@author` é o aluno (o modelo acima mostra o formato do professor).

## Jargão fixo
- 1ª frase com verbo na 3ª pessoa: "Constroi...", "Obtem...", "Ajusta...", "Verifica...", "Valida...", "Produz e resulta...".
- "a instância à qual este método for aplicado" (= `this`); "o chamante do método".
- hashCode: "Calcula o código de espalhamento (ou código de hash, ou ainda o hashcode)".
- Construtor de cópia: "Constroi uma cópia da instância fornecida como modelo".
- `ondeEsta`: explica a codificação `pos+1` / `-(pos+1)` com `<ol><li>`.
- Tags inline disponíveis: `{@code}`, `{@link}`, `{@literal}`, `{@inheritDoc}`.

## Alertas
- ⚠️ Javadoc copiado e colado errado no material (construtor `Agenda2()` com texto do de cópia; `AgendaOrdenada` dizendo "AgendaDesordenada"; Contato falando em "frações"). Documente o que o método realmente faz.
- ⚠️ Typos do material ("implemementada", "exclir", "AgendaConsutavel", "pasar", "ammazenado"): não copie.
- `@param` com o nome real do parâmetro (o Deposito documenta `nom` para um parâmetro `i`).
- Gere com `javadoc -d doc *.java` (com `-encoding UTF-8 -charset UTF-8` se houver acento).

## Fontes
apostila p. 100–104 · `1) APOSTILA/Exemplos/Expl_09/Fracao.java` · `1) APOSTILA/Exemplos/Expl_11/Agenda.java` (27–34, 87–100).
