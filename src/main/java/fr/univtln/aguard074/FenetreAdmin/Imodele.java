package fr.univtln.aguard074.FenetreAdmin;

import fr.univtln.group_aha.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface Imodele  {

    public void creerEtudiant(String nom, String prenom, Date date, Formation formation);
    public void creerEnseignant(String nom, String prenom, Date date, Departement departement);

    public void afficherPersonne(Personne p);

    ArrayList<Formation> getFormations();
    ArrayList<Departement> getDepartements();

    void suppprimerEtudiant(Etudiant etudiant);
    void suppprimerEnseignant(Enseignant enseignant);

    ArrayList<Etudiant> getEtudiants();

    ArrayList<Enseignant> getEnseignants();

    void modifierEtudiant(Etudiant etudiant);
    void modifierEnseignant(Enseignant enseignant);

    void creerSalle(String nom, int capacite, List<Materiel.TypeMateriel> listMateriel);

    void suppprimerSalle(Salle salle);

    void modifierSalle(Salle salle);
}
