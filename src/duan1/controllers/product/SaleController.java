package duan1.controllers.product;

import duan1.models.product.SaleModel;
import duan1.dao.product.SaleDAO;

import java.util.ArrayList;

public class SaleController {
    private SaleDAO saleDAO = new SaleDAO();

    public void add(SaleModel sale) throws Exception {
        saleDAO.add(sale);
    }

    public ArrayList<SaleModel> getAll(SaleModel... queries) throws Exception {
        return saleDAO.getAll(queries);
    }

    public SaleModel get(SaleModel query) throws Exception {
        return saleDAO.get(query);
    }
}