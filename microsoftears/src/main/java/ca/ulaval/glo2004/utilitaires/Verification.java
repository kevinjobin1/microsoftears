package ca.ulaval.glo2004.utilitaires;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.composante.*;

public class Verification {

    public static boolean verificationEpaisseurHayon(Pouce valeur){
        return valeur.ste(new Pouce(10,0,1))
                && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationDistancePoutreHayon(Pouce valeur, RoulotteController parent){
        Hayon hayon = (Hayon) parent.getListeComposantes().get(8);
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        PoutreArriere poutre = (PoutreArriere) parent.getListeComposantes().get(7);
        return valeur.st(poutre.getCentre().getX().diff(poutre.getLongueur().diviser(2)).
                diff(mur.getCentre().getX().diff(mur.getLongueur().diviser(2))).
                diff(hayon.getEpaisseur()).diff(hayon.getEpaisseurTraitScie())) &&
                valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationDistancePlancherHayon(Pouce valeur, RoulotteController parent){
        Plancher plancher = (Plancher) parent.getListeComposantes().get(6);
        return valeur.st(plancher.getMargeArriere()) && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationEpaisseurTraitScieHayon(Pouce valeur){
        return valeur.st(new Pouce(1,0,1)) && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationRayonArcCercleHayon(Pouce valeur, RoulotteController parent){
        Hayon hayon = (Hayon) parent.getListeComposantes().get(8);
        return valeur.st(hayon.getEpaisseur().add(new Pouce(1,0,1))) &&
                valeur.gt(hayon.getEpaisseur().diff(new Pouce(1,0,1)));
    }

    public static boolean verificationLongueurMurBrute(Pouce valeur){
        return valeur.ste(new Pouce(96,0,1)) && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationLargeurMurBrute(Pouce valeur){
        return valeur.ste(new Pouce(48,0,1)) && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationXEllipse0and3(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.gt(mur.getCentre().getX()) && valeur.ste(mur.getCentre().getX().add(mur.getLongueur().diviser(2)));
    }

    public static boolean verificationXEllipse1and2(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getCentre().getX()) && valeur.gte(mur.getCentre().getX().diff(mur.getLongueur().diviser(2)));
    }

    public static boolean verificationYEllipse0and1(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getCentre().getY()) && valeur.gte(mur.getCentre().getY().diff(mur.getLargeur().diviser(2)));
    }

    public static boolean verificationYEllipse2and3(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.gt(mur.getCentre().getY()) && valeur.ste(mur.getCentre().getY().add(mur.getLargeur().diviser(2)));
    }
    public static boolean verificationEpaisseurMurSeparateur(Pouce valeur){
        return valeur.ste(new Pouce(10,0,1)) &&
                valeur.gte(new Pouce(0,0,1));
    }

    public static boolean verificationHauteurMurSeparateur(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.ste(mur.getLargeur()) && valeur.gte(new Pouce(0,0,1));
    }

    public static boolean verificationDistancePoutreArriereMurSeparateur(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        PoutreArriere poutre = (PoutreArriere) parent.getListeComposantes().get(7);
        return valeur.st(mur.getCentre().getX().add(mur.getLongueur().diviser(2)).
                diff(poutre.getCentre().getX().add(poutre.getLongueur().diviser(2)))) &&
                valeur.gte(new Pouce(0,0,1));
    }

    public static boolean verificationHauteurOuvertureLaterale(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getLargeur()) && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationLargeurOuvertureLaterale(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getLongueur()) && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationCentreXOuvertureLaterale(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.gt(mur.getCentre().getX().diff(mur.getLongueur().diviser(2))) &&
                valeur.st(mur.getCentre().getX().add(mur.getLongueur().diviser(2)));
    }

    public static boolean verificationCentreYOuvertureLaterale(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.gt(mur.getCentre().getY().diff(mur.getLargeur().diviser(2))) &&
                valeur.st(mur.getCentre().getY().add(mur.getLargeur().diviser(2)));
    }

    public static boolean verificationEpaisseurPlancher(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getCentre().getY()) && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationMargeAvantPlancher(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        MurProfile profil = (MurProfile) parent.getListeComposantes().get(1);
        return valeur.st(mur.getLongueur().diviser(2)) &&
                valeur.gt(profil.getProfilEllipses()[3].getLongueur().diviser(2).
                        diff(profil.getProfilEllipses()[3].getCentre().getX().
                                diff(mur.getPolygone().getListePoints().get(3).getX())));
    }

    public static boolean verificationMargeArrierePlancher(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        MurProfile profil = (MurProfile) parent.getListeComposantes().get(1);
        return valeur.st(mur.getLongueur().diviser(2)) &&
                valeur.gt(profil.getProfilEllipses()[2].getLongueur().diviser(2).
                        diff(mur.getPolygone().getListePoints().get(2).getX().
                                diff(profil.getProfilEllipses()[2].getCentre().getX())));
    }

    public static boolean verificationLongueurPoutreArriere(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getLongueur().diviser(2)) && valeur.gt(new Pouce(0,0,1));
    }
    public static boolean verificationHauteurPoutreArriere(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.st(mur.getLargeur().diviser(2)) && valeur.gt(new Pouce(0,0,1));
    }
    public static boolean verificationCentreXPoutreArriere(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        PoutreArriere poutre = (PoutreArriere) parent.getListeComposantes().get(7);
        Hayon hayon = (Hayon) parent.getListeComposantes().get(8);
        return valeur.gt(mur.getCentre().getX().diff(mur.getLongueur().diviser(2)).add(poutre.getLongueur().diviser(2)).add(hayon.getEpaisseur()))
                && valeur.st(mur.getCentre().getX().add(mur.getLongueur().diviser(2)).diff(poutre.getLongueur().diviser(2)));
    }

    public static boolean verificationLongueurProfilEllipse(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.ste(mur.getLongueur()) && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationHauteurProfilEllipse(Pouce valeur, RoulotteController parent){
        MurBrute mur = (MurBrute) parent.getListeComposantes().get(0);
        return valeur.ste(mur.getLargeur()) && valeur.gt(new Pouce(0,0,1));
    }

    public static boolean verificationEpaisseurToit(Pouce valeur){
        return valeur.ste(new Pouce(10,0,1)) &&
                valeur.gt(new Pouce(0,0,1));
    }
}
