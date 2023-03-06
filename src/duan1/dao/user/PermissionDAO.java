package duan1.dao.user;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.user.PermissionModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class PermissionDAO extends DAO<PermissionModel> {
    public PermissionDAO() {
        super(Collections.PERMISSION, new PermissionModel());
    }

    public void add(PermissionModel data) throws Exception {
        super.add(data);
    }

    public ArrayList<PermissionModel> getAll(PermissionModel... queries) throws Exception {
        return super.getAll(queries);
    }

    public PermissionModel get(PermissionModel query) throws Exception {
        return super.get(query);
    }

    public void deleteOne(PermissionModel query) throws Exception {
        super.deleteOne(query);
    }

    public void deleteMany(PermissionModel query) throws Exception {
        super.deleteMany(query);
    }
}
