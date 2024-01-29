package pe.gob.sunat.citas;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.fxml.FXML;
import pe.gob.sunat.mongo.MongoDBConnector;

public class PrimaryController {

	private MongoClient mongoClient;
    private MongoDatabase database;

    public void initialize() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void validarIngreso() throws IOException {
        System.out.println("pruebaaa");
        
        MongoCollection<Document> collection = database.getCollection("usuarios");
        FindIterable<Document> documents = collection.find();
        
        for (Document document : documents) {
        	System.out.println(document.get("nombres"));
		}
    }
    
}
