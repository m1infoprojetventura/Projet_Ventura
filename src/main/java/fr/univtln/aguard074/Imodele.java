package fr.univtln.aguard074;

import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Parcours;
import fr.univtln.group_aha.Professeur;
import fr.univtln.group_aha.Personne;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

 public interface Imodele   {



    //public void creerPersonne(int id, String nom, String Prenom);


    public void creerEtudiant(int id, String nom, String prenom, Parcours parcours);

    public void creerEnseignant(int id, String nom, String prenom);

    public void afficherPersonne(Personne p);

    public void modifierPersonne(Personne p);

    public void supprimerPersonne(Personne p);



 }
