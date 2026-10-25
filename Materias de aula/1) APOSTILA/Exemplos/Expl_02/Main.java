import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    public static void main (String args []) throws IOException
    {
        BufferedReader teclado = new BufferedReader (
                                 new InputStreamReader (
                                 System.in));

        System.out.println ();

        System.out.print   ("Qual o seu nome? ");
        String nome        = teclado.readLine ();

        System.out.println ("Bem vindo(a) ao estudo de JAVA, " +
                            nome +
                            "!");

        System.out.println ();

        System.out.print   ("Com que nota voce pretende passar? ");

        float nota = Float.parseFloat (teclado.readLine ());
        /*
        ou
        float nota = Float.valueOf (teclado.readLine ()).intValue ();
        */

        System.out.println ();

        System.out.println (nome +
                            ", desejo sucesso a voce;");

        System.out.println ("que voce passe com " +
                            nota +
                            " ou mais!");

        System.out.println ();
    }
}