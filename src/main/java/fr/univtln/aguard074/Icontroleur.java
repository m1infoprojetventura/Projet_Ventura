package fr.univtln.aguard074;

import fr.univtln.group_aha.Personne;

import java.util.Date;

public interface Icontroleur {

    public void creerEtudiant(int id, String nom, String prenom, String intituleParcours, Date dateNaissance);
    public void creerEnseignant(int id, String nom, String prenom,Date dateNaissance);

    //void afficherPersonne(Personne p);
    void suprimerPersonne(Personne p);


}
