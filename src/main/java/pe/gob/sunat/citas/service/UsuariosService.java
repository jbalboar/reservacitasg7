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
    
    public UsuarioBean autenticarUsuario(String dni, String password) {
    	initialize();
        MongoCollection<Document> collection = database.getCollection("usuarios");

        Document filtro = new Document();
        filtro.append("dni", dni);
        filtro.append("password", password);
        filtro.append("activo", true);
        
        Document resultDocument = collection.find(filtro).first();

        if (resultDocument != null) {
            return MongoUtils.documentToJavaBean(resultDocument, UsuarioBean.class);
        }

        return null;
    }
}
