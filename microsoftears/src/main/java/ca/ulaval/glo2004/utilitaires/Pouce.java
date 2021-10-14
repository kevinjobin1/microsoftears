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
     @return Un objet Mesure
     */

    public Pouce(int pouces, int numerateur, int denominateur)
    {
        this.pouces = pouces;
        this.numerateur = numerateur;
        this.denominateur = denominateur;
    }

    /**
     Ce constructeur acceptes une mesure en milimetres.
     @param milimetres The value to assign to inches.
     */

    //problème potentiel avec Pouce(double pouce)
    public Pouce(float milimetres)
    {
       this.milimetres = milimetres;
    }


    //à tester
    public Pouce(double pouce){
        int pouceEntier = (int) pouce;
        double reste = pouce - pouceEntier;
        int digitsDec = String.valueOf(reste).length() - 2;
        int denom = 1;
        for(int i = 0; i < digitsDec; i++){
            reste *= 10;
            denom *= 10;
        }
        int num = (int) Math.round(reste);
        this.pouces = pouceEntier;
        this.numerateur = num;
        this.denominateur = denom;
    }

    /**
     The following is a copy constructor. It accepts a
     reference to another FeetInches object. The feet
     and inches fields are set to the same values as
     those in the argument object.
     @param copie The object to copy.
     */

    public Pouce(Pouce copie)
    {
        pouces = copie.pouces;
    }

    /**
     The setInches method assigns a value to
     the inches field.
     @param i The value to assign to inches.
     */

    public void setPouces(int i)
    {
        pouces = i;
    }

    /**
     getInches method
     @return The value in the inches field.
     */

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
     toString method
     @return a reference to a String stating
     the feet and inches.
     */

    public String toString()
    {
        String str = pouces + " pouces";
        return str;
    }

    /**
     The add method returns a FeetInches object
     that holds the sum of this object and another
     FeetInches object.
     @param mesure The other FeetInches object.
     @return A reference to a FeetInches object.
     */

    public Pouce add(Pouce mesure)
    {
        int total;

        total = pouces + mesure.pouces;
        return new Pouce(total);
    }

    /**
     The equals method compares this object to the
     argument object. If both have the same values,
     the method returns true.
     @return true if the objects are equal, false
     otherwise.
     */

    public boolean equals(Pouce mesure)
    {
        boolean status;

        if (mesure == null)
            status = false;
        else if (
                pouces == mesure.pouces)
            status = true;
        else
            status = false;
        return status;
    }

    /**
     The copy method makes a copy of the
     the calling object.
     @return A reference to the copy.
     */

    public Pouce copy()
    {
        // Make a new FeetInches object and
        // initialize it with the same data
        // as the calling object.
        Pouce newObject =
                new Pouce(pouces);

        // Return a reference to the new object.
        return newObject;
    }

    public double toDouble(){
        return this.getPouces()+this.getNumerateur()/this.getDenominateur();
    }
}
