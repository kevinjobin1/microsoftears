package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Ellipse;

public class Profil {
    private ProfilEllipse[] profilEllipses= new ProfilEllipse[4];
    private ProfilBezier profilBezier;
    /**
     * true: Éllipses
     * false: Bézier
     */
    private boolean mode;

    //à compléter
    public Profil(RoulotteController parent, boolean mode) {
        this.mode = mode;
        this.profilEllipses[0] = new ProfilEllipse(parent);
        this.profilEllipses[1] = new ProfilEllipse(parent);
        this.profilEllipses[2] = new ProfilEllipse(parent);
        this.profilEllipses[3] = new ProfilEllipse(parent);
        this.profilBezier = new ProfilBezier(parent);
    }

    public ProfilEllipse[] getProfilEllipses() {
        return profilEllipses;
    }

    public ProfilBezier getProfilBezier() {
        return profilBezier;
    }

    public boolean getMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }
}
