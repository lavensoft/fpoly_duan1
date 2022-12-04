package duan1.dao.customer;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.customer.CustomerModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class CustomerDAO extends DAO<CustomerModel> {
    public CustomerDAO() {
        super(Collections.CUSTOMER, new CustomerModel());
    }

    public void add(CustomerModel customer) throws Exception {
        customer.dateCreated = new Date().toString();
        super.add(customer);
    }

    public void updateOne(CustomerModel query, CustomerModel customer) throws Exception {
        super.updateOne(query, customer);
    }

    public void updateMany(CustomerModel query, CustomerModel customer) throws Exception {
        super.updateMany(query, customer);
    }

    public ArrayList<CustomerModel> getAll(CustomerModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return super.getAll(queries);
    }

    public CustomerModel get(CustomerModel query) throws Exception {
        return super.get(query);
    }

    public void deleteOne(CustomerModel query) throws Exception {
        super.deleteOne(query);
    }

    public void deleteMany(CustomerModel query) throws Exception {
        super.deleteMany(query);
    }
}
