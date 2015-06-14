import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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

            while ((inputLine = in.readLine()) != null) {
                outputLine = rP.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }












            /*
            Scanner in = new Scanner(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            System.out.println("running service, " + Thread.currentThread());
            System.out.println("Verbindung zu Client aufgebaut.");

            if (in.hasNext()) {
                String answer = in.nextLine();
                System.out.println(answer);
            } else {
                System.out.println("No message recieved,");
            }
            out.println("y von Server");

            System.out.println("Displying Server Message");

            //sending answer
            // out.println(answer + " von Server");
            // System.out.println(nachricht);

            out.println("Message vom Server");



            InputStream is = client.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String clientInput = br.readLine();
            System.out.println("Printing Client Message: ");
            System.out.println(clientInput);
            System.out.println();

            OutputStream os = client.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("Server Antwort: " + clientInput);
            System.out.println("Message sent to the client.");
            bw.flush();

            */

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
