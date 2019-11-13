package fr.univtln.aguard074;

import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Formation;

import java.util.ArrayList;
import java.util.Date;

public interface Imodele  {

    public void creerEtudiant(String nom, String prenom, Date date, Formation formation);
    public void creerEnseignant(String nom, String prenom, Date date, Departement departement);

    public Etudiant trouverEtudiant(int id);
    public void afficherPersonne(Personne p);

    ArrayList<Formation> getFormations();
}
