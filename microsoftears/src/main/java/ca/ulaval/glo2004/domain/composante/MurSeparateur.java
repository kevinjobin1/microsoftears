package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.awt.*;
import java.awt.geom.Area;

public class MurSeparateur extends Composante{

    private Pouce epaisseur;
    private Pouce hauteur;
    private Pouce distancePoutreArriere;
    private Rectangle rectangle;

    public MurSeparateur(RoulotteController parent, Pouce epaisseur, Pouce hauteur, Pouce distancePoutreArriere) {
        super(parent);
        this.epaisseur = epaisseur;
        this.hauteur = hauteur;
        this.distancePoutreArriere = distancePoutreArriere;
        this.rectangle = new Rectangle(this.epaisseur, this.hauteur, this.getCentre());
        this.setCouleurInitiale(new Color(200,100,150));
        this.setCouleur(getCouleurInitiale());
        this.setTransparenceInitiale(0.1f);
        this.setTransparence(0.1f);
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.MUR_SEPARATEUR);

    }

    public MurSeparateur(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(3,0,1);
        this.hauteur = new Pouce(40,0,1);
        this.distancePoutreArriere = new Pouce(5,0,1);
        this.rectangle = new Rectangle(this.epaisseur, this.hauteur, this.getCentre());
        this.setCouleurInitiale(new Color(200,100,150));
        this.setCouleur(getCouleurInitiale());
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.MUR_SEPARATEUR);
        this.setVisible(true);
    }

    @Override
    public void afficher(Graphics2D g2d){
        if (estVisible()){
            Area area = getArea();
            Composite compositeInitial = g2d.getComposite();
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

    public Pouce getHauteur() {
        return hauteur;
    }

    public void setHauteur(Pouce hauteur) {
        this.hauteur = hauteur;
    }

    public Pouce getDistancePoutreArriere() {
        return distancePoutreArriere;
    }

    public void setDistancePoutreArriere(Pouce distancePoutreArriere) {
        this.distancePoutreArriere = distancePoutreArriere;
    }

    public PointPouce getCentre(){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        PoutreArriere poutre = (PoutreArriere) parent.getListeComposantes().get(7);
        Pouce x = poutre.getCentre().getX().add(poutre.getLongueur().diviser(2)).add(distancePoutreArriere).add(epaisseur.diviser(2));
        Pouce y = mur.getCentre().getY().diff(mur.getLargeur().diviser(2)).add(hauteur.diviser(2));
        return new PointPouce(x,y);
    }

    @Override
    public int[] getValeurs() {
        return new int[]{hauteur.getPouces(), hauteur.getNumerateur(), hauteur.getDenominateur(),
                epaisseur.getPouces(), epaisseur.getNumerateur(), epaisseur.getDenominateur(),
                distancePoutreArriere.getPouces(), distancePoutreArriere.getNumerateur(),
                distancePoutreArriere.getDenominateur()};
    }

    @Override
    public void translate(PointPouce delta) {

    }

    @Override
    public void snapToGrid(PointPouce pointGrille){

    }


    @Override
    public String[] getNomsAttributs() {
        return new String[]{"Hauteur", "Épaisseur", "Distance (poutre-arrière)"};
    }

    @Override
    public boolean getMode() {
        return false;
    }
}
