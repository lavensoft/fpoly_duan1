package duan1.states;

import io.socket.client.Socket;

public abstract class State {
    protected Socket socket;

    public State(Socket socket) throws Exception {
        //* INIT SOCKET */
        this.socket = socket;
        this.initSocket();

        //* FETCH DATA */
        this.fetchData();
    }

    protected abstract void fetchData() throws Exception;

    protected abstract void initSocket() throws Exception;
}
