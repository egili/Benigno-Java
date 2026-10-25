import java.util.regex.*;

/**
A interface Agenda especifica o comportamento padrão de uma agenda telefonica.
Instâncias de classes  que implementam esta interface devem conter métodos
para incluir e excluir.
Apesar de não especificar, explicitamente, os métodos canônicos, é óbvio que
esses métodos devem ser implementados nas classes que implementam esta
interface.
@author André Luís dos Reis Gomes de Carvalho.
@since 2000.
*/
public interface Agenda extends Cloneable
{
    class Contato
    {
        /**
        Expressão regular que define a forma de um nome válido.
        */
        public static final String  regExNom  =
        "^[A-Z][a-z]*(?: (?:[A-Z]|[a-z])[a-z]*)*$";

        /**
        Padrão que define como é um nome válido.
        */
        public static final Pattern padraoNom = Pattern.compile (regExNom);

        /**
        Expressão regular que define a forma de um telefone nacional.
        */
        public static final String  regExTel  =
        "^(?:\\([0-9]{2}\\) )?9?[0-9]{4}-[0-9]{4}$";

        /**
        Padrão que define como é um telefone nacional válido.
        */
        public static final Pattern padraoTel = Pattern.compile (regExTel);

        /**
        Valida o nome de um contato.
        Verifica se o nome fornecido como parâmetro é um nome válido,
        lançando exceções, caso incorretudes sejam detectadas.
        @param nom o nome a ser validado.
        @throws Exception se não for fornecido um nome, ou se o nome fornecido
                          não parecer ser um nome correto.
        */
        public static void valideNome (String nom) throws Exception
        {
            if (nom==null)
                throw new Exception ("Nome nao fornecido");

            if (!Agenda.Contato.padraoNom.matcher(nom).matches())
                throw new Exception ("Nome invalido");
        }

        /**
        Valida o telefone de um contato.
        Verifica se o número de telefone fornecido é um número
        nacional válido, lançando exceções, caso incorretudes sejam
        detectadas.
        @param tel o telefone a ser validado.
        @throws Exception se não for fornecido um telefone, ou se o telefone
                          fornecido não parecer ser um telefone correto.
        */
        public static void valideTelefone (String tel) throws Exception
        {
            if (tel==null)
                throw new Exception ("Telefone nao fornecido");

            if (!Agenda.Contato.padraoTel.matcher(tel).matches())
                throw new Exception ("Telefone invalido");
        }

        /**
        Atributo que representa o nome de um contato.
        */
        protected String nome;

        /**
        Atributo que representa o telefone de um contato.
        */
        protected String telefone;

        /**
        Constroi uma nova instância da classe interna e protegida
        Contato.
        Para tanto, devem ser fornecidos dois Strings que serão
        utilizados, respectivamente, como nome e telefone da
        instância recém criada.
        @param nome o String a ser utilizado como nome.
        @param telefone o String a ser utilizado como telefone.
        @throws Exception se não for fornecido um nome, ou se o nome fornecido
                          não parecer ser um nome correto, ou se não for fornecido
                          um telefone, ou se o telefone fornecido não parecer ser
                          um telefone correto.
        */
        public Contato (String nom, String tel) throws Exception
        {
            this.setNome     (nom);
            this.setTelefone (tel);
        }

        /**
        Obtem o nome de um contato.
        Permite que seja recuperado o nome do contato chamante
        do método.
        @return o nome do contato.
        */
        public String getNome ()
        {
            return this.nome;
        }

        /**
        Obtem o telefone de um contato.
        Permite que seja recuperado o telefone do contato chamante
        do método.
        @return o telefone do contato.
        */
        public String getTelefone ()
        {
            return this.telefone;
        }

        /**
        Ajusta o nome de um contato.
        Permite a modificação do nome do contato chamante
        do método.
        @param nom o novo nome do contato.
        @throws Exception se não for fornecido um nome, ou se o nome fornecido
                          não parecer ser um nome correto.
        */
        public void setNome (String nom) throws Exception
        {
            Agenda.Contato.valideNome (nom);
            this.nome = nom;
        }

