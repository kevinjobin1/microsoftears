package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.composante.*;
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.gui.FenetrePrincipale;

import java.awt.*;
import java.util.ArrayList;

public class RoulotteController {
    private final FenetrePrincipale parent;
    private ArrayList<Composante> listeComposantes;
    private ArrayList<OuvertureLaterale> listeOuverturesLaterales;
    private ArrayList<AideDesign> listeAidesDesign;


    // controle de l'affichage
    private static final int PIXEL_RATIO = 7;
    private static final int NOMBRE_POINTS = 300;
    private int[] delta;
    private Point positionSouris;
    private double scale;

    public RoulotteController(FenetrePrincipale parent) {
        this.parent = parent;
        this.listeComposantes = new ArrayList<>();
        this.listeOuverturesLaterales = new ArrayList<>();
        this.listeAidesDesign = new ArrayList<>();
        this.delta = new int[]{0,0};
        this.scale = 1;
        this.positionSouris = new Point();
        calculerDisposition();
    }

    protected void invaliderDisposition(){
        // TODO
    }

    protected void calculerDisposition(){
        // Ordre (index): murBrute (0), murProfile(1), ellipses(2,3,4,5),
        // plancher(6), poutre(7), hayon(8), murSeparateur(9)
        MurBrute murBrute = new MurBrute(this);
        listeComposantes.add(murBrute);
        MurProfile murProfile = new MurProfile(this,true);
        listeComposantes.add(murProfile);
        for (ProfilEllipse ellipse : murProfile.getProfilEllipses()){
            listeComposantes.add(ellipse);
        }
        Plancher plancher = new Plancher(this);
        listeComposantes.add(plancher);
        PoutreArriere poutre = new PoutreArriere(this);
        listeComposantes.add(poutre);
        Hayon hayon = new Hayon(this);
        listeComposantes.add(hayon);
        MurSeparateur murSeparateur = new MurSeparateur(this);
        listeComposantes.add(murSeparateur);

    }

