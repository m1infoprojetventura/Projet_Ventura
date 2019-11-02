package fr.univtln.aguard074;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Modele extends Observable implements Imodele{
    private ArrayList<Personne> listPersonnes= new ArrayList<Personne>();
    //private ArrayList<Observer> listObserver = new ArrayList<Observer>();


    @Override
    public void creerPersonne(String nom, String prenom, int age, Personne.Statut statut) {
       Personne p = new Personne(nom,prenom,age,statut);
       this.listPersonnes.add(p);
       setChanged();
       notifyObservers(p);

    }

    @Override
    public void afficherPersonne(Personne p) {
        System.out.println(p);


    }

    /*public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }*/


}

