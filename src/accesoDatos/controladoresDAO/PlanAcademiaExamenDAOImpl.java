package accesoDatos.controladoresDAO;

import accesoDatos.Conexion;
import accesoDatos.interfacesDAO.IPlanAcademiaExamenDAO;
import dominio.PlanAcademiaExamen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanAcademiaExamenDAOImpl implements IPlanAcademiaExamenDAO {
    private final Conexion conexion;
    private Connection connection;
    private ResultSet resultado;
    private PreparedStatement preparedStatement;

    public PlanAcademiaExamenDAOImpl() { conexion = new Conexion(); }

    @Override
    public boolean agregarPlanAcademiaExamen(PlanAcademiaExamen planAcademiaExamen, int numeroPlan) {
        boolean agregar = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentencePlanAcademiaExamen = connection.prepareStatement("INSERT INTO PlanAcademiaExamen(experienciaEducativa, " +
                    "temasParcial1, temasParcial2, numeroPlan) VALUES(?,?,?,?)");
            sentencePlanAcademiaExamen.setString(1, planAcademiaExamen.getExperienciaEducativa());
            sentencePlanAcademiaExamen.setString(2, planAcademiaExamen.getTemasParcial1());
            sentencePlanAcademiaExamen.setString(3, planAcademiaExamen.getTemasParcial2());
            sentencePlanAcademiaExamen.setInt(4, numeroPlan);
            sentencePlanAcademiaExamen.executeUpdate();
            agregar = true;
        }catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaExamenDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return agregar;
    }

    @Override
    public boolean eliminarPlanAcademiaExamen(int numeroPlan) {
        boolean resultado = false;
        try{
            connection = conexion.getConnection();
            String queryEliminar = "DELETE FROM PlanAcademiaExamen WHERE numeroPlan = ?";
            PreparedStatement sentenceEliminar = connection.prepareStatement(queryEliminar);
            sentenceEliminar.setInt(1, numeroPlan);
            sentenceEliminar.executeUpdate();
            resultado = true;
        }catch(SQLException ex){
            Logger.getLogger(PlanAcademiaExamenDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean modificarPlanAcademiaExamen(PlanAcademiaExamen planAcademiaExamen, int numeroPlan) {
        boolean modificado = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentencePlanAcademiaExamen = connection.prepareStatement("UPDATE PlanAcademiaExamen SET " +
                    "experienciaEducativa = ?, temasParcial1 = ?, temasParcial2 = ? WHERE numeroPlan = ?");
            sentencePlanAcademiaExamen.setString(1, planAcademiaExamen.getExperienciaEducativa());
            sentencePlanAcademiaExamen.setString(2, planAcademiaExamen.getTemasParcial1());
            sentencePlanAcademiaExamen.setString(3, planAcademiaExamen.getTemasParcial2());
            sentencePlanAcademiaExamen.setInt(4, numeroPlan);
            sentencePlanAcademiaExamen.executeUpdate();
            modificado = true;
        }catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaExamenDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return modificado;
    }

    @Override
    public int buscarIdPlanAcademiaExamen(String experienciaEducativa, int numeroPlan) {
        int idPlanAcademiaExamen = 0;
        try {
            connection = conexion.getConnection();
            String queryIdPlanAcademiaExamen = "Select idPlanAcademiaExamen FROM PlanAcademiaExamen WHERE " +
                    "experienciaEducativa = ? AND numeroPlan = ? ";
            preparedStatement = connection.prepareStatement(queryIdPlanAcademiaExamen);
            preparedStatement.setString(1, experienciaEducativa);
            preparedStatement.setInt(2, numeroPlan);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                idPlanAcademiaExamen = resultado.getInt("idPlanAcademiaAccion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaAccionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return idPlanAcademiaExamen;
    }
}
