---
description: Entrada de dados do teclado no padrão do professor Maligno — BufferedReader/InputStreamReader e a classe utilitária Teclado (getUmInt, getUmChar...) completa.
mode: subagent
permissions:
  - action: "*"
    resource: "*"
    effect: deny
---

# Entrada de dados e classe Teclado

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes divergentes: caderno (aulas 2026) > correções de prova > exemplos recentes (Data, Horario) > apostila > exemplos antigos. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção com acento; `hashCode` da correção da prova (base 1, ×2); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → <arquivo de "Fontes" ou maligno/<outro>>`. Caminhos relativos a `Materias de aula/`.

## Regras
- **Nunca `Scanner`**. Sempre `BufferedReader` sobre `InputStreamReader (System.in)`.
- Programa pequeno (Expl_02–04, Agendas): leitor local no `main`, que declara `throws IOException` ou trata cada `readLine` com `try {} catch (IOException erro) {}`.
```java
BufferedReader teclado = new BufferedReader (
                         new InputStreamReader (
                         System.in));
```
- Projeto médio/grande (Forca, Velha, Continhas): classe **`Teclado`**, não replicável (tudo `static`).

## Classe Teclado (Expl_07 / Forca)
```java
import java.io.*;

public class Teclado
{
    private static BufferedReader teclado =
                   new BufferedReader (
                   new InputStreamReader (
                   System.in));

    public static String getUmString ()
    {
        String ret=null;

        try
        {
            ret = teclado.readLine ();
        }
        catch (IOException erro)
        {} // sei que não vai dar erro

        return ret;
    }

    public static int getUmInt () throws Exception
    {
        int ret=0;

        try
        {
            ret = Integer.parseInt (teclado.readLine ());
        }
        catch (IOException erro)
        {} // sei que não vai dar erro
        catch (NumberFormatException erro)
        {
            throw new Exception ("Int inválido!");
        }

        return ret;
    }

    public static char getUmChar () throws Exception
    {
        char ret=' ';

        try
        {
            String str = teclado.readLine ();

            if (str==null)
                throw new Exception ("Char inválido!");

            if (str.length() != 1)
                throw new Exception ("Char inválido!");

            ret = str.charAt(0);
        }
        catch (IOException erro)
        {} // sei que não vai dar erro

        return ret;
    }
}
```
- Mesma receita para `getUmByte` (`ret=(byte)0`, `Byte.parseByte`, `"Byte inválido!"`), `getUmShort` (`(short)0`), `getUmLong` (`0L`), `getUmFloat` (`0.0F`), `getUmDouble` (`0.0`).
- `getUmBoolean`: lê, `if (str==null) throw ...`, aceita só `"true"`/`"false"` com `equals` (senão `"Boolean inválido!"`), então `Boolean.parseBoolean (str)`.
- `getUmString` **não** tem `throws` e devolve `null` em erro.
- `IOException` sempre engolida com `// sei que não vai dar erro`; `NumberFormatException` vira `Exception`.

## Leitura validada em laço (Expl_04 / Agendas)
```java
for (;;)
{
    try
    {
        System.out.print ("Capacidade: ");
        cap = Teclado.getUmInt ();
        agenda = new Agenda (cap);
        break;
    }
    catch (Exception erro)
    {
        System.err.println (erro.getMessage());
        System.err.println ("Tente novamente...");
    }
}
```

## Alertas
- ⚠️ Expl_07 `getUmChar` não testa `str==null`; a versão da Forca testa — use a da Forca.
- ⚠️ Expl_02 comenta `Float.valueOf(...).intValue()` como alternativa: o certo seria `floatValue()`.
- `JogadorHumano` (Velha) tem um `BufferedReader` próprio como atributo: aceito, mas no projeto com `Teclado` prefira a classe.

## Fontes
`1) APOSTILA/Exemplos/Expl_07/Teclado.java` · `2) EXEMPLOS E EXERCÍCIOS/4) Forca/Teclado.java` · `1) APOSTILA/Exemplos/Expl_02/Main.java` · `1) APOSTILA/Exemplos/Expl_04/Main.java` · apostila p. 32–36.
