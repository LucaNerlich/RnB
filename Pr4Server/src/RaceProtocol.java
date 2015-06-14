/**
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
                theOutput = "/HELP \n /INFO \n /REGcar \n /STARTrace \n";
            } else if (theInput.equals("/EXIT")) {
                //
                /*
            } else {
                switch (state) {
                    case WAITING:
                        theOutput = "WAITING " + theInput;
                        state = RACEONGOING;
                        break;
                    case RACEONGOING:
                        theOutput = "RACE ONGOING " + theInput;
                        state = RACEFINISHED;
                        break;
                    case RACEFINISHED:
                        theOutput = "RACE FINISHED " + theInput;
                        state = WAITING;
                        break;
                }
            */
            }
        }
        return theOutput;
    }
}
