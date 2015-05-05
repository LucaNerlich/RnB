package Praktikum1Versuch2;

import java.util.ArrayList;

public class Scheduler {
    //singleton
    private static Scheduler instance = null;
    private ArrayList<ServiceKraft> sks = new ArrayList();

    private Scheduler() {

    }

    public static Scheduler getInstance() {
        if (instance == null) {
            Scheduler.instance = new Scheduler();
        }
        return Scheduler.instance;
    }

    /**
     * tell the asking SK if its her turn to grab a burger.
     *
     * @param self
     * @return
     */
    public boolean isMyTurn(ServiceKraft self) {
        // wenn ich 3 weniger habe als der andere, darf er keine Burger nehmen.

        ServiceKraft sk = sks.get(0);

        // unsere bestellung ist kleiner
        int bestellteBurger = Integer.MAX_VALUE;
        for (ServiceKraft serviceKraft : sks) {
            if (serviceKraft.getBestellung() != null) {
                if (serviceKraft.getBestellung().getAnzahlBurgerBestellt() < bestellteBurger) {
                    sk = serviceKraft;
                }
            }
        }

        // wir haben 3 weniger als der andere
        boolean myTurn = ((self.getCounterFertigeBestellungen() - sk.getCounterFertigeBestellungen()) <= 3);
        if (myTurn) {
            sk = self;
        }

        // todo kein kunde darf laenger als ne max zeit warten.

        return self.equals(sk);
    }

    public void addSK(ServiceKraft serviceKraft) {
        sks.add(serviceKraft);
    }

    // burger: max 12 insgesamt, max 5 ohne bestellung, min 2 ohne bestellung

    public boolean shouldMakeBurger(Warteschlange burgerLaufband) {
        boolean makeBurger = false;
        int anzahlBestellungen = 0;

        for (ServiceKraft sk : sks) {
            anzahlBestellungen += sk.getKundenQueue().getSize();
        }

        //min 1 Bestellung
        if (anzahlBestellungen > 0) {
            makeBurger = true;
        } else {
            //max 5 Burger ohne Bestellung.
            if (burgerLaufband.getSize() <= 5) {
                makeBurger = true;
            }
        }

        //immer min 2 Burger auf dem Laufband.
        if (burgerLaufband.getSize() <= 2) {
            makeBurger = true;
        }


        return makeBurger;
    }
}
