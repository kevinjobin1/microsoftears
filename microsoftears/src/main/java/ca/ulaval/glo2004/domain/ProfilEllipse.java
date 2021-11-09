package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;


public class ProfilEllipse extends Composante{
    private Pouce longueur;
    private Pouce hauteur;
    private PointPouce centre;
    private Ellipse ellipse;

    public ProfilEllipse(RoulotteController parent, Pouce longueur, Pouce hauteur, PointPouce centre) {
        super(parent);
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.centre = centre;
        this.ellipse = new Ellipse(this.longueur,this.hauteur,this.centre, this.parent.getNombrePoint());
        this.setPolygone(ellipse.getPolygone());
        this.setType(TypeComposante.PROFIL_ELLIPSE);
    }

    //todo ou enlever
    public ProfilEllipse(RoulotteController parent) {
        super(parent);
        //this.longueur = ;
        //this.hauteur = ;
        //this.centre = ;
        this.ellipse = new Ellipse(longueur,hauteur,centre, this.parent.getNombrePoint());
        this.setPolygone(ellipse.getPolygone());
        this.setType(TypeComposante.PROFIL_ELLIPSE);
    }

    public Pouce getLongueur() {
        return longueur;
    }

    public void setLongueur(Pouce longueur) {
        this.longueur = longueur;
    }

    public Pouce getHauteur() {
        return hauteur;
    }

    public void setHauteur(Pouce hauteur) {
        this.hauteur = hauteur;
    }

    public PointPouce getCentre() {
        return centre;
    }

    public void setCentre(PointPouce centre) {
        this.centre = centre;
    }
}
