package ca.ulaval.glo2004.utilitaires;

import java.io.Serializable;
import java.util.LinkedList;

public class Ligne extends Forme {
    private Pouce gauche, haut, droite, bas;     //coordonnées x1,y1, x2, y2

    public Ligne(Pouce gauche, Pouce haut, Pouce droite, Pouce bas) {
        super(new Pouce(), new Pouce(), new PointPouce());

        this.gauche = gauche.toDouble() > 0 ? gauche : new Pouce(0);
        this.haut = haut.toDouble() > 0 ? haut : new Pouce(0);
        this.droite = droite.toDouble() > 0 ? droite : new Pouce(0);
        this.bas = bas.toDouble() > 0 ? bas : new Pouce(0);

        Pouce centreX = Pouce.min(gauche, droite);
        Pouce centreY = Pouce.min(haut, bas);
        Pouce longueur = Pouce.abs(gauche.diff(droite));
        Pouce hauteur = Pouce.abs(haut.diff(bas));

        setLongueur(longueur);
        setHauteur(hauteur);
        setCentre(new PointPouce(centreX, centreY));

    }

    public Ligne(PointPouce p1, PointPouce p2){
        super(new Pouce(), new Pouce(), new PointPouce());
        this.gauche = p1.getX().toDouble() > 0 ? p1.getX() : new Pouce(0);
        this.haut = p1.getY().toDouble() > 0 ? p1.getY() : new Pouce(0);
        this.droite = p2.getX().toDouble() > 0 ? p2.getX() : new Pouce(0);
        this.bas = p2.getY().toDouble() > 0 ? p2.getY() : new Pouce(0);

        Pouce centreX = Pouce.min(gauche, droite);
        Pouce centreY = Pouce.min(haut, bas);
        Pouce longueur = Pouce.abs(gauche.diff(droite));
        Pouce hauteur = Pouce.abs(haut.diff(bas));

        setLongueur(longueur);
        setHauteur(hauteur);
        setCentre(new PointPouce(centreX, centreY));
    }

    public Pouce getPente(){

        return droite.diff(gauche).toDouble() != 0 ? new Pouce((bas.diff(haut)).diviser(droite.diff(gauche))) : null;
    }

    public Pouce getGauche() {
        return gauche;
    }

    public void setGauche(Pouce gauche) {
        this.gauche = gauche.toDouble() > 0 ? gauche : new Pouce(0);
    }

    public Pouce getHaut() {
        return haut;
    }

    public void setHaut(Pouce haut) {
        this.haut = haut.toDouble() > 0 ? haut : new Pouce(0);
    }

    public Pouce getDroite() {
        return droite;
    }

    public void setDroite(Pouce droite) {
        this.droite = droite.toDouble() > 0 ? droite : new Pouce(0);
    }

    public Pouce getBas() {
        return bas;
    }

    public void setBas(Pouce bas) {
        this.bas = bas.toDouble() > 0 ? bas : new Pouce(0);
    }

    @Override
    protected LinkedList<PointPouce> getListePoints() {
        LinkedList<PointPouce> listePoints = new LinkedList<>();
        listePoints.add(new PointPouce(gauche, haut));
        listePoints.add(getCentre());
        listePoints.add(new PointPouce(droite, bas));

        return listePoints;
    }
}
