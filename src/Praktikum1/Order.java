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
    private int counterBurgerFertig;

    public Order(int counterBurgerBestellt) {
        orderId++;
        this.counterBurgerBestellt = counterBurgerBestellt;
    }

    public int getCounterBurgerBestellt() {
        return counterBurgerBestellt;
    }

    public void setCounterBurgerBestellt(int counterBurgerBestellt) {
        this.counterBurgerBestellt = counterBurgerBestellt;
    }

    public int getCounterBurgerFertig() {
        return counterBurgerFertig;
    }

    public void setCounterBurgerFertig(int counterBurgerFertig) {
        this.counterBurgerFertig = counterBurgerFertig;
    }
}
