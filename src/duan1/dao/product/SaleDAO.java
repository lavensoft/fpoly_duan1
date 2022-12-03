package duan1.dao.product;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.product.SaleModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class SaleDAO extends DAO<SaleModel> {
    public SaleDAO() {
        super(Collections.PRODUCT_SALE, new SaleModel());
    }

    public void add(SaleModel sale) throws Exception {
        sale.dateCreated = new Date().toString();
        super.add(sale);
    }

    public ArrayList<SaleModel> getAll(SaleModel... sale) throws Exception {
        return super.getAll(sale);
    }

    public SaleModel get(SaleModel sale) throws Exception {
        return super.get(sale);
    }
}
