public enum Mes
{
    JANEIRO,
    FEVEREIRO,
    MARCO,
    ABRIL,
    MAIO, 
    JUNHO,
    JULHO,
    AGOSTO,
    SETEMBRO,
    OUTUBRO, 
    NOVEMBRO,
    DEZEMBRO;

    public String getDescricao()
    {
	String descricao=null;
        switch (this)
        {
            case JANEIRO:   descricao="Janeiro"; break;
            case FEVEREIRO: descricao="Fevereiro"; break;
            case MARCO:     descricao="Março"; break;
            case ABRIL:     descricao="Abril"; break;
            case MAIO:      descricao="Maio"; break;
            case JUNHO:     descricao="Junho"; break;
            case JULHO:     descricao="Julho"; break;
            case AGOSTO:    descricao="Agosto"; break;
            case SETEMBRO:  descricao="Setembro"; break;
            case OUTUBRO:   descricao="Outubro"; break;
            case NOVEMBRO:  descricao="Novembro"; break;
            case DEZEMBRO:  descricao="Dezembro"; break;
        }
        return descricao;
    }
}
