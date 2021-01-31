package accesoDatos.controladoresDAO;

import accesoDatos.Conexion;
import accesoDatos.interfacesDAO.IPlanAcademiaAccionDetalleDAO;
import dominio.PlanAcademiaAccionDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanAcademiaAccionDetalleDAOImpl implements IPlanAcademiaAccionDetalleDAO {
    private final Conexion conexion;
    private Connection connection;
    private ResultSet resultado;
    private PreparedStatement preparedStatement;

    public PlanAcademiaAccionDetalleDAOImpl() { conexion = new Conexion(); }

    @Override
    public boolean agregarPlanAcademiaAccionDetalle(PlanAcademiaAccionDetalle planAcademiaAccionDetalle, int idPlanAcademiaAccion) {
        boolean agregar = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentencePlanAcademiaAccionDetalle = connection.prepareStatement("INSERT INTO PlanAcademiaAccionDetalle (accion, semana, forma, idPlanAcademiaAccion) VALUES(?,?,?,?)");
            sentencePlanAcademiaAccionDetalle.setString(1, planAcademiaAccionDetalle.getAccion());
            sentencePlanAcademiaAccionDetalle.setString(2, planAcademiaAccionDetalle.getSemana());
            sentencePlanAcademiaAccionDetalle.setString(3, planAcademiaAccionDetalle.getForma());
            sentencePlanAcademiaAccionDetalle.setInt(4, idPlanAcademiaAccion);
            sentencePlanAcademiaAccionDetalle.executeUpdate();
            agregar = true;
        }catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaAccionDetalleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return agregar;
    }

    @Override
    public boolean eliminarPlanAcademiaAccionDetalle(int idPlanAcademiaAccion) {
        boolean resultado = false;
        try{
            connection = conexion.getConnection();
            String queryEliminar = "DELETE FROM PlanAcademiaAccionDetalle WHERE idPlanAcademiaAccion = ?";
            PreparedStatement sentenceEliminar = connection.prepareStatement(queryEliminar);
            sentenceEliminar.setInt(1, idPlanAcademiaAccion);
            sentenceEliminar.executeUpdate();
            resultado = true;
        }catch(SQLException ex){
            Logger.getLogger(PlanAcademiaAccionDetalleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean modificarAcademico(PlanAcademiaAccionDetalle planAcademiaAccionDetalle, int idPlanAcademiaAccion) {
        boolean modificado = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentencePlanAcademiaAccionDetalle = connection.prepareStatement("UPDATE PlanAcademiaAccionDetalle SET " +
                    "accion = ?, semana = ?, forma = ? WHERE idPlanAcademiaAccion = ?");
            sentencePlanAcademiaAccionDetalle.setString(1, planAcademiaAccionDetalle.getAccion());
            sentencePlanAcademiaAccionDetalle.setString(2, planAcademiaAccionDetalle.getSemana());
            sentencePlanAcademiaAccionDetalle.setString(3, planAcademiaAccionDetalle.getForma());
            sentencePlanAcademiaAccionDetalle.setInt(4, idPlanAcademiaAccion);
            sentencePlanAcademiaAccionDetalle.executeUpdate();
            modificado = true;
        }catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaAccionDetalleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return modificado;
    }

    @Override
    public int buscarIdPlanAcademiaAccionDetalle(String accion, int idPlanAcademiaAccion) {
        int idPlanAcademiaAccionDetalle = 0;
        try {
            connection = conexion.getConnection();
            String queryIdPlanAcademiaAccionDetalle = "Select idPlanAcademiaAccionDetalle FROM PlanAcademiaAccionDetalle where " +
                    "accion = ? AND idPlanAcademiaAccion = ? ";
            preparedStatement = connection.prepareStatement(queryIdPlanAcademiaAccionDetalle);
            preparedStatement.setString(1, accion);
            preparedStatement.setInt(2, idPlanAcademiaAccion);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                idPlanAcademiaAccionDetalle = resultado.getInt("idPlanAcademiaAccionDetalle");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaAccionDetalleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return idPlanAcademiaAccionDetalle;
    }

}