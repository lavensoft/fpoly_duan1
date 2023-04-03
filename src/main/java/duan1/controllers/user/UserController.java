package duan1.controllers.user;

import duan1.models.user.PermissionModel;
import duan1.models.user.UserModel;
import duan1.dao.*;
import duan1.dao.user.UserDAO;
import duan1.utils.*;

import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

import org.bson.Document;
import org.apache.commons.codec.digest.DigestUtils;

public class UserController {
    private UserDAO userDAO = new UserDAO();

    public void add(UserModel user) throws Exception {
        if(user.name.equals("") || user.password.equals("") || user.cccd.equals("") || user.email.equals("") || user.salary == 0)
            throw new Exception("ACCOUNT_FORM_EMPTY");
        if(!user.email.matches("\\w+@\\w+(\\.\\w+){1,2}"))
            throw new Exception("ACCOUNT_FORM_WRONG_EMAIL_FORMAT");
        if(!user.cccd.matches("\\d{12}"))
            throw new Exception("ACCOUNT_FORM_WRONG_CCCD_FORMAT");
        if(user.salary < 0)
            throw new Exception("ACCOUNT_FORM_WRONG_SALARY_FORMAT");
        UserModel query = new UserModel();
        query.email = user.email;
        UserModel result = userDAO.get(query);
        if(result != null)
            throw new Exception("ACCOUNT_FORM_EXSITS");
        user.password = DigestUtils.sha256Hex(user.password);
        userDAO.add(user);
    }

    public void updateOne(UserModel query, UserModel user) throws Exception {
        userDAO.updateOne(query, user);
    }

    public void updateMany(UserModel query, UserModel user) throws Exception {
        userDAO.updateMany(query, user);
    }

    public UserModel checkLogin() throws Exception {
        UserModel user = new UserModel();

        String jwt = LocalStorage.get("@jwt");

        if(jwt == null) throw new Exception("TOKEN_IS_NULL");

        //Verify token
        try {
            Document jwtPayload = AccessToken.verify(jwt);
            user._id = jwtPayload.getString("uid");

            user = userDAO.get(user);

            if(user == null) throw new Exception("USER_NOT_FOUND");

            return user;
        }catch(Exception e) {
            throw e;
        }
    }

    public UserModel login(String email, String password) throws Exception {
        UserModel userQuery = new UserModel();
        userQuery.email = email;
        userQuery.password = DigestUtils.sha256Hex(password);

        UserModel user = userDAO.get(userQuery);

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

    public ArrayList<UserModel> getAll(UserModel... queries) throws Exception {
        return userDAO.getAll(queries);
    }

    public UserModel get(UserModel query) throws Exception {
        return userDAO.get(query);
    }

    public void deleteOne(UserModel query) throws Exception {
        userDAO.deleteOne(query);
    }

    public void deleteMany(UserModel query) throws Exception {
        userDAO.deleteMany(query);
    }
}