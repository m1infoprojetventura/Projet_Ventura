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
import java.util.List;

public class ModeleEmploi extends Observable {

    private Connection connect;
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
    private List<Contrainte>[] emploiDuTempsContrainte = new List[semaines];
    private static SeanceDAO seanceDAO;
    private static SalleDAO salleDAO;
    private static EnseignantDAO enseignantDAO;
    private static EtudiantDAO etudiantDAO;
    private static AdmnistrateurDAO admnistrateurDAO;
    private static FormationDAO formationDAO;
    private static ReservationDAO reservationDAO;
    private static ContrainteDAO contrainteDAO;

    public ModeleEmploi() {
        for (Salle salle : salleDAO.getData())
            salles.add(salle);
        for (Matiere matiere : matiereDAO.getData())
            matieres.add(matiere);
        for (Enseignant enseignant : enseignantDAO.getData())
            enseignants.add(enseignant);
        for (Formation formation : formationDAO.getData())
            formations.add(formation);

        for (int i = 0; i < semaines; i++) {
            emploiDuTemps[i] = new ArrayList<>();
        }

        for (int i = 0; i < semaines; i++) {
            emploiDuTempsContrainte[i] = new ArrayList<>();
        }

        for (Reservation reservation : reservationDAO.getAllData())
            totalReservations.add(reservation);

        GregorianCalendar dateActuelle = new GregorianCalendar();
        if (dateActuelle.get(Calendar.MONTH) >= Calendar.SEPTEMBER) {
            anneeEnCours = dateActuelle.get(Calendar.YEAR);
        } else {
            anneeEnCours = dateActuelle.get(Calendar.YEAR) - 1;
        }

        GregorianCalendar calendar1 = new GregorianCalendar();
        this.semainesSelectionnees = Arrays.asList(getIndiceEmploi(calendar1));
    }

    public ModeleEmploi(Connection connect) {
        emploiDuTemps = new List[semaines];
        this.connect = connect;
        matiereDAO = new MatiereDAO(connect);
        seanceDAO = new SeanceDAO(connect);
        salleDAO = new SalleDAO(connect);
        enseignantDAO = new EnseignantDAO(connect);
        etudiantDAO = new EtudiantDAO(connect);
        formationDAO = new FormationDAO(connect);
        admnistrateurDAO = new AdmnistrateurDAO(connect);
        reservationDAO = new ReservationDAO(connect);
        contrainteDAO = new ContrainteDAO(connect);

        for (Salle salle : salleDAO.getData())
            salles.add(salle);
        for (Matiere matiere : matiereDAO.getData())
            matieres.add(matiere);
        for (Enseignant enseignant : enseignantDAO.getData())
            enseignants.add(enseignant);
        for (Formation formation : formationDAO.getData())
            formations.add(formation);

        for (int i = 0; i < semaines; i++) {
            emploiDuTemps[i] = new ArrayList<>();
        }

        for (int i = 0; i < semaines; i++) {
            emploiDuTempsContrainte[i] = new ArrayList<>();
        }

        for (Reservation reservation : reservationDAO.getAllData())
            totalReservations.add(reservation);


        GregorianCalendar dateActuelle = new GregorianCalendar();
        if (dateActuelle.get(Calendar.MONTH) >= Calendar.SEPTEMBER) {
            anneeEnCours = dateActuelle.get(Calendar.YEAR);
        } else {
            anneeEnCours = dateActuelle.get(Calendar.YEAR) - 1;
        }

        GregorianCalendar calendar1 = new GregorianCalendar();
        this.semainesSelectionnees = new LinkedList<Integer>(Arrays.asList(getIndiceEmploi(calendar1)));
    }

    public void sendAuthentification(String personneAuthentifiee) {
        reservationDAO.setPersonneAuthentifiee(personneAuthentifiee);
    }

