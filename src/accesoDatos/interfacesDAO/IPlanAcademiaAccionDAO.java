package accesoDatos.interfacesDAO;

import dominio.PlanAcademiaAccion;

public interface IPlanAcademiaAccionDAO {
    boolean agregarPlanAcademiaAccion(PlanAcademiaAccion planAcademiaAccion, int numeroPlan);
    boolean eliminarPlanAcademiaAccion(int numeroPlan);
    boolean modificarPlanAcademiaAccion(PlanAcademiaAccion planAcademiaAccion, int numeroPlan);
    int buscarIdPlanAcademiaAccion(String objetivoParticular, String meta, int numeroPlan);
}
