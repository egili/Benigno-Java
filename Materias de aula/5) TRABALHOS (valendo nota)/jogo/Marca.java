package jogo;

public class Marca
{
    private char simbolo;

    public Marca (char smb) throws Exception
    {
        // validar smb, garantindo que ele
        // seja 'X' ou 'O' e coloca-lo em
        // simbolo
        // dar excecao em caso de valor invalido
    }

    public char getSimbolo ()
    {
        // retornar simbolo
    }

    @Override
    public String toString ()
    {
        return ""+simbolo;
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