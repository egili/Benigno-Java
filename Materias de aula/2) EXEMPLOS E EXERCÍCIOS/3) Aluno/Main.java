// programa da anta
public class Main
{
    public static void main (String[] args)
    {
        try
        {
            Data d = new Data ((byte)19,(byte)1,(short)1966);
            Aluno a = new Aluno (...,60,"André",d,...);

            d.setDia(30);
            // se em setNascimento em Aluno foi feita a
            // implementação sem noção, está quebrado o
            // encapsulamento
        }
        catch (Exception erro)
        {
            System.err.println(erro.getMessage());
        }     
    }
}