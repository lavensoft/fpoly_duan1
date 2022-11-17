/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package duan1;
import java.util.ArrayList;

import duan1.config.*;
import duan1.models.*;
import duan1.models.product.ProductModel;
import duan1.utils.Log;
import duan1.views.Main;
import duan1.controllers.*;

public class DuAn1 {
    public static void main(String[] args) {
        try {
            ProductModel product = new ProductModel();
            product.name = "iphone";

            ProductController productController = new ProductController();
            productController.add(product);

            ArrayList<ProductModel> products = new ArrayList<>();
            products = productController.getAll();

            System.out.println(products.size());
            System.out.println(products.get(0).toDocument());
        }catch(Exception e) {
            Log.error(e);
        }
    }
    
}
