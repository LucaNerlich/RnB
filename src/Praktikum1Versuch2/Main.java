package Praktikum1Versuch2;

public class Main {

    public static void main(String[] args) {

        Warteschlange queue1 = new Warteschlange(1, 10); // haellt Kunden
        Warteschlange queue2 = new Warteschlange(2, 10); // haellt Kunden
        Warteschlange burgerQueue = new Warteschlange(3, 12); // haellt "Burger"
        Warteschlange bestellungenQueue = new Warteschlange(4, 2); // haellt int

        Kundengenerator kg = new Kundengenerator(25, queue1, queue2);

        ServiceKraft sk1 = new ServiceKraft(queue1, bestellungenQueue);
        ServiceKraft sk2 = new ServiceKraft(queue2, bestellungenQueue);

        Thread tKG = new Thread(kg);
        Thread tSK1 = new Thread(sk1);
        Thread tSK2 = new Thread(sk2);

        tKG.start();
        tSK1.start();
        tSK2.start();
    }
}
