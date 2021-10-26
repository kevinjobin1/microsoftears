package ca.ulaval.glo2004.gui;

import javax.swing.*;
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
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new Label(text), BorderLayout.CENTER);
        return panel;
    }

}
