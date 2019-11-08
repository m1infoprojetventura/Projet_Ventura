package fr.univtln.group_aha;
// Il faudra mettre tout ça dans des packages spécifique du genre fr.univtln.group_aha.model

/**
 * Classe représentant une Personne du corps universitaire
 * @author Adrien
 * @author Anouar
 * @author Haribou
 */
public abstract class Personne {
    private int id;
    private String nom;
    private String prenom;

    public Personne() {
        id = 0;
        nom = "";
        prenom = "";
    }

    /**
     * Contructeur de la classe Personne
     * @param id
     *    Identifiant d'une personne
     * @param nom
     *    Nom d'une personne
     * @param prenom
     *    Prénom d'une personne
     */

    public Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String toString()
    {
        return this.nom +" "+ this.prenom + " " + this.id ;
    }
}
