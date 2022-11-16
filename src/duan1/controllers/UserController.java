package duan1.controllers;

import duan1.models.*;
import duan1.dao.*;
import duan1.utils.*;

import java.util.prefs.BackingStoreException;

import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserController {
    public static UserModel checkLogin() throws Exception {
        UserModel user = new UserModel();

        String jwt = LocalStorage.get("@jwt");

        if(jwt == null) throw new Exception("TOKEN_IS_NULL");

        //Verify token
        try {
            Document jwtPayload = AccessToken.verify(jwt);
            user._id = jwtPayload.getString("uid");

            user = UserDAO.get(user);

            if(user == null) throw new Exception("USER_NOT_FOUND");

            return user;
        }catch(Exception e) {
            throw e;
        }
    }

    public static UserModel login(String email, String password) throws Exception {
        UserModel userQuery = new UserModel();
        userQuery.email = email;
        userQuery.password = password;

        UserModel user = UserDAO.get(userQuery);

        System.out.println("QUERY ---");

        System.out.println("QUERY DATA -----");

        if(user == null) { //User not exists
            throw new Exception("USER_INVALID");
        }else { //User Exists
            String token = AccessToken.generate(user);
            AccessToken.verify(token);

            //Save to localStorage
            LocalStorage.put("@jwt", token);
        }

        return user;
    }

    public static void logout() throws BackingStoreException {
        //Clear local storage
        LocalStorage.clear();

        //Clear temp user data
        AccessToken.clear();
    }
}