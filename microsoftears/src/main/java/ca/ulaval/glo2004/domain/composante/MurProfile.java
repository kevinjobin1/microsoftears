package ca.ulaval.glo2004.domain.composante;

import ca.ulaval.glo2004.domain.RoulotteController;
import ca.ulaval.glo2004.domain.TypeComposante;
import ca.ulaval.glo2004.utilitaires.*;
import ca.ulaval.glo2004.utilitaires.Rectangle;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static ca.ulaval.glo2004.utilitaires.Forme.*;

public class MurProfile extends Composante{

    /**
     * profilEllipses[0] est l'éllipse en haut à droite
     * profilEllipses[1] est l'éllipse en haut à gauche
     * profilEllipses[2] est l'éllipse en bas à gauche
     * profilEllipses[3] est l'éllipse en bas à droite
     */
    private ProfilEllipse[] profilEllipses;
    private ProfilBezier profilBezier;
    /**
     * true: Éllipses
     * false: Bézier
     */
    private boolean mode; // true -> profil ellipse, false -> bézier
    private boolean modeContreplaque; // true -> si extérieur, false -> intérieur

    public MurProfile(RoulotteController parent) {
        super(parent);
        this.mode = true; // ellipses par défaut
        this.modeContreplaque = true; // on affiche le contreplaqué extérieur par défaut
        this.profilEllipses = new ProfilEllipse[4];
        this.initialiserEllipses();
        this.profilBezier = new ProfilBezier(parent);
        this.setCouleurInitiale(new Color(100,100,100));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.MUR_PROFILE);
        this.setPolygone(getPolygone());
    }
    public MurProfile(RoulotteController parent, boolean mode) {
        super(parent);
        this.mode = mode; // ellipses par défaut
        this.modeContreplaque = true; // on affiche le contreplaqué extérieur par défaut
        this.profilEllipses = new ProfilEllipse[4];
        this.initialiserEllipses();
        this.profilBezier = new ProfilBezier(parent);
        this.setCouleurInitiale(new Color(100,100,100));
        this.setCouleur(getCouleurInitiale());
        this.setType(TypeComposante.MUR_PROFILE);
        this.setPolygone(getPolygone());
    }


    /** Constructeur copie */
    public MurProfile(MurProfile copie, PointPouce decalage, boolean modeProfil, boolean afficheContreplaque){
        super(copie.getParent());
        this.mode = modeProfil;
        this.modeContreplaque = afficheContreplaque;
        this.profilEllipses = copie.getProfilEllipses();
        updateProfilEllipses(decalage);
        this.profilBezier = copie.getProfilBezier();
        this.setCouleurInitiale(copie.getCouleurInitiale());
        this.setCouleur(copie.getCouleur());
        this.setType(copie.getType());
        this.setPolygone(copie.getPolygone());
    }



    @Override
    public void afficher(Graphics2D g2d){
        if (estVisible()){
            if(parent.afficherLabel()){
            if (getAfficherPosition()) {
                g2d.setColor(Color.BLACK);
                g2d.drawString(this.toString(), (float) parent.getPositionSouris().getX() + 30, (float) parent.getPositionSouris().getY());
            }}

            Area area = getArea(); // on va l'aire totale du profile

            // Portion contreplaqué extérieur
            if (!parent.getListeOuverturesLaterales().isEmpty()){
                // on retranche tous les ouvertures latérales
                for(OuvertureLaterale ouverture : parent.getListeOuverturesLaterales()){
                    Area areaOuverture = ouverture.getArea();
                    area.subtract(areaOuverture);
                }
            }

            int indexHayon = parent.getIndexComposante(TypeComposante.HAYON);
            Hayon hayon = ((Hayon) (parent.getListeComposantes().get(indexHayon)));
            if (indexHayon != -1){
                Area areaHayon = hayon.getArea();
                area.subtract(areaHayon);
            }

            int indexPlancher = parent.getIndexComposante(TypeComposante.PLANCHER);
            Plancher plancher = ((Plancher) (parent.getListeComposantes().get(indexPlancher)));

            // Portion a retiré
            Pouce epaisseurHayon = hayon.getEpaisseur();
            Pouce distancePlancher = hayon.getDistancePlancher();
            Pouce centreXPlancher = plancher.getCentre().getX();
            Pouce centreYPlancher = plancher.getCentre().getY();
            Pouce epaisseurPlancher = plancher.getEpaisseur();
            Pouce largeurPlancher = plancher.getRectangle().getLongueur();
            Pouce centreXPortionARetirer = centreXPlancher.diff(largeurPlancher.diviser(2)).diff(distancePlancher.diviser(2));
            Pouce centreYPortionARetirer = centreYPlancher.add(epaisseurPlancher.diviser(2)).diff(epaisseurHayon.diviser(2));
            PointPouce centrePortionARetirer = new PointPouce(centreXPortionARetirer, centreYPortionARetirer); // TODO: à faire, calculer le centre du rectangle
            Rectangle portionARetirer = new Rectangle(distancePlancher, epaisseurHayon, centrePortionARetirer);

            Path2D path = new Path2D.Double();
            LinkedList<PointPouce> polygoneList = portionARetirer.getPolygone().getListePoints();
            double[] point;
            for (int i = 0; i < polygoneList.size(); i++){
                point = parent.getPositionEcran(polygoneList.get(i));
                if(i == 0) {
                    path.moveTo(point[0], point[1]);
                }
                else{
                    path.lineTo(point[0] ,point[1]);
                }
            }
            path.closePath();

            Area areaPortionARetirer = new Area(path);
            area.subtract(areaPortionARetirer);

            if(!modeContreplaque){
            // Portion contreplaqué intérieur
            if (indexPlancher != -1){
                Area areaPlancher = plancher.getArea();
                area.subtract(areaPlancher);
            }

            int indexMurSeparateur = parent.getIndexComposante(TypeComposante.MUR_SEPARATEUR);
            if (indexMurSeparateur != -1){
               Area areaMurSeparateur = parent.getListeComposantes().get(indexMurSeparateur).getArea();
               area.subtract(areaMurSeparateur);
            }
            int indexToit = parent.getIndexComposante(TypeComposante.TOIT);
            if (indexToit != -1) {
                Area areaToit = parent.getListeComposantes().get(indexToit).getArea();
                area.subtract(areaToit);
            }
                int indexPoutre = parent.getIndexComposante(TypeComposante.POUTRE_ARRIERE);
                if (indexPoutre != -1) {
                    Area areaPoutre = parent.getListeComposantes().get(indexPoutre).getArea();
                    area.subtract(areaPoutre);
                }
            }
            // Sinon, on skip direct ici

            Composite compositeInitial = g2d.getComposite();
            g2d.setComposite(definirComposite(getTransparence()));
            g2d.setPaint(getCouleur());
            g2d.fill(area);
            g2d.setComposite(compositeInitial);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(area);
            }
    }

    private void initialiserEllipses(){

        LinkedList<PointPouce> murBrutePoints = parent.getListeComposantes().get(0).getPolygone().getListePoints();

        this.profilEllipses[0] = new ProfilEllipse(parent,
                new Pouce(40,0,1),
                new Pouce(30,0,1),
                new PointPouce(
                        murBrutePoints.get(0).getX().diff(new Pouce(15,0,1)),
                        murBrutePoints.get(0).getY().add(new Pouce(15,0,1))
                ), TypeComposante.PROFIL_ELLIPSE_1); //ellipse en haut à droite

        this.profilEllipses[1] = new ProfilEllipse(parent,
                new Pouce(40,0,1),
                new Pouce(30,0,1),
                new PointPouce(
                        murBrutePoints.get(1).getX().add(new Pouce(15,1,2)),
                        murBrutePoints.get(1).getY().add(new Pouce(15,0,1))
                ), TypeComposante.PROFIL_ELLIPSE_2); //ellipse en haut à gauche

        this.profilEllipses[2] = new ProfilEllipse(parent,
                new Pouce(15,0,1),
                new Pouce(12,0,1),
                new PointPouce(
                        murBrutePoints.get(2).getX().add(new Pouce(7,0,1)),
                        murBrutePoints.get(2).getY().diff(new Pouce(5,0,1))
                ), TypeComposante.PROFIL_ELLIPSE_3); //ellipse en bas à gauche

        this.profilEllipses[3] = new ProfilEllipse(parent,
                new Pouce(5,0,1),
                new Pouce(5,0,1),
                murBrutePoints.get(3),
                TypeComposante.PROFIL_ELLIPSE_4); //ellipse en bas à droite

    }

    private void updateProfilEllipses(PointPouce decalage) {
        for (int i = 0; i < 4; i++) {
            this.profilEllipses[i] = new ProfilEllipse(parent,
                    this.profilEllipses[i].getLongueur(),
                    this.profilEllipses[i].getHauteur(),
                    new PointPouce(
                            this.profilEllipses[i].getCentre().getX().add(decalage.getX()),
                            this.profilEllipses[i].getCentre().getY().add(decalage.getY())
                    ), this.profilEllipses[i].getType());
        }
    }

    public ProfilEllipse[] getProfilEllipses() {
        return profilEllipses;
    }

    public void setProfilEllipses(ProfilEllipse[] profilEllipses) {
        this.profilEllipses = profilEllipses;
    }

    public ProfilBezier getProfilBezier() {
        return profilBezier;
    }

    public void setProfilBezier(ProfilBezier profilBezier) {
        this.profilBezier = profilBezier;
    }

    public boolean getMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    @Override
    public Polygone getPolygone(){
        Polygone polygone;
        if(mode){
            polygone = new Polygone(listePointsModeEllipse());
        } else {
            polygone = new Polygone(listePointsModeBezier());
        }
        return polygone;
    }

    @Override
    public PointPouce getCentre() {
        return null;
    }


    @Override
    public int[] getValeurs() {
        int[] valeurs = new int[13];
        int[] valeursMurBrute = parent.getListeComposantes().get(0).getValeurs();
        for (int i = 0; i < valeursMurBrute.length; i++) {
            valeurs = Arrays.copyOf(valeursMurBrute, valeursMurBrute.length +1);
        }
        valeurs[12] = mode ? 1 : 0;
        return valeurs;
    }

    @Override
    public void translate(PointPouce delta) {

    }

    @Override
    public void snapToGrid(PointPouce pointGrille){

    }

    @Override
    public String[] getNomsAttributs() {
        return parent.getListeComposantes().get(0).getNomsAttributs();
    }

    private LinkedList<PointPouce> listePointsModeBezier() {
        profilBezier.updatePointsControles();
        LinkedList<PointPouce> pointsMur = parent.getListeComposantes().get(0).getPolygone().getListePoints();
        LinkedList<PointPouce> pointsBezier = profilBezier.getPolygone().getListePoints();
        LinkedList<PointPouce> pointsModeBezier = new LinkedList<>();

        // On veut que tous nos points soient à l'intérieur du Mur Brute, on coupe si un point
        // dépasse les valeurs maximales, pour le bas on sait que les points sont toujours
        // sur les coins du murBrute
        Pouce xMax = pointsMur.get(0).getX();
        Pouce yMin = pointsMur.get(0).getY();
        Pouce xMin = pointsMur.get(1).getX();
        PointPouce point;

        for(int i = 0; i < pointsBezier.size(); i++){
            point = pointsBezier.get(i);
            if (point.getY().st(yMin)){
                point.setY(yMin);
            }
            if (point.getX().st(xMin)){
                point.setX(xMin);
            }
            if (point.getX().gt(xMax)){
                point.setX(xMax);
            }
            pointsModeBezier.add(point);
        }

        return pointsModeBezier;
    }


    private LinkedList<PointPouce> listePointsModeEllipse(){

        List<PointPouce> pointsMur = parent.getListeComposantes().get(0).getPolygone().getListePoints();
        
        //cadran en haut à droite de l'éllipse0
        List<PointPouce> listeEllipse0 = new LinkedList<> (profilEllipses[0].getPolygone().getListePoints().
                subList(0, Math.round(Forme.getNombrePoint()/4)));

        //cadran en haut à gauche de l'éllipse1
        List<PointPouce> listeEllipse1 = new LinkedList<> (profilEllipses[1].getPolygone().getListePoints().
                subList( Math.round(Forme.getNombrePoint()/4),  Math.round(Forme.getNombrePoint()/2)));

        //cadran en bas à gauche de l'éllipse2
        List<PointPouce> listeEllipse2 = new LinkedList<> (profilEllipses[2].getPolygone().getListePoints().
                subList(  Math.round(Forme.getNombrePoint()/2),  Math.round(Forme.getNombrePoint()*3/4)));

        //cadran en bas à droite de l'éllipse3
        List<PointPouce> listeEllipse3 = new LinkedList<> (profilEllipses[3].getPolygone().getListePoints().
                subList(  Math.round(Forme.getNombrePoint()*3/4),  Math.round(Forme.getNombrePoint())));

        LinkedList<PointPouce> listeRetour = new LinkedList<>();

        //ellipse 0
        boolean intersectionX = false;
        boolean intersectionY = false;
        boolean aucunPointAjoute = true;
        int indiceDernierPoint = 0;
        for(PointPouce point : listeEllipse0){
            if(point.getX().gte(pointsMur.get(0).getX())){
                intersectionX = true;
            }
            if(point.getY().ste(pointsMur.get(0).getY())){
                intersectionY = true;
            }
        }
        if(intersectionX && intersectionY){
            for(int i = 0; i < listeEllipse0.size(); i++){
                if(listeEllipse0.get(i).getX().ste(pointsMur.get(0).getX()) &&
                        listeEllipse0.get(i).getY().gte(pointsMur.get(0).getY())) {
                    if(aucunPointAjoute){
                        //ajouter un point sur la ligne du mur brute
                        listeRetour.add(new PointPouce(pointsMur.get(0).getX(), listeEllipse0.get(i).getY()));
                    }
                    listeRetour.add(listeEllipse0.get(i));
                    aucunPointAjoute = false;
                    indiceDernierPoint = i;
                }
            }
        }
        if (aucunPointAjoute) {
            listeRetour.add(pointsMur.get(0));
        }else{
            //ajouter un point sur la ligne du mur brute
            listeRetour.add(new PointPouce(listeEllipse0.get(indiceDernierPoint).getX(), pointsMur.get(0).getY()));
        }

        //ellipse 1
        intersectionX = false;
        intersectionY = false;
        aucunPointAjoute = true;
        indiceDernierPoint = 0;
        for(PointPouce point : listeEllipse1){
            if(point.getX().ste(pointsMur.get(1).getX())){
                intersectionX = true;
            }
            if(point.getY().ste(pointsMur.get(1).getY())){
                intersectionY = true;
            }
        }
        if(intersectionX && intersectionY){
            for(int i = 0; i < listeEllipse1.size(); i++){
                if(listeEllipse1.get(i).getX().gte(pointsMur.get(1).getX()) &&
                        listeEllipse1.get(i).getY().gte(pointsMur.get(1).getY())) {
                    if(aucunPointAjoute){
                        //ajouter un point sur la ligne du mur brute
                        listeRetour.add(new PointPouce(listeEllipse1.get(i).getX(), pointsMur.get(1).getY()));
                    }
                    listeRetour.add(listeEllipse1.get(i));
                    aucunPointAjoute = false;
                    indiceDernierPoint = i;
                }
            }
        }
        if (aucunPointAjoute) {
            listeRetour.add(pointsMur.get(1));
        }else{
            //ajouter un point sur la ligne du mur brute
            listeRetour.add(new PointPouce(pointsMur.get(1).getX(), listeEllipse1.get(indiceDernierPoint).getY()));
        }

        //ellipse 2
        intersectionX = false;
        intersectionY = false;
        aucunPointAjoute = true;
        indiceDernierPoint = 0;
        for(PointPouce point : listeEllipse2){
            if(point.getX().ste(pointsMur.get(2).getX())){
                intersectionX = true;
            }
            if(point.getY().gte(pointsMur.get(2).getY())){
                intersectionY = true;
            }
        }
        if(intersectionX && intersectionY){
            for(int i = 0; i < listeEllipse2.size(); i++){
                if(listeEllipse2.get(i).getX().gte(pointsMur.get(2).getX()) &&
                        listeEllipse2.get(i).getY().ste(pointsMur.get(2).getY())) {
                    if(aucunPointAjoute){
                        //ajouter un point sur la ligne du mur brute
                        listeRetour.add(new PointPouce(pointsMur.get(2).getX(), listeEllipse2.get(i).getY()));
                    }
                    listeRetour.add(listeEllipse2.get(i));
                    aucunPointAjoute = false;
                    indiceDernierPoint = i;
                }
            }
        }
        if (aucunPointAjoute) {
            listeRetour.add(pointsMur.get(2));
        }else{
            //ajouter un point sur la ligne du mur brute
            listeRetour.add(new PointPouce(listeEllipse2.get(indiceDernierPoint).getX(), pointsMur.get(2).getY()));
        }

        //ellipse 3
        intersectionX = false;
        intersectionY = false;
        aucunPointAjoute = true;
        indiceDernierPoint = 0;
        for(PointPouce point : listeEllipse3){
            if(point.getX().gte(pointsMur.get(3).getX())){
                intersectionX = true;
            }
            if(point.getY().gte(pointsMur.get(3).getY())){
                intersectionY = true;
            }
        }
        if(intersectionX && intersectionY){
            for(int i = 0; i < listeEllipse3.size(); i++){
                if(listeEllipse3.get(i).getX().ste(pointsMur.get(3).getX()) &&
                        listeEllipse3.get(i).getY().ste(pointsMur.get(3).getY())) {
                    if(aucunPointAjoute){
                        //ajouter un point sur la ligne du mur brute
                        listeRetour.add(new PointPouce(listeEllipse3.get(i).getX(), pointsMur.get(3).getY()));
                    }
                    listeRetour.add(listeEllipse3.get(i));
                    aucunPointAjoute = false;
                    indiceDernierPoint = i;
                }
            }
        }
        if (aucunPointAjoute) {
            listeRetour.add(pointsMur.get(3));
        }else{
            //ajouter un point sur la ligne du mur brute
            listeRetour.add(new PointPouce(pointsMur.get(3).getX(), listeEllipse3.get(indiceDernierPoint).getY()));
        }

        return listeRetour;
    }

    @Override
    public boolean[] getModes(){
        return new boolean[]{mode, modeContreplaque};
    }

    public boolean getModeContreplaque() {
        return modeContreplaque;
    }

    public void setModeContreplaque(String modeContreplaque) {
        switch(modeContreplaque){
            case "Profil complet":
                this.modeContreplaque = true;
                break;

            case "Contreplaqué intérieur":
                this.modeContreplaque = true;
                break;

            case "Contreplaqué extérieur":
                this.modeContreplaque = false;
                break;
        }

    }

    public enum ModeContreplaque {
            COMPLET {
                public String toString(){
                    return "Profil complet";
                }
            },
            INTERIEUR {
                public String toString(){
                    return "Contreplaqué intérieur";
                }
            },
            EXTERIEUR {
                public String toString(){
                    return "Contreplaqué extérieur";
                }
            }
    }


}
