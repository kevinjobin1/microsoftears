package ca.ulaval.glo2004.domain;


import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Rectangle;

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
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.PLANCHER);
    }

    //à compléter
    public Plancher(RoulotteController parent) {
        super(parent);
        this.epaisseur = new Pouce(1,1,4);
        //this.margeAvant = ;
        //this.margeArriere = ;
        this.rectangle = new Rectangle(this.getLongueur(), this.epaisseur, this.getCentre());
        this.setPolygone(rectangle.getPolygone());
        this.setType(TypeComposante.PLANCHER);
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

    //à coder
    private PointPouce getCentre(){
        return null;
    }

    //à coder
    private Pouce getLongueur(){
        return null;
    }
}
