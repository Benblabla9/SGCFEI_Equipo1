package accesoDatos.interfacesDAO;

import dominio.PlanCurso;
import java.util.List;

public interface IPlanCursoDAO {
    boolean agregarPlanCurso(PlanCurso planCurso);
    boolean eliminarPlanCurso(int nrc);
    boolean modificarPlanCurso(PlanCurso planCurso,int nrc);
    boolean validarPlanCurso(int nrc);
    PlanCurso getPlanCurso(int nrc);
    List<PlanCurso> getPlanesCurso();
}
