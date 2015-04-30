package Praktikum1Versuch2;

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

        // add timer, then interrupt?
        while (!(Thread.interrupted())) {
            generateKunden();
            try {
                Thread.sleep((long) (Math.random() * 500) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (kundenGenerated == maxKunden) {
                break;
            }
        }
    }

    private void generateKunden() {

        Kunde kunde = new Kunde();
        boolean added = false;
        double rndm = Math.random(); // 50/50 auf die beiden Quese verteilen. Else sollte nicht erreicht werden.
        if (rndm < 0.5) {
            added = warteschlange1.enter(kunde);
            if (added) {
                kundenGenerated++;
                System.err.println("[KG] __ Added " + kunde.getName() + "  to Queue " + warteschlange1.getWarteschlangeId());
                // System.err.println("Order: " + kunde.getOrder().getOrderId());
                // System.err.println("Schlangengroesse: " + warteschlange1.getSize() + "\n");
            }
        } else if (rndm > 0.5) {
            added = warteschlange2.enter(kunde);
            if (added) {
                kundenGenerated++;
                System.err.println("[KG] __ Added " + kunde.getName() + " to Queue " + warteschlange2.getWarteschlangeId());
                //  System.err.println("Order: " + kunde2.getOrder().getOrderId());
                // System.err.println("Schlangengroesse: " + warteschlange2.getSize() + "\n");
            }
        }

        if (!added) {
            counterAbgewiesen++;
            System.err.println("[KG] __ Abgewiesen: " + counterAbgewiesen);
        }
    }
}