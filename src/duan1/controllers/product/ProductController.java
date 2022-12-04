package duan1.controllers.product;

import duan1.models.product.ProductModel;
import duan1.utils.HttpClient;
import duan1.dao.product.ProductDAO;

import java.io.File;
import java.util.ArrayList;

public class ProductController {
    private ProductDAO productDAO = new ProductDAO();

    public void add(ProductModel product) throws Exception {
        //Upload image
        String imageUrl = HttpClient.uploadFile(new File(product.banner));
        product.banner = imageUrl;

        //Add product
        productDAO.add(product);
    }

    public ArrayList<ProductModel> getAll(ProductModel... queries) throws Exception {
        return productDAO.getAll(queries);
    }

    public ProductModel get(ProductModel query) throws Exception {
        return productDAO.get(query);
    }

    public void deleteOne(ProductModel query) throws Exception {
        productDAO.deleteOne(query);
    }

    public void deleteMany(ProductModel query) throws Exception {
        productDAO.deleteMany(query);
    }
}