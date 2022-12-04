package duan1.states;

import duan1.states.product.DimensionState;
import duan1.states.product.ProductState;
import duan1.states.user.*;
import duan1.utils.Log;
import io.socket.client.Socket;

public class AppStates {
    public static UserState user = new UserState();
    public static ProductState product;
    public static DimensionState dimension;

    private static Socket socket;

    public static void init(Socket socket) {
        try {
            AppStates.socket = socket;

            //* INIT STATES */
            AppStates.product = new ProductState(socket);
            AppStates.dimension = new DimensionState(socket);

            Log.success("[ APP STATES LOADED SUCCESSFULLY ]", AppStates.class.getName());
        }catch(Exception e) {
            Log.error(e);
        }
    }
}
