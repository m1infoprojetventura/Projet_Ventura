package fr.univtln.aguard074;

import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Parcours;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public interface Imodele  {

    public void creerEtudiant(String nom, String prenom, int age, Parcours parcours);
    public void creerEnseignant(String nom, String prenom, int age, Departement departement);

    public Etudiant trouverEtudiant(int id);
    public void afficherPersonne(Personne p);




}
