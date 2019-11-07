package fr.univtln.aguard074;

import fr.univtln.group_aha.Parcours;
import fr.univtln.group_aha.Personne;

public interface Icontroleur {

    public void creerEtudiant(int id, String nom, String prenom, Parcours parcours);
    public void creerEnseignant(int id, String nom, String prenom);

    //void afficherPersonne(Personne p);
    void suprimerPersonne(Personne p);


}
