package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.ArrayList;

public class Profil {

    private PointPouce[] centresEllipse = new PointPouce[4];
    private Pouce[] hauteursEllipse = new Pouce[4];
    private Pouce[] longueursEllipse = new Pouce[4];
    private ArrayList<PointPouce> pointsBezier;
    private boolean mode;
}
