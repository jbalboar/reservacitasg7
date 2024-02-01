package pe.gob.sunat.citas.service;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import pe.gob.sunat.citas.bean.PacienteBean;
import pe.gob.sunat.citas.bean.UsuarioBean;
import pe.gob.sunat.citas.utils.MongoUtils;
import pe.gob.sunat.mongo.MongoDBConnector;

public class PacienteService {
	private MongoClient mongoClient;
    private MongoDatabase database;
    
    public void initialize() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }
    
    public PacienteBean registrarPaciente(PacienteBean bean) {
    	initialize();
        MongoCollection<Document> collection = database.getCollection("pacientes");

        Document datos = new Document();
        datos.append("dni", bean.getDni());
        datos.append("nombres", bean.getNombres());
        datos.append("primerApellido", bean.getPrimerApellido());
        datos.append("segundoApellido", bean.getSegundoApellido());
        datos.append("fechaNacimiento", bean.getFechaNacimiento());
        datos.append("email", bean.getEmail());
        datos.append("tieneSeguro", bean.isTieneSeguro());    
        InsertOneResult result = collection.insertOne(datos);       
        bean.setId(MongoUtils.extractObjectId(result.getInsertedId()));

        return bean;
    }

	public PacienteBean obtenerDatosPaciente(String dni) {
		initialize();
        MongoCollection<Document> collection = database.getCollection("pacientes");
        
        Document filtro = new Document();
        filtro.append("dni", dni);
        
        Document resultDocument = collection.find(filtro).first();

        if (resultDocument != null) {
            return MongoUtils.documentToJavaBean(resultDocument, PacienteBean.class);
        }

        return null;
	}
}
