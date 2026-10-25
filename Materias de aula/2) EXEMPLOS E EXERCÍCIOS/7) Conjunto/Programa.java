// import conjunto.*;
import conjunto.Conjunto;
// import conjunto.ordenado.*;
import conjunto.ordenado.ConjuntoOrdenado;

public class Programa
{
     public static void main (String[] args)
     {
         try
         {
             ConjuntoOrdenado<String> c1 = new ConjuntoOrdenado<String> ();
             ConjuntoOrdenado<String> c2 = new ConjuntoOrdenado<String> (4);
             Conjunto<String>  u;
             Conjunto<String>  i;
             Conjunto<String>  d;

                                    
             //ConjuntoOrdenado<ConjuntoOrdenado<String>> x = new ConjuntoOrdenado<ConjuntoOrdenado<String>>();
                                    
             //ConjuntoOrdenado<Data>      c3 = new ConjuntoOrdenado<Data>      (3);
             //ConjuntoOrdenado<Horario>   c4 = new ConjuntoOrdenado<Horario>   ();
             //ConjuntoOrdenado<Integer>   c5 = new ConjuntoOrdenado<Integer>   (5);
             //ConjuntoOrdenado<Float>     c6 = new ConjuntoOrdenado<Float>     (9);
             //ConjuntoOrdenado<Character> c7 = new ConjuntoOrdenado<Character> ();
            
             System.out.println(1);
             c1.inclua("vermelho"); System.out.println(2);
             c1.inclua("azul");System.out.println(3);
             c1.inclua("branco");System.out.println(4);
             c1.inclua("lilas");System.out.println(5);
             c1.inclua("verde");System.out.println(6);
             System.out.println (c1);System.out.println(7);
                                    
             c2.inclua("rosa");
             c2.inclua("azul");
             c2.inclua("cinza");
             c2.inclua("lilas");
             c2.inclua("preto");
             c2.inclua("amarelo");
             System.out.println (c2);
                                    
            //u = c1.uniao(c2);
            //System.out.println (u);

            // ja deve estar pronto
            //i = c1.interseccao(c2);
            //System.out.println (i);
                            
            // abaixo temos uma atividade autonoma que passo agora para
            // voces fazerem e que sera corrigida na proxima aula sincrona
            //d = c1.menos(c2);
            //System.out.println (d);
        }
        catch (Exception erro)
        {
            System.err.println (erro.getMessage());
        }
    }
}
