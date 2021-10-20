package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;

import java.util.LinkedList;

public class MurProfile extends Composante{

    /**
     * profilEllipses[0] est l'éllipse en haut à droite
     * profilEllipses[1] est l'éllipse en haut à gauche
     * profilEllipses[2] est l'éllipse en bas à gauche
     * profilEllipses[3] est l'éllipse en bas à droite
     */
    private ProfilEllipse[] profilEllipses= new ProfilEllipse[4];
    private ProfilBezier profilBezier;
    /**
     * true: Éllipses
     * false: Bézier
     */
    private boolean mode;

    //à compléter
    public MurProfile(RoulotteController parent, boolean mode) {
        super(parent);
        this.mode = mode;
        this.profilEllipses[0] = new ProfilEllipse(parent); //ellipse en haut à droite
        this.profilEllipses[1] = new ProfilEllipse(parent);
        this.profilEllipses[2] = new ProfilEllipse(parent);
        this.profilEllipses[3] = new ProfilEllipse(parent);
        this.profilBezier = new ProfilBezier(parent);
    }

    public ProfilEllipse[] getProfilEllipses() {
        return profilEllipses;
    }

    public ProfilBezier getProfilBezier() {
        return profilBezier;
    }

    public boolean getMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    //à tester
    @Override
    public Polygone getPolygone(){
        LinkedList<PointPouce> listePointMur = parent.getMurBrute().getPolygone().getListePoints();
        LinkedList<PointPouce> liste0 = new LinkedList<PointPouce>(); //liste de points de l'éllipse0 coupés par le murBrute
        LinkedList<PointPouce> liste1 = new LinkedList<PointPouce>(); //liste de points de l'éllipse1 coupés par le murBrute
        LinkedList<PointPouce> liste2 = new LinkedList<PointPouce>(); //liste de points de l'éllipse2 coupés par le murBrute
        LinkedList<PointPouce> liste3 = new LinkedList<PointPouce>(); //liste de points de l'éllipse3 coupés par le murBrute

        for(int i = 0; i < profilEllipses[0].getPolygone().getListePoints().size(); i++) {
            PointPouce point = profilEllipses[0].getPolygone().getListePoints().get(i);
            if (point.getX().plusPetitEgal(listePointMur.get(0).getX())
            && point.getY().plusGrandEgal(listePointMur.get(0).getY())) {
                liste0.add(point);
            }
        }

        for(int i = 0; i < profilEllipses[1].getPolygone().getListePoints().size(); i++) {
            PointPouce point = profilEllipses[1].getPolygone().getListePoints().get(i);
            if (point.getX().plusGrandEgal(listePointMur.get(1).getX())
                    && point.getY().plusGrandEgal(listePointMur.get(1).getY())) {
                liste1.add(point);
            }
        }

        for(int i = 0; i < profilEllipses[2].getPolygone().getListePoints().size(); i++) {
            PointPouce point = profilEllipses[2].getPolygone().getListePoints().get(i);
            if (point.getX().plusGrandEgal(listePointMur.get(2).getX())
                    && point.getY().plusPetitEgal(listePointMur.get(2).getY())) {
                liste2.add(point);
            }
        }

        for(int i = 0; i < profilEllipses[3].getPolygone().getListePoints().size(); i++) {
            PointPouce point = profilEllipses[3].getPolygone().getListePoints().get(i);
            if (point.getX().plusPetitEgal(listePointMur.get(3).getX())
                    && point.getY().plusPetitEgal(listePointMur.get(3).getY())) {
                liste3.add(point);
            }
        }
        if(liste0.isEmpty())
            liste0.add(listePointMur.get(0));

        if(liste1.isEmpty())
            liste1.add(listePointMur.get(1));

        if(liste2.isEmpty())
            liste2.add(listePointMur.get(2));

        if(liste3.isEmpty())
            liste3.add(listePointMur.get(3));

        liste0.addAll(liste1);
        liste0.addAll(liste2);
        liste0.addAll(liste3);

        return new Polygone(liste0);
    }
}
