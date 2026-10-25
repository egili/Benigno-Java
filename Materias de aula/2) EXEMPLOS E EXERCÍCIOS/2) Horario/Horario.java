public class Horario
{
    private byte hora, minuto, segundo;

    public /*void*/ Horario (byte hora, byte minuto, byte segundo) throws Exception
    {
        this.setHora   (hora);
        this.setMinuto (minuto);
        this.setSegundo(segundo);
    }

    public void setHora (byte hora) throws Exception
    {
        if (hora<0 || hora>23) throw new Exception ("Hora invalida");

        this.hora=hora;
    }

    public void setMinuto (byte minuto) throws Exception
    {
        if (minuto<0 || minuto>59) throw new Exception ("Minuto invalido");

        this.minuto=minuto;
    }

    public void setSegundo (byte segundo) throws Exception
    {
        if (segundo<0 || segundo>59) throw new Exception ("Segundo invalido");

        this.segundo=segundo;
    }

    public byte getHora ()
    {
        return this.hora;
    }

    public byte getMinuto ()
    {
        return this.minuto;
    }

    public byte getSegundo ()
    {
        return this.segundo;
    }

    public void adiante (int qtdSegundos) throws Exception // altera o this
    {
        if (qtdSegundos<=0) throw new Exception ("Quantidade invalida");

        long horario = this.hora*3600+
                       this.minuto*60+
                       this.segundo;

        horario+=qtdSegundos;
        horario%=86400; // trazendo do futuro para o presente, estando no futuro

        this.hora=(byte)(horario/3600);
        horario=horario%3600;
        this.minuto=(byte)(horario/60);
        this.segundo=(byte)(horario%60);
    }

    public void retroceda (int qtdSegundos) throws Exception // altera o this
    {
        if (qtdSegundos<=0) throw new Exception ("Quantidade invalida");

        long horario = this.hora*3600+
                       this.minuto*60+
                       this.segundo;

        horario-=qtdSegundos;

        int resto=horario%86400;
        if (resto<0) resto=-resto;
        horario=resto;

        this.hora=(byte)(horario/3600);
        horario=horario%3600;
        this.minuto=(byte)(horario/60);
        this.segundo=(byte)(horario%60);
    }

    public Horario getHorarioFuturo (int qtdSegundos) throws Exception // nao altera o this
    {
        Horario retorno = new Horario (this);
        retorno.adiante(qtdSegundos);
        return retorno;
    }

    public Horario getHorarioPassado (int qtdSegundos) throws Exception // nao altera o this
    {
        Horario retorno = new Horario (this);
        retorno.retroceda(qtdSegundos);
        return retorno;
    }

    @Override
    public String toString ()
    {
        return (this.hora<10?"0":"")+
                this.hora + ":" +
               (this.minuto<10?"0":"")+
                this.minuto + ":" +
               (this.segundo<10?"0":"")+
                this.segundo;
    }

    // serve para comparar this e obj
    // this sabidamente é Horario, já que estamos no equals de Horario
    // this nunca é null, se fosse dava erro na chamada e não entrava no método
    @Override
    public boolean equals (Object obj)
    {
        if (obj==this) return true;
        if (obj==null) return false;
        if (obj.getClass()!=this.getClass()) return false;
        Horario hor = (Horario)obj;
        if (hor.hora   !=this.hora)    return false;
        if (hor.minuto !=this.minuto)  return false;
        if (hor.segundo!=this.segundo) return false;
        return true;
    }

    @Override
    public int hashCode ()
    {
        int retorno=1 /* um nº natural qualquer, menos o zero */

        retorno = retorno * 2 /* um nº primo qualquer */ + ((Byte)this.hora)   .hashCode();
        retorno = retorno * 2 /* um nº primo qualquer */ + ((Byte)this.minuto) .hashCode();
        retorno = retorno * 2 /* um nº primo qualquer */ + ((Byte)this.segundo).hashCode();
        /*
        caso houvesse nessa classe um atributo chamado atr que fosse um objeto de
        qualquer classe, faríamos, simplesmente:
        if (atr!=null) retorno = retorno * 2 /* um nº primo qualquer */ + atr.hashCode();
        se houvesse chance atr ser null, senão faríamos simplesmente:
        retorno = retorno * 2 /* um nº primo qualquer */ + atr.hashCode();
        */
        /*
        caso houvesse nesta classe um atributo que fosse um vetor, faríamos um loop
        para percorrer o vetor e tratar cada posição como se fosse um atributo separado
        */
        if (retorno<0) retorno=-retorno;
        return retorno;
    }

    // compara this e hor e retorna:
    // um inteiro positivo se this for maior que dat;
    // zero se this for igual a dat; ou
    // um inteiro negativo se this for menor que dat.
    @Override  
    public int compareTo (Horario hor)
    {
        if (this.hora<hor.hora) return -666;
        if (this.hora>hor.hora) return  666;
        if (this.minuto<hor.minuto) return -666;
        if (this.minuto>hor.minuto) return  666;
        if (this.segundo<hor.segundo) return -666;
        if (this.segundo>hor.segundo) return  666;
        return 0;
    }

    // construtor de cópia
    public /*void*/ Horario (Horario modelo) throws Exception
    {
        if (modelo==null) throw new Exception ("Modelo ausente");

        this.hora  =modelo.hora;
        this.minuto=modelo.minuto;
        this.segundo=modelo.segundo;

        // se o atributo fosse um vetor, deveriamos fazer new
        // para criar um novo vetor e entao um for para copiar
        // td do vetor do modelo para o vetor do this

        // se o atributo fosse um objeto ALTERAVEL e sua classe
        // eventualmente o ALTERASSE, deveriamos criar uma copia
        // dele com o construtor de copia ou com o clone
    }

    @Override
    public Object clone ()
    {
        Horario retorno=null;

        try
        {
            retorno = new Horario (this);
        }
        catch (Exception erro)
        {} // sei que nao vai dar exceção pq o construtor de copia só da erro 
           // quando recebe um null no parametro e está sendo fornecido o this
           // e o this nunca é null

        return retorno;
    }
}