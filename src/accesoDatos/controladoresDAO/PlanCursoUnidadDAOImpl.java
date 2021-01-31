package accesoDatos.controladoresDAO;

import accesoDatos.Conexion;
import accesoDatos.interfacesDAO.IPlanCursoUnidadDAO;
import dominio.PlanCursoUnidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanCursoUnidadDAOImpl implements IPlanCursoUnidadDAO{
    private final Conexion conexion;
    private Connection connection;
    private ResultSet resultado;
    private PreparedStatement preparedStatement;

    public PlanCursoUnidadDAOImpl() { conexion = new Conexion(); }

    @Override
    public boolean agregarPlanCursoUnidad(PlanCursoUnidad planCursoUnidad, int nrc) {
        boolean agregar = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentencePlanCursoUnidad = connection.prepareStatement("INSERT INTO PlanCursoUnidad(unidad, tema, fechas, nrc) VALUES(?,?,?,?)");
            sentencePlanCursoUnidad.setString(1, planCursoUnidad.getUnidad());
            sentencePlanCursoUnidad.setString(2, planCursoUnidad.getTema());
            sentencePlanCursoUnidad.setString(3, planCursoUnidad.getFechas());
            sentencePlanCursoUnidad.setInt(4, nrc);
            sentencePlanCursoUnidad.executeUpdate();
            agregar = true;
        }catch (SQLException ex) {
            Logger.getLogger(PlanCursoUnidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return agregar;
    }

    @Override
    public boolean eliminarPlanCursoUnidad(int nrc) {
        boolean resultado = false;
        try{
            connection = conexion.getConnection();
            String queryEliminar = "DELETE FROM planCursoUnidad WHERE nrc = ?";
            PreparedStatement sentenceEliminar = connection.prepareStatement(queryEliminar);
            sentenceEliminar.setInt(1, nrc);
            sentenceEliminar.executeUpdate();
            resultado = true;
        }catch(SQLException ex){
            Logger.getLogger(PlanCursoUnidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean modificarPlanCursoUnidad(PlanCursoUnidad planCursoUnidad, int nrc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validarPlanCursoUnidad(int nrc) {

        boolean esValido = false;
        try{
            connection = conexion.getConnection();
            String queryPlanCursoUnidad = "SELECT nrc FROM PlanCursoUnidad WHERE nrc = ?";
            PreparedStatement sentencePlanCursoUnidad = connection.prepareStatement(queryPlanCursoUnidad);
            sentencePlanCursoUnidad.setInt(1, nrc);
            resultado = sentencePlanCursoUnidad.executeQuery();
            if(resultado.next()){
                esValido = true;
            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanCursoUnidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return esValido;
    }


    @Override
    public PlanCursoUnidad getPlanCursoUnidad(int nrc) {
        PlanCursoUnidad planCursoUnidad = new PlanCursoUnidad();
        try {
            connection = conexion.getConnection();
            String queryGetPlanCursoUnidad = "SELECT idPlanCursoUnidad, unidad,Tema, fechas, FROM PlanCursoUnidad" +
                    "bloque, seccion,ExperienciaEducstiva, ProgramaEducativo,Objetivo WHERE PlanCursoUnidad.nrc = planCurso.nrc";
            preparedStatement = connection.prepareStatement(queryGetPlanCursoUnidad);
            preparedStatement.setInt(1, nrc);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                planCursoUnidad.setUnidad(resultado.getString("unidad"));
                planCursoUnidad.setTema(resultado.getString("tema"));
                planCursoUnidad.setFechas(resultado.getString("fechas"));

            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanCursoUnidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return planCursoUnidad;
    }

    @Override
    public List<PlanCursoUnidad> getPlanesCursoUnidad() {
        List<PlanCursoUnidad> listaPlanCursoUnidad = new ArrayList<>();
        try{
            connection = conexion.getConnection();
            String queryPlanCursoUnidad = "SELECT unidad,Tema, fechas FROM planCursoEvaluacion";
            PreparedStatement sentenciaPlanCursoUnidad = connection.prepareStatement(queryPlanCursoUnidad);
            resultado = sentenciaPlanCursoUnidad.executeQuery();
            while(resultado.next()){
                PlanCursoUnidad planCursoUnidad = new PlanCursoUnidad();
                planCursoUnidad.setUnidad(resultado.getString("Unidad"));
                planCursoUnidad.setTema(resultado.getString("Tema"));
                planCursoUnidad.setFechas(resultado.getString("Fechas"));

                listaPlanCursoUnidad.add(planCursoUnidad);
            }
        }catch (SQLException ex){
            Logger.getLogger(PlanCursoUnidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return listaPlanCursoUnidad;
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
            Logger.getLogger(PlanCursoUnidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return nrc;
    }
}
