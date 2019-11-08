package fr.univtln.group_aha;

import java.util.Date;

public class Enseignant extends Personne {
    private Departement departement;

    public Enseignant() {
        super();
        departement = new Departement();
    }

    public Enseignant(String nom, String prenom, Date date, Departement departement) {
        super(nom, prenom, date);
        this.departement = departement;
    }


    public Departement getDepartement() {
        return departement;
    }
}
