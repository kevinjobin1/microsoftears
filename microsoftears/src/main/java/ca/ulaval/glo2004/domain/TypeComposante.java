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
    PROFIL_ELLIPSE{
        public String toString(){
            return "Profil ellipse";
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
    }
}
