package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.composante.*;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;

public class RoulotteController implements Serializable{
    private Grille grille;
    private ArrayList<Composante> listeComposantes;
    private ArrayList<OuvertureLaterale> listeOuverturesLaterales;
    private ArrayList<AideDesign> listeAidesDesign;
    private boolean afficherGrille;
    private boolean afficherLabel;
    private Composante composanteChoisie;
    private Color couleurChoisie;
    private RoulotteController undoController = null;
    private RoulotteController redoController = null;
    private boolean estImperial = true;

    // controle de l'affichage
    private static final int PIXEL_RATIO = 7;
    private int[] delta;
    private Point2D positionSouris;
    private double scale;


    public RoulotteController() {
        this.listeComposantes = new ArrayList<>();
        this.listeOuverturesLaterales = new ArrayList<>();
        this.listeAidesDesign = new ArrayList<>();
        this.delta = new int[]{0,0};
        this.scale = 1;
        this.positionSouris = new Point();
        this.afficherGrille = true;
        this.afficherLabel = true;
        this.couleurChoisie = new Color(0, 217, 217); // couleur par défaut
        calculerDisposition();
        this.grille = new Grille(this, 6,false, afficherGrille, new Dimension());
    }

    public RoulotteController getUndoController() {
        return undoController;
    }

    public void setUndoController(RoulotteController undoController) {
        this.undoController = undoController;
    }

    public void setRedoController(RoulotteController redoController) {
        this.redoController = redoController;
    }

    public RoulotteController getRedoController() {
        return redoController;
    }

    public boolean afficherGrille() {
        return afficherGrille;
    }

    protected void invaliderDisposition(){
        // TODO
    }


    protected void calculerDisposition(){
        // TODO
        // Ordre (index): murBrute (0), murProfile(1), ellipses(2,3,4,5),
        // plancher(6), poutre(7), hayon(8), murSeparateur(9), toit (10), ressort (11), aide design & ouvertures (11 et +)
        MurBrute murBrute = new MurBrute(this);
        listeComposantes.add(murBrute);
        MurProfile murProfile = new MurProfile(this, false);
        listeComposantes.add(murProfile);
        if (murProfile.getMode()){
            for (ProfilEllipse ellipse : murProfile.getProfilEllipses()){
                listeComposantes.add(ellipse);}
        }
      else {
        for (PointControle pointControle : murProfile.getProfilBezier().getPointsControle()){
            listeComposantes.add(pointControle);}
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
        Ressorts ressorts = new Ressorts(this);
        listeComposantes.add(ressorts);
        OuvertureLaterale ouverture = new OuvertureLaterale(this);
        listeComposantes.add(ouverture);
        listeOuverturesLaterales.add(ouverture);
        AideDesign aideDesign = new AideDesign(this);
        listeComposantes.add(aideDesign);
        listeAidesDesign.add(aideDesign);
    }

    public void addOuvertureLateral(){
        OuvertureLaterale ouvertureLaterale = new OuvertureLaterale(this);
        listeComposantes.add(ouvertureLaterale);
        listeOuverturesLaterales.add(ouvertureLaterale);
    }

    public void addAideDesign(){
        AideDesign aideDesign = new AideDesign(this);
        listeComposantes.add(aideDesign);
        listeAidesDesign.add(aideDesign);
    }

    public RoulotteController deepCopy(){
        RoulotteController copy = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            // serialize and pass the object
            oos.writeObject(this);
            oos.flush();
            ByteArrayInputStream bin =
                    new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);
            // return the new object
            copy = (RoulotteController) ois.readObject();
            oos.close();
            ois.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return copy;
    }

