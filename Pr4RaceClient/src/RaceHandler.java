import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class RaceHandler implements Runnable {

    private Socket socket;
    int i = 0;

    public RaceHandler() {
        //
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {


                Thread.sleep(50);
                socket = ConnectionHandler.getConnection();

                /*
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                System.out.print("Enter something:");
                String input = System.console().readLine();
                if (input.equals("/HELP")) {
                    ConnectionHandler.printAvailableFunctions();
                }
                out.print(input);
                */

                //Send the message to the server
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                System.out.print("Enter something:");
                String sendMessage = System.console().readLine();
                if (sendMessage.equals("/HELP")) {
                    ConnectionHandler.printAvailableFunctions();
                } else {
                    bw.write(sendMessage + i + "\n");
                    bw.flush();
                    System.out.println("Message sent to the server.");
                    i++;
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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
