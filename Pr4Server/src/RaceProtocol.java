/**
 * Created by Luca on 14.06.2015.
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

/**
 * Application class for our list
 *
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 *         orietiert an https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 */

public class RaceProtocol {

    private static final int WAITING = 0;
    private static final int RACEONGOING = 1;
    private static final int RACEFINISHED = 2;

    private int state = WAITING;

    public String processInput(String theInput){
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "WAITING " + theInput;
            state = RACEONGOING;
        } else if (state == RACEONGOING) {
            theOutput = "RACE ONGOING " + theInput;
            state = RACEFINISHED;
        }else if (state == RACEFINISHED) {
            theOutput = "RACE FINISHED " + theInput;
            state = WAITING;
        }

        return theOutput;
    }
}
