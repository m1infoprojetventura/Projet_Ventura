package fr.univtln.aguard074.FenetreAdmin;

import fr.univtln.group_aha.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controleur implements Icontroleur {
    //private VueGestionaire vueGestionaire;
    private Imodele modele;

    public Controleur(Imodele modele){
        this.modele = modele;
    }

    @Override
    public void creerEtudiant(String nom, String prenom, Date date, Formation formation) {
        this.modele.creerEtudiant(nom, prenom, date, formation);
    }

    @Override
    public void creerEnseignant(String nom, String prenom, Date date, Departement departement) {
        this.modele.creerEnseignant(nom, prenom, date, departement);
    }

    @Override
    public ArrayList<Formation> getFormations() {
        return this.modele.getFormations();
    }

    @Override
    public ArrayList<Departement> getDepartements() {
        return this.modele.getDepartements();
    }

    @Override
    public Etudiant afficherEtudiant(int id) {
        return null;
    }


    @Override
    public void afficherPersonne(Personne p) {
        this.modele.afficherPersonne(p);
    }

    @Override
    public ArrayList<Etudiant> getEtudiants() {
        return modele.getEtudiants();
    }

    @Override
    public ArrayList<Enseignant> getEnseignants() {
        return modele.getEnseignants();
    }

    @Override
    public void suprimerEtudiant(Etudiant etudiant) {
        this.modele.suppprimerEtudiant(etudiant);
    }

    @Override
    public void suprimerEnseignant(Enseignant enseignant) {
        this.modele.suppprimerEnseignant(enseignant);
    }

    @Override
    public void modifierEtudiant(Etudiant etudiant) {
        this.modele.modifierEtudiant(etudiant);
    }

    @Override
    public void modifierEnseignant(Enseignant enseignant) {
        this.modele.modifierEnseignant(enseignant);
    }

    @Override
    public void creerSalle(String nom, int capacite, List<Materiel.TypeMateriel> listMateriel) {
        this.modele.creerSalle(nom, capacite, listMateriel);
    }

    @Override
    public void suprimerSalle(Salle salle) {
        this.modele.suppprimerSalle(salle);

    }

    @Override
    public void modifierSalle(Salle salle) {
        this.modele.modifierSalle(salle);

    }
}
