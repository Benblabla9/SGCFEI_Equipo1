package accesoDatos.interfacesDAO;

import dominio.PlanAcademiaAccionDetalle;

public interface IPlanAcademiaAccionDetalleDAO {
    boolean agregarPlanAcademiaAccionDetalle(PlanAcademiaAccionDetalle planAcademiaAccionDetalle);
    boolean eliminarPlanAcademiaAccionDetalle(PlanAcademiaAccionDetalle planAcademiaAccionDetalle);
    boolean modificarPlanAcademiaAccionDetalle(PlanAcademiaAccionDetalle planAcademiaAccionDetalle);
}
