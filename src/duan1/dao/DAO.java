package duan1.dao;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

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

    public void updateOne(M query, M data) throws Exception {
        try {
            Bson updates = data.toUpdates();
            collection.updateOne(query.toDocument(), updates, new UpdateOptions().upsert(true));
        }catch(Exception e) {
            Log.error(e);
            throw e;
        }
    }

    public void updateMany(M query, M data) throws Exception {
        try {
            Bson updates = data.toUpdates();
            collection.updateMany(query.toDocument(), updates, new UpdateOptions().upsert(true));
        }catch(Exception e) {
            Log.error(e);
            throw e;
        }
    }

    public ArrayList<M> search(String value) throws Exception {
        ArrayList<M> docs = new ArrayList<M>();
        TextSearchOptions options = new TextSearchOptions().caseSensitive(false);
        Bson filter = Filters.text(value, options);
        MongoCursor<Document> documents = collection.find(filter).cursor();
            
        while(documents.hasNext()) {
            M doc = (M) type.getClass().newInstance();
            doc.fromDocument(documents.next());
            docs.add(doc);
        }

        return docs;
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

    public void deleteOne(M query) throws InstantiationException, IllegalAccessException, Exception {
        try {
            collection.deleteOne(query.toDocument());
        }catch(Exception e) {
            Log.error(e);
            throw e;
        }
    }

    public void deleteMany(M query) throws InstantiationException, IllegalAccessException, Exception {
        try {
            collection.deleteMany(query.toDocument());
        }catch(Exception e) {
            Log.error(e);
            throw e;
        }
    }
}
