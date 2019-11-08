package fr.univtln.aguard074;

import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Formation;

import java.util.Date;

public interface Icontroleur {

    void creerEtudiant(String nom, String prenom, Date date, Formation formation);
    void creerEnseignant(String nom, String prenom, Date date, Departement departement);

    public Etudiant afficherEtudiant(int id);
    void afficherPersonne(Personne p);


}
