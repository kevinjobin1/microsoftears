package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;

public abstract class Composante extends Area {

    protected RoulotteController parent;
    private TypeComposante type;
    private Polygone polygone;
    private Color couleur;
    private boolean estVisible;

    public Composante(RoulotteController parent) {
        this.parent = parent;
        this.couleur = Color.WHITE;
        this.estVisible = true;
    }

    public void afficher(Graphics2D g2d){
        if (estVisible){
            GeneralPath path = new GeneralPath();
            LinkedList<PointPouce> polygoneList = this.getPolygone().getListePoints();
            double x, y;
            for (int i = 0; i < polygoneList.size(); i++){
                x = parent.xVersEcran(polygoneList.get(i).getX());
                y = parent.yVersEcran(polygoneList.get(i).getY());
                if(i == 0) {
                    path.moveTo(x, y);
                }
                else{
                    path.lineTo(x ,y);
                }
            }
            path.closePath();
            g2d.setColor(couleur);
            g2d.fill(path);
            g2d.setColor(Color.BLACK);
            g2d.draw(path);
        }

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
        return type.toString();
    }

    public void setVisible(boolean b){
        this.estVisible = b;
    }
}