---
name: maligno-banco-de-dados
description: Acesso a banco de dados em Java (JDBC) no padrão das videoaulas do professor Maligno — driver e classpath, classes prontas MeuStatement/MeuPreparedStatement/MeuResultSet, classe de conexão BDSQLServer, DBO e DAO por tabela, commit/rollback, navegação no MeuResultSet.
tools: Read
model: haiku
---

# Banco de dados em Java (JDBC, DBO e DAO)

## Protocolo
- Você não tem ferramentas: responda só com o conhecimento abaixo, sem inventar padrão.
- Fontes: o código do professor em `17) Material sobre acesso a BDs em programas feitos em Java/BD/` (compila; testado contra um banco em memória) e as duas videoaulas assíncronas de BD (transcrição). Fontes divergentes: caderno > correções de prova > videoaula > código da pasta `17)`. Nunca reproduza itens marcados ⚠️.
- Decisões do projeto: mensagens de exceção **com acento** (o professor escreve sem: "Codigo invalido" → "Código inválido", "Nao cadastrado" → "Não cadastrado"); `equals`/`hashCode` do DBO na receita do projeto (não a do `Livro`); constantes em `MAIUSCULAS_COM_UNDERSCORE`; Java 8.
- Responda em português, curto: `REGRA:` · `CÓDIGO:` · `FONTE:` · `ALERTAS:`.
- Fora do tema ou não coberto: `NAO_COBERTO: <o que falta> → 17) Material sobre acesso a BDs em programas feitos em Java/BD/Demonstração com SQL Server/` (ou `maligno/<outro>`). Métodos obrigatórios do DBO em detalhe: `maligno/metodos-necessidade`, `maligno/equals`, `maligno/hashcode`, `maligno/clone-copia`.

## Estrutura do projeto (uma pasta `bd` minúscula = pacote `bd`)
```
Programa.java              (main; usa só DAOs e DBOs)
sqljdbc4.jar               (driver, junto com o programa)
bd/BDSQLServer.java        (conexão; package bd)
bd/core/MeuStatement.java, MeuPreparedStatement.java, MeuResultSet.java   (prontas, package bd.core)
bd/dbos/Livro.java         (um DBO por tabela, package bd.dbos)
bd/daos/Livros.java        (um DAO por tabela, package bd.daos)
```
- Outros SGBDs (pasta `Outros BDs/`): troque o `.jar` e use `BDMySQL`, `BDOracle` ou `BDPostgreSQL` no lugar de `BDSQLServer`.

## Driver e compilação
- Todo programa com BD precisa do **driver do SGBD** (um `.jar` do fabricante). Ele "deve estar aqui junto com seu programa" **e** precisa ser indicado ao compilar **e** ao executar.
- No editor das aulas (menu Build → Set Build Commands; provavelmente Geany): Compile `javac -classpath ".;sqljdbc4.jar" "%f"` e Execute `java -classpath ".;sqljdbc4.jar" "%e"`. Outro ambiente: "cada ambiente é de um jeito".
- Linha de comando (decisão do projeto, mensagens com acento): `javac -encoding UTF-8 -cp ".;sqljdbc4.jar" Programa.java bd/*.java bd/*/*.java` e `java -cp ".;sqljdbc4.jar" Programa`. **`;` no Windows, `:` no Linux/macOS.**
- `import java.sql.*;` em toda classe que trata `SQLException` (os DAOs).

| SGBD | `.jar` | Driver (1º parâmetro) | String de conexão (2º) |
|---|---|---|---|
| SQL Server | `sqljdbc4.jar` | `"com.microsoft.sqlserver.jdbc.SQLServerDriver"` | `"jdbc:sqlserver://SERVIDOR:1433;databasename=BD"` |
| MySQL | `mysql5.jar` | `"com.mysql.jdbc.Driver"` | `"jdbc:mysql://SERVIDOR:3306/BD"` |
| Oracle | `ojdbc6.jar` | `"oracle.jdbc.driver.OracleDriver"` | `"jdbc:oracle:thin:@SERVIDOR:1521:orcl"` (sem `BD`; `orcl` é o SID) |
| PostgreSQL | `postgresql4225.jar` | `"org.postgresql.Driver"` | `"jdbc:postgresql://SERVIDOR/BD"` |

SGBD fora da lista: copie uma dessas classes e troque só as linhas do driver e da string de conexão, com o que o site do fabricante indicar.

