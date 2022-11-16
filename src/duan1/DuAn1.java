/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package duan1;
import java.util.ArrayList;

import duan1.config.*;
import duan1.models.*;
import duan1.controllers.*;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

public class DuAn1 {
    public static void main(String[] args) {
        //DISABLE LOG MONGODB
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.ERROR);

        //INIT
        Database.init();

        //Test login
        try {
            UserModel usr = UserController.checkLogin();
            // UserController.login("phat@gmail.com", "12345");
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
}
