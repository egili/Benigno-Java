import java.util.ArrayList;

/**
A classe Agenda3 representa uma simples agenda de telefone implemementada tendo
como base um ArrayList que armazena instâncias da classe Contato, que é uma classe
interna da interface Agenda, implementada por esta classe.
Instâncias desta classe permitem a relização das operações básicas de uma agenda.
Nela encontramos, por exemplo, métodos para incluir, excluir e listar
contatos.
@author André Luís dos Reis Gomes de Carvalho.
@since 2000.
*/
public class Agenda3 implements Agenda
{
    /**
    Atributo que armazena os contatos destinados à agenda.
    */
    protected ArrayList<Agenda.Contato> contatos;

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
            fim        = this.contatos.size() - 1,
            meio       = 0,
            comparacao = -1;

        while (inicio <= fim)
        {
            meio       = (inicio + fim) / 2;
            comparacao = nom.compareTo (this.contatos.get(meio).getNome());

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
    Constroi uma cópia da instância da classe Agenda dada.
    Para tanto, deve ser fornecida uma instancia da classe Agenda para ser
    utilizada como modelo para a construção da nova instância criada.
    @param modelo a instância da classe Agenda4 a ser usada como modelo.
    @throws Exception se o modelo for null.
    */

    public Agenda3 ()
    {
        contatos = new ArrayList<Agenda.Contato> ();
    }

    /**
    Inclui um novo contato em uma agenda.
    Acrescenta um contato com o nome e telefone fornecidos na instância
    à qual este método for aplicado.
    @param nom o nome do novo contato.
    @param tel o telefone do novo contato.
    @throws Exception se não for fornecido um nome, ou se o nome fornecido
                      não parecer ser um nome correto, ou se a agenda
                      estiver vazia, ou ainda se a agenda não contiver um
                      contato com o nome fornecido.
    */
    public void registreContato (String nom, String tel) throws Exception
    {
        Agenda.Contato.valideNome     (nom);
        Agenda.Contato.valideTelefone (tel);

        int            posicao = this.ondeEsta (nom);

        if (posicao > 0)
            throw new Exception ("Nome ja registrado");

        posicao = (-posicao)-1;

        this.contatos.add (posicao, new Agenda.Contato(nom,tel));
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
        if (this.contatos.size() == 0)
            throw new Exception ("Agenda vazia");

        Agenda.Contato.valideNome (nom);

        int posicao = this.ondeEsta (nom);

        if (posicao < 0)
            throw new Exception ("Nome inexistente");

        posicao--;

        this.contatos.remove (posicao);
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

        for (Agenda.Contato c : this.contatos)
            ret += c.getNome() + "(" + c.getTelefone() + ")\n";

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

        Agenda3 agenda = (Agenda3)obj;

        return this.contatos.equals(agenda.contatos);
    }

    /**
    Calcula o código de espalhamento (ou código de hash) de uma agenda.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da agenda representada pela instância à qual o método for aplicado.
    @return o código de espalhamento da agenda chamante do método.
    */
    public int hashCode ()
    {
        return this.contatos.hashCode();
    }

    /**
    Constroi uma cópia da instância da classe Agenda dada.
    Para tanto, deve ser fornecida uma instancia da classe Agenda para ser
    utilizada como modelo para a construção da nova instância criada.
    @param modelo a instância da classe Agenda4 a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    public Agenda3 (Agenda3 modelo)
                    throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo nao fornecido");

        /*
        Não fosse o fato do método clone da classe Vector fazer shall copy
        (em vez de deep copy), poderiamos substituir tudo que segue este
        comentário, simplesmente, por:

        this.contatos = (Vector<Agenda.Contato>)modelo.contatos.clone();
        */

        this.contatos = new ArrayList<Agenda.Contato> (modelo.contatos.size());

        for (Agenda.Contato c : modelo.contatos)
            this.contatos.add ((Agenda.Contato)c.clone());
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
        Agenda3 copia=null;

        try
        {
            copia = new Agenda3 (this);
        }
        catch (Exception e)
        {}

        return copia;
    }
}
