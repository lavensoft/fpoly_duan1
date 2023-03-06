package duan1.controllers.product;

import duan1.models.product.PromotionModel;
import duan1.dao.product.PromotionDAO;

import java.util.ArrayList;

public class PromotionController {
    private PromotionDAO promotionDAO = new PromotionDAO();

    public void add(PromotionModel sale) throws Exception {
        promotionDAO.add(sale);
    }

    public void updateOne(PromotionModel query, PromotionModel promotion) throws Exception {
        promotionDAO.updateOne(query, promotion);
    }

    public void updateMany(PromotionModel query, PromotionModel promotion) throws Exception {
        promotionDAO.updateMany(query, promotion);
    }

    public ArrayList<PromotionModel> getAll(PromotionModel... queries) throws Exception {
        return promotionDAO.getAll(queries);
    }

    public PromotionModel get(PromotionModel query) throws Exception {
        return promotionDAO.get(query);
    }

    public void deleteOne(PromotionModel query) throws Exception {
        promotionDAO.deleteOne(query);
    }

    public void deleteMany(PromotionModel query) throws Exception {
        promotionDAO.deleteMany(query);
    }
}