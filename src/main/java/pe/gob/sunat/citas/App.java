package pe.gob.sunat.citas;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pe.gob.sunat.citas.utils.CitasUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * JavaFX App
 */
public class App extends Application {

	//@FXML
   // private ImageView imgLogin;
	
    private static Scene scene;
    

    @Override
    public void start(Stage stage) throws IOException {

        
        scene = new Scene(CitasUtils.loadFXML("fxml/login/login"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(CitasUtils.loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }

}