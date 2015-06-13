import java.net.Socket;

/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class Main {

    public static void main(String[] args) {
        ConnectionHandler connectionHandler = new ConnectionHandler(args[0], args[1]);
        RaceHandler raceHandler = new RaceHandler(connectionHandler.getConnection());
        Thread raceHandlerThread = new Thread(raceHandler);
        raceHandler.run();

        System.out.println("Ich bin ein Race Client!");

        System.out.println(connectionHandler.getIp());
        System.out.println(connectionHandler.getPort());

        RaceCar car1 = new RaceCar("Luca");
        RaceCar car2 = new RaceCar("Matteo");


        System.out.println("ENDE");
    }
}
