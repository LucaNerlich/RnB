package Praktikum1Versuch2;

public class Main {

    public static void main(String[] args) {

        Warteschlange queue1 = new Warteschlange(1, 10);
        Warteschlange queue2 = new Warteschlange(2, 10);
        Warteschlange burgerQueue = new Warteschlange(3, 12);

        Kundengenerator kg = new Kundengenerator(25, queue1, queue2);

        ServiceKraft sk1 = new ServiceKraft(queue1);
        ServiceKraft sk2 = new ServiceKraft(queue2);

        Thread tKG = new Thread(kg);
        Thread tSK1 = new Thread(sk1);
        Thread tSK2 = new Thread(sk2);

        tKG.start();
        tSK1.start();
        tSK2.start();
    }
}
