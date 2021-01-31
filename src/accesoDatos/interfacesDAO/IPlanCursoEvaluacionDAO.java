package accesoDatos.interfacesDAO;

import dominio.PlanCursoEvaluacion;
import java.util.List;

public interface IPlanCursoEvaluacionDAO {
    boolean agregarPlanCursoEvaluacion(PlanCursoEvaluacion planCursoEvaluacion,int nrc);
    boolean eliminarPlanCursoEvaluacion(int nrc);
    boolean modificarPlanCursoEvaluacion (PlanCursoEvaluacion planCursoEvaluacion,int nrc);
    boolean validarPlanCursoEvaluacion(int nrc);
    PlanCursoEvaluacion getPlanCursoEvaluacion(int nrc);
    List<PlanCursoEvaluacion> getPlanesCursoEvaluacion();
    public int buscarNrc(int nrc);
}
