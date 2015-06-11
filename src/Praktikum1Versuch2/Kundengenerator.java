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

        while (!(Thread.interrupted())) {
            generateKunden();
            if (kundenGenerated == maxKunden) {
                break;
            }
        }

        // zu viele abgewiesen. Erstellung wird gestoppt.
        if(counterAbgewiesen > 30){
            History.getInstance().addStringToAusgabe("[KG_" + Thread.currentThread().getName() + "] __ Erstellung Abgebrochen. Laden wird geschlossen.");
        Thread.currentThread().interrupt();
        }
    }

    /**
     * Generates Customers as well as Orders every other second.
     * Places them on two Queues.
     */
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

        // make sure that both queues are of similar length
        if (ws1Size <= ws2Size) {
            added = warteschlange1.enter(kunde);
            if (added) {
                kundenGenerated++;

                History.getInstance().addStringToAusgabe("[KG_" + Thread.currentThread().getName() + "] __ Added " + kunde.getName() + " to Queue "
                        + "[" + warteschlange1.getSize() + "; " + warteschlange2.getSize() + "]");
            }
        } else {
            added = warteschlange2.enter(kunde);
            if (added) {
                kundenGenerated++;

                History.getInstance().addStringToAusgabe("[KG_" + Thread.currentThread().getName() + "] __ Added " + kunde.getName() + " to Queue "
                        + "[" + warteschlange2.getSize() + "; " + warteschlange2.getSize() + "]");
            }
        }

        if (!added) {
            counterAbgewiesen++;
            System.err.println("[KG_" + Thread.currentThread().getName() + "] __ Abgewiesen: " + counterAbgewiesen);
        }
    }
}