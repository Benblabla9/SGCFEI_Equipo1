package dominio;

public class PlanAcademiaAccion extends PlanAcademia {
    private String objetivo;
    private String meta;

    public PlanAcademiaAccion() { }

    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getObjetivo() { return objetivo; }

    public void setMeta(String meta) { this.meta = meta; }

    public String getMeta() { return meta; }
}
