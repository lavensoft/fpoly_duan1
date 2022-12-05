package duan1.models.product;

import duan1.config.*;
import duan1.interfaces.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.*;
import com.mongodb.client.model.Updates;

import org.bson.types.ObjectId;

public class ManufacturerModel extends IModel {
    public String _id = "";
    public String title = "";
    public String description = "";
    public String logo = "";
    public String dateCreated = "";

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
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);

        return this;
    }

    @Override
    public Bson toUpdates() {
         Bson updates = Updates.combine(
            !this.title.isEmpty() ? Updates.set("title", this.title) : new Document(),
            !this.description.isEmpty() ? Updates.set("description", this.description) : new Document(),
            !this.logo.isEmpty() ? Updates.set("logo", this.logo) : new Document(),
            !this.dateCreated.isEmpty() ? Updates.set("dateCreated", this.dateCreated) : new Document()
         );
 
         return updates;
    }
}
