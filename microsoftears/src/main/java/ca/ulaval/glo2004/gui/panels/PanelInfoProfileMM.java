package ca.ulaval.glo2004.gui.panels;

import ca.ulaval.glo2004.gui.barres.BarreOnglet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelInfoProfileMM extends PanelComposante {
    private JLabel hauteurLabel,
            longueurLabel,
            centreXLabel,
            centreYLabel;
    private JSpinner hauteurSpinner,
            longueurSpinner,
            centreXSpinner,
            centreYSpinner;

    int[] hauteur,
            longueur,
            centreX,
            centreY;

    public PanelInfoProfileMM(BarreOnglet parent) {
        super(parent);
    }

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

        this.centreYLabel = creerLabelAttributMM("Centre Y : ", 0, 3);
        posY = 3;
        this.centreYSpinner = creerSpinnerMM(1, posY);
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

        centreYSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                centreYSpinnerChangeListener(e);
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

    private void centreYSpinnerChangeListener(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        this.centreY[0] = (int) spinner.getValue();
    }
}