    //temporaire
    public void setListSeances() {
        reservations.clear();
        for (Reservation reservation : reservationDAO.getData())
            reservations.add(reservation);

    }

    public void creerEmploi() {
        for (List<Seance> listeSeances : emploiDuTemps) {
            for (Seance seance : listeSeances) {
                try {
                    seanceDAO.create(seance);
                } catch (EchecContrainteException e) {
                    e.printStackTrace();
                } catch (EchecSeanceFormationException e) {
                    e.printStackTrace();
                } catch (EchecSeanceEnseignantException e) {
                    e.printStackTrace();
                } catch (EchecHeureException e) {
                    e.printStackTrace();
                }
            }
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

    public List<Formation> getFormations() {
        return formations;
    }

    public List<Enseignant> getEnseignantsList() {
        return enseignants;
    }

    public void associerMatiereProf(Matiere matiere, Enseignant enseignant) {
        matiereDAO.associate(matiere, enseignant);

    }

    public List<Enseignant> getAssocTeachers(Matiere matiere) {
        return matiereDAO.getAssocTeachers(matiere);
    }

    /**
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
        Seance seance = new Seance(id, salle, enseignant, matiere, debutCours, finCours, formation);
        GregorianCalendar calendar = new GregorianCalendar();

        int x = getIndiceEmploi(debutCours);

        emploiDuTemps[x].add(seance);

        setChanged();
        notifyObservers();
    }

    public void initEmploi(int id) {
        for (int i = 0; i < semaines; i++) {
            emploiDuTemps[i] = new ArrayList<>();
        }

        listSeances = seanceDAO.getSeanceFormation(id);

        for (Seance seance : listSeances) {
            int x = getIndiceEmploi(seance.getHdebut());
            emploiDuTemps[x].add(seance);
        }

        setChanged();
        notifyObservers();
    }

    public void modifierSeance(int idSeance, Salle salle, Enseignant enseignant, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) {
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
        for (Seance seance : listSeances)
            seanceDAO.delete(seance);

    }

    public List<Contrainte>[] getEmploiDuTempsContrainte() {
        return emploiDuTempsContrainte;
    }

    public boolean verifierAuthResponsable(String login, String password) {
        return enseignantDAO.verifierAuthResponsable(login, password);

    }

    public void modifierSeanceBDD(int idSalle, Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) {
        Seance seance = new Seance(idSalle, salle, enseignant, matiere, debutH, finH, formation);
        try {
            seanceDAO.update(seance);
            int semaine = getIndiceEmploi(seance.getHdebut());

            int idx = emploiDuTemps[semaine].indexOf(seance);
            emploiDuTemps[semaine].remove(idx);
            emploiDuTemps[semaine].add(seance);

            setChanged();
            notifyObservers();
        } catch (EchecContrainteException e) {
            e.printStackTrace();
        }
    }

    // Nom de Methode un peu troll je l'avoue
    public void modifierSeanceBDDsansAffichage(int idSalle, Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) {
        Seance seance = new Seance(idSalle, salle, enseignant, matiere, debutH, finH, formation);
        try {
            seanceDAO.update(seance);
        } catch (EchecContrainteException e) {
            e.printStackTrace();
        }

    }

    public void creerSeanceBDD(Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) throws EchecContrainteException, EchecSeanceEnseignantException, EchecSeanceFormationException, EchecHeureException {
        Seance seance = new Seance(salle, enseignant, matiere, debutH, finH, formation);
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
        Reservation reservation = new Reservation(id_enseignant, id_salle, date_reservation, id_seance);
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
        System.out.println(semainesSelectionnees);
        this.semainesSelectionnees.clear();
        this.semainesSelectionnees.addAll(semainesSelectionnees);

        setChanged();
        notifyObservers();
    }

    public List<Integer> getSemainesSelectionnees() {
        return semainesSelectionnees;
    }

    public void initEmploiEnseignant(int id_enseignant) {
        for (int i = 0; i < semaines; i++) {
            emploiDuTemps[i].clear();
        }

        listSeances = seanceDAO.getSeanceEnseignant(id_enseignant);
        for (Seance seance : listSeances) {
            int x = getIndiceEmploi(seance.getHdebut());
            emploiDuTemps[x].add(seance);

        }
        setChanged();
        notifyObservers();
    }

    public void initEmploiContrainteEnseignant(Enseignant enseignant) {
        for (int i = 0; i < semaines; i++) {
            emploiDuTempsContrainte[i].clear();
        }

        if(enseignant != null) {
            for (int i = 0; i < semaines; i++) {
                emploiDuTempsContrainte[i].clear();
            }
            for (Contrainte contrainte : contrainteDAO.getContraiteEnseignant(enseignant)) {
                int x = getIndiceEmploi(contrainte.getHdebut());
                emploiDuTempsContrainte[x].add(contrainte);
            }
        }

        setChanged();
        notifyObservers();
    }

    public Map<int[], HashSet<Seance>> gestionConflit() {
        Map<int[], HashSet<Seance>> mapSeances = new HashMap<>();
        boolean estPasse;

        for (int semaineAnnee : semainesSelectionnees) {
            for (Seance seance : emploiDuTemps[semaineAnnee]) {
                estPasse = false;
                Calendar debut = seance.getHdebut();
                Calendar fin = seance.getHfin();

                int debH = debut.get(Calendar.HOUR_OF_DAY) - 8;
                int finH = fin.get(Calendar.HOUR_OF_DAY) - 8;
                int debM = debut.get(Calendar.MINUTE) / 15;
                int finM = fin.get(Calendar.MINUTE) / 15;

                int d = 18 + (debH) * 38 + (debM) * 10;
                int f = 18 + (finH) * 38 + (finM) * 10;
                int j = debut.get(Calendar.DAY_OF_WEEK) - 2;

                for (Map.Entry entry : mapSeances.entrySet()) {
                    int[] intervalle = (int[]) entry.getKey();

                    if ((j == intervalle[2]) && (d < intervalle[1]) && (f > intervalle[0])) {
                        estPasse = true;
                        HashSet<Seance> ensemble = (HashSet<Seance>) entry.getValue();
                        ensemble.add(seance);

                        if (d < intervalle[0])
                            intervalle[0] = d;

                        if (f > intervalle[1])
                            intervalle[1] = f;
                    }
                }

                if (!estPasse) {
                    HashSet<Seance> seanceHashSet = new HashSet<>();
                    seanceHashSet.add(seance);
                    mapSeances.put(new int[]{d, f, j}, seanceHashSet);
                }
            }

        }

        return mapSeances;
    }

    public List<Salle> getSallesDispo(GregorianCalendar debutH, GregorianCalendar finH) {
        return seanceDAO.getSalleDispo(debutH, finH);
    }

    public List<Seance> getSeancesEnseignant(int id_enseignant) {

        return seanceDAO.getSeanceEnseignant(id_enseignant);
    }

    public List<Salle> getEnseignantDispo() {
        return enseignantDispo;
    }

    public void confirmerReservation(Reservation resv) {
        reservationDAO.update(resv);
    }

    public void refuserReservation(Reservation resv) {
        reservationDAO.refuseupdate(resv);
    }

    public Salle getSalleById(int id) {
        return salleDAO.find(id);
    }

    public void createFakeSeance(Seance s) {
        seanceDAO.createFakeSeance(s);
    }

    public Seance getSeanceById(int id_seance) {
        return seanceDAO.find(id_seance);
    }

    public void changerEmploiDutemps(Formation formation, Enseignant enseignant) {
        ArrayList<Seance> seanceArrayList = seanceDAO.getSeanceFormationEnseignant(formation, enseignant);

        for (List<Seance> seanceList : emploiDuTemps) {
            seanceList.clear();
        }

        for (Seance seance : seanceArrayList) {
            int x = getIndiceEmploi(seance.getHdebut());
            emploiDuTemps[x].add(seance);
        }

        setChanged();
        notifyObservers();
    }

    public void creerContrainte(Enseignant enseignant, Calendar debutH, Calendar finH, String motif) throws EchecContrainteException {
        Contrainte contrainte = new Contrainte(enseignant, debutH, finH, motif);
        contrainteDAO.create(contrainte);
        int x = getIndiceEmploi(contrainte.getHdebut());
        emploiDuTempsContrainte[x].add(contrainte);
        setChanged();
        notifyObservers();
    }

    public void supprimerContrainte(int idContrainte, int semaineAnnee) {
        Contrainte contrainte = new Contrainte(idContrainte);
        contrainteDAO.delete(contrainte);
        int idx = emploiDuTempsContrainte[semaineAnnee].indexOf(contrainte);
        //System.out.println(idx);
        emploiDuTempsContrainte[semaineAnnee].remove(idx);
        setChanged();
        notifyObservers();
    }

    public void modifierContrainte(int id, Enseignant enseignant, GregorianCalendar debutH, GregorianCalendar finH, String motif) {
        try {
            Contrainte contrainte = new Contrainte(id, enseignant, debutH, finH, motif);
            contrainteDAO.update(contrainte);

            int semaine = getIndiceEmploi(contrainte.getHdebut());

            int idx = emploiDuTempsContrainte[semaine].indexOf(contrainte);
            emploiDuTempsContrainte[semaine].remove(idx);
            emploiDuTempsContrainte[semaine].add(contrainte);

            setChanged();
            notifyObservers();
        } catch (EchecContrainteException e) {
            e.printStackTrace();
        }

    }

    public boolean verifierAuthEtudiant(String login, String password) {
        return etudiantDAO.verifierAuthEtudiant(login, password);
    }

    public boolean verifierAuthEnseignant(String login, String password) {
        return enseignantDAO.verifierAuthEnseignant(login, password);
    }

    public boolean verifierAuthAdministrateur(String login, String password) {
        return admnistrateurDAO.verifierAuthAdministrateur(login, password);
    }

    public Connection getConnect() {
        return connect;
    }

    public List<List> getReservationsAffiche() {
        List<List> resultat = new ArrayList();

        for(Reservation reservation: reservations) {
            List attributs = new ArrayList();
            attributs.add(reservation.getId());
            attributs.add(salleDAO.find(reservation.getId_salle()));
            attributs.add(enseignantDAO.find(reservation.getId_enseignant()));
            attributs.add(reservation.getDate_reservation());
            attributs.add(reservation.getEtat_reservation());
            Seance seance = seanceDAO.find(reservation.getId_seance());
            Calendar hdebut = seance.getHdebut();
            attributs.add(hdebut.get(Calendar.DAY_OF_MONTH) + "/" + (hdebut.get(Calendar.MONTH)+1)+ "/" + hdebut.get(Calendar.YEAR));
            resultat.add(attributs);
        }

        return resultat;
    }

    public List<List> getTotalReservationsAffiche() {
        List<List> resultat = new ArrayList();

        for(Reservation reservation: totalReservations) {
            List attributs = new ArrayList();
            attributs.add(reservation.getId());
            attributs.add(salleDAO.find(reservation.getId_salle()));
            attributs.add(enseignantDAO.find(reservation.getId_enseignant()));
            attributs.add(reservation.getDate_reservation());
            attributs.add(reservation.getEtat_reservation());
            Seance seance = seanceDAO.find(reservation.getId_seance());
            Calendar hdebut = seance.getHdebut();
            attributs.add(hdebut.get(Calendar.DAY_OF_MONTH) + "/" + (hdebut.get(Calendar.MONTH)+1)+ "/" + hdebut.get(Calendar.YEAR));
            resultat.add(attributs);
        }

        return resultat;
    }
}
