package accesoDatos.interfacesDAO;

import dominio.Academico;

import java.util.List;

public interface IAcademicoDAO {
    boolean agregarAcademico(Academico academico);
    boolean eliminarAcademico(String numeroPersonal, String nombre, String correo);
    boolean modificarAcademico(Academico academico);
    boolean validarAcademico(String numeroPersonal);
    Academico getAcademico(String numeroPersonal);
    List<Academico> getAcademicos();
}
