package duan1.dao.product;

import java.util.ArrayList;

import duan1.models.product.DimensionModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class DimensionDAO extends DAO<DimensionModel> {
    public DimensionDAO() {
        super(Collections.PRODUCT_DIMENSION, new DimensionModel());
    }

    public void add(DimensionModel dimension) throws Exception {
        super.add(dimension);
    }

    public ArrayList<DimensionModel> getAll(DimensionModel... dimension) throws Exception {
        if(dimension.length > 0) dimension[0].toDocument();
        return super.getAll(dimension);
    }

    public DimensionModel get(DimensionModel dimension) throws Exception {
        return super.get(dimension);
    }
}
