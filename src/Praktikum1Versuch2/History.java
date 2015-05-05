package Praktikum1Versuch2;

import java.util.ArrayList;

/**
 * collects and prints strings to the console.
 * "needed" to ensure the correct order of console prints during multithreading.
 */
public class History implements Runnable {

    private Warteschlange warteschlange;
    private static History instance = null;
    private int minimalTime = Integer.MAX_VALUE;
    private int maximalTime = 0;
    private int averageTime;

    // sammelt die Wartezeiten fuer die Berechnung der min. max und average.
    private ArrayList<Integer> waitingTimes = new ArrayList();


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

    public synchronized void addWaitingTimeToList(int waitingtime){
        waitingTimes.add(waitingtime);
    }

    public void calculateWaitingTime(){
        averageTime = 0;

        for(int time : waitingTimes){
            if(time < minimalTime){
                minimalTime = time;
            }
            if(time > maximalTime){
                maximalTime = time;
            }
            averageTime += time;
        }
        averageTime = (averageTime / waitingTimes.size());
    }

    public int getMinimalTime() {
        return minimalTime;
    }

    public int getMaximalTime() {
        return maximalTime;
    }

    public int getAverageTime() {
        return averageTime;
    }
}
