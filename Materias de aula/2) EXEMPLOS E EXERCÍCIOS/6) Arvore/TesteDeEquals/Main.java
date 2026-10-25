public class Main
{
	public static void main (String[] args)
	{
		try
		{
			ArvoreBinariaDeBusca<Integer> arv1 = new ArvoreBinariaDeBusca<Integer> ();
			ArvoreBinariaDeBusca<Integer> arv2 = new ArvoreBinariaDeBusca<Integer> ();
			ArvoreBinariaDeBusca<Integer> arv3 = new ArvoreBinariaDeBusca<Integer> ();

			arv1.guardeUmItem(30);
			arv1.guardeUmItem(20);
			arv1.guardeUmItem(40);

			arv2.guardeUmItem(30);
			arv2.guardeUmItem(20);
			arv2.guardeUmItem(40);
			
			arv3.guardeUmItem(30);
			arv3.guardeUmItem(20);
			arv3.guardeUmItem(40);
			arv3.guardeUmItem(35);

			System.out.println(arv1.equals(arv2)); // true
			System.out.println(arv1.equals(arv3)); // false
		}
		catch (Exception erro)
		{
			System.err.println(erro.getMessage());
		}
	}
}
