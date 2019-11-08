package fr.univtln.group_aha;

public class Enseignant extends Personne {
    private Departement departement;

    public Enseignant() {
        super();
        departement = new Departement();
    }

    public Enseignant(int id, String nom, String prenom, Departement departement) {
        super(id, nom, prenom);
        this.departement = departement;
    }


    public Departement getDepartement() {
        return departement;
    }
}
