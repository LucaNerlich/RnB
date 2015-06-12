package Praktikum1Versuch2;

public class Kunde {
    private static int kundeId = 0;
    private String name;
    private int maxWaittime = 60;
    private int waitingTime = 0; // per Systemzeit ausrechnen.
    private long createdWhen;
    private long placedOrderAt;
    private long orderFinishedAt;
    private Bestellung bestellung;
    private boolean receivedOrder = false;

    public Kunde() {
        kundeId++;
        name = "kunde_" + kundeId;
        createdWhen = System.currentTimeMillis();
        generateOrder();
    }

    // generiert zwischen 1 und 8 "burger" fuer die Bestellung.
    private void generateOrder() {
        int anzahlBurgerBestellt = (int) (Math.random() * 8) + 1;
        bestellung = new Bestellung(this, anzahlBurgerBestellt);
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

    public long getOrderFinishedAt() {
        return orderFinishedAt;
    }

    public void setOrderFinishedAt(long orderFinishedAt) {
        this.orderFinishedAt = orderFinishedAt;
    }

    public long getCreatedWhen() {
        return createdWhen;
    }

    public long getPlacedOrderAt() {
        return placedOrderAt;
    }

    // calculates time from placing order till recveiving burgers
    public void calculateAndSetWaitingTime() {
        if ((placedOrderAt > 0) && (orderFinishedAt > 0)) {
            waitingTime = (int)(orderFinishedAt - placedOrderAt) / 1000;
        }
        //Zeit um den Laden zu verlassen: 10 - 20 Sekunden.
        waitingTime += (int)((Math.random()* 10) + 10);

        // save starttime in currenttimemillis
        // save end time
        // (end - start)/1000 = time taken in seconds
    }

    public boolean isReceivedOrder() {
        return receivedOrder;
    }

    public void setReceivedOrder(boolean receivedOrder) {
        this.receivedOrder = receivedOrder;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    // Abfrage ob der Kunde bereits laenger als gewuenscht gewartet hat
    public boolean hasWaitedTooLong(){
        boolean hasWaitedTooLong = false;

        long currenttime = System.currentTimeMillis();
        int cachetime = (int)((currenttime - placedOrderAt) / 1000);

        if(cachetime >= maxWaittime){
            hasWaitedTooLong = true;
        }

        return hasWaitedTooLong;
    }
}
