package jogador;

import jogo.Marca;
import jogo.Tabuleiro;

public class JogadorAutomatico implements Jogador, Cloneable
{
    private Marca     marca     = null;
    private Tabuleiro tabDoJogo = null;

    public JogadorAutomatico (Tabuleiro tab) throws Exception
    {   tabDoJogo = tab;
        // atribuir tab para tabDoJogo
        // fazer o jogador escolher sua marca
        // via sorteio de numeros aleatorio
        if ((int)(Math.random()*2) == 0)
            marca=new marca('X');
        else
            marca=new marca('O');
    }

    public JogadorAutomatico (Tabuleiro tab, Marca mrc) throws Exception
    {
        // atribuir tab para tabDoJogo
        // e mrc para marca
    }

    public Marca getMarca ()
    {
        // retorna a marca do jogador
    }

    public void facaSuaJogada ()
    {
        try
        {
            // tentando ganhar na linha 0
            if (!this.tabDoJogo.haMarcaNaPosicao(0,0) &&
                 this.tabDoJogo.haMarcaNaPosicao(0,1) && this.tabDoJogo.getMarcaNaPosicao(0,1).equals(this.marca) &&
                 this.tabDoJogo.haMarcaNaPosicao(0,2) && this.tabDoJogo.getMarcaNaPosicao(0,2).equals(this.marca))
                 this.tabDoJogo.setMarcaNaPosicao(this.marca, 0,0);
            else
            if ( this.tabDoJogo.haMarcaNaPosicao(0,0) && this.tabDoJogo.getMarcaNaPosicao(0,0).equals(this.marca) &&
                !this.tabDoJogo.haMarcaNaPosicao(0,1) &&
                 this.tabDoJogo.haMarcaNaPosicao(0,2) && this.tabDoJogo.getMarcaNaPosicao(0,2).equals(this.marca))
                 this.tabDoJogo.setMarcaNaPosicao(this.marca, 0,1);
            else
            if ( this.tabDoJogo.haMarcaNaPosicao(0,0) && this.tabDoJogo.getMarcaNaPosicao(0,0).equals(this.marca) &&
                 this.tabDoJogo.haMarcaNaPosicao(0,1) && this.tabDoJogo.getMarcaNaPosicao(0,1).equals(this.marca) &&
                !this.tabDoJogo.haMarcaNaPosicao(0,2))
                 this.tabDoJogo.setMarcaNaPosicao(this.marca, 0,2);
            else
            // tentando ganhar na linha 1
            if ... else if ... else if ...
            else
            // tentando ganhar na linha 2
            if ... else if ... else if ...
            else
            // tentando ganhar na coluna 0
            if ... else if ... else if ...
            else
            // tentando ganhar na coluna 1
            if ... else if ... else if ...
            else
            // tentando ganhar na coluna 2
            if ... else if ... else if ...
            else
            // tentando ganhar na diagonal principal
            if ... else if ... else if ...
            else
            // tentando ganhar na diagonal secundaria
            if ... else if ... else if ...
            else
            // tentando atrapalhar vitoria na linha 0
            if ... else if ... else if ...
            else
            // tentando atrapalhar vitoria na linha 1
            if ... else if ... else if ...
            else
            // tentando atrapalhar vitoria na linha 2
            if ... else if ... else if ...
            else
            // tentando atrapalhar vitoria na coluna 0
            if ... else if ... else if ...
            else
            // tentando atrapalhar vitoria na coluna 1
            if ... else if ... else if ...
            else
            // tentando atrapalhar vitoria na coluna 2
            if ... else if ... else if ...
            else
            // tentando atrapalhar vitoria na diagonal principal
            if ... else if ... else if ...
            else
            // tentando atrapalhar vitoria na diagonal secundaria
            if ... else if ... else if ...
            else
            // tentando jogada de futuro (há 1 marca do jogador e mais nada; vamos lá e plantamos outra marca) na linha 0
            if ... else if ... else if ...
            else
            // tentando jogada de futuro (há 1 marca do jogador e mais nada; vamos lá e plantamos outra marca) na linha 1
            if ... else if ... else if ...
            else
            // tentando jogada de futuro (há 1 marca do jogador e mais nada; vamos lá e plantamos outra marca) na linha 2
            if ... else if ... else if ...
            else
            // tentando jogada de futuro (há 1 marca do jogador e mais nada; vamos lá e plantamos outra marca) na coluna 0
            if ... else if ... else if ...
            else
            // tentando jogada de futuro (há 1 marca do jogador e mais nada; vamos lá e plantamos outra marca) na coluna 1
            if ... else if ... else if ...
            else
            // tentando jogada de futuro (há 1 marca do jogador e mais nada; vamos lá e plantamos outra marca) na coluna 2
            if ... else if ... else if ...
            else
            // tentando jogada de futuro (há 1 marca do jogador e mais nada; vamos lá e plantamos outra marca) na diagonal principal
            if ... else if ... else if ...
            else
            // tentando jogada de futuro (há 1 marca do jogador e mais nada; vamos lá e plantamos outra marca) na diagonal secundaria
            if ... else if ... else if ...
            else
            // termine jogando num lugar qualquer que esteja livre
        }
        catch (Exception erro)
        {} // ignorando pois sei que n o estou usando posicao invalida nem comparando com equals um null
    }

    @Override
    public String toString ()
    {
        // faça
    }

    @Override
    public boolean equals (Object obj)
    {
        // faça
    }

    @Override
    public int hashCode ()
    {
        // faça
    }

    // construtor de cópia
    public JogadorAutomatico (JogadorAutomatico modelo) throws Exception
    {
        // faça
    }

    @Override
    public Object clone ()
    {
        // faça
    }
}