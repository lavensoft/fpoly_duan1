package duan1.dao.order;

import java.util.ArrayList;

import duan1.models.order.OrderDimensionModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class OrderDimensionDAO extends DAO<OrderDimensionModel> {
    public OrderDimensionDAO() {
        super(Collections.ORDER_DIMENSION, new OrderDimensionModel());
    }

    public void add(OrderDimensionModel orderDimension) throws Exception {
        super.add(orderDimension);
    }

    public ArrayList<OrderDimensionModel> getAll(OrderDimensionModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return super.getAll(queries);
    }

    public OrderDimensionModel get(OrderDimensionModel query) throws Exception {
        return super.get(query);
    }

    public void deleteOne(OrderDimensionModel query) throws Exception {
        super.deleteOne(query);
    }

    public void deleteMany(OrderDimensionModel query) throws Exception {
        super.deleteMany(query);
    }
}
