package agenda.consultavel;
import  agenda.*;

/**
A classe AgendaConsutavel representa uma simples agenda de telefone
possível de ser consultada.

Instâncias desta classe permitem a relização das operações básicas de uma agenda.
Nela encontramos, não só, métodos para incluir, excluir e listar
contatos, mas também métodos que permitem consultá-la.
@author André Luís dos Reis Gomes de Carvalho.
@since 2000.
*/
public class AgendaConsultavel extends Agenda
{
    /**
    Constroi uma nova instância da classe AgendaConsutavel.
    Para tanto, deve ser fornecido um inteiro que será utilizado
    como capacidade da instância recém criada.
    @param cap o número inteiro a ser utilizado como capacidade.
    @throws Exception se a capacidade for negativa ou zero.
    */
    public AgendaConsultavel (int cap) throws Exception
    {
        super (cap); // invoca o construtor da classe Agenda
    }

    /**
    Obtem o telefone de um dado nome.
    Resulta o telefone do contato cujo nome foi fornecido.
    @return o telefone do contato desejado.
    @throws Exception se não for fornecido um nome, ou se o nome fornecido
                      não parecer ser um nome correto, ou se a agenda
                      estiver vazia, ou ainda se a agenda não contiver um
                      contato com o nome fornecido.
    */
    public String getTelefone (String nom)
                               throws Exception
    {
	Agenda.valideNome (nom);

        if (this.qtdContatos == 0)
            throw new Exception ("Agenda vazia");

        int posicao = this.ondeEsta (nom);

        if (posicao < 0)
            throw new Exception ("Nome inexistente");

        posicao--;

        return this.telefone [posicao];
    }

    /**
    Constroi uma cópia da instância da classe AgendaConsultavel dada.
    Para tanto, deve ser fornecida uma instancia da classe AgendaConsultavel
    para ser utilizada como modelo para a construção da nova instância
    criada.
    @param modelo a instância da classe AgendaConsultavel a ser usada como
                  modelo.
    @throws Exception se o modelo for null.
    */
    public AgendaConsultavel (AgendaConsultavel modelo) throws Exception
    {
        super (modelo);
    }
}