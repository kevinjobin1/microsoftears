package ca.ulaval.glo2004.domain.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;

import ca.ulaval.glo2004.domain.composante.Composante;
import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.composante.MurProfile;
import ca.ulaval.glo2004.domain.composante.PointControle;
import ca.ulaval.glo2004.domain.composante.ProfilBezier;
import ca.ulaval.glo2004.utilitaires.Ellipse;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

public class RoulotteAfficheur
{

    private final RoulotteController roulotte;
    private final Dimension dimensionEcran;
    private final Point origineEcran;
    private final Point limiteEcran;

    
    public RoulotteAfficheur(RoulotteController roulotte, Dimension dimensionEcran)
    {
        this.roulotte = roulotte;
        this.dimensionEcran = dimensionEcran;
        this.origineEcran = new Point(0,0);
        this.limiteEcran = new Point((int) dimensionEcran.getWidth(),(int) dimensionEcran.getHeight());
       }

    //todo rendre ça propre
    public void afficher(Graphics2D g2d)
    {
        afficherGrille(g2d);
        afficherTousPolygones(g2d);
        AffineTransform af = new AffineTransform();
        af.scale(roulotte.getScale(), roulotte.getScale());
        g2d.setTransform(af);
    }

    private void afficherGrille(Graphics2D g2d){

        PointPouce debutEcran = roulotte.getPositionPlan(origineEcran);
        PointPouce finEcran = roulotte.getPositionPlan(limiteEcran);
        System.out.println("Debut écran : " + debutEcran);
        System.out.println("Fin écran" + finEcran);

        double[] p1,p2;
        g2d.setColor(Color.BLACK);
        p1 = roulotte.getPositionEcran(debutEcran);
        p2 = roulotte.getPositionEcran(finEcran);
        g2d.drawRect((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);

        if((roulotte.getScale() >= 0.5) && (roulotte.getScale() <= 1.5)){
            // lignes horizontales
            int limite = finEcran.getX().toInt();
            for (int i=debutEcran.getY().toInt(); i < finEcran.getY().toInt(); i+=3){
                p1 = roulotte.getPositionEcran(new PointPouce(debutEcran.getX(), new Pouce(i)));
                p2 = roulotte.getPositionEcran(new PointPouce(finEcran.getX(), new Pouce(i)));
                if (i % 6 != 0){
                    // Couleur ligne 3 pouces
                    g2d.setColor(new Color(200,200, 200));
                }

                else {
                    // Couleur ligne 6 pouces
                    g2d.setColor(new Color(160,160, 160));
                }
                g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
            }

            // lignes verticales
            limite = finEcran.getY().toInt();
            for (int i=debutEcran.getX().toInt(); i < finEcran.getX().toInt(); i+=3){
                p1 = roulotte.getPositionEcran(new PointPouce(new Pouce(i), debutEcran.getY()));
                p2 = roulotte.getPositionEcran(new PointPouce(new Pouce(i), finEcran.getY()));
                if (i % 6 != 0){
                    // Couleur ligne 3 pouces
                    g2d.setColor(new Color(200,200, 200));
                }

                else {
                    // Couleur ligne 6 pouces
                    g2d.setColor(new Color(160,160, 160));
                }
                g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
            }
        }
        else if (roulotte.getScale() > 1.5){
            // lignes horizontales
            int limite = finEcran.getX().toInt();
            for (int i=debutEcran.getY().toInt(); i < finEcran.getY().toInt(); i++){
                p1 = roulotte.getPositionEcran(new PointPouce(debutEcran.getX(), new Pouce(i)));
                p2 = roulotte.getPositionEcran(new PointPouce(finEcran.getX(), new Pouce(i)));
                if (i % 6 != 0){
                    // Couleur ligne pouces
                    g2d.setColor(new Color(200,200, 200));
                }
                else {
                    // Couleur ligne pieds
                    g2d.setColor(new Color(150,150, 150));
                }
                g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
            }

            // lignes verticales
            limite = finEcran.getY().toInt();
            for (int i=debutEcran.getX().toInt(); i < finEcran.getX().toInt(); i++){
                p1 = roulotte.getPositionEcran(new PointPouce(new Pouce(i), debutEcran.getY()));
                p2 = roulotte.getPositionEcran(new PointPouce(new Pouce(i), finEcran.getY()));
                if (i % 6 != 0){
                    // Couleur ligne pouces
                    g2d.setColor(new Color(200,200, 200));
                }
                else {
                    // Couleur ligne pieds
                    g2d.setColor(new Color(150,150, 150));
                }
                g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
            }
        }
        else {
            g2d.setColor(new Color(160,160, 160));
            // lignes horizontales
            int limite = finEcran.getX().toInt();
            for (int i=debutEcran.getY().toInt(); i < finEcran.getY().toInt(); i+=12){
                p1 = roulotte.getPositionEcran(new PointPouce(debutEcran.getX(), new Pouce(i)));
                p2 = roulotte.getPositionEcran(new PointPouce(finEcran.getX(), new Pouce(i)));
                g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
            }

            // lignes verticales
            limite = finEcran.getY().toInt();
            for (int i=debutEcran.getX().toInt(); i < finEcran.getX().toInt(); i+=12){
                p1 = roulotte.getPositionEcran(new PointPouce(new Pouce(i), debutEcran.getY()));
                p2 = roulotte.getPositionEcran(new PointPouce(new Pouce(i), finEcran.getY()));
                g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
            }
        }

    }

    private void afficherTousPolygones(Graphics2D g2d)
    {
        ArrayList<Composante> composantes = roulotte.getListeComposantes();
        if (!composantes.isEmpty()) {
            for (Composante composante : composantes) {
              composante.afficher(g2d);
            }
            if (!composantes.get(1).getMode()){ // mode bézier
                ArrayList<PointControle> points = ((MurProfile) composantes.get(1)).getProfilBezier().getPointsControle();
                ArrayList<PointPouce> pointsPouce = new ArrayList<>();
                for (PointControle pointControle : points){
                    if (pointControle.estVisible()){
                    pointsPouce.add(pointControle.getCentre());
                    }
                }
                afficherLignesPointsControle(g2d, pointsPouce);
            }
        }
    }

    private void afficherLignesPointsControle(Graphics2D g2d, ArrayList<PointPouce> p) {
        if (p.size() == 4) {
            // On conserve l'état de l'objet graphics avant de le modifier
            Stroke strokeInitial = g2d.getStroke();
            Composite compositeInitial = g2d.getComposite();

            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.setColor(Color.BLACK);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            // On loop sur les 4 points et on trace une ligne entre chaque
            for (int i = 0, j = p.size()-1; i < p.size(); j = i++) {
                double[] p1 = roulotte.getPositionEcran(p.get(i));
                double[] p2 = roulotte.getPositionEcran(p.get(j));
                g2d.drawLine((int) p1[0], (int) p1[1], (int) p2[0], (int) p2[1]);
            }

            // On remet le stroke et le composite initiaux
            g2d.setComposite(compositeInitial);
            g2d.setStroke(strokeInitial);
        }
    }


    //todo pourrait être pratique
    public void afficherComposante(Graphics2D g2d, Composante composante){

    }
    //todo mais pas pour le livrable 3
    private void afficherRessort(Graphics g){
    }
    
    //todo mais pas pour le livrable 3
    public static void afficherBezier() {

    }



}

