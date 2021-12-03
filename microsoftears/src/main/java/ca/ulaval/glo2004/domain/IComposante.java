package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Pouce;

public interface IComposante {

    public int[] getValeurs();
    public TypeComposante getType();
    public String[] getNomsAttributs();
    public boolean estVisible();
    public boolean[] getModes();
    public Pouce[] getLimit();
}
