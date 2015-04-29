package Praktikum1;

public class Kunde implements Runnable {

    private static int kundeId = 0;
    private Order order;
    private long waitingTime = 0; // per Systemzeit ausrechnen.
    private long createdWhen;


    public Kunde(){
        createdWhen = System.currentTimeMillis();
        kundeId++;
    }

    @Override
    public void run() {

    }

    public synchronized Order getBestellung(){
        int burgerCounter = (int)(Math.random() * 8);
        order = new Order(burgerCounter);

        return order;
    }

    public Order getOrder() {
        return order;
    }
}
