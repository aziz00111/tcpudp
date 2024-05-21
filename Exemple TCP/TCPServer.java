import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Serveur démarré sur le port 12345");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Nouvelle connexion de " + clientSocket.getInetAddress().getHostAddress());
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Reçu: " + inputLine);
                        String reversed = new StringBuilder(inputLine).reverse().toString();
                        out.println(reversed);
                        System.out.println("Envoyé: " + reversed);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
