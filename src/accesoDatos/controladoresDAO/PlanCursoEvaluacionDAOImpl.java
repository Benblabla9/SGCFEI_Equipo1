package accesoDatos.controladoresDAO;

import accesoDatos.Conexion;
import accesoDatos.interfacesDAO.IPlanCursoEvaluacionDAO;
import dominio.PlanCursoEvaluacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanCursoEvaluacionDAOImpl implements IPlanCursoEvaluacionDAO {
    private final Conexion conexion;
    private Connection connection;
    private ResultSet resultado;
    private PreparedStatement preparedStatement;

    public PlanCursoEvaluacionDAOImpl() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarPlanCursoEvaluacion(PlanCursoEvaluacion planCursoEvaluacion, int nrc) {
        boolean agregar = false;
        try {
            connection = conexion.getConnection();
            PreparedStatement sentencePlanCursoEvaluacion = connection.prepareStatement("INSERT INTO PlanCursoEvaluacion(unidades, fechas, criterio, nrc, porcentaje, instrumento) VALUES(?,?,?,?,?,?)");
            sentencePlanCursoEvaluacion.setString(1, planCursoEvaluacion.getUnidades());
            sentencePlanCursoEvaluacion.setString(2, planCursoEvaluacion.getFechas());
            sentencePlanCursoEvaluacion.setString(3, planCursoEvaluacion.getCriterio());
            sentencePlanCursoEvaluacion.setInt(4, nrc);
            sentencePlanCursoEvaluacion.setInt(5, planCursoEvaluacion.getPorcentaje());
            sentencePlanCursoEvaluacion.setString(6, planCursoEvaluacion.getInstrumento());
            sentencePlanCursoEvaluacion.executeUpdate();
            agregar = true;
        } catch (SQLException ex) {
            Logger.getLogger(PlanCursoEvaluacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return agregar;
    }

    @Override
    public boolean eliminarPlanCursoEvaluacion(int nrc) {
        boolean resultado = false;
        try {
            connection = conexion.getConnection();
            String queryEliminar = "DELETE FROM planCursoEvaluacion WHERE nrc = ?";
            PreparedStatement sentenceEliminar = connection.prepareStatement(queryEliminar);
            sentenceEliminar.setInt(1, nrc);
            sentenceEliminar.executeUpdate();
            resultado = true;
        } catch (SQLException ex) {
            Logger.getLogger(PlanCursoEvaluacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean modificarPlanCursoEvaluacion(PlanCursoEvaluacion planCursoEvaluacion, int nrc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validarPlanCursoEvaluacion(int nrc) {

        boolean esValido = false;
        try {
            connection = conexion.getConnection();
            String queryPlanCursoEvaluacion = "SELECT nrc FROM PlanCursoEvaluacion WHERE nrc = ?";
            PreparedStatement sentencePlanCursoEvaluacion = connection.prepareStatement(queryPlanCursoEvaluacion);
            sentencePlanCursoEvaluacion.setInt(1, nrc);
            resultado = sentencePlanCursoEvaluacion.executeQuery();
            if (resultado.next()) {
                esValido = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanCursoEvaluacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return esValido;
    }

    @Override
    public PlanCursoEvaluacion getPlanCursoEvaluacion(int nrc) {
        PlanCursoEvaluacion planCursoEvaluacion = new PlanCursoEvaluacion();
        try {
            connection = conexion.getConnection();
            String queryGetPlanCursoEvaluacion = "SELECT idPlanCursoEvaluacion, unidades, fechas, criterio, porcentaje,instrumento FROM PlanCursoEvaluacion" +
                    "bloque, seccion,ExperienciaEducstiva, ProgramaEducativo,Objetivo WHERE nrc = ?";
            preparedStatement = connection.prepareStatement(queryGetPlanCursoEvaluacion);
            preparedStatement.setInt(1, nrc);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                planCursoEvaluacion.setUnidades(resultado.getString("unidades"));
                planCursoEvaluacion.setFechas(resultado.getString("fechas"));
                planCursoEvaluacion.setCriterio(resultado.getString("criterio"));
                planCursoEvaluacion.setPorcentaje(resultado.getInt("Porcentaje"));
                planCursoEvaluacion.setInstrumento(resultado.getString("Instrumento"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanCursoEvaluacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return planCursoEvaluacion;
    }

    @Override
    public List<PlanCursoEvaluacion> getPlanesCursoEvaluacion() {
        List<PlanCursoEvaluacion> listaPlanCursoEvaluacion = new ArrayList<>();
        try {
            connection = conexion.getConnection();
            String queryPlanCursoEvaluacion = "SELECT unidades, fechas, criterio, porcentaje,instrumento FROM planCursoEvaluacion";
            PreparedStatement sentenciaPlanCursoEvaluacion = connection.prepareStatement(queryPlanCursoEvaluacion);
            resultado = sentenciaPlanCursoEvaluacion.executeQuery();
            while (resultado.next()) {
                PlanCursoEvaluacion planCursoEvaluacion = new PlanCursoEvaluacion();
                planCursoEvaluacion.setUnidades(resultado.getString("Unidades"));
                planCursoEvaluacion.setFechas(resultado.getString("Fechas"));
                planCursoEvaluacion.setCriterio(resultado.getString("criterio"));
                planCursoEvaluacion.setPorcentaje(resultado.getInt("procentaje"));
                planCursoEvaluacion.setInstrumento(resultado.getString("instrumento"));

                listaPlanCursoEvaluacion.add(planCursoEvaluacion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanCursoEvaluacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return listaPlanCursoEvaluacion;
    }

    @Override
    public int buscarNrc(int nrc) {
        int nrc1 = 0;
        try {
            connection = conexion.getConnection();
            String queryNrc = "Select nrc FROM PlanCurso where nrc = ? ";
            preparedStatement = connection.prepareStatement(queryNrc);
            preparedStatement.setInt(1, nrc1);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                nrc = resultado.getInt("nrc");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanCursoEvaluacionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return nrc;
    }
}