package dominio;

public class PlanAcademia {
    private String fecha;
    private String periodo;
    private String objetivo;
    private String programaEducativo;
    private String nombreAcademia;
    private String miembros;

    public PlanAcademia() { }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getFecha() { return fecha; }

    public void setPeriodo(String periodo) { this.periodo = periodo; }

    public String getPeriodo() { return periodo; }

    public void setObjetivo() { this.objetivo = objetivo; }

    public String getObjetivo() { return objetivo; }

    public void setProgramaEducativo(String programaEducativo) { this.programaEducativo = programaEducativo; }

    public String getProgramaEducativo() { return programaEducativo; }

    public void setNombreAcademia(String nombreAcademia) { this.nombreAcademia = nombreAcademia; }

    public String getNombreAcademia() { return nombreAcademia; }

    public void setMiembros(String miembros) { this.miembros = miembros; }

    public String getMiembros() { return miembros; }
}
