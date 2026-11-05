public interface PadraoDeFila <X>
{
    void guardeItem (X item) throws Exception;

    X getItem () throws Exception;

    void removaItem () throws Exception;
}
