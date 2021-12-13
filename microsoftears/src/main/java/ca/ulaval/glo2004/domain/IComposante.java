package ca.ulaval.glo2004.domain;

public interface IComposante {

    int[] getValeurs();
    TypeComposante getType();
    String[] getNomsAttributs();
    boolean estVisible();
    Object[] getModes();
    boolean estAjoute();
}
