package fr.univtln.group_aha;

import java.util.Date;

public class Etudiant extends Personne {
    private Parcours parcours;

    /**
     * @see Personne#Personne()
     */
    public Etudiant() {
        super();
        parcours = new Parcours();
    }

    /**
     * @param parcours
     *    Donne le parcours (son cursus) d'un etudiant
     */
    public Etudiant(int id, String nom, String prenom, Date dateNaissance, Parcours parcours) {
        super(id, nom, prenom,dateNaissance);
        this.parcours = parcours;
    }

    public Parcours getParcours() {
        return parcours;
    }

    @Override
    public String toString() {
        return super.toString() +" " +parcours.getIntitule();
    }
}
