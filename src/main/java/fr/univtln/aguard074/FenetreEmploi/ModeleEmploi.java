package fr.univtln.aguard074.FenetreEmploi;

import fr.univtln.group_aha.*;
import fr.univtln.group_aha.Matiere;
import fr.univtln.group_aha.MatiereDAO;
import fr.univtln.group_aha.Seance;
import fr.univtln.group_aha.SeanceDAO;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ModeleEmploi extends Observable {

    private List<Seance> listSeances = new ArrayList<>();
    private List<Salle> salles = new ArrayList<>();
    private List<Salle> sallesDispo = new ArrayList<>();
    private List<Salle> enseignantDispo = new ArrayList<>();
    private List<Matiere> matieres = new ArrayList<>();
    final List<Enseignant> enseignants = new ArrayList<>();
    private List<Formation> formations = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private List<Reservation> totalReservations = new ArrayList<>();
    private final int semaines = 53;
    private int anneeEnCours;
    private List<Integer> semainesSelectionnees;

    private MatiereDAO matiereDAO;
    private List<Seance>[] emploiDuTemps = new List[semaines];
    private static SeanceDAO seanceDAO;
    private static SalleDAO salleDAO;
    private static EnseignantDAO enseignantDAO;
    private static EtudiantDAO etudiantDAO;
    private static FormationDAO formationDAO;
    private static ReservationDAO reservationDAO;

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
        for(Reservation reservation: reservationDAO.getAllData())
            totalReservations.add(reservation);

        GregorianCalendar dateActuelle = new GregorianCalendar();
        if(dateActuelle.get(Calendar.MONTH) >= Calendar.SEPTEMBER) {
            anneeEnCours = dateActuelle.get(Calendar.YEAR);
        }

        else {
            anneeEnCours = dateActuelle.get(Calendar.YEAR) - 1;
        }

    }

    public ModeleEmploi(Connection connect) {
        emploiDuTemps = new List[semaines];
        matiereDAO = new MatiereDAO(connect);
        seanceDAO = new SeanceDAO(connect);
        salleDAO = new SalleDAO(connect);
        enseignantDAO = new EnseignantDAO(connect);
        etudiantDAO = new EtudiantDAO(connect);
        formationDAO = new FormationDAO(connect);
        reservationDAO= new ReservationDAO(connect);

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
        for(Reservation reservation: reservationDAO.getAllData())
            totalReservations.add(reservation);


        GregorianCalendar dateActuelle = new GregorianCalendar();
        if(dateActuelle.get(Calendar.MONTH) >= Calendar.SEPTEMBER) {
            anneeEnCours = dateActuelle.get(Calendar.YEAR);
        }

        else {
            anneeEnCours = dateActuelle.get(Calendar.YEAR) - 1;
        }

    }

    public void sendAuthentification(String personneAuthentifiee){
        reservationDAO.setPersonneAuthentifiee(personneAuthentifiee);
    }
    //temporaire
    public void setListSeances(){
        reservations.clear();
        for(Reservation reservation: reservationDAO.getData())
            reservations.add(reservation);

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

    /**
     *
     * @param calendar date dont on souhaite la semaine (shifté)
     *                 la semaine du 2 septembre est à 0
     * @return l'indice de la structure emploi du temps
     */
    public int getIndiceEmploi(Calendar calendar) {
        GregorianCalendar calendar1 = new GregorianCalendar();

        calendar1.set(Calendar.DAY_OF_MONTH, 2);
        calendar1.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar1.set(Calendar.YEAR, anneeEnCours);

        Date input = calendar.getTime();
        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Date input1 = calendar1.getTime();
        LocalDate date1 = input1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long x = ChronoUnit.WEEKS.between(date1, date);

        return (int) x;

    }
    public void creerSeance(int id, Salle salle, Enseignant enseignant, Matiere matiere, Calendar debutCours, Calendar finCours, Formation formation) {
        Seance seance = new Seance(id,salle, enseignant, matiere, debutCours, finCours, formation);
        GregorianCalendar calendar = new GregorianCalendar();

        int x = getIndiceEmploi(debutCours);

        emploiDuTemps[x].add(seance);

        setChanged();
        notifyObservers();
    }

    public void initEmploi(int id) {
        for(int i = 0; i < semaines; i++) {
            emploiDuTemps[i] = new ArrayList<>();}
        listSeances = seanceDAO.getSeanceFormation(id);

        for (Seance seance : listSeances) {
            int x = getIndiceEmploi(seance.getHdebut());
            emploiDuTemps[x].add(seance);
        }

        setChanged();
        notifyObservers();
    }


    public void modifierSeance(int idSeance, Salle salle, Enseignant enseignant, GregorianCalendar debutH, GregorianCalendar finH,Formation formation) {
        //System.out.println("et en fait"+ debutH.get(Calendar.DAY_OF_WEEK));
        int x = getIndiceEmploi(debutH);
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
        Seance seance = new Seance(idSalle,salle,enseignant,matiere,debutH,finH,formation);
        seanceDAO.update(seance);
        int semaine = getIndiceEmploi(seance.getHdebut());

        int idx = emploiDuTemps[semaine].indexOf(seance);
        emploiDuTemps[semaine].remove(idx);
        emploiDuTemps[semaine].add(seance);

        setChanged();
        notifyObservers();
    }

    // Nom de Methode un peu troll je l'avoue
    public void modifierSeanceBDDsansAffichage(int idSalle, Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation){
        Seance seance = new Seance(idSalle,salle,enseignant,matiere,debutH,finH,formation);
        seanceDAO.update(seance);

    }

    public void creerSeanceBDD(Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) {
        Seance seance = new Seance(salle,enseignant,matiere,debutH,finH,formation);
        seanceDAO.create(seance);
        int x = getIndiceEmploi(seance.getHdebut());
        emploiDuTemps[x].add(seance);
        setChanged();
        notifyObservers();
    }

    public void supprimerSeanceBDD(int idSalle, int semaine) {
        System.out.println("uuuuuuuu");
        Seance seance = new Seance(idSalle);
        seanceDAO.delete(seance);

        int idx = emploiDuTemps[semaine].indexOf(seance);
        //System.out.println(idx);
        emploiDuTemps[semaine].remove(idx);
        setChanged();
        notifyObservers();
    }

    public void changerSemaine() {
        setChanged();
        notifyObservers();
    }
    public String getTypeLogin(String login) {
        return enseignantDAO.getTypeLogin(login);
    }

    public Enseignant getEnseignantByLogin(String sessionPersonne) {
        return enseignantDAO.getEnseignantByLogin(sessionPersonne);
    }

    public Etudiant getEtudiantbyLogin(String sessionPersonne) {
        return etudiantDAO.getEtudiantByLogin(sessionPersonne);
    }

    public void creerReservation(int id_enseignant, int id_salle, Date date_reservation, int id_seance) {
        Reservation reservation = new Reservation(id_enseignant,id_salle,date_reservation, id_seance);
        reservationDAO.create(reservation);
        notifyObservers();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    public List<Reservation> getTotalReservations() {
        System.out.println("marche");
        return totalReservations;

    }


    public void setSemainesSelectionnees(List<Integer> semainesSelectionnees) {
        this.semainesSelectionnees = semainesSelectionnees;
        setChanged();
        notifyObservers();
    }

    public List<Integer> getSemainesSelectionnees() {
        return semainesSelectionnees;
    }

    public void initEmploiEnseignant(int id_enseignant) {
        for(int i = 0; i < semaines; i++) {
            emploiDuTemps[i] = new ArrayList<>();}
        listSeances = seanceDAO.getSeanceEnseignant(id_enseignant);
        for (Seance seance : listSeances) {
            int x = getIndiceEmploi(seance.getHdebut());
            emploiDuTemps[x].add(seance);

        }
        setChanged();
        notifyObservers();
    }


    public List<Salle> getSallesDispo(GregorianCalendar debutH, GregorianCalendar finH) {
        return seanceDAO.getSalleDispo(debutH,finH);
    }

    public List<Seance> getSeancesEnseignant(int id_enseignant) {

        return seanceDAO.getSeanceEnseignant(id_enseignant);
    }

    public List<Salle> getEnseignantDispo() {
        return enseignantDispo;
    }
    public void confirmerReservation(Reservation resv){
        reservationDAO.update(resv);
    }
    public void refuserReservation(Reservation resv){
        reservationDAO.refuseupdate(resv);
    }
    public Salle getSalleById(int id){
        return salleDAO.find(id);
    }
    public void createFakeSeance(Seance s){
        seanceDAO.createFakeSeance(s);
    }

    public Seance getSeanceById(int id_seance) {
        return seanceDAO.find(id_seance);
    }
}
