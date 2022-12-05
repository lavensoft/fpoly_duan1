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
    public String ram = "";
    public String rom = "";
    public String pin = "";
    public String camera = "";
    public String display = "";
    public String sim = "";
    public String manufacturer = "";
    public String releaseYear = "";
        
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
        this.ram =  document.getString("ram");
        this.rom =  document.getString("rom");
        this.pin =  document.getString("pin");
        this.camera =  document.getString("camera");
        this.display =  document.getString("display");
        this.sim =  document.getString("sim");
        this.manufacturer =  document.getString("manufacturer");
        this.releaseYear =  document.getString("releaseYear");
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
        if(!this.ram.isEmpty()) put("ram", this.ram);
        if(!this.rom.isEmpty()) put("rom", this.rom);
        if(!this.pin.isEmpty()) put("pin", this.pin);
        if(!this.camera.isEmpty()) put("camera", this.camera);
        if(!this.display.isEmpty()) put("display", this.display);
        if(!this.sim.isEmpty()) put("sim", this.sim);
        if(!this.manufacturer.isEmpty()) put("manufacturer", this.manufacturer);
        if(!this.releaseYear.isEmpty()) put("releaseYear", this.releaseYear);

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
             !this.product.isEmpty() ? Updates.set("product", this.product) : new Document(),
             !this.ram.isEmpty() ? Updates.set("ram", this.ram) : new Document(),
             !this.rom.isEmpty() ? Updates.set("rom", this.rom) : new Document(),
             !this.pin.isEmpty() ? Updates.set("pin", this.pin) : new Document(),
             !this.camera.isEmpty() ? Updates.set("camera", this.camera) : new Document(),
             !this.display.isEmpty() ? Updates.set("display", this.display) : new Document(),
             !this.sim.isEmpty() ? Updates.set("sim", this.sim) : new Document(),
             !this.manufacturer.isEmpty() ? Updates.set("manufacturer", this.manufacturer) : new Document(),
             !this.releaseYear.isEmpty() ? Updates.set("releaseYear", this.releaseYear) : new Document()
         );
 
         return updates;
    }
}
