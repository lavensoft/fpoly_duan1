/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package duan1;
import java.util.ArrayList;

import duan1.config.*;
import duan1.models.*;
import duan1.views.Main;
import duan1.controllers.*;

public class DuAn1 {
    public static void main(String[] args) {
        try {
            UserController userController = new UserController();
            userController.login("phat@gmail.com", "12345");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
}
