package gui.administrador;

import gui.GUIControladorGeneral;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIMenuAdministradorControlador extends GUIControladorGeneral implements Initializable {
    @FXML private Button btnRegistrar;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnCerrarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    public void registrar() {
        abrirVentana("/gui/administrador/GUIRegistrarUsuario.fxml", btnRegistrar);
    }

    public void modificar() {
        abrirVentana("/gui/administrador/GUIModificarUsuario.fxml", btnModificar);
    }

    public void eliminar() {
        abrirVentana("/gui/administrador/GUIEliminarUsuario.fxml", btnEliminar);
    }

    public void cerrarSesion() {
        cerrarSesion();
    }
}

