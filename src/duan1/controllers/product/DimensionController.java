package duan1.controllers.product;

import duan1.models.product.DimensionModel;
import duan1.utils.HttpClient;
import duan1.dao.product.DimensionDAO;

import java.io.File;
import java.util.ArrayList;

public class DimensionController {
    private DimensionDAO dimensionDAO = new DimensionDAO();

    public void add(DimensionModel dimension) throws Exception {
        //Upload image
        String imageUrl = HttpClient.uploadFile(new File(dimension.banner));
        dimension.banner = imageUrl;
        
        dimensionDAO.add(dimension);
    }

    public ArrayList<DimensionModel> getAll(DimensionModel... queries) throws Exception {
        return dimensionDAO.getAll(queries);
    }

    public DimensionModel get(DimensionModel query) throws Exception {
        return dimensionDAO.get(query);
    }
}