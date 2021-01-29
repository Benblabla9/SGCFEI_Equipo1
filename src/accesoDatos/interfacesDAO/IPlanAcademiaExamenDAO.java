package accesoDatos.interfacesDAO;

import dominio.PlanAcademiaExamen;

public interface IPlanAcademiaExamenDAO {
    boolean agregarPlanAcademiaExamen(PlanAcademiaExamen planAcademiaExamen, int numeroPlan);
    boolean eliminarPlanAcademiaExamen(int numeroPlan);
    boolean modificarPlanAcademiaExamen(PlanAcademiaExamen planAcademiaExamen, int numeroPlan);
    int buscarIdPlanAcademiaExamen(String experienciaEducativa, int numeroPlan);
}
