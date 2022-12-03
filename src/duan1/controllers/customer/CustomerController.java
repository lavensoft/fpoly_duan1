package duan1.controllers.customer;

import duan1.models.customer.CustomerModel;
import duan1.dao.customer.CustomerDAO;

import java.util.ArrayList;

public class CustomerController {
    private CustomerDAO customerDAO = new CustomerDAO();

    public void add(CustomerModel customer) throws Exception {
        customerDAO.add(customer);
    }

    public ArrayList<CustomerModel> getAll(CustomerModel... queries) throws Exception {
        return customerDAO.getAll(queries);
    }

    public CustomerModel get(CustomerModel query) throws Exception {
        return customerDAO.get(query);
    }
}