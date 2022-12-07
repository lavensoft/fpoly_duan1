package duan1.models.order;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Updates;

import duan1.interfaces.IModel;

public class OrderDimensionModel extends IModel {
   public String _id = "";
   public String order = "";
   public String product = "";
   public Integer count;
   public Double discount;

   @Override
   public void fromDocument(Document document) {
    this._id = document.getObjectId("_id").toString();
    this.order = document.getString("order");
    this.product = document.getString("product");
    this.count = document.getInteger("count");
    this.discount = document.getDouble("discount");
   }

   @Override
   public Document toDocument() {
       if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
       if(!this.order.isEmpty()) put("order", this.order);
       if(!this.product.isEmpty()) put("product", this.product);
       if(count != null) put("count", this.count);
       if(discount != null) put("discount", this.discount);

       return this;
   }

   @Override
   public Bson toUpdates() {
        Bson updates = Updates.combine(
            !this.order.isEmpty() ? Updates.set("order", this.order) : new Document(),
            !this.product.isEmpty() ? Updates.set("product", this.product) : new Document(),
            count != null ? Updates.set("count", this.count) : new Document(),
            discount != null ? Updates.set("discount", this.discount) : new Document()
        );

        return updates;
   }
}
