package Praktikum1Versuch2;

public class Bestellung {

    private Kunde owner;

    private int anzahlBurgerBestellt;

    public Bestellung(Kunde owner, int anzahlBurgerBestellt){
        this.owner = owner;
        this.anzahlBurgerBestellt = anzahlBurgerBestellt;
    }

    public int getAnzahlBurgerBestellt() {
        return anzahlBurgerBestellt;
    }

    public Kunde getOwner() {
        return owner;
    }
}
