package duan1.controllers.product;

import duan1.models.product.DimensionConfigurationModel;
import duan1.models.product.DimensionPromotionModel;
import duan1.dao.product.DimensionConfigurationDAO;
import duan1.dao.product.DimensionPromotionDAO;

import java.util.ArrayList;

public class DimensionPromotionController {
    private DimensionPromotionDAO promotionDAO = new DimensionPromotionDAO();

    public void add(DimensionPromotionModel promotion) throws Exception {
        promotionDAO.add(promotion);
    }

    public ArrayList<DimensionPromotionModel> getAll(DimensionPromotionModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return promotionDAO.getAll(queries);
    }

    public DimensionPromotionModel get(DimensionPromotionModel query) throws Exception {
        return promotionDAO.get(query);
    }

    public void deleteOne(DimensionPromotionModel query) throws Exception {
        promotionDAO.deleteOne(query);
    }

    public void deleteMany(DimensionPromotionModel query) throws Exception {
        promotionDAO.deleteMany(query);
    }
}