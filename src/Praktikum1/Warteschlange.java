package Praktikum1;

import java.util.LinkedList;

public class Warteschlange<E> {

    private int warteschlangeId;
    private int maxSize;
    private int counter;

    private int kundenCounter = 0;


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
    public synchronized void enter(E item) {
      if(warteschlange.size() <= maxSize){

        warteschlange.add(item);

          System.err.println("Added Kunde to Queue " + warteschlangeId);
          System.err.println("Schlangengroesse: " + warteschlange.size() + "\n");
        // Alle Threads die in der Warteschlange (wait) werden geweckt
        this.notifyAll();
        }
        else{
            System.err.println("Kunde abgewiesen, Schlange " + warteschlangeId + " voll!");
        }
    }

    /**
     * Consumer (Verbraucher) rufen die Methode REMOVE auf Entfernt das erste
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
        // Methode von LinkedList
        item = warteschlange.removeFirst();
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
