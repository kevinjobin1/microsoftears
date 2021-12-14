package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.composante.*;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.Verification;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoulotteController implements Serializable{
    private Grille grille;
    private final ArrayList<Composante> listeComposantes;
    private ArrayList<OuvertureLaterale> listeOuverturesLaterales;
    private ArrayList<AideDesign> listeAidesDesign;
    private List<TypeComposante> typeComposantes;
    private final boolean afficherGrille;
    private boolean afficherLabel;
    private Composante composanteChoisie;
    private Color couleurChoisie;
    private RoulotteController undoController = null;
    private RoulotteController redoController = null;
    private boolean estImperial = true;

    // controle de l'affichage
    private static final int PIXEL_RATIO = 6;
    private final int[] delta;
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

    protected void calculerDisposition(){
        // Ordre (index): murBrute (0), murProfile(1), ellipses(2,3,4,5),
        // plancher(6), poutre(7), hayon(8), murSeparateur(9), toit (10), ressort (11),
        // porte (12), roue (13), frame (14), lit (15), personne (16), logo (17), fenetre (18)
        MurBrute murBrute = new MurBrute(this);
        listeComposantes.add(murBrute);
        MurProfile murProfile = new MurProfile(this, true, MurProfile.ModeContreplaque.COMPLET);
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
        OuvertureLaterale ouverture = new OuvertureLaterale(this, TypeComposante.PORTE);
        listeComposantes.add(ouverture);
        listeOuverturesLaterales.add(ouverture);
        AideDesign aideDesign = new AideDesign(this, TypeComposante.CADRE);
        listeComposantes.add(aideDesign);
        listeAidesDesign.add(aideDesign);
        aideDesign = new AideDesign(this, TypeComposante.ROUE);
        listeComposantes.add(aideDesign);
        listeAidesDesign.add(aideDesign);
         aideDesign = new AideDesign(this, TypeComposante.LIT);
        listeComposantes.add(aideDesign);
        listeAidesDesign.add(aideDesign);
         aideDesign = new AideDesign(this, TypeComposante.PERSONNE);
        listeComposantes.add(aideDesign);
        listeAidesDesign.add(aideDesign);
         aideDesign = new AideDesign(this, TypeComposante.LOGO);
        listeComposantes.add(aideDesign);
        listeAidesDesign.add(aideDesign);
        ouverture = new OuvertureLaterale(this, TypeComposante.FENETRE);
        listeComposantes.add(ouverture);
        listeOuverturesLaterales.add(ouverture);
        aideDesign = new AideDesign(this, TypeComposante.AIDE_AU_DESIGN);
        listeComposantes.add(aideDesign);
        listeAidesDesign.add(aideDesign);
    }

    public void ajouterComposante(TypeComposante type){
       int index = getIndexComposante(type);
       listeComposantes.get(index).estAjoute(true);
    }

    public RoulotteController deepCopy(){
        RoulotteController copy = null;
        ObjectOutputStream oos;
        ObjectInputStream ois;
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

    public void updateComposante(int[] valeurs, TypeComposante type, boolean sansSauvegarde){
        if (!sansSauvegarde) {
            undoController = this.deepCopy();
        }
        switch(type){
            case MUR_PROFILE:
                // On mets a jour le Mur Brute
                 MurBrute mur = new MurBrute(this,
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11])));
                MurBrute ancienMur = (MurBrute) listeComposantes.get(0);
                listeComposantes.set(0, mur);

                // On mets à jour le profil
                boolean modeProfil = valeurs[12] == 1 ? true: false;
                MurProfile profile = new MurProfile(((MurProfile) listeComposantes.get(1)), mur.getCentre().getX().diff(ancienMur.getCentre().getX()),
                        mur.getCentre().getY().diff(ancienMur.getCentre().getY()), modeProfil, MurProfile.ModeContreplaque.COMPLET);
                listeComposantes.set(1, profile);

                if (modeProfil){
                    // On mets à jour les ellipses
                    for(int i = 2, j = 0; i < 6; i++, j++){
                        listeComposantes.set(i, profile.getProfilEllipses()[j]);
                    }
                }
                // On mets à jour les points de contrôles
                else {
                    for(int i = 2, j = 0; i < 6; i++, j++){
                        listeComposantes.set(i, profile.getProfilBezier().getPointsControle().get(j));
                        profile.getProfilBezier().updatePointsControles(new Pouce(), new Pouce());
                    }
                }

                listeComposantes.set(6, new Plancher((Plancher) listeComposantes.get(6)));
                listeComposantes.set(7, new PoutreArriere((PoutreArriere) listeComposantes.get(7)));
                listeComposantes.set(8, new Hayon((Hayon) listeComposantes.get(8)));
                listeComposantes.set(9,new MurSeparateur((MurSeparateur) listeComposantes.get(9)));
                listeComposantes.set(10, new Toit((Toit) listeComposantes.get(10)));
                listeComposantes.set(11,new Ressorts((Ressorts) listeComposantes.get(11)));
                listeComposantes.set(12, new OuvertureLaterale((OuvertureLaterale) listeComposantes.get(12)));
                listeComposantes.set(13, new AideDesign((AideDesign) listeComposantes.get(13)));
                listeComposantes.set(14, new AideDesign((AideDesign) listeComposantes.get(14)));
                listeComposantes.set(15, new AideDesign((AideDesign) listeComposantes.get(15)));
                listeComposantes.set(16, new AideDesign((AideDesign) listeComposantes.get(16)));
                listeComposantes.set(17, new AideDesign((AideDesign) listeComposantes.get(17)));
                listeComposantes.set(18, new OuvertureLaterale((OuvertureLaterale) listeComposantes.get(18)));
                listeComposantes.set(19, new AideDesign((AideDesign) listeComposantes.get(19)));
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
                listeComposantes.set(7, new PoutreArriere((PoutreArriere) listeComposantes.get(7)));
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
                ((MurProfile) listeComposantes.get(1)).getProfilBezier().updatePointsControles(new Pouce(), new Pouce());
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
                ProfilBezier profileBezier = ((MurProfile) listeComposantes.get(1)).getProfilBezier();
                listeComposantes.set(3, pointControle);
                profileBezier.getPointsControle().set(1, pointControle);
                profileBezier.updatePointsControles(new Pouce(), new Pouce());
                listeComposantes.set(7, new PoutreArriere((PoutreArriere) listeComposantes.get(7)));
                listeComposantes.set(8, new Hayon((Hayon) listeComposantes.get(8)));
                listeComposantes.set(9,new MurSeparateur((MurSeparateur) listeComposantes.get(9)));
                listeComposantes.set(10, new Toit((Toit) listeComposantes.get(10)));
                listeComposantes.set(11,new Ressorts((Ressorts) listeComposantes.get(11)));
                break;
            case POINT_CONTROLE_3:
                pointControle = new PointControle(this,
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11]))
                        , type);
                profileBezier = ((MurProfile) listeComposantes.get(1)).getProfilBezier();
                profileBezier.getPointsControle().set(2, pointControle);
                listeComposantes.set(4, pointControle);
                profileBezier.updatePointsControles(new Pouce(), new Pouce());
                listeComposantes.set(4, pointControle);
                listeComposantes.set(7, new PoutreArriere((PoutreArriere) listeComposantes.get(7)));
                listeComposantes.set(8, new Hayon((Hayon) listeComposantes.get(8)));
                listeComposantes.set(9,new MurSeparateur((MurSeparateur) listeComposantes.get(9)));
                listeComposantes.set(10, new Toit((Toit) listeComposantes.get(10)));
                listeComposantes.set(11,new Ressorts((Ressorts) listeComposantes.get(11)));
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
                ((MurProfile) listeComposantes.get(1)).getProfilBezier().updatePointsControles(new Pouce(), new Pouce());
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
                listeComposantes.set(8, new Hayon((Hayon) listeComposantes.get(8)));
                listeComposantes.set(9,new MurSeparateur((MurSeparateur) listeComposantes.get(9)));
                listeComposantes.set(10, new Toit((Toit) listeComposantes.get(10)));
                listeComposantes.set(11,new Ressorts((Ressorts) listeComposantes.get(11)));
                break;
            case HAYON:
                Hayon hayon = new Hayon(this,
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                        new Pouce(valeurs[9], valeurs[10], valeurs[11]),
                        new Pouce(valeurs[12], valeurs[13], valeurs[14]));
                listeComposantes.set(8, hayon);
                listeComposantes.set(11,new Ressorts((Ressorts) listeComposantes.get(11)));
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

            case RESSORTS:
                Ressorts ressorts = new Ressorts(this,valeurs[0]);
                listeComposantes.set(11,ressorts);
                break;

            case PORTE:
                OuvertureLaterale ouvertureLaterale = new OuvertureLaterale(this,
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11])),
                        new Pouce(valeurs[12], valeurs[13], valeurs[14]), type, true);
                listeComposantes.set(12, ouvertureLaterale);
                listeOuverturesLaterales.set(0, ouvertureLaterale);
                break;

            case ROUE:
                AideDesign aideDesign = new AideDesign(this,
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11])), type, true);
                listeComposantes.set(14, aideDesign);
                listeAidesDesign.set(1, aideDesign);
                break;

            case CADRE:
                aideDesign = new AideDesign(this,
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11])), type, true);
                listeComposantes.set(13, aideDesign);
                listeAidesDesign.set(0, aideDesign);
                break;

            case LIT:
                aideDesign = new AideDesign(this,
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11])), type, true);
                listeComposantes.set(15, aideDesign);
                listeAidesDesign.set(2, aideDesign);
                break;

            case PERSONNE:
                aideDesign = new AideDesign(this,
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11])), type, true);
                listeComposantes.set(16, aideDesign);
                listeAidesDesign.set(3, aideDesign);
                break;

            case LOGO:
                aideDesign = new AideDesign(this,
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11])), type, true);
                listeComposantes.set(17, aideDesign);
                listeAidesDesign.set(4, aideDesign);
                break;

            case AIDE_AU_DESIGN:
                aideDesign = new AideDesign(this,
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11])), type, true);
                listeComposantes.set(19, aideDesign);
                listeAidesDesign.set(5, aideDesign);
                break;

            case FENETRE:
                ouvertureLaterale = new OuvertureLaterale(this,
                        new Pouce(valeurs[0], valeurs[1], valeurs[2]),
                        new Pouce(valeurs[3], valeurs[4], valeurs[5]),
                        new PointPouce(
                                new Pouce(valeurs[6], valeurs[7], valeurs[8]),
                                new Pouce(valeurs[9], valeurs[10], valeurs[11])),
                        new Pouce(valeurs[12], valeurs[13], valeurs[14]), type, true);
                listeComposantes.set(18, ouvertureLaterale);
                listeOuverturesLaterales.set(1, ouvertureLaterale);
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
    public double xVersEcran(Pouce x){
        return (x.toPixel(this.getPixelsToInchesRatio()) - delta[0]) * this.getScale();
    }
    public double yVersEcran(Pouce y){
        return (y.toPixel(this.getPixelsToInchesRatio()) - delta[1]) * this.getScale();
    }

    /** Converti les coordonnées de l'écran --> coordonnées réelles */
    public Pouce xVersReel(double x){
        // On veut la coordonnée sans le zoom
        x /= this.getScale();
        // On recentre le point par rapport au décalage
        x += delta[0];
        // On traduit en pouce
        x /= this.getPixelsToInchesRatio();

        return new Pouce(x);
    }

    public Pouce yVersReel(double y){
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

                if (composante.getPolygone().contient(positionClic) && composante.estVisible() && composante.estAjoute()) {
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
        PointPouce positionDrag = getPositionPlan(mousePoint);
        Point plusProcheVoisin = null;

        // Si la grille est magnétique, alors on déplace au plus proche voisin son centre
        if (grille != null && grille.estMagnetique()){
            plusProcheVoisin = grille.pointLePlusProche(mousePoint);
        }

        // Si une composante est sélectionnée, alors on la drag et on drag les autres composantes rattachées
        if(composanteChoisie != null && plusProcheVoisin == null) {

            // On drag la composante en appelant sa fonction translate
            System.out.println("translate : " + composanteChoisie);

            // cas particulier si c'est le mur brute alors on déplace les points de contrôles aussi
            if (composanteChoisie.getType() == TypeComposante.MUR_PROFILE ||
                    composanteChoisie.getType() == TypeComposante.MUR_BRUTE) {
                for (int i = 0; i < listeComposantes.size(); i++) {
                    listeComposantes.get(i).translate(positionDrag);
                }
            } else if (composanteChoisie.getType() == TypeComposante.POUTRE_ARRIERE) {
                for (int i = 7; i <= 11; i++) {
                    listeComposantes.get(i).translate(positionDrag);
                }
            } else {
                composanteChoisie.translate(positionDrag);
                updateComposante(composanteChoisie.getValeurs(), composanteChoisie.getType(), true);
            }

           /* // On s'assure que les autres composantes sont conformes
            updateComposante(composanteChoisie.getValeurs(), composanteChoisie.getType(), true);*/

        }

       // Même drag, mais en mode magnétique
        else if (composanteChoisie != null & plusProcheVoisin != null) {
            composanteChoisie.snapToGrid(getPositionPlan(plusProcheVoisin));
            updateComposante(composanteChoisie.getValeurs(), composanteChoisie.getType(), true);
        }

        // Sinon, aucune composante n'a été sélectionnée alors on drag le plan
        else{
            setTranslate((int) mousePoint.getX(), (int) mousePoint.getY());
        }

        // Finalement, on mets à jour la position de la souris
        setPositionSouris(mousePoint);


       /* if(composanteChoisie.getType() == TypeComposante.POINT_CONTROLE_1 ||
                composanteChoisie.getType() == TypeComposante.POINT_CONTROLE_2 ||
                composanteChoisie.getType() == TypeComposante.POINT_CONTROLE_3 ||
                composanteChoisie.getType() == TypeComposante.POINT_CONTROLE_4) {
            listeComposantes.set(7, new PoutreArriere((PoutreArriere) listeComposantes.get(7)));
            listeComposantes.set(11, new Ressorts((Ressorts) listeComposantes.get(11)));
            listeComposantes.set(8, new Hayon((Hayon) listeComposantes.get(8)));
            listeComposantes.set(9,new MurSeparateur((MurSeparateur) listeComposantes.get(9)));
            listeComposantes.set(10, new Toit((Toit) listeComposantes.get(10)));
        }

        if(composanteChoisie.getType() == TypeComposante.POUTRE_ARRIERE){
            listeComposantes.set(11, new Ressorts((Ressorts) listeComposantes.get(11)));
            listeComposantes.set(8, new Hayon((Hayon) listeComposantes.get(8)));
            listeComposantes.set(9,new MurSeparateur((MurSeparateur) listeComposantes.get(9)));
            listeComposantes.set(10, new Toit((Toit) listeComposantes.get(10)));
            listeComposantes.set(11,new Ressorts((Ressorts) listeComposantes.get(11)));
        }
        if(composanteChoisie.getType() == TypeComposante.PROFIL_ELLIPSE_2){
            listeComposantes.set(11, new Ressorts((Ressorts) listeComposantes.get(11)));
            listeComposantes.set(8, new Hayon((Hayon) listeComposantes.get(8)));
            listeComposantes.set(9,new MurSeparateur((MurSeparateur) listeComposantes.get(9)));
            listeComposantes.set(10, new Toit((Toit) listeComposantes.get(10)));
            listeComposantes.set(11,new Ressorts((Ressorts) listeComposantes.get(11)));
            listeComposantes.set(7, new PoutreArriere((PoutreArriere) listeComposantes.get(7)));
        }*/

    }

    public int getIndexComposante(TypeComposante type){
      switch(type){
          case MUR_BRUTE:
              return 0;
          case MUR_PROFILE:
          case CONTREPLAQUE_INTERIEUR:
          case CONTREPLAQUE_EXTERIEUR:
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
          case RESSORTS:
              return 11;
          case PORTE:
              return 12;
          case CADRE:
              return 13;
          case ROUE:
              return 14;
          case LIT:
              return 15;
          case PERSONNE:
              return 16;
          case LOGO:
              return 17;
          case FENETRE:
              return 18;
          case AIDE_AU_DESIGN:
              return 19;
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
        return PIXEL_RATIO;
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

    public void setAffichageContreplaque(String nomComposante) {
        int indexProfil = getIndexComposante(TypeComposante.MUR_PROFILE);
        ((MurProfile) (listeComposantes.get(indexProfil))).setModeContreplaque(nomComposante);
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

    public void removeComposante() {
        // si une composante est sélectionnée, alors on la retire seulement si c'est aide au design/ouvertures
        if (composanteChoisie != null){
            if (composanteChoisie.getType() == TypeComposante.ROUE ||
                    composanteChoisie.getType() == TypeComposante.CADRE ||
                    composanteChoisie.getType() == TypeComposante.LIT ||
                    composanteChoisie.getType() == TypeComposante.PERSONNE ||
                    composanteChoisie.getType() == TypeComposante.LOGO ||
                    composanteChoisie.getType() == TypeComposante.PORTE ||
                    composanteChoisie.getType() == TypeComposante.FENETRE ||
                    composanteChoisie.getType() == TypeComposante.AIDE_AU_DESIGN){
                listeComposantes.get(composanteChoisie.getIndex()).estAjoute(false);
            }
        }
    }
}
