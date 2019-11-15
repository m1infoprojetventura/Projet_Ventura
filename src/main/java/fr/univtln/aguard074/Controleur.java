package fr.univtln.aguard074;

import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Formation;

import java.util.ArrayList;
import java.util.Date;

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


}
