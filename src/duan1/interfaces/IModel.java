package duan1.interfaces;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;

public interface IModel {
    public String _id = "";
    public MongoCollection<Document> collection = null;
    
    public void fromDocument(Document document);
    public Document toDocument();
    public Bson toUpdates();
}
