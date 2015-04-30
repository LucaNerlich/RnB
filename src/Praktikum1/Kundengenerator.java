package Praktikum1;

public class Kundengenerator implements Runnable {
    private int maxKunden;
    private int counterAbgewiesen = 0;
    private int kundenGenerated = 0;
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
                Thread.sleep((long) (Math.random() * 5500) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(kundenGenerated == maxKunden){
                break;
            }
        }
    }

    private void generateKunden() {

        boolean added = false;
        double rndm = Math.random(); // 50/50 auf die beiden Quese verteilen. Else sollte nicht erreicht werden.
        if (rndm < 0.5) {
            Kunde kunde = new Kunde();
            added = warteschlange1.enter(kunde);
            if (added) {
                kundenGenerated++;
                System.err.println("Added Kunde " + kunde.getKundeId() + "  to Queue " + warteschlange1.getWarteschlangeId());
                System.err.println("Schlangengroesse: " + warteschlange1.getSize() + "\n");
            } else {
                //System.err.println("SCHLANGE 1 VOLL");
            }
        } else if (rndm > 0.5) {
            Kunde kunde2 = new Kunde();
            added = warteschlange2.enter(kunde2);
            if (added) {
                kundenGenerated++;
                System.err.println("Added Kunde " + kunde2.getKundeId() + " to Queue " + warteschlange2.getWarteschlangeId());
                System.err.println("Schlangengroesse: " + warteschlange2.getSize() + "\n");
            } else {
                //System.err.println("SCHLANGE 2 VOLL");
            }
        }

        if (!added) {
            counterAbgewiesen++;
        }
    }
}
