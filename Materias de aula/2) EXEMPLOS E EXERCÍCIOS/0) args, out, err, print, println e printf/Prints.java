public class Prints
{
    public static void main (String[] args)
    {
        int i=032;
        float f=3.14f;
        boolean b=false;
        char c='A';
        String linguagem="JAVA";
        System.out.printf("\n\nO int vale %d, o float vale %.2f, o boolean vale %b, o char vale %c e o string vale %s\n",
                          i,f,b,c,linguagem);
        System.out.println("O int vale "+i+", o float vale "+f+", o boolean vale "+b+
                           ", o char vale "+c+" e o string vale "+linguagem);
    }
}