        /**
        Ajusta o telefone de um contato.
        Permite a modificação do telefone do contato chamante
        do método.
        @param tel o novo telefone do contato.
        @throws Exception se não for fornecido um telefone, ou se o telefone
                          fornecido não parecer ser um telefone correto.
        */
        public void setTelefone (String tel) throws Exception
        {
            Agenda.Contato.valideTelefone (tel);
            this.telefone = tel;
        }

        /**
        Converte uma agenda em um String.
        Produz e resulta uma instância da classe String que
        representa a instância à qual este método for aplicado.
        @return o String que representa a agenda chamante do
                método.
        */
        public String toString ()
        {
            return this.nome     + " (" +
                   this.telefone + ")";
        }

        /**
        Verifica a igualdade entre duas agendas.
        Verifica se o Object fornecido como parâmetro representa
        uma agenda com conteúdo idêntico ao da instância à qual
        este método for aplicado, resultando true em caso afirmativo,
        ou false, caso contrário.
        @return true, caso o Object fornecido ao método e a instância
                chamante do método representarem frações numericamente
                equivalentes, ou false, caso contrário.
        */
        public boolean equals (Object obj)
        {
            if (obj==null)
                return false;

            if (this.getClass()!=obj.getClass())
                return false;

            Contato contato = (Contato)obj;

            if (!this.nome.equals(contato.nome))
                return false;

            if (!this.telefone.equals(contato.telefone))
                return false;

            return true;
        }

        /**
        Calcula o código de espalhamento (ou código de hash) de uma
        agenda.
        Calcula e resulta o código de espalhamento (ou código de
        hash, ou ainda o hashcode) da agenda representada pela
        instância à qual o método for aplicado.
        @return o código de espalhamento da agenda chamante do método.
        */
        public int hashcode ()
        {
            int ret = 666;

            ret = 13*ret + this.nome.hashCode();
            ret = 13*ret + this.telefone.hashCode();

            return ret;
        }

        /**
        Constroi uma nova instância da classe Contato.
        Para tanto, deve ser fornecida uma agenda que servirá como
        modelo.
        @param modelo a instância que servirá como modelo.
        @throws Exception se o modelo for null.
        */
        public Contato (Contato modelo) throws Exception
        {
            if (modelo==null)
                throw new Exception ("Contato modelo nao fornecido");

            this.nome     = modelo.nome;
            this.telefone = modelo.telefone;
        }


        /**
        Produz uma cópia fiel de uma agenda.
        Produz e resulta uma cópia exata da agenda à qual o método for cado.
        @return a cópia da agenda chamante do método.
        */
        public Object clone ()
        {
            Contato ret = null;

            try
            {
                ret = new Contato (this);
            }
            catch (Exception e)
            {}

            return ret;
        }
    }

    /**
    Estabelece que agendas são capazes de incorporar um novo contato.
    Estabelece que, dados um nome e um telefone, agendas às quais este
    método for aplicado devem passar a armazenar um novo contato com
    os dados fornecidos.
    @param nom o nome do novo contato.
    @param tel o telefone do novo contato.
    @throws Exception se não for fornecido um nome, ou se o nome fornecido
                      não parecer ser um nome correto, ou se não for fornecido
                      um telefone, ou se o telefone fornecido não parecer ser
                      um telefone correto, ou se a agenda estiver cheia, ou
                      ainda se o nome fornecido já estiver cadastrado.
    */
    void registreContato (String nom, String tel) throws Exception;

    /**
    Estabelece que agendas são capazes de descartar um contato.
    Estabelece que, dado um nome, agendas às quais este método for aplicado
    devem deixar de armazenar o contato com o nome fornecido.
    @param nom o nome do contato a ser descartado.
    @throws Exception se não for fornecido um nome, ou se o nome fornecido
                      não parecer ser um nome correto, ou se a agenda
                      estiver vazia, ou ainda se a agenda não contiver um
                      contato com o nome fornecido.
    */
    void descarteContato (String nom) throws Exception;
}
