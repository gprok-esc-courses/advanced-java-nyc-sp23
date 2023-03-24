package simple_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private String ip;
    private int port;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;

    public Client() {
        ip = "127.0.0.1";
        port = 6020;
        try {
            scanner = new Scanner(System.in);
            socket = new Socket(ip, port);
            System.out.println("Client started");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String name = scanner.next();
            out.println(name);

            String message = in.readLine();
            System.out.println(message);
        } catch (IOException e) {
            System.out.println("Client failed to start");
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
