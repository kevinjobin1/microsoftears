package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Polygone;

import java.awt.*;
import java.awt.geom.Area;

public abstract class Composante extends Area {

    protected RoulotteController parent;
    private TypeComposante type;
    private Polygone polygone;
    private Color couleur;

    public Composante(RoulotteController parent) {
        this.parent = parent;
        this.couleur = Color.BLACK;
    }

    public RoulotteController getParent() {
        return parent;
    }

    public void setParent(RoulotteController parent) {
        this.parent = parent;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public Polygone getPolygone() {
        return polygone;
    }

    protected void setPolygone(Polygone polygone) {
        this.polygone = polygone;
    }

    public TypeComposante getType() {
        return type;
    }

    protected void setType(TypeComposante type) {
        this.type = type;
    }
}