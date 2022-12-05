package duan1.controllers.product;

import duan1.models.product.DimensionConfigurationModel;
import duan1.dao.product.DimensionConfigurationDAO;

import java.util.ArrayList;

public class DimensionConfigurationController {
    private DimensionConfigurationDAO configurationDAO = new DimensionConfigurationDAO();

    public void add(DimensionConfigurationModel dimension) throws Exception {
        configurationDAO.add(dimension);
    }

    public ArrayList<DimensionConfigurationModel> getAll(DimensionConfigurationModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return configurationDAO.getAll(queries);
    }

    public DimensionConfigurationModel get(DimensionConfigurationModel query) throws Exception {
        return configurationDAO.get(query);
    }

    public void deleteOne(DimensionConfigurationModel query) throws Exception {
        configurationDAO.deleteOne(query);
    }

    public void deleteMany(DimensionConfigurationModel query) throws Exception {
        configurationDAO.deleteMany(query);
    }
}