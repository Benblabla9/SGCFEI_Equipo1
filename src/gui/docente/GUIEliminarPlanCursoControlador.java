package gui.docente;

import accesoDatos.controladoresDAO.PlanCursoDAOImpl;
import accesoDatos.controladoresDAO.PlanCursoEvaluacionDAOImpl;
import accesoDatos.controladoresDAO.PlanCursoUnidadDAOImpl;
import dominio.PlanCurso;
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

public class GUIEliminarPlanCursoControlador extends GUIControladorGeneral implements Initializable {
    @FXML private TableView<PlanCurso> tvListaPlanCurso;
    @FXML private TableColumn<PlanCurso, String> tvNrc;
    @FXML private TableColumn<PlanCurso, String> tvBloque;
    @FXML private TableColumn<PlanCurso, String> tvSeccion;
    @FXML private TableColumn<PlanCurso, String> tvExperienciaEducativa;
    @FXML private TableColumn<PlanCurso, String> tvProgramaEducativo;
    @FXML private TableColumn<PlanCurso, String> tvObjetivo;
    @FXML private Button btnRegresar;
    private ObservableList<PlanCurso> planCursoObservableList;
    private PlanCurso seleccionarPlanCurso;

    @Override
    public void initialize(URL url, ResourceBundle rb) { llenarTabla(); }

    public void clickTabla() {
        seleccionarPlanCurso = this.tvListaPlanCurso.getSelectionModel().getSelectedItem();
        datosSeleccionados(seleccionarPlanCurso);
    }

    public void datosSeleccionados(PlanCurso seleccionarPlanCurso) {
        PlanCurso planCurso = new PlanCurso();
        planCurso.setNrc(seleccionarPlanCurso.getNrc());
        planCurso.setSeccion(seleccionarPlanCurso.getSeccion());
        planCurso.setBloque(seleccionarPlanCurso.getBloque());
        planCurso.setProgramaEducativo(seleccionarPlanCurso.getProgramaEducativo());
        planCurso.setExperienciaEducativa(seleccionarPlanCurso.getExperienciaEducativa());
        planCurso.setObjetivo(seleccionarPlanCurso.getObjetivo());
        eliminarPlanCursoUnidad(planCurso);
    }

    public void eliminarPlanCursoUnidad(PlanCurso planCurso) {
        PlanCursoUnidadDAOImpl planCursoUnidadDAO = new PlanCursoUnidadDAOImpl();
        boolean eliminar = planCursoUnidadDAO.eliminarPlanCursoUnidad(planCurso.getNrc());
        if(eliminar == true) {
            eliminarPlanCursoEvaluacion(planCurso);
        }else {
            generarError("No se pudo eliminar el plan de curso de unidad");
        }
    }

    public void eliminarPlanCursoEvaluacion(PlanCurso planCurso) {
        PlanCursoEvaluacionDAOImpl planCursoEvaluacionDAO = new PlanCursoEvaluacionDAOImpl();
        boolean eliminar = planCursoEvaluacionDAO.eliminarPlanCursoEvaluacion(planCurso.getNrc());
        if(eliminar == true) {
            eliminarPlanCurso(planCurso);
        }else {
            generarError("No se pudo eliminar el plan de curso evaluacion");
        }
    }

    public void eliminarPlanCurso(PlanCurso planCurso) {
        boolean respuesta = generarConfirmacion("¿Desea eliminar este plan de curso?");
        if(respuesta == true) {
            PlanCursoDAOImpl planCursoDAO = new PlanCursoDAOImpl();
            boolean eliminar = planCursoDAO.eliminarPlanCurso(planCurso.getNrc());
            if(eliminar == true) {
                generarInformation("El plan de curso fue eliminado correctamente");
            }else {
                generarError("No se pudo eliminar el plan de curso");
            }
        }
    }

    public void regresar() {
        generarCancelacion("¿Seguro que desea regresar?", btnRegresar, "/gui/docente/GUIMenuDocente.fxml");
    }

    public void llenarTabla() {
        PlanCursoDAOImpl planCursoDAO = new PlanCursoDAOImpl();
        planCursoObservableList = FXCollections.observableArrayList();
        planCursoObservableList.addAll(planCursoDAO.getPlanesCurso());
        this.tvNrc.setCellValueFactory(new PropertyValueFactory<PlanCurso, String>("nrc"));
        this.tvBloque.setCellValueFactory(new PropertyValueFactory<PlanCurso, String>("bloque"));
        this.tvSeccion.setCellValueFactory(new PropertyValueFactory<PlanCurso, String>("seccion"));
        this.tvExperienciaEducativa.setCellValueFactory(new PropertyValueFactory<PlanCurso, String>("experienciaEducativa"));
        this.tvProgramaEducativo.setCellValueFactory(new PropertyValueFactory<PlanCurso, String>("programaEducativo"));
        this.tvObjetivo.setCellValueFactory(new PropertyValueFactory<PlanCurso, String>("objetivo"));
        this.tvListaPlanCurso.setItems(planCursoObservableList);
    }
}
