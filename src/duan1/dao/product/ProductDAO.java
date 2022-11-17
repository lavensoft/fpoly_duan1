package duan1.dao.product;

import java.util.ArrayList;

import duan1.models.product.ProductModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class ProductDAO extends DAO<ProductModel> {
    public ProductDAO() {
        super(Collections.PRODUCT);
    }

    public void add(ProductModel product) {
        super.add(product);
    }

    public ArrayList<ProductModel> getAll(ProductModel... product) throws InstantiationException, IllegalAccessException {
        return super.getAll(product);
    }

    public ProductModel get(ProductModel product) throws InstantiationException, IllegalAccessException {
        return super.get(product);
    }
}
