package Praktikum1;

public class Kundengenerator implements Runnable {
    private int kundenCounter = 0;
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
        while(kundenCounter <= maxKunden) {
            generateKunden();
            try {
                Thread.sleep((long) (Math.random() * 3000) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateKunden() {
        Kunde kunde = new Kunde();
        if(warteschlange1.getSize() < warteschlange2.getSize()){
            warteschlange1.enter(kunde);
            kundenCounter++;
            System.err.println("Added Kunde" + kunde.getKundeId() + " to Warteschlange 1");
            System.err.println("Schlangengroesse: " + warteschlange1.getSize());
        }else{
            warteschlange2.enter(kunde);
            kundenCounter++;
            System.err.println("Added Kunde" + kunde.getKundeId() + " to Warteschlange 2");
            System.err.println("Schlangengroesse: " + warteschlange2.getSize());
        }
    }
}
