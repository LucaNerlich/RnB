package Praktikum1;

import java.util.LinkedList;

public class Warteschlange<E> {

    private int warteschlangeId;
    private int maxSize;



    private LinkedList<E> warteschlange;

    public Warteschlange(int id, int maxSize) {
        this.warteschlange = new LinkedList<E>();
        warteschlangeId = id;
        this.maxSize = maxSize;
    }

    /**
     * Producer (Erzeuger) rufen die Methode enter() auf Diese legt das item in
     * den Puffer mit der add() Methode Synchronized da es sich um einen
     * kritischen Bereich handelt, wird in die Pufferliste geschrieben ->
     * Monitor
     */
    public synchronized boolean enter(E item) {
        if (warteschlange.size() < maxSize) {

            warteschlange.add(item);

            // Alle Threads die in der Warteschlange (wait) werden geweckt
            this.notifyAll();
            return true;
        } else {
           // System.err.println("Schlange " + warteschlangeId + " voll!");
            return false;
        }
    }

    /**
     * Consumer (Verbraucher) rufen die Methode REMOVE auf. Entfernt und returned das erste
     * Element der Liste / Buffer
     */
    public synchronized E remove() {
        E item;
        if (warteschlange.size() == 0) {
           //  System.err.println("Couldnt remove First, Queue is empty.");
            item = null;
        } else {
            item = warteschlange.removeFirst();
            // informiert alle wartenden Threads
            this.notifyAll();
        }
        return item;
    }

    public synchronized E getFirst() {
        E item;

        if (warteschlange.size() == 0) {
            // System.err.println("Couldnt get First, Queue is empty.");
            item = null;
        } else {
            item = warteschlange.getFirst();
            this.notifyAll();
        }
        return item;
    }

    public int getSize() {
        return warteschlange.size();
    }

    public int getWarteschlangeId() {
        return warteschlangeId;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
