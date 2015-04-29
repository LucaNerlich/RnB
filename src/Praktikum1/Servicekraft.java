package Praktikum1;

import javax.xml.ws.Service;

/**
 * Praktikum WIAD, WS 2014/2015
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 * mainNeu.java
 */

public class Servicekraft implements Runnable {

    private Order currentOrder = null;
    private int id;
    private Warteschlange warteSchlange;
    private Order[] bestellungen;
    private boolean currentOrderFinished = true;
    private int acceptedOrders = 0;
    private int[] acceptedOrdersOverview; //welche Hilfskraft hat wie viel. ID entspricht Index

    public Servicekraft(int id, Warteschlange warteschlangeZugeordnet, Order[] bestellungen, int[] acceptedOrdersOverview) {
        this.id = id;
        warteSchlange = warteschlangeZugeordnet;
        this.bestellungen = bestellungen;
        this.acceptedOrdersOverview = acceptedOrdersOverview;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            // Wenn eine SK 3 mehr als die andere hat, wird geschlafen.
            // WERT MUSS BEI ABSCHLUSS DER BESTELLUNG UM 1 REDUZIERT WERDEN!!!
            if (id == 0) {
                if ((acceptedOrdersOverview[1] - acceptedOrdersOverview[id]) > 3) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (id == 1) {
                if ((acceptedOrdersOverview[0] - acceptedOrdersOverview[id]) < 3) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                getNextOrder();
            }
        }
    }

    public void getNextOrder() {

        // verarbeite Bestellung.
        long timeCache = (long) ((Math.random() * 5000) + 5001); // zwischen 5 und 10 sekunden.
        try {
            Thread.sleep(timeCache);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (currentOrderFinished) { // nur neue Bestellungen holen, wenn alte fertig ist.
            if ((id == 0) && bestellungen[0] == null) {
                Kunde kunde = (Kunde) warteSchlange.remove();

                System.err.println("Bestellung des Kunden " + kunde.getKundeId() + " wurde angenommen.");
                System.err.println("Kunde " + kunde.getKundeId() + " wartet. \n");

                bestellungen[0] = kunde.getOrder();
                currentOrder = kunde.getOrder();
                kunde.getOrder().setWirdBearbeitetVon(this); // wird gebraucht um spaeter currentOrderFinished auf true zu setzen.
                kunde.saveOrderPlacedTime(); // Zeitpunkt der Bestellung festhalten.
                currentOrderFinished = false; // muss auf fertigung der Burger warten.
                acceptedOrders++;
                acceptedOrdersOverview[id]++;
            } else if ((id == 1) && bestellungen[1] == null) {
                Kunde kunde = (Kunde) warteSchlange.remove();
                bestellungen[1] = kunde.getOrder();
                currentOrder = kunde.getOrder();
                kunde.getOrder().setWirdBearbeitetVon(this);
                kunde.saveOrderPlacedTime();
                currentOrderFinished = false;
                acceptedOrders++;
            } else {
                System.err.println("ERROR beim Bestellungannehmen.");
            }
        }
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public int getId() {
        return id;
    }

    public int getAcceptedOrders() {
        return acceptedOrders;
    }

    public int[] getAcceptedOrdersOverview() {
        return acceptedOrdersOverview;
    }

    public Warteschlange getWarteSchlange() {
        return warteSchlange;
    }

    public Order[] getBestellungen() {
        return bestellungen;
    }

    public boolean isCurrentOrderFinished() {
        return currentOrderFinished;
    }
}
