public class Main
{
    public static void main (String[] args)
    {
        Fracao f1, f2, f3, f4;

        try
        {
            f2 = new Fracao (1,2);
            f3 = new Fracao (3,5);
            f4 = new Fracao ("3/5");

            System.out.println  ();

            f1 = f2.mais(f3);
            System.out.println (f1 + " = " + f2 + " + " + f3);

            f1 = f2.mais(7);
            System.out.println (f1 + " = " + f2 + " + " + 7);

            f1 = f2.menos(f3);
            System.out.println (f1 + " = " + f2 + " - " + f3);

            f1 = f2.menos(7);
            System.out.println (f1 + " = " + f2 + " - " + 7);

            f1 = f2.vezes(f3);
            System.out.println (f1 + " = " + f2 + " * " + f3);

            f1 = f2.vezes(7);
            System.out.println (f1 + " = " + f2 + " * " + 7);

            f1 = f2.divididoPor(f3);
            System.out.println (f1 + " = " + f2 + " / " + f3);

            f1 = f2.divididoPor(7);
            System.out.println (f1 + " = " + f2 + " / " + 7);

            if (f2.equals(f3))
                System.out.println (f2 + " == " + f3 + " (pelo equals)");
            else
                System.out.println (f2 + " != " + f3 + " (pelo equals)");

            if (f3.equals(f4))
                System.out.println (f3 + " == " + f4 + " (pelo equals)");
            else
                System.out.println (f3 + " != " + f4 + " (pelo equals)");

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

            System.out.println ("O codigo de espalhamento de " +
                                "uma instancia valendo " + f2  +
                                " vale " + f2.hashCode());

            System.out.println ("O codigo de espalhamento de " +
                                "uma instancia valendo " + f3  +
                                " vale " + f3.hashCode());

            System.out.println ("O codigo de espalhamento de "  +
                                "outra instancia valendo " + f4 +
                                " vale " + f4.hashCode());
        }
        catch (Exception e)
        {
            System.err.println (e.getMessage ());
        }
    }
}
