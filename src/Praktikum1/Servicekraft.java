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
    private Order[] bestellungen;

    public Servicekraft(Warteschlange warteschlangeZugeordnet, Order[] bestellungen) {
        this.warteschlangeZugeordnetKunden = warteschlangeZugeordnet;
        this.bestellungen = bestellungen;

    }

    @Override
    public void run() {
        getNextOrder();
    }

    public synchronized void getNextOrder(){
            //hier in array 1 oder 2 fuellen.
        Kunde kunde = (Kunde) warteschlangeZugeordnetKunden.remove();
            //Order order = kunde.getBestellung();

    }
}
