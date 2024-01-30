package pe.gob.sunat.citas.service;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pe.gob.sunat.citas.bean.UsuarioBean;
import pe.gob.sunat.citas.utils.MongoUtils;
import pe.gob.sunat.mongo.MongoDBConnector;

public class UsuariosService {
	private MongoClient mongoClient;
    private MongoDatabase database;

    public void initialize() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }
    
    public UsuarioBean findDocumentById(String id) {
    	initialize();
        MongoCollection<Document> collection = database.getCollection("usuarios");

        Document query = new Document("dni", id);
        Document resultDocument = collection.find(query).first();

        if (resultDocument != null) {
            return documentToJavaBean(resultDocument, UsuarioBean.class);
        }

        return null;
    }
    
    private <T> T documentToJavaBean(Document document, Class<T> clazz) {
    	
    	return MongoUtils.documentToJavaBean(document, clazz);
    	
    }
}
