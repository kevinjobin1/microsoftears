package ca.ulaval.glo2004.domain;

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
        //public Pouce largeur;
        //public Pouce hauteur;
        public int width = 1035;
        public int height = 735;
        public double scale = 1;
        public double zoom;
        public int origineX = 0;
        public int origineY = 0;
        public int offsetX = 0;
        public int offsetY = 0;
        public int visibleWidth;
        public int visibleHeight;
        public int pixelsToInchesRatio = 10;


    public RoulotteController() {
        this.listeComposantes = new ArrayList<Composante>();
        this.listeOuverturesLaterales = new ArrayList<OuvertureLaterale>();
        this.listeAidesDesign = new ArrayList<AideDesign>();
        // Dimensions
        //this.largeur = new Pouce(96,0,1);
        //this.hauteur = new Pouce(48, 0,0);
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

    public void setScale(int wheelRotation, Point mousePoint){

        double scaleFactor = 0.1;
        wheelRotation = wheelRotation < 0 ? 1 : -1;
        zoom = Math.exp(wheelRotation * scaleFactor);

        offsetX = (int) -(mousePoint.getX());
        offsetY = (int) -(mousePoint.getY());

        int mouseX = (int) (mousePoint.getX());
        int mouseY = (int) (mousePoint.getY());

        origineX -= mouseX/(scale * zoom) - mouseX/scale;
        origineY -= mouseY/(scale * zoom) - mouseY/scale;

        scale = scale * zoom;
        visibleHeight = (int) (height/scale);
        visibleWidth = (int) (width/scale);

        System.out.println(" Scale: " + scale);
        System.out.println(" Rapport (%): " + ((1/scale) * 100) + "%");
        System.out.println(" WheelRotation: " + wheelRotation);
        System.out.println(" Zoom: " + zoom);
        System.out.println(" MousePoint(X,Y): (" + mousePoint.getX() + "," + mousePoint.getY() + ")");
        System.out.println(" Origine(X,Y): (" + origineX + "," + origineY + ")");
        System.out.println(" Offset(X,Y): (" + offsetX + "," + offsetY + ")");
        System.out.println(" VisibleDimension(width, height): (" + visibleWidth + "," + visibleHeight + ")");
        System.out.println();



    }

    public void setCenter(Point mousePoint){
        //TODO: à faire, zoom en fonction de la position de la souris,
        // il faut tenir compte du déplacement du centre réel de l'objet Graphics
        // pour l'instant le zoom/position fonctionne seulement si on reste à l'intérieur de la forme...


    }

    public Point getPosition(Point mousePoint) {
        //TODO: à refaire, pas du tout le bon calcul, on cherche à savoir si le point est dans le plan ou non

        return new Point (0,0);
    }

    public int getPixelsToInchesRatio() {
        return this.pixelsToInchesRatio;
    }

    public int inchesToPixel(int inches){
        int pixels = inches * pixelsToInchesRatio;
        return pixels;
    }

    public int pixelsToInches(int pixels){
        int inches = pixels / this.getPixelsToInchesRatio();
        return inches;
    }

    public void zoomOut() {

        double scaleFactor = 0.1;
        zoom = Math.exp(scaleFactor);

        scale = scale * zoom;
        visibleHeight = (int) (height/scale);
        visibleWidth = (int) (width/scale);
    }

    public void zoomIn() {

        double scaleFactor = 0.1;
        zoom = Math.exp(-scaleFactor);

        scale = scale * zoom;
        visibleHeight = (int) (height/scale);
        visibleWidth = (int) (width/scale);
    }
}
