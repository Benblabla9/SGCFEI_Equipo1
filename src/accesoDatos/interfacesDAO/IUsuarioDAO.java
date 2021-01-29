package accesoDatos.interfacesDAO;

import dominio.Usuario;

import java.util.List;

public interface IUsuarioDAO {
    boolean agregarUsuario(Usuario usuario);
    boolean eliminarUsuario(int idUsuario);
    boolean validarUsuario(String nombreUsuario);
    String rolUsuario(String nombreUsuario, String contrasenia);
    boolean buscarUsuario(String nombreUsuario, String contrasenia);
    int buscarIdUsuario(String nombreUsuario, String contrasenia);
    List<Usuario> getUsuarios();
}
