package duan1.dao.product;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.product.ManufacturerModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class ManufacturerDAO extends DAO<ManufacturerModel> {
    public ManufacturerDAO() {
        super(Collections.PRODUCT_MANUFACTURER, new ManufacturerModel());
    }

    public void add(ManufacturerModel manufacturer) throws Exception {
        manufacturer.dateCreated = new Date().toString();
        super.add(manufacturer);
    }

    public ArrayList<ManufacturerModel> getAll(ManufacturerModel... manufacturer) throws Exception {
        return super.getAll(manufacturer);
    }

    public ManufacturerModel get(ManufacturerModel manufacturer) throws Exception {
        return super.get(manufacturer);
    }
}
