package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.utilitaires.Polygone;

public class MurProfile {

    private MurBrute murBrute;
    private Profil profil;
    private Polygone polygone;

    public MurProfile(MurBrute murBrute, Profil profil) {
        this.murBrute = murBrute;
        this.profil = profil;
    }

    public MurBrute getMurBrute() {
        return murBrute;
    }

    public void setMurBrute(MurBrute murBrute) {
        this.murBrute = murBrute;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    //à coder livrable 2 #4
    public Polygone getPolygone(){
        return null;
    }
}
