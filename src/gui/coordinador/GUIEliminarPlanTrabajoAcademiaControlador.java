package gui.coordinador;

import accesoDatos.controladoresDAO.PlanAcademiaAccionDAOImpl;
import accesoDatos.controladoresDAO.PlanAcademiaAccionDetalleDAOImpl;
import accesoDatos.controladoresDAO.PlanAcademiaDAOImpl;
import accesoDatos.controladoresDAO.PlanAcademiaExamenDAOImpl;
import dominio.PlanAcademia;
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

public class GUIEliminarPlanTrabajoAcademiaControlador extends GUIControladorGeneral implements Initializable {

    @FXML private TableView<PlanAcademia> tvListaPlanAcademia;
    @FXML private TableColumn<PlanAcademia, String> tvNumeroPlan;
    @FXML private TableColumn<PlanAcademia, String> tvFecha;
    @FXML private TableColumn<PlanAcademia, String> tvPeriodo;
    @FXML private TableColumn<PlanAcademia, String> tvObjetivo;
    @FXML private TableColumn<PlanAcademia, String> tvProgramaEducativo;
    @FXML private TableColumn<PlanAcademia, String> tvNombreAcademia;
    @FXML private TableColumn<PlanAcademia, String> tvMiembros;
    @FXML private Button btnRegresar;
    private ObservableList<PlanAcademia> planAcademiaObservableList;
    private PlanAcademia seleccionarPlanAcademia;

    @Override
    public void initialize(URL url, ResourceBundle rb) { llenarTabla();}

    public void clickTabla() {
        seleccionarPlanAcademia = this.tvListaPlanAcademia.getSelectionModel().getSelectedItem();
        datosSeleccionados(seleccionarPlanAcademia);
    }

    public void datosSeleccionados(PlanAcademia seleccionarPlanAcademia) {
        PlanAcademia planAcademia = new PlanAcademia();
        planAcademia.setNumeroPlan(seleccionarPlanAcademia.getNumeroPlan());
        planAcademia.setFecha(seleccionarPlanAcademia.getFecha());
        planAcademia.setProgramaEducativo(seleccionarPlanAcademia.getProgramaEducativo());
        planAcademia.setMiembros(seleccionarPlanAcademia.getMiembros());
        planAcademia.setNombreAcademia(seleccionarPlanAcademia.getNombreAcademia());
        planAcademia.setObjetivo(seleccionarPlanAcademia.getObjetivo());
        planAcademia.setPeriodo(seleccionarPlanAcademia.getPeriodo());
        buscarIdPlanAcademiaAccion(planAcademia);
    }

    public void buscarIdPlanAcademiaAccion(PlanAcademia planAcademia) {
        PlanAcademiaAccionDAOImpl planAcademiaAccionDAO = new PlanAcademiaAccionDAOImpl();
        int idPlanAcademiaAccion = planAcademiaAccionDAO.buscarIdPlanAcademiaAccion(planAcademia.getNumeroPlan());
        boolean resultado;
        resultado = eliminarPlanAcademiaAccionDetalle(idPlanAcademiaAccion);
        if(resultado == true) {
            eliminarPlanAcademiaAccion(planAcademia);
        }else {
            generarError("No se pudo eliminar el plan de academia accion detalle");
        }
    }

    public boolean eliminarPlanAcademiaAccionDetalle(int idPlanAcademiaAccion) {
        boolean resultado = false;
        PlanAcademiaAccionDetalleDAOImpl planAcademiaAccionDetalleDAO = new PlanAcademiaAccionDetalleDAOImpl();
        boolean eliminar = planAcademiaAccionDetalleDAO.eliminarPlanAcademiaAccionDetalle(idPlanAcademiaAccion);
        if(eliminar == true) {
            resultado = true;
        }
        return resultado;
    }

    public void eliminarPlanAcademiaAccion(PlanAcademia planAcademia) {
        PlanAcademiaAccionDAOImpl planAcademiaAccionDAO = new PlanAcademiaAccionDAOImpl();
        boolean eliminar = planAcademiaAccionDAO.eliminarPlanAcademiaAccion(planAcademia.getNumeroPlan());
        if(eliminar == true) {
            eliminarPlanAcademiaExamen(planAcademia);
        }else {
            generarError("No se pudo eliminar el plan de academia accion");
        }
    }

    public void eliminarPlanAcademiaExamen(PlanAcademia planAcademia) {
        PlanAcademiaExamenDAOImpl planAcademiaExamenDAO = new PlanAcademiaExamenDAOImpl();
        boolean eliminar = planAcademiaExamenDAO.eliminarPlanAcademiaExamen(planAcademia.getNumeroPlan());
        if (eliminar == true) {
            eliminarPlanAcademia(planAcademia);
        }else {
            generarError("No se pudo eliminar el plan academia examen");
        }
    }

    public void eliminarPlanAcademia(PlanAcademia planAcademia) {
        PlanAcademiaDAOImpl planAcademiaDAO = new PlanAcademiaDAOImpl();
        boolean eliminar = planAcademiaDAO.eliminarPlanAcademia(planAcademia.getNumeroPlan());
        if (eliminar == true) {
            generarInformation("El plan de academia fue eliminado exitosamente");
        }else {
            generarError("No se pudo eliminar el plan de academia");
        }
    }

    public void regresar() {
        generarCancelacion("¿Seguro que desea regresar?", btnRegresar, "/gui/coordinador/GUIMenuCoordinador.fxml");
    }

    public void llenarTabla() {
        PlanAcademiaDAOImpl planAcademiaDAO = new PlanAcademiaDAOImpl();
        planAcademiaObservableList = FXCollections.observableArrayList();
        planAcademiaObservableList.addAll(planAcademiaDAO.getPlanesAcademias());
        this.tvNumeroPlan.setCellValueFactory(new PropertyValueFactory<PlanAcademia, String>("numeroPlan"));
        this.tvFecha.setCellValueFactory(new PropertyValueFactory<PlanAcademia, String>("fecha"));
        this.tvPeriodo.setCellValueFactory(new PropertyValueFactory<PlanAcademia, String>("periodo"));
        this.tvObjetivo.setCellValueFactory(new PropertyValueFactory<PlanAcademia, String>("objetivo"));
        this.tvProgramaEducativo.setCellValueFactory(new PropertyValueFactory<PlanAcademia, String>("programaEducativo"));
        this.tvNombreAcademia.setCellValueFactory(new PropertyValueFactory<PlanAcademia, String>("nombreAcademia"));
        this.tvMiembros.setCellValueFactory(new PropertyValueFactory<PlanAcademia, String>("miembros"));
        this.tvListaPlanAcademia.setItems(planAcademiaObservableList);
    }
}