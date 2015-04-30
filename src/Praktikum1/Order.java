package Praktikum1;/**
 * Created by Luca on 29.04.2015.
 */

/**
 * Praktikum WIAD, WS 2014/2015
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 * mainNeu.java
 */

/**
 * Application class for our list
 *
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 */

public class Order {
    private static int orderId = 0;
    private int counterBurgerBestellt;
    private int counterBurgerFertig = 0;
    private Kunde owner;
    Servicekraft wirdBearbeitetVon;

    public Order(int counterBurgerBestellt, Kunde owner) {
        orderId++;
        this.owner = owner;
        this.counterBurgerBestellt = counterBurgerBestellt;
    }

    public int getCounterBurgerBestellt() {
        return counterBurgerBestellt;
    }

    public int getCounterBurgerFertig() {
        return counterBurgerFertig;
    }


    //gives the customer one burger
    public void addBurgerToOrder() {
        counterBurgerFertig++;
    }

    public int getOrderId() {
        return orderId;
    }

    public Servicekraft getWirdBearbeitetVon() {
        return wirdBearbeitetVon;
    }

    public void setWirdBearbeitetVon(Servicekraft wirdBearbeitetVon) {
        this.wirdBearbeitetVon = wirdBearbeitetVon;
    }

    public Kunde getOwner() {
        return owner;
    }
}
