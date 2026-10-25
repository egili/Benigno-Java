import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    public static void main (String[] args)
    {
        Agenda agenda = null;

        BufferedReader entrada = new BufferedReader
                                (new InputStreamReader
                                (System.in));
        int opcao;

        for (;;)
        {
            System.out.print ("Digite sua opcao (" +
                              "1=Vetor/" +
                              "2=Vector)" +
                              "3=ArrayList)" +
                              ": ");

            try
            {
                opcao = Integer.parseInt (entrada.readLine ());

                if (opcao>=1 && opcao<=3)
                    break;
            }
            catch (IOException e)
            {}
            catch (NumberFormatException e)
            {
                System.err.println ("Opcao invalida\n\n");
            }
        }

        System.err.println ();

        switch (opcao)
        {
            case 1:
                for (;;)
                {
                    System.out.println ();

                    System.out.print ("Capacidade desejada para a Agenda: ");
                    try
                    {
                        int cap = Integer.parseInt (entrada.readLine ());
                        agenda  = new Agenda1 (cap);
                        break;
                    }
                    catch (IOException e)
                    {}
                    catch (NumberFormatException e)
                    {
                        System.err.println ("Nao foi digitado um numero inteiro");
                        System.err.println ();
                    }
                    catch (Exception e)
                    {
                        System.err.println (e.getMessage());
                        System.err.println ();
                    }
                }
                break;


            case 2:
                agenda = new Agenda2 ();
                break;


            case 3:
                agenda = new Agenda3 ();
        }

        System.out.println ();

        String nome = null, telefone = null;

        do
        {
            System.out.println ();

            System.out.print ("Digite sua Opcao (" +
                              "I=Incluir/" +
                              "E=Excluir/" +
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
                case 'i':
                case 'I':
                    try
                    {
                        System.out.print ("Nome....: ");
                        nome = entrada.readLine ();
                    }
                    catch (IOException e)
                    {}

                    try
                    {
                        System.out.print ("Telefone: ");
                        telefone = entrada.readLine ();
                    }
                    catch (IOException e)
                    {}

                    try
                    {
                        agenda.registreContato (nome, telefone);
                    }
                    catch (Exception e)
                    {
                        System.err.println (e.getMessage());
                    }

                    System.out.println ();
                    break;


                case 'e':
                case 'E':
                    System.out.print ("Nome....: ");
                    try
                    {
                        nome = entrada.readLine ();
                    }
                    catch (IOException e)
                    {}

                    try
                    {
                        agenda.descarteContato (nome);
                    }
                    catch (Exception e)
                    {
                        System.err.println (e.getMessage ());
                    }

                    System.out.println ();
                    break;


                case 'l':
                case 'L':
                    System.out.println (agenda);
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