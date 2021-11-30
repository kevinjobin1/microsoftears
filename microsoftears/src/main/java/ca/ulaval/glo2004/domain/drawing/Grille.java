package ca.ulaval.glo2004.domain.drawing;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;
import ca.ulaval.glo2004.utilitaires.Pouce;

import java.awt.*;

public class Grille {
    private final RoulotteController parent;
    private final Point origineEcran;
    private final Point limiteEcran;
    private final Color couleurGrille = Color.WHITE;
    private final Color couleurBordureGrille = Color.BLACK;
    private Dimension dimensions;
    private int echelle;
    private boolean estMagnetique;

    public Grille(RoulotteController parent, int echelle, boolean estMagnetique, Dimension dimensionEcran) {
        this.parent = parent;
        this.echelle = echelle;
        this.estMagnetique = estMagnetique;
        this.dimensions = dimensionEcran;
        this.origineEcran = new Point(0,0);
        this.limiteEcran = new Point((int) dimensionEcran.getWidth(),(int) dimensionEcran.getHeight());
    }

    void afficher(Graphics2D g2d){

        PointPouce debutEcran = parent.getPositionPlan(origineEcran);
        PointPouce finEcran = parent.getPositionPlan(limiteEcran);

        double[] p1,p2;
        g2d.setColor(couleurBordureGrille);
        p1 = parent.getPositionEcran(debutEcran);
        p2 = parent.getPositionEcran(finEcran);
        g2d.drawRect((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
        g2d.setColor(this.couleurGrille);
        g2d.fillRect((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);

        if((parent.getScale() >= 0.5) && (parent.getScale() <= 1.5)){
            // lignes horizontales
            int limite = finEcran.getX().toInt();
            for (int i=debutEcran.getY().toInt(); i < finEcran.getY().toInt(); i+=echelle){
                p1 = parent.getPositionEcran(new PointPouce(debutEcran.getX(), new Pouce(i)));
                p2 = parent.getPositionEcran(new PointPouce(finEcran.getX(), new Pouce(i)));
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
            for (int i=debutEcran.getX().toInt(); i < finEcran.getX().toInt(); i+=echelle){
                p1 = parent.getPositionEcran(new PointPouce(new Pouce(i), debutEcran.getY()));
                p2 = parent.getPositionEcran(new PointPouce(new Pouce(i), finEcran.getY()));
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
        else if (parent.getScale() > 1.5){
            // lignes horizontales
            int limite = finEcran.getX().toInt();
            for (int i=debutEcran.getY().toInt(); i < finEcran.getY().toInt(); i++){
                p1 = parent.getPositionEcran(new PointPouce(debutEcran.getX(), new Pouce(i)));
                p2 = parent.getPositionEcran(new PointPouce(finEcran.getX(), new Pouce(i)));
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
                p1 = parent.getPositionEcran(new PointPouce(new Pouce(i), debutEcran.getY()));
                p2 = parent.getPositionEcran(new PointPouce(new Pouce(i), finEcran.getY()));
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
            for (int i=debutEcran.getY().toInt(); i < finEcran.getY().toInt(); i+=(echelle*2)){
                p1 = parent.getPositionEcran(new PointPouce(debutEcran.getX(), new Pouce(i)));
                p2 = parent.getPositionEcran(new PointPouce(finEcran.getX(), new Pouce(i)));
                g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
            }

            // lignes verticales
            limite = finEcran.getY().toInt();
            for (int i=debutEcran.getX().toInt(); i < finEcran.getX().toInt(); i+=(echelle*2)){
                p1 = parent.getPositionEcran(new PointPouce(new Pouce(i), debutEcran.getY()));
                p2 = parent.getPositionEcran(new PointPouce(new Pouce(i), finEcran.getY()));
                g2d.drawLine((int) p1[0], (int) p1[1],(int) p2[0], (int) p2[1]);
            }
        }

    }

    public void getPositionGrille(PointPouce p){
        /*int largeurGrille = dimensions.getWidth();
        if (p.X %  < gridWidth/2)
            p.X = pt.X - pt.X % gridWidth;
        else
            p.X = pt.X + (gridWidth - pt.X % gridWidth);

        if (p.Y % gridHeight < gridHeight / 2)
            p.Y = pt.Y - pt.Y % gridHeight;
        else
            p.Y = pt.Y + (gridHeight - pt.Y % gridHeight);*/
    }

    public int getEchelle() {
        return echelle;
    }

    public void setEchelle(int echelle) {
        this.echelle = echelle;
    }

    public RoulotteController getParent() {
        return parent;
    }

    public boolean isEstMagnetique() {
        return estMagnetique;
    }

    public void setEstMagnetique(boolean estMagnetique) {
        this.estMagnetique = estMagnetique;
    }

    public Color getCouleurGrille() {
        return couleurGrille;
    }
}
