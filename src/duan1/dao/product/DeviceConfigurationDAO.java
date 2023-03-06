package duan1.dao.product;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.product.DeviceConfigurationModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class DeviceConfigurationDAO extends DAO<DeviceConfigurationModel> {
    public DeviceConfigurationDAO() {
        super(Collections.DEVICE_CONFIGURATION, new DeviceConfigurationModel());
    }

    public void add(DeviceConfigurationModel configuration) throws Exception {
        configuration.dateCreated = new Date().toString();
        super.add(configuration);
    }

    public ArrayList<DeviceConfigurationModel> getAll(DeviceConfigurationModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return super.getAll(queries);
    }

    public DeviceConfigurationModel get(DeviceConfigurationModel query) throws Exception {
        return super.get(query);
    }

    public void deleteOne(DeviceConfigurationModel query) throws Exception {
        super.deleteOne(query);
    }

    public void deleteMany(DeviceConfigurationModel query) throws Exception {
        super.deleteMany(query);
    }
}
