package duan1.controllers.user;

import duan1.models.user.PermissionModel;
import duan1.utils.HttpClient;
import duan1.dao.user.PermissionDAO;

import java.io.File;
import java.util.ArrayList;

public class PermissionController {
    private PermissionDAO PermissionDAO = new PermissionDAO();

    public void add(PermissionModel data) throws Exception {
        //Add product
        PermissionDAO.add(data);
    }

    public ArrayList<PermissionModel> getAll(PermissionModel... queries) throws Exception {
        return PermissionDAO.getAll(queries);
    }

    public PermissionModel get(PermissionModel query) throws Exception {
        return PermissionDAO.get(query);
    }

    public void updateOne(PermissionModel query, PermissionModel data) throws Exception {
        PermissionDAO.updateOne(query, data);
    }

    public void updateMany(PermissionModel query, PermissionModel data) throws Exception {
        PermissionDAO.updateMany(query, data);
    }

    public void deleteOne(PermissionModel query) throws Exception {
        PermissionDAO.deleteOne(query);
    }

    public void deleteMany(PermissionModel query) throws Exception {
        PermissionDAO.deleteMany(query);
    }
}