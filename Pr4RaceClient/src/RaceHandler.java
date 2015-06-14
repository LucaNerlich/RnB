import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class RaceHandler implements Runnable {

    private Socket socket;

    public RaceHandler() {
      //
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {

            try {

                socket = ConnectionHandler.getConnection();

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                System.out.print("Enter something:");
                String input = System.console().readLine();
                if (input.equals("/HELP")) {
                    ConnectionHandler.printAvailableFunctions();
                }

                // wird nur geschickt, wenn socket closed?
                out.print(input);


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                    }
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
