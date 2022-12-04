package duan1.models.product;

import duan1.config.*;
import duan1.interfaces.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.*;
import com.mongodb.client.model.Updates;

import org.bson.types.ObjectId;

public class DimensionModel extends Document implements IModel {
    public String _id = "";
    public String name = "";
    public String description = "";
    public Double price;
    public Double salePrice;
    public String banner = "";
    public String dateCreated = "";
    public Integer stocks;
    public String product = "";

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        this.name = document.getString("name");
        this.description = document.getString("description");
        this.price = document.getDouble("price");
        this.salePrice = document.getDouble("salePrice");
        this.banner = document.getString("banner");
        this.dateCreated = document.getString("dateCreated");
        this.stocks = document.getInteger("stocks");
        this.product =  document.getString("product");
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.name.isEmpty()) put("name", this.name);
        if(!this.description.isEmpty()) put("description", this.description);
        if(price != null) put("price", this.price);
        if(salePrice != null) put("salePrice", this.salePrice);
        if(!this.banner.isEmpty()) put("banner", this.banner);
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
        if(stocks != null) put("stocks", this.stocks);
        if(!this.product.isEmpty()) put("product", this.product);

        return this;
    }

    @Override
    public Bson toUpdates() {
         Bson updates = Updates.combine(
             !this.name.isEmpty() ? Updates.set("name", this.name) : new Document(),
             !this.description.isEmpty() ? Updates.set("description", this.description) : new Document(),
             price != null ? Updates.set("price", this.price) : new Document(),
             salePrice != null ? Updates.set("salePrice", this.salePrice) : new Document(),
             !this.banner.isEmpty() ? Updates.set("banner", this.banner) : new Document(),
             !this.dateCreated.isEmpty() ? Updates.set("dateCreated", this.dateCreated) : new Document(),
             stocks != null ? Updates.set("stocks", this.stocks) : new Document(),
             !this.product.isEmpty() ? Updates.set("product", this.product) : new Document()
         );
 
         return updates;
    }
}
