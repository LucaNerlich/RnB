package Praktikum1;

public class Kunde{

    private static int kundeId = 0;
    private Order order;
    private long waitingTime = 0; // per Systemzeit ausrechnen.
    private long createdWhen;


    public Kunde(){
        createdWhen = System.currentTimeMillis();
        kundeId++;
        generateBestellung();
    }

    public void generateBestellung(){
        int burgerCounter = (int)(Math.random() * 8);
        order = new Order(burgerCounter);
    }

    public Order getOrder() {
        return order;
    }

    public int getKundeId() {
        return kundeId;
    }
}
