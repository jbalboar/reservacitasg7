package pe.gob.sunat.citas;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pe.gob.sunat.citas.bean.UsuarioBean;
import pe.gob.sunat.citas.service.UsuariosService;
import pe.gob.sunat.mongo.MongoDBConnector;

public class PrimaryController {

	@FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;
    
	private MongoClient mongoClient;
    private MongoDatabase database;
    
    private final UsuariosService usuarioService = new UsuariosService();


    public void initialize() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void validarIngreso1() throws IOException {
        System.out.println("pruebaaa");
        
        MongoCollection<Document> collection = database.getCollection("usuarios");
        FindIterable<Document> documents = collection.find();
        
        for (Document document : documents) {
        	System.out.println(document.get("nombres"));
		}
    }
    
    @FXML
    private void validarIngreso() throws IOException {
        System.out.println("pruebaaa");
        
        UsuarioBean result = usuarioService.findDocumentById(txtUser.getText());
        showAlert(result);
        System.out.println(result);
        
        /*
        MongoCollection<Document> collection = database.getCollection("usuarios");
        FindIterable<Document> documents = collection.find();
        
        for (Document document : documents) {
        	System.out.println(document.get("nombres"));
		}*/
    }
    
    private void showAlert(UsuarioBean result) {
    	Alert alert;
    	if(result!=null && result.getNombres()!=null) {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText("Bienvenido: " + result.getNombres());
    	}else {
    		alert = new Alert(AlertType.ERROR);
    	}


        // Mostrar y esperar a que el usuario cierre la alerta
        alert.showAndWait();
    }
    
}
