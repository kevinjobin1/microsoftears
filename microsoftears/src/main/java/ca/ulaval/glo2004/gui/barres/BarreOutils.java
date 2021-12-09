package ca.ulaval.glo2004.gui.barres;

import javax.swing.*;
import java.awt.*;

import ca.ulaval.glo2004.gui.FenetrePrincipale;
import org.kordamp.ikonli.swing.*;
import org.kordamp.ikonli.bootstrapicons.*;

import java.awt.event.*;

public class BarreOutils extends JToolBar {

    public FenetrePrincipale parent;
    private JButton selectionButton,
            zoomInButton,
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
        this.setMargin(new Insets(150,5,5,5));
        this.addSeparator();
        this.setFloatable(false);

        //======== Boutons ========
        selectionButton = creerBouton(BootstrapIcons.CURSOR, 20, Color.WHITE);
        zoomInButton = creerBouton(BootstrapIcons.ZOOM_IN, 20, Color.WHITE);
        zoomOutButton = creerBouton(BootstrapIcons.ZOOM_OUT, 20, Color.WHITE);
        dragButton  = creerBouton(BootstrapIcons.ARROWS_MOVE, 20, Color.WHITE);
        ajoutComposanteButton  = creerBouton(BootstrapIcons.PLUS_CIRCLE, 20, Color.WHITE);
        ajoutComposantePopup = creerAjoutComposantePopup();
        dessinerButton  = creerBouton(BootstrapIcons.PENCIL, 20, Color.WHITE);
        removeComposanteButton  = creerBouton(BootstrapIcons.TRASH, 20, Color.WHITE);
        remplirButton = creerBouton(BootstrapIcons.PAINT_BUCKET, 20, Color.WHITE);
        couleurButton = creerBouton(BootstrapIcons.SQUARE_FILL, 20, parent.controller.getCouleurChoisie());
        couleurChooser = new JColorChooser(parent.controller.getCouleurChoisie());

        //======= Actions (Events) ========
        selectionButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                parent.setActionChoisie(FenetrePrincipale.TypeAction.SELECTION);

                parent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });


        ajoutComposanteButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // on affiche le popup menu lorsque le user clique sur le bouton ajout de la barre d'outils
                ajoutComposantePopup.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        couleurButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // on affiche le colorchooser lorsque le user clique sur le carré, et on change pour la couleur choisie
                parent.controller.setCouleurChoisie(JColorChooser.showDialog(couleurChooser, "Palette de couleurs", couleurButton.getBackground()));
                if (parent.controller.getCouleurChoisie() == null){
                    parent.controller.setCouleurChoisie(Color.WHITE);
                }
                FontIcon nouvelIcon = FontIcon.of(BootstrapIcons.SQUARE_FILL, 20, parent.controller.getCouleurChoisie());
                couleurButton.setIcon(nouvelIcon);
            }
        });

        zoomInButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                parent.controller.setScale(1);
                parent.repaint();
            }
        });

        zoomOutButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                parent.controller.setScale(-1);
                parent.repaint();
            }
        });

        remplirButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                parent.setActionChoisie(FenetrePrincipale.TypeAction.REMPLIR);
                Image image = (FontIcon.of(BootstrapIcons.PAINT_BUCKET, 20, Color.BLACK)).toImageIcon().getImage();
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                parent.setCursor(toolkit.createCustomCursor(image , new Point(e.getX(),
                        e.getY()), "Remplir"));
        }});
    }

    private JPopupMenu creerAjoutComposantePopup() {
        JPopupMenu popup = new JPopupMenu("Ajout composante");

        // icone pour le symbole "+"
        FontIcon icone = FontIcon.of(BootstrapIcons.PLUS, 20, Color.WHITE);

        // item dans le popup menu (les composantes et aide au design)
        popup.add(new JMenuItem(new AbstractAction("Aide au design", icone) {
            public void actionPerformed(ActionEvent e) {
                parent.setActionChoisie(FenetrePrincipale.TypeAction.AJOUT);
                parent.controller.addAideDesign();
                parent.setActionChoisie(FenetrePrincipale.TypeAction.SELECTION);
                parent.updateBarres();
            }
        }));

        popup.add(new JMenuItem(new AbstractAction("Ouvertures Latérales", icone) {
            public void actionPerformed(ActionEvent e) {
                parent.setActionChoisie(FenetrePrincipale.TypeAction.AJOUT);
                parent.controller.addOuvertureLateral();
                parent.setActionChoisie(FenetrePrincipale.TypeAction.SELECTION);
                parent.updateBarres();
            }
        }));

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
