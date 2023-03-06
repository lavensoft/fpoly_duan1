package duan1.models.product;

import duan1.config.*;
import duan1.interfaces.IModel;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.*;
import com.mongodb.client.model.Updates;

import org.bson.types.ObjectId;

public class ProductModel extends IModel {
    public String _id = "";
    public String name = "";
    public String description = "";
    public String banner = "";
    public String dateCreated = "";
    public String releaseYear = "";
    public String author = "";
    public ArrayList<String> dimensions = new ArrayList<String>();
    public String manufacturer = "";

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        if(document.getString("name") != null) this.name = document.getString("name");
        if(document.getString("description") != null) this.description = document.getString("description");
        this.banner = document.getString("banner");
        this.dateCreated = document.getString("dateCreated");
        this.releaseYear = document.getString("releaseYear");
        if(document.getString("author") != null) this.author = document.getString("author");
        if(document.get("dimensions") != null) this.dimensions = (ArrayList<String>)document.get("dimensions");
        this.manufacturer = document.getString("manufacturer");
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.name.isEmpty()) put("name", this.name);
        if(!this.description.isEmpty()) put("description", this.description);
        if(!this.banner.isEmpty()) put("banner", this.banner);
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
        if(!this.releaseYear.isEmpty()) put("releaseYear", this.releaseYear);
        if(!this.author.isEmpty()) put("author", this.author);
        if(!this.dimensions.isEmpty()) put("dimensions", this.dimensions);
        if(!this.manufacturer.isEmpty()) put("manufacturer", this.manufacturer);

        return this;
    }

    @Override
    public Bson toUpdates() {
        Bson updates = Updates.combine(
            !this.name.isEmpty() ? Updates.set("name", this.name) : new Document(),
            !this.description.isEmpty() ? Updates.set("description", this.description) : new Document(),
            !this.banner.isEmpty() ? Updates.set("banner", this.banner) : new Document(),
            !this.dateCreated.isEmpty() ? Updates.set("dateCreated", this.dateCreated) : new Document(),
            !this.releaseYear.isEmpty() ? Updates.set("releaseYear", this.releaseYear) : new Document(),
            !this.author.isEmpty() ? Updates.set("author", this.author) : new Document(),
            !this.dimensions.isEmpty() ? Updates.set("dimensions", this.dimensions) : new Document(),
            !this.manufacturer.isEmpty() ? Updates.set("manufacturer", this.manufacturer) : new Document()
        );

        return updates;
    }
}
