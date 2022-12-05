package duan1.models.customer;

import java.util.Date;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Updates;

import duan1.interfaces.IModel;

public class CustomerModel extends IModel {
   public String _id = "";
   public String phone = "";
   public String name = "";
   public String dateCreated = "";
   public Double points;

   @Override
   public void fromDocument(Document document) {
    this._id = document.getObjectId("_id").toString();
    this.phone = document.getString("phone");
    this.name = document.getString("name");
    this.dateCreated = document.getString("dateCreated");
    this.points = document.getDouble("points");
   }

   @Override
   public Document toDocument() {
       if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
       if(!this.phone.isEmpty()) put("phone", this.phone);
       if(!this.name.isEmpty()) put("name", this.name);
       if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
       if(points != null) put("points", this.points);

       return this;
   }

   @Override
   public Bson toUpdates() {
        Bson updates = Updates.combine(
            !this.phone.isEmpty() ? Updates.set("phone", this.phone) : new Document(),
            !this.name.isEmpty() ? Updates.set("name", this.name) : new Document(),
            !this.dateCreated.isEmpty() ? Updates.set("dateCreated", this.dateCreated) : new Document(),
            points != null ? Updates.set("points", this.points) : new Document()
        );

        return updates;
   }
}
