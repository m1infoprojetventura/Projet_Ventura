package fr.univtln.aguard074.FenetreEmploi;

import fr.univtln.group_aha.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

public class ModeleEmploi extends Observable {

    private List<Seance> listSeances = new ArrayList<>();
    private List<Salle> salles = new ArrayList<>();
    private List<Matiere> matieres = new ArrayList<>();
    final List<Enseignant> enseignants = new ArrayList<>();
    private static SeanceDAO seanceDAO = new SeanceDAO();
    private static SalleDAO salleDAO = new SalleDAO();
    private static MatiereDAO matiereDAO = new MatiereDAO();
    private static EnseignantDAO enseignantDAO = new EnseignantDAO();

    public ModeleEmploi(){
        for(Salle salle: salleDAO.getData())
            salles.add(salle);
        for(Matiere matiere: matiereDAO.getData())
            matieres.add(matiere);
        for(Enseignant enseignant: enseignantDAO.getData())
            enseignants.add(enseignant);
    }

    public void creerSeance(Calendar test1, Calendar test2, Calendar test3) {
        Seance seance = new Seance(test1, test2, test3);
        listSeances.add(seance);
    }

    public void creerEmploi() {
        for (Seance seance : listSeances) {
            seanceDAO.create(seance);
        }
    }

    public List<Salle> getSalles() {
        return salles;
    }

    public List<Matiere> getMatieres(){
        return matieres;
    }
    public List<Enseignant>getEnseignantsList(){return enseignants;}

    public void associerMatiereProf(Matiere matiere, Enseignant enseignant) {
        matiereDAO.associate(matiere,enseignant);

    }

     public List<Enseignant> getAssocTeachers(Matiere matiere) {
        return matiereDAO.getAssocTeachers(matiere);
    }
}
