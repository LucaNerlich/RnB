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

            System.out.println("Verbindung zu Client aufgebaut.");
            System.out.println("running service, " + Thread.currentThread());

            out.println("y von Server");

            String answer = in.nextLine();
            System.out.println("Displying Server Message");

            System.out.println(answer);

            //sending answer
            out.println(answer + " von Server");

            /*
            BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(
                                    client.getInputStream()));


            char[] buffer = new char[100];
            int anzahlZeichen = bufferedReader.read(buffer, 0, 100); // blockiert bis Nachricht empfangen
            String nachricht = new String(buffer, 0, anzahlZeichen);
            */



           // System.out.println(nachricht);

            out.println("Message vom Server");

            if (!client.isClosed()) {
                System.out.println("****** Handler:Client close");
                try {
                    client.close();
                } catch (IOException e) {
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
