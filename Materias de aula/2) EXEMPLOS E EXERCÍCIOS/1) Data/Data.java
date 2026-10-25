public class Data implements Comparable<Data>, Cloneable
{
    private byte  dia, mes;
    private short ano;

    private static int qtd=0;

    public static int getQtd ()
    {
        return Data.qtd;
    }

    public static boolean isBissexto (short ano)
    {
        // Calendario Juliano
        if (ano<1582)
            if (ano%4==0)
                return true;
            else
                return false;

        // Calendario Gregoriano
        if (ano%400==0) return true;
        if (ano%  4==0 && ano%100!=0) return true;
        return false;
    }

    public static boolean isValida (byte dia, byte mes, short ano)
    {
        if (ano<-45) return false; // antes do Calendario Juliano
        if (ano== 0) return false; // nao existiu ano 0; do ano 1ac foi direto para o ano 1dc
        if (ano==1582 && mes==10 && dia>=5 && dia<=14) return false; // dias cortados dos calendario pelo Papa Gregorio

        if (dia<1 || dia>31 || mes<1 || mes>12) return false;

        if (dia>30 && (mes==4 || mes==6 || mes==9 || mes==11)) return false;
        if (dia>29 && mes==2) return false;
        if (dia>28 && mes==2 && !Data.isBissexto(ano)) return false;

        return true;
    }

    public /*void*/ Data (byte dia, byte mes, short ano) throws Exception
    {
        if (!Data.isValida(dia,mes,ano))
            throw new Exception ("Data invalida");

        this.dia=dia;
        this.mes=mes;
        this.ano=ano;

        Data.qtd++;
    }

    public void setDia (byte dia) throws Exception
    {
        if (!Data.isValida(dia,this.mes,this.ano))
            throw new Exception ("Dia invalido");

        this.dia=dia;
    }

    public byte getDia ()
    {
        return this.dia;
    }
    
    public void setMes (byte mes) throws Exception
    {
        if (!Data.isValida(this.dia,mes,this.ano))
            throw new Exception ("Mes invalido");

        this.mes=mes;
    }

    public byte getMes ()
    {
        return this.mes;
    }
    
    public void setAno (short ano) throws Exception
    {
        if (!Data.isValida(this.dia,this.mes,ano))
            throw new Exception ("Ano invalido");

        this.ano=ano;
    }

    public short getAno ()
    {
        return this.ano;
    }

    public void avanceUmDia () // altera o this
    {
        if (this.ano==1582 && this.mes==10 && this.dia==4)
            this.dia=(byte)15;
        else if (this.dia==31 && this.mes==12 && this.ano==-1)
        {
            this.dia=(byte)1;
            this.mes=(byte)1;
            this.ano=(short)1;
        }
        else if (Data.isValida((byte)(this.dia+1),this.mes,this.ano))
            this.dia++;
        else if (Data.isValida((byte)1,(byte)(this.mes+1),this.ano))
        {
            this.dia=(byte)1;
            this.mes++;
        }
        else // só sobrou estar no dia 31/12 de um ano que não é -1
        {
            this.dia=(byte)1;
            this.mes=(byte)1;
            this.ano++;
        }
    }    

    public void avanceVariosDias (int qtd) throws Exception // altera o this
    {
        if (qtd<=0) throw new Exception ("Quantidade invalida");
        for (int i=0; i<qtd; i++) this.avanceUmDia();
    }

    public Data getDiaSeguinte () // não altera o this
    {
        Data retorno=null;

        try
        {
            retorno = new Data (this.dia,this.mes,this.ano);
        }
        catch (Exception erro)
        {} // sei que o construtor nao vai dar Exception porque ele só dá quando recebe uma data invalida e ele esta recebendo dia, mes e ano tirados de dentro de outra Data, que foi validada quando foi criada e portanto nao pode estar errada

        retorno.adianteUmDia();
        return retorno;
    }
    /*
    public Data getDiaSeguinte () // não altera o this
    {
        if (this.ano==1582 && this.mes==10 && this.dia==4)
            { try { return new Data ((byte)15,(byte)10,(short)1582); } catch (Exception erro) {}}
        else if (this.dia==31 && this.mes==12 && this.ano==-1)
            { try { return new Data ((byte)1,(byte)1,(short)1); } catch (Exception erro) {}}
        else
        {
            try
            {
                return new Data ((byte)(this.dia+1),this.mes,this.ano);
            }
            catch (Exception erro)
            {
                try
                {
                    return new Data ((byte)1,(byte)(this.mes+1),this.ano);
                }
                catch (Exception erro)
                {
                    try
                    {
                        return new Data ((byte)1,(byte)1,(byte)(this.ano+1);
                    }
                    catch (Exception erro)
                    {} // agora nao vai dar erro
                }
            }
    }
    */
    public Data getVariosDiasAdiante (int qtd) throws Exception // não altera o this
    {
        Data retorno=null;

        try
        {
            retorno = new Data (this.dia,this.mes,this.ano);
        }
        catch (Exception erro)
        {} // sei que o construtor nao vai dar Exception porque ele só dá quando recebe uma data invalida e ele esta recebendo dia, mes e ano tirados de dentro de outra Data, que foi validada quando foi criada e portanto nao pode estar errada

        retorno.avanceVariosDias(qtd);
        return retorno;  
    }

    public void retrocedaUmDia () throws Exception // altera o this
    {
        if (this.dia==1 && this.mes==1 && this.ano==-45) 
            throw new Exception ("Tentativa de tornar o ano -46!")

        if (this.ano==1582 && this.mes==10 && this.dia==15)
        {
            this.dia=(byte)4;
        }
        else if (this.dia==1 && this.mes==1 && this.ano==1)
        {
            this.dia=(byte)31;
            this.mes=(byte)12;
            this.ano=(short)-1;
        }
        else if (Data.isValida((byte)(this.dia-1),this.mes,this.ano))
            this.dia--;
        else if (Data.isValida((byte)31,(byte)(this.mes-1),this.ano))
        {
            this.dia=(byte)31;
            this.mes--;
        }
        else if (Data.isValida((byte)30,(byte)(this.mes-1),this.ano))
        {
            this.dia=(byte)30;
            this.mes--;
        }
        else if (Data.isValida((byte)29,(byte)(this.mes-1),this.ano))
        {
            this.dia=(byte)29;
            this.mes--;
        }
        else if (Data.isValida((byte)28,(byte)(this.mes-1),this.ano))
        {
            this.dia=(byte)28;
            this.mes--;
        }
        else // só sobrou estar no dia 1/1 de um ano que não é 1 e nem -45
        {
            this.dia=(byte)31;
            this.mes=(byte)12;
            this.ano--;
        }
    }

    public void retrocedaVariosDias (int qtd) throws Exception // altera o this
    {
        if (qtd<=0) throw new Exception ("Quantidade invalida");
        for (int i=0; i<qtd; i++) this.retrocedaUmDia();
    }

    public Data getDiaAnterior () throws Exception // não altera o this
    {
        // originalmente erra conforme abaixo, usando
        // de forma gambiarrenta, um construtor que nao
        // era o de copia
        /*
        Data retorno=null;

        try
        {
            retorno = new Data (this.dia,this.mes,this.ano);
        }
        catch (Exception erro)
        {} // sei que nao vai dar erro pq criei a nova data a partir duma velha que, quando foi criada, ja foi validada

        retorno.retrocedaUmDia();
        return retorno;
        */

        Data retorno = (Data)this.clone();
        retorno.retrocedaUmDia();
        return retorno;
    }

    public Data getVariosDiasAtras (int qtd) throws Exception // não altera o this
    {
        Data retorno=null;

        try
        {
            // era conforme a linha abaixo, gambiarrento
            // retorno = new Data (this.dia,this.mes,this.ano);
            retorno = new Data (this);
        }
        catch (Exception erro)
        {} // sei que nao vai dar erro pq criei a nova data a partir duma velha que, quando foi criada, ja foi validada

        retorno.retrocedaVariosDias(qtd);
        return retorno; 
    }

    @Override
    public String toString ()
    {
        /*
        return (this.dia + "/" +
                this.mes + "/" +
                this.ano);
        */
        return (this.dia<10?"0":"")+
                this.dia + "/" +
               (this.mes<10?"0":"")+
                this.mes + "/" +
               (this.ano<0?(-this.ano)+"ac":this.ano);
    }
    
    // compara this e obj
    @Override
    public boolean equals (Object obj)
    {
        if (obj==this) return true;
        if (obj==null) return false; // this nunca é null
        if (obj.getClass() != this.getClass()) return false; // sei que this.getClass() é Data
        Data d = (Data)obj;
        if (d.dia!=this.dia) return false;
        if (d.mes!=this.mes) return false;
        if (d.ano!=this.ano) return false;
        return true;
    }

    @Override
    public int hashCode ()
    {
        int retorno=1 /* um nº natural qualquer, menos o zero */

        retorno = retorno * 2 /* um nº primo qualquer */ + ((Byte) this.dia).hashCode();
        retorno = retorno * 2 /* um nº primo qualquer */ + ((Byte) this.mes).hashCode();
        retorno = retorno * 2 /* um nº primo qualquer */ + ((Short)this.ano).hashCode();
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

    // compara this e dat e retorna:
    // um inteiro positivo se this for maior que dat;
    // zero se this for igual a dat; ou
    // um inteiro negativo se this for menor que dat.
    @Override
    public int compareTo (Data dat)
    {
        if (this.ano<dat.ano) return -666;
        if (this.ano>dat.ano) return  666;
        if (this.mes<dat.mes) return -666;
        if (this.mes>dat.mes) return  666;
        if (this.dia<dat.dia) return -666;
        if (this.dia>dat.dia) return  666;
        return 0;
    }

    // construtor de cópia
    public /*void*/ Data (Data modelo) throws Exception
    {
        if (modelo==null) throw new Exception ("Modelo ausente");

        this.dia=modelo.dia;
        this.mes=modelo.mes;
        this.ano=modelo.ano;

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
        Data retorno=null;

        try
        {
            retorno = new Data (this);
        }
        catch (Exception erro)
        {} // sei que nao vai dar exceção pq o construtor de copia só da erro 
           // quando recebe um null no parametro e está sendo fornecido o this
           // e o this nunca é null

        return retorno;
    }
}
