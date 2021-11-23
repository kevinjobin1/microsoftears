package ca.ulaval.glo2004.domain.roulotte;

import ca.ulaval.glo2004.domain.composante.*;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.gui.FenetrePrincipale;

import java.awt.*;
import java.util.ArrayList;

public class RoulotteController implements Observable{

    private ArrayList<RoulotteControllerObserver> listeObservers;
    private ArrayList<Composante> listeComposantes;
        private ArrayList<OuvertureLaterale> listeOuverturesLaterales;
        private ArrayList<AideDesign> listeAidesDesign;
        private MurProfile Murprofile;
        private Hayon hayon;
        private Ressorts ressorts;
        private MurBrute murBrute;
        private MurSeparateur murSeparateur;
        private Plancher plancher;
        private PoutreArriere poutreArriere;
        private Toit toit;
        private FenetrePrincipale parent;
        private ContreplaqueExterieur contreplaqueExterieur;
        private ContreplaqueInterieur contreplaqueInterieur;


        // controle de l'affichage
        public double scale = 1;
        public double zoom = 1;
        public double facteurZoom = 0.1;
        public int deltaX = 0;
        public int deltaY = 0;
        public double largeurPlan;
        public double hauteurPlan;
        public double centrePlanX;
        public double centrePlanY;
        public int positionSourisX;
        public int positionSourisY;
        public PointPouce mousePoint = new PointPouce();
        public static int pixelsToInchesRatio = 7;
        private static final int NOMBRE_POINTS = 300;


    public RoulotteController(FenetrePrincipale parent) {
        this.parent = parent;
        this.listeComposantes = new ArrayList<>();
        this.listeOuverturesLaterales = new ArrayList<>();
        this.listeAidesDesign = new ArrayList<>();
        this.listeObservers = new ArrayList<>();
    }

    public ArrayList<Composante> getListeComposantes() {
        return listeComposantes;
    }

    public MurProfile getMurprofile() {
        return Murprofile;
    }

    public void setMurprofile(MurProfile murprofile) {
        Murprofile = murprofile;
    }

    public Hayon getHayon() {
        return hayon;
    }

    public void setHayon(Hayon hayon) {
        this.hayon = hayon;
    }

    public Ressorts getRessorts() {
        return ressorts;
    }

    public void setRessorts(Ressorts ressorts) {
        this.ressorts = ressorts;
    }

    public MurBrute getMurBrute() {
        return murBrute;
    }

    public void setMurBrute(MurBrute murBrute) {
        this.murBrute = murBrute;
    }

    public MurSeparateur getMurSeparateur() {
        return murSeparateur;
    }

    public void setMurSeparateur(MurSeparateur murSeparateur) {
        this.murSeparateur = murSeparateur;
    }

    public Plancher getPlancher() {
        return plancher;
    }

    public void setPlancher(Plancher plancher) {
        this.plancher = plancher;
    }

    public PoutreArriere getPoutreArriere() {
        return poutreArriere;
    }

    public void setPoutreArriere(PoutreArriere poutreArriere) {
        this.poutreArriere = poutreArriere;
    }

    public Toit getToit() {
        return toit;
    }

    public void setToit(Toit toit) {
        this.toit = toit;
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

    public ContreplaqueExterieur getContreplaqueExterieur() {
        return contreplaqueExterieur;
    }

    public void setContreplaqueExterieur(ContreplaqueExterieur contreplaqueExterieur) {
        this.contreplaqueExterieur = contreplaqueExterieur;
    }

    public ContreplaqueInterieur getContreplaqueInterieur() {
        return contreplaqueInterieur;
    }

    public void setContreplaqueInterieur(ContreplaqueInterieur contreplaqueInterieur) {
        this.contreplaqueInterieur = contreplaqueInterieur;
    }

    public void ajouterComposante(TypeComposante composanteChoisie, Point mousePoint) {}

    public void setScale(int wheelRotation){
        // on limite à une rotation seulement
        wheelRotation = wheelRotation < 0 ? -1 : 1;

        // on décide du facteur de zoom
        zoom = Math.exp(wheelRotation * facteurZoom);

        // on calcule le déplacement/translation nécessaire par rapport à la position de la souris
        deltaX -= ((positionSourisX/(scale * zoom)) - (positionSourisX/scale));
        deltaY -= ((positionSourisY/(scale * zoom)) - (positionSourisY/scale));

        // on ajuste l'échelle en conséquence
        scale = scale * zoom;

    }

    public void setDimension(Dimension dimensionAfficheur){
        this.hauteurPlan = dimensionAfficheur.getHeight() * scale;
        this.largeurPlan = dimensionAfficheur.getWidth() * scale;
        this.centrePlanY = (hauteurPlan/2);
        this.centrePlanX = (largeurPlan/2);
        notifyObserversForUpdatedRoulotte();
    }

    public PointPouce getPositionPlan(Point mousePoint) {
        Pouce x = xVersReel(mousePoint.getX()),
                y = yVersReel(mousePoint.getY());
        return new PointPouce(x, y);
    }

    /** Converti les coordonnées réelles --> coordonnées dans l'écran */
    public double xVersEcran(Pouce x)
    {
        return (x.toPixel(this.getPixelsToInchesRatio()) - deltaX) * this.getScale();
    }

    public double yVersEcran(Pouce y)
    {
        return (y.toPixel(this.getPixelsToInchesRatio()) - deltaY) * this.getScale();
    }

    /** Converti les coordonnées de l'écran --> coordonnées réelles */

    public Pouce xVersReel(double x)
    {
        // On veut la coordonnée sans le zoom
        x /= this.getScale();
        // On recentre le point par rapport au décalage
        x += deltaX;
        // On traduit en pouce
        x /= this.getPixelsToInchesRatio();

        return new Pouce(x);
    }

    public Pouce yVersReel(double y)
    {
        // On veut la coordonnée sans le zoom
        y /= this.getScale();
        // On recentre le point par rapport au décalage
        y += deltaY;
        // On traduit en pouce
        y /= this.getPixelsToInchesRatio();

        return new Pouce(y);
    }


    public int getPixelsToInchesRatio() {
        return this.pixelsToInchesRatio;
    }

    public int getNombrePoint() {
        return this.NOMBRE_POINTS;
    }

    public double getScale() {
        return scale;
    }

    public void setPositionSouris(int x, int y) {
        positionSourisX = x;
        positionSourisY = y;
        notifyObserversForUpdatedRoulotte();
    }

    public void clicSurPlan(Point mousePressedPoint) {
        PointPouce positionClic = getPositionPlan(mousePressedPoint);
        this.mousePoint = positionClic;

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

    private Pouce getLargeurPlan() {
        double largeurReel = (this.largeurPlan/this.scale)/this.getPixelsToInchesRatio();
        return new Pouce(largeurReel);
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
