package fr.univtln.aguard074;

import fr.univtln.group_aha.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.List;

import static fr.univtln.group_aha.DAO.*;

// Modele sert de Factory, c'est-à-dire qu'il permet de créer toutes les personnes à partir de cette classe
public class Modele extends Observable implements Imodele{
    private ArrayList<Personne> listPersonnes= new ArrayList<Personne>();
    //private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    // la variable static est temporaire (ou pas)
    private static EtudiantDAO etudiantDAO;

    public Modele() {
        etudiantDAO = new EtudiantDAO();
    }

    @Override
    public void creerEtudiant(String nom, String prenom, int age, Parcours parcours) {

        List attribut = new ArrayList();
        attribut.add(nom);
        attribut.add(prenom);
        attribut.add(age);
        attribut.add(parcours);

        // Le hashcode est une solution temporaire pour créer un identifinat
        // Le constructeur parcours
        Etudiant etudiant = new Etudiant(attribut.hashCode(), nom, prenom, parcours);

        etudiantDAO.create(etudiant);

        setChanged();
        notifyObservers(etudiant);

    }

    @Override
    public void creerEnseignant(String nom, String prenom, int age, Departement departement) {
        List attribut = new ArrayList();
        attribut.add(nom);
        attribut.add(prenom);
        attribut.add(age);
        attribut.add(departement);

        Enseignant enseignant = new Enseignant(attribut.hashCode(), nom, prenom, departement);

        // Prévision du codage de professeurDAO (plus tard)
        setChanged();
        notifyObservers(enseignant);
    }

    @Override
    public Etudiant trouverEtudiant(int id) {
        return etudiantDAO.find(id);
    }

    @Override
    public void afficherPersonne(Personne p) {
        System.out.println(p);
    }

    /*public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }*/


}

