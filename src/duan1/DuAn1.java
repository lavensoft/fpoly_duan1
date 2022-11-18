/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package duan1;

import java.io.File;
import java.util.ArrayList;

import org.bson.Document;

import duan1.config.*;

import duan1.models.product.ProductModel;
import duan1.utils.HttpClient;
import duan1.utils.Log;
import duan1.views.Main;

import duan1.controllers.product.ProductController;

public class DuAn1 {

    public static void main(String[] args) {
        try {
            ProductModel product = new ProductModel();
            product.name = "iPhone";
            product.banner = "/Users/nhatsdevil/Pictures/LOC22945.png";

            new ProductController().add(product);
        } catch (Exception e) {
            Log.error(e);
        }
    }

}
