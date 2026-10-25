public class Args
{
    public static void main (String[] args)
    {
        if (args.length==0)
           System.out.println("Nao foi passado nada ao nosso programa pelo Windows");
        else
        {
           System.out.println("O Windows passou o seguinte para nosso programa:");
           for (int i=0; i<args.length; i++)
               System.out.println (args[i]);
        } 
    }
}