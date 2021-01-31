package dominio;

public class PlanCursoEvaluacion extends PlanCurso {
    private String unidades;
    private String fechas;
    private String criterio;
    private String instrumento;
    private int porcentaje;

    public PlanCursoEvaluacion() { }

    public void setUnidades(String unidades) { this.unidades = unidades; }

    public String getUnidades() { return unidades; }

    public void setFechas(String fechas) { this.fechas = fechas; }

    public String getFechas() { return fechas; }

    public void setCriterio(String criterio) { this.criterio = criterio; }

    public String getCriterio() { return criterio; }

    public void setInstrumento(String instrumento) { this.instrumento = instrumento; }

    public String getInstrumento() { return instrumento; }

    public void setPorcentaje(int porcentaje) { this.porcentaje = porcentaje; }

    public int getPorcentaje() { return porcentaje; }
}
