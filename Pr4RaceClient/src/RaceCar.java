/**
 * Created by Luca Nerlich on 13.06.2015.
 */
public class RaceCar {

    private static int id = 0;
    private String name;

    private int timeRaceFinished;

    public RaceCar(String name) {
        this.name = name;
        id = id++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeRaceFinished() {
        return timeRaceFinished;
    }

    public void setTimeRaceFinished(int timeRaceFinished) {
        this.timeRaceFinished = timeRaceFinished;
    }
}
