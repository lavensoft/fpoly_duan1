package duan1.controllers.product;

import duan1.models.product.ManufacturerModel;
import duan1.dao.product.ManufacturerDAO;

import java.util.ArrayList;

public class ManufacturerController {
    private ManufacturerDAO manufacturerDAO = new ManufacturerDAO();

    public void add(ManufacturerModel manufacturer) throws Exception {
        manufacturerDAO.add(manufacturer);
    }

    public ArrayList<ManufacturerModel> getAll(ManufacturerModel... queries) throws Exception {
        return manufacturerDAO.getAll(queries);
    }

    public ManufacturerModel get(ManufacturerModel query) throws Exception {
        return manufacturerDAO.get(query);
    }
}