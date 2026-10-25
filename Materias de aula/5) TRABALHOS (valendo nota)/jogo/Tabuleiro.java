package jogo;

public class Tabuleiro implements Cloneable
{
    // a principio, toda igual a null
    private Marca matriz [][];

    public Tabuleiro ()
    {
        this.matriz = new Marca [3][3];
    }

    public boolean haMarcaNaPosicao (int lin, int col) throws Exception
    {
        // verifico se em matriz[lin][col]
        // tem null, retornando false em caso
        // afirmativo, ou true em caso negativo
        // dar excecao caso lin ou col invalido
    }

    public Marca getMarcaNaPosicao (int lin, int col) throws Exception
    {
        // retornar a marca que tem em
        // matriz[lin][col]
        // dar excecao caso lin ou col invalido
    }

    public void setMarcaNaPosicao (Marca mrc, int lin, int col) throws Exception
    {
        // colocar mrc em matriz[lin][col],
        // dar excecao caso lin ou col invalido
        // dar excecao caso mrc seja null
    }

    @Override
    public String toString ()
    {
        // retornar em um String o tabuleiro
        // formatado para ser exibido na tela
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

    // construtor de copia
    public Tabuleiro (Tabuleiro modelo) throws Exception
    {
        // faça
    }

    @Override
    public Object clone ()
    {
        // faça
    }
}