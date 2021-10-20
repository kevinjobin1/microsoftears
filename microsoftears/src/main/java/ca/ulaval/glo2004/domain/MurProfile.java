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
        this.setType(TypeComposante.MUR_PROFILE);
        this.setPolygone(getPolygone());
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

    @Override
    public Polygone getPolygone(){
        Polygone retour;
        if(mode){
            retour = new Polygone(listePointsModeEllipse());
        } else {
            retour = new Polygone(listePointsModeBezier());
        }
        return retour;
    }

    //à coder
    private LinkedList<PointPouce> listePointsModeBezier() {
        return null;
    }

    //à tester
    private LinkedList<PointPouce> listePointsModeEllipse(){
        LinkedList<PointPouce> listePointMur = parent.getMurBrute().getPolygone().getListePoints();

        //cadran en haut à droite de l'éllipse0
        LinkedList<PointPouce> listeEllipse0 = (LinkedList<PointPouce>) profilEllipses[0].getPolygone().getListePoints().
                subList(0, Math.round(Ellipse.NOMBRE_POINTS/4-1));

        //cadran en haut à gauche de l'éllipse1
        LinkedList<PointPouce> listeEllipse1 = (LinkedList<PointPouce>) profilEllipses[1].getPolygone().getListePoints().
                subList(Math.round(Ellipse.NOMBRE_POINTS/4), Math.round(Ellipse.NOMBRE_POINTS/2-1));

        //cadran en bas à gauche de l'éllipse2
        LinkedList<PointPouce> listeEllipse2 = (LinkedList<PointPouce>) profilEllipses[2].getPolygone().getListePoints().
                subList(Math.round(Ellipse.NOMBRE_POINTS/2), Math.round(Ellipse.NOMBRE_POINTS*3/4-1));

        //cadran en bas à droite de l'éllipse3
        LinkedList<PointPouce> listeEllipse3 = (LinkedList<PointPouce>) profilEllipses[3].getPolygone().getListePoints().
                subList(Math.round(Ellipse.NOMBRE_POINTS*3/4), Math.round(Ellipse.NOMBRE_POINTS-1));

        LinkedList<PointPouce> liste0 = new LinkedList<PointPouce>(); //listeEllipse0 coupés par le murBrute
        LinkedList<PointPouce> liste1 = new LinkedList<PointPouce>(); //listeEllipse1 coupés par le murBrute
        LinkedList<PointPouce> liste2 = new LinkedList<PointPouce>(); //listeEllipse2 coupés par le murBrute
        LinkedList<PointPouce> liste3 = new LinkedList<PointPouce>(); //listeEllipse3 coupés par le murBrute

        for(int i = 0; i < listeEllipse0.size(); i++) {
            PointPouce point = listeEllipse0.get(i);
            if (point.getX().plusPetitEgal(listePointMur.get(0).getX())
            && point.getY().plusGrandEgal(listePointMur.get(0).getY())) {
                liste0.add(point);
            }
        }

        for(int i = 0; i < listeEllipse1.size(); i++) {
            PointPouce point = listeEllipse1.get(i);
            if (point.getX().plusGrandEgal(listePointMur.get(1).getX())
                    && point.getY().plusGrandEgal(listePointMur.get(1).getY())) {
                liste1.add(point);
            }
        }

        for(int i = 0; i < listeEllipse2.size(); i++) {
            PointPouce point = listeEllipse2.get(i);
            if (point.getX().plusGrandEgal(listePointMur.get(2).getX())
                    && point.getY().plusPetitEgal(listePointMur.get(2).getY())) {
                liste2.add(point);
            }
        }

        for(int i = 0; i < listeEllipse3.size(); i++) {
            PointPouce point = listeEllipse3.get(i);
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

        return liste0;
    }
}
