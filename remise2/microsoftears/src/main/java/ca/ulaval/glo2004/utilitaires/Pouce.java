package ca.ulaval.glo2004.utilitaires;

public class Pouce
{
    private int pouces;    // The number of inches

    /**
     This constructor assigns 0 inches field.
     */

    public Pouce()
    {
        pouces = 0;
    }

    /**
     This constructor accepts two arguments which
     are assigned to the feet and inches fields.
     The simplify method is then called.
     @param i The value to assign to inches.
     */

    public Pouce(int i)
    {
        pouces = i;
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


}
