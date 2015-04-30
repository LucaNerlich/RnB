package Praktikum1;

public class Kundengenerator implements Runnable {
    private int maxKunden;
    private int counterAbgewiesen = 0;
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
        boolean added = false;
        // FUNKTIONIERT NICHT. Sobald id 0 voll ist, haengt er sich auf.
        int rndm = (int) ((Math.random() * 2) + 1); // 50/50 auf die beiden Quese verteilen. Else sollte nicht erreicht werden.
        if (rndm == 1) {
            added = warteschlange1.enter(kunde);
            if (added) {
                System.err.println("Added Kunde " + kunde.getKundeId() + "  to Queue " + warteschlange1.getWarteschlangeId());
                System.err.println("Schlangengroesse: " + warteschlange1.getSize() + "\n");
            }
        } else if (rndm == 2) {
            added = warteschlange2.enter(kunde);
            if (added) {
                System.err.println("Added Kunde " + kunde.getKundeId() + " to Queue " + warteschlange2.getWarteschlangeId());
                System.err.println("Schlangengroesse: " + warteschlange2.getSize() + "\n");
            }
        } else {
            System.out.println("Math Random Error");
        }

        if (!added) {
            counterAbgewiesen++;
        }
    }
}
