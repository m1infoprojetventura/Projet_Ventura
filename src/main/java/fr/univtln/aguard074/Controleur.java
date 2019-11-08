package fr.univtln.aguard074;

import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Parcours;

public class Controleur implements Icontroleur {
    //private VueGestionaire vueGestionaire;
    private Imodele modele;

    public Controleur(Imodele modele){
        this.modele = modele;
    }

    @Override
    public void creerEtudiant(String nom, String prenom, int age, Parcours parcours) {
        this.modele.creerEtudiant(nom, prenom, age, parcours);
    }

    @Override
    public void creerEnseignant(String nom, String prenom, int age, Departement departement) {
        this.modele.creerEnseignant(nom, prenom, age, departement);
    }

    @Override
    public void creerPersonne(String nom, String prenom, int age, Personne.Statut statut) {
        // this.modele.creerPersonne(nom,prenom,age,statut);

    }

    @Override
    public Etudiant afficherEtudiant(int id) {
        return null;
    }

    @Override
    public void afficherPersonne(Personne p) {
        this.modele.afficherPersonne(p);

    }

}
