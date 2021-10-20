package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.ArrayList;

public class RoulotteController {

        private ArrayList<Composante> listeComposantes;
        private MurProfile Murprofile;
        private Hayon hayon;
        private Ressorts ressorts;
        private MurBrute murBrute;
        private MurSeparateur murSeparateur;
        private Plancher plancher;
        private PoutreArriere poutreArriere;
        private Toit toit;
        private ArrayList<OuvertureLaterale> listeOuverturesLaterales;
        private ArrayList<AideDesign> listeAidesDesign;


    public RoulotteController() {
    }

    public ArrayList<Composante> getListeComposantes() {
        return listeComposantes;
    }

    public MurProfile getMurprofile() {
        return Murprofile;
    }

    public void setMurprofile(MurProfile murprofile) {
        Murprofile = murprofile;
    }

    public Hayon getHayon() {
        return hayon;
    }

    public void setHayon(Hayon hayon) {
        this.hayon = hayon;
    }

    public Ressorts getRessorts() {
        return ressorts;
    }

    public void setRessorts(Ressorts ressorts) {
        this.ressorts = ressorts;
    }

    public MurBrute getMurBrute() {
        return murBrute;
    }

    public void setMurBrute(MurBrute murBrute) {
        this.murBrute = murBrute;
    }

    public MurSeparateur getMurSeparateur() {
        return murSeparateur;
    }

    public void setMurSeparateur(MurSeparateur murSeparateur) {
        this.murSeparateur = murSeparateur;
    }

    public Plancher getPlancher() {
        return plancher;
    }

    public void setPlancher(Plancher plancher) {
        this.plancher = plancher;
    }

    public PoutreArriere getPoutreArriere() {
        return poutreArriere;
    }

    public void setPoutreArriere(PoutreArriere poutreArriere) {
        this.poutreArriere = poutreArriere;
    }

    public Toit getToit() {
        return toit;
    }

    public void setToit(Toit toit) {
        this.toit = toit;
    }

    public ArrayList<OuvertureLaterale> getListeOuverturesLaterales() {
        return listeOuverturesLaterales;
    }

    public void setListeOuverturesLaterales(ArrayList<OuvertureLaterale> listeOuverturesLaterales) {
        this.listeOuverturesLaterales = listeOuverturesLaterales;
    }

    public ArrayList<AideDesign> getListeAidesDesign() {
        return listeAidesDesign;
    }

    private void setListeAidesDesign(ArrayList<AideDesign> listeAidesDesign) {
        this.listeAidesDesign = listeAidesDesign;
    }

    public void ajouterComposante(TypeComposante composanteChoisie, Point mousePoint) {}
}
