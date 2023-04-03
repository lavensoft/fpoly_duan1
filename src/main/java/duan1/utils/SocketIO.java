package duan1.utils;

import java.net.URI;

import duan1.config.Config;
import io.socket.client.IO;
import io.socket.emitter.Emitter;
import io.socket.emitter.Emitter.Listener;
import io.socket.engineio.client.transports.Polling;
import io.socket.engineio.client.transports.WebSocket;
import io.socket.client.Socket;

public class SocketIO {
    private URI uri = URI.create(Config.API_URL);
    public Socket socket = IO.socket(uri);

    public SocketIO() {
        try {
            socket.connect();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
