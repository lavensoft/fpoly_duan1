package duan1.controllers.order;

import duan1.models.order.OrderDimensionModel;
import duan1.dao.order.OrderDimensionDAO;

import java.util.ArrayList;

public class OrderDimensionController {
    private OrderDimensionDAO orderDimensionDAO = new OrderDimensionDAO();

    public void add(OrderDimensionModel orderDimension) throws Exception {
        orderDimensionDAO.add(orderDimension);
    }

    public ArrayList<OrderDimensionModel> getAll(OrderDimensionModel... queries) throws Exception {
        return orderDimensionDAO.getAll(queries);
    }

    public OrderDimensionModel get(OrderDimensionModel query) throws Exception {
        return orderDimensionDAO.get(query);
    }
}