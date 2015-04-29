package Praktikum1;

public class BurgerKraft {

    private Warteschlange burgerLaufband;
    private Order[] bestellungen;

    public BurgerKraft(Warteschlange burgerLaufband, Order[] bestellungen) {
        this.burgerLaufband = burgerLaufband;
        this.bestellungen = bestellungen;

    }
}
