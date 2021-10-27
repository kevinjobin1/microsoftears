package ca.ulaval.glo2004.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class BarreOnglet extends JTabbedPane {
    public FenetrePrincipale parent;


    public BarreOnglet(FenetrePrincipale parent)
    {
        this.parent = parent;
        this.initialiser();
    }

    public void initialiser()
    {
        //== TODO: Initialiser les attributs du TabbedPane ici
        this.setPreferredSize(new Dimension(300, 900));

        //== TODO: Remplacer this.add(...) par ajouterOnglet("MaComposanteExemple")

        this.addTab("Hayon", creerTabPanel("Informations du hayon..."));
        this.addTab("Plancher",creerTabPanel("Informations du plancher..."));
        this.addTab("Mur Int.",creerTabPanel("Informations du mur intérieur..."));
        this.addTab("Mur Ext.",creerTabPanel("Informations du mur extérieur..."));
    }

    private static void ajouterOnglet(String nomOnglet){
        // TODO: automatiser l'ajout d'un onglet
    }

    private static JPanel creerTabPanel(String text) {
        Border whiteLine = BorderFactory.createLineBorder(Color.white);

        //A border that puts 10 extra pixels at the sides and
        //bottom of each pane.
        Border panelEdge = BorderFactory.createEmptyBorder(0,10,10,10);
        JPanel panel = new JPanel();
        panel.setBorder(panelEdge);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //panel.add(new Label(text), BorderLayout.CENTER);
        addBorder(whiteLine, text, panel);
        return panel;
    }

    static void addBorder(Border border, String description, Container container){
        JPanel contour = new JPanel(false);
        JLabel label = new JLabel(description, JLabel.CENTER);
        contour.setLayout(new GridLayout(1, 1));
        contour.add(label);
        contour.setBorder(border);

        container.add(Box.createRigidArea(new Dimension(0,10)));
        container.add(contour);

    }

}
