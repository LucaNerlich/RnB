/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Ich bin ein Race Client!");
        System.out.println("Remeber: /HELP");

        String ip = "127.0.0.1";
        String port = "3141";
       // ConnectionHandler.setupConnection(ip, port);
        ConnectionHandler.setupConnection(args[0], args[1]);
        RaceHandler raceHandler = new RaceHandler();


        Thread raceHandlerThread = new Thread(raceHandler);
        raceHandlerThread.start();

        RaceCar car1 = new RaceCar("Luca");
        RaceCar car2 = new RaceCar("Daniel");
    }
}
