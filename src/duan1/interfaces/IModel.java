package duan1.interfaces;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;

public abstract class IModel extends Document {
    public String _id = "";
    public MongoCollection<Document> collection = null;
    
    public abstract void fromDocument(Document document);
    public abstract Document toDocument();
    public abstract Bson toUpdates();
}
