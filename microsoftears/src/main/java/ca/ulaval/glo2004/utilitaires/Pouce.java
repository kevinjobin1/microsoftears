package ca.ulaval.glo2004.utilitaires;

public class Pouce
{
    private int pouces;
    private int numerateur;
    private int denominateur;
    private double milimetres;
    private final double MM_PAR_POUCE = 25.4;

    /**
     Ce constructeur acceptes une mesure en pouce qui sont attribués aux champs pouces,
     numerateur et denominateur de la classe Mesure. Initialise a vrai le choix de l'affichage de la mesure.
     @param pouces La valeur entière du nombre de pouces
     @param numerateur Le numérateur de la fraction de pouce
     @param denominateur Le dénominateur de la fraction de pouce
     */

    public Pouce(int pouces, int numerateur, int denominateur)
    {
        if (denominateur == 0) {
            throw new IllegalArgumentException("Le dénominateur est 0.");
        }

        else if (numerateur == 0) {
            denominateur = 1;
        }

        else if (numerateur < 0 || denominateur < 0) {
            throw new IllegalArgumentException("La fraction est négative.");
        }

        else {

            this.pouces = pouces;
            this.numerateur = numerateur;
            this.denominateur = denominateur;
            this.milimetres = MM_PAR_POUCE * toDouble();
            simplifierFraction();
        }
    }

    /**
     Ce constructeur acceptes une mesure en milimetres ou en pouces.
     @param mesure double représentant la mesure (en pouces (") ou milimètre (mm))
     */
    // TODO: à tester pour savoir si ça fonctionne!

    public Pouce(double mesure, boolean estPouce)
    {
        // on convertit si la mesure est en milimètres

        if (estPouce){
            this.milimetres = mesure * MM_PAR_POUCE;
        }
        else {
            this.milimetres = mesure;
            mesure /= MM_PAR_POUCE;
        }

        // mesure est en pouce, on convertit de double vers entier + fraction
        int pouceEntier = (int) Math.floor(mesure);
        double reste = mesure - pouceEntier;
        int digitsDec = String.valueOf(reste).length() - 2;
        int denom = 1;
        for (int i = 0; i < digitsDec; i++) {
            reste *= 10;
            denom *= 10;
        }
        int num = (int) Math.round(reste);


        this.pouces = pouceEntier;
        this.numerateur = num;
        this.denominateur = denom;
        simplifierFraction();

        }
        /** Ce constructeur acceptes une mesure en milimetres ou en pouces.
            @param poucesEntier (int) représentant la mesure en pouces (") entières (sans fraction)
        */

        public Pouce(int poucesEntier){
        this.pouces = poucesEntier;
        this.numerateur = 0;
        this.denominateur = 1;
        }

    /**
     Constructeur copie
     @param copie l'objet Pouce à copier
     */

    public Pouce(Pouce copie)
    {
        this.pouces = copie.pouces;
        this.numerateur = copie.numerateur;
        this.denominateur = copie.denominateur;
        this.milimetres = copie.milimetres;
    }

    public void setPouces(int pouces)
    {
        this.pouces = pouces;
    }


    public int getPouces()
    {
        return pouces;
    }

    public int getNumerateur() {
        return numerateur;
    }

    public void setNumerateur(int numerateur) {
        this.numerateur = numerateur;
    }

    public int getDenominateur() {
        return denominateur;
    }

    public void setDenominateur(int denominateur) {
        this.denominateur = denominateur;
    }

    /**
     Méthode d'affichage toString
     @return str (String) un objet string qui représente la mesure en pouces
     */

    public String toString()
    {
        return getPouces() + "-" + getNumerateur() + "/" +  getDenominateur();
    }

    /**
     Cette méthode retourne un nouvel objet contenant
     la somme des deux mesures.
     @param mesure (Pouce) l'autre objet Pouce à additionner
     @return référence au nouvel objet calculé
     
        Exemple: 1-1/2 + 3-3/4
     // 1. On multiplie l'entier par son dénominateur pour obtenir son numérateur
     //              1-1/2 = 3/2    et      3-3/4 = 15/4
     // 2. On additionne chaque numérateur multiplié par le dénominateur de l'autre terme
     //              3 * 4 + 15 * 2 = 12 + 30 = 42
     // 3. On multiplie les dénominateurs
     //              2 * 4 = 8
     // 4. On obtient l'entier par division entière de la somme des numérateurs
     // par le produit des dénominateurs
     //              nb pouce entier = 42 // 8
     // 5. On obtient le reste fractionnaire en calculant la valeur du numérateur qui est égal au
     // modulo de la division par le nouveau dénominateur commun
     //             num = num % denom                           
     */

    public Pouce add(Pouce mesure)
    {
        
       int num1 = numerateur + (pouces * denominateur);
       int num2 = mesure.numerateur + (mesure.pouces * mesure.denominateur);
       int num = num1 * mesure.denominateur + num2 * denominateur;
       int denom = denominateur * mesure.denominateur;
       int poucesEntier = num / denom;
       num %= denom;

        return new Pouce(poucesEntier, num, denom);
    }
    /**
     Cette méthode retourne un nouvel objet contenant
     la différence (en valeur absolue) des deux mesures.
     @param mesure (Pouce) l'autre objet Pouce à soustraire
     @return référence au nouvel objet calculé
     */
    public Pouce diff(Pouce mesure){

        int num1 = numerateur + (pouces * denominateur);
        int num2 = mesure.numerateur + (mesure.pouces * mesure.denominateur);
        int num = Math.abs(num1 * mesure.denominateur - num2 * denominateur);
        int denom = denominateur * mesure.denominateur;
        int poucesEntier = num / denom;
        num %= denom;

        return new Pouce(poucesEntier, num, denom);
    }



    /**
     Cette méthode vérifie que deux mesures sont égales.
     @param mesure (Pouce) une mesure
     @return Retourne vrai si c'est le cas, faux sinon.
     */

    public boolean equals(Pouce mesure)
    {
        boolean resultat;

        if (mesure == null)
            resultat = false;
        else resultat = (pouces == mesure.pouces
                && numerateur == mesure.numerateur
                && denominateur == mesure.denominateur);
        return resultat;
    }

    /**
     The copy method makes a copy of the
     the calling object.
     @return A reference to the copy.
     */

    public Pouce copy()
    {
        // Fabrique un nouvel objet Pouce et initialise avec les mêmes champs que l'objet qui l'appelle

        // Retourne la référence au nouvel objet
        return new Pouce(pouces, numerateur, denominateur);
    }

    // TODO : à vérifier
    public double toDouble(){

        return (double) this.getPouces() + this.getNumerateur() / this.getDenominateur();
    }

    /** Calcul du plus grand dénominateur commun (pgdc)
     * @param num un entier représentant le numérateur
     * @param denom un entier représentant le dénominateur
     * @return le plus grand dénominateur commun de a et b */

    public static int plusGrandDenominateurCommun(int num, int denom) {
        return (denom == 0 ? num : plusGrandDenominateurCommun(denom, num % denom));
    }

    private void simplifierFraction(){
        int pgdc = plusGrandDenominateurCommun(numerateur, denominateur);
        this.setNumerateur(numerateur/pgdc);
        this.setDenominateur(denominateur/pgdc);
    }
}
