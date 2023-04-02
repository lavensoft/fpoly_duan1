package duan1.models.user;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Updates;

import duan1.interfaces.IModel;

public class PermissionModel extends IModel {
    public String _id = "";
    public String name = "";
    public String order = "";
    public String discount = "";
    public String customer = "";
    public String staff = "";
    public String permission = "";
    public String product = "";
 
    @Override
    public void fromDocument(Document document) {
     this._id = document.getObjectId("_id").toString();
     this.name = document.getString("name");
     this.order = document.getString("order");
     this.discount = document.getString("discount");
     this.customer = document.getString("customer");
     this.staff = document.getString("staff");
     this.permission = document.getString("permission");
     this.product = document.getString("product");
    }
 
    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.name.isEmpty()) put("name", this.name);
        if(!this.order.isEmpty()) put("order", this.order);
        if(!this.discount.isEmpty()) put("discount", this.discount);
        if(!this.customer.isEmpty()) put("customer", this.customer);
        if(!this.staff.isEmpty()) put("staff", this.staff);
        if(!this.permission.isEmpty()) put("permission", this.permission);
        if(!this.product.isEmpty()) put("product", this.product);
 
        return this;
    }
 
    @Override
    public Bson toUpdates() {
         Bson updates = Updates.combine(
            !this.name.isEmpty() ? Updates.set("name", this.name) : new Document(),
            !this.order.isEmpty() ? Updates.set("order", this.order) : new Document(),
            !this.discount.isEmpty() ? Updates.set("discount", this.discount) : new Document(),
            !this.customer.isEmpty() ? Updates.set("customer", this.customer) : new Document(),
            !this.staff.isEmpty() ? Updates.set("staff", this.staff) : new Document(),
            !this.product.isEmpty() ? Updates.set("product", this.product) : new Document(),
            !this.permission.isEmpty() ? Updates.set("permission", this.permission) : new Document()
         );
 
         return updates;
    }
}
