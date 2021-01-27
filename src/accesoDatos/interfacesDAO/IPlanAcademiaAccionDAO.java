package accesoDatos.interfacesDAO;

import dominio.PlanAcademiaAccion;

public interface IPlanAcademiaAccionDAO {
    boolean agregarPlanAcademiaAccion(PlanAcademiaAccion planAcademiaAccion);
    boolean eliminarPlanAcademiaAccion(PlanAcademiaAccion planAcademiaAccion);
    boolean modificarPlanAcademiaAccion(PlanAcademiaAccion planAcademiaAccion);
}
