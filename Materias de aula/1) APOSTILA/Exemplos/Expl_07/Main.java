class Main
{
    public static void main (String[] args)
    {
        char opcao;
        do
        {
            try
            {
                System.out.println ();
                System.out.println ("(a) Digitar um String");
                System.out.println ("(b) Digitar um byte");
                System.out.println ("(c) Digitar um short");
                System.out.println ("(d) Digitar um int");
                System.out.println ("(e) Digitar um long");
                System.out.println ("(f) Digitar um float");
                System.out.println ("(g) Digitar um double");
                System.out.println ("(h) Digitar um char");
                System.out.println ("(i) Digitar um boolean");
                System.out.println ("(j) Sair");
                System.out.print   ("Sua opcao? ");
                opcao = Character.toLowerCase(Teclado.getUmChar());
            }
            catch (Exception erro)
            {
                opcao='z';
            }

            try
            {
                switch (opcao)
                {
                    case 'a':
                    System.out.print ("Digite o valor para um String: ");
                    String str = Teclado.getUmString();
                    System.out.println ("Foi digitado: "+str);
                    break;

                    case 'b':
                    System.out.print ("Digite o valor para um byte: ");
                    byte b = Teclado.getUmByte();
                    System.out.println ("Foi digitado: "+b);
                    break;

                    case 'c':
                    System.out.print ("Digite o valor para um short: ");
                    short s = Teclado.getUmShort();
                    System.out.println ("Foi digitado: "+s);
                    break;

                    case 'd':
                    System.out.print ("Digite o valor para um int: ");
                    int i = Teclado.getUmInt();
                    System.out.println ("Foi digitado: "+i);
                    break;

                    case 'e':
                    System.out.print ("Digite o valor para um long: ");
                    long l = Teclado.getUmLong();
                    System.out.println ("Foi digitado: "+l);
                    break;

                    case 'f':
                    System.out.print ("Digite o valor para um float: ");
                    float f = Teclado.getUmFloat();
                    System.out.println ("Foi digitado: "+f);
                    break;

                    case 'g':
                    System.out.print ("Digite o valor para um double: ");
                    double d = Teclado.getUmDouble();
                    System.out.println ("Foi digitado: "+d);
                    break;

                    case 'h':
                    System.out.print ("Digite o valor para um char: ");
                    char c = Teclado.getUmChar();
                    System.out.println ("Foi digitado: "+c);
                    break;

                    case 'i':
                    System.out.print ("Digite o valor para um boolean: ");
                    boolean boo = Teclado.getUmBoolean();
                    System.out.println ("Foi digitado: "+boo);
                    break;

                    case 'j':
                    break;

                    default:
                    System.err.println ("Opcao invalida! Tente novamente...\n");
                }
            }
            catch (Exception erro)
            {
                System.err.println (erro.getMessage());
            }
        }
        while (opcao!='j');
    }
}
