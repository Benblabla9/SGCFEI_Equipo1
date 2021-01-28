package gui;

import accesoDatos.controladoresDAO.UsuarioDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logica.ValidacionGeneral;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIInicioSesionControlador extends GUIControladorGeneral implements Initializable {
    @FXML private Button btnIngresar;
    @FXML private TextField tftNombreUsuario;
    @FXML private TextField tftContrasenia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limiteTextField(tftNombreUsuario,20);
        limiteTextField(tftContrasenia, 20);
    }

    public void ingresar() {
        boolean esValidoInformacion = validateInformation();
        if(esValidoInformacion) {
            String nombreUsuario = tftNombreUsuario.getText();
            String contrasenia = tftContrasenia.getText();
            UsuarioDAOImpl cuentaDAO = new UsuarioDAOImpl();
            boolean esCuenta = cuentaDAO.buscarUsuario(nombreUsuario,contrasenia);
            if(esCuenta == false){
                generarError("La cuenta no existe. Registrese");
            }else {
                if(esCuenta == true){
                    String tipo = cuentaDAO.rolUsuario(nombreUsuario,contrasenia);
                    switch (tipo) {
                        case "Docente":
                            abrirVentana("/gui/docente/GUIMenuDocente.fxml", btnIngresar);
                            break;
                        case "Coordinador":
                            abrirVentana("/gui/coordinador/GUIMenuCoordinador.fxml", btnIngresar);
                            break;
                        case "Administrador":
                            abrirVentana("/gui/administrador/GUIMenuAdministrador.fxml", btnIngresar);
                            break;
                        default: generarInformation(tipo);
                    }
                }else {
                    generarError("Error en la conexion. Intente más tarde");
                }
            }
        }else {
            generarAlerta("Ingresar datos válidos");
        }
    }

    private boolean validateInformation() {
        boolean validacion = true;
        ValidacionGeneral validacionGeneral = new ValidacionGeneral();
        boolean esValidoCuenta = validacionGeneral.validarPalabra(tftNombreUsuario.getText());
        if (!esValidoCuenta) {
            tftNombreUsuario.getStyleClass().add("error");
            validacion = false;
        }
        boolean esValidoContrasenia = validacionGeneral.validarPalabra(tftContrasenia.getText());
        if (!esValidoContrasenia) {
            validacion = false;
        }
        return  validacion;
    }
}


