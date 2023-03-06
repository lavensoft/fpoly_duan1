package duan1.states.product;

import java.util.ArrayList;

import org.bson.Document;

import duan1.controllers.product.ProductController;
import duan1.models.product.ProductModel;
import duan1.states.State;
import duan1.utils.Log;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ProductState extends State {
    public ArrayList<ProductModel> products = new ArrayList<>();

    public ProductState(Socket socket) throws Exception {
        super(socket);
        Log.success("[ INIT PRODUCT STATES ]", ProductState.class.getName());
    }

    @Override
    protected void fetchData() throws Exception {
        products = new ProductController().getAll();
    }

    @Override
    protected void initSocket() {
        socket.on("/products/add", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //Update data
                ProductModel product = new ProductModel();

                Document data = new Document();
                data = data.parse((String) args[0]);
                
                product.fromDocument(data);

                products.add(0, product);
            }
        });
    }
}
