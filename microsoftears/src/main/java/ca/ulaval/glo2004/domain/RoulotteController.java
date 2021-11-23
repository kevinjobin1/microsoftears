package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.composante.*;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.gui.FenetrePrincipale;

import java.awt.*;
import java.util.ArrayList;

public class RoulotteController implements Observable{
    private final FenetrePrincipale parent;
    private ArrayList<RoulotteControllerObserver> listeObservers;
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
        this.listeObservers = new ArrayList<>();
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
        this.getListeComposantes().add(murBrute);
        MurProfile murProfile = new MurProfile(this,true);
        this.getListeComposantes().add(murProfile);
        for (ProfilEllipse ellipse : murProfile.getProfilEllipses()){
            this.getListeComposantes().add(ellipse);
        }
        Plancher plancher = new Plancher(this);
        this.getListeComposantes().add(plancher);
        PoutreArriere poutre = new PoutreArriere(this);
        this.getListeComposantes().add(poutre);
        Hayon hayon = new Hayon(this);
        this.getListeComposantes().add(hayon);
        MurSeparateur murSeparateur = new MurSeparateur(this);
        this.getListeComposantes().add(murSeparateur);
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
        notifyObserversForUpdatedRoulotte();
    }

    /** Fonction qui détermine quelle forme a été sélectionnée */
    public void clicSurPlan(Point mousePressedPoint) {

        PointPouce positionClic = getPositionPlan(mousePressedPoint);

        int indexComposante = -1;
        if (!listeComposantes.isEmpty()) {
            for (int i=0; i < listeComposantes.size(); i++) {
                Composante composante = listeComposantes.get(i);
                if ((composante.getType() != TypeComposante.PROFIL_ELLIPSE_1) ||
                    (composante.getType() != TypeComposante.PROFIL_ELLIPSE_2) ||
                    (composante.getType() != TypeComposante.PROFIL_ELLIPSE_3) ||
                    (composante.getType() != TypeComposante.PROFIL_ELLIPSE_4)){
                    composante.setCouleur(Color.WHITE);
                }
               else{
                   composante.setCouleur(Color.CYAN);
                }

                if (composante.getPolygone().contient(positionClic)) {
                    System.out.println("Clique sur : " + composante);
                    indexComposante = i;
                }
            }
            if (indexComposante != -1){
                Composante composante = listeComposantes.get(indexComposante);
                parent.setComposanteChoisie(composante.getType());
                composante.setCouleur(parent.getCouleurChoisie());
            }
        }
        notifyObserversForUpdatedRoulotte();
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

    @Override
    public void registerObserver(RoulotteControllerObserver newListener) {
        listeObservers.add(newListener);
    }

    @Override
    public void unregisterObserver(RoulotteControllerObserver listener) {
        listeObservers.remove(listener);
    }

    public void notifyObserversForUpdatedRoulotte() {
        for (RoulotteControllerObserver observer : listeObservers) {
            observer.notifyUpdatedRoulotte();
        }
    }
}
