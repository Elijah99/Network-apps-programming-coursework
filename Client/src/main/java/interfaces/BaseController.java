package interfaces;

import client.ClientSocket;

import java.io.*;
import java.net.Socket;

public abstract class BaseController<T extends BaseView> {
    private Socket socket;
    protected T view;

    protected BaseController() {
        socket = ClientSocket.getSocket();
    }

    protected void sendDataToServer(String res) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println(res);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getDataFromServer() {
        String received = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            received = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return received;
    }

    public void attachView(T view) {
        this.view = view;
    }
}
