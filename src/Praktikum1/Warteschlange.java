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
        while (warteschlange.size() >= maxSize) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        // Alle Threads die in der Warteschlange (wait) werden geweckt
        warteschlange.add(item);
        System.err
                .println("<= ENTER: "
                        + Thread.currentThread().getName()
                        + " hat ein Objekt in dem Puffer: "
                        + warteschlangeId
                        + " gelegt. Aktuelle Puffergroesse: "
                        + warteschlange.size()
                        + "\n");
        this.notifyAll();
        return true;
    }

    /**
     * Consumer (Verbraucher) rufen die Methode REMOVE auf. Entfernt und returned das erste
     * Element der Liste / Buffer
     */
    public synchronized E remove() {
        E item;
        while (warteschlange.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                return null;
            }
        }
        item = warteschlange.removeFirst();
        System.err
                .println("<= REMOVE: "
                        + Thread.currentThread().getName()
                        + " hat ein Objekt aus dem Puffer: "
                        + warteschlangeId
                        + " genommen. Aktuelle Puffergroesse: "
                        + warteschlange.size()
                        + "\n");
        // informiert alle wartenden Threads
        this.notifyAll();

        return item;
    }

    public synchronized E getFirst() {
        E item;

        while (warteschlange.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                return null;
            }
        }
        item = warteschlange.getFirst();
        System.err
                .println("<= GETFIRST: "
                        + Thread.currentThread().getName()
                        + " hat ein Objekt aus dem Puffer: "
                        + warteschlangeId
                        + " angeguckt.");
        this.notifyAll();
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
