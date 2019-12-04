package fr.univtln.aguard074.FenetreEmploi;

import fr.univtln.group_aha.*;
import fr.univtln.group_aha.Matiere;
import fr.univtln.group_aha.MatiereDAO;
import fr.univtln.group_aha.Seance;
import fr.univtln.group_aha.SeanceDAO;

import javax.swing.*;
import java.util.*;

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

        for(int i = 0; i < semaines; i++) {
            emploiDuTemps[i] = new ArrayList<>();
        }

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

    public void creerSeance(int id, Salle salle, Enseignant enseignant, Matiere matiere, Calendar debutCours, Calendar finCours) {
        Seance seance = new Seance(id,salle, enseignant, matiere, debutCours, finCours);
        int x = debutCours.get(Calendar.WEEK_OF_YEAR);

        System.out.println(x);
        emploiDuTemps[x].add(seance);
        setChanged();
        notifyObservers();
    }



    public void modifierSeance(int idSeance, Salle salle, Enseignant enseignant, GregorianCalendar debutH, GregorianCalendar finH) {
        //System.out.println("et en fait"+ debutH.get(Calendar.DAY_OF_WEEK));
        int x = debutH.get(Calendar.WEEK_OF_YEAR);
        Seance seance = new Seance(idSeance);
        int idx = emploiDuTemps[x].indexOf(seance);
        emploiDuTemps[x].get(idx).setId(idSeance);
        Calendar ancien = emploiDuTemps[x].get(idx).getHdebut();
        //emploiDuTemps[x].get(idx).getHdebut().set(Calendar.DAY_OF_WEEK,ancien.get(Calendar.DAY_OF_WEEK));
        emploiDuTemps[x].get(idx).setSalle(salle);
        emploiDuTemps[x].get(idx).setEnseignant(enseignant);
        emploiDuTemps[x].get(idx).setHdebut(debutH);
        emploiDuTemps[x].get(idx).setHfin(finH);
        setChanged();
        notifyObservers();

    }

    public void supprimerSeance(int idSeance, int jourSemaine) {
        System.out.println(jourSemaine);
        Seance seance = new Seance(idSeance);
        int idx = emploiDuTemps[jourSemaine].indexOf(seance);
        //System.out.println(idx);
        emploiDuTemps[jourSemaine].remove(idx);
        setChanged();
        notifyObservers();

    }
}
