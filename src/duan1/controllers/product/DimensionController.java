package duan1.controllers.product;

import duan1.models.product.DimensionModel;
import duan1.dao.product.DimensionDAO;

import java.util.ArrayList;

public class DimensionController {
    private DimensionDAO dimensionDAO = new DimensionDAO();

    public void add(DimensionModel dimension) throws Exception {
        dimensionDAO.add(dimension);
    }

    public ArrayList<DimensionModel> getAll(DimensionModel... queries) throws Exception {
        return dimensionDAO.getAll(queries);
    }

    public DimensionModel get(DimensionModel query) throws Exception {
        return dimensionDAO.get(query);
    }
}