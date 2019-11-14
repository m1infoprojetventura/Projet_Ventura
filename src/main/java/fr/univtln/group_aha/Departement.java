package fr.univtln.group_aha;


public class Departement {
    private int id;
    private String nom;
    private Enseignant responsable;

    /**
     *
     * @param nom
     *    Nom du département
     * @param responsable
     *  Il s'agit de l'identifiant du professeur responsble
     */
    public Departement(int id, String nom, Enseignant responsable) {
        this.id = id;
        this.nom = nom;
        this.responsable = responsable;
    }

    public Departement() {
        this.id = 0;
        this.nom = "";
        this.responsable = new Enseignant();
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    public Enseignant getResponsable() {
        return responsable;
    }

    public int getId() {
        return id;
    }
}
