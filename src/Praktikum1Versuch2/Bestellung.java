package Praktikum1Versuch2;

public class Bestellung {

    private int anzahlBurgerBestellt;

    public Bestellung(int anzahlBurgerBestellt){
        this.anzahlBurgerBestellt = anzahlBurgerBestellt;
    }

    public int getAnzahlBurgerBestellt() {
        return anzahlBurgerBestellt;
    }

    public void setAnzahlBurgerBestellt(int anzahlBurgerBestellt) {
        this.anzahlBurgerBestellt = anzahlBurgerBestellt;
    }

    public void oneBurgerFinished(){
        anzahlBurgerBestellt--;
    }
}
