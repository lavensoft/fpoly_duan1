package duan1.models.product;

import duan1.config.*;
import duan1.interfaces.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.*;
import com.mongodb.client.model.Updates;

import org.bson.types.ObjectId;

public class PromotionModel extends IModel {
    public String _id = "";
    public String title = "";
    public String description = "";
    public Double percent;
    public String startDate = "";
    public String endDate = "";
    public String dateCreated = "";
    public Double points;
    public String product = "";

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        if(document.getString("title") != null) this.title = document.getString("title");
        if(document.getString("email") != null) this.description = document.getString("email");
        if(document.getDouble("percent") != null) this.percent = document.getDouble("percent");
        if(document.getString("startDate") != null) this.startDate = document.getString("startDate");
        if(document.getString("endDate") != null) this.endDate = document.getString("endDate");
        if(document.getString("dateCreated") != null) this.dateCreated = document.getString("dateCreated");
        if(document.getDouble("points") != null) this.points = document.getDouble("points");
        if(document.getString("product") != null) this.product = document.getString("product");
        
        //Put to document
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.title.isEmpty()) put("title", this.title);
        if(!this.description.isEmpty()) put("description", this.description);
        if(percent != null) put("percent", this.percent);
        if(!this.endDate.isEmpty()) put("endDate", this.endDate);
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
        if(points != null) put("points", this.points);
        if(!this.product.isEmpty()) put("product", this.product);
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.title.isEmpty()) put("title", this.title);
        if(!this.description.isEmpty()) put("description", this.description);
        if(percent != null) put("percent", this.percent);
        if(!this.startDate.isEmpty()) put("startDate", this.startDate);
        if(!this.endDate.isEmpty()) put("endDate", this.endDate);
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
        if(points != null) put("points", this.points);
        if(!this.product.isEmpty()) put("product", this.product);

        return this;
    }

    @Override
    public Bson toUpdates() {
        Bson updates = Updates.combine(
            !this.title.isEmpty() ? Updates.set("title", this.title) : new Document(),
            !this.description.isEmpty() ? Updates.set("description", this.description) : new Document(),
            percent != null ? Updates.set("percent", this.percent) : new Document(),
            !this.startDate.isEmpty() ? Updates.set("startDate", this.startDate) : new Document(),
            !this.endDate.isEmpty() ? Updates.set("endDate", this.endDate) : new Document(),
            !this.dateCreated.isEmpty() ? Updates.set("dateCreated", this.dateCreated) : new Document(),
            points != null ? Updates.set("points", this.points) : new Document(),
            !this.product.isEmpty() ? Updates.set("product", this.product) : new Document()
        );

        return updates;
    }
}
