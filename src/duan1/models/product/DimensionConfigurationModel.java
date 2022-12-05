package duan1.models.product;

import java.util.Date;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Updates;

import duan1.interfaces.IModel;

public class DimensionConfigurationModel extends Document implements IModel {
    public String _id = "";
    public String dimension = "";
    public String configuration = "";

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        this.dimension = document.getString("dimension");
        this.configuration = document.getString("configuration");
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.dimension.isEmpty()) put("dimension", this.dimension);
        if(!this.configuration.isEmpty()) put("configuration", this.configuration);

        return this;
    }

    @Override
    public Bson toUpdates() {
            Bson updates = Updates.combine(
                !this.dimension.isEmpty() ? Updates.set("dimension", this.dimension) : new Document(),
                !this.configuration.isEmpty() ? Updates.set("configuration", this.configuration) : new Document()
            );

            return updates;
    }
}
