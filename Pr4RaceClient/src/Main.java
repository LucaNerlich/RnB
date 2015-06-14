/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class Main {

    public static void main(String[] args) {
        ConnectionHandler.setupConnection(args[0], args[1]);
        RaceHandler raceHandler = new RaceHandler();
        Thread raceHandlerThread = new Thread(raceHandler);
        System.out.println("Ich bin ein Race Client!");
        ConnectionHandler.printAvailableFunctions();

        raceHandlerThread.start();

        RaceCar car1 = new RaceCar("Luca");
        RaceCar car2 = new RaceCar("Matteo");
    }
}
