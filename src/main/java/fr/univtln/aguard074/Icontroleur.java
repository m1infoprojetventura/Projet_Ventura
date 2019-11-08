package fr.univtln.aguard074;

import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Parcours;

public interface Icontroleur {

    void creerEtudiant(String nom, String prenom, int age, Parcours parcours);
    void creerEnseignant(String nom, String prenom, int age, Departement departement);
    void creerPersonne(String nom, String prenom, int age, Personne.Statut statut);

    public Etudiant afficherEtudiant(int id);
    void afficherPersonne(Personne p);


}
