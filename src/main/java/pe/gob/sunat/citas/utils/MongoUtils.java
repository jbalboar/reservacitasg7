package pe.gob.sunat.citas.utils;

import java.lang.reflect.Field;

import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class MongoUtils {
	private static CodecRegistry codecRegistry;

	static {
		codecRegistry = createCodecRegistry();
	}

	public static CodecRegistry getMongoCodecRegistry() {
		return codecRegistry;
	}

	private static CodecRegistry createCodecRegistry() {
		PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
		return org.bson.codecs.configuration.CodecRegistries.fromRegistries(
				org.bson.codecs.configuration.CodecRegistries.fromCodecs(),
				org.bson.codecs.configuration.CodecRegistries.fromProviders(pojoCodecProvider));
	}

	public static <T> T documentToJavaBean(Document document, Class<T> clazz) {
		T instance = null;
		try {
			instance = clazz.getDeclaredConstructor().newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if (field.isAnnotationPresent(BsonId.class)) {
					fieldName = "_id"; // MongoDB _id field
				}
				if (document.containsKey(fieldName)) {
					field.set(instance, document.get(fieldName));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public static ObjectId extractObjectId(BsonValue bsonValue) {
        if (bsonValue instanceof BsonObjectId) {
            return ((BsonObjectId) bsonValue).getValue();
        } else {
            throw new IllegalArgumentException("El BsonValue no es un ObjectId");
        }
    }
}
