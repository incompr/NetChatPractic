package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class User implements Runnable {
    Socket socket;
    Scanner in;
    PrintStream out;
    ChatServer server;

    public User(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
        new Thread(this).start();

    }

    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            in = new Scanner(is);
            out = new PrintStream(os);

            out.println("Welcome to NetChat!");
            String input = in.nextLine();
            while (!input.equals("exit")) {
                server.sendMessageToChat(input);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void receiveMessageFromNet(String message) {
        out.println(message);


    }
}