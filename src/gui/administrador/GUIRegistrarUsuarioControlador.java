package gui.administrador;

import accesoDatos.controladoresDAO.AcademicoDAOImpl;
import accesoDatos.controladoresDAO.UsuarioDAOImpl;
import dominio.Academico;
import dominio.Usuario;
import gui.GUIControladorGeneral;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIRegistrarUsuarioControlador extends GUIControladorGeneral implements Initializable {
    @FXML private Button btnRegistrar;
    @FXML private Button btnCancelar;
    @FXML private Button btnCerrarSesion;
    @FXML private TextField tftNombreUsuario;
    @FXML private TextField tftContrasenia;
    @FXML private ComboBox cbRol;
    @FXML private TextField tftNumeroPersonal;
    @FXML private TextField tftNombre;
    @FXML private TextField tftCorreo;
    @FXML private TextField tftApellidos;
    @FXML private TextField tftNumeroCelular;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { comenzarComponentes(); }

    public void cerrarSesion() { cerrarSesion(); }

    public void registrar() {
        ingresarDatosUsuario();
    }

    public void ingresarDatosUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(tftNombreUsuario.getText());
        usuario.setContrasenia(tftContrasenia.getText());
        usuario.setRol((String) cbRol.getValue());
        validarUsuarioRepetido(usuario);
    }

    public void validarUsuarioRepetido(Usuario usuario) {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        boolean esRepetido = usuarioDAO.validarUsuario(tftNombreUsuario.getText());
        if (esRepetido == true) {
            generarError("Este nombre de usuario ya existe");
        }else{
            registrarUsuario(usuario);
        }
    }

    public void registrarUsuario(Usuario usuario) {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        boolean registrarUsuario = usuarioDAO.agregarUsuario(usuario);
        if(registrarUsuario == true){
            ingresarDatosAcademico();
        }else {
            generarError("No se pudo registrar el usuario");
        }
    }

    public void ingresarDatosAcademico() {
        Academico academico = new Academico();
        academico.setNumeroPersonal(tftNumeroPersonal.getText());
        academico.setNombre(tftNombre.getText());
        academico.setCorreo(tftCorreo.getText());
        academico.setApellidos(tftApellidos.getText());
        int numeroCelular = Integer.parseInt(tftNumeroCelular.getText());
        academico.setNumeroCelular(numeroCelular);
        validarAcademicoRepetido(academico);
    }

    public void validarAcademicoRepetido(Academico academico) {
        AcademicoDAOImpl academicoDAO = new AcademicoDAOImpl();
        boolean esRepetido = academicoDAO.validarAcademico(tftNumeroPersonal.getText());
        if(esRepetido == true) {
            generarError("El numero de personal ya esta registrado");
        }else {
            registrarAcademico(academico);
        }
    }

    public void registrarAcademico(Academico academico) {
        AcademicoDAOImpl academicoDAO = new AcademicoDAOImpl();
        boolean registrarAcademico = academicoDAO.agregarAcademico(academico);
        if(registrarAcademico == true){
            generarInformation("El usuario fue registrado correctamente");
            abrirVentana("/gui/administrador/GUIInicioSesion.fxml", btnRegistrar);
        }else{
            generarError("No se pudo registrar el usuario");
        }
    }

    public void cancelar() {
        generarCancelacion("¿Seguro que desea cancelar?", btnCancelar, "/gui/administrador/GUIMenuAdministrador.fxml");
    }

    public void comenzarComponentes() {
        cbRol.getItems().addAll("Administrador", "Coordinador", "Docente");
        limiteTextField(tftNombreUsuario, 50);
        limiteTextField(tftContrasenia, 10);
        limiteTextField(tftNumeroPersonal, 10);
        limiteTextField(tftNombre, 50);
        limiteTextField(tftCorreo, 50);
        limiteTextField(tftApellidos, 50);
        limiteTextField(tftNumeroCelular,10);
        palabrasProhibidasTextField(tftNumeroCelular);
        numerosProhibidosTextField(tftNombre);
        numerosProhibidosTextField(tftApellidos);
        espaciosProhibidosTextField(tftNumeroCelular);
        espaciosProhibidosTextField(tftContrasenia);
        espaciosProhibidosTextField(tftCorreo);
    }

}
