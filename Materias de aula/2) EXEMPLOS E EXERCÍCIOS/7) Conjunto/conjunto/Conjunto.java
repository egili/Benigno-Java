package conjunto;

// import clonador.*;
import clonador.Clonador;

public class Conjunto <X> implements Cloneable
{
    protected X[] elem;
    protected int qtd=0;
    protected int capacidadeInicial;
        
    public /*void*/ Conjunto (int capacidadeInicial) throws Exception
    {
        if (capacidadeInicial<=0) throw new Exception ("Capacidade invalida");
            
      //this.elem =       new X      [capacidadeInicial];
        this.elem = (X[]) new Object [capacidadeInicial];
        
        this.capacidadeInicial = capacidadeInicial;
    }

    public /*void*/ Conjunto () // construtor padrão (construtor sem parâmetros)
    {
      //this.elem =       new X      [10];
        this.elem = (X[]) new Object [10];
        
        this.capacidadeInicial = 10;
    }

    protected void redimensioneSe (int novaCap)
    {
      //X[] novo =       new X      [novaCap];
        X[] novo = (X[]) new Object [novaCap];

        for (int i=0; i<this.qtd; i++)
            novo[i] = this.elem[i];

        this.elem = novo;
        // System.gc();
    }

    // faz busca sequencial e retorna true, indicando que achou o que era procurado e,
    // junto com o true, a posição onde foi encontrado o que era procurado; ou
    // retorna false, indicando que NÃO achou o que era procurado e,
    // junto com o false, a posição onde é possível incluir aquilo que era procurado
    // e NÃO foi encontrado, caso seja do nosso desejo incluir
    protected Object[] ondeEsta (X x)
    {
        for (int i=0; i<this.qtd; i++)
            if (x.equals(this.elem[i]))
            {
                Object[] ret = {true,i};
                return ret;
            }

        Object[] ret = {false,this.qtd};
        return ret;
    }
    
    public void inclua (X x) throws Exception
    {
        if (x==null) throw new Exception ("Elemento ausente");

        Object[] onde    = this.ondeEsta(x);
        boolean  achou   = (Boolean)onde[0];
        int      posicao = (Integer)onde[1];

        if (achou) throw new Exception ("Elemento repetido");

        if (this.qtd==this.elem.length)
            this.redimensioneSe (2*this.elem.length);

        for (int i=this.qtd-1; i>=posicao; i--)
            this.elem[i+1] = this.elem[i];

        if (x instanceof Cloneable)
        {
            Clonador<X> clonador = new Clonador<X> (); // aprender clonador na aula que vem
          //this.elem[posicao] = (X)x.clone(); // vai dar erro, pois Object nao tem clone
            this.elem[posicao] = clonador.clone (x);
        }
        else
            this.elem[posicao] = x;

        this.qtd++;
    }

    public X getElemento (int posicao) throws Exception
    {
        if (posicao<0 || posicao>=this.qtd)
            throw new Exception ("Posicao invalida");
            
        if (this.elem[posicao] instanceof Cloneable)
        {
            Clonador<X> clonador = new Clonador<X> (); // aprender clonador na aula que vem
          //return (X)this.elem[posicao].clone(); // vai dar erro, pois Object nao tem clone
            return clonador.clone (this.elem[posicao]);
        }
        else
            return this.elem[posicao];
    }

    public void remova (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Elemento ausente");
            
        Object[] onde    = this.ondeEsta(x);
        boolean  achou   = (Boolean)onde[0];
        int      posicao = (Integer)onde[1];
        
        if (!achou)
            throw new Exception ("Elemento inexistente");
        
        for (int i=posicao; i<this.qtd-1; i++)
            this.elem[i] = this.elem[i+1];
        
        this.qtd--;
        this.elem[this.qtd] = null;
        
        if (this.elem.length>this.capacidadeInicial && this.qtd<=(int)(0.25*this.elem.length))
            this.redimensioneSe (this.elem.length/2);
    }

    public int getQtdElementos ()
    {
        return this.qtd;
    }

    // retornar uma instancia de conjunto com todos os elementos do
    // this e mais todos os elementos do conj, sem repetição de elementos
    public Conjunto<X> uniao (Conjunto<X> conj) throws Exception
    {
        if (conj==null)
            throw new Exception ("Conjunto ausente");

        Conjunto<X> ret = new Conjunto<X> (this.qtd+conj.qtd);

        Conjunto<X> menor, maior;

        if (this.qtd<conj.qtd)
        {
            menor = this;
            maior = conj;
        }
        else
        {
            menor = conj;
            maior = this;
        }

        ret.qtd = maior.qtd;
        for (int i=0; i<maior.qtd; i++)
            ret.elem[i] = maior.elem[i];

        int posicao=ret.qtd;
        for (int i=0; i<menor.qtd; i++)
        {
            Object[] onde = ret.ondeEsta((X)menor.elem[i]);
            boolean achou=(Boolean)onde[0];

            if (!achou)
            {
                ret.elem[posicao++]=menor.elem[i];
                ret.qtd++;
            }
        }

        return ret;
    } 
     
