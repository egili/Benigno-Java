import java.lang.reflect.*;

/**
A classe Deposito representa um repositório genérico de ítens.
Instâncias desta classe permitem a relização das operações básicas de uma repositório.
Nela encontramos, por exemplo, métodos para guardar ítens, jogar fora ítens guardados,
verificar a presença de ítens no repositório e listar ítens.
@author André Luís dos Reis Gomes de Carvalho.
@since 2016.
*/
public class Deposito <X> implements Cloneable // X é forçosamente uma classe
{
    /**
    Mantém armazenados os ítens armazenados no deposito.
    */
    protected Object[] elem;

    /**
    Expressa, em cada instante, a posição na qual se encontra ammazenado o último ítem
    do deposito.
    */
    protected int ultimo;

    /**
    Constroi uma nova instância da classe genérica Deposito.
    Para tanto, deve ser fornecido um inteiro que será utilizado
    como capacidade da instância recém criada.
    @param tam número inteiro a ser utilizado como capacidade.
    @throws Exception se a capacidade for negativa ou zero.
    */
    public Deposito (int tam) throws Exception
    {
        if (tam<=0)
            throw new Exception ("Tamanho invalido");

        elem   = new Object [tam];
        ultimo = -1;
    }

    /**
    Armazena um novo ítem em um depósito.
    Acrescenta um novo ítem na instância à qual este método for aplicado.
    @param i o ítem a ser guardado.
    @throws Exception se o deposito estiver cheio.
    */
    public void guarde (X i) throws Exception
    {
        if (ultimo==elem.length-1)
            throw new Exception ("Deposito cheio");

        ultimo++;

        if (i instanceof Cloneable)
        {
            Class classe            = i.getClass();
            Class<?>[] parmsFormais = null; // = null é desnecessário
            Method metodo           = classe.getMethod ("clone", parmsFormais);
            Object[] parmsReais     = null;
            elem[ultimo]            = (X)metodo.invoke(i,parmsReais);

          //elem[ultimo] = (X)i.clone();
        }
        else
            elem[ultimo] = i;
    }

    /**
    Verifica se um certo ítem se encontra armazenado.
    Resulta um valor lógico, verdadeiro ou falso, conforme o ítem fornecido
    possa ou não ser encontrado no depósito.
    @return a possibilidade de encontrar o ítem dado.
    @throws Exception se não for fornecido o ítem a ser procurado.
    */
    public boolean tem (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Ítem a pesquisar náo fornecido");

        for (int pos=0; pos<=ultimo; pos++)
            if (elem[pos].equals(i))
                return true;

        return false;
    }

    /**
    Recupera um ítem armazenado em uma posição fornecida.
    Recupera um ítem, dado o número de ordem dele no depósito.
    @return o ítem recuperado.
    @throws Exception o número de ordem fornecido náo se referir a uma posição válida
                      no depósito.
    */
    public X getElem (int pos) throws Exception
    {
        if (pos<0 || pos>this.ultimo)
            throw new Exception ("Posicao invalida");

        if (this.elem[pos] instanceof Cloneable)
        {
            Class classe            = this.elem[pos].getClass();
            Class<?>[] parmsFormais = null; // = null é desnecessário
            Method metodo           = classe.getMethod ("clone", parmsFormais);
            Object[] parmsReais     = null;
            return (X)metodo.invoke(this.elem[pos],parmsReais);

          //return (X)this.elem[pos].clone();
        }
        else
            return (X)this.elem[pos];
    }

    /**
    Remove um ítem do depósito.
    Exclui da instância à qual este método for aplicado o ítem fornecido.
    @param nom o ítem a ser jogado fora.
    @throws Exception se não for fornecido um ítem a ser descartato.
    */
    public void jogueFora (X i) throws Exception
    {
        int pos;

        if (ultimo==-1)
            throw new Exception ("Deposito vazio");

        for (pos=0; pos<=ultimo; pos++)
            if (elem[pos].equals(i))
                break;

        if (pos==ultimo+1)
            throw new Exception ("Valor inexistente");

        for ( ; pos<ultimo; pos++)
            elem[pos] = elem[pos+1];

        ultimo--;
    }

    /**
    Verifica a igualdade entre dois depósitos.
    Verifica se o Object fornecido como parâmetro representa um
    depósito igual àquele representada pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem depósitos iguais, ou false, caso contrário.
    */
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

      //if (!(obj instanceof Deposito<X>))
        if (this.getClass() != obj.getClass())
            return false;

        Deposito dep = (Deposito)obj;

        if (this.ultimo!=dep.ultimo)
            return false;

        for (int i=0; i<=this.ultimo; i++)
            if (!this.elem[i].equals(dep.elem[i]))
                return false;

        return true;
    }

    /**
    Gera uma representação textual de todo conteúdo do depósito.
    Produz e resulta um String com todos os ítens contidos no deposito.
    @return um String contendo todo o conteúdo do depósito.
    */
    public String toString ()
    {
        String ret="{";

        for (int i=0; i<this.ultimo; i++)
            ret += this.elem[i]+",";

        if (this.ultimo>-1)
            ret += this.elem[this.ultimo];

        ret += "}";

        return ret;
    }

    /**
    Calcula o código de espalhamento (ou código de hash) de um depósito.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) do depósito representado pela instância à qual o método for aplicado.
    @return o código de espalhamento do depósito chamante do método.
    */
    public int hashCode ()
    {
        final int O_PRIMO_ESCOLHIDO = 13;

        int ret = 666;

        ret = ret*O_PRIMO_ESCOLHIDO + Integer.valueOf(this.ultimo).hashCode();

        for (int i=0; i<=this.ultimo; i++)
            ret = ret*O_PRIMO_ESCOLHIDO + this.elem[i].hashCode();

        return ret;
    }

    /**
    Constroi uma cópia da instância da classe Deposito dada.
    Para tanto, deve ser fornecida uma instancia da classe Depósito para ser
    utilizada como modelo para a construção da nova instância criada.
    @param modelo a instância da classe Depósito a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    public Deposito (Deposito<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.elem = new Object [modelo.elem.length];

        for (int i=0; i<=modelo.ultimo; i++)
            if (modelo.elem[i] instanceof Cloneable)
            {
                Class classe            = modelo.elem[i].getClass();
                Class<?>[] parmsFormais = null; // = null é desnecessário
                Method metodo           = classe.getMethod ("clone", parmsFormais);
                Object[] parmsReais     = null;
                this.elem[i]            = (X)metodo.invoke(modelo.elem[i],parmsReais);

              //this.elem[i] = (x)modelo.elem[i].clone();
            }
            else
                this.elem[i] = modelo.elem[i];

        this.ultimo = modelo.ultimo;
    }

    /**
    Clona um depósito.
    Produz e resulta uma cópia do depósito representado pela instância
    à qual o método for aplicado.
    @return a cópia do depósito representado pela instância à qual
            o método for aplicado.
    */
    public Object clone ()
    {
        Deposito<X> ret=null;

        try
        {
            ret = new Deposito<X> (this);
        }
        catch (Exception erro)
        {} // tenho certeza que nao vai acontecer

        return ret;
    }
}
