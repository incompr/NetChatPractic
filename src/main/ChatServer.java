package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    ArrayList<User> connectedUserList = new ArrayList<>();
    ServerSocket serverSocket;

    public ChatServer() {
        try {
            serverSocket = new ServerSocket(5558);
            System.out.println("Server created successfully at port: " + serverSocket.getLocalPort());
        } catch (IOException ex) {
            System.out.println("Cannot create ServerSocket");
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new ChatServer().run();

    }

    void sendMessageToChat(String message) {
        for (User user : connectedUserList) {
            user.receiveMessageFromNet(message);

        }

    }


    public void run() {

        while (true) {
            System.out.println("Waiting for chat users...");

            try {
                Socket socket = serverSocket.accept();
                System.out.println("***User connected!****");

                connectedUserList.add(new User(socket, this));


            } catch (IOException ex) {
                System.out.println("Cannot accept connection");
                ex.printStackTrace();
            }
        }

    }

}