package fr.univtln.group_aha;


public class Departement {
    private String nom;
    private int id;

    /**
     *
     * @param nom
     *    Nom du département
     * @param id
     *  Il s'agit de l'identifiant du professeur responsble
     */
    public Departement(String nom, int id) {
        this.nom = nom;
        this.id = id;
    }

    public Departement() {
        this.nom = "";
        this.id = 0;
    }

    public String getNom() {
        return nom;
    }

    public Enseignant getResponsable() {
        EnseignantDAO enseignantDAO = new EnseignantDAO();
        return enseignantDAO.find(id);
    }
}
