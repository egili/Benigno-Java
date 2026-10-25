public class Main
{
	public static void main (String[] args)
	{
		try
		{
			ArvoreBinariaDeBusca<Integer> arv =
			new ArvoreBinariaDeBusca<Integer> ();
			System.out.println(arv);
			arv.guardeUmItem(50);
			System.out.println(arv);
			arv.guardeUmItem(30);
			System.out.println(arv);
			arv.guardeUmItem(70);
			System.out.println(arv);
			arv.guardeUmItem(20);
			System.out.println(arv);
			arv.guardeUmItem(40);
			System.out.println(arv);
			arv.guardeUmItem(60);
			System.out.println(arv);
			arv.guardeUmItem(80);
			System.out.println(arv);
			arv.guardeUmItem(50);
			System.out.println(arv);
		}
		catch (Exception erro)
		{
			System.err.println(erro.getMessage());
		}
	}
}
