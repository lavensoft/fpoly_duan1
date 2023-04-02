package duan1.controllers.order;

import duan1.models.order.OrderModel;
import duan1.dao.order.OrderDAO;

import java.util.ArrayList;

public class OrderController {
    private OrderDAO orderDAO = new OrderDAO();

    public void add(OrderModel order) throws Exception {
        orderDAO.add(order);
    }

    public ArrayList<OrderModel> getAll(OrderModel... queries) throws Exception {
        return orderDAO.getAll(queries);
    }

    public OrderModel get(OrderModel query) throws Exception {
        return orderDAO.get(query);
    }
}