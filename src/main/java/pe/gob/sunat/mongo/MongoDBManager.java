package pe.gob.sunat.mongo;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBManager {
	private MongoClient mongoClient;
    private MongoDatabase database;
    
    public MongoDBManager() {
        mongoClient = MongoDBConnector.getMongoClient();
        database = mongoClient.getDatabase(MongoDBConnector.getDatabaseName());
    }

    public void insertData(String collectionName, Document document) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(document);
    }
}
