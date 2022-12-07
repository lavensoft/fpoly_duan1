package duan1.models.product;

import java.util.Date;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Updates;

import duan1.interfaces.IModel;

public class DimensionPromotionModel extends IModel {
    public String _id = "";
    public String dimension = "";
    public String promotion = "";

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        this.dimension = document.getString("dimension");
        this.promotion = document.getString("promotion");
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.dimension.isEmpty()) put("dimension", this.dimension);
        if(!this.promotion.isEmpty()) put("promotion", this.promotion);

        return this;
    }

    @Override
    public Bson toUpdates() {
            Bson updates = Updates.combine(
                !this.dimension.isEmpty() ? Updates.set("dimension", this.dimension) : new Document(),
                !this.promotion.isEmpty() ? Updates.set("promotion", this.promotion) : new Document()
            );

            return updates;
    }
}