## Classes prontas de `bd.core` (use, não reescreva)
- `Statement`, `PreparedStatement` e `ResultSet` de `java.sql` são **interfaces** (o professor se corrige na aula). `MeuStatement implements Statement`; `MeuPreparedStatement extends MeuStatement implements PreparedStatement`; `MeuResultSet implements ResultSet` (construtor `protected`).
- Motivo de existirem: com as nativas, um segundo `select` destrói o `ResultSet` do primeiro. As "Meu" guardam cada `MeuResultSet` e criam um comando novo, então dá para ter dois resultados vivos ao mesmo tempo.
- O construtor de `MeuStatement (drv, strCon, usr, senha)` faz `Class.forName`, `DriverManager.getConnection` e `setAutoCommit(false)` (por isso o `commit` é obrigatório), e cria cursores `TYPE_SCROLL_INSENSITIVE`/`CONCUR_READ_ONLY` (por isso `first`/`previous`/`absolute` funcionam).
- `MeuPreparedStatement`: `prepareStatement (String sql)`; `setByte/setShort/setInt/setLong/setFloat/setDouble/setString/setBoolean (int numInterrogacao, valor)`; `executeQuery ()` (devolve `ResultSet`, que de fato é `MeuResultSet`); `executeUpdate ()`; `commit ()`; `rollback ()`.
- Depois de `executeQuery` o comando preparado é descartado: **chame `prepareStatement` de novo antes de cada comando** (o padrão do DAO já faz isso).

## Classe de conexão `BDSQLServer` (package `bd`)
```java
package bd;

import bd.core.*;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
        MeuPreparedStatement comando = null;

        try
        {
            comando =
            new MeuPreparedStatement (
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://SERVIDOR:1433;databasename=BD",
            "USUARIO", "SENHA");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexão com o BD");
            System.exit(0); // aborta o programa
        }

        COMANDO = comando;
    }
}
```
- Toda aplicação com BD tem **uma** classe assim. É não instanciável (tudo `static`, nunca `new`); o resto do programa usa sempre `BDSQLServer.COMANDO.<método>`.
- `COMANDO` é `public static final`: "atributos públicos variáveis são condenáveis e reprováveis", constante pública "não tem problema nenhum". Nome em maiúsculas por ser constante.
- `static { ... }` é o que o professor chama de **"construtor" da classe**: inicializa atributos `static` quando a inicialização precisa de algoritmo (em Java: inicializador estático). A variável local `comando` existe porque a constante só pode receber valor **uma vez**: tenta na variável, depois `COMANDO = comando;` no fim.
- Troque **só** `SERVIDOR` (nome/IP da máquina do SGBD, ou `localhost`), `BD` (nome do banco, dado pelo administrador no `CREATE DATABASE`), `USUARIO` e `SENHA` (usuário **do banco**, com permissão). "Nada antes, nada depois": não apague `//`, `:`, `;` nem o `=` de `databasename=`.
- Sem conexão, o programa termina: "não dá para fazer nada".

## Tabela (criada fora do programa)
- As tabelas são criadas no SQL Server Management Studio (Mecanismo de Banco de Dados → Autenticação do SQL Server → Conectar → Nova Consulta → Executar), **não** pelo programa Java: "habitualmente a gente não usa [...] CREATE TABLE nos programas feitos em Java". O programa só inclui, exclui, altera e consulta.
```sql
CREATE TABLE Livros (
Codigo INT         PRIMARY KEY,
Nome   VARCHAR(50) NOT NULL,
Preco  SMALLMONEY  NOT NULL)
```

## Duas classes por tabela
- "Para cada tabela que existe no banco de dados, duas classes": 10 tabelas = 20 classes.
- **DBO** (DataBase Object) = **um item** da tabela, nome no **singular** (`Livro`), em `bd.dbos`.
- **DAO** (Data Access Object) = **a tabela toda**, nome no **plural** (`Livros`), em `bd.daos`.

## DBO: "receitinha de bolo"
1. Um atributo `private` por coluna, com o tipo Java correspondente.
2. Um setter que valida e lança exceção, e um getter, por atributo.
3. Construtor com todos os atributos, que chama os setters (`throws Exception` porque chama quem lança e não trata).
4. Os métodos obrigatórios (o `Livro` tem setters que alteram o `this`, então tem construtor de cópia + `clone`; `compareTo` só se houver ordem).

| SQL Server | Java | `get` no `MeuResultSet` |
|---|---|---|
| `TINYINT` | `byte` | `getByte` |
| `SMALLINT` | `short` | `getShort` |
| `INT` | `int` | `getInt` |
| `BIGINT` | `long` | `getLong` |
| `SMALLMONEY` (no `Livro`: "dinheiro tem centavos") | `float` | `getFloat` |
| real de precisão dupla | `double` | `getDouble` |
| `VARCHAR` (e coluna de um caractere só) | `String` | `getString` (**não existe `getChar`**) |
| lógico | `boolean` | `getBoolean` |

