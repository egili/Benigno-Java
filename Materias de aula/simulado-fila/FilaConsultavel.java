public class FilaConsultavel <X> extends Fila<X>
{
    public FilaConsultavel (int tamanhoMaximo) throws Exception
    {
        super (tamanhoMaximo);
    }

    public FilaConsultavel (FilaConsultavel<X> modelo) throws Exception
    {
        super (modelo);
    }

    public int getQuantidade ()
    {
        return this.ultimo+1;
    }

    public boolean isVazia ()
    {
        return this.ultimo==-1;
    }

    public boolean isCheia ()
    {
        return this.ultimo+1==this.elemento.length;
    }

    @Override
    public Object clone ()
    {
        FilaConsultavel<X> retorno=null;

        try
        {
            retorno=new FilaConsultavel<X> (this);
        }
        catch (Exception erro)
        {
            // não ocorre: modelo é this, que nunca é null
        }

        return retorno;
    }
}
