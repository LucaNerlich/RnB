import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String inputLine;
            String outputLine;

            // Initiate conversation with client
            RaceProtocol rP = new RaceProtocol();
            outputLine = rP.processInput(null);
            out.println(outputLine);

            //todo client input verwalten.
            while ((inputLine = in.readLine()) != null) {
                outputLine = rP.processInput(inputLine);
                if (outputLine != null) {
                    if (outputLine.equals("/EXIT")) {
                       break;
                    }
                    out.println(outputLine);
                }
            }


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
