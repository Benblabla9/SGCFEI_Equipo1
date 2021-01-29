package accesoDatos.interfacesDAO;

import dominio.PlanAcademiaAccionDetalle;

public interface IPlanAcademiaAccionDetalleDAO {
    boolean agregarPlanAcademiaAccionDetalle(PlanAcademiaAccionDetalle planAcademiaAccionDetalle, int idPlanAcademiaAccion);
    boolean eliminarPlanAcademiaAccionDetalle(int idPlanAcademiaAccion);
    boolean modificarAcademico(PlanAcademiaAccionDetalle planAcademiaAccionDetalle, int idPlanAcademiaAccion);
    int buscarIdPlanAcademiaAccionDetalle(String accion, int idPlanAcademiaAccion);
}
