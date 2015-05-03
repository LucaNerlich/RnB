package Praktikum1Versuch2;

public class Kunde {
    private static int kundeId = 0;
    private String name;
    private long waitingTime = 0; // per Systemzeit ausrechnen.
    private long createdWhen;
    private long placedOrderAt;
    private long orderFinishedAt;
    private Bestellung bestellung;
    private boolean receivedOrder = false;

    public Kunde() {
        createdWhen = System.currentTimeMillis();
        kundeId++;
        name = "kunde_" + kundeId;
        createdWhen = System.currentTimeMillis();
        generateOrder();
    }

    // todo bevor der kunde entfernt wird, muss noch eine zufallzeit fuers laden verlassen abgespeichert werden.

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
            waitingTime = (orderFinishedAt - placedOrderAt) / 1000;
        }
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

    public long getWaitingTime() {
        return waitingTime;
    }
}
