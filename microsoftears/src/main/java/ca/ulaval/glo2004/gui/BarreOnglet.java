package ca.ulaval.glo2004.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Cette classe affiche la barre onglet de la Fenetre Principal
 *
 */
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

        /*this.addTab("Hayon", creerTabPanel("Informations du hayon..."));
        this.addTab("Plancher",creerTabPanel("Informations du plancher..."));
        this.addTab("Mur Int.",creerTabPanel("Informations du mur intérieur..."));
        this.addTab("Mur Ext.",creerTabPanel("Informations du mur extérieur..."));*/

        this.addTab("Hayon", creerTabPanel("Hayon"));
        this.addTab("Plancher",creerTabPanel("Plancher"));
        this.addTab("Poutre",creerTabPanel("Poutre"));
        //this.addTab("Mur Ext.",creerTabPanel());
    }

    private void ajouterOnglet(){
        // TODO: automatiser l'ajout d'un onglet
    }


    /**
     * Creates a tabbed pane with the tabs used in the initialser function
     * Sets borders using the addborder function
     * @return panel
     */
    private static JPanel creerTabPanel(String composante) {


        //creates a lowered level border using the border factory
        Border line = BorderFactory.createLoweredBevelBorder();

        //an empty border edge is created
        Border panelEdge = BorderFactory.createEmptyBorder(0,10,10,10);
        JPanel panel = new JPanel();

        //the edges of the borders are added to the JPanels created inside tabbed
        panel.setBorder(panelEdge);

        //layout is set to y_axis
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        addBorder(line,"", panel);
        addBorder(line,composante, panel);
        return panel;
    }


    /**
     * Adds borders to the panels inside the tabbed pane
     * Adds borders to containers and inserts a label inside the container
     * @param border creates border from border factory
     * @param container holds a container
     */
   public static void addBorder(Border border, String composante, Container container){

       JPanel contour = new JPanel(false);

       if (composante.equals("Hayon")){
           contour = creerPanelInfoHayon();
       }
       if (composante.equals("Plancher")){
           contour = creerPanelInfoPlancher();
       }
       if (composante.equals("Poutre")){
           contour = creerPanelInfoPoutre();
       }

        //borders are set
        contour.setBorder(border);

        //a space between containers is added
        container.add(Box.createRigidArea(new Dimension(0,10)));
        container.add(contour);
    }

    /**
     * Create the information for the Hayon that will be displayed when
     * it is selected
     */
    public static JPanel creerPanelInfoHayon(){

        //a list of the labels is created
       String[] labels = {"Épaisseur : ","Distance de la Poutre : ", "Distance du plancher : ",
               "Distance du trait de scie : ", "Rayon de l'arc du cercle : "  };

       //the length of the labels
       int pairs = labels.length;

       //create and fill panel
        JPanel panel = new JPanel(new GridLayout(0,2));

        //for loop will include all of the elements in liste
        for (int i = 0; i < pairs; i++){
            JLabel liste = new JLabel(labels[i], JLabel.LEFT);
            panel.add(liste);

            //adding restrictions to the spinners
            Integer value = 1;
            Integer min = 1;
            Integer max = 12;
            Integer step = 1;

            //creation of a spinnermodel
            SpinnerNumberModel model = new SpinnerNumberModel(value, min, max, step);

            //creation of spinner boxes
            JSpinner spinner= new JSpinner(model);
            liste.setLabelFor(spinner);
            panel.add(spinner);
        }
        return panel;
    }

    /**
     * Create the information for the Plancher that will be displayed when
     * it is selected
     */
    public static JPanel creerPanelInfoPlancher(){

        //a list of the labels is created
        String[] labels = {"Épaisseur : ","Marge avant : ", "Marge arrière : ",
                "Rectangle : "};

        //the length of the labels
        int pairs = labels.length;

        //create and fill panel
        JPanel panel = new JPanel(new GridLayout(0,2));

        //for loop will include all of the elements in liste
        for(int i = 0; i < pairs; i++){
            JLabel liste = new JLabel(labels[i], JLabel.LEFT);
            panel.add(liste);

            //adding restrictions to the spinners
            Integer value = 1;
            Integer min = 1;
            Integer max = 12;
            Integer step = 1;

            //creation of a spinnermodel
            SpinnerNumberModel model = new SpinnerNumberModel(value, min, max, step);

            //creation of spinner boxes
            JSpinner spinner= new JSpinner(model);
            liste.setLabelFor(spinner);
            panel.add(spinner);
        }
        return panel;
    }

    /**
     * Create the information for the Poutre that will be displayed when
     * it is selected
     */
    public static JPanel creerPanelInfoPoutre(){

        //a list of the labels is created
        String[] labels = {"Longueur: ","Hauteur : ", "Centre : ",
                "Rectangle : "};

        //the length of the labels
        int pairs = labels.length;

        //create and fill panel
        JPanel panel = new JPanel(new GridLayout(0,2));

        //for loop will include all of the elements in liste
        for(int i = 0; i < pairs; i++){
            JLabel liste = new JLabel(labels[i], JLabel.LEFT);
            panel.add(liste);
            JTextField textField = new JTextField(0);
            liste.setLabelFor(textField);
            panel.add(textField);
        }
        return panel;
    }

}
