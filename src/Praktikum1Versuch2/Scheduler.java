package Praktikum1Versuch2;

public class Scheduler {

    public Scheduler() {

    }

    // eventuell synchro und wait?
    public boolean isMyTurn(ServiceKraft self, ServiceKraft kollege) {
        // hat der andere 3 weniger als ich, lass ihm vortritt
        boolean myTurn = !((kollege.getCounterFertigeBestellungen() - self.getCounterFertigeBestellungen()) < -3);

        return myTurn;
    }
}
