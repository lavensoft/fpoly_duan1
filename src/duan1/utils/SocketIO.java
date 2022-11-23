package duan1.utils;

import java.net.URI;

import io.socket.client.IO;
import io.socket.emitter.Emitter;
import io.socket.emitter.Emitter.Listener;
import io.socket.engineio.client.transports.Polling;
import io.socket.engineio.client.transports.WebSocket;
import io.socket.client.Socket;

public class SocketIO {
    private URI uri = URI.create("http://localhost:8080");
    public Socket socket = IO.socket(uri);

    public SocketIO() {
        socket.connect();
    }
}
