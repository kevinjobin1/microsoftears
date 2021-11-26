package ca.ulaval.glo2004.domain.composante;

public enum TypeComposante {
    HAYON {
        public String toString(){ return "Hayon"; }
    },
    MUR_SEPARATEUR{
        public String toString(){ return "Mur Séparateur"; }
    },
    PLANCHER{
        public String toString(){
            return "Plancher";
        }
    },
    POUTRE_ARRIERE{
        public String toString(){
            return "Poutre Arrière";
        }
    },
    RESSORTS{
        public String toString(){
            return "Ressorts";
        }
    },
    TOIT{
        public String toString(){
            return "Toit";
        }
    },
    MUR_BRUTE{
        public String toString(){
            return "Mur brute";
        }
    },
    AIDE_DESIGN{
        public String toString(){
            return "Aide au design";
        }
    },
    OUVERTURE_LATERALE{
        public String toString(){
            return "Ouverture latérale";
        }
    },
    PROFIL_BEZIER{
        public String toString(){
            return "Profil bézier";
        }
    },
    PROFIL_ELLIPSE_1{
        public String toString(){
            return "Ellipse 1";
        }
    },
    PROFIL_ELLIPSE_2{
        public String toString(){
            return "Ellipse 2";
        }
    },
    PROFIL_ELLIPSE_3{
        public String toString(){
            return "Ellipse 3";
        }
    },
    PROFIL_ELLIPSE_4{
        public String toString(){
            return "Ellipse 4";
        }
    },
    CONTREPLAQUE_EXTERIEUR{
        public String toString(){
            return "Contreplaqué extérieur";
        }
    },
    CONTREPLAQUE_INTERIEUR{
        public String toString(){
            return "Contreplaqué intérieur";
        }
    },
    MUR_PROFILE{
        public String toString(){
            return "Mur profilé";
        }
    },
    PLAN{
        public String toString(){return "Plan";}
    }

}
