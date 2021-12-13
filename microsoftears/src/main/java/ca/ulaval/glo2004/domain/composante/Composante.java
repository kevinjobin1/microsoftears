package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.LinkedList;

public abstract class Composante implements IComposante, Serializable {

    protected RoulotteController parent;
    private TypeComposante type;
    private Polygone polygone;
    private Color couleurInitiale;
    private Color couleur;
    private float transparence ;
    private float transparenceInitiale;
    private Color strokeCouleurInitiale;
    private Color strokeCouleur;
    private boolean estVisible;
    private boolean estChoisie;
    private boolean estAjoute = true;
    private boolean afficherPosition;

    public Composante(RoulotteController parent) {
        this.parent = parent;
        this.transparenceInitiale = 0.75f;
        this.transparence = 0.75f;
        this.couleur = Color.WHITE;
        this.couleurInitiale = Color.WHITE;
        this.strokeCouleurInitiale = new Color(75,75,75);
        this.strokeCouleur = new Color(75,75,75);
        this.estVisible = true;
        this.afficherPosition = false;
    }

    public Path2D getPath(){
        Path2D path = new Path2D.Double();
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
        return path;
    }

    public Area getArea(){
        return new Area(getPath());
    }

    static AlphaComposite definirComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }

    public void afficher(Graphics2D g2d){
        if (estVisible){
            if (parent.afficherLabel()){
            if (getAfficherPosition()) {
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawString(this.toString(), (float) parent.getPositionSouris().getX() + 30, (float) parent.getPositionSouris().getY() - 30);
            }}

            Area area = getArea();
            Composite compositeInitial = g2d.getComposite();
            g2d.setComposite(definirComposite(getTransparence()));
            g2d.setPaint(getCouleur());
            g2d.fill(area);
            g2d.setComposite(compositeInitial);
            g2d.setColor(getStrokeCouleur());
            //g2d.setStroke(getStroke());
            g2d.draw(area);
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

    public void setCouleurInitiale(Color couleurInitiale) {
        this.couleurInitiale = couleurInitiale;
        this.couleur = couleurInitiale;
    }

    public void setTransparenceInitiale(float transparenceInitiale) {
        this.transparenceInitiale = transparenceInitiale;
        this.transparence = transparenceInitiale;
    }

    public void setStrokeCouleurInitiale(Color strokeCouleurInitiale) {
        this.strokeCouleurInitiale = strokeCouleurInitiale;
        this.strokeCouleur = strokeCouleurInitiale;
    }

    public float getTransparenceInitiale() {
        return transparenceInitiale;
    }

    public void setTransparence(float alpha){
        this.transparence = alpha;
    }

    public float getTransparence(){
        return transparence;
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

    public boolean estVisible() {
        return estVisible;
    }

    public abstract PointPouce getCentre();

    public void setVisible(boolean b) {
        this.estVisible = b;
    }

    public abstract int[] getValeurs();

    public abstract void translate(PointPouce delta);

    public abstract void snapToGrid(PointPouce pointGrille);

    public Color getStrokeCouleurInitiale() {
        return strokeCouleurInitiale;
    }

    public Color getStrokeCouleur() {
        return strokeCouleur;
    }

    public void setStrokeCouleur(Color strokeCouleur) {
        this.strokeCouleur = strokeCouleur;
    }

    public void resetTransparence(){
        this.transparence = transparenceInitiale;
    }

    public void resetCouleur(){
        this.couleur = couleurInitiale;
    }

    public void resetStrokeCouleur(){
        this.strokeCouleur = strokeCouleurInitiale;
    }

    public boolean estChoisie() {
        return estChoisie;
    }

    public void setChoisie(boolean estChoisie) {
        if (estChoisie){
            this.couleur = new Color(255,60,60);
            this.transparence = 0.9f;
        }
        else {
            resetCouleur();
            resetTransparence();
        }
        this.estChoisie = estChoisie;
    }

    public boolean getAfficherPosition() {
        return afficherPosition;
    }

    public void setAfficherPosition(boolean afficherPosition) {
        this.afficherPosition = afficherPosition;
    }

    public String getSVG(){
    String svgPath = "<path fill=\"none\" stroke-width=\"2\" stroke=\"black\" ";
        
        // path svg attributs d, une liste de points
        double[] point;
        LinkedList<PointPouce> polygoneList = this.getPolygone().getListePoints();
        for (int i = 0; i < polygoneList.size(); i++){
            point = parent.getPositionEcran(polygoneList.get(i));
            if(i == 0) {
                svgPath += "d=\"M" + Math.round(point[0]) + " " + Math.round(point[1]) + " ";
            }
            else if (i == polygoneList.size() -1){
                svgPath += "L" + Math.round(point[0]) + " " + Math.round(point[1]) + "Z\"/>";
            }
            else {
                svgPath += "L" + Math.round(point[0]) + " " + Math.round(point[1]) + " ";
            }
        }
        
        return svgPath;

    }

    public int getIndex(){
        return parent.getIndexComposante(this.getType());
    }

    public boolean estAjoute() {
        return estAjoute;
    }

    public void estAjoute(boolean estAjoute) {
        this.estAjoute = estAjoute;
    }
}