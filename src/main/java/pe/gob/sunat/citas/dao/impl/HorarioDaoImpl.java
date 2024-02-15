package pe.gob.sunat.citas.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pe.gob.sunat.citas.bean.EstadisticaBean;
import pe.gob.sunat.citas.bean.MedicoBean;
import pe.gob.sunat.citas.dao.HorarioDao;
import pe.gob.sunat.citas.utils.CitasUtils;
import pe.gob.sunat.citas.utils.MongoUtils;
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

	@Override
	public List<EstadisticaBean> obtenerEstadisticas() {
		initialize();
		MongoCollection<Document> collection = database.getCollection("horariosReservados");

		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
				new Document("$lookup",
						new Document("from", "medicos").append("localField", "codigoMedico")
								.append("foreignField", "codigo").append("as", "resultado")),
				new Document("$unwind", "$resultado"),
				new Document("$project", new Document("resultado.nombres", 1).append("resultado.primerApellido", 1)
						.append("resultado.segundoApellido", 1).append("total", new Document("$size", "$horarios"))),
				new Document("$group",
						new Document("_id",
								new Document("$concat",
										Arrays.asList("$resultado.nombres", " ", "$resultado.primerApellido", " ",
												"$resultado.segundoApellido")))
								.append("totalCitas", new Document("$sum", "$total")))));

		List<EstadisticaBean> lstEstadisticaBean = new ArrayList<EstadisticaBean>();
		
		for (Document document : result) {
			EstadisticaBean bean = new EstadisticaBean();
			bean.setMedico(document.getString("_id"));
			bean.setCitas(document.getInteger("totalCitas"));
			lstEstadisticaBean.add(bean);
		}

		return lstEstadisticaBean;
	}
    
    
}
