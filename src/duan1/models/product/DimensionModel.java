package duan1.models.product;

import duan1.config.*;
import duan1.interfaces.*;

import org.bson.Document;
import com.mongodb.client.*;
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
        this.description = document.getString("email");
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
        if(this.description != null || !this.description.isEmpty()) put("description", this.description);
        if(price != null) put("price", this.price);
        if(salePrice != null) put("salePrice", this.salePrice);
        if(!this.banner.isEmpty()) put("banner", this.banner);
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
        if(stocks != null) put("stocks", this.stocks);
        if(!this.product.isEmpty()) put("product", this.product);

        return this;
    }
}
