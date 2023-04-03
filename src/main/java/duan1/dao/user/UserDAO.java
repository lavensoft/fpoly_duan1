package duan1.dao.user;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.user.UserModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class UserDAO extends DAO<UserModel> {
    public UserDAO() {
        super(Collections.USER, new UserModel());
    }

    public void add(UserModel user) throws Exception {
        user.dateCreated = new Date().toString();
        super.add(user);
    }

    public void updateOne(UserModel query, UserModel user) throws Exception {
        super.updateOne(query, user);
    }

    public void updateMany(UserModel query, UserModel user) throws Exception {
        super.updateMany(query, user);
    }

    public ArrayList<UserModel> getAll(UserModel... user) throws Exception {
        return super.getAll(user);
    }

    public UserModel get(UserModel user) throws Exception {
        System.out.println(user.toDocument());
        return super.get(user);
    }

    public void deleteOne(UserModel query) throws Exception {
        super.deleteOne(query);
    }

    public void deleteMany(UserModel query) throws Exception {
        super.deleteMany(query);
    }
}
