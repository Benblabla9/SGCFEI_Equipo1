package gui.administrador;

import accesoDatos.controladoresDAO.UsuarioDAOImpl;
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
    @FXML private TextField tftNombreUsuario;
    @FXML private TextField tftContrasenia;
    @FXML private ComboBox cbRol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { comenzarComponentes(); }

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
            generarInformation("El usuario fue registrado correctamente");
            abrirVentana("/gui/administrador/GUIRegistrarDocente", btnRegistrar);
        }else {
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
        espaciosProhibidosTextField(tftContrasenia);
    }

}
