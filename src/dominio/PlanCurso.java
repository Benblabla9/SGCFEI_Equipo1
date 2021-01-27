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

    public void setNrc(int nrc) { this.nrc = nrc; }

    public int getNrc() { return nrc; }

    public void setExperienciaEducativa(String experienciaEducativa) { this.experienciaEducativa = experienciaEducativa; }

    public String getExperienciaEducativa() { return experienciaEducativa; }

    public void setProgramaEducativo(String programaEducativo) { this.programaEducativo = programaEducativo; }

    public String getProgramaEducativo() { return programaEducativo; }

    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getObjetivo() { return objetivo; }
}
