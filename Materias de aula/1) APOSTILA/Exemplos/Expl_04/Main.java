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
        int numero;

        System.out.println ();
        System.out.println ("PROGRAMA PARA CALCULAR FATORIAL");

        for (;;)
        {
            System.out.println ();
            System.out.print ("Digite um numero natural: ");
            double numeroDigitado = Double.parseDouble
                                   (teclado.readLine ());

            if (numeroDigitado < 0.0)
            {
                System.err.print   ("Numeros naturais nao ");
                System.err.println ("podem ser negativos!");
                System.err.println ("Tente novamente...");
            }
            else
            {
                numero = (int)numeroDigitado;

                if (numero != numeroDigitado)
                {
                    System.err.print   ("Numeros naturais nao podem");
                    System.err.println ("ter parte fracionaria!");
                    System.err.println ("Tente novamente...");
                }
                else
                    break;
            }
        }

        int fatorial=1;

        while (numero > 1)
        {
            fatorial = fatorial*numero;
            numero--;
        }

        System.out.println ("Resultado: " + fatorial);
        System.out.println ();
    }
}