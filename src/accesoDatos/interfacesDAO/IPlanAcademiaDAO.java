package accesoDatos.interfacesDAO;

import dominio.PlanAcademia;
import java.util.List;

public interface IPlanAcademiaDAO {
    boolean agregarPlanAcademia(PlanAcademia planAcademia);
    boolean eliminarPlanAcademia(int numeroPlan);
    boolean validarPlanAcademia(int numeroPlan);
    PlanAcademia getPlanAcademia(int numeroPlan);
    List<PlanAcademia> getPlanesAcademias();
}
