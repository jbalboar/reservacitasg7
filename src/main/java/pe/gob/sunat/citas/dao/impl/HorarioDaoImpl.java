package pe.gob.sunat.citas.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pe.gob.sunat.citas.bean.MedicoBean;
import pe.gob.sunat.citas.dao.HorarioDao;
import pe.gob.sunat.citas.utils.CitasUtils;
import pe.gob.sunat.mongo.MongoDBConnector;

public class HorarioDaoImpl implements HorarioDao{
	private MongoClient mongoClient;
    private MongoDatabase database;
    
    public void initialize() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }

    @Override
	public List<String> obtenerHorarioDisponible(LocalDate localDate, MedicoBean bean) {
		initialize();
        MongoCollection<Document> collection = database.getCollection("horariosReservados");

        Document filtro = new Document();
        filtro.append("codigoMedico", bean.getCodigo());
        filtro.append("fecha", CitasUtils.formatDate(localDate));
        
        Document document = collection.find(filtro).first();
  
		return CitasUtils.getHorarioLibre(document);
	}
    
    
}
