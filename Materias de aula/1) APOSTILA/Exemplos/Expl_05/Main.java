public class Main
{
    public static void main (String args [])
    {
        try
        {
            System.out.println ();

            System.out.println ("Fracao vale, inicialmente, " +
                                Fracao.getNumerador   () +
                                "/" +
                                Fracao.getDenominador ());

            Fracao.setNumerador   (1);
            Fracao.setDenominador (2);

            System.out.println ();

            System.out.print (Fracao.getNumerador   () +
                              "/" +
                              Fracao.getDenominador ());

            Fracao.someSeCom (7);

            System.out.println (" + 7 = " + 
                                Fracao.getNumerador   () +
                                "/" +
                                Fracao.getDenominador ());

            System.out.print (Fracao.getNumerador   () +
                              "/" +
                              Fracao.getDenominador ());

            Fracao.subtraiaDeSi (7);

            System.out.println (" - 7 = " + 
                                Fracao.getNumerador   () +
                                "/" +
                                Fracao.getDenominador ());

            System.out.print (Fracao.getNumerador   () +
                              "/" +
                              Fracao.getDenominador ());

            Fracao.multipliqueSePor (7);

            System.out.println (" * 7 = " + 
                                Fracao.getNumerador   () +
                                "/" +
                                Fracao.getDenominador ());

            System.out.print (Fracao.getNumerador   () +
                              "/" +
                              Fracao.getDenominador ());

            Fracao.dividaSePor (7);

            System.out.println (" / 7 = " + 
                                Fracao.getNumerador   () +
                                "/" +
                                Fracao.getDenominador ());
        }
        catch (Exception e)
        {
            System.err.println (e.getMessage ());
        }
    }
}