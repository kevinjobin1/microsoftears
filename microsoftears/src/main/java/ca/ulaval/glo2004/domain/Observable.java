package ca.ulaval.glo2004.domain;

public interface Observable {

    public void registerObserver(RoulotteControllerObserver newListener);

    public void unregisterObserver(RoulotteControllerObserver listener);

}