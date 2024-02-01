package pe.gob.sunat.citas.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pe.gob.sunat.citas.bean.MedicoBean;
import pe.gob.sunat.citas.utils.MongoUtils;
import pe.gob.sunat.mongo.MongoDBConnector;

public class MedicoService {
	private MongoClient mongoClient;
    private MongoDatabase database;
    
    public void initialize() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }

	public List<MedicoBean> getMedicosPorEspecialidad(String codigo) {
		initialize();
        MongoCollection<Document> collection = database.getCollection("medicos");
        Document filtro = new Document();
        filtro.append("especialidad.codigo", codigo);
        FindIterable<Document> lstDocument = collection.find(filtro);
        
        List<MedicoBean> lstMedicos = new ArrayList<MedicoBean>();
        for (Document document : lstDocument) {
        	MedicoBean bean = MongoUtils.documentToJavaBean(document, MedicoBean.class);
        	lstMedicos.add(bean);
		}       
        
        return lstMedicos;
	}
    
    
}