    // retornar uma instancia de conjunto com os elementos comuns
    // ao this e ao conj
    public Conjunto<X> interseccao (Conjunto<X> conj) throws Exception
    {
        if (conj==null)
            throw new Exception ("Conjunto ausente");

        Conjunto<X> menor, maior;

        if (this.qtd<conj.qtd)
        {
            menor = this;
            maior = conj;
        }
        else
        {
            menor = conj;
            maior = this;
        }

        Conjunto<X> ret = new Conjunto<X> (menor.qtd);

        int posicao=ret.qtd;
        for (int i=0; i<menor.qtd; i++)
        {
            Object[] onde = maior.ondeEsta((X)menor.elem[i]);
            boolean achou=(Boolean)onde[0];

            if (achou)
            {
                ret.elem[posicao++]=menor.elem[i];
                ret.qtd++;
            }
        }

        return ret;
    }    
    
    // retornar uma instancia de  conjunto com todos os elementos
    // do this que não são também elementos do conj
    public Conjunto<X> menos (Conjunto<X> conj) throws Exception
    {
        if (conj==null)
            throw new Exception ("Conjunto ausente");

        Conjunto<X> ret = new Conjunto<X> (this.qtd);

        int posicao=0;
        for (int i=0; i<this.qtd; i++)
        {
            Object[] onde = conj.ondeEsta((X)this.elem[i]);
            boolean achou=(Boolean)onde[0];

            if (!achou)
            {
                ret.elem[posicao++]=this.elem[i];
                ret.qtd++;
            }
        }

        return ret;
    }

    // retornar true se o this contém o conj, ou seja se todos
    // os elementos do conj forem também elementos do this;
    // retornar false, caso contrário
    public boolean contem (Conjunto<X> conj) throws Exception
    {
        if (conj==null)
            throw new Exception ("Conjunto ausente");

        for (int i=0; i<conj.qtd; i++)
        {
            Object[] onde = this.ondeEsta(conj.elem[i]);
            boolean achou=(Boolean)onde[0];

            if (!achou) return false;
        }

        return true;
    }  
    
    // retornar true se o conj contém o this, ou seja se todos
    // os elementos do this forem também elementos do conj;
    // retornar false, caso contrário
    public boolean estaContido (Conjunto<X> conj) throws Exception
    {
        if (conj==null)
            throw new Exception ("Conjunto ausente");

        for (int i=0; i<this.qtd; i++)
        {
            Object[] onde = conj.ondeEsta((X)this.elem[i]);
            boolean achou=(Boolean)onde[0];

            if (!achou) return false;
        }

        return true;
    }
    
    @Override
    public String toString ()
    {
        String ret="{";
        
        if (this.qtd>0)
            ret += this.elem[0];
            
        for (int i=1; i<this.qtd; i++)
            ret += ", "+this.elem[i];
        
        return ret+"}";
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this==obj) return true;
        
        if (obj==null) return false;
        
        if (this.getClass()!=obj.getClass()) return false;
        
        Conjunto<X> conj = (Conjunto<X>)obj;
        
        if (this.qtd!=conj.qtd) return false;
        
        if (this.capacidadeInicial!=conj.capacidadeInicial) return false;
        
        //leva em conta a ordem
        //for (int i=0; i<this.qtd; i++)
        //    if (!this.elem[i].equals(conj.elem[i]))
        //        return false;
        

        // sem levar em conta a ordem
        for (int i=0; i<conj.qtd; i++)
        {
            Object[] onde  = this.ondeEsta((X)conj.elem[i]);
            boolean  achou = (Boolean)onde[0];

            if (!achou) return false;
        }
                
        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret=1;
        
        ret = 13*ret + ((Integer)this.qtd       ).hashCode();
        ret = 13*ret + ((Integer)this.capacidadeInicial).hashCode();
        
        for (int i=0; i<this.qtd; i++)
          //if (this.elem[i]!=null)
               ret = 7*ret + this.elem[i].hashCode();
               
        if (ret<0) ret=-ret;
        return ret;    
    }

    //construtor de copia
    public Conjunto (Conjunto<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");
            
        this.qtd               = modelo.qtd;
        this.capacidadeInicial = modelo.capacidadeInicial;
        
      //this.elem       =       new X      [modelo.elem.length];
        this.elem       = (X[]) new Object [modelo.elem.length];
        
        for (int i=0; i<this.qtd; i++)
            this.elem[i] = modelo.elem[i];
    }
    
    @Override
    public Object clone ()
    {
        Conjunto<X> ret=null;
        
        try
        {
            ret = new Conjunto<X> (this);
        }
        catch (Exception erro)
        {}
        
        return ret;
    }
}
