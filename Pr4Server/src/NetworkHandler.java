import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class NetworkHandler implements Runnable {

    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public NetworkHandler(ExecutorService pool,
                          ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.pool = pool;
    }

    public void run() { // run the service
        try {
            //Endlos-Schleife: warte auf Client-Anforderungen
            //Abbruch durch Strg+C oder Client-Anforderung 'Exit',
            //dadurch wird der ServerSocket beendet, was hier zu einer IOException
            //f�hrt und damit zum Ende der run-Methode mit vorheriger Abarbeitung der
            //finally-Klausel.
            while (true) {
        /*
         Zun�chst wird eine Client-Anforderung entgegengenommen(accept-Methode).
         Der ExecutorService pool liefert einen Thread, dessen run-Methode
         durch die run-Methode der Handler-Instanz realisiert wird.
         Dem Handler werden als Parameter �bergeben:
         der ServerSocket und der Socket des anfordernden Clients.
        */
                Socket cs = serverSocket.accept();  //warten auf Client-Anforderung

                //starte den Handler-Thread zur Realisierung der Client-Anforderung
                pool.execute(new Handler(serverSocket, cs));
            }
        } catch (IOException ex) {
            System.out.println("--- Interrupt NetworkService-run");
        } finally {
            System.out.println("--- Ende NetworkService(pool.shutdown)");
            pool.shutdown();  //keine Annahme von neuen Anforderungen
            try {
                //warte maximal 4 Sekunden auf Beendigung aller Anforderungen
                pool.awaitTermination(4L, TimeUnit.SECONDS);
                if (!serverSocket.isClosed()) {
                    System.out.println("--- Ende NetworkService:ServerSocket close");
                    serverSocket.close();
                }
            } catch (IOException e) {
            } catch (InterruptedException ei) {
            }
        }
    }
}