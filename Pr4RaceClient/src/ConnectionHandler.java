import java.io.IOException;
import java.net.Socket;

/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class ConnectionHandler {

    private static String ip;
    private static int port;
    private static ConnectionHandler instance = null;
    private static Socket socket = null;

    private ConnectionHandler() {
        //
    }

    public synchronized static ConnectionHandler getInstance() {
        if (instance == null) {
            ConnectionHandler.instance = new ConnectionHandler();
        }
        return ConnectionHandler.instance;
    }

    public static void setupConnection(String ip, String port) {
        setIp(ip);
        setPort(port);
    }

    public static Socket getConnection() {
        try {
            if (socket == null) {
                socket = new Socket(ip, port);
            }
        } catch (IOException e) {
            System.err.println("Error while creating Socket connection!");
        }
        return socket;
    }

    public static void closeConnection() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
            socket = null;
        }
        System.out.println("Socket closed by Connection Handler");
    }

    public static String getIp() {
        return ip;
    }

    private static void setIp(String ip) {
        ConnectionHandler.ip = ip;
    }

    public static int getPort() {
        return port;
    }

    private static void setPort(String port) {
        try {
            ConnectionHandler.port = Integer.parseInt(port);
        } catch (NumberFormatException ex) {
            System.err.println("You did not provide a valid int value for the port!");
        }
    }
}
