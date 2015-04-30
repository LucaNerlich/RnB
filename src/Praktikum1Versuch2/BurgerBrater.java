package Praktikum1Versuch2;

public class BurgerBrater implements Runnable {

    private Warteschlange bestellungenQueue;
    private Warteschlange burgerLaufband;

    public BurgerBrater(Warteschlange bestellungenQueue, Warteschlange burgerLaufband) {
        this.bestellungenQueue = bestellungenQueue;
        this.burgerLaufband = burgerLaufband;
    }

    @Override
    public void run() {
        while (!(Thread.interrupted())) {
            makeBurger();

            try {
                // sleep for 2 - 5 sec
                Thread.sleep((long) ((Math.random() * 3000) + 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void makeBurger() {
        burgerLaufband.enter(new Burger());
        System.err.println("[BB_" + Thread.currentThread().getName() + "] __ 1 Burger aufs Laufband gelegt. \n");
    }
}
