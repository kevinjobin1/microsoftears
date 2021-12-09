package ca.ulaval.glo2004.domain;

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
    AIDE_DESIGN_2{
        public String toString(){
            return "Aide au design 2";
        }
    },
    AIDE_DESIGN_3{
        public String toString(){
            return "Aide au design 3";
        }
    },
    AIDE_DESIGN_4{
        public String toString(){
            return "Aide au design 4";
        }
    },
    AIDE_DESIGN_5{
        public String toString(){
            return "Aide au design 5";
        }
    },
    OUVERTURE_LATERALE{
        public String toString(){
            return "Ouverture latérale";
        }
    },
    OUVERTURE_LATERALE_2{
        public String toString(){
            return "Ouverture latérale 2";
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
    POINT_CONTROLE_1{
        public String toString(){
            return "Point contrôle 1";
        }
    },
    POINT_CONTROLE_2{
        public String toString(){
            return "Point contrôle 2";
        }
    },
    POINT_CONTROLE_3{
        public String toString(){
            return "Point contrôle 3";
        }
    },
    POINT_CONTROLE_4{
        public String toString(){
            return "Point contrôle 4";
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
