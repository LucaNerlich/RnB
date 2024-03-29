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

    public String processInput(String theInput) {
        String theOutput = null;
        if (theInput != null) {
            if (theInput.equals("/HELP")) {
                theOutput = "Commands: '/HELP', '/INFO', '/EXIT'" + theInput;
            } else if (theInput.equals("/INFO")) {
                theOutput = "Server: INFO ";
            } else if (theInput.equals("/START")) {
                theOutput = "Race will start in 3 Seconds";
            } else if (theInput.equals("/EXIT")) {
                theOutput = "/EXIT";
            } else {
                theOutput = "INVALID";
            }
        }
        return theOutput;
    }
}
