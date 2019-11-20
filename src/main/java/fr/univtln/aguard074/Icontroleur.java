package fr.univtln.aguard074;

import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Formation;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.ArrayList;

public interface Icontroleur {

    void creerEtudiant(String nom, String prenom, Date date, Formation formation);
    void creerEnseignant(String nom, String prenom, Date date, Departement departement);

    // J'ignore s'il faut une methode générique ou pas (c'est à dire un getData comme dans la DAO)
    ArrayList<Formation> getFormations();
    ArrayList<Departement> getDepartements();
    Etudiant afficherEtudiant(int id);
    void afficherPersonne(Personne p);

    ArrayList<Etudiant> getEtudiants();
    ArrayList<Enseignant> getEnseignants();

    void suprimerEtudiant(Etudiant etudiant);
    void suprimerEnseignant(Enseignant enseignant);

    void modifierEtudiant(Etudiant etudiant);
    void modifierEnseignant(Enseignant enseignant);
}
