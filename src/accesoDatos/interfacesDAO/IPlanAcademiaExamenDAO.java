package accesoDatos.interfacesDAO;

import dominio.PlanAcademia;
import dominio.PlanAcademiaExamen;

public interface IPlanAcademiaExamenDAO {
    boolean agregarPlanAcademiaExamen(PlanAcademiaExamen planAcademiaExamen);
    boolean modificarPlanAcademiaExamen(PlanAcademiaExamen planAcademiaExamen);
    boolean eliminarPlanAcademiaExamen(PlanAcademiaExamen planAcademiaExamen);
}
