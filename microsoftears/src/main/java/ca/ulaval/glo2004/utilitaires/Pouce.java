package ca.ulaval.glo2004.utilitaires;

import ca.ulaval.glo2004.domain.RoulotteController;

public class Pouce
{
    private int pouces;
    private int numerateur;
    private int denominateur;
    private int milimetres;
    private final double MM_PAR_POUCE = 25.4;
    private static final int PRECISION_POUCE = 64;

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
            this.pouces = pouces;
            this.numerateur = numerateur;
            this.denominateur = 1;
            this.milimetres = (int) ((int) MM_PAR_POUCE * toDouble());
            simplifier();
        }
        else {
            this.pouces = pouces;
            this.numerateur = numerateur;
            this.denominateur = denominateur;
            this.milimetres = (int) ((int) MM_PAR_POUCE * toDouble());
            simplifier();
        }
    }

    /**
     Ce constructeur acceptes une mesure en pouces.
     @param mesurePouce double représentant la mesure en pouces (")
     */

    public Pouce(double mesurePouce)
    {
        // on convertit la mesure en milimètres
        this.milimetres = (int) (mesurePouce * MM_PAR_POUCE);

        // on convertit la mesure de double vers entier + fraction
        int[] poucesConvertis = convertiPouceDecimalEnFraction(mesurePouce);
        this.pouces = poucesConvertis[0];
        this.numerateur = poucesConvertis[1];
        this.denominateur = poucesConvertis[2];
        simplifier();

        }
        /** Ce constructeur acceptes une mesure en milimetres.
            @param mesureMM (int) représentant la mesure en milimetres
        */

        public Pouce(int mesureMM){

        this.milimetres = mesureMM;

        // on convertit la mesure en pouces
        double mesurePouce = mesureMM / MM_PAR_POUCE;

        // on convertit la mesure double vers entier + fraction
        int[] poucesConvertis = convertiPouceDecimalEnFraction(mesurePouce);
        this.pouces = poucesConvertis[0];
        this.numerateur = poucesConvertis[1];
        this.denominateur = poucesConvertis[2];
        simplifier();
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

    public int getMilimetres() {
        return milimetres;
    }

    public void setMilimetres(int milimetres) {
        this.milimetres = milimetres;
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
        return this.getNumerateur() != 0 ? getPouces() + " " + getNumerateur() + "/" +  getDenominateur() + "\"" : getPouces() + "\"";
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

    public Pouce diviser(double mesure){
        double num = ((double)numerateur/denominateur + pouces) * PRECISION_POUCE/mesure;
        int entier = (int) (num / PRECISION_POUCE);
        num -= PRECISION_POUCE*entier;
        int[] fraction = this.simplifierFraction((int)Math.round(num),PRECISION_POUCE);
        return new Pouce(entier, fraction[0], fraction[1]);
    }

    //todo
    public double diviser(Pouce pouce){
        return 0;
    }


    public Pouce multiplier(double mesure){
            double num = ((double)numerateur/denominateur + pouces) * PRECISION_POUCE * mesure;
            int entier = (int) (num / PRECISION_POUCE);
            num -= entier * PRECISION_POUCE;
            int[] fraction = this.simplifierFraction((int) Math.round(num), PRECISION_POUCE);
        return new Pouce(entier, fraction[0], fraction[1]);
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


    public boolean gt(Pouce mesure){
        return this.gt(mesure.toDouble());
    }

    public  boolean gte(Pouce mesure){
        return this.gte(mesure.toDouble());
    }

    public  boolean st(Pouce mesure){
        return this.st(mesure.toDouble());
    }


    public  boolean ste(Pouce mesure){
        return this.ste(mesure.toDouble());
    }

    /**
     *  Implémentation de l'opérateur > (plus grand que)
     * @param mesure la mesure en impériale à comparer
     * @return (boolean) true si la mesure est plus grande, false sinon
     */
    public boolean gt(double mesure){
        boolean resultat = false;

        if (this.toDouble() > mesure) {
            resultat = true;
        }
        return resultat;
    }
    /**
     *  Implémentation de l'opérateur >= (plus grand ou égal)
     * @param mesure la mesure en impériale à comparer
     * @return (boolean) true si la mesure est plus grande ou égale, false sinon
     */
    public  boolean gte(double mesure){
        boolean resultat = false;

        if (this.toDouble() >= mesure) {
            resultat = true;
        }
        return resultat;
    }
    /**
     *  Implémentation de l'opérateur < (plus petit que)
     * @param mesure la mesure en impériale à comparer
     * @return (boolean) true si la mesure est plus petite, false sinon
     */
    public  boolean st(double mesure){
        boolean resultat = false;

        if (this.toDouble() < mesure) {
            resultat = true;
        }
        return resultat;
    }

    /**
     *  Implémentation de l'opérateur <= (plus petit ou égal)
     * @param mesure la mesure en impériale à comparer
     * @return (boolean) true si la mesure est plus petite ou égale, false sinon
     */
    public  boolean ste(double mesure){
        boolean resultat = false;

        if (this.toDouble() <= mesure) {
            resultat = true;
        }
        return resultat;
    }

    /**
     Constructeur copie
     @return (Pouce) une copie de la mesure.
     */

    public Pouce copy()
    {
        // Fabrique un nouvel objet Pouce et initialise avec les mêmes champs que l'objet qui l'appelle

        // Retourne la référence au nouvel objet
        return new Pouce(pouces, numerateur, denominateur);
    }

    public double toDouble(){
        double num = getNumerateur();
        double denom = getDenominateur();
        double pouces = getPouces();
        double mesureEnDouble = pouces + (num/denom);

        return mesureEnDouble;
    }

    /** Calcul du plus grand dénominateur commun (pgdc)
     * @param num un entier représentant le numérateur
     * @param denom un entier représentant le dénominateur
     * @return le plus grand dénominateur commun de a et b */

    public static int plusGrandDenominateurCommun(int num, int denom) {
        return (denom == 0 ? num : plusGrandDenominateurCommun(denom, num % denom));
    }

    private void simplifier(){
        int pgdc = plusGrandDenominateurCommun(numerateur, denominateur);
        this.setNumerateur(numerateur/pgdc);
        this.setDenominateur(denominateur/pgdc);
    }

    public static int[] simplifierFraction(int num, int denom){
        int pgdc = plusGrandDenominateurCommun(num, denom);
        int[] fraction = new int[2];
        fraction[0] = num/pgdc;
        fraction[1] = denom/pgdc;
        return fraction;
    }

    static private int[] convertiPouceDecimalEnFraction(double mesure){

        int entier = (int) Math.floor(mesure);
        double reste = mesure - entier;
        double nbDecimales = String.valueOf(reste).length()-2;
        double denom = Math.pow(10, nbDecimales);
        double num = Math.pow(10, nbDecimales) * reste;

        // on arrondit au 1/64" près
        int numArrondi = (int) Math.round((num * PRECISION_POUCE) / denom);

        return new int[]{entier, numArrondi, PRECISION_POUCE};
    }

    public int toPixel() {
        return (int) (this.toDouble() * RoulotteController.pixelsToInchesRatio);
    }
}
