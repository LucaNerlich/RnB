package Praktikum1Versuch2;

public class Main {

    public static void main(String[] args) {

        Warteschlange queue1 = new Warteschlange(1, 10);
        Warteschlange queue2 = new Warteschlange(2, 10);
        Kundengenerator kg = new Kundengenerator(25, queue1, queue2);

        Thread tKG = new Thread(kg);

        tKG.start();
    }
}
