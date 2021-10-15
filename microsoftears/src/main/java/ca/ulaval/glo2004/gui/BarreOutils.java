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
            removeComposanteButton;
    private JPopupMenu ajoutComposantePopup;
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
        zoomInButton = creerBouton(BootstrapIcons.ZOOM_IN);
        zoomOutButton = creerBouton(BootstrapIcons.ZOOM_OUT);
        dragButton  = creerBouton(BootstrapIcons.ARROWS_MOVE);
        ajoutComposanteButton  = creerBouton(BootstrapIcons.PLUS_CIRCLE);
        ajoutComposantePopup = creerAjoutComposantePopup();
        dessinerButton  = creerBouton(BootstrapIcons.PENCIL);
        remplirButton  = creerBouton(BootstrapIcons.TRASH);
        removeComposanteButton  = creerBouton(BootstrapIcons.PAINT_BUCKET);
        couleursBox = new JComboBox(parent.couleurs);
        couleursBox.setEditable(true);
        couleursBox.setEditor(new CouleurComboBoxEditor(Color.WHITE));
        couleursBox.setPreferredSize(new Dimension(40,20));
        couleursBox.setMaximumSize( couleursBox.getPreferredSize() );
        this.add(couleursBox);

        //======= Actions ========
        ajoutComposanteButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // on affiche le popup menu lorsque le user clique sur le bouton ajout de la barre d'outils
                ajoutComposantePopup.show(e.getComponent(), e.getX(), e.getY());
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

    private JButton creerBouton(BootstrapIcons icone){
        JButton bouton = new javax.swing.JButton();
        FontIcon fontIcon = FontIcon.of(icone, 20, Color.WHITE);
        bouton.setIcon(fontIcon);
        this.add(bouton);
        this.addSeparator();
        return bouton;
    }

    class CouleurComboBoxEditor implements ComboBoxEditor {
        final protected JButton editor;
        protected EventListenerList listenerList = new EventListenerList();

        public CouleurComboBoxEditor(Color initialColor) {
            editor = new JButton("");
            editor.setBackground(initialColor);
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Color currentBackground = editor.getBackground();
                    Color color = JColorChooser.showDialog(editor, "Choix de couleurs", currentBackground);
                    if ((color != null) && (currentBackground != color)) {
                        editor.setBackground(color);
                        fireActionEvent(color);
                    }
                }
            };
            editor.addActionListener(actionListener);
        }

        public void addActionListener(ActionListener l) {
            listenerList.add(ActionListener.class, l);
        }

        public Component getEditorComponent() {
            return editor;
        }

        public Object getItem() {
            return editor.getBackground();
        }

        public void removeActionListener(ActionListener l) {
            listenerList.remove(ActionListener.class, l);
        }

        public void selectAll() {
            // Ignore
        }

        public void setItem(Object newValue) {
            if (newValue instanceof Color) {
                Color color = (Color) newValue;
                editor.setBackground(color);
            } else {
                try {
                    Color color = Color.decode(newValue.toString());
                    editor.setBackground(color);
                } catch (NumberFormatException e) {
                }
            }
        }

        protected void fireActionEvent(Color color) {
            Object listeners[] = listenerList.getListenerList();
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == ActionListener.class) {
                    ActionEvent actionEvent = new ActionEvent(editor, ActionEvent.ACTION_PERFORMED, color
                            .toString());
                    ((ActionListener) listeners[i + 1]).actionPerformed(actionEvent);
                }
            }
        }
    }

}
