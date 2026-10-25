import matematica.*;

public class Main
{
    public static void main (String[] args)
    {
        Fracao f1, f2, f3, f4, f5;

        try
        {
            f2 = new Fracao (5,7);
            
            System.out.println ("Numerador   de " + f2 + " = " +
                                f2.getNumerador());

            System.out.println ("Denominador de " + f2 + " = " +
                                f2.getDenominador());

            System.out.println ();

            f3 = f2;
            f4 = (Fracao)f2.clone();
            f5 = (Fracao)f2.clone();

            System.out.println ("Alterando numerador e denominador, ");
            System.out.println ("respectivamente para 1 e 2, ");
            System.out.print   (f2  + " torna-se ");

            f2.setNumerador   (1);
            f2.setDenominador (2);

            System.out.println (f2);
            System.out.println ();

            System.out.println ("Observe que a mudanca acima");
            System.out.println ("tambem afeta " + f3);
            System.out.println ("mas nao " + f4);

            System.out.println ();

            f1 = f2.mais(f4);
            System.out.println (f1 + " = " + f2 + " + " + f4);

            f1 = f2.menos(f4);
            System.out.println (f1 + " = " + f2 + " - " + f4);

            f1 = f2.vezes(f4);
            System.out.println (f1 + " = " + f2 + " * " + f4);

            f1 = f2.divididoPor(f4);
            System.out.println (f1 + " = " + f2 + " / " + f4);

            System.out.println ();

            if (f2==f3)
                System.out.println (f2 + " == " + f3 + " (pelo ==)");
            else
                System.out.println (f2 + " != " + f3 + " (pelo ==)");

            if (f2.equals(f3))
                System.out.println (f2 + " == " + f3 + " (pelo equals)");
            else
                System.out.println (f2 + " != " + f3 + " (pelo equals)");

            if (f3==f4)
                System.out.println (f3 + " == " + f4 + " (pelo ==)");
            else
                System.out.println (f3 + " != " + f4 + " (pelo ==)");

            if (f3.equals(f4))
                System.out.println (f3 + " == " + f4 + " (pelo equals)");
            else
                System.out.println (f3 + " != " + f4 + " (pelo equals)");

            if (f4==f5)
                System.out.println (f4 + " == " + f5 + " (pelo ==)");
            else
                System.out.println (f4 + " != " + f5 + " (pelo ==)");

            if (f4.equals(f5))
                System.out.println (f4 + " == " + f5 + " (pelo equals)");
            else
                System.out.println (f4 + " != " + f5 + " (pelo equals)");

            System.out.println ();

            int comp = f2.compareTo(f3);
            if (comp < 0)
                System.out.println (f2 + " < " + f3 + " (pelo compareTo)");
            else
                if (comp == 0)
                    System.out.println (f2 + " == " + f3 +
                                        " (pelo compareTo)");
                else
                    System.out.println (f2 + " > " + f3 +
                                        " (pelo compareTo)");

            comp = f3.compareTo(f2);
            if (comp < 0)
                System.out.println (f3 + " < " + f2 + " (pelo compareTo)");
            else
                if (comp == 0)
                    System.out.println (f3 + " == " + f2 +
                                        " (pelo compareTo)");
                else
                    System.out.println (f3 + " > " + f2 +
                                        " (pelo compareTo)");

            comp = f3.compareTo(f4);
            if (comp < 0)
                System.out.println (f3 + " < " + f4 + " (pelo compareTo)");
            else
                if (comp == 0)
                    System.out.println (f3 + " == " + f4 +
                                        " (pelo compareTo)");
                else
                    System.out.println (f3 + " > " + f4 +
                                        " (pelo compareTo)");

            comp = f4.compareTo(f3);
            if (comp < 0)
                System.out.println (f4 + " < " + f3 + " (pelo compareTo)");
            else
                if (comp == 0)
                    System.out.println (f4 + " == " + f3 +
                                        " (pelo compareTo)");
                else
                    System.out.println (f4 + " > " + f3 +
                                        " (pelo compareTo)");

            comp = f4.compareTo(f5);
            if (comp < 0)
                System.out.println (f4 + " < " + f5 + " (pelo compareTo)");
            else
                if (comp == 0)
                    System.out.println (f4 + " == " + f5 +
                                        " (pelo compareTo)");
                else
                    System.out.println (f4 + " > " + f5 +
                                        " (pelo compareTo)");

            comp = f5.compareTo(f4);
            if (comp < 0)
                System.out.println (f5 + " < " + f4 + " (pelo compareTo)");
            else
                if (comp == 0)
                    System.out.println (f5 + " == " + f4 +
                                        " (pelo compareTo)");
                else
                    System.out.println (f5 + " > " + f4 +
                                        " (pelo compareTo)");

            System.out.println ();

            System.out.println ("O codigo de espalhamento de " +
                                "uma instancia valendo " + f1  +
                                " vale " + f1.hashCode());

            System.out.println ("O codigo de espalhamento de " +
                                "uma instancia valendo " + f2  +
                                " vale " + f2.hashCode());

            System.out.println ("O codigo de espalhamento de " +
                                "outra instancia valendo " + f3  +
                                " vale " + f3.hashCode());

            System.out.println ("O codigo de espalhamento de "  +
                                "uma instancia valendo " + f4 +
                                " vale " + f4.hashCode());

            System.out.println ("O codigo de espalhamento de " +
                                "outra instancia valendo " + f5  +
                                " vale " + f5.hashCode());
        }
        catch (Exception e)
        {
            System.err.println (e.getMessage ());
        }
    }
}