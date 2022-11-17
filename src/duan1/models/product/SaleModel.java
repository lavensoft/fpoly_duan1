package duan1.models.product;

import duan1.config.*;
import duan1.interfaces.*;

import org.bson.Document;
import com.mongodb.client.*;
import org.bson.types.ObjectId;

public class SaleModel extends Document implements IModel {
    public String _id = "";
    public String title = "";
    public String description = "";
    public Double percent;
    public String startDate = "";
    public String endDate = "";
    public String dateCreated = "";
    public Integer stocks;
    public String product = "";

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        this.title = document.getString("title");
        this.description = document.getString("email");
        this.percent = document.getDouble("percent");
        this.startDate = document.getString("startDate");
        this.endDate = document.getString("endDate");
        this.dateCreated = document.getString("dateCreated");
        this.stocks = document.getInteger("stocks");
        this.product = document.getString("product");
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.title.isEmpty()) put("title", this.title);
        if(!this.description.isEmpty()) put("description", this.description);
        if(percent != null) put("price", this.percent);
        if(!this.startDate.isEmpty()) put("startDate", this.startDate);
        if(!this.endDate.isEmpty()) put("endDate", this.endDate);
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
        if(stocks != null) put("stocks", this.stocks);
        if(!this.product.isEmpty()) put("product", this.product);

        return this;
    }
}
