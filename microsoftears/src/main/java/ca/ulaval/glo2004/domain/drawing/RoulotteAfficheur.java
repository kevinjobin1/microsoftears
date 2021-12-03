package ca.ulaval.glo2004.domain.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import ca.ulaval.glo2004.domain.Grille;
import ca.ulaval.glo2004.domain.composante.*;
import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.utilitaires.PointPouce;

import javax.imageio.ImageIO;
import javax.swing.*;

import static ca.ulaval.glo2004.utilitaires.ImageDesign.toBufferedImage;

public class RoulotteAfficheur
{

    private final RoulotteController roulotte;
    private final Dimension dimensionEcran;

    
    public RoulotteAfficheur(RoulotteController roulotte, Dimension dimensionEcran)
    {
        this.roulotte = roulotte;
        this.dimensionEcran = dimensionEcran;
       }

    //todo rendre ça propre
    public void afficher(Graphics2D g2d)
    {
        afficherGrille(g2d);
        afficherTousPolygones(g2d);
        //afficherImage(g2d, "man_sleeping.png", 170,350, 200, 152);
        //afficherImage(g2d, "man_standing.png", 360,230, 200, 200);
        AffineTransform af = new AffineTransform();
        af.scale(roulotte.getScale(), roulotte.getScale());
        g2d.setTransform(af);
    }

    private void afficherGrille(Graphics2D g2d) {
        roulotte.getGrille().afficher(g2d, dimensionEcran);
    }

    private void afficherTousPolygones(Graphics2D g2d)
    {
        ArrayList<Composante> composantes = roulotte.getListeComposantes();
        if (!composantes.isEmpty()) {
            for (Composante composante : composantes) {
                    composante.afficher(g2d);
            }
            if (!composantes.get(1).getModes()[0]){ // mode bézier
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

    private void afficherImage(Graphics2D g2d, String fileName, int posX, int posY, int largeur, int hauteur){
        BufferedImage image = toBufferedImage(new ImageIcon(fileName).getImage());
        g2d.drawImage(image, posX, posY, largeur, hauteur, null);
    }

}

