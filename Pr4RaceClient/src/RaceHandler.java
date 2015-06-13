import java.io.IOException;
import java.net.Socket;

/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class RaceHandler implements Runnable {

    private Socket socket;

    public RaceHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {

            try {
                String userInput = String.valueOf(System.in.read());
                System.out.println(userInput);
            } catch (IOException e) {
                e.printStackTrace();
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
