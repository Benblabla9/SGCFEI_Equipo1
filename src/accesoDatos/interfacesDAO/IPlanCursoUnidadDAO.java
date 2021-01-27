package accesoDatos.interfacesDAO;

import dominio.PlanCursoUnidad;

public interface IPlanCursoUnidadDAO {
    boolean agregarPlanCursoUnidad(PlanCursoUnidad planCursoUnidad);
    boolean modificarPlanCursoUnidad(PlanCursoUnidad planCursoUnidad);
    boolean eliminarPlanCursoUnidad(PlanCursoUnidad planCursoUnidad);
}
