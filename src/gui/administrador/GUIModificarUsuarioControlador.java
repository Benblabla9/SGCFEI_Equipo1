package gui.administrador;

import accesoDatos.controladoresDAO.AcademicoDAOImpl;
import accesoDatos.controladoresDAO.UsuarioDAOImpl;
import dominio.Academico;
import dominio.Usuario;
import gui.GUIControladorGeneral;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIModificarUsuarioControlador extends GUIControladorGeneral implements Initializable {
    @FXML private Button btnModificar;
    @FXML private Button btnCancelar;
    @FXML private TableView<Usuario> tvListaUsuarios;
    @FXML private TableColumn<Usuario, String> tvNombreUsuario;
    @FXML private TableColumn<Usuario, String> tvRol;
    @FXML private TextField tftNumeroPersonal;
    @FXML private TextField tftNombre;
    @FXML private TextField tftApellidos;
    @FXML private TextField tftCorreo;
    @FXML private TextField tftNumeroCelular;
    @FXML private int idUsuario;
    private ObservableList<Usuario> usuarioObservableList;
    private  Usuario seleccionarUsuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarTabla();
        comenzarComponentes();
    }

    public void modificar() {
        ingresarDatosAcademico(idUsuario);
    }

    public void cancelar() {
        generarCancelacion("¿Seguro que desea cancelar?", btnCancelar, "/gui/administrador/GUIMenuAdministrador.fxml");
    }

    public void clickTabla() {
        seleccionarUsuario = this.tvListaUsuarios.getSelectionModel().getSelectedItem();
        datosSeleccionados(seleccionarUsuario);
    }

    public void datosSeleccionados(Usuario seleccionarUsuario) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(seleccionarUsuario.getNombreUsuario());
        usuario.setContrasenia(seleccionarUsuario.getContrasenia());
        usuario.setRol(seleccionarUsuario.getRol());
        buscarIdUsuario(usuario);
    }

    public void buscarIdUsuario(Usuario usuario) {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        idUsuario = usuarioDAO.buscarIdUsuario(usuario.getNombreUsuario(), usuario.getContrasenia());
        buscarAcademico(idUsuario);
    }

    public void buscarAcademico(int idUsuario) {
        Academico academico;
        AcademicoDAOImpl academicoDAO = new AcademicoDAOImpl();
        academico = academicoDAO.getAcademico(idUsuario);
        tftNumeroPersonal.setText(academico.getNumeroPersonal());
        tftNombre.setText(academico.getNombre());
        tftApellidos.setText(academico.getApellidos());
        tftCorreo.setText(academico.getCorreo());
        tftNumeroCelular.setText(academico.getNumeroPersonal());
    }

    public void ingresarDatosAcademico(int idUsuario) {
        Academico academico = new Academico();
        academico.setNumeroPersonal(tftNumeroPersonal.getText());
        academico.setNombre(tftNombre.getText());
        academico.setCorreo(tftCorreo.getText());
        academico.setApellidos(tftApellidos.getText());
        academico.setNumeroCelular(tftNumeroCelular.getText());
        validarAcademicoRepetido(academico, idUsuario);
    }

    public void validarAcademicoRepetido(Academico academico, int idUsuario) {
        AcademicoDAOImpl academicoDAO = new AcademicoDAOImpl();
        boolean esRepetido = academicoDAO.validarAcademico(tftNumeroPersonal.getText());
        if(esRepetido == true) {
            generarError("El numero de personal ya esta registrado");
        }else {
            modificarAcademico(academico, idUsuario);
        }
    }

    public void modificarAcademico(Academico academico, int idUsuario) {
        AcademicoDAOImpl academicoDAO = new AcademicoDAOImpl();
        boolean modificarAcademico = academicoDAO.modificarAcademico(academico, idUsuario);
        if(modificarAcademico == true){
            generarInformation("El academico fue modificado correctamente");
            abrirVentana("/gui/administrador/GUIMenuAdministrador.fxml", btnModificar);
        }else{
            generarError("No se pudo modificar el academico");
        }
    }

    public void llenarTabla() {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        usuarioObservableList = FXCollections.observableArrayList();
        usuarioObservableList.addAll(usuarioDAO.getUsuarios());
        this.tvNombreUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombreUsuario"));
        this.tvRol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("rol"));
        this.tvListaUsuarios.setItems(usuarioObservableList);
    }

    public void comenzarComponentes() {
        limiteTextField(tftNumeroPersonal, 10);
        limiteTextField(tftNombre, 50);
        limiteTextField(tftCorreo, 50);
        limiteTextField(tftApellidos, 50);
        limiteTextField(tftNumeroCelular,10);
        palabrasProhibidasTextField(tftNumeroCelular);
        numerosProhibidosTextField(tftNombre);
        numerosProhibidosTextField(tftApellidos);
        espaciosProhibidosTextField(tftNumeroCelular);
        espaciosProhibidosTextField(tftCorreo);
    }

}


