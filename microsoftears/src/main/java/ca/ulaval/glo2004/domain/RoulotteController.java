package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;
import java.util.ArrayList;

public class RoulotteController {

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


        // controle de l'affichage
        public double scale = 1;
        public double zoom = 1;
        public double facteurZoom = 0.1;
        public int origineX = 0;
        public int origineY = 0;
        public double largeurPlan;
        public double hauteurPlan;
        public double centrePlanX;
        public double centrePlanY;
        public int positionSourisX;
        public int positionSourisY;
        public static int pixelsToInchesRatio = 7;
        private static final int NOMBRE_POINTS = 200;


    public RoulotteController() {
        this.listeComposantes = new ArrayList<>();
        this.listeOuverturesLaterales = new ArrayList<>();
        this.listeAidesDesign = new ArrayList<>();
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

    public void ajouterComposante(TypeComposante composanteChoisie, Point mousePoint) {}

    public void setScale(int wheelRotation){
        wheelRotation = wheelRotation < 0 ? -1 : 1;
        zoom = Math.exp(wheelRotation * facteurZoom);

        origineX -= ((positionSourisX/(scale * zoom)) - (positionSourisX/scale));
        origineY -= ((positionSourisY/(scale * zoom)) - (positionSourisY/scale));

        scale = scale * zoom;

        System.out.println(" Scale: " + scale);
        System.out.println(" Rapport (%): " + ((1/scale) * 100) + "%");
        System.out.println(" WheelRotation: " + wheelRotation);
        System.out.println(" Zoom: " + zoom);
        System.out.println(" MousePoint(X,Y): (" + positionSourisX + "," + positionSourisY + ")");
        System.out.println(" Origine(X,Y): (" + origineX + "," + origineY + ")");
        System.out.println();

    }

    public void setDimension(Dimension dimensionAfficheur){
        //TODO: à faire, zoom en fonction de la position de la souris,
        // il faut tenir compte du déplacement du centre réel de l'objet Graphics
        // pour l'instant le zoom/position fonctionne seulement si on reste à l'intérieur de la forme...
        this.hauteurPlan = dimensionAfficheur.getHeight() * scale;
        this.largeurPlan = dimensionAfficheur.getWidth() * scale;
        this.centrePlanY = (hauteurPlan/2);
        this.centrePlanX = (largeurPlan/2);

    }

    public Point getPosition(Point mousePoint) {
        //TODO: à refaire, pas du tout le bon calcul, on cherche à savoir si le point est dans le plan ou non

        return new Point (0,0);
    }

    /** Converti les coordonnées réelles --> coordonnées dans l'écran */
    public double xVersEcran(Pouce x)
    {
        return (x.toPixel(this.getPixelsToInchesRatio()) - origineX) * this.getScale();
    }

    public double yVersEcran(Pouce y)
    {
        return (y.toPixel(this.getPixelsToInchesRatio()) - origineY) * this.getScale();
    }

    /** Converti les coordonnées de l'écran --> coordonnées réelles */
    public PointPouce ecranVersReel(Point mousePoint)
    {
        // TODO: à faire!
       return new PointPouce();
    }

    public int getPixelsToInchesRatio() {
        return this.pixelsToInchesRatio;
    }

    public void zoomOut() {

        double scaleFactor = 0.1;
        zoom = Math.exp(scaleFactor);
        scale = scale * zoom;
    }

    public void zoomIn() {

        double scaleFactor = 0.1;
        zoom = Math.exp(-scaleFactor);

        scale = scale * zoom;
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
    }
}
