package pe.gob.sunat.citas.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pe.gob.sunat.citas.bean.CatalogoBean;
import pe.gob.sunat.citas.utils.MongoUtils;
import pe.gob.sunat.mongo.MongoDBConnector;

public class EspecialidadService {
	private MongoClient mongoClient;
    private MongoDatabase database;
    
    public void initialize() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }

	public List<CatalogoBean> getEspecialidades() {
		initialize();
        MongoCollection<Document> collection = database.getCollection("especialidades");
        FindIterable<Document> lstDocument = collection.find();
        
        List<CatalogoBean> lstCatalogo = new ArrayList<CatalogoBean>();
        for (Document document : lstDocument) {
        	CatalogoBean bean = MongoUtils.documentToJavaBean(document, CatalogoBean.class);
        	lstCatalogo.add(bean);
		}
        
        return lstCatalogo;
	}
}
