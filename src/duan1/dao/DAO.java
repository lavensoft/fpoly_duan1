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

    public DAO(String collection) {
        if(Database.getDatabase() == null) Database.init();
        this.collection = Database.getDatabase().getCollection(collection);
    }

    public void add(M model) {
        collection.insertOne(model.toDocument());
    }

    public ArrayList<M> getAll(M... model) throws InstantiationException, IllegalAccessException {
        ArrayList<M> docs = new ArrayList<M>();

        Document findQuery = model.length > 0 ? (Document) model[0] : new Document();
        MongoCursor<Document> documents = collection.find(findQuery).cursor();
        
        Log.info("A", DAO.class.getSimpleName());
        
        while(documents.hasNext()) {
            M doc = (M) model.getClass().newInstance();
            System.out.println("A");
            doc.fromDocument(documents.next());
            System.out.println("B");
            docs.add(doc);
        }

        return docs;
    }

    public M get(M model) throws InstantiationException, IllegalAccessException {
        Document document = collection.find(model.toDocument()).first();

        if(document == null) return null; //Not exists
        
        M modelData = (M) model.getClass().newInstance();

        modelData.fromDocument(document);

        return modelData;
    }
}
