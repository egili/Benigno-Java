/**
A classe Agenda1 representa uma simples agenda de telefone implemementada tendo
como base um vetor que armazena instâncias da classe Contato, que é uma classe
interna da interface Agenda, implementada por esta classe.
Instâncias desta classe permitem a relização das operações básicas de uma agenda.
Nela encontramos, por exemplo, métodos para incluir, exclir e listar
contatos.
@author André Luís dos Reis Gomes de Carvalho.
@since 2000.
*/
public class Agenda1 implements Agenda
{
    /**
    Atributo que representa, em qualquer instante, a quantidade de contatos
    armazenados em uma agenda.
    */
    protected int qtdContatos=0;

    /**
    Atributo que representa o conjunto dos contatos armazenados em uma
    agenda.
    */
    protected Agenda.Contato[] contato;

    /**
    Localiza um nome dado na agenda.
    Procura um contato na agenda, pelo método da busca binária,
    resultando um número inteiro negativo, quando o nome
    procurado não tiver sido encontrado, ou um numero inteiro
    positivo, quando o nome procurado tiver sido encontrado.
    @param nom o nome a ser procurado.
    @return um inteiro ao qual se deve dar a seguinte interpretação:
            <ol>
             <li>
              Um inteiro i negativo é retornado quando o nome
              procurado não foi encontrado, mas, caso ele fosse ser
              inserido, para manter os nomes da agenda em ordem
              alfabética, o local apropriado para a inserção seria
              a posição -i-1.
             </li>
             <li>
              Um inteiro i positivo é retornado quando o nome
              procurado foi encontrado na posição i-1.
             </li>
            </ol>
    */
    protected int ondeEsta (String nom)
    {
        int inicio     = 0,
            fim        = this.qtdContatos - 1,
            meio       = 0,
            comparacao;

        while (inicio <= fim)
        {
            meio       = (inicio + fim) / 2;
            comparacao = nom.compareTo (this.contato[meio].getNome());

            if (comparacao == 0)
                return meio+1;
            else
                if (comparacao < 0)
                    fim = meio-1;
                else
                    inicio = meio+1;
        }

        return -(inicio+1);
    }

    /**
    Constroi uma nova instância da classe Agenda1.
    Para tanto, deve ser fornecido um inteiro que será utilizado
    como capacidade da instância recém criada.
    @param cap o número inteiro a ser utilizado como capacidade.
    @throws Exception se a capacidade for negativa ou zero.
    */
    public Agenda1 (int cap) throws Exception
    {
        if (cap <= 0)
            throw new Exception ("Capacidade invalida");

        this.contato = new Agenda.Contato [cap];
    }

    /**
    Inclui um novo contato em uma agenda.
    Acrescenta um contato com o nome e telefone fornecidos na instância
    à qual este método for aplicado.
    @param nom o nome do novo contato.
    @param tel o telefone do novo contato.
    @throws Exception se não for fornecido um nome, ou se o nome fornecido
                      não parecer ser um nome correto, ou se não for fornecido
                      um telefone, ou se o telefone fornecido não parecer ser
                      um telefone correto, ou se a agenda estiver cheia, ou
                      ainda se o nome fornecido já estiver cadastrado.
    */
    public void registreContato (String nom, String tel) throws Exception
    {
        if (this.qtdContatos == this.contato.length)
            throw new Exception ("Agenda cheia");

        Agenda.Contato.valideNome     (nom);
        Agenda.Contato.valideTelefone (tel);

        int posicao = this.ondeEsta (nom);

        if (posicao > 0)
            throw new Exception ("Nome ja registrado");

        posicao = (-posicao)-1;

        for (int pos=this.qtdContatos-1; pos>=posicao; pos--)
            this.contato [pos+1] = this.contato [pos];

        this.contato [posicao] = new Agenda.Contato (nom, tel);

        this.qtdContatos++;
    }

    /**
    Remove um contato, dado seu nome.
    Exclui da instância à qual este método for aplicado o  contato
    cujo nome foi fornecido.
    @param nom o nome do contato a ser descartado.
    @throws Exception se não for fornecido um nome, ou se o nome fornecido
                      não parecer ser um nome correto, ou se a agenda
                      estiver vazia, ou ainda se a agenda não contiver um
                      contato com o nome fornecido.
    */
    public void descarteContato (String nom) throws Exception
    {
        if (this.qtdContatos == 0)
            throw new Exception ("Agenda vazia");

        Agenda.Contato.valideNome (nom);

        int posicao = this.ondeEsta (nom);

        if (posicao < 0)
            throw new Exception ("Nome inexistente");

        posicao--;

        int pos;

        for (pos = posicao; pos < this.qtdContatos - 1; pos++)
            this.contato [pos] = this.contato [pos+1];

        this.contato [pos] = null;

        this.qtdContatos--;
    }

    /**
    Gera uma representação textual de todo conteúdo da agenda.
    Produz e resulta um String com todos os nomes e telefones contidos
    na agenda.
    @return um String contendo todo o conteúdo da agenda.
    */
    public String toString ()
    {
        String ret = "";

        for (int pos=0; pos<this.qtdContatos; pos++)
            ret += this.contato [pos] + "\n";

        return ret;
    }

    /**
    Verifica a igualdade entre duas agendas.
    Verifica se o Object fornecido como parâmetro representa uma
    agenda igual àquela representada pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem agendas iguais, ou false, caso contrário.
    */
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Agenda1 agenda = (Agenda1)obj;

        if (this.qtdContatos != agenda.qtdContatos)
            return false;

        for (int pos=0; pos<this.qtdContatos; pos++)
            if (!this.contato[pos].equals(agenda.contato[pos]))
                return false;

        return true;
    }

    /**
    Calcula o código de espalhamento (ou código de hash) de uma agenda.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da agenda representada pela instância à qual o método for aplicado.
    @return o código de espalhamento da agenda chamante do método.
    */
    public int hashCode ()
    {
        int ret = 666;

        ret = 13*ret + this.qtdContatos;

        for (int pos=0; pos<this.qtdContatos; pos++)
            ret = 13*ret + this.contato[pos].hashCode();

        return ret;
    }

    /**
    Constroi uma cópia da instância da classe Agenda1 dada.
    Para tanto, deve ser fornecida uma instancia da classe Agenda1 para ser
    utilizada como modelo para a construção da nova instância criada.
    @param modelo a instância da classe Agenda1 a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    public Agenda1 (Agenda1 modelo)
                   throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        this.contato = new Agenda.Contato [modelo.contato.length];

        this.qtdContatos = modelo.qtdContatos;

        for (int pos=0; pos<this.qtdContatos; pos++)
            this.contato[pos] = (Agenda.Contato)modelo.contato[pos].clone();
    }

    /**
    Clona uma agenda.
    Produz e resulta uma cópia da agenda representada pela instância
    à qual o método for aplicado.
    @return a cópia da agenda representada pela instância à qual
            o método for aplicado.
    */
    public Object clone ()
    {
        Agenda1 copia=null;

        try
        {
            copia = new Agenda1 (this);
        }
        catch (Exception e)
        {}

        return copia;
    }
}