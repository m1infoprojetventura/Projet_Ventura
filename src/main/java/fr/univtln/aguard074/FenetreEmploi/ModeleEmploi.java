package fr.univtln.aguard074.FenetreEmploi;

import fr.univtln.group_aha.*;
import fr.univtln.group_aha.Matiere;
import fr.univtln.group_aha.MatiereDAO;
import fr.univtln.group_aha.Seance;
import fr.univtln.group_aha.SeanceDAO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

public class ModeleEmploi extends Observable {

    private List<Seance> listSeances = new ArrayList<>();
    private List<Salle> salles = new ArrayList<>();
    private List<Matiere> matieres = new ArrayList<>();
    final List<Enseignant> enseignants = new ArrayList<>();
    private final int semaines = 53;
    private MatiereDAO matiereDAO = new MatiereDAO();
    private List<Seance>[] emploiDuTemps = new List[semaines];
    private static SeanceDAO seanceDAO = new SeanceDAO();
    private static SalleDAO salleDAO = new SalleDAO();
    private static EnseignantDAO enseignantDAO = new EnseignantDAO();

    public ModeleEmploi(){
        for(Salle salle: salleDAO.getData())
            salles.add(salle);
        for(Matiere matiere: matiereDAO.getData())
            matieres.add(matiere);
        for(Enseignant enseignant: enseignantDAO.getData())
            enseignants.add(enseignant);
    }

    public void creerEmploi() {
        for (List<Seance> listeSeances : emploiDuTemps) {
            for (Seance seance : listeSeances)
                seanceDAO.create(seance);
        }
    }

    public List<Seance>[] getEmploiDuTemps() {
        return emploiDuTemps;
    }

    public List<Matiere> getMatieres() {
        return matiereDAO.getData();
    }

    public List<Salle> getSalles() {
        return salles;
    }

    public List<Enseignant>getEnseignantsList(){return enseignants;}

    public void associerMatiereProf(Matiere matiere, Enseignant enseignant) {
        matiereDAO.associate(matiere,enseignant);

    }

     public List<Enseignant> getAssocTeachers(Matiere matiere) {
        return matiereDAO.getAssocTeachers(matiere);
    }

    public void creerSeance(Salle salle, Enseignant enseignant, Matiere matiere, Calendar debutCours, Calendar finCours) {
        Seance seance = new Seance(salle, enseignant, matiere, debutCours, finCours);
        int x = debutCours.getWeekYear();
        emploiDuTemps[x].add(seance);
        notifyObservers();
    }
}
