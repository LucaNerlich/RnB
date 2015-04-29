package Praktikum1;
/**
 * Praktikum WIAD, WS 2014/2015
 * Gruppe: Luca Nerlich (Lucasteffen.Nerlich@haw-hamburg.de)
 * 		   Daniel Sommerlig (Daniel.Sommerlig@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1
 * mainNeu.java
 */

public class Servicekraft implements Runnable {

    private Order currentOrder;
    private Warteschlange warteschlangeZugeordnetKunden;
    private Warteschlange warteschlangeOrder;

    public Servicekraft(Warteschlange warteschlangeZugeordnet, Warteschlange warteschlangeOrder) {
        this.warteschlangeZugeordnetKunden = warteschlangeZugeordnet;
        this.warteschlangeOrder = warteschlangeOrder;

    }

    @Override
    public void run() {
        getNextOrder();
    }

    public synchronized void getNextOrder(){
        if(warteschlangeOrder.getSize() <= 2){

        Kunde kunde = (Kunde) warteschlangeZugeordnetKunden.remove();
            Order order = kunde.getBestellung();
        }
    }
}
