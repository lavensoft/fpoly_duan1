package duan1.dao;

import java.util.ArrayList;
import duan1.models.user.UserModel;
import duan1.config.Collections;

public class UserDAO extends DAO<UserModel> {
    public UserDAO() {
        super(Collections.USER);
    }

    public void add(UserModel user) {
        super.add(user);
    }

    public ArrayList<UserModel> getAll(UserModel... user) throws InstantiationException, IllegalAccessException {
        return super.getAll(user);
    }

    public UserModel get(UserModel user) throws InstantiationException, IllegalAccessException {
        return super.get(user);
    }
}
