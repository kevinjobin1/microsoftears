package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.roulotte.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;

public abstract class Composante {

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

    static AlphaComposite definirComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
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
            g2d.setColor(Color.DARK_GRAY);
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
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

    public boolean estVisible() {
        return estVisible;
    }
}