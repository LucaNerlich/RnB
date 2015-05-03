package Praktikum1Versuch2;

public class Main {

    public static void main(String[] args) {

        //singleton speichert sich in sich selbst
        History.getInstance();
        Thread tHS = new Thread(History.getInstance());
        tHS.start();

        Warteschlange queue1 = new Warteschlange(10); // haellt Kunden
        Warteschlange queue2 = new Warteschlange(10); // haellt Kunden
        Warteschlange burgerLaufband = new Warteschlange(12); // haellt "Burger"

        Kundengenerator kg = new Kundengenerator(25, queue1, queue2);

        ServiceKraft sk1 = new ServiceKraft(queue1, burgerLaufband);
        ServiceKraft sk2 = new ServiceKraft(queue2, burgerLaufband);

        //singleton speichert sich in sich selbst
        Scheduler.getInstance();
        Scheduler.getInstance().addSK(sk1);
        Scheduler.getInstance().addSK(sk2);

        BurgerBrater bk1 = new BurgerBrater(burgerLaufband);
        BurgerBrater bk2 = new BurgerBrater(burgerLaufband);
        BurgerBrater bk3 = new BurgerBrater(burgerLaufband);

        Thread tKG = new Thread(kg);

        Thread tSK1 = new Thread(sk1);
        Thread tSK2 = new Thread(sk2);

        Thread tBK1 = new Thread(bk1);
        Thread tBK2 = new Thread(bk2);
        Thread tBK3 = new Thread(bk3);

        tKG.start();

        tSK1.start();
        tSK2.start();

        tBK1.start();
        tBK2.start();
        tBK3.start();
    }
}
