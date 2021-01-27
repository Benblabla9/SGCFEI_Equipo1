package dominio;

public class Academico extends Usuario {
    private String numeroPersonal;
    private String nombre;
    private String correo;
    private String apellidos;
    private int numeroCelular;

    public Academico() { }

    public String getNumeroPersonal() { return numeroPersonal; }

    public void setNumeroPersonal(String numeroPersonal) { this.numeroPersonal = numeroPersonal; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }

    public void setCorreo() { this.correo = correo; }

    public String getApellidos() { return apellidos; }

    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public int getNumeroCelular() { return numeroCelular; }

    public void setNumeroCelular(int numeroCelular) { this.numeroCelular = numeroCelular; }
}


