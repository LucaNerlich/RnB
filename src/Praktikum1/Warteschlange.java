package Praktikum1;

import java.util.LinkedList;

public class Warteschlange<E> {

    private static int warteschlangeId = 0;
    private int maxSize;
    private int counter;

    private LinkedList<E> warteschlange;

    public Warteschlange(int maxSize) {
        this.warteschlange = new LinkedList<E>();
        warteschlangeId++;
        this.maxSize = maxSize;
    }

    /**
     * Producer (Erzeuger) rufen die Methode enter() auf Diese legt das item in
     * den Puffer mit der add() Methode Synchronized da es sich um einen
     * kritischen Bereich handelt, wird in die Pufferliste geschrieben ->
     * Monitor
     **/
    public synchronized void enter(E item) {
        // Prueft ob noch Platz im Puffer ist, falls nicht geht der Thread in
        // die Warteschlange
        while (warteschlange.size() >= maxSize) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        // Mehtode von LinkedList
        warteschlange.add(item);
        System.err
                .println("=> ENTER: "
                        + Thread.currentThread().getName()
                        + " hat ein Objekt in den Puffer gelegt. Aktuelle Puffergroesse: "
                        + warteschlange.size()
                        + "\n");
        // Alle Threads die in der Warteschlange werden geweckt
        this.notifyAll();
    }

    /**
     * Consumer (Verbraucher) rufen die Methode REMOVE auf Entfernt das erste
     * Element der Liste / Buffer
     **/
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
        // Methode von LinkedList
        item = warteschlange.removeFirst();
        System.err
                .println("<= REMOVE: "
                        + Thread.currentThread().getName()
                        + " hat ein Objekt aus dem Puffer entnommen. Aktuelle Puffergroesse: "
                        + warteschlange.size());
        // informiert alle wartenden Threads
        this.notifyAll();
        return item;
    }

    public synchronized void counter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public int getSize(){
        return warteschlange.size();
    }
}