A aula só dá os nomes SQL dos inteiros e os do `Livro`; para os outros, use o tipo SQL que o enunciado der.

```java
package bd.dbos;

public class Livro implements Cloneable
{
    private int    codigo;
    private String nome;
    private float  preco;

    public void setCodigo (int codigo) throws Exception
    {
        if (codigo <= 0)
            throw new Exception ("Código inválido");

        this.codigo = codigo;
    }
    // setNome: null ou "" → "Nome não fornecido"; setPreco: <= 0 → "Preço inválido"; getters simples

    public Livro (int codigo, String nome, float preco) throws Exception
    {
        this.setCodigo (codigo);
        this.setNome   (nome);
        this.setPreco  (preco);
    }
    // toString, equals, hashCode, construtor de cópia e clone: receitas de maligno/tostring, equals, hashcode, clone-copia
}
```

## DAO: regras
- **Sem atributos**; **todos os métodos `public static ... throws Exception`**.
- Só os métodos das operações que o programa usa: "não é para você ficar achando que só porque nesse DAO Livros eu fiz incluir, tem que ter incluir em qualquer DAO". O `Livros` mostra `cadastrado (int)`, `incluir (Livro)`, `excluir (int)`, `alterar (Livro)`, `getLivro (int)`, `getLivros ()`.
- Valide **antes** do `try`: `null` → "Livro não fornecido"; `excluir`/`alterar` de código inexistente → `if (!cadastrado (...)) throw new Exception ("Não cadastrado");`.
- SQL numa `String sql`, concatenada em linhas, com **`?` no lugar de cada valor**, trocadas depois pelos `setXxx` (o professor nunca concatena o valor no SQL).
- **`executeUpdate` para tudo que não é `SELECT`** (INSERT, DELETE, UPDATE e até CREATE: "de alguma forma atualiza o banco"). **`SELECT` sempre com `executeQuery`**, com cast `(MeuResultSet)` ("revelação de tipo").
- Depois de INSERT/DELETE/UPDATE, **sempre `commit()`**. No `catch (SQLException erro)`, **`rollback()` e logo depois** `throw new Exception ("<mensagem>")`.

```java
public static void incluir (Livro livro) throws Exception
{
    if (livro==null)
        throw new Exception ("Livro não fornecido");

    try
    {
        String sql;

        sql = "INSERT INTO LIVROS " +
              "(CODIGO,NOME,PRECO) " +
              "VALUES " +
              "(?,?,?)";

        BDSQLServer.COMANDO.prepareStatement (sql);

        BDSQLServer.COMANDO.setInt    (1, livro.getCodigo ());
        BDSQLServer.COMANDO.setString (2, livro.getNome ());
        BDSQLServer.COMANDO.setFloat  (3, livro.getPreco ());

        BDSQLServer.COMANDO.executeUpdate ();
        BDSQLServer.COMANDO.commit        ();
    }
    catch (SQLException erro)
    {
        BDSQLServer.COMANDO.rollback();
        throw new Exception ("Erro ao inserir livro");
    }
}
```
- `excluir`: `"DELETE FROM LIVROS WHERE CODIGO=?"`, mensagem "Erro ao excluir livro".
- `alterar`: `"UPDATE LIVROS SET NOME=?, PRECO=? WHERE CODIGO = ?"` (vírgula entre as colunas, **um só `SET`**), sets na ordem das `?` (nome 1, preço 2, código 3), mensagem "Erro ao atualizar dados de livro".

```java
public static Livro getLivro (int codigo) throws Exception
{
    Livro livro = null;

    try
    {
        String sql;

        sql = "SELECT * " +
              "FROM LIVROS " +
              "WHERE CODIGO = ?";

        BDSQLServer.COMANDO.prepareStatement (sql);

        BDSQLServer.COMANDO.setInt (1, codigo);

        MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

        if (!resultado.first())
            throw new Exception ("Não cadastrado");

        livro = new Livro (resultado.getInt   ("CODIGO"),
                           resultado.getString("NOME"),
                           resultado.getFloat ("PRECO"));
    }
    catch (SQLException erro)
    {
        throw new Exception ("Erro ao procurar livro");
    }

    return livro;
}
```
- `cadastrado`: mesmo `SELECT`, `retorno = resultado.first();` (não interessa o conteúdo, só se há linha). Alternativa: `SELECT COUNT(*) AS QUANTOS ...`, `resultado.first(); retorno = resultado.getInt("QUANTOS") != 0;`. Mensagem "Erro ao procurar livro".
- `getLivros`: `"SELECT * FROM LIVROS"`, sem `set` (não há `?`), devolve o `MeuResultSet`; mensagem "Erro ao recuperar livros".

