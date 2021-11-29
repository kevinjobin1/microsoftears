package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.composante.TypeComposante;

public interface IComposante {

    public int[] getValeurs();
    public TypeComposante getType();
    public String[] getNomsAttributs();
    public boolean estVisible();
    public boolean getMode();
}
