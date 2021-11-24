package ca.ulaval.glo2004.gui.panels;


import ca.ulaval.glo2004.gui.barres.BarreOnglet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoEllipse extends PanelComposante  {
    private JLabel hauteurLabel,
            longueurLabel,
            centreXLabel,
            centreYLabel;
    private JSpinner hauteurSpinner1,
            hauteurSpinner2,
            hauteurSpinner3,
            longueurSpinner1,
            longueurSpinner2,
            longueurSpinner3,
            centreXSpinner1,
            centreXSpinner2,
            centreXSpinner3,
            centreYSpinner1,
            centreYSpinner2,
            centreYSpinner3;

    int[] hauteur,
            longueur,
            centreX,
            centreY;

    public PanelInfoEllipse(BarreOnglet parent, int[] valeursChamps) {
        super(parent);
        initialiser(valeursChamps);
    }


    void initialiser(int[] valeurs) {

        hauteur = new int[3];
        longueur = new int[3];
        centreX = new int[3];
        centreY = new int[3];

        for (int i = 0; i < valeurs.length; i++){
            if (i < 3){
                hauteur[i] = valeurs[i];
            }
            else if (i < 6) {
                longueur[i-3] = valeurs[i];
            }
            else if (i < 9){
                centreX[i-6] = valeurs[i];
            }
            else {
                centreY[i-9] = valeurs[i];
            }
        }

        this.hauteurLabel = creerLabelAttribut("Hauteur : ", 0,0);
        int posY = 2*getNbAttribut() -1;
        this.hauteurSpinner1 = creerSpinnerPouces(0, posY, hauteur);
        creerLabelSymbole( " \" ",1,posY);
        this.hauteurSpinner2 =  creerSpinnerPouces(2, posY, hauteur);
        creerLabelSymbole( " / ",3,posY);
        this.hauteurSpinner3 = creerSpinnerPouces(4, posY, hauteur);

        this.longueurLabel = creerLabelAttribut("Longueur : ", 0, 2);
        posY = 2*getNbAttribut() -1;
        this.longueurSpinner1 = creerSpinnerPouces(0, posY, longueur);
        creerLabelSymbole( " \" ",1,posY);
        this.longueurSpinner2 =  creerSpinnerPouces(2, posY, longueur);
        creerLabelSymbole( " / ",3,posY);
        this.longueurSpinner3 = creerSpinnerPouces(4, posY, longueur);

        this.centreXLabel = creerLabelAttribut("Centre X : ", 0,4 );
        posY = 2*getNbAttribut() -1;
        this.centreXSpinner1 = creerSpinnerPouces(0, posY, centreX);
        creerLabelSymbole( " \" ",1,posY);
        this.centreXSpinner2 =  creerSpinnerPouces(2, posY, centreX);
        creerLabelSymbole( " / ",3,posY);
        this.centreXSpinner3 = creerSpinnerPouces(4, posY, centreX);

        this.centreYLabel = creerLabelAttribut("Centre Y : ", 0,6 );
        posY = 2*getNbAttribut() -1;
        this.centreYSpinner1 = creerSpinnerPouces(0, posY, centreY);
        creerLabelSymbole( " \" ",1,posY);
        this.centreYSpinner2 =  creerSpinnerPouces(2, posY, centreY);
        creerLabelSymbole( " / ",3,posY);
        this.centreYSpinner3 = creerSpinnerPouces(4, posY, centreY);


        //=============================================================================//
        //                                                                             //
        //=========================EVENTS (ACTION LISTENERS)===========================//
        //                                                                             //
        //=============================================================================//


        //==============HAUTEUR=============//

        hauteurSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                hauteurSpinner1ChangeListener(e);
            }
        });

        hauteurSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                hauteurSpinner2ChangeListener(e);
            }
        });

        hauteurSpinner3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                hauteurSpinner3ChangeListener(e);
            }
        });

        //==============LONGUEUR=============//
        longueurSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                longueurSpinner1ChangeListener(e);
            }
        });

        longueurSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                longueurSpinner2ChangeListener(e);
            }
        });

        longueurSpinner3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                longueurSpinner3ChangeListener(e);
            }
        });

        //==============CENTRE X=============//
        centreXSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centreXSpinner1ChangeListener(e);
            }
        });

        centreXSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centreXSpinner2ChangeListener(e);
            }
        });

        centreXSpinner3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centreXSpinner3ChangeListener(e);
            }
        });

        //==============CENTRE Y=============//
        centreYSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centreYSpinner1ChangeListener(e);
            }
        });

        centreYSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centreYSpinner2ChangeListener(e);
            }
        });

        centreYSpinner3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centreYSpinner3ChangeListener(e);
            }
        });
    }

    private void hauteurSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.hauteur[0] =  value;
    }
    private void hauteurSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value < this.hauteur[2]){
            this.hauteur[1] =  value;
        }
        else if (value >= this.hauteur[2]){
            this.hauteurSpinner1.setValue((int) hauteurSpinner1.getValue() + 1);
            this.hauteur[1] = 0;
        }
    }
    private void hauteurSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();
        if (value > this.hauteur[1]){
            this.hauteur[2] =  value;
        }
        else{
            this.hauteurSpinner3.setValue(this.hauteur[2]);
        }

    }

    private void longueurSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.longueur[0] =  value;
    }
    private void longueurSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.longueur[1] =  value;
    }
    private void longueurSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.longueur[2] =  value;
    }

    private void centreXSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.centreX[0] =  value;
    }
    private void centreXSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.centreX[1] =  value;
    }
    private void centreXSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.centreX[2] =  value;
    }

    private void centreYSpinner1ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.centreY[0] =  value;
    }
    private void centreYSpinner2ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.centreY[1] =  value;
    }
    private void centreYSpinner3ChangeListener(ChangeEvent e) {
        int value = (int) ((JSpinner) e.getSource()).getValue();;
        this.centreY[2] =  value;
    }
    
    private void updateEllipse(){
        
    }

    @Override
    void initialiser() {

    }
}
