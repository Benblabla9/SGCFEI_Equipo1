package gui.coordinador;

import accesoDatos.controladoresDAO.PlanAcademiaAccionDAOImpl;
import accesoDatos.controladoresDAO.PlanAcademiaAccionDetalleDAOImpl;
import accesoDatos.controladoresDAO.PlanAcademiaDAOImpl;
import accesoDatos.controladoresDAO.PlanAcademiaExamenDAOImpl;
import dominio.PlanAcademia;
import dominio.PlanAcademiaAccion;
import dominio.PlanAcademiaAccionDetalle;
import dominio.PlanAcademiaExamen;
import gui.GUIControladorGeneral;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalDateStringConverter;

import java.net.URL;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class GUIRegistrarPlanTrabajoAcademiaControlador extends GUIControladorGeneral implements Initializable {
    @FXML private TextField tftNumeroPlan;
    @FXML private TextField tftPeriodo;
    @FXML private DatePicker dpFecha;
    @FXML private ComboBox cbProgramaEducativo;
    @FXML private TextField tftObjetivo;
    @FXML private TextField tftNombreAcademia;
    @FXML private TextField tftMiembros;
    @FXML private TextField tftObjetivoParticular;
    @FXML private TextField tftMeta;
    @FXML private TextField tftAccion;
    @FXML private TextField tftSemana;
    @FXML private TextField tftForma;
    @FXML private TextField tftExperienciaEducativa;
    @FXML private TextField tftTemasParcial1;
    @FXML private TextField tftTemasParcial2;
    @FXML private Button btnRegistrar;
    @FXML private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { comenzarComponentes(); }

    public void registrar() { ingresarDatosPlanAcademia(); }

    public void ingresarDatosPlanAcademia() {
        PlanAcademia planAcademia = new PlanAcademia();
        int numeroPlan = Integer.parseInt(tftNumeroPlan.getText());
        planAcademia.setNumeroPlan(numeroPlan);
        planAcademia.setPeriodo(tftPeriodo.getText());
        planAcademia.setObjetivo(tftObjetivo.getText());
        planAcademia.setNombreAcademia(tftNombreAcademia.getText());
        planAcademia.setMiembros(tftMiembros.getText());
        planAcademia.setProgramaEducativo((String) cbProgramaEducativo.getValue());
        String fecha = dpFecha.getEditor().getText();
        planAcademia.setFecha(fecha);
        validarPlanAcademiaRepetido(planAcademia);
    }

    public void validarPlanAcademiaRepetido(PlanAcademia planAcademia) {
        int numeroPlan = Integer.parseInt(tftNumeroPlan.getText());
        PlanAcademiaDAOImpl planAcademiaDAO = new PlanAcademiaDAOImpl();
        boolean esRepetido = planAcademiaDAO.validarPlanAcademia(numeroPlan);
        if (esRepetido == true) {
            generarError("Este numero de plan ya esta registrado");
        }else{
            registrarPlanAcademia(planAcademia);
        }
    }

    public void registrarPlanAcademia(PlanAcademia planAcademia) {
        PlanAcademiaDAOImpl planAcademiaDAO = new PlanAcademiaDAOImpl();
        boolean registrarPlanAcademia = planAcademiaDAO.agregarPlanAcademia(planAcademia);
        if(registrarPlanAcademia == true){
            int numeroPlan = planAcademia.getNumeroPlan();
            ingresarDatosPlanAcademiaAccion(numeroPlan);
        }else {
            generarError("No se pudo registrar el usuario");
        }
    }

    public void ingresarDatosPlanAcademiaAccion(int numeroPlan) {
        PlanAcademiaAccion planAcademiaAccion = new PlanAcademiaAccion();
        planAcademiaAccion.setObjetivoParticular(tftObjetivoParticular.getText());
        planAcademiaAccion.setMeta(tftMeta.getText());
        registrarPlanAcademiaAccion(planAcademiaAccion, numeroPlan);
    }

    public void registrarPlanAcademiaAccion(PlanAcademiaAccion planAcademiaAccion, int numeroPlan) {
        PlanAcademiaAccionDAOImpl planAcademiaAccionDAO = new PlanAcademiaAccionDAOImpl();
        boolean registrarPlanAcademiaAccion = planAcademiaAccionDAO.agregarPlanAcademiaAccion(planAcademiaAccion, numeroPlan);
        if(registrarPlanAcademiaAccion == true) {
            int idPlanAcademiaAccion = 0;
            idPlanAcademiaAccion = planAcademiaAccionDAO.buscarIdPlanAcademiaAccion(numeroPlan);
            ingresarDatosPlanAcademiaAccionDetalle(idPlanAcademiaAccion, numeroPlan);
        }else {
            generarError("No se pudo registrar el plan de academia accion");
        }
    }

    public void ingresarDatosPlanAcademiaAccionDetalle(int idPlanAcademiaAccion, int numeroPlan) {
        PlanAcademiaAccionDetalle planAcademiaAccionDetalle = new PlanAcademiaAccionDetalle();
        planAcademiaAccionDetalle.setAccion(tftAccion.getText());
        planAcademiaAccionDetalle.setSemana(tftSemana.getText());
        planAcademiaAccionDetalle.setForma(tftForma.getText());
        registrarPlanAcademiaAccionDetalle(planAcademiaAccionDetalle, idPlanAcademiaAccion, numeroPlan);
    }

    public void registrarPlanAcademiaAccionDetalle(PlanAcademiaAccionDetalle planAcademiaAccionDetalle, int idPlanAcademiaAccion, int numeroPlan) {
        PlanAcademiaAccionDetalleDAOImpl planAcademiaAccionDetalleDAO = new PlanAcademiaAccionDetalleDAOImpl();
        boolean registrarPlanAcademiaAccionDetalle = planAcademiaAccionDetalleDAO.agregarPlanAcademiaAccionDetalle(planAcademiaAccionDetalle, idPlanAcademiaAccion);
        if(registrarPlanAcademiaAccionDetalle == true) {
            ingresarDatosPlanAcademiaExamen(numeroPlan);
        }else {
            generarError("No se pudo registrar el plan de academia accion detalle");
        }
    }

    public void ingresarDatosPlanAcademiaExamen(int numeroPlan) {
        PlanAcademiaExamen planAcademiaExamen = new PlanAcademiaExamen();
        planAcademiaExamen.setExperienciaEducativa(tftExperienciaEducativa.getText());
        planAcademiaExamen.setTemasParcial1(tftTemasParcial1.getText());
        planAcademiaExamen.setTemasParcial2(tftTemasParcial2.getText());
        registrarPlanAcademiaExamen(planAcademiaExamen, numeroPlan);
    }

    public void registrarPlanAcademiaExamen(PlanAcademiaExamen planAcademiaExamen, int numeroPlan) {
        PlanAcademiaExamenDAOImpl planAcademiaExamenDAO = new PlanAcademiaExamenDAOImpl();
        boolean registrarPlanAcademiaExamen = planAcademiaExamenDAO.agregarPlanAcademiaExamen(planAcademiaExamen, numeroPlan);
        if(registrarPlanAcademiaExamen == true){
            generarInformation("El plan de trabajo de academia fue registrado correctamente");
            abrirVentana("/gui/coordinador/GUIMenuCoordinador.fxml", btnRegistrar);
        }else {
            generarError("No se pudo registrar el plan de academia evaluacion");
        }
    }

    public void cancelar() {
        generarCancelacion("¿Seguro que desea cancelar?", btnCancelar, "/gui/coordinador/GUIMenuACoordinador.fxml");
    }

    public void comenzarComponentes() {
        cbProgramaEducativo.getItems().addAll("Ingenieria en Software", "Redes y Servicios de Computo", "Tecnologías Computacionales");
        cbProgramaEducativo.getItems().addAll("Estadistica", "Informatica", "Geografia", "Economia");
        limiteTextField(tftNumeroPlan, 5);
        palabrasProhibidasTextField(tftNumeroPlan);
        limiteTextField(tftPeriodo, 30);
        limiteTextField(tftObjetivo,200);
        limiteTextField(tftNombreAcademia, 100);
        numerosProhibidosTextField(tftNombreAcademia);
        limiteTextField(tftMiembros,250);
        numerosProhibidosTextField(tftMiembros);
        limiteTextField(tftObjetivoParticular,200);
        limiteTextField(tftMeta,200);
        limiteTextField(tftAccion, 200);
        limiteTextField(tftSemana, 50);
        limiteTextField(tftForma, 50);
        limiteTextField(tftExperienciaEducativa,100);
        limiteTextField(tftTemasParcial1,250);
        limiteTextField(tftTemasParcial2,250);
        dpFecha.setEditable(false);
        dpFecha.setConverter(new LocalDateStringConverter(FormatStyle.FULL));
    }

}



