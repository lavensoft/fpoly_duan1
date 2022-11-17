package duan1.abstracts;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import duan1.interfaces.*;

public class DAO<M extends IModel> {
    private Class<M> clazz;

    public void add(M model) {
        model.collection.insertOne(model.toDocument());
    }

    public ArrayList<M> getAll(M... model) throws InstantiationException, IllegalAccessException {
        ArrayList<M> docs = new ArrayList<M>();

        Document findQuery = model.length > 0 ? (Document) model[0] : new Document();
        MongoCursor<Document> documents = M.collection.find(findQuery).cursor();

        while(documents.hasNext()) {
            M doc = clazz.newInstance();
            doc.fromDocument(documents.next());
            docs.add(doc);
        }

        return docs;
    }

    public M get(M model) throws InstantiationException, IllegalAccessException {
        Document document = M.collection.find(model.toDocument()).first();

        if(document == null) return null; //Not exists
        
        M modelData = clazz.newInstance();

        modelData.fromDocument(document);

        return modelData;
    }
}
