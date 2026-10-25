package jogador;

import jogo.Marca;
import jogo.Tabuleiro;
import java.io.*;

public class JogadorHumano implements Jogador, Cloneable
{
    private Marca          marca     = null;
    private Tabuleiro      tabDoJogo = null;
    private BufferedReader teclado   = new BufferedReader
                                        (new InputStreamReader
                                        (System.in));

    public JogadorHumano (Tabuleiro tab) throws Exception
    {
        // atribuir tab para tabDoJogo
        // pedir para o jogador escolher
        // sua marca e permitir digitacao
        // intanciando marca
    }

    public JogadorHumano (Tabuleiro tab, Marca mrc) throws Exception
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
        // pergunta para o jogador onde ele quer
        // jogar, permitindo digitacao e chamando
        // os métodos apropriados de tabDoJogo
    }

    public void vcGanhou ()
    {
        // mostra mensagem indicando vitoria
    }

    public void vcPerdeu ()
    {
        // mostra mensagem indicando derrota
    }

    public void deuVelha ()
    {
        // mostra mensagem indicando empate
    }

    public boolean querMaisUmaPartida ()
    {
        // perguntar se deseja jogar de novo
        // permitindo digitacao e retornando
        // true ou false, conforme a resposta
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
    public JogadorHumano (JogadorHumano modelo) throws Exception

    {
        // faça
    }

    @Override
    public Object clone ()
    {
        // faça
    }
}