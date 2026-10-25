import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TesteDeDeposito
{
    public static void main (String[] args) throws IOException
    {
        Deposito<String> deposito = null;

        BufferedReader entrada = new BufferedReader
                                (new InputStreamReader
                                (System.in));

        for (;;)
        {
            System.out.print ("Capacidade desejada para o Deposito: ");
            try
            {
                int cap  = Integer.parseInt (entrada.readLine ());
                deposito = new Deposito<String> (cap);
                System.out.println ();
                break;
            }
            catch (IOException e)
            {}
            catch (NumberFormatException e)
            {
                System.err.println ("Nao foi digitado um numero inteiro\n");
                System.err.println ();
            }
            catch (Exception e)
            {
                System.err.println (e.getMessage()+"\n");
                System.err.println ();
            }
        }

        char   opcao=' ';
        String item = null;

        do
        {
            System.out.println ();

            System.out.print ("Digite sua Opcao (" +
                              "G=Guardar/" +
                              "I=obter Item/" +
                              "T=ver se Tem/" +
                              "J=Jogar fora/" +
                              "L=Listar/" +
                              "S=Sair)" +
                              ": ");

            try
            {
                String str = entrada.readLine ();

                if (str.length()==1)
                    opcao = str.charAt(0);
                else
                    opcao = 'A'; // forçando opção inválida
            }
            catch (IOException e)
            {}

            switch (opcao)
            {
                case 'g':
                case 'G':
                    try
                    {
                        System.out.print ("Item: ");
                        item = entrada.readLine ();
                    }
                    catch (IOException e)
                    {}

                    try
                    {
                        deposito.guarde(item);
                    }
                    catch (Exception e)
                    {
                        System.err.println (e.getMessage());
                    }

                    System.out.println ();
                    break;


                case 't':
                case 'T':
                    System.out.print ("Item: ");
                    try
                    {
                        item = entrada.readLine ();

                        if (deposito.tem(item))
                            System.out.println ("Tem");
                        else
                            System.out.println ("Nao tem");
                    }
                    catch (Exception e)
                    {}

                    System.out.println ();
                    break;


                case 'i':
                case 'I':
                    int pos;

                    for (;;)
                    {
                        System.out.print ("Numero de ordem: ");
                        try
                        {
                            pos = Integer.parseInt (entrada.readLine ());

                            if (pos<0)
                                System.err.println ("Nao foi digitato um numero natural\n");
                            else
                                break;
                        }
                        catch (IOException e)
                        {}
                        catch (NumberFormatException e)
                        {
                            System.err.println ("Nao foi digitado um numero natural\n");
                        }
                    }

                    try
                    {
                        item = deposito.getElem(pos);
                        System.out.println (item);
                    }
                    catch (Exception e)
                    {
                        System.err.println (e.getMessage ());
                    }

                    System.out.println ();
                    break;


                case 'j':
                case 'J':
                    System.out.print ("Item: ");
                    try
                    {
                        item = entrada.readLine ();
                    }
                    catch (IOException e)
                    {}

                    try
                    {
                        deposito.jogueFora(item);
                    }
                    catch (Exception e)
                    {
                        System.err.println (e.getMessage ());
                    }

                    System.out.println ();
                    break;


                case 'l':
                case 'L':
                    System.out.println (deposito);
                    System.out.println ();
                    break;


                case 's':
                case 'S':
                    break;


                default :
                    System.err.println ("Opcao invalida");
                    System.err.println ();
            }
        }
        while ((opcao != 's') && (opcao != 'S'));
    }
}