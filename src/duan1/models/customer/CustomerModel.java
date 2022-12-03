package duan1.models.customer;

import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;
import duan1.interfaces.IModel;

public class CustomerModel extends Document implements IModel {
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
}
