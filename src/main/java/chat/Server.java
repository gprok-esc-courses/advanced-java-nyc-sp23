package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    int port;
    ArrayList<ClientThread> clients;

    public Server() {
        port = 6021;
        clients = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started at " + port);
        } catch (IOException e) {
            System.out.println("Server failed to start");
        }
    }

    public void start() {
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket);
                client.start();
                clients.add(client);
            } catch (IOException e) {
                System.out.println("IO error");
            }
        }
    }

    class ClientThread extends Thread {
        private PrintWriter out;
        private BufferedReader in;
        String name;

        public ClientThread(Socket socket) {
            System.out.println("New Client created");
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                name = "N/A";
            } catch (IOException e) {
                System.out.println("IO error");
            }
        }

        public void sendMessage(String msg) {
            out.println(msg);
        }

        @Override
        public void run() {
            try {
                String name = in.readLine();
                this.name = name;
                System.out.println(name + " thread started");
                while(true) {
                        String input = in.readLine();
                        String msg = name + ": " + input;
                        System.out.println(msg);
                        for(ClientThread cth : clients) {
                            System.out.println("client " + cth.name);
                            if(cth != this) {
                                System.out.println("sending to " + cth.name);
                                cth.out.println(msg);
                            }
                        }
                }
            } catch (IOException e) {
                System.out.println("Error reading socket");
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
