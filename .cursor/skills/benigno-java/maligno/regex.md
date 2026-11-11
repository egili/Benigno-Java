---
description: Validação de formato com Pattern/Matcher no padrão do professor Maligno — regex de nome e telefone, constantes compiladas, métodos valideX.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Validação com expressões regulares

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Padrão (Expl_11, com constantes em MAIÚSCULAS)
```java
import java.util.regex.*;

public class Agenda implements Cloneable
{
    private static final String  REGEX_NOME =
    "^[A-Z][a-z]*(?: (?:[A-Z]|[a-z])[a-z]*)*$";
    private static final Pattern PADRAO_NOME =
    Pattern.compile (Agenda.REGEX_NOME);

    private static final String  REGEX_TELEFONE =
    "^(?:\\([0-9]{2}\\) )?9?[0-9]{4}-[0-9]{4}$";
    private static final Pattern PADRAO_TELEFONE =
    Pattern.compile (Agenda.REGEX_TELEFONE);

    public static void valideNome (String nom) throws Exception
    {
        if (nom==null)
            throw new Exception ("Nome ausente");

        if (!Agenda.PADRAO_NOME.matcher(nom).matches())
            throw new Exception ("Nome inválido");
    }

    public static void valideTelefone (String tel) throws Exception
    {
        if (tel==null)
            throw new Exception ("Telefone ausente");

        if (!Agenda.PADRAO_TELEFONE.matcher(tel).matches())
            throw new Exception ("Telefone inválido");
    }
}
```
- Nome: começa com maiúscula, sem acento, palavras separadas por um espaço ("Joao da Silva").
- Telefone: `(DD) ` opcional, `9` opcional, `XXXX-XXXX`.
- `Pattern` compilado **uma vez** como `static final`; `Matcher` nunca é declarado — só `PADRAO.matcher(x).matches()`.
- Validação: primeiro `null`, depois o regex, cada um com seu `throw`.
- Métodos `valideX` são `static`, chamados qualificados: `Agenda.valideNome (nom);`.

## Onde colocar
- Classe única (Expl_11): `public static` na própria classe.
- Herança (Expl_12/14): constantes e validadores `protected static` (ou `public static`) na base.
- Interface (Expl_13): `public static` dentro da classe membro `Contato`; os setters do Contato validam e o construtor usa os setters.

## Outros formatos
Para CPF, e-mail, placa etc. siga o mesmo esqueleto (constante `REGEX_X`, `PADRAO_X`, `valideX`). O material não tem esses regex: avise que é generalização.

## Alertas
- ⚠️ Material usa `regExNom`/`padraoNom` em camelCase: o caderno manda MAIÚSCULAS.
- ⚠️ `descarteContato` do Expl_11/12 chama `valideNome` **e** repete o `matcher`: valide uma vez só.
- O regex de nome rejeita acentos e nomes com uma letra minúscula inicial; avise o usuário se o domínio precisar de acentos.

## Fontes
`1) APOSTILA/Exemplos/Expl_11/Agenda.java` (15–60) · `1) APOSTILA/Exemplos/Expl_13/Agenda.java` (97–151) · `1) APOSTILA/Exemplos/Expl_14/agenda/Agenda.java` (20–73).
