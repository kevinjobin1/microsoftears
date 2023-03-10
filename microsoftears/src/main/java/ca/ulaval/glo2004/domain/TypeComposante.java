package ca.ulaval.glo2004.domain;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.URL;

import static ca.ulaval.glo2004.utilitaires.ImageDesign.toBufferedImage;

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

    ROUE {
        public String toString(){
            return "Roue";
        }
        public BufferedImage getImage(){
            URL image = this.getClass().getClassLoader().getResource("trailer_wheel.png");
            ImageIcon icon = new ImageIcon(image);
            return toBufferedImage(icon.getImage()); }
    },
    CADRE {
        public String toString(){
            return "Cadre (remorque)";
        }
        public BufferedImage getImage(){
            URL image = this.getClass().getClassLoader().getResource("trailer_frame.png");
            ImageIcon icon = new ImageIcon(image);
            return toBufferedImage(icon.getImage()); }
    },
    LIT {
        public String toString(){
            return "Lit";
        }
        public BufferedImage getImage(){
            URL image = this.getClass().getClassLoader().getResource("man_sleeping.png");
            ImageIcon icon = new ImageIcon(image);
            return toBufferedImage(icon.getImage()); }
    },
    PERSONNE {
        public String toString(){
            return "Personne";
        }
        public BufferedImage getImage(){
            URL image = this.getClass().getClassLoader().getResource("man.png");
            ImageIcon icon = new ImageIcon(image);
            return toBufferedImage(icon.getImage()); }
    },
    LOGO {
        public String toString(){
            return "Logo";
        }
        public BufferedImage getImage(){
            URL image = this.getClass().getClassLoader().getResource("caravan.png");
            ImageIcon icon = new ImageIcon(image);
            return toBufferedImage(icon.getImage()); }
    },
    PORTE{
        public String toString(){
            return "Porte";
        }
    },
    FENETRE{
        public String toString(){
            return "Fenêtre";
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
    AIDE_AU_DESIGN{
        public String toString(){return "Aide au design";}
    };

    public BufferedImage getImage() {
        return this.getImage();
    }
}
