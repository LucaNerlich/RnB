package Praktikum1Versuch2;

public class ServiceKraft implements Runnable {

    private Warteschlange kundenQueue;
    private Warteschlange burgerLaufband;
    private int counterFertigeBestellungen = 0;
    private Kunde kunde = null;
    private Bestellung bestellung = null;

    public ServiceKraft(Warteschlange kundenQueue, Warteschlange burgerLaufband) {
        this.kundenQueue = kundenQueue;
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

            History.getInstance().addStringToAusgabe("\n[SK_" + Thread.currentThread().getName() + "] __ "
                    + kunde.getName() + " Hat bezahlt und seine Bestellung erhalten und verlaesst den Laden.");

            //System.err.println("\n[SK_" + Thread.currentThread().getName() + "] __ "
            //       + kunde.getName() + " Hat bezahlt und seine Bestellung erhalten und verlaesst den Laden.");

            kunde = null;
            kundenQueue.remove();

            // todo bevor der kunde entfernt wird, muss noch eine zufallzeit fuers laden verlassen abgespeichert werden.
        }
    }

    private void getNextOrder() {
        kunde = (Kunde) kundenQueue.getFirst();

        if (kunde != null) {
            kunde.setPlacedOrderAt(System.currentTimeMillis());

            bestellung = kunde.getBestellung();

            try {
                // sleep for 5 - 10 sec
                Thread.sleep((long) ((Math.random() * 5000) + 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            History.getInstance().addStringToAusgabe("[SK_" + Thread.currentThread().getName() + "] __ "
                    + kunde.getName() + " will: " + bestellung.getAnzahlBurgerBestellt() + " Burger.");

            //System.err.println("\n[SK_" + Thread.currentThread().getName() + "] __ "
            //      + kunde.getName() + " will: " + bestellung.getAnzahlBurgerBestellt() + " Burger.");

        }
    }

    private void getBurgerFromKitchen() {
        //burger werden einfach geloescht, kunde hat damit alle erhalten

        //scheduler verhindert deadlocks

        int gewuenschteBurger = bestellung.getAnzahlBurgerBestellt();
        int burgerGeholt = 0;

        //while (Scheduler.getInstance().isMyTurn(this) && (burgerLaufband.getSize() >= bestellung.getAnzahlBurgerBestellt())) {
        while (Scheduler.getInstance().isMyTurn(this) && (burgerGeholt < gewuenschteBurger)) {
            //for (int i = 0; i <= bestellung.getAnzahlBurgerBestellt(); i++) {
                if (burgerLaufband.remove() != null) {
                    burgerGeholt++;
                    History.getInstance().addStringToAusgabe("[SK_" + Thread.currentThread().getName()
                            + "] __ Hat einen Burger vom Band genommen."
                    + "Es fehlen noch: " + (gewuenschteBurger - burgerGeholt)
                    + " Burger.");
                }
                //  System.err.println("\n[SK_" + Thread.currentThread().getName() + "] __ Hat einen Burger vom Band genommen.");
           // }
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
