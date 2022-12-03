package duan1.dao.customer;

import java.util.ArrayList;

import duan1.models.customer.CustomerModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class CustomerDAO extends DAO<CustomerModel> {
    public CustomerDAO() {
        super(Collections.CUSTOMER, new CustomerModel());
    }

    public void add(CustomerModel customer) throws Exception {
        super.add(customer);
    }

    public ArrayList<CustomerModel> getAll(CustomerModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return super.getAll(queries);
    }

    public CustomerModel get(CustomerModel query) throws Exception {
        return super.get(query);
    }

    public void delete(CustomerModel query) throws Exception {
        super.delete(query);
    }

    public void deleteMany(CustomerModel query) throws Exception {
        super.deleteMany(query);
    }
}
