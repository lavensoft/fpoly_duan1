package duan1.dao.product;

import java.util.ArrayList;
import java.util.Date;

import duan1.models.product.PromotionModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class PromotionDAO extends DAO<PromotionModel> {
    public PromotionDAO() {
        super(Collections.PROMOTION, new PromotionModel());
    }

    public void add(PromotionModel sale) throws Exception {
        sale.dateCreated = new Date().toString();
        super.add(sale);
    }

    public void updateOne(PromotionModel query, PromotionModel promotion) throws Exception {
        super.updateOne(query, promotion);
    }

    public void updateMany(PromotionModel query, PromotionModel promotion) throws Exception {
        super.updateMany(query, promotion);
    }

    public ArrayList<PromotionModel> getAll(PromotionModel... sale) throws Exception {
        return super.getAll(sale);
    }

    public PromotionModel get(PromotionModel sale) throws Exception {
        return super.get(sale);
    }

    public void deleteOne(PromotionModel query) throws Exception {
        super.deleteOne(query);
    }

    public void deleteMany(PromotionModel query) throws Exception {
        super.deleteMany(query);
    }
}
