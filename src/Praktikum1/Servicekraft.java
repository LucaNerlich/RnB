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
    private int burgersToMake;
    private int id;
    private Warteschlange warteSchlange;
    private int acceptedOrders = 0;
    private int finishedOrders = 0;
    private Warteschlange burgerLaufband;

    //JEDE SK Soll eine WS kriegen und nur diese abarbeiten.
    public Servicekraft(int id, Warteschlange warteschlangeZugeordnet, int burgersToMake, Warteschlange burgerLaufband) {
        this.id = id;
        warteSchlange = warteschlangeZugeordnet;
        this.burgersToMake = burgersToMake;
        this.burgerLaufband = burgerLaufband;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {

            getNextOrder();
            long timeCache = (long) ((Math.random() * 500) + 501); // zwischen 5 und 10 sekunden.
            try {
                Thread.sleep(timeCache);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finishOrder();
            long timeCache2 = (long) ((Math.random() * 500) + 501); // zwischen 5 und 10 sekunden.
            try {
                Thread.sleep(timeCache2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getNextOrder() {
        /*
        // verarbeite Bestellung.
        long timeCache = (long) ((Math.random() * 500) + 501); // zwischen 5 und 10 sekunden.
        try {
            Thread.sleep(timeCache);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        if (currentOrder == null) {
            Kunde kunde = (Kunde) warteSchlange.getFirst();
            if (kunde != null) {
                currentOrder = kunde.getOrder();
                acceptedOrders++;

                System.err.println("--> Bestellung des Kunden " + kunde.getKundeId() + " queue: " + kunde.getFromQueue() + " wurde angenommen.");
                System.err.println("--> Bearbeitet von SK: " + Thread.currentThread().getName());
                System.err.println("--> Kunde " + kunde.getKundeId() + " wartet. \n");

                kunde.getOrder().setWirdBearbeitetVon(this); // wird gebraucht um spaeter currentOrderFinished auf true zu setzen.
                kunde.saveOrderPlacedTime(); // Zeitpunkt der Bestellung festhalten.

                // bestellte Burger an Kueche weiterleiten.
                increaseBurgersToMake(currentOrder);

                long timeCache = (long) ((Math.random() * 5000) + 5000); // zwischen 5 und 10 sekunden.
                try {
                    Thread.sleep(timeCache);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("--> Kundewarteschlange ist leer.");
            }
        }
    }

    private synchronized void increaseBurgersToMake(Order currentOrder) {
        burgersToMake += currentOrder.getCounterBurgerBestellt();
        this.notifyAll();
    }

    private void finishOrder() {
        if (currentOrder != null) {
            int burgerBestellt = currentOrder.getCounterBurgerBestellt();
            int burgersReady = 0;
            while (burgerBestellt != burgersReady) {
                // System.err.println("Attempting to grab Burger");
                Burger burger = (Burger) burgerLaufband.remove();
                if (burger != null) {
                    currentOrder.addBurgerToOrder();
                    burgersReady++;
                    //System.err.println("--> Burger successfully added to order " + currentOrder.getOwner().getKundeId());
                }
            }
            currentOrder.getOwner().saveOrderFinishedTime();

            //Kunde kann erst verlassen, wenn er alle Burger erhalten hat.

            Kunde ownerRemoved = (Kunde) warteSchlange.remove();

            if (ownerRemoved != null) {
                System.err.println("--> Kunde " + ownerRemoved.getKundeId() +  " queue: " + ownerRemoved.getFromQueue() + " order " + ownerRemoved.getOrder().getOrderId() + " finished. removed");
                System.err.println("--> Kunde hat Rechung bezahlt. Verlaesst den Laden.");
                System.err.println("--> By SK " + Thread.currentThread().getName());
                finishedOrders++;
                currentOrder = null;

            } else {
                System.err.println("--> Error while removing by SK " + Thread.currentThread().getName());
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
}
