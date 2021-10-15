package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Polygone;

import java.awt.*;

public abstract class Composante {

    public RoulotteController parent;
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