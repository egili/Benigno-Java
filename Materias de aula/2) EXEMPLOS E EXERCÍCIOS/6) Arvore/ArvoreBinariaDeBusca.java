import java.lang.reflect.*;

public class ArvoreBinariaDeBusca <X extends Comparable<X>>
{
    private class No
    {
        private No esq;
        private X  info;
        private No dir;

        public No (No e, X i, No d)
        {
            this.esq  = e;
            this.info = i;
            this.dir  = d;
        }

        public No (X i)
        {
            this.esq  = null;
            this.info = i;
            this.dir  = null;
        }

        public No getEsq ()
        {
            return this.esq;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getDir ()
        {
            return this.dir;
        }

        public void setEsq (No e)
        {
            this.esq = e;
        }
        
        public void setInfo (X i)
        {
            this.info = i;
        }

        public void setDir (No d)
        {
            this.dir = d;
        }
    } //fim da classe No

    private Clonador<X> clonador = new Clonador<X> ();
    private No raiz;

    public void guardeUmItem (X i) throws Exception
    {
        if (i==null) throw new Exception ("Informacao ausente");
        
        if (this.raiz==null)
        {
            if (i instanceof Cloneable) i=this.clonador.clone(i);
            this.raiz = new No (i);
            return;
        }
        
        No atual=this.raiz;
        for(;;) // forever
        {
            int comparacao=i.compareTo(atual.getInfo());
            
            if (comparacao==0) throw new Exception ("Elemento repetido");
            
            if (comparacao<0)
            {
                if (atual.getEsq()==null)
                {
                    if (i instanceof Cloneable) i=this.clonador.clone(i);
                    atual.setEsq (new No (i));
                    return;
                }
                else
                    atual=atual.getEsq();
            }
            else // comparacao>0
            {
                if (atual.getDir()==null)
                {
                    if (i instanceof Cloneable) i=this.clonador.clone(i);
                    atual.setDir (new No (i));
                    return;
                }
                else
                    atual=atual.getDir();
            }
        }
    }
    
    public boolean temOItem (X i) throws Exception
    {
        if (i==null) throw new Exception ("Informacao ausente");
        if (this.raiz==null) return false;
        
        No atual=this.raiz;
        while (atual!=null)
        {
            int comparacao=i.compareTo(atual.getInfo());
            if (comparacao==0) return true;
            if (comparacao<0)
                atual=atual.getEsq();
            else // comparacao>0
                atual=atual.getDir();
        }
        return false;
    }
    
    private int getQtdDeNodos (No r)
    {
        if (r==null) return 0;
        return this.getQtdDeNodos(r.getEsq()) +
               1 +
               this.getQtdDeNodos(r.getDir());
    }
    
    public int getQtdDeNodos ()
    {
        return getQtdDeNodos (this.raiz);
    }

    public void removaUmItem (X i) throws Exception
    {
        if (i==null) throw new Exception ("Informacao ausente");
        if (this.raiz == null) throw new Exception("Arvore vazia");

        No atual = this.raiz;
        No pai = null;
        boolean filhoEsquerdo;

        for(;;) // forever
        {
            int comparacao = i.compareTo(atual.getInfo());
            if (comparacao==0) break;
     
            pai = atual;
            if (comparacao<0)
            {
                atual=atual.getEsq();
                filhoEsquerdo=true;
            }
            else // comparacao>0
            {
                atual=atual.getDir();
                filhoEsquerdo=false;
            }
            
            if (atual==null) throw new Exception ("Remocao de algo inexistente");
        }

        // se a info for encontrada numa folha, deslique a folha da árvore,
        // fazendo o ponteiro que aponta para ela dentro do seu nó pai,
        // tornar-se null
        if (atual.getEsq()==null && atual.getDir()==null)
        {
            if (atual==this.raiz)
                this.raiz=null;
            else if (filhoEsquerdo)
                pai.setEsq(null);
            else
                pai.setDir(null);
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if (atual.getDir()==null && filhoEsquerdo)
        {
            if (atual==this.raiz)
                this.raiz=atual.getEsq();
            else
                pai.setEsq(atual.getEsq());
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho direito de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else // ...
    }

    /*
    private int getAltura (No r)
    {
        // faça
    }
    
    public int getAltura ()
    {
        //faça
    }
    
    private boolean isBalanceada (No r)
    {
        // faça
    }
    
    public boolean isBalanceada ()
    {
        // faça
    }
    
    private void balanceieSe (No r)
    {
        // faça
    }

    public void balanceieSe ()
    {
        // faça
    }
    */
    /*
    // implemente os 4 métodos abaixo, imaginando
    // que a árvore não é uma árvore binária
    // DE BUSCA.
    
    private boolean isEspelho (No r1, No r2)
    {
        // faça
    }    
    
    public boolean isEspelho (ArvoreBinariaDeBusca<X> arv)
    {
        // faça
    }
    
    private void espelheSe (No r)
    {
        // faça
    }
    
    public void espelheSe ()
    {
        // faça
    }
    */

    private String toString (No r)
    {
		if (r==null) return "()";
		return "(" +
		       this.toString(r.getEsq()) +
		       r.getInfo() +
		       this.toString(r.getDir()) +
		       ")";
	}
	
	@Override
	public String toString ()
	{
		return this.toString(this.raiz);
	}

    private boolean equals (No r1, No r2)
    {
		// faça
	}
	
	@Override
	public boolean equals (Object obj)
	{
		if (obj==this) return true;
		if (obj==null) return false;
		if (obj.getClass()!=this.getClass()) return false;
		
		ArvoreBinariaDeBusca<X> arv = (ArvoreBinariaDeBusca<X>)obj;
		return this.equals(this.raiz,arv.raiz);
	}

    // fazer todos os métodos cabíveis dentre toString, equals,
    // hashCode, construtor de copia, clone e compareTo.

} // fim da classe ArvoreBinariaDeBusca
