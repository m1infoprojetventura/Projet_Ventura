package fr.univtln.group_aha;

import java.util.Date;
import java.util.List;

public class Administrateur extends Personne {
    // Temporaire à débattre si on crée une classe Parcours ou un String Parcours
    private Formation formation;

    /**
     * @see Personne#Personne()
     */ public Administrateur() {
        super();
        formation = new Formation();
    }

    /**
     * @see Personne#Personne(int, String, String, Date)
     */
    public Administrateur(String nom, String prenom, Date date) {
        super(nom, prenom, date);
    }

    public Administrateur(int id, String nom, String prenom, Date date) {
        super(id, nom, prenom, date);
    }
}
