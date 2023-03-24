package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private Socket socket;
    private String ip;
    private int port;
    private PrintWriter out;
    private BufferedReader in;

    public Client() {
        ip = "127.0.0.1";
        port = 6021;
        try {
            Scanner scanner = new Scanner(System.in);
            socket = new Socket(ip, port);
            System.out.println("Client started");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.print("Name: ");
            String name = scanner.nextLine();
            out.println(name);

            ClientReader reader = new ClientReader();
            reader.start();

            while(true) {
                System.out.print("Message: ");
                String message = scanner.nextLine();
                out.println(message);
            }

            // String message = in.readLine();
            // System.out.println(message);
        } catch (IOException e) {
            System.out.println("Client failed to start");
        }
    }




    class ClientReader extends Thread {
        @Override
        public void run() {
            System.out.println("Waiting for server messages");
            while(true) {
                try {
                    String msg = in.readLine();
                    System.out.println(msg);
                } catch (IOException e) {
                    System.out.println("Client Reader failed");
                }

            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
