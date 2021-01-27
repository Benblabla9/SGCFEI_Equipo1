package accesoDatos.interfacesDAO;

import dominio.PlanCurso;
import java.util.List;

public interface IPlanCursoDAO {
    boolean agregarPlanCurso(PlanCurso planCurso);
    boolean modificarPlanCurso(PlanCurso planCurso);
    boolean eliminarPlanCurso(PlanCurso planCurso);
    boolean validarPlanCurso(int nrc);
    PlanCurso getPlanCurso(int nrc);
    List<PlanCurso> getPlanesCurso();
}
