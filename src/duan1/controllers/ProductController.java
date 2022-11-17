package duan1.controllers;

import duan1.models.product.ProductModel;
import duan1.dao.product.ProductDAO;

import java.util.ArrayList;

public class ProductController {
    private ProductDAO productDAO = new ProductDAO();

    public void add(ProductModel product) throws Exception {
        productDAO.add(product);
    }

    public ArrayList<ProductModel> getAll(ProductModel... queries) throws Exception {
        return productDAO.getAll(queries);
    }

    public ProductModel get(ProductModel query) throws Exception {
        return productDAO.get(query);
    }
}