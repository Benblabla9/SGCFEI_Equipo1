package dominio;

public class PlanAcademiaAccionDetalle extends PlanAcademiaAccion {
    private String accion;
    private String semana;
    private String forma;

    public PlanAcademiaAccionDetalle() { }

    public void setAccion(String accion) { this.accion = accion; }

    public String getAccion() { return accion; }

    public void setSemana(String semana) { this.semana = semana; }

    public String getSemana() { return semana; }

    public void setForma(String forma) { this.forma = forma; }

    public String getForma() { return forma; }
}
