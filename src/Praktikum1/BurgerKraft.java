package Praktikum1;

public class BurgerKraft implements Runnable {

    private Warteschlange burgerLaufband;

    public BurgerKraft(Warteschlange burgerLaufband) {
        this.burgerLaufband = burgerLaufband;

    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            makeBurger();
        }
    }

    private void makeBurger(){
        Burger burger = new Burger();

        //todo check with BURGERSTOMAKE, always make these plus some spare

        long timeCache = (long) ((Math.random() * 5000) + 5000); // zwischen 5 und 10 sekunden.
        try {
            Thread.sleep(timeCache);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        burgerLaufband.enter(burger);
       // System.out.println("Burger aufs Laufband gelegt.");
    }
}
