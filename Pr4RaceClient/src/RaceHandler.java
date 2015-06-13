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

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
