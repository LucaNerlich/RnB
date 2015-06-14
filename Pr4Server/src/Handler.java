import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class Handler implements Runnable {
    //hier muessen alle autos gesammelt werden?

    private final Socket client;
    private final ServerSocket serverSocket;

    Handler(ServerSocket serverSocket, Socket client) { //Server/Client-Socket
        this.client = client;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            System.out.println("running service, " + Thread.currentThread());
            System.out.println("Verbindung zu Client aufgebaut.");

            if (in.hasNext()) {
                String answer = in.nextLine();
                System.out.println(answer);
            } else {
                System.out.println("No message recieved,");
            }
            out.println("y von Server");

            System.out.println("Displying Server Message");

            //sending answer
            // out.println(answer + " von Server");
            // System.out.println(nachricht);

            out.println("Message vom Server");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null)
                try {
                    client.close();
                } catch (IOException e) {
                }
        }
    }


}
