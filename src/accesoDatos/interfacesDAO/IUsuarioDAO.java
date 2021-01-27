package accesoDatos.interfacesDAO;

import dominio.Usuario;

public interface IUsuarioDAO {
    boolean agregarUsuario(Usuario usuario);
    boolean validarUsuario(String nombreUsuario);
}
