/**
 * Created by Luca on 14.06.2015.
 * <p/>
 * Praktikum WIAD, WS 2014/2015
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 * mainNeu.java
 * <p/>
 * Praktikum WIAD, WS 2014/2015
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 * mainNeu.java
 * <p/>
 * Praktikum WIAD, WS 2014/2015
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 * mainNeu.java
 */

/**
 * Praktikum WIAD, WS 2014/2015
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 * mainNeu.java
 */

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Application class for our list
 *
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *        (Lucasteffen.Nerlich@haw-hamburg.de)
 *
 *        soll permanent auf Nachrichten vom Server warten.
 */
public class Listener extends Thread implements Runnable {
    Socket socket;

    public Listener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Scanner in = null;
            try {
                if (!socket.isClosed()) {
                    in = new Scanner(socket.getInputStream());
                    if (in.hasNext()) {
                        System.out.println(in);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
