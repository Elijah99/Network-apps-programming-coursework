package client;

import contoller.RegisterController;
import view.MainMenu;

import javax.swing.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class ClientSocket {
    private static Socket socket;

    public static void initSocket() {
        try {
            socket = new Socket("127.0.0.1", 1024);
            System.out.println("Connected");
        } catch(ConnectException e) {
            String[] options = {"Yes, please", "No, thanks"};
            //JFrame jFrame = new JFrame("Ошибка!");
            int x = JOptionPane.showOptionDialog(
                    null,
                    "Не удалось подключиться к серверу. Попробовать снова ?",
                    "Register",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (x == 0) {
               initSocket();
            } else
            {
                System.exit(0);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Socket getSocket() {
        return socket;
    }
}
