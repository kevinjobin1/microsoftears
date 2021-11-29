package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;

public abstract class Composante implements IComposante {

    protected RoulotteController parent;
    private TypeComposante type;
    private Polygone polygone;
    private Color couleur;
    private float transparence = 0.75f;
    private boolean estVisible;
    private Color couleurInitiale;


    public Composante(RoulotteController parent) {
        this.parent = parent;
        this.couleur = Color.WHITE;
        this.couleurInitiale = Color.WHITE;
        this.estVisible = true;
    }

    public void resetCouleur(){
        this.setCouleur(couleurInitiale);
    }

    static AlphaComposite definirComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }

    public void afficher(Graphics2D g2d){
        if (estVisible){
            GeneralPath path = new GeneralPath();
            LinkedList<PointPouce> polygoneList = this.getPolygone().getListePoints();
            double[] point;
            for (int i = 0; i < polygoneList.size(); i++){
                 point = parent.getPositionEcran(polygoneList.get(i));
                if(i == 0) {
                    path.moveTo(point[0], point[1]);
                }
                else{
                    path.lineTo(point[0] ,point[1]);
                }
            }
            path.closePath();
            Composite compositeInitial = g2d.getComposite();
            g2d.setComposite(definirComposite(transparence));
            g2d.setPaint(couleur);
            g2d.fill(path);
            g2d.setComposite(compositeInitial);
            g2d.setColor(Color.BLACK);
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

    public Color getCouleurInitiale() {
        return couleurInitiale;
    }

    public void setCouleurInitiale(Color couleur) {
        this.couleurInitiale = couleur;
    }


    public void setTransparence(float alpha){
        this.transparence = alpha;
    }

    public float getTransparence(){
        return transparence;
    }

    public void resetTransparence(){
        this.transparence = 1.0f;
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

    public abstract PointPouce getCentre();

    public void estVisible(boolean b) {
        this.estVisible = b;
    }

    public abstract int[] getValeurs();

    public abstract void translate(PointPouce delta);
}