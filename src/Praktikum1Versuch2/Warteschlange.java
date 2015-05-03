package Praktikum1Versuch2;

import java.util.LinkedList;

public class Warteschlange<E> {

    private int maxSize;


    private LinkedList<E> warteschlange;

    public Warteschlange(int maxSize) {
        this.warteschlange = new LinkedList<E>();
        this.maxSize = maxSize;
    }


    public synchronized boolean enter(E item) {
        boolean added = false;
        if (warteschlange.size() >= maxSize) {
            // do nothing
        } else {
            warteschlange.add(item);

            // Alle Threads die in der Warteschlange (wait) werden geweckt
            this.notifyAll();
            added = true;
        }
        return added;
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

        this.notifyAll();
        return item;
    }

    public int getSize() {
        return warteschlange.size();
    }
}
