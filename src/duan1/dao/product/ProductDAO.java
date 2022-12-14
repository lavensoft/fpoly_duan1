package duan1.dao.product;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.product.ProductModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class ProductDAO extends DAO<ProductModel> {
    public ProductDAO() {
        super(Collections.PRODUCT, new ProductModel());
    }

    public void add(ProductModel product) throws Exception {
        product.dateCreated = new Date().toString();
        super.add(product);
    }

    public ArrayList<ProductModel> getAll(ProductModel... queries) throws Exception {
        return super.getAll(queries);
    }

    public ProductModel get(ProductModel query) throws Exception {
        return super.get(query);
    }

    public void deleteOne(ProductModel query) throws Exception {
        super.deleteOne(query);
    }

    public void deleteMany(ProductModel query) throws Exception {
        super.deleteMany(query);
    }
}
