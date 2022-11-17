package duan1.interfaces;

import org.bson.Document;
import com.mongodb.client.MongoCollection;

public interface IModel {
    enum Collections {
        USER("users"), 
        PRODUCT_MANUFACTURER("product_manufacturers"),
        PRODUCT("products"),
        PRODUCT_DIMENSION("product_dimensions"),
        PRODUCT_SALE("product_sales");
 
        private String value;
 
        private Collections(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    public String _id = "";
    public MongoCollection<Document> collection = null;
    
    public void fromDocument(Document document);
    public Document toDocument();
}
