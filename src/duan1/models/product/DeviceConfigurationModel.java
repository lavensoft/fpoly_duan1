package duan1.models.product;

import java.util.Date;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Updates;

import duan1.interfaces.IModel;

public class DeviceConfigurationModel extends Document implements IModel {
   public String _id = "";
   public String key = "";
   public String value = "";
   public String dateCreated = "";

   @Override
   public void fromDocument(Document document) {
    this._id = document.getObjectId("_id").toString();
    this.key = document.getString("key");
    this.value = document.getString("value");
    this.dateCreated = document.getString("dateCreated");
   }

   @Override
   public Document toDocument() {
       if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
       if(!this.key.isEmpty()) put("key", this.key);
       if(!this.value.isEmpty()) put("value", this.value);
       if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);

       return this;
   }

   @Override
   public Bson toUpdates() {
        Bson updates = Updates.combine(
            !this.key.isEmpty() ? Updates.set("key", this.key) : new Document(),
            !this.value.isEmpty() ? Updates.set("value", this.value) : new Document(),
            !this.dateCreated.isEmpty() ? Updates.set("dateCreated", this.dateCreated) : new Document()
        );

        return updates;
   }
}
