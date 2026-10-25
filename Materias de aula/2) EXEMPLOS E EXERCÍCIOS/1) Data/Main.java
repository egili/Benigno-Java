// import java.util.*;
import java.util.ArrayList;

public class Main
{
    public static void main (String[] args)
    {
        System.out.println(Data.getQtd()); // 0
        System.out.println(Data.isBissexto((short)2000)); // true
        System.out.println(Data.isValida((byte)10,(byte)8,(short)2026)); // true

        try
        {
            Data niverMaligno = new Data ((byte)19,(byte)1,(short)1966);
            System.out.println(niverMaligno); // 19/1/1966
            System.out.println(niverMaligno.toString()); // 19/1/1966
            niverMaligno.retrocedaUmDia();
            System.out.println(niverMaligno.toString()); // 18/1/1966
            Data dat = niverMaligno.getDiaAnterior();
            System.out.println(niverMaligno.toString()); // 18/1/1966
            System.out.println(dat); // 17/01/1966
            /*
            ArrayList<Integer> lista1 = new ArrayList<Integer> ();
            lista1.add(2);
            lista1.add(3);
            lista1.add(5);
            ArrayList<Integer> lista2 = new ArrayList<Integer> ();
            lista2.add(2);
            lista2.add(3);
            lista2.add(5);
            System.out.println(lista1==lista2); // false
            System.out.println(lista1.equals(lista2)); // true
            System.out.println(lista1.contains(3)); // false
            */
            Data hoje  = new Data ((byte)21,(byte)8,(short)2026);
            Data today = new Data ((byte)21,(byte)8,(short)2026);
            System.out.println(hoje==today); // false
            System.out.println(hoje.equals(today)); // true
            ArrayList<Data> lista = new ArrayList<Data> ();
        }
        catch (Exception erro)
        {
            System.err.println(erro.getMessage());
        }
    }
}
