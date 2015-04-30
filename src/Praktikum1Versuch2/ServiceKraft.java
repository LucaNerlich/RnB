package Praktikum1Versuch2;

import Praktikum1.Servicekraft;

public class ServiceKraft implements Runnable {

    private Warteschlange queue;

    public ServiceKraft(Warteschlange queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        getNextOrder();

        try {
            // sleep for 0 - 2 sec
            Thread.sleep((long) (Math.random() * 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getNextOrder() {
        Kunde kunde = (Kunde) queue.getFirst();
        int anzahlBurger = kunde.getAnzahlBurgerBestellt();
        System.err.println("[SK_" + Thread.currentThread().getName() + "] __ " + kunde.getName() + " will so viele Burger: " + anzahlBurger + "\n");
    }
}
