package Praktikum1Versuch2;

public class Kunde {
    private static int kundeId = 0;
    private String name;
    private long waitingTime = 0; // per Systemzeit ausrechnen.
    private long createdWhen;
    private long placedOrderAt;
    private long orderFinishedAt;
    private Bestellung bestellung;

    public Kunde() {
        createdWhen = System.currentTimeMillis();
        kundeId++;
        name = "kunde_" + kundeId;
        createdWhen = System.currentTimeMillis();
        generateOrder();
    }


    // generiert zwischen 1 und 8 "burger" fuer die Bestellung.
    private void generateOrder() {
        int anzahlBurgerBestellt = (int) (Math.random() * 8) + 1;
        bestellung = new Bestellung(anzahlBurgerBestellt);
    }

    public int getKundeId() {
        return kundeId;
    }


    public String getName() {
        return name;
    }

    public void setPlacedOrderAt(long placedOrderAt) {
        this.placedOrderAt = placedOrderAt;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }
}
