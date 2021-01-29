package accesoDatos.interfacesDAO;

import dominio.Academico;

import java.util.List;

public interface IAcademicoDAO {
    boolean agregarAcademico(Academico academico, int idUsuario);
    boolean eliminarAcademico(String numeroPersonal);
    boolean modificarAcademico(Academico academico);
    boolean validarAcademico(String numeroPersonal);
    int buscarIdAcademico(String numeroPersonal);
    Academico getAcademico(String numeroPersonal);
    List<Academico> getAcademicos();
}
