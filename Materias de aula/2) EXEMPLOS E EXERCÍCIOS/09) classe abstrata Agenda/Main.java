import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import agenda.*;
import agenda.ordenada.*;
import agenda.desordenada.*;

public class Main
{
    public static void main (String[] args) throws IOException
    {
        Agenda agenda = null;

        BufferedReader entrada = new BufferedReader
                                (new InputStreamReader
                                (System.in));
        int opcao=0;

        for (;;)
        {
            System.out.println ();

            System.out.print ("Digite sua opcao (" +
                              "1=Desordenada/" +
                              "2=Ordenada)" +
                              ": ");

            try
            {
                opcao = Integer.parseInt (entrada.readLine ());
            }
            catch (IOException e)
            {}
            catch (NumberFormatException e)
            {
                System.err.println ("Nao foi digitado um numero inteiro");
                System.err.println ();
            }

            if (opcao==1 || opcao==2)
                break;

            System.err.println ("Opcao invalida!");
        }

        for (;;)
        {
            System.out.println ();

            System.out.print ("Capacidade desejada para a Agenda: ");
            try
            {
                int cap  = Integer.parseInt (entrada.readLine ());

                if (opcao==1)
                    agenda = new AgendaDesordenada (cap);
                else
                    agenda = new AgendaOrdenada    (cap);

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
