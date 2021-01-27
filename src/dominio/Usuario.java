package dominio;

public class Usuario {
    private String nombreUsuario;
    private String contrasenia;
    private String rol;

    public Usuario() { }

    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }

    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public String getContrasenia() { return contrasenia; }

    public void setRol(String rol) { this.rol = rol; }

    public String getRol() { return rol; }
}

