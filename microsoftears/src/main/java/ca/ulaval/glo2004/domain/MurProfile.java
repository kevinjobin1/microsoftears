package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Polygone;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.util.LinkedList;
import java.util.List;

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

    //todo
    public MurProfile(RoulotteController parent, boolean mode) {
        super(parent);
        this.mode = mode;
        this.profilEllipses[0] = new ProfilEllipse(parent, new Pouce(10,0,1), new Pouce(10,0,1),
                new PointPouce(parent.getMurBrute().getPolygone().getListePoints().get(0).getX().diff(new Pouce(5,0,1)),
                        parent.getMurBrute().getPolygone().getListePoints().get(0).getY().add(new Pouce(5,0,1)))); //ellipse en haut à droite

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

    //todo
    private LinkedList<PointPouce> listePointsModeBezier() {
        return null;
    }


    private LinkedList<PointPouce> listePointsModeEllipse(){
        List<PointPouce> listePointMur = parent.getMurBrute().getPolygone().getListePoints();

        //cadran en haut à droite de l'éllipse0
        List<PointPouce> listeEllipse0 = new LinkedList<> (profilEllipses[0].getPolygone().getListePoints().
                subList(0, Math.round(Ellipse.NOMBRE_POINTS/4-1)));

        //cadran en haut à gauche de l'éllipse1
        List<PointPouce> listeEllipse1 = new LinkedList<> (profilEllipses[1].getPolygone().getListePoints().
                subList(Math.round(Ellipse.NOMBRE_POINTS/4), Math.round(Ellipse.NOMBRE_POINTS/2-1)));

        //cadran en bas à gauche de l'éllipse2
        List<PointPouce> listeEllipse2 = new LinkedList<> (profilEllipses[2].getPolygone().getListePoints().
                subList(Math.round(Ellipse.NOMBRE_POINTS/2), Math.round(Ellipse.NOMBRE_POINTS*3/4-1)));

        //cadran en bas à droite de l'éllipse3
        List<PointPouce> listeEllipse3 = new LinkedList<> (profilEllipses[3].getPolygone().getListePoints().
                subList(Math.round(Ellipse.NOMBRE_POINTS*3/4), Math.round(Ellipse.NOMBRE_POINTS-1)));

        List<PointPouce>[] listesEllipses = new List[4];
        listesEllipses[0] = listeEllipse0;
        listesEllipses[1] = listeEllipse1;
        listesEllipses[2] = listeEllipse2;
        listesEllipses[3] = listeEllipse3;

        LinkedList<PointPouce> listeRetour = new LinkedList<>();

        int indiceDepart;
        int indiceFin;
        PointPouce point;
        for (int j = 0; j < listesEllipses.length; j++){
            indiceDepart = 0;
            indiceFin = 0;
            for (int i = 0; i < listesEllipses[j].size(); i++) {
                point = listesEllipses[j].get(i);
                if (point.getX().equals(listePointMur.get(j).getX())) {
                    if(((j == 0 || j==1) && point.getY().gte(listePointMur.get(j).getY())) ||
                            ((j == 2 || j == 3) && point.getY().ste(listePointMur.get(j).getY()))){
                        indiceDepart = i;
                    }
                }
                if (i >= indiceDepart && indiceDepart !=0 && point.getY().equals(listePointMur.get(j).getY())) {
                    indiceFin = i + 1;
                }
            }
            listesEllipses[j] = listesEllipses[j].subList(indiceDepart,indiceFin);

            if (listesEllipses[j].isEmpty()){
                listesEllipses[j].add(listePointMur.get(j));
            }
        }

        listeRetour.addAll(listesEllipses[0]);
        listeRetour.addAll(listesEllipses[1]);
        listeRetour.addAll(listesEllipses[2]);
        listeRetour.addAll(listesEllipses[3]);

        return listeRetour;
    }
}
