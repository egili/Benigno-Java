public class Main
{
    public static void main(String args [])
    {
        if (args.length != 1)
            System.err.println ("Uso correto: " +
                                "java MeuPeso " +
                                "<seupesonaterra>");
        else
        {
            double pesoNaTerra  = Double.parseDouble (args[0]);

            for (PlanetaDoSistemaSolar p :
                 PlanetaDoSistemaSolar.values())
                 System.out.println ("Seu peso em " +
                                     p +
                                     " eh " +
                                     p.getPesoNaSuperficie
                                     (pesoNaTerra) +
                                     "\n\n");
                                     
        }
    }
}
