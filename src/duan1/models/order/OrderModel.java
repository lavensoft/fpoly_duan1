package duan1.models.order;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Updates;

import duan1.interfaces.IModel;

public class OrderModel extends IModel {
   public String _id = "";
   public String author = "";
   public String customer = "";
   public String description = "";
   public String dateCreated = "";
   public String paymentMethod = "";
   public String paymentStatus = "";
   public String paymentOrderId = "";

   @Override
   public void fromDocument(Document document) {
    this._id = document.getObjectId("_id").toString();
    this.author = document.getString("author");
    this.customer = document.getString("customer");
    this.description = document.getString("description");
    this.dateCreated = document.getString("dateCreated");
    this.paymentMethod = document.getString("paymentMethod");
    this.paymentStatus = document.getString("paymentStatus");
    this.paymentOrderId = document.getString("paymentOrderId");
   }

   @Override
   public Document toDocument() {
       if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
       if(!this.author.isEmpty()) put("author", this.author);
       if(!this.customer.isEmpty()) put("customer", this.customer);
       if(!this.description.isEmpty()) put("description", this.description);
       if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
       if(!this.paymentMethod.isEmpty()) put("paymentMethod", this.paymentMethod);
       if(!this.paymentStatus.isEmpty()) put("paymentStatus", this.paymentStatus);
       if(!this.paymentOrderId.isEmpty()) put("paymentOrderId", this.paymentOrderId);

       return this;
   }

   @Override
   public Bson toUpdates() {
        Bson updates = Updates.combine(
            !this.author.isEmpty() ? Updates.set("author", this.author) : new Document(),
            !this.customer.isEmpty() ? Updates.set("customer", this.customer) : new Document(),
            !this.description.isEmpty() ? Updates.set("description", this.description) : new Document(),
            !this.dateCreated.isEmpty() ? Updates.set("dateCreated", this.dateCreated) : new Document(),
            !this.paymentMethod.isEmpty() ? Updates.set("paymentMethod", this.paymentMethod) : new Document(),
            !this.paymentStatus.isEmpty() ? Updates.set("paymentStatus", this.paymentStatus) : new Document(),
            !this.paymentOrderId.isEmpty() ? Updates.set("paymentOrderId", this.paymentOrderId) : new Document()
        );

        return updates;
   }
}
