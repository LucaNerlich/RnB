package Praktikum1;

public class Kunde {

    private static int kundeId = 0;
    private String fromQueue;
    private Order order;
    private long waitingTime = 0; // per Systemzeit ausrechnen.
    private long createdWhen;
    private long placedOrderAt;
    private long orderFinishedAt;


    public Kunde(String fromQueue) {
        createdWhen = System.currentTimeMillis();
        kundeId++;
        generateBestellung();
        this.fromQueue = fromQueue;
    }

    public void generateBestellung() {
        int burgerCounter = (int) ((Math.random() * 8) + 1);
        order = new Order(burgerCounter, this);
    }

    public Order getOrder() {
        return order;
    }

    public int getKundeId() {
        return kundeId;
    }

    public void saveOrderPlacedTime() {
        placedOrderAt = System.currentTimeMillis();
        waitingTime += (placedOrderAt - createdWhen);
    }

    public void saveOrderFinishedTime(){
        orderFinishedAt = System.currentTimeMillis();
        waitingTime += (orderFinishedAt - waitingTime);
    }

    public String getFromQueue() {
        return fromQueue;
    }
}
