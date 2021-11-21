package ca.ulaval.glo2004.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoPoutreMM extends PanelComposante{
    private JLabel hauteurLabel,
            longueurLabel,
            centreXLabel;
    private JSpinner hauteurSpinner,
            longueurSpinner,
            centreXSpinner;

    int[] hauteur,
            longueur,
            centreX;

    @Override
    void initialiser() {
        this.hauteurLabel = creerLabelAttributMM("Hauteur : ", 0, 0);
        int posY = 0;
        this.hauteurSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm ", 2, posY);

        this.longueurLabel = creerLabelAttributMM("Longueur : ", 0, 1);
        posY = 1;
        this.longueurSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm ", 2, posY);


        this.centreXLabel = creerLabelAttributMM("Centre X : ", 0, 2);
        posY = 2;
        this.centreXSpinner = creerSpinnerMM(1, posY);
        creerLabelSymbole(" mm  ", 2, posY);


        // ==== Poutre =======
        hauteurSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                hauteurSpinnerChangeListener(e);
            }
        });

        // ==== Plancher =======
        longueurSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                longueurSpinnerChangeListener(e);
            }
        });
        centreXSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centreXSpinnerChangeListener(e);
            }
        });
    }
    private void hauteurSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.hauteur[0] = (int) spinner.getValue();
    }


    private void longueurSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.longueur[0] = (int) spinner.getValue();
    }

    private void centreXSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.centreX[0] = (int) spinner.getValue();
    }
}
