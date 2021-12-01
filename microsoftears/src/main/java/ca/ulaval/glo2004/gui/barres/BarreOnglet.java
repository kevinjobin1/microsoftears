package ca.ulaval.glo2004.gui.barres;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.gui.FenetrePrincipale;
import ca.ulaval.glo2004.gui.panels.*;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe affiche la barre onglet de la Fenetre Principale
 *
 */
public class BarreOnglet extends JTabbedPane {

    public FenetrePrincipale parent;

    public BarreOnglet(FenetrePrincipale parent)
    {
        this.parent = parent;
        this.initialiser();
    }

    private void initialiser()
    {
        for(IComposante composante : parent.controller.getListeIComposantes()){
            if (composante.getType() == TypeComposante.MUR_BRUTE){
                // TODO: à traiter éventuellement
            }
            else{
                this.addTab(composante.toString(), creerTabPanel(composante));
            }
        }
    }

    public void update(){
        this.removeAll();
        for(IComposante composante : parent.controller.getListeIComposantes()){
            if (composante.getType() == TypeComposante.MUR_BRUTE){
                // TODO: à traiter éventuellement
            }
            else{
                this.addTab(composante.toString(), creerTabPanel(composante));

            }
            //parent.controller.updateComposante(composante.getValeurs(), composante.getType());
        }
    }


   public JPanel creerTabPanel(IComposante composante){
       //creates a lowered level border using the border factory, will be used by addborder()
       Border line = BorderFactory.createLoweredBevelBorder();

       //an empty border edge is created
       Border panelEdge = BorderFactory.createEmptyBorder(0,10,10,10);

       //this is the main panel that will hold two panels inside one for image other for components
       JPanel tabPanel = new JPanel(new GridLayout());

       //the edges of the borders are added to the JPanels created inside tabbed
       tabPanel.setBorder(panelEdge);

       //layout is set to y_axis
       tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.Y_AXIS));

       PanelComposante contour = null;

            if (composante.toString().equals("Hayon")) {
                contour = new PanelInfoHayon(this, composante);
            }
             if (composante.toString().equals("Plancher")) {
                contour = new PanelInfoPlancher(this, composante);
            }
             if (composante.toString().equals("Poutre Arrière")) {
                contour = new PanelInfoPoutre(this, composante);
            }
             if (composante.toString().equals("Ellipse 1")) {
                contour = new PanelInfoEllipse(this, composante);
            }
             if (composante.toString().equals("Ellipse 2")) {
                contour = new PanelInfoEllipse(this, composante);
            }
             if (composante.toString().equals("Ellipse 3")) {
                contour = new PanelInfoEllipse(this, composante);
            }
             if (composante.toString().equals("Ellipse 4")) {
                contour = new PanelInfoEllipse(this, composante);
            }
             if (composante.toString().equals("Point contrôle 1")){
                 contour = new PanelInfoPointControle(this, composante);
             }
           if (composante.toString().equals("Point contrôle 2")){
               contour = new PanelInfoPointControle(this, composante);
           }
           if (composante.toString().equals("Point contrôle 3")){
               contour = new PanelInfoPointControle(this, composante);
           }
           if (composante.toString().equals("Point contrôle 4")){
               contour = new PanelInfoPointControle(this, composante);
           }
             if (composante.toString().equals("Mur profilé")) {
                contour = new PanelInfoProfile(this, composante);
            }
            if (composante.toString().equals("Ouverture latérale")) {
                contour = new PanelInfoOuvertureLaterales(this, composante);
            }
            if (composante.toString().equals("Toit")) {
                contour = new PanelInfoToit(this, composante);
            }
           if (composante.toString().equals("Mur Séparateur")) {
               contour = new PanelInfoMurSeparateur(this, composante);
           }

       JPanel panelMesure = creerPanelMesure();
       //borders are set
        panelMesure.setBorder(line);
        contour.setBorder(line);

       tabPanel.add(panelMesure);
       tabPanel.add(Box.createRigidArea(new Dimension(0,10)));
       tabPanel.add(contour);

       return tabPanel;

    }

    private JPanel creerPanelMesure(){

        // Notre panel d'options
        JPanel panel = new JPanel(new GridBagLayout());
        ButtonGroup groupeBoutonMesure = new ButtonGroup();

        // Layout gridbaglayout
        GridBagConstraints c = new GridBagConstraints();

        // Bouton mm
        JToggleButton boutonMM = new JToggleButton("Métrique (mm)");
        boutonMM.setSelected(false);
        groupeBoutonMesure.add(boutonMM);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        panel.add(boutonMM, c);

        // Bouton pouces
        JToggleButton boutonPouces = new JToggleButton("Impérial (\")");
        boutonPouces.setSelected(true);
        groupeBoutonMesure.add(boutonPouces);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        panel.add(boutonPouces, c);

        // ==== Bouton MM =======
        boutonMM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boutonUniteMesureActionPerformed(e);
            }
        });

        // ==== Bouton Pouces =======
        boutonPouces.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boutonUniteMesureActionPerformed(e);
            }
        });

        return panel;
    }

    public void rafraichir(){
        this.removeAll();
        this.initialiser();
        this.repaint();
    }

    private void boutonUniteMesureActionPerformed(ActionEvent e) {
        JToggleButton bouton = (JToggleButton) e.getSource();
        int index = this.getSelectedIndex();
        this.parent.estImperial(bouton.getText().equals("Impérial (\")"));
        this.rafraichir();
        this.setSelectedIndex(index);
    }

    public boolean estImperial(){
        return parent.estImperial();
    }

}


