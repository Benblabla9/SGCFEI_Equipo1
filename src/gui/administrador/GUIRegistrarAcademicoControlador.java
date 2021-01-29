package gui.administrador;

import accesoDatos.controladoresDAO.AcademicoDAOImpl;
import dominio.Academico;
import gui.GUIControladorGeneral;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIRegistrarAcademicoControlador extends GUIControladorGeneral implements Initializable {
    @FXML private TextField tftNumeroPersonal;
    @FXML private TextField tftNombre;
    @FXML private TextField tftCorreo;
    @FXML private TextField tftApellidos;
    @FXML private TextField tftNumeroCelular;
    @FXML private Button btnRegistrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { comenzarComponentes(); }

    public void registrar() {
        ingresarDatosAcademico();
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
