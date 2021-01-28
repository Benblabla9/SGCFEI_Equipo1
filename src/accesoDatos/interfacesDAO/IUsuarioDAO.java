package accesoDatos.interfacesDAO;

import dominio.Usuario;

public interface IUsuarioDAO {
    boolean agregarUsuario(Usuario usuario);
    boolean validarUsuario(String nombreUsuario);
    String rolUsuario(String nombreUsuario, String contrasenia);
    boolean buscarUsuario(String nombreUsuario, String contrasenia);
}
