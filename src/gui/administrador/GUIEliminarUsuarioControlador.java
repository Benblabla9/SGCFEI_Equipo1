package gui.administrador;

import accesoDatos.controladoresDAO.AcademicoDAOImpl;
import accesoDatos.controladoresDAO.UsuarioDAOImpl;
import dominio.Academico;
import gui.GUIControladorGeneral;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIEliminarUsuarioControlador extends GUIControladorGeneral implements Initializable{
    @FXML private Button btnEliminar;
    @FXML private Button btnCancelar;
    @FXML private TableView<Academico> tvListaAcademicos;
    @FXML private TableColumn<Academico, String> tvNumeroPersonal;
    @FXML private TableColumn<Academico, String> tvNombre;
    @FXML private TableColumn<Academico, String> tvApellidos;
    @FXML private TableColumn<Academico, String> tvCorreo;
    @FXML private TableColumn<Academico, String> tvNumeroCelular;
    private ObservableList<Academico> academicoObservableList;
    private  Academico seleccionarAcademico;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarTabla();
    }

    public void eliminar() {
        clickTabla();
    }

    public void llenarTabla() {
        AcademicoDAOImpl academicoDAO = new AcademicoDAOImpl();
        academicoObservableList = FXCollections.observableArrayList();
        academicoObservableList.addAll(academicoDAO.getAcademicos());
        this.tvNumeroPersonal.setCellValueFactory(new PropertyValueFactory<Academico, String>("numeroPersonal"));
        this.tvNombre.setCellValueFactory(new PropertyValueFactory<Academico, String>("nombre"));
        this.tvApellidos.setCellValueFactory(new PropertyValueFactory<Academico, String>("apellidos"));
        this.tvCorreo.setCellValueFactory(new PropertyValueFactory<Academico, String>("correo"));
        this.tvNumeroCelular.setCellValueFactory(new PropertyValueFactory<Academico, String>("numeroCelular"));
        this.tvListaAcademicos.setItems(academicoObservableList);
    }

    public void clickTabla() {
        seleccionarAcademico = this.tvListaAcademicos.getSelectionModel().getSelectedItem();
        datosSeleccionados(seleccionarAcademico);
    }

    public void datosSeleccionados(Academico seleccionarAcademico) {
        Academico academico = new Academico();
        academico.setNumeroPersonal(seleccionarAcademico.getNumeroPersonal());
        academico.setNombre(seleccionarAcademico.getNombre());
        academico.setApellidos(seleccionarAcademico.getApellidos());
        academico.setCorreo(seleccionarAcademico.getCorreo());
        academico.setNumeroCelular(seleccionarAcademico.getNumeroCelular());
        eliminarAcademico(academico);
    }

    public void eliminarAcademico(Academico academico) {
        AcademicoDAOImpl academicoDAO = new AcademicoDAOImpl();
        int idUsuario = academicoDAO.buscarIdAcademico(academico.getNumeroPersonal());
        boolean eliminar = academicoDAO.eliminarAcademico(academico.getNumeroPersonal());
        if(eliminar == true) {
            eliminarUsuario(idUsuario);
        }else {
            generarError("No se pudo eliminar el academico");
        }
    }

    public void eliminarUsuario(int idUsuario) {
        generarConfirmacion("¿Seguro que desea eliminar al Academico?");
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        boolean eliminar = usuarioDAO.eliminarUsuario(idUsuario);
        if(eliminar == true) {
            generarInformation("El academico fue eliminado exitosamente");
        }else {
            generarError("No se pudo eliminar el academico");
        }
    }

    //academicos = FXCollections.observableArrayList(activeAcademicosList);
    //this.tvListaAcademicos.setItems(academicos);
    //AcademicoDAOImpl academicoDAO = new AcademicoDAOImpl();
    //List<Object> academicosObjectList = academicoDaoImpl.getAcademicos();
    //List<Academico> activeAcademicosList = new ArrayList<Academico>(academicoDAO.getAcademicos());
}

