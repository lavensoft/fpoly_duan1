package duan1.dao.product;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.product.DimensionModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class DimensionDAO extends DAO<DimensionModel> {
    public DimensionDAO() {
        super(Collections.PRODUCT_DIMENSION, new DimensionModel());
    }

    public void add(DimensionModel dimension) throws Exception {
        dimension.dateCreated = new Date().toString();
        super.add(dimension);
    }

    public ArrayList<DimensionModel> getAll(DimensionModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return super.getAll(queries);
    }

    public DimensionModel get(DimensionModel query) throws Exception {
        return super.get(query);
    }

    public void delete(DimensionModel query) throws Exception {
        super.delete(query);
    }

    public void deleteMany(DimensionModel query) throws Exception {
        super.deleteMany(query);
    }
}
