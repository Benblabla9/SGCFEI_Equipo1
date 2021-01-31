package accesoDatos.interfacesDAO;

import dominio.PlanCursoUnidad;
import java.util.List;

public interface IPlanCursoUnidadDAO {
    boolean agregarPlanCursoUnidad(PlanCursoUnidad planCursoUnidad, int nrc);
    boolean eliminarPlanCursoUnidad(int nrc);
    boolean modificarPlanCursoUnidad(PlanCursoUnidad planCursoUnidad, int nrc);
    boolean validarPlanCursoUnidad(int nrc);
    PlanCursoUnidad getPlanCursoUnidad(int nrc);
    List<PlanCursoUnidad> getPlanesCursoUnidad();
    public int buscarNrc(int nrc);
}
