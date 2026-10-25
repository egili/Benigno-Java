import java.lang.reflect.*;

public class Reflect
{
    public static void main(String[] args)
    {
        String s = "MALIGNO";
        String sub;
        
        // sub = s.substring(2,5);
        // System.out.println(sub);
        
        Class<?> classe = s.getClass();
                
        Class<?>[] tpsParmsForms = new Class<?> [2];
        tpsParmsForms[0]=int.class;
        tpsParmsForms[1]=int.class;
        
        Method metodo=null;
        try
        {
             metodo = classe.getMethod ("substring",tpsParmsForms);
        }
        catch (NoSuchMethodException erro)
        {}

        Object[] parmsReais = new Object [2];
        parmsReais[0]=2;
        parmsReais[1]=5;
        
        sub=null;
        try
        {
            sub = (String)metodo.invoke(s,parmsReais);
        }
        catch (InvocationTargetException erro)
        {}
        catch (IllegalAccessException erro)
        {}
        
        System.out.println(sub);
    }
}
