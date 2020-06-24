package kneu.ishi501.service;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.HashMap;
import java.util.Map;

public class DbService {

    public static Document document;

    private MongoCollection openDbAndCollection(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("client");
        MongoCollection collection = db.getCollection("scoringClient");
        return collection;
    }

    public Document checkCorrectInput(String lastName, String firstName, String patronymic){
        document = null;
        Map<String, String> map = new HashMap<>();
        map.put("lastName", lastName);
        map.put("firstName", firstName);
        map.put("patronymic", patronymic);
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.putAll(map);
        System.out.println(lastName + " " + firstName + " " + patronymic);

        MongoCursor<Document> cursor = openDbAndCollection().find(basicDBObject).iterator();
        while (cursor.hasNext()){
            document = cursor.next();
            System.out.println(document);
        }
        System.out.println(document);
        return document;
    }
}
