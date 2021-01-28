package accesoDatos.controladoresDAO;

import accesoDatos.Conexion;
import accesoDatos.interfacesDAO.IAcademicoDAO;
import dominio.Academico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcademicoDAOImpl implements IAcademicoDAO {
    private final Conexion conexion;
    private Connection connection;
    private ResultSet resultado;
    private PreparedStatement preparedStatement;

    public AcademicoDAOImpl() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarAcademico(Academico academico) {
        boolean agregar = false;
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        int idUsuario = usuarioDAO.buscarIdUsuario(academico.getNombreUsuario(), academico.getContrasenia());
        try{
            connection = conexion.getConnection();
            PreparedStatement sentenceAcademico = connection.prepareStatement("INSERT INTO Academico(numeroPersonal, nombre, correo, apellidos, numeroCelular, idUsuario) VALUES(?,?,?,?,?,?)");
            sentenceAcademico.setString(1, academico.getNumeroPersonal());
            sentenceAcademico.setString(2, academico.getNombre());
            sentenceAcademico.setString(3, academico.getApellidos());
            sentenceAcademico.setInt(4, academico.getNumeroCelular());
            sentenceAcademico.setInt(5, idUsuario);
            sentenceAcademico.executeUpdate();
            agregar = true;
        }catch (SQLException ex) {
            Logger.getLogger(AcademicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return agregar;
    }

    @Override
    public boolean validarAcademico(String numeroPersonal) {
        boolean esValido = false;
        try{
            connection = conexion.getConnection();
            String queryAcademico = "SELECT numeroPersonal FROM Academico WHERE numeroPersonal = ?";
            PreparedStatement sentenceAcademico = connection.prepareStatement(queryAcademico);
            sentenceAcademico.setString(1, numeroPersonal);
            resultado = sentenceAcademico.executeQuery();
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
    public boolean eliminarAcademico(String numeroPersonal, String nombre, String correo) {
        boolean resultado = false;
        try{
            connection = conexion.getConnection();
            String queryEliminar = "DELETE FROM Academico WHERE numeroPersonal = ? AND nombre = ? AND correo = ?";
            PreparedStatement sentenceEliminar = connection.prepareStatement(queryEliminar);
            sentenceEliminar.setString(1, numeroPersonal);
            sentenceEliminar.setString(2, nombre);
            sentenceEliminar.setString(3, correo);
            sentenceEliminar.executeUpdate();
            resultado = true;
        }catch(SQLException ex){
            Logger.getLogger(AcademicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                conexion.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean modificarAcademico(Academico academico) {
        boolean modificado = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentenceAcademico = connection.prepareStatement("UPDATE Academico INNER JOIN Usuario ON Academico.idUsuario = User.idUsuario SET " +
                    "numeroPersonal = ?, nombre = ?, correo = ?, apellidos = ?, numeroCelular = ? WHERE Academido.idUsuario = Usuario.idUsuario");
            sentenceAcademico.setString(1, academico.getNumeroPersonal());
            sentenceAcademico.setString(2, academico.getNombre());
            sentenceAcademico.setString(3, academico.getCorreo());
            sentenceAcademico.setString(4, academico.getApellidos());
            sentenceAcademico.setInt(5, academico.getNumeroCelular());
            sentenceAcademico.executeUpdate();
            modificado = true;
        }catch (SQLException ex) {
            Logger.getLogger(AcademicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return modificado;
    }

    @Override
    public Academico getAcademico(String numeroPersonal){
        Academico academico = new Academico();
        try {
            connection = conexion.getConnection();
            String queryGetAcademico = "SELECT numeroPersonal, nombre, correo, apellidos, numeroCelular FROM Academico" +
                    "Usuario, nombreUsuario, contrasenia, rol WHERE Academico.idUsuario = Usuario.idUsuario AND Academico.numeroPersonal = ?";
            preparedStatement = connection.prepareStatement(queryGetAcademico);
            preparedStatement.setString(1, numeroPersonal);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                academico.setNumeroPersonal(resultado.getString("numeroPersonal"));
                academico.setNombre(resultado.getString("nombre"));
                academico.setCorreo(resultado.getString("correo"));
                academico.setApellidos(resultado.getString("apellidos"));
                academico.setNumeroCelular(resultado.getInt("numeroCelular"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(AcademicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return academico;
    }

    @Override
    public List<Academico> getAcademicos(){
        List<Academico> listaAcademicos = new ArrayList<>();
        try{
            connection = conexion.getConnection();
            String queryAcademico = "SELECT numeroPersonal, nombre, correo FROM Academico";
            PreparedStatement sentenciaAcademico = connection.prepareStatement(queryAcademico);
            resultado = sentenciaAcademico.executeQuery();
            while(resultado.next()){
                Academico academico = new Academico();
                academico.setNumeroPersonal(resultado.getString("numeroPersonal"));
                academico.setNombre(resultado.getString("nombre"));
                academico.setCorreo(resultado.getString("correo"));
                listaAcademicos.add(academico);
            }
        }catch (SQLException ex){
            Logger.getLogger(AcademicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return listaAcademicos;
    }
}
