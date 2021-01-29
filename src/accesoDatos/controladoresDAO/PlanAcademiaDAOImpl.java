package accesoDatos.controladoresDAO;

import accesoDatos.Conexion;
import accesoDatos.interfacesDAO.IPlanAcademiaDAO;
import dominio.PlanAcademia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanAcademiaDAOImpl implements IPlanAcademiaDAO {
    private final Conexion conexion;
    private Connection connection;
    private ResultSet resultado;
    private PreparedStatement preparedStatement;

    public PlanAcademiaDAOImpl () { conexion = new Conexion(); }

    @Override
    public boolean agregarPlanAcademia(PlanAcademia planAcademia) {
        boolean registrar = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentencePlanAcademia = connection.prepareStatement("INSERT INTO PlanAcademia" +
                    "(numeroPlan, fecha, peridoo, objetivo, programaEducativo, nombreAcademia, miembros)" +
                    " VALUES(?,?,?.?,?,?,?)");
            sentencePlanAcademia.setInt(1, planAcademia.getNumeroPlan());
            sentencePlanAcademia.setString(2, planAcademia.getFecha());
            sentencePlanAcademia.setString(3, planAcademia.getPeriodo());
            sentencePlanAcademia.setString(4,planAcademia.getObjetivo());
            sentencePlanAcademia.setString(5, planAcademia.getProgramaEducativo());
            sentencePlanAcademia.setString(6, planAcademia.getNombreAcademia());
            sentencePlanAcademia.setString(7, planAcademia.getMiembros());
            sentencePlanAcademia.executeUpdate();
            registrar = true;
        }catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return registrar;
    }

    @Override
    public boolean eliminarPlanAcademia(int numeroPlan) {
        boolean resultado = false;
        try{
            connection = conexion.getConnection();
            String queryEliminar = "DELETE FROM PlanAcademia WHERE numeroPlan = ?";
            PreparedStatement sentenceEliminar = connection.prepareStatement(queryEliminar);
            sentenceEliminar.setInt(1, numeroPlan);
            sentenceEliminar.executeUpdate();
            resultado = true;
        }catch(SQLException ex){
            Logger.getLogger(PlanAcademiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean validarPlanAcademia(int numeroPlan) {
        boolean esValido = false;
        try{
            connection = conexion.getConnection();
            String queryPlanAcademia = "SELECT numeroPlan FROM PlanAcademia WHERE numeroPlan = ?";
            PreparedStatement sentencePlanAcademia = connection.prepareStatement(queryPlanAcademia);
            sentencePlanAcademia.setInt(1, numeroPlan);
            resultado = sentencePlanAcademia.executeQuery();
            if(resultado.next()){
                esValido = true;
            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return esValido;
    }

    @Override
    public PlanAcademia getPlanAcademia(int numeroPlan) {
        PlanAcademia planAcademia = new PlanAcademia();
        try {
            connection = conexion.getConnection();
            String queryGetPlanAcademia = "SELECT numeroPlan, fecha, periodo, objetivo, programaEducativo, nombreAcademia" +
                    "FROM PlanAcademia WHERE numeroPlan = ?";
            preparedStatement = connection.prepareStatement(queryGetPlanAcademia);
            preparedStatement.setInt(1, numeroPlan);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                planAcademia.setNumeroPlan(resultado.getInt("numeroPlan"));
                planAcademia.setFecha(resultado.getString("fecha"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanCursoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return planAcademia;
    }

    @Override
    public List<PlanAcademia> getPlanesAcademias() {
        List<PlanAcademia> listaPlanAcademia = new ArrayList<>();
        try{
            connection = conexion.getConnection();
            String queryPlanAcademia = "SELECT numeroPlan, fecha, periodo, programaEducativo, nombreAcademia" +
                    "FROM PlanAcademia";
            PreparedStatement sentenciaPlanAcademia = connection.prepareStatement(queryPlanAcademia);
            resultado = sentenciaPlanAcademia.executeQuery();
            while(resultado.next()){
                PlanAcademia planAcademia = new PlanAcademia();
                planAcademia.setNumeroPlan(resultado.getInt("numeroPlan"));
                planAcademia.setFecha(resultado.getString("fecha"));
                planAcademia.setPeriodo(resultado.getString("periodo"));
                planAcademia.setProgramaEducativo(resultado.getString("programaEducativo"));
                planAcademia.setNombreAcademia(resultado.getString("nombreAcademia"));
                listaPlanAcademia.add(planAcademia);
            }
        }catch (SQLException ex){
            Logger.getLogger(AcademicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return listaPlanAcademia;
    }

    @Override
    public int buscarNumeroPlan(String periodo, String programaEducativo, String nombreAcademia) {
        int numeroPlan = 0;
        try {
            connection = conexion.getConnection();
            String queryNumeroPlan = "Select numeroPlan FROM PlanAcademia where periodo = ? AND programaEducativo = ? " +
                    "AND nombreAcademia = ? ";
            preparedStatement = connection.prepareStatement(queryNumeroPlan);
            preparedStatement.setString(1, periodo);
            preparedStatement.setString(2, programaEducativo);
            preparedStatement.setString(3, nombreAcademia);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                numeroPlan = resultado.getInt("numeroPlan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanAcademiaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return numeroPlan;
    }
}