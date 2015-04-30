package Praktikum1Versuch2;

public class History implements Runnable {

    private Warteschlange warteschlange;
    private static History instance = null;


    private History() {
        warteschlange = new Warteschlange(5, 50);
    }

    public static History getInstance() {
        if (instance == null) {
            History.instance = new History();
        }
        return History.instance;
    }


    @Override
    public void run() {
        while (!(Thread.interrupted())) {
            System.err.println(warteschlange.remove());
        }
    }

    public synchronized void addStringToAusgabe(String message) {
        warteschlange.enter(message);
    }


}
