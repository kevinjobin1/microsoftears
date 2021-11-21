package ca.ulaval.glo2004.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInfoHayon extends PanelComposante{

    private JLabel epaisseurLabel,
     poutreLabel,
     plancherLabel,
     scieLabel,
     rayonLabel;
    private JSpinner epaisseurSpinner1,
     epaisseurSpinner2,
     epaisseurSpinner3,
     poutreSpinner1,
     poutreSpinner2,
     poutreSpinner3,
     plancherSpinner1,
     plancherSpinner2,
     plancherSpinner3,
     scieSpinner1,
     scieSpinner2,
     scieSpinner3,
     rayonSpinner1,
     rayonSpinner2,
     rayonSpinner3;
    int[] epaisseur,
            poutre,
            plancher,
            scie,
            rayon;

    public PanelInfoHayon() {
        super();
        this.initialiser();
    }

    @Override
    void initialiser() {

        this.epaisseur = new int[3];
        this.poutre = new int[3];
        this.plancher = new int[3];
        this.scie = new int[3];
        this.rayon= new int[3];

        this.epaisseurLabel = creerLabelAttribut("Épaisseur : ", 0,0);
        int posY = 2*getNbAttribut() -1;
        this.epaisseurSpinner1 = creerSpinnerPouces(0, posY);
        creerLabelSymbole( " \" ",1,posY);
        this.epaisseurSpinner2 =  creerSpinnerPouces(2, posY);
        creerLabelSymbole( " / ",3,posY);
        this.epaisseurSpinner3 = creerSpinnerPouces(4, posY);

        this.poutreLabel = creerLabelAttribut("Distance de la poutre : ", 0, 2);
        posY = 2*getNbAttribut() -1;
        this.poutreSpinner1 = creerSpinnerPouces(0, posY);
        creerLabelSymbole( " \" ",1,posY);
        this.poutreSpinner2 =  creerSpinnerPouces(2, posY);
        creerLabelSymbole( " / ",3,posY);
        this.poutreSpinner3 = creerSpinnerPouces(4, posY);

        this.plancherLabel = creerLabelAttribut("Distance du plancher : ", 0,4 );
        posY = 2*getNbAttribut() -1;
        this.plancherSpinner1 = creerSpinnerPouces(0, posY);
        creerLabelSymbole( " \" ",1,posY);
        this.plancherSpinner2 =  creerSpinnerPouces(2, posY);
        creerLabelSymbole( " / ",3,posY);
        this.plancherSpinner3 = creerSpinnerPouces(4, posY);

        this.scieLabel = creerLabelAttribut("Distance du trait de scie : ", 0,6);
        posY = 2*getNbAttribut() -1;
        this.scieSpinner1 = creerSpinnerPouces(0, posY);
        creerLabelSymbole( " \" ",1,posY);
        this.scieSpinner2 =  creerSpinnerPouces(2, posY);
        creerLabelSymbole( " / ",3,posY);
        this.scieSpinner3 = creerSpinnerPouces(4, posY);

        this.rayonLabel = creerLabelAttribut("Rayon de l'arc du cercle : ", 0, 8);
        posY = 2*getNbAttribut() -1;
        this.rayonSpinner1 = creerSpinnerPouces(0, posY);
        creerLabelSymbole( " \" ",1,posY);
        this.rayonSpinner2 =  creerSpinnerPouces(2, posY);
        creerLabelSymbole( " / ",3,posY);
        this.rayonSpinner3 = creerSpinnerPouces(4, posY);

        // Events (ActionListener) //

        // ==== Bouton nouveau projet =======
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


    }

    private void epaisseurSpinner1ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[0] = (int) spinner.getValue();
        System.out.println(epaisseur[0]);
    }
    private void epaisseurSpinner2ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[1] = (int) spinner.getValue();
        System.out.println(epaisseur[1]);
    }
    private void epaisseurSpinner3ChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.epaisseur[2] = (int) spinner.getValue();
        System.out.println(epaisseur[2]);
    }


}
