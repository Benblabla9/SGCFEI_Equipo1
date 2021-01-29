package accesoDatos.controladoresDAO;

import accesoDatos.Conexion;
import accesoDatos.interfacesDAO.IPlanAcademiaAccionDAO;
import dominio.PlanAcademiaAccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanAcademiaAccionDAOImpl implements IPlanAcademiaAccionDAO {
    private final Conexion conexion;
    private Connection connection;
    private ResultSet resultado;
    private PreparedStatement preparedStatement;

    public PlanAcademiaAccionDAOImpl() { conexion = new Conexion(); }

    @Override
    public boolean agregarPlanAcademiaAccion(PlanAcademiaAccion planAcademiaAccion, int numeroPlan) {
        boolean agregar = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentencePlanAcademiaAccion = connection.prepareStatement("INSERT INTO PlanAcademiaAccion(objetivoParticular, meta, numeroPersonal) VALUES(?,?,?)");
            sentencePlanAcademiaAccion.setString(1, planAcademiaAccion.getObjetivoParticular());
            sentencePlanAcademiaAccion.setString(2, planAcademiaAccion.getMeta());
            sentencePlanAcademiaAccion.setInt(3, numeroPlan);
            sentencePlanAcademiaAccion.executeUpdate();
            agregar = true;
        }catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaAccionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return agregar;
    }

    @Override
    public boolean eliminarPlanAcademiaAccion(int numeroPlan) {
        boolean resultado = false;
        try{
            connection = conexion.getConnection();
            String queryEliminar = "DELETE FROM PlanAcademiaAccion WHERE numeroPlan = ?";
            PreparedStatement sentenceEliminar = connection.prepareStatement(queryEliminar);
            sentenceEliminar.setInt(1, numeroPlan);
            sentenceEliminar.executeUpdate();
            resultado = true;
        }catch(SQLException ex){
            Logger.getLogger(PlanAcademiaAccionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean modificarPlanAcademiaAccion(PlanAcademiaAccion planAcademiaAccion, int numeroPlan) {
        boolean modificado = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentencePlanAcademiaAccion = connection.prepareStatement("UPDATE PlanAcademiaAccion SET " +
                    "objetivoParticular = ?, meta = ? WHERE numeroPlan = ?");
            sentencePlanAcademiaAccion.setString(1, planAcademiaAccion.getObjetivoParticular());
            sentencePlanAcademiaAccion.setString(2, planAcademiaAccion.getMeta());
            sentencePlanAcademiaAccion.setInt(3, numeroPlan);
            sentencePlanAcademiaAccion.executeUpdate();
            modificado = true;
        }catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaAccionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return modificado;
    }

    @Override
    public int buscarIdPlanAcademiaAccion(String objetivoParticular, String meta, int numeroPlan) {
        int idPlanAcademiaAccion = 0;
        try {
            connection = conexion.getConnection();
            String queryIdPlanAcademiaAccion = "Select idPlanAcademiaAccion FROM PlanAcademiaAccion where " +
                    "objetivoParticular = ?, meta = ?, numeroPlan = ? ";
            preparedStatement = connection.prepareStatement(queryIdPlanAcademiaAccion);
            preparedStatement.setString(1, objetivoParticular);
            preparedStatement.setString(2, meta);
            preparedStatement.setInt(3, numeroPlan);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                idPlanAcademiaAccion = resultado.getInt("idPlanAcademiaAccion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaAccionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return idPlanAcademiaAccion;
    }
}
