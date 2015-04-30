package Praktikum1Versuch2;

public class Kunde {
    private static int kundeId = 0;
    private String name;
    private long waitingTime = 0; // per Systemzeit ausrechnen.
    private long createdWhen;
    private long placedOrderAt;
    private long orderFinishedAt;

    public Kunde() {
        createdWhen = System.currentTimeMillis();
        kundeId++;
        name = "" + kundeId;
    }

    public int getKundeId() {
        return kundeId;
    }
}
