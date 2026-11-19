package bd;

import bd.core.*;
import bd.daos.*;

public class BDMySQL
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
            comando = new MeuPreparedStatement (
                      "com.mysql.jdbc.Driver",
                      "jdbc:mysql://SERVIDOR:3306/BD",
                      "USUARIO", "SENHA");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
}