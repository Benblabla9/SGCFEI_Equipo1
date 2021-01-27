package dominio;

public class PlanAcademiaAccionDetalle extends PlanAcademiaAccion {
    private String accion;
    private String fecha;
    private String forma;

    public PlanAcademiaAccionDetalle() { }

    public void setAccion(String accion) { this.accion = accion; }

    public String getAccion() { return accion; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getFecha() { return fecha; }

    public void setForma(String forma) { this.forma = forma; }

    public String getForma() { return forma; }
}
