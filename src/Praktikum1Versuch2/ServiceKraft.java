package Praktikum1Versuch2;

public class ServiceKraft implements Runnable {

    private Warteschlange kundenQueue;
    private Warteschlange bestellQueue;
    private Warteschlange burgerLaufband;
    private int counterFertigeBestellungen = 0;
    private Kunde kunde = null;
    private Bestellung bestellung = null;

    public ServiceKraft(Warteschlange kundenQueue, Warteschlange bestellQueue, Warteschlange burgerLaufband) {
        this.kundenQueue = kundenQueue;
        this.bestellQueue = bestellQueue;
        this.burgerLaufband = burgerLaufband;
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
            System.err.println("\n[SK_" + Thread.currentThread().getName() + "] __ "
                    + kunde.getName() + " Hat bezahlt und seine Bestellung erhalten.");

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

            // ueberhaupt noetig?
            // bestellQueue.enterBurger(bestellung);
        }
    }

    private void getBurgerFromKitchen() {
        //burger werden einfach geloescht, kunde hat damit alle erhalten

        //scheduler verhindert deadlocks
        while (Scheduler.getInstance().isMyTurn(this) && (burgerLaufband.getSize() >= bestellung.getAnzahlBurgerBestellt())) {
            for (int i = 0; i <= bestellung.getAnzahlBurgerBestellt(); i++) {
                burgerLaufband.remove();
            }
        }
        kunde.setOrderFinishedAt(System.currentTimeMillis());
    }

    public int getCounterFertigeBestellungen() {
        return counterFertigeBestellungen;
    }

    public Bestellung getBestellung() {
        return bestellung;
    }
}
