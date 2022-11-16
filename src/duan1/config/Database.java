package duan1.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Database {
    private static MongoClientURI uri;
    private static MongoClient client;
    private static MongoDatabase mongodb;

    public static void init() {
        uri = new MongoClientURI("mongodb+srv://lavensoft:irUV9ikbAtcgdPQb@cluster0.51kmh.mongodb.net/duan1?retryWrites=true&w=majority");
        client = new MongoClient(uri);
        mongodb = client.getDatabase("duan1");
        System.out.println("====== [ DATABASE CONNECT SUCCESSFULLY ] ======");
    }

    public static MongoDatabase getDatabase() {
        return mongodb;
    }
}
