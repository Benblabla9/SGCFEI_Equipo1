package accesoDatos.interfacesDAO;

import java.util.List;
import dominio.Academico;

public interface IAcademicoDAO {
    boolean agregarAcademico(Academico academico);
    boolean eliminarAcademico(Academico academico);
    boolean modificarAcademico(Academico academico);
    boolean validarAcademico(String numeroPersonal);
    Academico getAcademico(String numeroPersonal);
    List<Academico> getAcademicos();
}
