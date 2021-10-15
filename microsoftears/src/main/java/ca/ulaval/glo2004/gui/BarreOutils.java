package ca.ulaval.glo2004.gui;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import org.kordamp.ikonli.swing.*;
import org.kordamp.ikonli.bootstrapicons.*;

import java.awt.event.*;

public class BarreOutils extends JToolBar {

    public FenetrePrincipale parent;
    private JButton zoomInButton,
            zoomOutButton,
            dragButton,
            ajoutComposanteButton,
            dessinerButton,
            remplirButton,
            removeComposanteButton,
            couleurButton;
    private JPopupMenu ajoutComposantePopup;
    private JColorChooser couleurChooser;
    private JComboBox couleursBox;
    
    public BarreOutils(FenetrePrincipale parent)
    {
        this.parent = parent;
        this.initialiser();
    }

    public void initialiser()
    {
        //======== this ========

        this.setOrientation(SwingConstants.VERTICAL);
        this.setMargin(new Insets(10,5,5,5));
        this.setFloatable(false);
        this.addSeparator();

        //======== Boutons ========
        zoomInButton = creerBouton(BootstrapIcons.ZOOM_IN, 20, Color.WHITE);
        zoomOutButton = creerBouton(BootstrapIcons.ZOOM_OUT, 20, Color.WHITE);
        dragButton  = creerBouton(BootstrapIcons.ARROWS_MOVE, 20, Color.WHITE);
        ajoutComposanteButton  = creerBouton(BootstrapIcons.PLUS_CIRCLE, 20, Color.WHITE);
        ajoutComposantePopup = creerAjoutComposantePopup();
        dessinerButton  = creerBouton(BootstrapIcons.PENCIL, 20, Color.WHITE);
        remplirButton  = creerBouton(BootstrapIcons.TRASH, 20, Color.WHITE);
        removeComposanteButton  = creerBouton(BootstrapIcons.PAINT_BUCKET, 20, Color.WHITE);
        couleurButton = creerBouton(BootstrapIcons.SQUARE_FILL, 20, Color.WHITE);
        couleurChooser = new JColorChooser();

        //======= Actions ========
        ajoutComposanteButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // on affiche le popup menu lorsque le user clique sur le bouton ajout de la barre d'outils
                ajoutComposantePopup.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        couleurButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // on affiche le colorchooser lorsque le user clique sur le carré, et on change pour la couleur choisie
                parent.couleurChoisie = JColorChooser.showDialog(couleurChooser, "Palette de couleurs", couleurButton.getBackground());
                FontIcon nouvelIcone = FontIcon.of(BootstrapIcons.SQUARE_FILL, 20, parent.couleurChoisie);
                couleurButton.setIcon(nouvelIcone);
            }
        });
    }

    private JPopupMenu creerAjoutComposantePopup() {
        JPopupMenu popup = new JPopupMenu("Ajout composante");

        popup.add(new JMenuItem(new AbstractAction("Hayon") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parent, "Ajout d'hayon selectionné");
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Plancher") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parent, "Ajout de plancher selectionné");
            }
        }));

        // TODO: ajouter les autres JMenuItem des différentes composantes du domaine

        return popup;
    }


    private JButton creerBouton(BootstrapIcons icone, int taille, Color couleur){
        JButton bouton = new javax.swing.JButton();
        FontIcon fontIcon = FontIcon.of(icone, taille, couleur);
        bouton.setIcon(fontIcon);
        this.add(bouton);
        this.addSeparator();
        return bouton;
    }

}
