/**
A classe Fracao representa frações, conforme as conhecemos da matemática.

Em outras palavras, a classe Fracao representa o conjunto matemático dos
números racionais. Nela encontraremos diversos métodos para operar com frações.
@author André Luís dos Reis Gomes de Carvalho
@since 2000
*/
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

    /**
    Constroi uma nova instância da classe Fracao.
    Para tanto, devem ser fornecidos dois inteiros que serão utilizados
    respectivamente, como numerador e como denominador da instância recém
    criada.
    @param numerador o número inteiro a ser utilizado como numerador
    @param denominador o número inteiro a ser utilizado como denominador
    @throws Exception se o denominador for igual a zero
    */
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

    /**
    Obtem o numerador de uma fração.
    Resulta o numerador da instância à qual este método for aplicado.
    @return o numerador da fração chamante do método
    */
    public long getNumerador ()
    {
        return this.numerador;
    }

    /**
    Obtem o denominador de uma fração.
    Resulta o denominador da instância à qual este método for aplicado.
    @return o denominador da fração chamante do método
    */
    public long getDenominador ()
    {
        return this.denominador;
    }

    /**
    Ajusta o numerador de uma fração.
    Ajusta o numerador da instância à qual este método for aplicado.
    @param numerador o numerador que a fração chamante do método
           deve pasar a ter
    */
    public void setNumerador (long numerador)
    {
        this.numerador = numerador;
        this.simplifiqueSe ();
    }

    /**
    Ajusta o denominador de uma fração.
    Ajusta o denominador da instância à qual este método for aplicado.
    @param denominador o denominador que a fração chamante do método
           deve pasar a ter
    @throws Exception se o denominador for igual a zero
    */
    public void setDenominador (long denominador) throws Exception
    {
        if (denominador==0)
            throw new Exception ("Denominador zero");

        this.denominador = denominador;
        this.simplifiqueSe ();
    }

    /**
    Realiza a operação de soma para duas frações.
    Soma a instância à qual este método for aplicado com aquela que lhe
    for fornecida como parâmetro.
    @param fracao a fração que deve ser somada à chamante do método
    @return a fração chamante do método somada à fração fornecida
    @throws Exception se for fornecido null como parâmetro
    */
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

    /**
    Realiza a operação de subtração para duas frações.
    Subtrai da instância à qual este método for aplicado aquela que lhe
    for fornecida como parâmetro.
    @param fracao a fração que deve ser subtraida da chamante do método
    @return a fração chamante do método menos a fração fornecida
    @throws Exception se for fornecido null como parâmetro
    */
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

    /**
    Realiza a operação de multiplicação para duas frações.
    Multiplica a instância à qual este método for aplicado por aquela que lhe
    for fornecida como parâmetro.
    @param fracao a fração que deve ser multiplicada pela chamante do método
    @return a fração chamante do método multiplicada pela fração fornecida
    @throws Exception se for fornecido null como parâmetro
    */
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

    /**
    Realiza a operação de divisão para duas frações.
    Divide a instância à qual este método for aplicado por aquela que lhe
    for fornecida como parâmetro.
    @param fracao a fração pela qual deve ser dividida a chamante do método
    @return a fração chamante do método dividida pela fração fornecida
    @throws Exception se for fornecido null como parâmetro
    */
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

    /**
    Converte uma fração em um String.
    Produz e resulta uma instância da classe String que representa a
    instância à qual este método for aplicado.
    @return o String que representa a fração chamante do método
    */
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

    /**
    Verifica a igualdade entre duas frações.
    Verifica se o Object fornecido como parâmetro representa uma
    fração numericamente equivalente àquela representada pela instância
    à qual este método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem frações numericamente equivalentes, ou false,
            caso contrário
    */
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

    /**
    Calcula o código de espalhamento (ou código de hash) de uma fração.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da fração representada pela instância à qual o método for aplicado.
    @return o código de espalhamento da fração chamante do método
    */
    public int hashCode ()
    {
        int ret = 666;

        ret = 13*ret +
              Long.valueOf(numerador).hashCode();

        ret = 13*ret +
              ((Long)denominador).hashCode();

        return ret;
    }

    /**
    Compara duas frações.
    Compara as frações representadas respectivamente pela instância à qual
    o método for aplicado e pela instância fornecida como parâmetro, resultando
    um número negativo, caso a primeira seja numericamente menor que a segunda,
    zero, caso as duas sejam numericamente iguais, ou
    um número positivo, caso a primeira seja numericamente maior que a segunda.
    @return um número negativo, caso a primeira seja numericamente menor que a
            segunda, zero, caso as duas sejam numericamente iguais, ou um
            número positivo, caso a primeira seja numericamente maior que a
            segunda.

    */
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

    /**
    Constroi uma cópia da instância da classe Fracao dada.
    Para tanto, deve ser fornecida uma instancia da classe Fracao para ser
    utilizada como modelo para a construção da nova instância criada.
    @param modelo a instância da classe Fracao a ser usada como modelo
    @throws Exception se o modelo for null
    */
    public Fracao (Fracao modelo)
                   throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.numerador   = modelo.numerador;
        this.denominador = modelo.denominador;
    }

    /**
    Clona uma fração.
    Produz e resulta uma cópia da fração representada pela instância
    à qual o método for aplicado.
    @return a cópia da fração representada pela instância à qual
            o método for aplicado
    */
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
