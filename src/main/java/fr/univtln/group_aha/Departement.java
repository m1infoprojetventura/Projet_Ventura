package fr.univtln.group_aha;


public class Departement {
    private String nom;
    private Enseignant responsable;

    /**
     *
     * @param nom
     *    Nom du département
     * @param responsable
     *  Il s'agit de l'identifiant du professeur responsble
     */
    public Departement(String nom, Enseignant responsable) {
        this.nom = nom;
        this.responsable = responsable;
    }

    public Departement() {
        this.nom = "";
        this.responsable = new Enseignant();
    }

    public String getNom() {
        return nom;
    }

    public Enseignant getResponsable() {
        return responsable;
    }
}