    public void updateComposante(int[] valeurs, TypeComposante type){
       switch(type){
           case MUR_PROFILE:
               MurBrute mur = new MurBrute(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11])));
               listeComposantes.set(0, mur);
               break;
           case PROFIL_ELLIPSE_1:
               ProfilEllipse ellipse = new ProfilEllipse(this,
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilEllipses()[0] = ellipse;
               listeComposantes.set(2, ellipse);
               break;
           case PROFIL_ELLIPSE_2:
               ellipse = new ProfilEllipse(this,
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilEllipses()[1] = ellipse;
               listeComposantes.set(3, ellipse);
               break;
           case PROFIL_ELLIPSE_3:
               ellipse = new ProfilEllipse(this,
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilEllipses()[2] = ellipse;
               listeComposantes.set(4, ellipse);
               break;
           case PROFIL_ELLIPSE_4:
               ellipse = new ProfilEllipse(this,
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilEllipses()[3] = ellipse;
               listeComposantes.set(5, ellipse);
               break;

       }
    }

    public void ajouterComposante(TypeComposante composante) {

    }

    public void setScale(int wheelRotation){
        // on limite à une rotation seulement
        wheelRotation = wheelRotation < 0 ? -1 : 1;

        // on décide du facteur de zoom
        double zoom = Math.exp(wheelRotation * 0.1);

        // on calcule le déplacement/translation nécessaire par rapport à la position de la souris
        delta[0] -= ((positionSouris.getX()/(scale * zoom)) - (positionSouris.getX()/scale));
        delta[1] -= ((positionSouris.getY()/(scale * zoom)) - (positionSouris.getY()/scale));

        // on ajuste l'échelle en conséquence
        scale = scale * zoom;

    }

    public PointPouce getPositionPlan(Point mousePoint) {
        Pouce x = xVersReel(mousePoint.getX()),
                y = yVersReel(mousePoint.getY());
        return new PointPouce(x, y);
    }

    public double[] getPositionEcran(PointPouce point){
       return new double[]{xVersEcran(point.getX()), yVersEcran(point.getY())};
    }

    /** Converti les coordonnées réelles --> coordonnées dans l'écran */
    private double xVersEcran(Pouce x)
    {
        return (x.toPixel(this.getPixelsToInchesRatio()) - delta[0]) * this.getScale();
    }

    private double yVersEcran(Pouce y)
    {
        return (y.toPixel(this.getPixelsToInchesRatio()) - delta[1]) * this.getScale();
    }

    /** Converti les coordonnées de l'écran --> coordonnées réelles */

    private Pouce xVersReel(double x)
    {
        // On veut la coordonnée sans le zoom
        x /= this.getScale();
        // On recentre le point par rapport au décalage
        x += delta[0];
        // On traduit en pouce
        x /= this.getPixelsToInchesRatio();

        return new Pouce(x);
    }

    private Pouce yVersReel(double y)
    {
        // On veut la coordonnée sans le zoom
        y /= this.getScale();
        // On recentre le point par rapport au décalage
        y += delta[1];
        // On traduit en pouce
        y /= this.getPixelsToInchesRatio();

        return new Pouce(y);
    }

    /** Setter pour la position de la souris */

    public void setPositionSouris(int x, int y) {
        this.positionSouris = new Point(x,y);
        
    }

    /** Fonction qui détermine quelle forme a été sélectionnée */
    public void clicSurPlan(Point mousePressedPoint) {

        PointPouce positionClic = getPositionPlan(mousePressedPoint);

        int indexComposante = -1;
        if (!listeComposantes.isEmpty()) {
            for (int i=0; i < listeComposantes.size(); i++) {
                Composante composante = listeComposantes.get(i);
                composante.resetCouleur();
                composante.resetTransparence();

                if (composante.getPolygone().contient(positionClic) && composante.estVisible()) {
                    System.out.println("Clique sur : " + composante);
                    indexComposante = i;
                }
            }
            if (indexComposante != -1){
                Composante composante = listeComposantes.get(indexComposante);
                parent.setComposanteChoisie(composante.getType());
                composante.setCouleur(new Color(255,60,60));
                composante.setTransparence(0.6f);
            }
        }
        
    }

    public void remplirComposante(Point mousePressedPoint){
        PointPouce positionClic = getPositionPlan(mousePressedPoint);

        int indexComposante = -1;
        if (!listeComposantes.isEmpty()) {
            for (int i=0; i < listeComposantes.size(); i++) {
                Composante composante = listeComposantes.get(i);

                if (composante.getPolygone().contient(positionClic) && composante.estVisible()) {
                    indexComposante = i;
                }
            }
            if (indexComposante != -1){
                Composante composante = listeComposantes.get(indexComposante);
                parent.setComposanteChoisie(composante.getType());
                composante.setCouleurInitiale(parent.getCouleurChoisie());
                composante.resetCouleur();
            }
        }
    }

    public void setComposanteVisible(boolean estVisible, String nomComposante) {
        if (!listeComposantes.isEmpty()) {
            for (Composante composante : listeComposantes) {
            if (nomComposante == composante.toString()){
                composante.setVisible(estVisible);
                }
            }
        }
    }

    public int getPixelsToInchesRatio() {
        return this.PIXEL_RATIO;
    }

    public int getNombrePoint() {
        return this.NOMBRE_POINTS;
    }

    public double getScale() {
        return scale;
    }

    public ArrayList<IComposante> getListeIComposantes(){
        ArrayList<IComposante> listeIComposantes = new ArrayList<>();
        for (Composante composante : listeComposantes){
            listeIComposantes.add(composante);
        }
        return listeIComposantes;
    }

    public ArrayList<Composante> getListeComposantes() {
        return listeComposantes;
    }

    public ArrayList<OuvertureLaterale> getListeOuverturesLaterales() {
        return listeOuverturesLaterales;
    }

    public void setListeOuverturesLaterales(ArrayList<OuvertureLaterale> listeOuverturesLaterales) {
        this.listeOuverturesLaterales = listeOuverturesLaterales;
    }

    public ArrayList<AideDesign> getListeAidesDesign() {
        return listeAidesDesign;
    }

    private void setListeAidesDesign(ArrayList<AideDesign> listeAidesDesign) {
        this.listeAidesDesign = listeAidesDesign;
    }
}
