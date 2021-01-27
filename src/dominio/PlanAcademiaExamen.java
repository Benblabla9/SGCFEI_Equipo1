package dominio;

public class PlanAcademiaExamen extends PlanAcademia {
    private String experienciaEducativa;
    private String temasParcial1;
    private String temasParcial2;

    public PlanAcademiaExamen() { }

    public void setExperienciaEducativa(String experienciaEducativa) { this.experienciaEducativa = experienciaEducativa; }

    public String getExperienciaEducativa() { return experienciaEducativa; }

    public void setTemasParcial1(String temasParcial1) { this.temasParcial1 = temasParcial1; }

    public String getTemasParcial1() { return temasParcial1; }

    public void setTemasParcial2(String temasParcial2) { this.temasParcial2 = temasParcial2; }

    public String getTemasParcial2() { return temasParcial2; }
}
