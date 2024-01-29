package pe.gob.sunat.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnector {
	private static final String DATABASE_NAME = "dbCitas";
    private static final String CONNECTION_STRING = "mongodb+srv://root:root@cluster0.b7qks3q.mongodb.net/?retryWrites=true&w=majority"; 

    private static MongoClient mongoClient;
    
    static {
        mongoClient = MongoClients.create(CONNECTION_STRING);
    }
   
    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }
}
