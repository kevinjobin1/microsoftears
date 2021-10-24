package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

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
        this.profilEllipses[0] = new ProfilEllipse(parent, new Pouce(5,0,1), new Pouce(5,0,1),
                parent.getMurBrute().getPolygone().getListePoints().get(0)); //ellipse en haut à droite

        this.profilEllipses[1] = new ProfilEllipse(parent, new Pouce(5,0,1), new Pouce(5,0,1),
                parent.getMurBrute().getPolygone().getListePoints().get(1)); //ellipse en haut à gauche

        this.profilEllipses[2] = new ProfilEllipse(parent, new Pouce(5,0,1), new Pouce(5,0,1),
                parent.getMurBrute().getPolygone().getListePoints().get(2)); //ellipse en bas à gauche

        this.profilEllipses[3] = new ProfilEllipse(parent, new Pouce(5,0,1), new Pouce(5,0,1),
                parent.getMurBrute().getPolygone().getListePoints().get(3)); //ellipse en bas à droite

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

    //à tester (je vais attendre de pouvoir l'afficher)
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

        LinkedList<PointPouce>[] listesEllipses = new LinkedList[4];
        listesEllipses[0] = listeEllipse0;
        listesEllipses[1] = listeEllipse1;
        listesEllipses[2] = listeEllipse2;
        listesEllipses[3] = listeEllipse3;

        LinkedList<PointPouce> listeRetour;

        int indiceDepart;
        int indiceFin;
        for (int j = 0; j < listesEllipses.length; j++){
            indiceDepart = 0;
            indiceFin = 0;
            for (int i = 0; i < listeEllipse0.size(); i++) {
                PointPouce point = listeEllipse0.get(i);
                if (point.getX().equals(listePointMur.get(j).getX())) {
                    indiceDepart = i;
                }
                if (i >= indiceDepart && point.getY().equals(listePointMur.get(j).getY())) {
                    indiceFin = i + 1;
                }
            }
            listesEllipses[j] = (LinkedList<PointPouce>) listesEllipses[j].subList(indiceDepart,indiceFin);

            if (listesEllipses[j].isEmpty()){
                listesEllipses[j].add(listePointMur.get(j));
            }
        }

        listeRetour = listesEllipses[0];
        listeRetour.addAll(listesEllipses[1]);
        listeRetour.addAll(listesEllipses[2]);
        listeRetour.addAll(listesEllipses[3]);

        return listeRetour;
    }
}
