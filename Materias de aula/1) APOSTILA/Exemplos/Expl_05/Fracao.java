public class Fracao
{
    private static long numerador, denominador;

    static
    {
        Fracao.numerador   = 0;
        Fracao.denominador = 1;
    }

    private static void simplifiqueSe ()
    {
        long menor = Math.min (Math.abs (Fracao.numerador),
                               Math.abs (Fracao.denominador));

        if (Fracao.numerador  %menor == 0 &&
            Fracao.denominador%menor == 0)
        {
            Fracao.numerador   = Fracao.numerador  /menor;
            Fracao.denominador = Fracao.denominador/menor;
        }
        else
            for (int i=2; i<=menor/2; i++)
                while (Fracao.numerador  %i == 0 &&
                       Fracao.denominador%i == 0)
                {
                    Fracao.numerador   = Fracao.numerador  /i;
                    Fracao.denominador = Fracao.denominador/i;
                }
    }

    public static long getNumerador ()
    {
        return Fracao.numerador;
    }

    public static long getDenominador ()
    {
        return Fracao.denominador;
    }

    public static void setNumerador (long numerador)
    {
        Fracao.numerador = numerador;
        Fracao.simplifiqueSe ();
    }

    public static void setDenominador (long denominador) throws Exception
    {
        if (denominador==0)
            throw new Exception ("Denominador zero");

        Fracao.denominador = denominador;
        Fracao.simplifiqueSe ();
    }

    public static void someSeCom (long numero)
    {
        Fracao.numerador = Fracao.numerador   +
                           Fracao.denominador * numero;

    }

    public static void subtraiaDeSi (long numero)
    {
        Fracao.numerador = Fracao.numerador   -
                           Fracao.denominador * numero;
    }

    public static void multipliqueSePor (long numero)
    {
        Fracao.numerador = Fracao.numerador * numero;
    }

    public static void dividaSePor (long numero) throws Exception
    {
        if (numero == 0)
            throw new Exception ("Divisao por zero");

        Fracao.denominador = Fracao.denominador * numero;

        if (Fracao.denominador < 0)
        {
            Fracao.numerador   = -Fracao.numerador;
            Fracao.denominador = -Fracao.denominador;
        }
    }
}