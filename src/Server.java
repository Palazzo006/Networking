import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket server;

    public Server() {
        try {
            this.server = new ServerSocket(9090, 0, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            System.out.println("Error creating server: " + e.getMessage());
        }
    }

    public void run() {
        System.out.println("Server is running...");
        while (true) {
            try (
                Socket clientSocket = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String state = in.readLine();
                String cost = getTravelCost(state);
                out.println(cost);
                System.out.println("Processed request for " + state + ": " + cost);
            } catch (IOException e) {
                System.out.println("Error handling client request: " + e.getMessage());
            }
        }
    }

    private String getTravelCost(String state) {
        String normalizedState = state.toLowerCase().trim();
    
        // Match normalized state names
        return switch (normalizedState) {
            case "arizona" -> "$500";
            case "new york" -> "$600";
            case "utah" -> "$300";
            case "alaska" -> "$700";
            default -> "State not found";
        };
    }

    public static void main(String[] args) {
        Server myServer = new Server();
        myServer.run();
    }
}


