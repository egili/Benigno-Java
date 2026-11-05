public class Fila <X> implements PadraoDeFila<X>, Cloneable
{
    protected X[] elemento;
    protected int ultimo = -1; // índice do último elemento; -1 = vazia
    private final int tamanhoInicial;

    public Fila (int tamanhoMaximo) throws Exception
    {
        if (tamanhoMaximo<=0) throw new Exception ("Capacidade inválida");

        this.tamanhoInicial=tamanhoMaximo;
        this.elemento=(X[])new Object [tamanhoMaximo];
    }

    public Fila (Fila<X> modelo) throws Exception
    {
        if (modelo==null) throw new Exception ("Modelo ausente");

        this.tamanhoInicial=modelo.tamanhoInicial;
        this.elemento=(X[])new Object [modelo.elemento.length];
        this.ultimo=modelo.ultimo;

        for (int pos=0; pos<=this.ultimo; pos++)
            this.elemento[pos]=this.copie (modelo.elemento[pos]);
    }

    @Override
    public void guardeItem (X item) throws Exception
    {
        if (item==null) throw new Exception ("Elemento ausente");

        if (this.ultimo+1==this.elemento.length) this.redimensione (this.elemento.length*2);

        this.ultimo++;
        this.elemento[this.ultimo]=this.copie (item);
    }

    @Override
    public X getItem () throws Exception
    {
        if (this.ultimo==-1) throw new Exception ("Nada a recuperar");

        return this.copie (this.elemento[0]);
    }

    @Override
    public void removaItem () throws Exception
    {
        if (this.ultimo==-1) throw new Exception ("Nada a remover");

        for (int pos=0; pos<this.ultimo; pos++)
            this.elemento[pos]=this.elemento[pos+1];

        this.elemento[this.ultimo]=null;
        this.ultimo--;

        if (this.elemento.length/2>=this.tamanhoInicial && (this.ultimo+1)*4<=this.elemento.length)
            this.redimensione (this.elemento.length/2);
    }

    private void redimensione (int novoTamanho)
    {
        X[] novo=(X[])new Object [novoTamanho];

        for (int pos=0; pos<=this.ultimo; pos++)
            novo[pos]=this.elemento[pos];

        this.elemento=novo;
    }

    private X copie (X item) throws Exception
    {
        if (item instanceof Cloneable) return (X)new Clonador<X> ().clone (item);

        return item;
    }

    @Override
    public String toString ()
    {
        String retorno="Fila com "+(this.ultimo+1)+" elemento(s):";

        for (int pos=0; pos<=this.ultimo; pos++)
            retorno+=" "+this.elemento[pos];

        return retorno;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj==this) return true;
        if (obj==null) return false;
        if (obj.getClass()!=this.getClass()) return false;

        Fila<X> fila=(Fila<X>)obj;

        if (this.ultimo!=fila.ultimo) return false;

        for (int pos=0; pos<=this.ultimo; pos++)
            if (!this.elemento[pos].equals(fila.elemento[pos])) return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int retorno=1;

        retorno=retorno*2+((Integer)this.ultimo).hashCode();

        for (int pos=0; pos<=this.ultimo; pos++)
            retorno=retorno*2+this.elemento[pos].hashCode();

        if (retorno<0) retorno=-retorno;

        return retorno;
    }

    @Override
    public Object clone ()
    {
        Fila<X> retorno=null;

        try
        {
            retorno=new Fila<X> (this);
        }
        catch (Exception erro)
        {
            // não ocorre: modelo é this, que nunca é null
        }

        return retorno;
    }
}
