package accesoDatos.controladoresDAO;

import accesoDatos.Conexion;
import accesoDatos.interfacesDAO.IPlanCursoDAO;
import dominio.PlanCurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanCursoDAOImpl implements IPlanCursoDAO {
    private final Conexion conexion;
    private Connection connection;
    private ResultSet resultado;
    private PreparedStatement preparedStatement;

    public PlanCursoDAOImpl() { conexion = new Conexion(); }

    @Override
    public boolean agregarPlanCurso(PlanCurso planCurso) {
        boolean agregar = false;
        try{
            connection = conexion.getConnection();
            PreparedStatement sentencePlanCurso = connection.prepareStatement("INSERT INTO PlanCurso(nrc, bloque, seccion, experienciaEducativa," +
                    "programaEducativo, objetivo) VALUES(?,?,?,?,?,?)");
            sentencePlanCurso.setInt(1, planCurso.getNrc());
            sentencePlanCurso.setString(2, planCurso.getBloque());
            sentencePlanCurso.setString(3, planCurso.getSeccion());
            sentencePlanCurso.setString(4, planCurso.getExperienciaEducativa());
            sentencePlanCurso.setString(5, planCurso.getProgramaEducativo());
            sentencePlanCurso.setString(6, planCurso.getObjetivo());
            sentencePlanCurso.executeUpdate();
            agregar = true;
        }catch (SQLException ex) {
            Logger.getLogger(PlanCursoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrarConexion();
        }
        return agregar;
    }

    @Override
    public boolean eliminarPlanCurso(int nrc) {
        boolean resultado = false;
        try{
            connection = conexion.getConnection();
            String queryEliminar = "DELETE FROM PlanCurso WHERE nrc = ?";
            PreparedStatement sentenceEliminar = connection.prepareStatement(queryEliminar);
            sentenceEliminar.setInt(1, nrc);
            sentenceEliminar.executeUpdate();
            resultado = true;
        }catch(SQLException ex){
            Logger.getLogger(PlanCursoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean validarPlanCurso(int nrc) {
        boolean esValido = false;
        try{
            connection = conexion.getConnection();
            String queryPlanCurso = "SELECT nrc FROM PlanCurso WHERE nrc = ?";
            PreparedStatement sentencePlanCurso = connection.prepareStatement(queryPlanCurso);
            sentencePlanCurso.setInt(1, nrc);
            resultado = sentencePlanCurso.executeQuery();
            if(resultado.next()){
                esValido = true;
            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanCursoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return esValido;
    }

    @Override
    public PlanCurso getPlanCurso(int nrc) {
        PlanCurso planCurso = new PlanCurso();
        try {
            connection = conexion.getConnection();
            String queryGetPlanCurso = "SELECT nrc, bloque, seccion, experienciaEducativa, programaEducativo, objetivo FROM PlanCurso" +
                    "WHERE nrc = ?";
            preparedStatement = connection.prepareStatement(queryGetPlanCurso);
            preparedStatement.setInt(1, nrc);
            resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                planCurso.setNrc(resultado.getInt("nrc"));
                planCurso.setBloque(resultado.getString("bloque"));
                planCurso.setSeccion(resultado.getString("seccion"));
                planCurso.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                planCurso.setProgramaEducativo(resultado.getString("programaEducativo"));
                planCurso.setObjetivo(resultado.getString("objetivo"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(PlanCurso.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conexion.cerrarConexion();
        }
        return planCurso;
    }

    @Override
    public List<PlanCurso> getPlanesCurso() {
        List<PlanCurso> listaPlanCurso = new ArrayList<>();
        try{
            connection = conexion.getConnection();
            String queryPlanCurso = "SELECT nrc, bloque, seccion, experienciaEducativa, programaEducativo FROM PlanCurso";
            PreparedStatement sentenciaPlanCurso = connection.prepareStatement(queryPlanCurso);
            resultado = sentenciaPlanCurso.executeQuery();
            while(resultado.next()){
                PlanCurso planCurso = new PlanCurso();
                planCurso.setNrc(resultado.getInt("nrc"));
                planCurso.setBloque(resultado.getString("bloque"));
                planCurso.setSeccion(resultado.getString("seccion"));
                planCurso.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                planCurso.setProgramaEducativo(resultado.getString("programaEducativo"));
                listaPlanCurso.add(planCurso);
            }
        }catch (SQLException ex){
            Logger.getLogger(AcademicoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrarConexion();
        }
        return listaPlanCurso;
    }
}
