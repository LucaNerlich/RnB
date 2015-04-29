package Praktikum1;


/**
 * Praktikum RB, SS 2015
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 2
 * Main.java
 *
 * Application class for our list
 *
 * @author (Daniel.Sommerlig@haw-hamburg.de) &
 *         (Lucasteffen.Nerlich@haw-hamburg.de)
 */

public class Main {

    public static void main(String[] args) {
        Kueche kueche = new Kueche();
        Kundengenerator kundengenerator = new Kundengenerator(50); // 50 Kunden

        Warteschlange kundenWS1 = new Warteschlange(10);
        Warteschlange kundenWS2 = new Warteschlange(10);
        Warteschlange burgerLaufband = new Warteschlange(12);
        Warteschlange bestellungen = new Warteschlange(2); // Als ARRAY UMSETZEN

        Servicekraft skTresen1 = new Servicekraft(kundenWS1, bestellungen);
        Servicekraft skTresen2 = new Servicekraft(kundenWS2, bestellungen);

        BurgerKraft skBurger1 = new Servicekraft(burgerLaufband, bestellungen);
        BurgerKraft skBurger2 = new Servicekraft(burgerLaufband, bestellungen);
        BurgerKraft skBurger3 = new Servicekraft(burgerLaufband, bestellungen);




        System.out.println("test");
    }
}
