package accesoDatos.interfacesDAO;

import dominio.PlanCursoEvaluación;

public interface IPlanCursoEvaluacionDAO {
    boolean agregarPlanCursoEvalucion(PlanCursoEvaluación planCursoEvaluación);
    boolean modificarPlanCursoEvaluacion(PlanCursoEvaluación planCursoEvaluación);
    boolean eliminarPlanCursoEvaluacion(PlanCursoEvaluación planCursoEvaluación);
}
