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

            if (kundenGenerated == maxKunden) {
                break;
            }
        }
    }

    private void generateKunden() {

        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Kunde kunde = new Kunde();
        boolean added;

        int ws1Size = warteschlange1.getSize();
        int ws2Size = warteschlange2.getSize();

        if (ws1Size <= ws2Size) {
            added = warteschlange1.enter(kunde);
            if (added) {
                kundenGenerated++;

                History.getInstance().addStringToAusgabe("[KG] __ Added " + kunde.getName() + " to Queue "
                        + "[" + warteschlange1.getSize() + "; " + warteschlange2.getSize() + "]");

                // System.err.println("[KG] __ Added " + kunde.getName() + " to Queue " + warteschlange1.getWarteschlangeId());
                // System.err.println("Order: " + kunde.getOrder().getOrderId());
                // System.err.println("Schlangengroesse: " + warteschlange1.getSize() + "\n");
            }
        } else {
            added = warteschlange2.enter(kunde);
            if (added) {
                kundenGenerated++;

                History.getInstance().addStringToAusgabe("[KG] __ Added " + kunde.getName() + " to Queue " +
                        warteschlange2.getWarteschlangeId()
                        + "[" + warteschlange1.getSize() + "; " + warteschlange2.getSize() + "]");

                // System.err.println("[KG] __ Added " + kunde.getName() + " to Queue " + warteschlange2.getWarteschlangeId());
                // System.err.println("Order: " + kunde2.getOrder().getOrderId());
                // System.err.println("Schlangengroesse: " + warteschlange2.getSize() + "\n");
            }
        }

        if (!added) {
            counterAbgewiesen++;
            System.err.println("[KG] __ Abgewiesen: " + counterAbgewiesen);
        }
    }
}