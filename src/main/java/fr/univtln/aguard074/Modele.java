package fr.univtln.aguard074;

import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Parcours;
import fr.univtln.group_aha.Professeur;
import fr.univtln.group_aha.Personne;

import java.util.*;

public class Modele extends Observable implements Imodele{
    private ArrayList<Personne> listPersonnes= new ArrayList<Personne>();






    @Override
    public void creerEtudiant(int id, String nom, String prenom, Date dateNaissance,String intituleParcours) {
        Personne etudiant = new Etudiant(id,nom,prenom,dateNaissance,new Parcours(intituleParcours));
        this.listPersonnes.add(etudiant);
        setChanged();
        notifyObservers(etudiant);

    }

    @Override
    public void creerEnseignant(int id, String nom, String prenom,Date dateNaissance) {
        Personne enseignant = new Professeur(id,nom,prenom,dateNaissance);
        this.listPersonnes.add(enseignant);
        setChanged();
        notifyObservers(enseignant);

    }

    @Override
    public void afficherPersonne(Personne p) {

        System.out.println(p);


    }

    @Override
    public void modifierPersonne(Personne p) {

    }

    @Override
    public void supprimerPersonne(Personne p) {
        Iterator iterator = listPersonnes.iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals(p))
                listPersonnes.remove(p);
            System.out.println(iterator.next());
        }
        listPersonnes.remove(p);
        setChanged();
        notifyObservers(p);

    }

    public ArrayList<Personne> getListPersonnes() {
        return listPersonnes;
    }

/*public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }*/


}

