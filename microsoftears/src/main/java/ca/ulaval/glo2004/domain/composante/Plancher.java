package ca.ulaval.glo2004.domain.composante;


import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static ca.ulaval.glo2004.utilitaires.ImageDesign.toBufferedImage;

public class Plancher extends Composante {
    private Pouce epaisseur;
    private Pouce margeAvant;
    private Pouce margeArriere;
    private Rectangle rectangle;

    public Plancher(RoulotteController parent, Pouce epaisseur, Pouce margeAvant, Pouce margeArriere) {
        super(parent);
        this.epaisseur = epaisseur;
        this.margeAvant = margeAvant;
        this.margeArriere = margeArriere;
        this.rectangle = new Rectangle(this.getLongueur(), this.epaisseur, this.getCentre());
        this.setCouleurInitiale(new Color(250,220,20));
        this.setCouleur(getCouleurInitiale());
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.PLANCHER);
    }

    public Plancher(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(1,1,4);
        this.margeAvant = new Pouce(5,0,1);
        this.margeArriere = new Pouce(5,0,1);
        this.rectangle = new Rectangle(this.getLongueur(), this.epaisseur, this.getCentre());
        this.setCouleurInitiale(new Color(250,220,20));
        this.setCouleur(getCouleurInitiale());
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.PLANCHER);
    }
    /** Constructeur copie */
    public Plancher(Plancher copie){
        super(copie.parent);
        this.epaisseur = copie.getEpaisseur();
        this.margeAvant = copie.getMargeAvant();
        this.margeArriere = copie.getMargeArriere();
        this.rectangle = new Rectangle(getLongueur(), epaisseur, getCentre());
        this.setCouleurInitiale(copie.getCouleurInitiale());
        this.setCouleur(copie.getCouleur());
        this.setPolygone(rectangle.getPolygone());
        this.setType(copie.getType());

    }

    @Override
    public void afficher(Graphics2D g2d) {
        if (estVisible()){
            if(parent.afficherLabel()){
            if (getAfficherPosition()) {
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawString(this.toString(), (float) parent.getPositionSouris().getX() + 30, (float) parent.getPositionSouris().getY() - 30);
            }}

            Composite compositeInitial = g2d.getComposite();
            Area area = getArea();
            g2d.setComposite(definirComposite(getTransparence()));
            g2d.setPaint(getCouleur());
            g2d.fill(area);
            g2d.setComposite(compositeInitial);
        }


    }

    public Pouce getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Pouce epaisseur) {
        this.epaisseur = epaisseur;
    }

    public Pouce getMargeAvant() {
        return margeAvant;
    }

    public void setMargeAvant(Pouce margeAvant) {
        this.margeAvant = margeAvant;
    }

    public Pouce getMargeArriere() {
        return margeArriere;
    }

    public void setMargeArriere(Pouce margeArriere) {
        this.margeArriere = margeArriere;
    }

    @Override
    public PointPouce getCentre(){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        Pouce x = mur.getCentre().getX().add(mur.getLongueur().diviser(2)).diff(margeAvant).diff(getLongueur().diviser(2));
        Pouce y = mur.getLargeur().diviser(2).add(mur.getCentre().getY()).diff(this.epaisseur.diviser(2));
        return new PointPouce(x,y);

    }

    @Override
    public int[] getValeurs() {
        return new int[]{epaisseur.getPouces(), epaisseur.getNumerateur(), epaisseur.getDenominateur(),
                margeAvant.getPouces(), margeAvant.getNumerateur(), margeAvant.getDenominateur(),
                margeArriere.getPouces(), margeArriere.getNumerateur(), margeArriere.getDenominateur()};
    }

    @Override
    public void translate(PointPouce delta) {
        this.rectangle = new Rectangle(this.getLongueur(), this.epaisseur, this.getCentre());
        this.setPolygone(rectangle.getPolygone());
    }

    @Override
    public void snapToGrid(PointPouce pointGrille){
      translate(pointGrille);
    }

    @Override
    public String[] getNomsAttributs() {
        return new String[]{"??paisseur", "Marge (avant)", "Marge (arri??re)"};
    }

    @Override
    public Object[] getModes(){
        return new Object[]{};
    }

    private Pouce getLongueur(){
        return ((MurBrute)(parent.getListeComposantes().get(0))).getLongueur().diff(this.margeArriere).diff(this.margeAvant);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
