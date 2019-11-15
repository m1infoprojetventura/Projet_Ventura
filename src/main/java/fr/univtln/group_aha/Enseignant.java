package fr.univtln.group_aha;

import java.util.Date;
import java.util.List;

public class Enseignant extends Personne {
    private Departement departement;

    public Enseignant() {
        super();
    }

    public Enseignant(String nom, String prenom, Date date, Departement departement) {
        super(nom, prenom, date);
        this.departement = departement;

    }

    public Enseignant(int id, String nom, String prenom, Date date, Departement departement) {
        super(id, nom, prenom, date);
        this.departement = departement;
    }

    public Departement getDepartement() {
        return departement;
    }

    public List getAttributs() {
        List resultat = super.getAttributs();
        resultat.add(this.departement);

        return resultat;
    }
}