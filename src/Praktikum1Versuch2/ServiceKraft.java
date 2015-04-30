package Praktikum1Versuch2;

import Praktikum1.Servicekraft;

public class ServiceKraft implements Runnable {

    private Warteschlange kundenQueue;
    private Warteschlange bestellQueue;

    public ServiceKraft(Warteschlange kundenQueue, Warteschlange bestellQueue) {
        this.kundenQueue = kundenQueue;
        this.bestellQueue = bestellQueue;
    }

    @Override
    public void run() {
        while (!(Thread.interrupted())) {
            getNextOrder();

            try {
                // sleep for 0 - 2 sec
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void getNextOrder() {
        Kunde kunde = (Kunde) kundenQueue.getFirst();
        if (kunde != null) {
            kunde.setPlacedOrderAt(System.currentTimeMillis());
            int anzahlBurger = kunde.getAnzahlBurgerBestellt();

            System.err.println("\n[SK_" + Thread.currentThread().getName() + "] __ "
                    + kunde.getName() + " will: " + anzahlBurger + " Burger.");

            //sende Bestellung an Kueche
            bestellQueue.enterBurger(anzahlBurger);
            System.err.println("[SK_" + Thread.currentThread().getName() + "] __ Bestellung ueber " + anzahlBurger + " Burger an Kueche geschickt. \n");
        }
    }
}
