package matematica;

// se a palavra public abaixo fosse retirada, esta classe
// nao poderia ser utilizada na main; isto porque classes
// nao publicas somente podem ser utilizadas por outras
// classes dentro da mesmma pasta

public class Fracao implements Comparable<Fracao>, Cloneable
{
    private long numerador, denominador;

    private double valorReal ()
    {
        return (double)numerador /
               (double)denominador;
    }

    private void simplifiqueSe ()
    {
        long menor = Math.min (Math.abs (this.numerador),
                               Math.abs (this.denominador));

        if (this.numerador%this.denominador == 0)
        {
            this.numerador   = this.numerador / this.denominador;
            this.denominador = 1;
        }
        else
            if (this.numerador  %menor == 0 &&
                this.denominador%menor == 0)
            {
                this.numerador   = this.numerador  /menor;
                this.denominador = this.denominador/menor;
            }
            else
                for (long metade=menor/2, i=2; i<=metade; i++)
                    while (this.numerador  %i == 0 &&
                           this.denominador%i == 0)
                    {
                        this.numerador   = this.numerador  /i;
                        this.denominador = this.denominador/i;
                    }
    }

    public Fracao (long numerador,
                   long denominador)
                   throws Exception
    {
        if (denominador == 0)
            throw new Exception ("Denominador zero");

        if (denominador < 0)
        {
            this.numerador   = -numerador;
            this.denominador = -denominador;
        }
        else
        {
            this.numerador   = numerador;
            this.denominador = denominador;
        }

        this.simplifiqueSe ();
    }

    public long getNumerador ()
    {
        return this.numerador;
    }

    public long getDenominador ()
    {
        return this.denominador;
    }

    public void setNumerador (long numerador)
    {
        this.numerador = numerador;
        this.simplifiqueSe ();
    }

    public void setDenominador (long denominador) throws Exception
    {
        if (denominador==0)
            throw new Exception ("Denominador zero");

        this.denominador = denominador;
        this.simplifiqueSe ();
    }

    public Fracao mais (Fracao fracao) throws Exception
    {
        if (fracao == null)
            throw new Exception ("Falta de operando em soma");

        long numerador   = this.numerador   * fracao.denominador +
                           this.denominador * fracao.numerador,

             denominador = this.denominador * fracao.denominador;

        Fracao resultado = new Fracao (numerador,denominador);

        resultado.simplifiqueSe ();
        return resultado;
    }

    public Fracao menos (Fracao fracao) throws Exception
    {
        if (fracao == null)
            throw new Exception ("Falta de operando em soma");

        long numerador   = this.numerador   * fracao.denominador -
                           this.denominador * fracao.numerador,

             denominador = this.denominador * fracao.denominador;

        Fracao resultado = new Fracao (numerador, denominador);

        resultado.simplifiqueSe ();
        return resultado;
    }

    public Fracao vezes (Fracao fracao) throws Exception
    {
        if (fracao == null)
            throw new Exception ("Falta de operando em multiplicacao");

        long numerador   = this.numerador   * fracao.numerador,
             denominador = this.denominador * fracao.denominador;

        Fracao resultado = new Fracao (numerador,denominador);

        resultado.simplifiqueSe ();
        return resultado;
    }

    public Fracao divididoPor (Fracao fracao) throws Exception
    {
        if (fracao == null)
            throw new Exception ("Falta de operando em divisao");

        if (fracao.numerador == 0)
            throw new Exception ("Divisao por zero");

        long numerador   = this.numerador   * fracao.denominador,
             denominador = this.denominador * fracao.numerador;

        if (denominador < 0)
        {
            numerador   = -numerador;
            denominador = -denominador;
        }

        Fracao resultado = new Fracao (numerador,denominador);

        resultado.simplifiqueSe ();
        return resultado;
    }

    public String toString ()
    {
        if (this.numerador == this.denominador)
            return "1";

        if (this.numerador + this.denominador == 0)
            return "-1";

        if (this.numerador == 0 || this.denominador == 1)
            return "" + this.numerador;

        return this.numerador + "/" + this.denominador;
    }

    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

      //if (!obj instanceof Fracao)
        if (this.getClass() != obj.getClass())
            return false; // poderiamos fazer de outra forma
                          // caso quisessemos compatibilizar
                          // fracoes com outras classes, e.g.,
                          // com strings; naturalmente, isso
                          // causaria impacto no hashCode

        Fracao fracao = (Fracao)obj;

        if (this.valorReal() != fracao.valorReal())
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret = 666;

        ret = 13*ret +
              Long.valueOf(numerador).hashCode(); // valueOf é 1 jeito

        ret = 13*ret +
              ((Long)denominador).hashCode(); // cast é outro jeito

        return ret;
    }

    public int compareTo (Fracao fracao)
    {
        double vrThis   = this  .valorReal(),
               vrFracao = fracao.valorReal();

        if (vrThis < vrFracao)
            return -1;
        else
            if (vrThis == vrFracao)
                return 0;
            else
                return 1;
    }

    public Fracao (Fracao modelo)
                   throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.numerador   = modelo.numerador;
        this.denominador = modelo.denominador;
    }

    public Object clone ()
    {
        Fracao copia=null;

        try
        {
            copia = new Fracao (this);
        }
        catch (Exception e)
        {}

        return copia;
    }
}
