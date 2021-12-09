package ca.ulaval.glo2004.gui.afficheur;

import ca.ulaval.glo2004.domain.drawing.RoulotteAfficheur;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PanneauAffichage extends JPanel implements Serializable {

    protected final FenetrePrincipale parent;
    protected RoulotteAfficheur afficheurRoulotte;

    public PanneauAffichage(FenetrePrincipale parent) {
        this.parent = parent;
        this.afficheurRoulotte = new RoulotteAfficheur(parent.controller, this.getSize());
        setVisible(true);
        this.setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if (parent != null){
            super.paintComponent(g);
            afficheurRoulotte = new RoulotteAfficheur(parent.controller, this.getSize());
            Graphics2D g2d = (Graphics2D) g;
            afficheurRoulotte.afficher(g2d);
            this.setAntiAlias(g2d, true);
    }
    }

    /**
     * Sert à appliquer ou non l'antialiasing de notre objet Graphics2d pour en améliorer la qualité du rendu
     * @param g2d (Graphics2d) notre objet graphique
     * @param isAntiAlias (boolean) un booléen correspondant à On (true) / Off (false)
     */

    public static void setAntiAlias(Graphics2D g2d, boolean isAntiAlias) {
            // On/Off selon la valeur de isAntiAlias passé en paramètre
            RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    isAntiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);

            // On applique le render à notre objet Graphics2d
            renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHints(renderHints);
    }

    public void exporter(String nomFichier) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        afficheurRoulotte = new RoulotteAfficheur(parent.controller, this.getSize());
        Graphics2D g2d = image.createGraphics();
        afficheurRoulotte.afficher(g2d);
        this.setAntiAlias(g2d, true);
        g2d.dispose();
        File outputfile = new File(nomFichier);
        try {
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

}



