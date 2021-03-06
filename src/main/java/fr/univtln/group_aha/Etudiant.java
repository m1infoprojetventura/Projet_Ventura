package fr.univtln.group_aha;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Etudiant extends Personne {
    // Temporaire à débattre si on crée une classe Parcours ou un String Parcours
    private Formation formation;

    /**
     * @see Personne#Personne()
     */ public Etudiant() {
        super();
        formation = new Formation();
    }

    /**
     * @see Personne#Personne(int, String, String, Date)
     * @param formation
     *    Donne le parcours (son cursus) d'un etudiant
     */
    public Etudiant(String nom, String prenom, Date date, Formation formation) {
        super(nom, prenom, date);
        this.formation = formation;
    }

    public Etudiant(int id, String nom, String prenom, Date date, Formation formation) {
        super(id, nom, prenom, date);
        this.formation = formation;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public List getAttributs(){
        List resultat = super.getAttributs();
        resultat.add(this.formation);

        return resultat;
    }
}
