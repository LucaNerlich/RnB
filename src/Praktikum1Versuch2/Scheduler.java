package Praktikum1Versuch2;

import org.omg.CORBA.INTERNAL;

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

    // eventuell synchro und wait?
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

        // kleinere Bestellung hat prio
        return self.equals(sk);
    }

    public void addSK(ServiceKraft serviceKraft) {
        sks.add(serviceKraft);
    }
}
