package dominio;

public class PlanCurso {
    private String bloque;
    private String seccion;
    private int nrc;
    private String experienciaEducativa;
    private String programaEducativo;
    private String objetivo;

    public PlanCurso() { }

    public void setBloque(String bloque) { this.bloque = bloque; }

    public String getBloque() { return bloque; }

    public void setSeccion(String seccion) { this.seccion = seccion; }

    public String getSeccion() { return seccion; }
}
