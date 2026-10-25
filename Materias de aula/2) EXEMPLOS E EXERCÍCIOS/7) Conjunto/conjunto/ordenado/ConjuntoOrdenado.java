package conjunto.ordenado;

// import conjunto.*;
import conjunto.Conjunto;

public class ConjuntoOrdenado<X extends Comparable<X>> extends Conjunto<X>
{
    public /*void*/ ConjuntoOrdenado (int capInicial) throws Exception
    {
        super(capInicial);
        // comandos para inicializar atributos proprios, se existissem
    }
            
    public /*void*/ ConjuntoOrdenado () throws Exception // construtor padrao
    {
        this(15);
    }
            
    // faz busca binária e retorna true, indicando que achou o que era procurado e,
    // junto com o true, a posição onde foi encontrado o que era procurado; ou
    // retorna false, indicando que NÃO achou o que era procurado e,
    // junto com o false, a posição onde é possível incluir aquilo que era procurado
    // e NÃO foi encontrado, caso seja do nosso desejo incluir
    @Override
    protected Object[] ondeEsta (X x)
    {
        int inicio=0, fim=this.qtd-1, meio;
        
        while (inicio<=fim)
        {
            meio = (inicio+fim)/2;
                                    
            int comp = x.compareTo(this.elem[meio]);
                                    
            if (comp==0) // achei
            {
                Object[] ret = {true,meio};
                return ret;
            }
                                                    
            if (comp<0)
               fim = meio-1;
            else
               inicio = meio+1;
        }
                        
        Object[] ret = {false,inicio};
        return ret;
    }
    
    @Override
    public String toString ()
    {
        String ret = super.toString();
        ret = "["+ret.subString(1);
        ret = ret.subString(0,ret.length()-1)+"]";
        return ret;
    }

    public /*void*/ ConjuntoOrdenado (ConjuntoOrdenado<X> modelo) throws Exception
    {
        super(modelo);
    }
            
    @Override
    public Object clone ()
    {
        ConjuntoOrdenado<X> ret=null;
                        
        try
        {
            ret = new ConjuntoOrdenado<X> (this);
        }
        catch (Exception erro)
        {}
        
        return ret;
    }
}
