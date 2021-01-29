package accesoDatos.controladoresDAO;

import accesoDatos.interfacesDAO.IUsuarioDAO;
import accesoDatos.Conexion;
import dominio.Usuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAOImpl implements IUsuarioDAO {
    private final Conexion conexion;
    private Connection connection;
    private ResultSet resultado;
    private ResultSet resultSet;
    PreparedStatement preparedStatement;

    public UsuarioDAOImpl() { conexion = new Conexion(); }

    @Override
    public boolean agregarUsuario(Usuario usuario) {
        boolean registrar = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentenceUsuario = connection.prepareStatement("INSERT INTO Usuario(nombreUsuario, contrasenia, rol) VALUES(?,?,?)");
            sentenceUsuario.setString(1, usuario.getNombreUsuario());
            sentenceUsuario.setString(2, usuario.getContrasenia());
            sentenceUsuario.setString(3, usuario.getRol());
            sentenceUsuario.executeUpdate();
            registrar = true;
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return registrar;
    }

    @Override
    public boolean eliminarUsuario(int idUsuario) {
        boolean resultado = false;
        try{
            connection = conexion.getConnection();
            String queryEliminar = "DELETE FROM Usuario WHERE idUsuario = ?";
            PreparedStatement sentenceEliminar = connection.prepareStatement(queryEliminar);
            sentenceEliminar.setInt(1, idUsuario);
            sentenceEliminar.executeUpdate();
            resultado = true;
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean validarUsuario(String nombreUsuario) {
        boolean esValido = false;
        try{
            connection = conexion.getConnection();
            String queryUsuario = "SELECT nombreUsuario FROM Usuario WHERE nombreUsuario = ?";
            PreparedStatement sentenceUsuario = connection.prepareStatement(queryUsuario);
            sentenceUsuario.setString(1, nombreUsuario);
            resultado = sentenceUsuario.executeQuery();
            if(resultado.next()){
                esValido = true;
            }
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return esValido;
    }

    @Override
    public String rolUsuario(String nombreUsuario, String contrasenia) {
        String rol = "No se encontró el rol del usuario";
        try{
            connection = conexion.getConnection();
            String queryRol = "SELECT rol FROM Usuario WHERE nombreUsuario = ?";
            PreparedStatement sentenceRol = connection.prepareStatement(queryRol);
            sentenceRol.setString(1, nombreUsuario);
            resultado = sentenceRol.executeQuery();
            while(resultado.next()){
                rol = resultado.getString("rol");
            }
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return rol;
    }

    @Override
    public boolean buscarUsuario(String nombreUsuario, String contrasenia) {
        boolean esUsuario = false;
        try{
            connection = conexion.getConnection();
            String queryUsuario = "SELECT nombreUsuario FROM Usuario WHERE nombreUsuario = ? AND contrasenia = ?";
            PreparedStatement sentenceUsuario = connection.prepareStatement(queryUsuario);
            sentenceUsuario.setString(1, nombreUsuario);
            sentenceUsuario.setString(2, contrasenia);
            resultado = sentenceUsuario.executeQuery();
            while (resultado.next()){
                esUsuario = true;
            }
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return esUsuario;
    }

    @Override
    public int buscarIdUsuario(String nombreUsuario, String contrasenia) {
        int idUsuario = 0;
        try {
            connection = conexion.getConnection();
            String queryIdUsuario = "Select idUser FROM User where nombreUsuario = AND contrasenia = ? ";
            preparedStatement = connection.prepareStatement(queryIdUsuario);
            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, contrasenia);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idUsuario = resultSet.getInt("idUsuario");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return idUsuario;
    }
}
