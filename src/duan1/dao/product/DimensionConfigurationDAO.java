package duan1.dao.product;

import java.util.ArrayList;
import duan1.models.product.DimensionConfigurationModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class DimensionConfigurationDAO extends DAO<DimensionConfigurationModel> {
    public DimensionConfigurationDAO() {
        super(Collections.DIMENSION_CONFIGURATION, new DimensionConfigurationModel());
    }

    public void add(DimensionConfigurationModel configuration) throws Exception {
        super.add(configuration);
    }

    public ArrayList<DimensionConfigurationModel> getAll(DimensionConfigurationModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return super.getAll(queries);
    }

    public DimensionConfigurationModel get(DimensionConfigurationModel query) throws Exception {
        return super.get(query);
    }

    public void deleteOne(DimensionConfigurationModel query) throws Exception {
        super.deleteOne(query);
    }

    public void deleteMany(DimensionConfigurationModel query) throws Exception {
        super.deleteMany(query);
    }
}
