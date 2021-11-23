package ca.ulaval.glo2004.domain.roulotte;

public interface Observable {

    public void registerObserver(RoulotteControllerObserver newListener);

    public void unregisterObserver(RoulotteControllerObserver listener);

}