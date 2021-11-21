package ca.ulaval.glo2004.gui;

import org.kordamp.ikonli.bootstrapicons.BootstrapIcons;
import org.kordamp.ikonli.swing.FontIcon;
import ca.ulaval.glo2004.utilitaires.Pouce;
import ca.ulaval.glo2004.utilitaires.PointPouce;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe affiche la barre onglet de la Fenetre Principal
 *
 */
public class BarreOnglet extends JTabbedPane {

    public FenetrePrincipale parent;

    public BarreOnglet(FenetrePrincipale parent)
    {
        this.parent = parent;
        boolean estImperial = true;
        this.initialiser();
    }

    public void initialiser()
    {
        //== TODO: Initialiser les attributs du TabbedPane ici
        //this.setPreferredSize(new Dimension(300, 900));

        //== TODO: Remplacer this.add(...) par ajouterOnglet("MaComposanteExemple")

        this.addTab("Hayon", creerTabPanel("Hayon"));
        this.addTab("Plancher",creerTabPanel("Plancher"));
        this.addTab("Poutre",creerTabPanel("Poutre"));
        this.addTab("Profile",creerTabPanel("Profile"));
        this.addTab("Ellipse 1", creerTabPanel("Ellipse 1"));
        this.addTab("Ellipse 2", creerTabPanel("Ellipse 2"));
        this.addTab("Ellipse 3", creerTabPanel("Ellipse 3"));
        this.addTab("Ellipse 4", creerTabPanel("Ellipse 4"));

    }


    /**
     * Creates a tabbed pane with the tabs used in the initialser function
     * Sets borders using the addborder function
     * @return panel
     */
    private static JPanel creerTabPanel(String composante) {

        //creates a lowered level border using the border factory, will be used by addborder()
        Border line = BorderFactory.createLoweredBevelBorder();

        //an empty border edge is created
        Border panelEdge = BorderFactory.createEmptyBorder(0,10,10,10);

        //this is the main panel that will hold two panels inside one for image other for components
        JPanel panel = new JPanel(new GridLayout());

        //the edges of the borders are added to the JPanels created inside tabbed
        panel.setBorder(panelEdge);

        //layout is set to y_axis
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //addBorder(line,"", panel);
        addBorder(line,composante, panel);
        return panel;
    }

    /**
     * Adds borders to the panels inside the tabbed pane
     * Adds borders to containers and inserts a label inside the container
     * @param border creates border from border factory
     * @param container holds a container
     * @param composante string that holds titles of tapped panels
     */
   public static void addBorder(Border border, String composante, Container container){

       JPanel panelMesure = creerPanelMesure();

       JPanel contour = new JPanel(false);

       if (composante.equals("Hayon")){
           contour = new PanelInfoHayonMM();
       }
       if (composante.equals("Plancher")){
           contour = new PanelInfoPlancher();
       }
       if (composante.equals("Poutre")){
           contour = new PanelInfoPoutreMM();
       }
       if (composante.equals("Ellipse 1")){
           contour = new PanelInfoEllipse();
       }
       if (composante.equals("Ellipse 2")){
           contour = new PanelInfoEllipse();
       }
       if (composante.equals("Ellipse 3")){
           contour = new PanelInfoEllipse();
       }
       if (composante.equals("Ellipse 4")){
           contour = new PanelInfoEllipse();
       }
       if (composante.equals("Profile")){
           contour = new PanelInfoProfileMM();
       }


       //insert borders in the new panel we created
        panelMesure.setBorder(border);

        //borders are set
        contour.setBorder(border);

        //a space between containers is added
       container.add(panelMesure);
       container.add(Box.createRigidArea(new Dimension(0,10)));
       container.add(contour);
    }





    /**
     * Create the information for the Ellipse that will be displayed when
     * it is selected
     */
    public static JPanel creerPanelInfoEllipse(){
        //create and fill panel
        JPanel panel = new JPanel(new GridBagLayout());

        //creating constraints for the layout
        GridBagConstraints c = new GridBagConstraints();

        //=============================================================================//
        //                                                                             //
        //==========================CODE FOR JSPINNER VALUES===========================//
        //                                                                             //
        //=============================================================================//

        //adding restrictions to the spinners
        Integer value = 1;
        Integer min = 1;
        Integer max = 12;
        Integer step = 1;

        //adding restrictions to the spinner1
        Integer value1 = 0;
        Integer min1 = 0;
        Integer max1 = 12;
        Integer step1 = 1;

        //adding restrictions to the spinner2
        Integer value2 = 0;
        Integer min2 = 0;
        Integer max2 = 12;
        Integer step2 = 1;

        //=============================================================================//
        //                                                                             //
        //=====================CODE FOR LABELED ROWS OF ELLIPSE========================//
        //                                                                             //
        //=============================================================================//

        //(0,0)
        JLabel epaisseur = new JLabel("Hauteur : ");
        c.gridx = 0;
        c.gridy= 0;
        c.gridwidth = 5;
        c.weightx = 0.20;
        c.insets = new Insets(20,5,20, 5);
        panel.add(epaisseur, c);

        //(0,2)
        JLabel margeAvant = new JLabel("Longueur : ");
        c.gridx = 0;
        c.gridy= 2;
        c.gridwidth = 5;
        c.weightx = 0.20;
        c.insets = new Insets(20,5,20, 5);
        panel.add(margeAvant, c);

        //(0,4)
        JLabel margeArriere = new JLabel("Centre X : ");
        c.gridx = 0;
        c.gridy= 4;
        c.gridwidth = 5;
        c.weightx = 0.20;
        c.insets = new Insets(20,5,20, 5);
        panel.add(margeArriere, c);

        //(0,6)
        JLabel margeArriereY = new JLabel("Centre Y : ");
        c.gridx = 0;
        c.gridy= 6;
        c.gridwidth = 5;
        c.weightx = 0.20;
        c.insets = new Insets(20,5,20, 5);
        panel.add(margeArriereY, c);


        //=============================================================================//
        //                                                                             //
        //=====================CODE FOR SECOND ROW OF ELLIPSE==========================//
        //                                                                             //
        //=============================================================================//

        //(0, 1)
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(value, min, max, step));
        c.gridx = 0;
        c.gridy= 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //(1, 1)
        JLabel label = new JLabel(" '' ");
        c.gridx = 1;
        c.gridy= 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(label, c);

