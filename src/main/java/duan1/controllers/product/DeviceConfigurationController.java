package duan1.controllers.product;

import duan1.models.product.DeviceConfigurationModel;
import duan1.dao.product.DeviceConfigurationDAO;
import java.util.ArrayList;

public class DeviceConfigurationController {
    private DeviceConfigurationDAO configurationDAO = new DeviceConfigurationDAO();

    public void add(DeviceConfigurationModel dimension) throws Exception {
        configurationDAO.add(dimension);
    }

    public ArrayList<DeviceConfigurationModel> getAll(DeviceConfigurationModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return configurationDAO.getAll(queries);
    }

    public DeviceConfigurationModel get(DeviceConfigurationModel query) throws Exception {
        return configurationDAO.get(query);
    }

    public void deleteOne(DeviceConfigurationModel query) throws Exception {
        configurationDAO.deleteOne(query);
    }

    public void deleteMany(DeviceConfigurationModel query) throws Exception {
        configurationDAO.deleteMany(query);
    }
}