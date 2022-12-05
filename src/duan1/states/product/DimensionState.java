package duan1.states.product;

import java.util.ArrayList;

import org.bson.Document;

import duan1.controllers.product.DimensionController;
import duan1.models.product.DimensionModel;
import duan1.states.State;
import duan1.utils.Log;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class DimensionState extends State {
    public ArrayList<DimensionModel> dimensions = new ArrayList<>();

    public DimensionState(Socket socket) throws Exception {
        super(socket);
        Log.success("[ INIT DIMENSION STATES ]", DimensionState.class.getName());
    }

    @Override
    protected void fetchData() throws Exception {
        dimensions = new DimensionController().getAll();
    }

    @Override
    protected void initSocket() {
        socket.on("/products/dimension/add", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //Update data
                DimensionModel dimension = new DimensionModel();

                Document data = new Document();
                data = data.parse((String) args[0]);
                
                dimension.fromDocument(data);

                dimensions.add(0, dimension);
            }
        });
    }
}