    public void updateComposante(int[] valeurs, TypeComposante type){
       undoController = this.deepCopy();
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
               boolean modeProfil = valeurs[12] == 1? true: false;
               MurProfile profile = new MurProfile(((MurProfile) listeComposantes.get(1)), new PointPouce(mur.getCentre().getX().diff(ancienMur.getCentre().getX()),
                       mur.getCentre().getY().diff(ancienMur.getCentre().getY())), modeProfil, true);
               listeComposantes.set(1, profile);

               for(int i = 2, j = 0; i < 6; i++, j++){
                   if (profile.getMode()){
                   listeComposantes.set(i, profile.getProfilEllipses()[j]);
                   }
                   else {
                       listeComposantes.set(i, profile.getProfilBezier().getPointsControle().get(j));
                   }
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
           case POINT_CONTROLE_1:
               PointControle pointControle = new PointControle(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilBezier().getPointsControle().set(0, pointControle);
               ((MurProfile) listeComposantes.get(1)).getProfilBezier().updatePointsControles();
               listeComposantes.set(2, pointControle);

               break;
           case POINT_CONTROLE_2:
                pointControle = new PointControle(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilBezier().getPointsControle().set(1, pointControle);
               ((MurProfile) listeComposantes.get(1)).getProfilBezier().updatePointsControles();
               listeComposantes.set(3, pointControle);
               break;
           case POINT_CONTROLE_3:
               pointControle = new PointControle(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilBezier().getPointsControle().set(2, pointControle);
               ((MurProfile) listeComposantes.get(1)).getProfilBezier().updatePointsControles();
               listeComposantes.set(4, pointControle);
               break;
           case POINT_CONTROLE_4:
               pointControle = new PointControle(this,
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                       , type);
               ((MurProfile) listeComposantes.get(1)).getProfilBezier().getPointsControle().set(3, pointControle);
               ((MurProfile) listeComposantes.get(1)).getProfilBezier().updatePointsControles();
               listeComposantes.set(5, pointControle);
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
                               new Pouce(valeurs[9], valeurs[10], valeurs[11])),
                       new Pouce(valeurs[12], valeurs[13], valeurs[14]));
               listeComposantes.set(11, ouvertureLaterale);
               listeOuverturesLaterales.set(0, ouvertureLaterale);
               break;

           case AIDE_DESIGN:
               AideDesign aideDesign = new AideDesign(this,
                       new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                       new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                       new PointPouce(
                               new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                               new Pouce(valeurs[9], valeurs[10], valeurs[11])));
               listeComposantes.set(12, aideDesign);
               //listeAidesDesign.set(0, aideDesign);
               break;

           case RESSORTS:
               Ressorts ressorts = new Ressorts(this,valeurs[0]);
               listeComposantes.set(13,ressorts);
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
        delta[0] -= (deltaX/scale) - (positionSouris.getX()/scale);
        delta[1] -= (deltaY/scale) - (positionSouris.getY()/scale);

    }

    public PointPouce getPositionPlan(Point2D mousePoint) {

        Pouce x = xVersReel(mousePoint.getX()),
                y = yVersReel(mousePoint.getY());
        return new PointPouce(x, y);
    }

    public double[] getPositionEcran(PointPouce point){
       return new double[]{xVersEcran(point.getX()), yVersEcran(point.getY())};
    }

    /** Converti les coordonnées réelles --> coordonnées dans l'écran */
    public double xVersEcran(Pouce x)
    {
        return (x.toPixel(this.getPixelsToInchesRatio()) - delta[0]) * this.getScale();
    }

    public double yVersEcran(Pouce y)
    {
        return (y.toPixel(this.getPixelsToInchesRatio()) - delta[1]) * this.getScale();
    }

    /** Converti les coordonnées de l'écran --> coordonnées réelles */

    public Pouce xVersReel(double x)
    {
        // On veut la coordonnée sans le zoom
        x /= this.getScale();
        // On recentre le point par rapport au décalage
        x += delta[0];
        // On traduit en pouce
        x /= this.getPixelsToInchesRatio();

        return new Pouce(x);
    }

    public Pouce yVersReel(double y)
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

    public void setPositionSouris(Point mousePoint) {
        PointPouce positionSouris = getPositionPlan(mousePoint);
        int indexComposante = -1;
        if (!listeComposantes.isEmpty()) {
            for (int i=0; i < listeComposantes.size(); i++) {
                Composante composante = listeComposantes.get(i);
                composante.setAfficherPosition(false);

                if (composante.getPolygone().contient(positionSouris) && composante.estVisible()){
                    indexComposante = i;
                }
            }
            if (indexComposante != -1){
                System.out.println(listeComposantes.get(indexComposante));
                listeComposantes.get(indexComposante).setAfficherPosition(true);
            }
        }
        this.positionSouris = new Point2D.Double(mousePoint.getX(),mousePoint.getY());
        
    }

    public IComposante getComposanteChoisie() {
        return composanteChoisie;
    }

    public void setComposanteChoisie(Composante composanteChoisie) {
        this.composanteChoisie = composanteChoisie;
    }

    /** Fonction qui détermine quelle forme a été sélectionnée */
    public void clicSurPlan(Point mousePressedPoint, FenetrePrincipale.TypeAction actionChoisie) {

        PointPouce positionClic = getPositionPlan(mousePressedPoint);
        int indexComposante = -1;

        if (!listeComposantes.isEmpty()) {
            for (int i=0; i < listeComposantes.size(); i++) {
                Composante composante = listeComposantes.get(i);
                composante.setChoisie(false);

                if (composante.getPolygone().contient(positionClic) && composante.estVisible()) {
                    indexComposante = i;
                }
            }
            // Si on a cliqué sur une composante, on agit en fonction de l'action demandée
            if (indexComposante != -1){
                composanteChoisie = listeComposantes.get(indexComposante);
                switch(actionChoisie){
                    case SELECTION:
                        composanteChoisie.setChoisie(true);
                        break;
                    case REMPLIR:
                        composanteChoisie.setCouleurInitiale(couleurChoisie);
                }
            }
            // Sinon, on ne fait rien
            else
            {
                composanteChoisie = null;
            }
        }
    }


    public void dragSurPlan(Point mousePoint){
        Point plusProcheVoisin = null;
        if (grille != null && grille.estMagnetique()){
            plusProcheVoisin = grille.pointLePlusProche(mousePoint);
        }
        if(composanteChoisie != null){
            if (composanteChoisie.getType() == TypeComposante.MUR_PROFILE ||
                    composanteChoisie.getType() == TypeComposante.MUR_BRUTE) {
                for (int i = 0; i < listeComposantes.size(); i++){
                    if (plusProcheVoisin != null){
                        listeComposantes.get(i).snapToGrid(getPositionPlan(plusProcheVoisin));
                    }
                 else {listeComposantes.get(i).translate(getPositionPlan(mousePoint));}
                }
            }
            else if(composanteChoisie.getType() == TypeComposante.POINT_CONTROLE_1 ||
                    composanteChoisie.getType() == TypeComposante.POINT_CONTROLE_2 ||
                    composanteChoisie.getType() == TypeComposante.POINT_CONTROLE_3 ||
                    composanteChoisie.getType() == TypeComposante.POINT_CONTROLE_4){
                for (int i = 6; i <= 11; i++){
                    if (plusProcheVoisin != null){
                        listeComposantes.get(i).snapToGrid(getPositionPlan(plusProcheVoisin));
                    }
                    else{listeComposantes.get(i).translate(getPositionPlan(mousePoint));}
                }
            }
            else if(composanteChoisie.getType() == TypeComposante.POUTRE_ARRIERE){
                for (int i = 8; i <= 11; i++){
                    if (plusProcheVoisin != null){
                        listeComposantes.get(i).snapToGrid(getPositionPlan(plusProcheVoisin));
                    }
                    else{listeComposantes.get(i).translate(getPositionPlan(mousePoint));}
                }
            }


            if (plusProcheVoisin != null){
                composanteChoisie.snapToGrid(getPositionPlan(plusProcheVoisin));
            }
            else{composanteChoisie.translate(getPositionPlan(mousePoint));}
        }
        else {
            setTranslate((int) mousePoint.getX(), (int) mousePoint.getY());
        }
        setPositionSouris(mousePoint);
    }

    public int getIndexComposante(TypeComposante type){
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
          case POINT_CONTROLE_1:
              return 2;
          case POINT_CONTROLE_2:
              return 3;
          case POINT_CONTROLE_3:
              return 4;
          case POINT_CONTROLE_4:
              return 5;
          case PLANCHER:
              return 6;
          case POUTRE_ARRIERE:
              return 7;
          case HAYON:
              return 8;
          case MUR_SEPARATEUR:
              return 9;
          case TOIT:
              return 10;
          case OUVERTURE_LATERALE:
              return 11;
          case AIDE_DESIGN:
              return 12;
          case RESSORTS:
              return 13;
      }
      // si aucune composante n'est trouvée, retourne -1
      return -1;
    }


    public void setComposanteVisible(boolean estVisible, String nomComposante) {
        if (!listeComposantes.isEmpty()) {
                for (Composante composante : listeComposantes) {
                    if (nomComposante == "Afficher/masquer tout"){
                        composante.setVisible(estVisible);
                    }
                    else if (nomComposante == "Afficher/masquer ellipses"){
                        if (composante.getType() == TypeComposante.PROFIL_ELLIPSE_1 ||
                                composante.getType() == TypeComposante.PROFIL_ELLIPSE_2 ||
                                composante.getType() == TypeComposante.PROFIL_ELLIPSE_3 ||
                                composante.getType() == TypeComposante.PROFIL_ELLIPSE_4){
                            composante.setVisible(estVisible);
                        }
                    }
                    else if (nomComposante == "Afficher/masquer points de contrôles"){
                        if (composante.getType() == TypeComposante.POINT_CONTROLE_1 ||
                                composante.getType() == TypeComposante.POINT_CONTROLE_2 ||
                                composante.getType() == TypeComposante.POINT_CONTROLE_3 ||
                                composante.getType() == TypeComposante.POINT_CONTROLE_4){
                            composante.setVisible(estVisible);
                        }
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

    public Point2D getPositionSouris() {
        return positionSouris;
    }

    public int[] getDelta() {
        return delta;
    }


    public Grille getGrille(){
        return this.grille;
    }

    public Pouce getEchelleGrille(){
        return new Pouce(this.grille.getEchelle());
    }

    public void setGrilleMagnetique(int echelleGrille, boolean estMagnetique, boolean estAffiche, Dimension dimensionAfficheur){

        this.grille = new Grille(this, echelleGrille, estMagnetique, estAffiche, dimensionAfficheur);
    }

    public Color getCouleurChoisie() {
        return this.couleurChoisie;
    }

    public void setCouleurChoisie(Color couleur) {
        this.couleurChoisie = couleur;
    }

    public void setAffichageContreplaque(boolean afficheContreplaqueExterieur) {
        int indexProfil = getIndexComposante(TypeComposante.MUR_PROFILE);
        ((MurProfile) (listeComposantes.get(indexProfil))).setModeContreplaque(afficheContreplaqueExterieur);
    }

    public boolean estImperial() {
        return estImperial;
    }

    public void setEstImperial(boolean estImperial) {
        this.estImperial = estImperial;
    }

    public boolean afficherLabel() {
        return afficherLabel;
    }

    public void setAfficherLabel(boolean afficherLabel) {
        this.afficherLabel = afficherLabel;
    }
}
