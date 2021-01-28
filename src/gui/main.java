package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage stageLogin) throws Exception {
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/gui/GUIInicioSesion.fxml"));
        Scene sceneLogin = new Scene(rootLogin);
        stageLogin.setScene(sceneLogin);
        stageLogin.setResizable(false);
        stageLogin.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

