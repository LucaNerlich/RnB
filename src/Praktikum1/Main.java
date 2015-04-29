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
        Kueche kueche = new Kueche();

        Warteschlange kundenWS1 = new Warteschlange(0, 1); // ID = 1 wichtig fuer Order zuordnung.
        Warteschlange kundenWS2 = new Warteschlange(1, 10); // ID = 2
        Warteschlange burgerLaufband = new Warteschlange(2, 12); // ID = 3

        Order[] bestellungen = new Order[2];
        int[] acceptedOrdersOverview = new int[2];

        Kundengenerator kundengenerator = new Kundengenerator(50, kundenWS1, kundenWS2); // 50 Kunden

        Servicekraft skTresen1 = new Servicekraft(0, kundenWS1, bestellungen, acceptedOrdersOverview);
        Servicekraft skTresen2 = new Servicekraft(1, kundenWS2, bestellungen, acceptedOrdersOverview);

        System.out.println(skTresen1.getId());
        System.out.println(skTresen2.getId());

        BurgerKraft skBurger1 = new BurgerKraft(burgerLaufband, bestellungen);
        BurgerKraft skBurger2 = new BurgerKraft(burgerLaufband, bestellungen);
        BurgerKraft skBurger3 = new BurgerKraft(burgerLaufband, bestellungen);

        kundengenerator.run();
        skTresen1.run();
        skTresen2.run();
        System.out.println("test");
    }
}
