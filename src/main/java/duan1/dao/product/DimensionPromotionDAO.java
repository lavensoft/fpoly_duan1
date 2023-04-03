package duan1.dao.product;

import java.util.ArrayList;
import duan1.models.product.DimensionConfigurationModel;
import duan1.models.product.DimensionPromotionModel;
import duan1.config.Collections;
import duan1.dao.DAO;

public class DimensionPromotionDAO extends DAO<DimensionPromotionModel> {
    public DimensionPromotionDAO() {
        super(Collections.DIMENSION_PROMOTION, new DimensionPromotionModel());
    }

    public void add(DimensionPromotionModel dimension) throws Exception {
        super.add(dimension);
    }

    public ArrayList<DimensionPromotionModel> getAll(DimensionPromotionModel... queries) throws Exception {
        if(queries.length > 0) queries[0].toDocument();
        return super.getAll(queries);
    }

    public DimensionPromotionModel get(DimensionPromotionModel query) throws Exception {
        return super.get(query);
    }

    public void deleteOne(DimensionPromotionModel query) throws Exception {
        super.deleteOne(query);
    }

    public void deleteMany(DimensionPromotionModel query) throws Exception {
        super.deleteMany(query);
    }
}
