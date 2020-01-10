package fr.univtln.aguard074.FenetreAdmin;


import fr.univtln.group_aha.*;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class Modele extends Observable implements Imodele {

    // Il faut que le modèle puisse accéder à la Vue comme sur le TP de Bibliotheque en ayant en attribut
    // des modèles des Componant (exemple modèle de JTextField: PlainDocument, ou Combobox: ComboboxModel)
    // la variable static est temporaire (ou pas)
    private  EtudiantDAO etudiantDAO;
    private  EnseignantDAO enseignantDAO;
    private  FormationDAO formationDAO;
    private  DepartementDAO departementDAO;
    private  SalleDAO salleDAO;
    final List<Etudiant> etudiants = new ArrayList<>() ;
    final List<Enseignant> enseignants = new ArrayList<>();
    final List<Salle> salles = new ArrayList<>();
    private Connection connect;

    public Modele(Connection connect) {
        super();
        this.connect = connect;

        etudiantDAO = new EtudiantDAO(this.connect);
        enseignantDAO = new EnseignantDAO(this.connect);
        formationDAO = new FormationDAO(this.connect);
        departementDAO = new DepartementDAO(this.connect);
        salleDAO = new SalleDAO(this.connect);

        for(Etudiant etudiant: etudiantDAO.getData())
            etudiants.add(etudiant);

        for(Enseignant enseignant: enseignantDAO.getData())
            enseignants.add(enseignant);
        for(Salle salle: salleDAO.getData())
            salles.add(salle);

    }
    @Override
    public void creerEtudiant(String nom, String prenom, Date date, Formation formation) {
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
        Etudiant etudiant1 = etudiants.get(i);
        etudiant1.setNom(etudiant.getNom());
        etudiant1.setPrenom(etudiant.getPrenom());
        etudiant1.setDate_naissance(etudiant.getDate_naissance());
        etudiant1.setFormation(etudiant.getFormation());
        etudiant1.generationMdp();
        etudiantDAO.update(etudiant1);
        setChanged();
        notifyObservers();
    }

    @Override
    public void modifierEnseignant(Enseignant enseignant) {
        int i = enseignants.indexOf(enseignant);
        Enseignant enseignant1 = enseignants.get(i);
        // Au moins ça a le mérite d'etre clair, on change les anciennes sonnées par les nouvelles.
        enseignant1.setNom(enseignant.getNom());
        enseignant1.setPrenom(enseignant.getPrenom());
        enseignant1.setDate_naissance(enseignant.getDate_naissance());
        enseignant1.setDepartement(enseignant.getDepartement());
        enseignant1.generationMdp();
        enseignantDAO.update(enseignant1);

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

    @Override
    public void modifierSalle(Salle salle) {
        int i = salles.indexOf(salle);
        System.out.println(salles);
        System.out.println(salle);
        salles.set(i, salle);
        salleDAO.update(salle);
        setChanged();
        notifyObservers();

    }


    // Temporaire le mieus serait de faire des étudiants modèles et des enseignants modèle

    /*public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }*/
}
