package simple_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int port;
    private PrintWriter out;
    private BufferedReader in;

    public Server() {
        port = 6020;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on " + port);
        } catch (IOException e) {
            serverSocket = null;
            System.out.println("Server failed to start");
        }
    }

    public void start() {
        while(true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("New request");
                System.out.println(clientSocket);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String name = in.readLine();
                out.println("Hello " + name + "!");
            }
            catch (IOException e) {
                System.out.println("IO Exception");
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }


}
