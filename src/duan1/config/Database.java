package duan1.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import duan1.utils.Log;

import org.bson.Document;
import org.slf4j.LoggerFactory;

public class Database {
    private static MongoClientURI uri;
    private static MongoClient client;
    private static MongoDatabase mongodb;

    public static void init() {
        try {
            //DISABLE LOG MONGODB
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
            rootLogger.setLevel(Level.ERROR);

            //CONNECT TO DATABASE
            uri = new MongoClientURI("mongodb+srv://lavensoft:irUV9ikbAtcgdPQb@cluster0.51kmh.mongodb.net/duan1?retryWrites=true&w=majority");
            client = new MongoClient(uri);
            mongodb = client.getDatabase("duan1");

            Log.success("[ DATABASE CONNECT SUCCESSFULLY ]", Database.class.getName());
        }catch(Exception e) {
            Log.error(e);
        }
    }

    public static MongoDatabase getDatabase() {
        return mongodb;
    }
}
