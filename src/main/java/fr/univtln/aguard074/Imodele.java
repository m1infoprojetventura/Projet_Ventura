package fr.univtln.aguard074;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

 public interface Imodele   {



    public void creerPersonne(String nom, String Prenom, int age, Personne.Statut statut);

    public void afficherPersonne(Personne p);

    public void modifierPersonne(Personne p);



 }
