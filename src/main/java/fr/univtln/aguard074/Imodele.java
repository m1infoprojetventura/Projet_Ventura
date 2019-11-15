package fr.univtln.aguard074;

import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;
import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Etudiant;
import fr.univtln.group_aha.Formation;

import java.util.ArrayList;
import java.util.Date;

public interface Imodele  {

    public void creerEtudiant(String nom, String prenom, Date date, Formation formation);
    public void creerEnseignant(String nom, String prenom, Date date, Departement departement);

    boolean trouverEtudiant(int id);
    boolean trouverEnseignant(int id);

    public void afficherPersonne(Personne p);

    ArrayList<Formation> getFormations();

    ArrayList<Departement> getDepartements();

    void suppprimerEtudiant(Etudiant etudiant);

    void suppprimerEnseignant(Enseignant enseignant);

    ArrayList<Etudiant> getEtudiants();

    ArrayList<Enseignant> getEnseignants();
}
