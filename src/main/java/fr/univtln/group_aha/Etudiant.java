package fr.univtln.group_aha;

public class Etudiant extends Personne {
    // Temporaire à débattre si on crée une classe Parcours ou un String Parcours
    private Parcours parcours;

    /**
     * @see Personne#Personne()
     */
    public Etudiant() {
        super();
        parcours = new Parcours();
    }

    /**
     * @see Personne#Personne(int, String, String)
     * @param parcours
     *    Donne le parcours (son cursus) d'un etudiant
     */
    public Etudiant(int id, String nom, String prenom, Parcours parcours) {
        super(id, nom, prenom);
        this.parcours = parcours;
    }

    public Parcours getParcours() {
        return parcours;
    }
}
