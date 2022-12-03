package duan1.models.order;

import org.bson.Document;
import org.bson.types.ObjectId;
import duan1.interfaces.IModel;

public class OrderModel extends Document implements IModel {
   public String _id = "";
   public String author = "";
   public String customer = "";
   public String description = "";
   public String dateCreated = "";
   public String paymentMethod = "";

   @Override
   public void fromDocument(Document document) {
    this._id = document.getObjectId("_id").toString();
    this.author = document.getString("author");
    this.customer = document.getString("customer");
    this.description = document.getString("description");
    this.dateCreated = document.getString("dateCreated");
    this.paymentMethod = document.getString("paymentMethod");
   }

   @Override
   public Document toDocument() {
       if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
       if(!this.author.isEmpty()) put("author", this.author);
       if(!this.customer.isEmpty()) put("customer", this.customer);
       if(!this.description.isEmpty()) put("description", this.description);
       if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
       if(!this.paymentMethod.isEmpty()) put("paymentMethod", this.paymentMethod);

       return this;
   }
}
