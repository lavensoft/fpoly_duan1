package duan1.dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import duan1.config.Database;
import duan1.interfaces.*;
import duan1.utils.Log;

public class DAO<M extends IModel> {
    private MongoCollection<Document> collection = null;
    private M type;

    public DAO(String collection, M type) {
        if(Database.getDatabase() == null) Database.init();
        this.collection = Database.getDatabase().getCollection(collection);
        this.type = type;
    }

    public void add(M model) throws Exception {
        try {
            // Log.info("ADDING TO DB", type.getClass().getSimpleName());

            collection.insertOne(model.toDocument());
        }catch(Exception e) {
            Log.error(e);
            throw e;
        }
    }

    public ArrayList<M> getAll(M... queries) throws InstantiationException, IllegalAccessException, Exception {
        try {
            // Log.info("GET ALL FROM DB", type.getClass().getSimpleName());

            ArrayList<M> docs = new ArrayList<M>();

            Document findQuery = queries.length > 0 ? (Document) queries[0] : new Document();
            MongoCursor<Document> documents = collection.find(findQuery).cursor();
            
            while(documents.hasNext()) {
                M doc = (M) type.getClass().newInstance();
                doc.fromDocument(documents.next());
                docs.add(doc);
            }

            return docs;
        }catch(Exception e) {
            Log.error(e);
            throw e;
        }
    }

    public M get(M model) throws InstantiationException, IllegalAccessException, Exception {
        try {
            // Log.info("GET FROM DB", type.getClass().getSimpleName());

            Document document = collection.find(model.toDocument()).first();

            if(document == null) return null; //Not exists
            
            M modelData = (M) type.getClass().newInstance();

            modelData.fromDocument(document);

            return modelData;
        }catch(Exception e) {
            Log.error(e);
            throw e;
        }
    }
}
