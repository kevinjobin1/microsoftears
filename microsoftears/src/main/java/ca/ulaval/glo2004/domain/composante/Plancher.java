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
        this.margeArriere = new Pouce(5,0,1);;
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

    public boolean verificationEpaisseur(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getCentre().getY()) && valeur.gt(new Pouce(0,0,1));
    }

    public boolean verificationMargeAvant(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        MurProfile profil = (MurProfile) parent.getListeComposantes().get(1);
        return valeur.st(mur.getLongueur().diviser(2)) &&
                valeur.gt(profil.getProfilEllipses()[3].getLongueur().diviser(2).
                        diff(profil.getProfilEllipses()[3].getCentre().getX().
                                diff(mur.getPolygone().getListePoints().get(3).getX())));
    }

    public boolean verificationMargeArriere(Pouce valeur){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        MurProfile profil = (MurProfile) parent.getListeComposantes().get(1);
        return valeur.st(mur.getLongueur().diviser(2)) &&
                valeur.gt(profil.getProfilEllipses()[2].getLongueur().diviser(2).
                        diff(mur.getPolygone().getListePoints().get(2).getX().
                                diff(profil.getProfilEllipses()[2].getCentre().getX())));
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
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            BufferedImage roue = toBufferedImage(new ImageIcon("trailer_wheel.png").getImage());
            double[] pos = parent.getPositionEcran(new PointPouce(this.getCentre().getX().diff(new Pouce(35,0,1)),
                    this.getCentre().getY().diff(new Pouce(7,0,1))));

            g2d.drawImage(roue, (int) (pos[0] + (100 * parent.getScale())), (int) pos[1],(int) (152 * parent.getScale()), (int) (152 * parent.getScale()), null);
            BufferedImage trailer = toBufferedImage(new ImageIcon("trailer_frame.png").getImage());
            g2d.drawImage(trailer, (int) (pos[0] - (105 * parent.getScale())), (int) pos[1],(int) (1050 * parent.getScale()), (int) (152 * parent.getScale()), null);
            g2d.setComposite(compositeInitial);
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
        MurProfile profil = (MurProfile) parent.getListeComposantes().get(1);
        if (profil.getMode()){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        Pouce x = mur.getCentre().getX().add(mur.getLongueur().diviser(2)).diff(margeAvant).diff(getLongueur().diviser(2));
        Pouce y = mur.getLargeur().diviser(2).add(mur.getCentre().getY()).diff(this.epaisseur.diviser(2));
        return new PointPouce(x,y);
        }
        else {
            return calculerPositionBezier(profil);
        }
    }

    private PointPouce calculerPositionBezier(MurProfile profil){
        LinkedList<PointPouce> pointsProfil = profil.getProfilBezier().getPolygone().getListePoints();
        PointPouce p0 = pointsProfil.getFirst();
        PointPouce p3 = pointsProfil.getLast();
        Pouce longueur = (p3.getX().diff(p0.getX()));
        PointPouce centreProfil = new PointPouce(p0.getX().add(longueur.diviser(2)), p0.getY());

        Pouce x = centreProfil.getX().add(longueur.diviser(2)).diff(margeAvant).diff(getLongueur().diviser(2));
        Pouce y = centreProfil.getY().diff(this.epaisseur.diviser(2));
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
        return new String[]{"Épaisseur", "Marge (avant)", "Marge (arrière)"};
    }

    @Override
    public boolean[] getModes(){
        return new boolean[]{};
    }

    @Override
    public Pouce[] getLimit() {
        return new Pouce[0];
    }

    private Pouce getLongueur(){
        return ((MurBrute)(parent.getListeComposantes().get(0))).getLongueur().diff(this.margeArriere).diff(this.margeAvant);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
