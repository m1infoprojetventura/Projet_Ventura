package fr.univtln.aguard074.FenetreAdmin;


import fr.univtln.group_aha.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class Modele extends Observable implements Imodele {
    private ArrayList<Personne> listPersonnes= new ArrayList<Personne>();
    //private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    // Il faut que le modèle puisse accéder à la Vue comme sur le TP de Bibliotheque en ayant en attribut
    // des modèles des Componant (exemple modèle de JTextField: PlainDocument, ou Combobox: ComboboxModel)
    // la variable static est temporaire (ou pas)
    private static EtudiantDAO etudiantDAO = new EtudiantDAO();
    private static EnseignantDAO enseignantDAO = new EnseignantDAO();
    private static FormationDAO formationDAO = new FormationDAO();
    private static DepartementDAO departementDAO = new DepartementDAO();
    private static SalleDAO salleDAO = new SalleDAO();
    final List<Etudiant> etudiants = new ArrayList<>() ;
    final List<Enseignant> enseignants = new ArrayList<>();
    final List<Salle> salles = new ArrayList<>();

    public Modele() {
        super();
        for(Etudiant etudiant: etudiantDAO.getData())
            etudiants.add(etudiant);

        for(Enseignant enseignant: enseignantDAO.getData())
            enseignants.add(enseignant);
        for(Salle salle: salleDAO.getData())
            salles.add(salle);

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
    public List<Salle>getSallesList(){return salles;}

    @Override
    public ArrayList<Enseignant> getEnseignants() {
        return enseignantDAO.getData();
    }

    @Override
    public void modifierEtudiant(Etudiant etudiant) {
        int i = etudiants.indexOf(etudiant);
        etudiants.set(i, etudiant);
        etudiantDAO.update(etudiant);
        setChanged();
        notifyObservers();
    }

    @Override
    public void modifierEnseignant(Enseignant enseignant) {
        int i = enseignants.indexOf(enseignant);
        enseignants.set(i, enseignant);
        enseignantDAO.update(enseignant);
        setChanged();
        notifyObservers();
    }
    @Override
    public void creerSalle(String nom, int capacite, List<Materiel.TypeMateriel> listMateriel){

        Salle salle = new Salle(nom,listMateriel,capacite);
        salleDAO.create(salle);
        salles.add(salle);
        setChanged();
        notifyObservers();

    }

    @Override
    public void suppprimerSalle(Salle salle) {
        salleDAO.delete(salle);
        salles.remove(salle);
        setChanged();
        notifyObservers(salle);

    }


    // Temporaire le mieus serait de faire des étudiants modèles et des enseignants modèle

    /*public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }*/


}