## MeuResultSet: o resultado é uma "tabelinha"
- Logo depois do `executeQuery` o cursor está **antes da primeira linha** (before first): `first()` ou `next()` levam à 1ª.
- Posicione primeiro, depois leia a coluna pelo **nome** (`"CODIGO"`) ou pelo **número**.
- Navegação: `beforeFirst`, `first`, `next`, `previous`, `last`, `afterLast`, `absolute (numLinha)`, `relative (deslocamento)` (negativo volta). Os que se movem devolvem `boolean` dizendo se deu para ir; `next()` devolve `false` quando acabam as linhas.
- Consulta da posição: `isBeforeFirst`, `isFirst`, `isLast`, `isAfterLast`, `getRow` (número da linha atual).
- Coluna que pode ser NULL: faça o `get` para uma variável e, **logo depois**, `if (resultado.wasNull()) ...`.
- **Em BD tudo é numerado a partir de 1**, nunca 0: `?` do `setXxx`, linhas do `absolute`, colunas do `getXxx (int)`. É o ponto que o professor mais repete.

```java
MeuResultSet resultado = Livros.getLivros ();

while (resultado.next())
    System.out.println (new Livro (resultado.getInt   ("CODIGO"),
                                   resultado.getString("NOME"),
                                   resultado.getFloat ("PRECO")));
```

## Programa
- Com DBOs, DAOs e `BDSQLServer` prontos, "fazer o programa em si que acessa o BD é muito, muito simples": cada operação é uma chamada de método do DAO.
```java
try
{
    Livros.incluir (new Livro (1,"L'Etranger",20));
    System.out.println ("Livro incluído com sucesso!");
}
catch (Exception erro)
{
    System.err.println (erro.getMessage());
}
```

## Alertas
- ⚠️ `Livros.alterar` do material: `"UPDATE LIVROS SET NOME=? SET PRECO=? ..."` (dois `SET`) é SQL inválido e sempre cai no catch ("Erro ao atualizar dados de livro"). Use `SET NOME=?, PRECO=?`.
- ⚠️ `Livro.equals` do material: `if (this.nome.equals(liv.nome)) return false;` sem `!` — livros iguais dão `false`. Também usa `instanceof`; o projeto usa `getClass()`.
- ⚠️ `Livro (Livro modelo)` não testa `null` (o comentário do `clone` diz que testa): ponha `if (modelo==null) throw new Exception ("Modelo ausente");`.
- ⚠️ `Livro.hashCode` usa `666`/`7` e `new Integer`/`new Float` (obsoletos): use a receita do projeto (`maligno/hashcode`).
- ⚠️ `equals` de `MeuStatement`/`MeuResultSet`: mesmo `!` faltando. As classes `core` são usadas prontas, mas não sirva de modelo para `equals`.
- `Principais métodos das classes core.txt` diz `void executeUpdate()`; no código ele devolve `int` (linhas afetadas). Ignorar o retorno, como o professor faz, está certo.
- As classes `core` e o `Livro` do material estão em ISO-8859-1: com `-encoding UTF-8` o `javac` dá "unmappable character". Converta para UTF-8 (`iconv -f latin1 -t utf-8`) antes de juntar ao projeto.
- `System.exit(0)` no catch de conexão é do professor (código 0 apesar do erro); mantenha como está no material.
- Comentário de `cadastrado` escreve `absotule`: o método é `absolute`.
- O professor não fala de SQL injection; a justificativa do `?` na aula é só "marcar o lugar onde mais tarde vai entrar um valor".

## Fontes
`17) Material sobre acesso a BDs em programas feitos em Java/BD/Demonstração com SQL Server/` (`Programa.java`, `bd/BDSQLServer.java`, `bd/core/*`, `bd/dbos/Livro.java`, `bd/daos/Livros.java`) · `BD/Outros BDs/` (`BDMySQL`, `BDOracle`, `BDPostgreSQL` + drivers) · `BD/Principais métodos das classes core.txt` · `BD/Instruções para SQL Server.txt` · videoaulas assíncronas de BD, partes 1 (driver, classpath, core, BDSQLServer) e 2 (tabela, DBO, DAO, MeuResultSet, numeração a partir de 1, demonstração).
