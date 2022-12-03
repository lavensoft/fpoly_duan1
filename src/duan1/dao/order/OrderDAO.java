package duan1.dao.order;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.order.OrderModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class OrderDAO extends DAO<OrderModel> {
    public OrderDAO() {
        super(Collections.ORDER, new OrderModel());
    }

    public void add(OrderModel order) throws Exception {
        order.dateCreated = new Date().toString();
        super.add(order);
    }

    public ArrayList<OrderModel> getAll(OrderModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return super.getAll(queries);
    }

    public OrderModel get(OrderModel query) throws Exception {
        return super.get(query);
    }

    public void delete(OrderModel query) throws Exception {
        super.delete(query);
    }

    public void deleteMany(OrderModel query) throws Exception {
        super.deleteMany(query);
    }
}
