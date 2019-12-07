package fr.univtln.aguard074.FenetreAdmin;

import fr.univtln.group_aha.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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

    void creerSalle(String nom, int capacite, List<Materiel.TypeMateriel> listMateriel);

    void suprimerSalle(Salle salle);

    void modifierSalle(Salle salle);
}
