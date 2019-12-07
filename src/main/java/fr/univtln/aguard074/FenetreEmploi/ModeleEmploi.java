package fr.univtln.aguard074.FenetreEmploi;

import fr.univtln.group_aha.*;
import fr.univtln.group_aha.Matiere;
import fr.univtln.group_aha.MatiereDAO;
import fr.univtln.group_aha.Seance;
import fr.univtln.group_aha.SeanceDAO;

import java.util.*;

public class ModeleEmploi extends Observable {

    private List<Seance> listSeances = new ArrayList<>();
    private List<Salle> salles = new ArrayList<>();
    private List<Matiere> matieres = new ArrayList<>();
    final List<Enseignant> enseignants = new ArrayList<>();
    private List<Formation> formations = new ArrayList<>();
    private final int semaines = 53;
    private MatiereDAO matiereDAO = new MatiereDAO();
    private List<Seance>[] emploiDuTemps = new List[semaines];
    private static SeanceDAO seanceDAO = new SeanceDAO();
    private static SalleDAO salleDAO = new SalleDAO();
    private static EnseignantDAO enseignantDAO = new EnseignantDAO();
    private static FormationDAO formationDAO = new FormationDAO();

    public ModeleEmploi(){
        for(Salle salle: salleDAO.getData())
            salles.add(salle);
        for(Matiere matiere: matiereDAO.getData())
            matieres.add(matiere);
        for(Enseignant enseignant: enseignantDAO.getData())
            enseignants.add(enseignant);
        for(Formation formation: formationDAO.getData())
            formations.add(formation);
        for(int i = 0; i < semaines; i++) {
            emploiDuTemps[i] = new ArrayList<>();}


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

    public List<Formation> getFormations(){
        return formations;
    }
    public List<Enseignant>getEnseignantsList(){return enseignants;}

    public void associerMatiereProf(Matiere matiere, Enseignant enseignant) {
        matiereDAO.associate(matiere,enseignant);

    }

     public List<Enseignant> getAssocTeachers(Matiere matiere) {
        return matiereDAO.getAssocTeachers(matiere);
    }

    public void creerSeance(int id, Salle salle, Enseignant enseignant, Matiere matiere, Calendar debutCours, Calendar finCours, Formation formation) {
        Seance seance = new Seance(id,salle, enseignant, matiere, debutCours, finCours, formation);
        int x = debutCours.get(Calendar.WEEK_OF_YEAR);

        System.out.println(x);
        emploiDuTemps[x].add(seance);
        setChanged();
        notifyObservers();
    }

    public void initEmploi(int id) {
        for(int i = 0; i < semaines; i++) {
            emploiDuTemps[i] = new ArrayList<>();}
        listSeances = seanceDAO.getSeanceFormation(id);
        for (Seance seance : listSeances) {
            int x = seance.getHdebut().get(Calendar.WEEK_OF_YEAR);
            emploiDuTemps[x].add(seance);

        }
        setChanged();
        notifyObservers();
    }


    public void modifierSeance(int idSeance, Salle salle, Enseignant enseignant, GregorianCalendar debutH, GregorianCalendar finH,Formation formation) {
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
        emploiDuTemps[x].get(idx).setFormation(formation);
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

    public List<String> recupMatieres(Enseignant enseignant) {
        return enseignantDAO.getMatieres(enseignant);
    }

    public void supprimerEmploi(int id) {
        listSeances = seanceDAO.getSeanceFormation(id);
        for(Seance seance: listSeances)
            seanceDAO.delete(seance);

    }

    public boolean verifierAuthResponsable(String login, String password) {
        return enseignantDAO.verifierAuthResponsable(login,password);

    }

    public void modifierSeanceBDD(int idSalle, Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) {
        Seance seance = new Seance(salle,enseignant,matiere,debutH,finH,formation);
        seanceDAO.update(seance);
        
        setChanged();
        notifyObservers();
    }

    public void creerSeanceBDD(Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) {
        Seance seance = new Seance(salle,enseignant,matiere,debutH,finH,formation);
        seanceDAO.create(seance);
        int x = seance.getHdebut().get(Calendar.WEEK_OF_YEAR);
        emploiDuTemps[x].add(seance);
        setChanged();
        notifyObservers();
    }
}
