package Praktikum1Versuch2;

import Praktikum1.Servicekraft;

public class ServiceKraft implements Runnable {

    private Warteschlange kundenQueue;
    private Warteschlange bestellQueue;
    private Warteschlange burgerLaufband;
    private ServiceKraft kollege;
    private int counterFertigeBestellungen = 0;
    private Kunde kunde = null;
    private Bestellung bestellung = null;
    private Scheduler scheduler;

    public ServiceKraft(Warteschlange kundenQueue, Warteschlange bestellQueue, Warteschlange burgerLaufband, Scheduler scheduler) {
        this.kundenQueue = kundenQueue;
        this.bestellQueue = bestellQueue;
        this.burgerLaufband = burgerLaufband;
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        while (!(Thread.interrupted())) {
            if (kunde == null) {
                getNextOrder();
            }

            getBurgerFromKitchen();
            counterFertigeBestellungen++;
            kunde = null;
            kundenQueue.remove();

            // todo wenn bestellung done, remove kunde, setz kunde auf null


            try {
                // sleep for 0 - 2 sec
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getNextOrder() {
        kunde = (Kunde) kundenQueue.getFirst();

        if (kunde != null) {
            kunde.setPlacedOrderAt(System.currentTimeMillis());

            bestellung = kunde.getBestellung();

            System.err.println("\n[SK_" + Thread.currentThread().getName() + "] __ "
                    + kunde.getName() + " will: " + bestellung.getAnzahlBurgerBestellt() + " Burger.");

            //sende Bestellung an Kueche
            //ueberhaupt noetig?
            bestellQueue.enterBurger(bestellung);
        }
    }

    private void getBurgerFromKitchen() {

        //scheduler verhindert deadlocks
       while (scheduler.isMyTurn(this, kollege) && (burgerLaufband.getSize() >= bestellung.getAnzahlBurgerBestellt() )) {
            for(int i = 0; i <= bestellung.getAnzahlBurgerBestellt(); i++){
                burgerLaufband.remove();
            }
        }
    }

    public ServiceKraft getKollege() {
        return kollege;
    }

    public void setKollege(ServiceKraft kollege) {
        this.kollege = kollege;
    }

    public int getCounterFertigeBestellungen() {
        return counterFertigeBestellungen;
    }
}
