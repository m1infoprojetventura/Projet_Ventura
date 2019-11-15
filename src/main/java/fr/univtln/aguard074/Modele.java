package fr.univtln.aguard074;


import fr.univtln.group_aha.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class Modele extends Observable implements Imodele{
    private ArrayList<Personne> listPersonnes= new ArrayList<Personne>();
    //private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    // Il faut que le modèle puisse accéder à la Vue comme sur le TP de Bibliotheque en ayant en attribut
    // des modèles des Componant (exemple modèle de JTextField: PlainDocument, ou Combobox: ComboboxModel)
    // la variable static est temporaire (ou pas)
    private static EtudiantDAO etudiantDAO = new EtudiantDAO();
    private static EnseignantDAO enseignantDAO = new EnseignantDAO();
    private static FormationDAO formationDAO = new FormationDAO();
    private static DepartementDAO departementDAO = new DepartementDAO();
    final List<Etudiant> etudiants = new ArrayList<>() ;
    final List<Enseignant> enseignants = new ArrayList<>();

    public Modele() {
        super();
        for(Etudiant etudiant: etudiantDAO.getData())
            etudiants.add(etudiant);

        for(Enseignant enseignant: enseignantDAO.getData())
            enseignants.add(enseignant);
    }
    @Override
    public void creerEtudiant(String nom, String prenom, Date date, Formation formation) {

        // Le hashcode est une solution temporaire pour créer un identifinat
        // Le constructeur parcours
        Etudiant etudiant = new Etudiant(nom, prenom, date, formation);
        etudiantDAO.create(etudiant);

        etudiants.add(etudiant);
        setChanged();
        notifyObservers(etudiant);

    }

    @Override
    public void creerEnseignant(String nom, String prenom, Date date, Departement departement) {

        Enseignant enseignant = new Enseignant(nom, prenom, date, departement);

        enseignantDAO.create(enseignant);
        enseignants.add(enseignant);
        // Prévision du codage de professeurDAO (plus tard)
        setChanged();
        notifyObservers(enseignant);
    }

    @Override
    public void afficherPersonne(Personne p) {
        System.out.println(p);


    }

    @Override
    public ArrayList<Formation> getFormations() {
        return formationDAO.getData();
    }

    @Override
    public ArrayList<Departement> getDepartements() {
        return departementDAO.getData();
    }

    // Vaut mieux créer une classe abstraite Modele étendu par deux classes EtudiantModele et EnseignantModele
    @Override
    public boolean trouverEtudiant(int id) {
        return (etudiantDAO.find(id) != null);
    }

    @Override
    public boolean trouverEnseignant(int id) {
       return (enseignantDAO.find(id) != null) ;
    }


    @Override
    public void suppprimerEtudiant(Etudiant etudiant) {
        etudiantDAO.delete(etudiant);
        etudiants.remove(etudiant);
        setChanged();
        notifyObservers(etudiant);
    }

    @Override
    public void suppprimerEnseignant(Enseignant enseignant) {
        enseignantDAO.delete(enseignant);
        enseignants.remove(enseignant);
        setChanged();
        notifyObservers(enseignant);
    }

    @Override
    public ArrayList<Etudiant> getEtudiants() {
        return etudiantDAO.getData();
    }
    public List<Etudiant> getEtudiantsList() {
        return etudiants;
    }
    public List<Enseignant>getEnseignantsList(){return enseignants;}

    @Override
    public ArrayList<Enseignant> getEnseignants() {
        return enseignantDAO.getData();
    }


    // Temporaire le mieus serait de faire des étudiants modèles et des enseignants modèle

    /*public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }*/


}

