package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.ArrayList;

public class Profil {

    private Ellipse[] ellipses = new Ellipse[4];
    private ArrayList<PointPouce> pointsBezier;
    private boolean mode;
}
