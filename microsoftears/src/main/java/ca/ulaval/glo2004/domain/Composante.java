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

    public String toString(){
        String retour;
        switch (type){
            case HAYON:
                retour = "Hayon";
            case MUR_SEPARATEUR:
                retour = "Mur séparateur";
            case PLANCHER:
                retour = "Plancher";
            case POUTRE_ARRIERE:
                retour = "Poutre arrière";
            case RESSORTS:
                retour = "Ressorts";
            case TOIT:
                retour = "Toit";
            case MUR_BRUTE:
                retour = "Mur Brute";
            case AIDE_DESIGN:
                retour = "Aide au design";
            case OUVERTURE_LATERALE:
                retour = "Ouverture latérale";
            case PROFIL_BEZIER:
                retour = "Profil bézier";
            case PROFIL_ELLIPSE:
                retour = "Profil ellipse";
            case CONTREPLAQUE_EXTERIEUR:
                retour = "Contreplaqué extérieur";
            case CONTREPLAQUE_INTERIEUR:
                retour = "Contreplaqué intérieur";
            case MUR_PROFILE:
                retour = "Mur profilé";
            default:
                retour = "erreur définir le type dans le constructeur de la composante";
        }
        return retour;
    }
}