        //(2, 1)
        spinner = new JSpinner(new SpinnerNumberModel(value1, min1, max1, step1));
        c.gridx = 2;
        c.gridy= 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //(3, 1)
        JLabel label2 = new JLabel(" / ");
        c.gridx = 3;
        c.gridy= 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(label2, c);

        //(2, 1)
        spinner = new JSpinner(new SpinnerNumberModel(value2, min2, max2, step2));
        c.gridx = 4;
        c.gridy= 1;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //=============================================================================//
        //                                                                             //
        //=====================CODE FOR FOURTH ROW OF ELLIPSE==========================//
        //                                                                             //
        //=============================================================================//

        //(0, 3)
        spinner = new JSpinner(new SpinnerNumberModel(value, min, max, step));
        c.gridx = 0;
        c.gridy= 3;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //(1, 3)
        label = new JLabel(" '' ");
        c.gridx = 1;
        c.gridy= 3;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(label, c);

        //(2, 3)
        spinner = new JSpinner(new SpinnerNumberModel(value1, min1, max1, step1));
        c.gridx = 2;
        c.gridy= 3;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //(3, 3)
        label2 = new JLabel(" / ");
        c.gridx = 3;
        c.gridy= 3;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(label2, c);

        //(4, 3)
        spinner = new JSpinner(new SpinnerNumberModel(value2, min2, max2, step2));
        c.gridx = 4;
        c.gridy= 3;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //=============================================================================//
        //                                                                             //
        //======================CODE FOR SIXTH ROW OF ELLISPE==========================//
        //                                                                             //
        //=============================================================================//

        //(0, 5)
        spinner = new JSpinner(new SpinnerNumberModel(value, min, max, step));
        c.gridx = 0;
        c.gridy= 5;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //(1, 5)
        label = new JLabel(" '' ");
        c.gridx = 1;
        c.gridy= 5;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(label, c);

        //(2, 5)
        spinner = new JSpinner(new SpinnerNumberModel(value1, min1, max1, step1));
        c.gridx = 2;
        c.gridy= 5;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //(3, 5)
        label2 = new JLabel(" / ");
        c.gridx = 3;
        c.gridy= 5;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(label2, c);

        //(4, 5)
        spinner = new JSpinner(new SpinnerNumberModel(value2, min2, max2, step2));
        c.gridx = 4;
        c.gridy= 5;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //=============================================================================//
        //                                                                             //
        //=======================CODE FOR EIGHTH ROW OF ELLIPSE========================//
        //                                                                             //
        //=============================================================================//

        //(0, 7)
        spinner = new JSpinner(new SpinnerNumberModel(value, min, max, step));
        c.gridx = 0;
        c.gridy= 7;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //(1, 7)
        label = new JLabel(" '' ");
        c.gridx = 1;
        c.gridy= 7;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(label, c);

        //(2, 7)
        spinner = new JSpinner(new SpinnerNumberModel(value1, min1, max1, step1));
        c.gridx = 2;
        c.gridy= 7;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        //(3, 7)
        label2 = new JLabel(" / ");
        c.gridx = 3;
        c.gridy= 7;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(label2, c);

        //(4, 7)
        spinner = new JSpinner(new SpinnerNumberModel(value2, min2, max2, step2));
        c.gridx = 4;
        c.gridy= 7;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10,5,10, 5);
        panel.add(spinner, c);

        return panel;
    }

    private static JPanel creerPanelMesure(){

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
                boutonMMActionPerformed(e);
                System.out.println("boutonMMActionPerformed()");
            }
        });

        // ==== Bouton MM =======
        boutonPouces.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boutonPoucesActionPerformed(e);
                System.out.println("boutonPoucesActionPerformed()");
            }
        });

        return panel;
    }

    private static void boutonPoucesActionPerformed(ActionEvent e) {

    }

    private static void boutonMMActionPerformed(ActionEvent e) {


    }

    private JButton creerBouton(BootstrapIcons icone, int taille, Color couleur){
        JButton bouton = new javax.swing.JButton();
        FontIcon fontIcon = FontIcon.of(icone, taille, couleur);
        bouton.setIcon(fontIcon);
        this.add(bouton);
        return bouton;
    }

}


