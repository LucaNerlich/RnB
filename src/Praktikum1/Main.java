package Praktikum1;


/**
 * Praktikum RB, SS 2015
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * Main.java
 * <p/>
 * Application class for our list
 *
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 */

public class Main {

    public static void main(String[] args) {

        Warteschlange kundenWS1 = new Warteschlange(0, 10); // ID = 1 wichtig fuer Order zuordnung.
        Warteschlange kundenWS2 = new Warteschlange(1, 10); // ID = 2
        Warteschlange burgerLaufband = new Warteschlange(2, 12); // ID = 3

        //haelt die Anzahl der bestellten Buerger fuer die Kueche.
        int burgersToMake = 0;

        Kundengenerator kundengenerator = new Kundengenerator(15, kundenWS1, kundenWS2); // 50 Kunden

        Servicekraft skTresen1 = new Servicekraft(0, kundenWS1, burgersToMake, burgerLaufband);
        Servicekraft skTresen2 = new Servicekraft(1, kundenWS2, burgersToMake, burgerLaufband);

        BurgerKraft skBurger1 = new BurgerKraft(burgerLaufband);
        BurgerKraft skBurger2 = new BurgerKraft(burgerLaufband);
        BurgerKraft skBurger3 = new BurgerKraft(burgerLaufband);

        Thread kg = new Thread(kundengenerator);

        Thread sk1 = new Thread(skTresen1);
        Thread sk2 = new Thread(skTresen2);

        Thread skB1 = new Thread(skBurger1);
        Thread skB2 = new Thread(skBurger2);
        Thread skB3 = new Thread(skBurger3);

        kg.start();
        sk1.start();
        sk2.start();

        skB1.start();
        skB2.start();
        skB3.start();

        /* kundengenerator.run();

        // WIRD NIE ERREICHT????
        skTresen1.run();
        skTresen2.run(); */
    }
}
