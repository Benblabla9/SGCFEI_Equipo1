package dominio;

public class PlanCursoEvaluación {
    private String unidades;
    private String fechas;
    private String criterio;

    public PlanCursoEvaluación() { }

    public void setUnidades(String unidades) { this.unidades = unidades; }

    public String getUnidades() { return unidades; }

    public void setFechas(String fechas) { this.fechas = fechas; }

    public String getFechas() { return fechas; }

    public void setCriterio(String criterio) { this.criterio = criterio; }

    public String getCriterio() { return criterio; }
}
