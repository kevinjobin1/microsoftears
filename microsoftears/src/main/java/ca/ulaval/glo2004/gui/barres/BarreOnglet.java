package ca.ulaval.glo2004.gui.barres;

import ca.ulaval.glo2004.domain.IComposante;
import ca.ulaval.glo2004.domain.composante.Toit;
import ca.ulaval.glo2004.domain.composante.TypeComposante;
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

    private boolean estImperial;
    public FenetrePrincipale parent;

    public BarreOnglet(FenetrePrincipale parent)
    {
        this.parent = parent;
        this.estImperial = true;
        this.initialiser();
    }

    private void initialiser()
    {
        for(IComposante composante : parent.controller.getListeIComposantes()){
            if (composante.getType() == TypeComposante.MUR_BRUTE ||
                    composante.getType() == TypeComposante.MUR_SEPARATEUR){
                for (int i : composante.getValeurs()){
                System.out.println(i);}
            }
            else if (composante.getType() == TypeComposante.MUR_PROFILE ||
            composante.getType() == TypeComposante.PROFIL_ELLIPSE_1 ||
                    composante.getType() == TypeComposante.PROFIL_ELLIPSE_2 ||
                    composante.getType() == TypeComposante.PROFIL_ELLIPSE_3 ||
                    composante.getType() == TypeComposante.PROFIL_ELLIPSE_4 ||
                    composante.getType() == TypeComposante.POUTRE_ARRIERE ||
                    composante.getType() == TypeComposante.PLANCHER ||
                    composante.getType() == TypeComposante.HAYON ||
                    composante.getType() == TypeComposante.TOIT){
                this.addTab(composante.toString(), creerTabPanel(composante));
            }
            else{
                this.addTab(composante.toString(), creerTabPanel(composante));

            }

        }

    }

    public void update(){
        this.removeAll();
        for(IComposante composante : parent.controller.getListeIComposantes()){
            if (composante.getType() == TypeComposante.MUR_BRUTE ||
                    composante.getType() == TypeComposante.MUR_SEPARATEUR){
                for (int i : composante.getValeurs()){
                    System.out.println(i);}
            }
            else if (composante.getType() == TypeComposante.MUR_PROFILE ||
                    composante.getType() == TypeComposante.PROFIL_ELLIPSE_1 ||
                    composante.getType() == TypeComposante.PROFIL_ELLIPSE_2 ||
                    composante.getType() == TypeComposante.PROFIL_ELLIPSE_3 ||
                    composante.getType() == TypeComposante.PROFIL_ELLIPSE_4 ||
                    composante.getType() == TypeComposante.POUTRE_ARRIERE ||
                    composante.getType() == TypeComposante.PLANCHER ||
                    composante.getType() == TypeComposante.HAYON){
                this.addTab(composante.toString(), creerTabPanel(composante));
            }
            else{
                this.addTab(composante.toString(), creerTabPanel(composante));

            }
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

       JPanel panelMesure = creerPanelMesure();

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
             if (composante.toString().equals("Mur profilé")) {
                contour = new PanelInfoProfile(this, composante);
            }
             if (composante.toString().equals("Toit")) {
                contour = new PanelInfoToit(this, composante);
            }
       //borders are set
        panelMesure.setBorder(line);
        contour.setBorder(line);

       tabPanel.add(panelMesure);
       tabPanel.add(Box.createRigidArea(new Dimension(0,10)));
       tabPanel.add(contour);

       return tabPanel;

    }

    private JPanel creerPanelMesure(){

        //creating a panel to hold the buttons inside
        JPanel panel = new JPanel(new GridBagLayout());

        ButtonGroup groupeBoutonMesure = new ButtonGroup();

        //establishing constraints for the gridbaglayout
        GridBagConstraints c = new GridBagConstraints();

        //creating first button
        JToggleButton boutonMM = new JToggleButton("Métrique (mm)");
        boutonMM.setSelected(false);
        groupeBoutonMesure.add(boutonMM);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        panel.add(boutonMM, c);

        //creating second button
        JToggleButton boutonPouces = new JToggleButton("Impérial (\")");
        boutonPouces.setSelected(true);
        groupeBoutonMesure.add(boutonPouces);
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        panel.add(boutonPouces, c);

        // ==== Bouton MM =======
        boutonMM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boutonUniteMesureActionPerformed(e);
            }
        });

        // ==== Bouton MM =======
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

        this.estImperial = bouton.getText().equals("Impérial (\")");
        this.rafraichir();
        this.setSelectedIndex(index);
    }


    public boolean estImperial(){
        return this.estImperial;
    }

}


