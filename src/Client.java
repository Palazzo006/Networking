import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public void run() {
        try (
            Socket clientSocket = new Socket(InetAddress.getByName("localhost"), 9090);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Enter a state name (Arizona, New York, Utah, Alaska): ");
            System.out.println("(Type 'exit' to quit)");
            String state = scanner.nextLine().toLowerCase().trim();
            
            if (state.equals("exit")) {
                System.out.println("Exiting client...");
                return; // Exit the client
            }
            
            // Send normalized input to server
            out.println(state);

            // Receive and display the response from the server
            String response = in.readLine();
            System.out.println("Travel cost for " + state + ": " + response);
        } catch (IOException e) {
            System.out.println("Error communicating with server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}