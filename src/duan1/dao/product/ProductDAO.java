package duan1.dao.product;

import java.util.ArrayList;

import duan1.models.product.ProductModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class ProductDAO extends DAO<ProductModel> {
    public ProductDAO() {
        super(Collections.PRODUCT, new ProductModel());
    }

    public void add(ProductModel product) throws Exception {
        super.add(product);
    }

    public ArrayList<ProductModel> getAll(ProductModel... queries) throws Exception {
        return super.getAll(queries);
    }

    public ProductModel get(ProductModel query) throws Exception {
        return super.get(query);
    }

    public void delete(ProductModel query) throws Exception {
        super.delete(query);
    }

    public void deleteMany(ProductModel query) throws Exception {
        super.deleteMany(query);
    }
}
