import java.io.IOException;
import java.net.Socket;

/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class ConnectionHandler {

    private String ip;
    private int port;

    public ConnectionHandler(String ip, String port) {
        setIp(ip);
        setPort(port);
    }

    public Socket getConnection(){
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            System.err.println("Error while creating Socket connection!");
        }
        return socket;
    }

    public String getIp() {
        return ip;
    }

    private void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    private void setPort(String port) {
        try {
            this.port = Integer.parseInt(port);
        }
        catch(NumberFormatException ex){
            System.err.println("You did not provide a valid int value for the port!");
        }
    }
}
