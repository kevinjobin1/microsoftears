package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.gui.barres.BarreOnglet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoPlancher extends PanelComposante {

    private JLabel epaisseurLabel,
            margeAvantLabel,
            margeArriereLabel;
    private JSpinner epaisseurSpinner1,
            epaisseurSpinner2,
            epaisseurSpinner3,
            margeAvantSpinner1,
            margeAvantSpinner2,
            margeAvantSpinner3,
            margeArriereSpinner1,
            margeArriereSpinner2,
            margeArriereSpinner3;

    int[] epaisseur,
            margeAvant,
            margeArriere;

    public PanelInfoPlancher(BarreOnglet parent, int[] valeursChamps) {
        super(parent);
        initialiser(valeursChamps);
    }

    @Override
    void initialiser(){
    }
    
    void initialiser(int[] valeurs) {
        epaisseur = new int[3];
        margeAvant = new int[3];
        margeArriere = new int[3];

        for (int i = 0; i < valeurs.length; i++){
            if (i < 3){
                epaisseur[i] = valeurs[i];
            }
            else if (i < 6) {
                margeAvant[i-3] = valeurs[i];
            }
            else {
                margeArriere[i-6] = valeurs[i];
            }
        }

        this.epaisseurLabel = creerLabelAttribut("Épaisseur : ", 0,0);
        int posY = 2*getNbAttribut() -1;
        this.epaisseurSpinner1 = creerSpinnerPouces(0, posY, epaisseur);
        creerLabelSymbole( " \" ",1,posY);
        this.epaisseurSpinner2 =  creerSpinnerPouces(2, posY, epaisseur);
        creerLabelSymbole( " / ",3,posY);
        this.epaisseurSpinner3 = creerSpinnerPouces(4, posY, epaisseur);

        this.margeAvantLabel = creerLabelAttribut("Marge Avant : ", 0, 2);
        posY = 2*getNbAttribut() -1;
        this.margeAvantSpinner1 = creerSpinnerPouces(0, posY, margeAvant);
        creerLabelSymbole( " \" ",1,posY);
        this.margeAvantSpinner2 =  creerSpinnerPouces(2, posY, margeAvant);
        creerLabelSymbole( " / ",3,posY);
        this.margeAvantSpinner3 = creerSpinnerPouces(4, posY, margeAvant);

        this.margeArriereLabel = creerLabelAttribut("Marge Arrière : ", 0,4 );
        posY = 2*getNbAttribut() -1;
        this.margeArriereSpinner1 = creerSpinnerPouces(0, posY, margeArriere);
        creerLabelSymbole( " \" ",1,posY);
        this.margeArriereSpinner2 =  creerSpinnerPouces(2, posY, margeArriere);
        creerLabelSymbole( " / ",3,posY);
        this.margeArriereSpinner3 = creerSpinnerPouces(4, posY, margeArriere);



        //=============================================================================//
        //                                                                             //
        //=========================EVENTS (ACTION LISTENERS)===========================//
        //                                                                             //
        //=============================================================================//


        //==============EPAISSEUR=============//

        epaisseurSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                epaisseurSpinner1ChangeListener(e);
            }
        });

        epaisseurSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                epaisseurSpinner2ChangeListener(e);
            }
        });

        epaisseurSpinner3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                epaisseurSpinner3ChangeListener(e);
            }
        });

        //==============MARGE AVANT=============//
        margeAvantSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margeAvantSpinner1ChangeListener(e);
            }
        });

        margeAvantSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margeAvantSpinner2ChangeListener(e);
            }
        });

        margeAvantSpinner3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margeAvantSpinner3ChangeListener(e);
            }
        });

        //==============MARGE ARREIRE=============//
        margeArriereSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margeArriereSpinner1ChangeListener(e);
            }
        });

        margeArriereSpinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margeArriereSpinner2ChangeListener(e);
            }
        });

        margeArriereSpinner3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                margeArriereSpinner3ChangeListener(e);
            }
        });
    }

    private void epaisseurSpinner1ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[0] = (int) spinner.getValue();
    }
    private void epaisseurSpinner2ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[1] = (int) spinner.getValue();
    }
    private void epaisseurSpinner3ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[2] = (int) spinner.getValue();
    }

    private void margeAvantSpinner1ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeAvant[0] = (int) spinner.getValue();
    }
    private void margeAvantSpinner2ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeAvant[1] = (int) spinner.getValue();
    }
    private void margeAvantSpinner3ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeAvant[2] = (int) spinner.getValue();
    }

    private void margeArriereSpinner1ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeArriere[0] = (int) spinner.getValue();
    }
    private void margeArriereSpinner2ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeArriere[1] = (int) spinner.getValue();
    }
    private void margeArriereSpinner3ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.margeArriere[2] = (int) spinner.getValue();
    }
}
