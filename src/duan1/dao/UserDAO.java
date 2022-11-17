package duan1.dao;

import java.util.ArrayList;
import duan1.models.user.UserModel;
import duan1.config.Collections;

public class UserDAO extends DAO<UserModel> {
    public UserDAO() {
        super(Collections.USER, new UserModel());
    }

    public void add(UserModel user) throws Exception {
        super.add(user);
    }

    public ArrayList<UserModel> getAll(UserModel... user) throws Exception {
        return super.getAll(user);
    }

    public UserModel get(UserModel user) throws Exception {
        return super.get(user);
    }
}
