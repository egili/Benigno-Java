import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    public static void main (String args []) throws IOException
    {
        BufferedReader teclado = new BufferedReader
                                (new InputStreamReader
                                (System.in));

        System.out.println ();
        System.out.print   ("PROGRAMA PARA CALCULAR RAIZ");
        System.out.print   (" DE EQUACAO DE 1o GRAU");
        System.out.println ();

        System.out.print   ("Coeficiente a: ");
        double a = Double.parseDouble (teclado.readLine ());

        System.out.print   ("Coeficiente b: ");
        double b = Double.parseDouble (teclado.readLine ());

        double raiz = -b/a;

        System.out.println ();
        System.out.println ("Raiz: " + raiz);
        System.out.println ();
    }
}