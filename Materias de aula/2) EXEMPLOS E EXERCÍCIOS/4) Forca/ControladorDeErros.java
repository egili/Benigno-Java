public class ControladorDeErros implements Cloneable
{
    private int qtdMax, qtdErr=0;

    public ControladorDeErros (int qtdMax) throws Exception
    {
        // verifica se qtdMax fornecida não é positiva, lançando
        // uma exceção.
        // armazena qtdMax fornecida em this.qtdMax.
    }

    public void registreUmErro () throws Exception
    {
        // verifica se this.qtdErr ja é igual a this.qtdMax,
        // lançando excecao em caso positivo ou
        // incrementando this.qtdErr em caso negativo
    }

    public boolean isAtingidoMaximoDeErros  ()
    {
        // returna true se this.qtdErr for igual a this.qtdMax,
        // ou então false, caso contrario.
    }

    @Override
    public String toString ()
    {
        return this.qtdErr + " de " + this.qtdMax;
    }

    @Override
    public boolean equals (Object obj)
    {
        // verificar se this e obj possuem o mesmo conteúdo, retornando
        // true no caso afirmativo ou false no caso negativo
    }

    @Override
    public int hashCode ()
    {
        // calcular e retornar o hashcode de this
    }

    public ControladorDeErros (ControladorDeErros c) throws Exception // construtor de cópia
    {
        // copiar c.qtdMax e c.qtdErr, respectivamente em, this.qtdMax e this.qtdErr
    }

    @Override
    public Object clone ()
    {
        // returnar uma cópia de this
    }
}
