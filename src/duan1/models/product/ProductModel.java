package duan1.models.product;

import duan1.config.*;
import duan1.interfaces.IModel;

import java.util.ArrayList;

import org.bson.Document;
import com.mongodb.client.*;
import org.bson.types.ObjectId;

public class ProductModel extends Document implements IModel {
    public String _id = "";
    public String name = "";
    public String description = "";
    public String banner = "";
    public String dateCreated = "";
    public String author = "";
    public ArrayList<String> dimensions = new ArrayList<String>();
    public String manufacturer = "";

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        this.name = document.getString("name");
        this.description = document.getString("email");
        this.banner = document.getString("banner");
        this.dateCreated = document.getString("dateCreated");
        this.author = document.getString("author");
        this.dimensions = (ArrayList<String>)document.get("dimensions");
        this.manufacturer = document.getString("manufacturer");
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.name.isEmpty()) put("name", this.name);
        if(!this.description.isEmpty()) put("description", this.description);
        if(!this.banner.isEmpty()) put("banner", this.banner);
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
        if(!this.author.isEmpty()) put("author", this.author);
        if(!this.dimensions.isEmpty()) put("dimensions", this.dimensions);
        if(!this.manufacturer.isEmpty()) put("manufacturer", this.manufacturer);

        return this;
    }
}
