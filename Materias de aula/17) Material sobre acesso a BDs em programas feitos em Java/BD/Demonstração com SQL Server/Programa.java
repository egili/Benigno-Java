import bd.daos.Livros;
import bd.dbos.Livro;

public class Programa
{
    public static void main (String[] args)
    {
        try
        {
            Livros.incluir (new Livro (1,"L'Etranger",20));
            System.out.println ("Livro incluido com sucesso!");
        }
        catch (Exception erro)
        {
			erro.printStackTrace();
            System.out.println (erro.getMessage());
        }
    }
}
