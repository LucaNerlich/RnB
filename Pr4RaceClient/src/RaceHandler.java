import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class RaceHandler implements Runnable {

    private Socket socket;
    private Listener listener;
    int i = 0;

    public RaceHandler() {
        socket = ConnectionHandler.getConnection();
        listener = new Listener(socket);
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(50);

                //establish writer
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                //get input and send to server, via PW connected to Socket.
                System.out.print("...:");
                String userInput;
                while ((userInput = stdIn.readLine()) != null) {

                    out.println(userInput);
                    //get message from server
                    System.out.println("echo: " + in.readLine());

                    System.out.print("...:");
                }
            }
        } catch (IOException e) {
            System.out.println("Socket already closed. Error");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (socket.isConnected())
                try {
                    ConnectionHandler.closeConnection();
                } catch (IOException e) {
                }
        }
    }
}
