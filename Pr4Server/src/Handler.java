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
        System.out.println("Verbindung läuft! ...");
    }



}
