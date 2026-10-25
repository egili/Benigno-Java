package jogo;

public class AnalistaSituacional
{
    private Tabuleiro tabDoJogo;

    public AnalistaSituacional (Tabuleiro tab) throws Exception
    {
        // colocar tab em tabDoJogo
    }

    public Marca getMarcaDoVencedor ()
    {
        // descobrir se ha um vencedor e retornar
        // a marca dele; retornara null, quando
        // nao houver vencedor
    }

    private boolean tabuleiroCheio ()
    {
        // verificar se todas as posicoes do
        // tabuleiro estao preenchidas (!=null)
        // retornando true em caso afirmativo,
        // ou false em caso negativo
    }

    public boolean fimDeJogo ()
    {
        // se getMarcaDoVencedor != null,
        // retorna true
        // se tabuleiroCheio == true,
        // retorna true
        // caso contrario, retorna false
    }

    @Override
    public String toString ()
    {
        // faça
    }

    @Override
    public boolean equals (Object obj)
    {
        // retornar true se this for igual a obj ou false caso contrario
    }

    @Override
    public int hashCode ()
    {
        // faça
    }
}