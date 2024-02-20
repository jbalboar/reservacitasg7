package pe.gob.sunat.citas.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pe.gob.sunat.citas.bean.MedicoBean;
import pe.gob.sunat.citas.bean.PacienteBean;
import pe.gob.sunat.citas.bean.PacienteViewBean;
import pe.gob.sunat.citas.dao.PacienteDao;
import pe.gob.sunat.citas.utils.CitasUtils;
import pe.gob.sunat.citas.utils.MongoUtils;
import pe.gob.sunat.mongo.MongoDBConnector;

public class PacienteDaoImpl implements PacienteDao{
	private MongoClient mongoClient;
    private MongoDatabase database;
    
    public void initialize() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }
    
    @Override
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

    @Override
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

	@Override
	public ObservableList<PacienteViewBean> buscarPacientes(PacienteBean bean) {
		initialize();
        MongoCollection<Document> collection = database.getCollection("pacientes");
        Document filtro = new Document();
        
        if(CitasUtils.esDiferenteNuloVacio(bean.getDni()))
        	filtro.append("dni", bean.getDni());
        
        if(CitasUtils.esDiferenteNuloVacio(bean.getNombres()))
        	filtro.append("nombres", bean.getNombres());
        
        if(CitasUtils.esDiferenteNuloVacio(bean.getPrimerApellido()))
        	filtro.append("primerApellido", bean.getPrimerApellido());
        
        if(CitasUtils.esDiferenteNuloVacio(bean.getSegundoApellido()))
        	filtro.append("segundoApellido", bean.getSegundoApellido());
        
        
        FindIterable<Document> lstDocument = collection.find(filtro);
        
        ObservableList<PacienteViewBean> lstPacientes = FXCollections.observableArrayList();
        
		for (Document document : lstDocument) {
			lstPacientes.add(new PacienteViewBean(document.getString("dni"), document.getString("nombres"),
					document.getString("primerApellido"), document.getString("segundoApellido"),
					CitasUtils.dateToString(document.getDate("fechaNacimiento")), document.getString("email")));
		}
        
		return lstPacientes;
	}
	
	@Override
	public ObservableList<PacienteViewBean> listarPacientes() {
		initialize();
        MongoCollection<Document> collection = database.getCollection("pacientes");
        Document filtro = new Document();        
        
        FindIterable<Document> lstDocument = collection.find(filtro);
        
        ObservableList<PacienteViewBean> lstPacientes = FXCollections.observableArrayList();
        
		for (Document document : lstDocument) {
			lstPacientes.add(new PacienteViewBean(document.getString("dni"), document.getString("nombres"),
					document.getString("primerApellido"), document.getString("segundoApellido"),
					CitasUtils.dateToString(document.getDate("fechaNacimiento")), document.getString("email")));
		}
        
		return lstPacientes;
	}
}
