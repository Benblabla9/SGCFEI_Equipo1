package dominio;

public class PlanAcademiaAccion extends PlanAcademia {
    private String objetivoParticular;
    private String meta;

    public PlanAcademiaAccion() { }

    public void setObjetivoParticular(String objetivoParticular) { this.objetivoParticular = objetivoParticular; }

    public String getObjetivoParticular() { return objetivoParticular; }

    public void setMeta(String meta) { this.meta = meta; }

    public String getMeta() { return meta; }
}
