package agenda.desordenada;
import  agenda.*;

/**
A classe AgendaDesordenada representa uma simples agenda de telefone implemementada
tendo como base dois vetores que armazenam, respectivamente, os nomes e os telefones
dos contatos da agenda.
Instâncias desta classe permitem a relização das operações básicas de uma agenda.
Nela encontramos, por exemplo, métodos para incluir, excluir e listar
contatos.
@author André Luís dos Reis Gomes de Carvalho.
@since 2000.
*/
public class AgendaDesordenada extends Agenda
{
    /**
    Localiza um nome dado na agenda.
    Procura um contato na agenda, pelo método da busca sequencial,
    resultando um número inteiro negativo, quando o nome
    procurado não tiver sido encontrado, ou um numero inteiro
    positivo, quando o nome procurado tiver sido encontrado.
    @param nom o nome a ser procurado.
    @return um inteiro ao qual se deve dar a seguinte interpretação:
            <ol>
             <li>
              Um inteiro i negativo é retornado quando o nome
              procurado não foi encontrado, mas, caso ele fosse ser
              inserido, o local apropriado para a inserção seria
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
		int pos;

        for (pos=0; pos<this.qtdContatos; pos++)
            if (nom.equals(this.nome[pos]))
                return pos+1;

        return -(pos+1);
    }

    /**
    Constroi uma nova instância da classe AgendaDesordenada.
    Para tanto, deve ser fornecido um inteiro que será utilizado
    como capacidade da instância recém criada.
    @param cap o número inteiro a ser utilizado como capacidade.
    @throws Exception se a capacidade for negativa ou zero.
    */
    public AgendaDesordenada (int cap)
                              throws Exception
    {
        super (cap);
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
    public void registreContato (String nom,
                                 String tel)
                                 throws Exception
    {
        if (this.qtdContatos == this.nome.length)
            throw new Exception ("Agenda cheia");

        Agenda.valideNome     (nom);
        Agenda.valideTelefone (tel);

        int posicao = this.ondeEsta (nom);

        if (posicao > 0)
            throw new Exception ("Nome ja registrado");

        posicao = (-posicao)-1;

        this.nome     [posicao] = nom;
        this.telefone [posicao] = tel;

        this.qtdContatos++;
    }

    /**
    Constroi uma cópia da instância da classe AgendaDesordenada dada.
    Para tanto, deve ser fornecida uma instancia da classe
    AgendaDesordenada para ser utilizada como modelo para a
    construção da nova instância criada.
    @param modelo a instância da classe AgendaDesordenada a ser usada
           como modelo.
    @throws Exception se o modelo for null.
    */
    public AgendaDesordenada (AgendaDesordenada modelo)
                              throws Exception
    {
        super (modelo);
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
        AgendaDesordenada copia=null;

        try
        {
            copia = new AgendaDesordenada (this);
        }
        catch (Exception e)
        {}

        return copia;
    }
}