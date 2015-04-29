package Praktikum1;

public class Kundengenerator implements Runnable {
    private int maxKunden;
    private Warteschlange warteschlange1;
    private Warteschlange warteschlange2;

    public Kundengenerator(int maxKunden, Warteschlange w1, Warteschlange w2) {
        this.maxKunden = maxKunden;
        warteschlange1 = w1;
        warteschlange2 = w2;
    }

    @Override
    public void run() {

        // add timer, then interrupt.
        while (!(Thread.interrupted())) {
            generateKunden();
            try {
                Thread.sleep((long) (Math.random() * 1500) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateKunden() {
        Kunde kunde = new Kunde();
        // FUNKTIONIERT NICHT. Sobald id 0 voll ist, haengt er sich auf.
        if ((warteschlange1.getSize() != warteschlange1.getMaxSize()) && warteschlange1.getSize() <= warteschlange2.getSize()) {
            warteschlange1.enter(kunde);
        }

        if ((warteschlange2.getSize() != warteschlange2.getMaxSize()) && warteschlange2.getSize() >= warteschlange1.getSize()) {
            warteschlange2.enter(kunde);
        }
        else{
            System.out.println("Schlangen voll, Kunde abgewiesen.");
        }
    }
}
