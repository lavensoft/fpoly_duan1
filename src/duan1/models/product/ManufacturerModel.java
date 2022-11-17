package duan1.models.product;

import duan1.config.*;
import duan1.interfaces.*;

import org.bson.Document;
import com.mongodb.client.*;
import org.bson.types.ObjectId;

public class ManufacturerModel extends Document implements IModel {
    public String _id = "";
    public String title = "";
    public String description = "";
    public String logo = "";
    public String dateCreated = "";

    public static MongoCollection<Document> collection = Database.getDatabase().getCollection(IModel.Collections.PRODUCT_MANUFACTURER.toString());

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        this.title = document.getString("title");
        this.description = document.getString("email");
        this.logo = document.getString("logo");
        this.dateCreated = document.getString("dateCreated");
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.title.isEmpty()) put("title", this.title);
        if(!this.description.isEmpty()) put("description", this.description);
        if(!this.logo.isEmpty()) put("banner", this.logo);

        return this;
    }
}
