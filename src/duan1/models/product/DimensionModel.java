package duan1.models.product;

import duan1.config.*;
import duan1.interfaces.*;

import org.bson.Document;
import com.mongodb.client.*;
import org.bson.types.ObjectId;

public class DimensionModel extends Document implements IModel {
    public String _id = "";
    public String title = "";
    public String description = "";
    public Double price;
    public Double salePrice;
    public String images = "";
    public String dateCreated = "";
    public Integer stocks;

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        this.title = document.getString("title");
        this.description = document.getString("email");
        this.price = document.getDouble("price");
        this.salePrice = document.getDouble("salePrice");
        this.images = document.getString("images");
        this.dateCreated = document.getString("dateCreated");
        this.stocks = document.getInteger("stocks");
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.title.isEmpty()) put("title", this.title);
        if(!this.description.isEmpty()) put("description", this.description);
        if(price != null) put("price", this.price);
        if(salePrice != null) put("salePrice", this.salePrice);
        if(!this.images.isEmpty()) put("images", this.images);
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
        if(stocks != null) put("stocks", this.stocks);

        return this;
    }
}
