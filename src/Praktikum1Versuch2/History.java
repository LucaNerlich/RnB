package Praktikum1Versuch2;

import java.util.ArrayList;

/**
 * collects and prints strings to the console.
 * "needed" to ensure the correct order of console prints during multithreading.
 */
public class History implements Runnable {

    private Warteschlange warteschlange;
    private static History instance = null;

    // sammelt die Wartezeiten fuer die Berechnung der min. max und average.
    private ArrayList waitingTimes = new ArrayList();


    private History() {
        warteschlange = new Warteschlange(50);
    }

    public synchronized static History getInstance() {
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

    public synchronized void addWaitingTimeToList(long waitingtime){
        waitingTimes.add(waitingtime);
    }

    public long getAverageWaitingTime(){
        long averageTime = 0;
        long cache;

        //todo calculater average

        return averageTime;
    }
}
