package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.composante.*;
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.gui.FenetrePrincipale;

import java.awt.*;
import java.lang.reflect.Type;
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
        // TODO
        // Ordre (index): murBrute (0), murProfile(1), ellipses(2,3,4,5),
        // plancher(6), poutre(7), hayon(8), murSeparateur(9), toit (10)
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

        Toit toit = new Toit(this);
        listeComposantes.add(toit);

        OuvertureLaterale ouvertureLaterale = new OuvertureLaterale(this);
        listeComposantes.add(ouvertureLaterale);
    }

    public void updateComposante(int[] valeurs, TypeComposante type){
       switch(type){
           case MUR_PROFILE:
               // Changement aux dimensions du mur brute, on doit recréé
               // le panneau brute, le profile, le plancher,
               MurBrute mur = new MurBrute(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11])));
               MurBrute ancienMur = (MurBrute) listeComposantes.get(0);
               listeComposantes.set(0, mur);
               MurProfile profile = new MurProfile(((MurProfile) listeComposantes.get(1)), new PointPouce(mur.getCentre().getX().diff(ancienMur.getCentre().getX()),
                       mur.getCentre().getY().diff(ancienMur.getCentre().getY())));
               listeComposantes.set(1, profile);
               for(int i = 2, j = 0; i < 6; i++, j++){
               listeComposantes.set(i, profile.getProfilEllipses()[j]);
               }
               if (listeComposantes.size() > 6){
                   listeComposantes.set(6, new Plancher((Plancher) listeComposantes.get(6)));
               }
               if (listeComposantes.size() > 7) {
                   listeComposantes.set(7, new PoutreArriere((PoutreArriere) listeComposantes.get(7)));
               }
               if (listeComposantes.size() > 8) {
                   listeComposantes.set(8, new Hayon((Hayon) listeComposantes.get(8)));
               }
               break;
           case PROFIL_ELLIPSE_1:
               ProfilEllipse ellipse = new ProfilEllipse(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilEllipses()[0] = ellipse;
               listeComposantes.set(2, ellipse);
               break;
           case PROFIL_ELLIPSE_2:
               ellipse = new ProfilEllipse(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilEllipses()[1] = ellipse;
               listeComposantes.set(3, ellipse);
               break;
           case PROFIL_ELLIPSE_3:
               ellipse = new ProfilEllipse(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilEllipses()[2] = ellipse;
               listeComposantes.set(4, ellipse);
               break;
           case PROFIL_ELLIPSE_4:
               ellipse = new ProfilEllipse(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilEllipses()[3] = ellipse;
               listeComposantes.set(5, ellipse);
               break;
           case PLANCHER:
               Plancher plancher = new Plancher(this,
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[6], valeurs[7], valeurs[8]));
               listeComposantes.set(6, plancher);
               break;
           case POUTRE_ARRIERE:
              PoutreArriere poutre = new PoutreArriere(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[6], valeurs[7], valeurs[8]));
               listeComposantes.set(7, poutre);
               break;
           case HAYON:
               Hayon hayon = new Hayon(this,
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                       new Pouce(valeurs[9], valeurs[10], valeurs[11]),
                       new Pouce(valeurs[12], valeurs[13], valeurs[14]));
               listeComposantes.set(8, hayon);
               break;

           case MUR_SEPARATEUR:
               MurSeparateur murSeparateur = new MurSeparateur(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[6], valeurs[7], valeurs[8]));
               listeComposantes.set(9, murSeparateur);
               break;

           case TOIT:
               Toit toit = new Toit(this,
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]));
               listeComposantes.set(10, toit);
               break;

           case OUVERTURE_LATERALE:
               OuvertureLaterale ouvertureLaterale = new OuvertureLaterale( this,
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11])));
               listeComposantes.set(11, ouvertureLaterale);
               break;
       }
    }

    public void ajouterComposante(TypeComposante type) {
        switch(type){
            case MUR_PROFILE:
                // si déjà existant, size == 6 (0,1,2,3,4,5)
                if (listeComposantes.size() == 0){
                   listeComposantes.add(new MurBrute(this));
                }
                else {
                    listeComposantes.set(0, new MurBrute(this));
                }
                if (listeComposantes.size() == 1){
                    listeComposantes.add(new MurProfile(this,true));
                    for (ProfilEllipse ellipse : ((MurProfile) listeComposantes.get(1)).getProfilEllipses()){
                        listeComposantes.add(ellipse);
                    }
                }
                else {
                    listeComposantes.set(1, new MurProfile(this, true));
                    for (int i = 2; i < 6; i++){
                        ProfilEllipse ellipse = ((MurProfile) listeComposantes.get(1)).getProfilEllipses()[i];
                        listeComposantes.set(i, ellipse);
                    }
                }
                break;
            case PROFIL_BEZIER:
                 //TODO: ajouter un profil en mode bézier
                break;
            case PLANCHER:
                if (listeComposantes.size() == 6){
                    listeComposantes.add(new Plancher(this));
                }
                else {
                    listeComposantes.set(6, new Plancher(this));
                    }
                break;
            case POUTRE_ARRIERE:
                if (listeComposantes.size() == 7){
                    listeComposantes.add(new PoutreArriere(this));
                }
                else{
                    listeComposantes.set(7, new PoutreArriere(this));
                }

                break;
            case HAYON:
                if (listeComposantes.size() == 8){
                    listeComposantes.add(new Hayon(this));
                }
                else{
                    listeComposantes.set(8, new Hayon(this));
                }
                break;
        }
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

    public void setTranslate(double deltaX, double deltaY){
        System.out.println("Delta entrée: " + delta[0] + "," + delta[1]);
        delta[0] -= (deltaX/scale) - (positionSouris.getX()/scale);
        delta[1] -= (deltaY/scale) - (positionSouris.getY()/scale);

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
            else {
                //parent.setComposanteChoisie(TypeComposante.PLAN);
            }
        }
    }

    public void dragSurPlan(Point mousePoint, TypeComposante type){
        int index = getIndexComposante(type);
        if(index == -1){
            setTranslate((int) mousePoint.getX(), (int) mousePoint.getY());
        }
        else {
            if (type == TypeComposante.MUR_PROFILE){
                for (int i = 0; i < listeComposantes.size(); i++){
                 listeComposantes.get(i).translate(getPositionPlan(mousePoint));
                }
            }
            listeComposantes.get(index).translate(getPositionPlan(mousePoint));
        }
        setPositionSouris((int) mousePoint.getX(), (int) mousePoint.getY());
    }

    private int getIndexComposante(TypeComposante type){
      switch(type){
          case MUR_BRUTE:
              return 0;
          case MUR_PROFILE:
              return 1;
          case PROFIL_ELLIPSE_1:
              return 2;
          case PROFIL_ELLIPSE_2:
              return 3;
          case PROFIL_ELLIPSE_3:
              return 4;
          case PROFIL_ELLIPSE_4:
              return 5;
          case PLANCHER:
              return 6;
          case POUTRE_ARRIERE:
              return 7;
          case HAYON:
              return 8;
          case MUR_SEPARATEUR:
              return 9;
      }
      // si aucune composante n'est trouvée, retourne -1
      return -1;
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
                    if (nomComposante == "Afficher/masquer tout"){
                        composante.setVisible(estVisible);
                    }
                    else if (nomComposante == composante.toString()){
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

    public Point getPositionSouris() {
        return positionSouris;
    }
}
