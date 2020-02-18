import constants.ServerActions;
import constants.DBInfo;
import service.CoefficientsService;
import service.CompanyService;
import service.UserService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {

    private ServerSocket socket;

    public static void main(String[] args) {
        Server server = new Server();
        server.initializeServer();
        System.out.println("Server initialized");
        server.listenConnections();
    }

    private void initializeServer() {
        try {
            socket = new ServerSocket(1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenConnections() {
        System.out.println("Listening connections ... ");
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Socket client = socket.accept();
                new Thread(() -> {
                    System.out.println("Client accepted");
                    try {
                        PrintWriter outputStream = new PrintWriter(client.getOutputStream(), true);
                        BufferedReader inputStream = new BufferedReader(new InputStreamReader( client.getInputStream()));

                        String clientAction;

                        boolean flag = true;

                        while (!client.isClosed() && flag) {
                            clientAction = inputStream.readLine();
                            clientAction = clientAction.trim();

                            if (clientAction.equalsIgnoreCase(ServerActions.END)) {
                                flag = false;
                            } else if (clientAction.equalsIgnoreCase(ServerActions.LOGIN)) {
                                UserService.getInstance().logIn(inputStream,outputStream);
                            } else if (clientAction.equalsIgnoreCase(ServerActions.REGISTER)) {
                                if(UserService.getInstance().registerUser(inputStream))
                                {
                                    sendDataToClient(outputStream, "REGISTERED");
                                } else sendDataToClient(outputStream, "USER_EXISTS");

                            } else if (clientAction.equalsIgnoreCase(ServerActions.GET_ALL_USERS)) {
                                UserService.getInstance().getAllUsers(outputStream);
                            } else if (clientAction.equalsIgnoreCase(ServerActions.GET_STATISTICS)) {
                                CompanyService.getInstance().getCompanyStatistics(inputStream, outputStream);
                            } else if(clientAction.equalsIgnoreCase(ServerActions.ADD_ADMIN)) {
                                if(UserService.getInstance().addAdmin(inputStream))
                                {
                                    sendDataToClient(outputStream,"ADMIN_ADDED");
                                } else sendDataToClient(outputStream, "USER_EXISTS");
                            } else if(clientAction.equalsIgnoreCase(ServerActions.ADD_STATISTICS)){
                                CompanyService.getInstance().addStatisticsRecord(inputStream);
                            } else if(clientAction.equalsIgnoreCase(ServerActions.CALC_COEFFICIENTS)){
                                CoefficientsService.getInstance().getCoeffs(inputStream,outputStream);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendDataToClient(PrintWriter outputStream, String data) {
        outputStream.println(data);
    }


}