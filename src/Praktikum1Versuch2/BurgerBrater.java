package Praktikum1Versuch2;

public class BurgerBrater implements Runnable {

    private Warteschlange burgerLaufband;

    public BurgerBrater(Warteschlange burgerLaufband) {
        this.burgerLaufband = burgerLaufband;
    }

    @Override
    public void run() {
        while (!(Thread.interrupted())) {
            makeBurger();
        }
    }

    /**
     * generates Burger and places them on the Burgerqueue
     */
    private void makeBurger() {

        // todo scheduler muss anzahl Burger ueberwachen

        try {
            // sleep for 10 - 20 sec
            Thread.sleep((long) ((Math.random() * 10000) + 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        burgerLaufband.enter(new Burger());

        History.getInstance().addStringToAusgabe("[BB_" + Thread.currentThread().getName()
                + "] __ 1 Burger aufs Laufband gelegt.");
    }
}
