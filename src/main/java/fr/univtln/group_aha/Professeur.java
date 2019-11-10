package fr.univtln.group_aha;

import java.util.Date;

public class Professeur extends Personne {
    public Professeur(int id, String nom, String prenom, Date dateNaissance)
    {
        super(id, nom, prenom,dateNaissance);
    }

    public enum Departement{
        INFORMATIQUE,
        DROIT,
        COMMUNICATION,
        SANTE,
        PHYSIQUE,
        MATHEMATIQUE;

    }
}
