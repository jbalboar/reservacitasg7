package pe.gob.sunat.citas.dao.impl;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;

import pe.gob.sunat.citas.bean.CitasBean;
import pe.gob.sunat.citas.bean.ReservaCitaBean;
import pe.gob.sunat.citas.dao.ReservaDao;
import pe.gob.sunat.citas.utils.MongoUtils;
import pe.gob.sunat.mongo.MongoDBConnector;

public class ReservaDaoImpl implements ReservaDao {

	private MongoClient mongoClient;
    private MongoDatabase database;
    
    public void initialize() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }
    
    @Override
	public ReservaCitaBean grabarCita(ReservaCitaBean reserva) {
		initialize();
		MongoCollection<Document> collection = database.getCollection("citas");
		
		Document datos = new Document();
        datos.append("dni", reserva.getDni());
        
        /*Primero buscamos si alguna vez el paciente registró una cita*/
        Document resultDocument = collection.find(datos).first();
        
        CitasBean citasBean = reserva.getCita().get(0);
        
        if(resultDocument!=null) {
        	//Si encuentra, entonces agregamos el elemento a su historial de citas
			collection.updateOne(datos, Updates.push("cita",
					new Document("medico",
							new Document("codigo", citasBean.getMedico().getCodigo()).append("descripcion",
									citasBean.getMedico().getDescripcion()))
							.append("especialidad",
									new Document("codigo", citasBean.getEspecialidad().getCodigo())
											.append("descripcion", citasBean.getEspecialidad().getDescripcion()))
							.append("fecha", citasBean.getFecha()).append("hora", citasBean.getHora())
							.append("estado", citasBean.getEstado())));
            
        }else {
        	
        	//Si nunca ha registrado una cita, se genera el documento nuevo
			datos.append("cita",
					Arrays.asList(new Document("medico",
							new Document("codigo", citasBean.getMedico().getCodigo()).append("descripcion",
									citasBean.getMedico().getDescripcion()))
							.append("especialidad",
									new Document("codigo", citasBean.getEspecialidad().getCodigo())
											.append("descripcion", citasBean.getEspecialidad().getDescripcion()))
							.append("fecha", citasBean.getFecha()).append("hora", citasBean.getHora())
							.append("estado", citasBean.getEstado())));

        	InsertOneResult result = collection.insertOne(datos); 
        	reserva.setId(MongoUtils.extractObjectId(result.getInsertedId()));
        }
        
        
        /*Una vez que agreguemos la cita, se separa el medico en el horario indicado*/
        MongoCollection<Document> collectionMedico	 = database.getCollection("horariosReservados");
        Document dMedico =  new Document();
        dMedico.append("codigoMedico", reserva.getCita().get(0).getMedico().getCodigo());
        dMedico.append("fecha", reserva.getCita().get(0).getFecha());
        
        /*buscamos si alguna vez el medico fue separado para un dia X*/
        Document resultMedico = collectionMedico.find(dMedico).first();
        
        if(resultMedico!=null) {
        	//Si encuentra, entonces agregamos el horario reservado
            collectionMedico.updateOne(dMedico, Updates.push("horarios", reserva.getCita().get(0).getHora()));
        }else {
        	//Si nunca ha sido separado, se registra
        	dMedico.append("horarios", Arrays.asList(reserva.getCita().get(0).getHora()) ); 
        	collectionMedico.insertOne(dMedico); 
        }
   
		return reserva;
		
	}

}
