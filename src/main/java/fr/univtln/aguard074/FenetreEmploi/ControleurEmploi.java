package fr.univtln.aguard074.FenetreEmploi;


import fr.univtln.group_aha.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ControleurEmploi{
    private ModeleEmploi modele;

    public ControleurEmploi(ModeleEmploi modele){
        this.modele = modele;
    }

    public void creerSeance(int id, Salle salle, Enseignant enseignant, Matiere matiere, Calendar debutCours, Calendar finCours, Formation formation) {
        this.modele.creerSeance(id,salle, enseignant, matiere, debutCours, finCours, formation);
    }

    public void creerEmploi() {
        this.modele.creerEmploi();
    }

    public void getSalles() {
        this.modele.getSalles();
    }

    public void associerMatiereProf(Matiere matiere, Enseignant enseignant) {
        this.modele.associerMatiereProf(matiere,enseignant);
    }

    public List<Enseignant> getAssocTeachers(Matiere matiere) {
        return this.modele.getAssocTeachers(matiere);
    }


    public void modifierSeance(int idSalle, Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) {
        this.modele.modifierSeance(idSalle,salle,enseignant,debutH,finH,formation);
    }

    public void supprimerSeance(int idSalle, int jourSemaine) {
        this.modele.supprimerSeance(idSalle, jourSemaine);
    }

    public List<String> recupMatieres(Enseignant enseignant) {
        return this.modele.recupMatieres(enseignant);
    }

    public void initEmploi(int id){
        this.modele.initEmploi(id);
    }

    public void supprimerEmploi(int id) {
        this.modele.supprimerEmploi(id);
    }

    public boolean verifierAuthResponsable(String login, String password) {
        return this.modele.verifierAuthResponsable(login,password);
    }

    public void modifierSeanceBDD(int idSalle, Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) {
        this.modele.modifierSeanceBDD(idSalle,salle,enseignant,matiere,debutH,finH,formation);
    }

    public void creerSeanceBDD(Salle salle, Enseignant enseignant, Matiere matiere, GregorianCalendar debutH, GregorianCalendar finH, Formation formation) {
        this.modele.creerSeanceBDD(salle, enseignant, matiere, debutH, finH, formation);
    }

    public void supprimerSeanceBDD(int idSalle,int semaine) {
        this.modele.supprimerSeanceBDD(idSalle, semaine);
    }

    public void changerSemaine() {
        modele.changerSemaine();
    }
    public String getTypeLogin(String login) {
        return this.modele.getTypeLogin(login);
    }

    public void creerReservation(int id_enseignant, int id_salle, Date date_reservation, int id_seance) {
        this.modele.creerReservation(id_enseignant,id_salle,date_reservation, id_seance);
    }

    public void setSemainesSelectionnees(List<Integer> semainesSelectionnees) {
        this.modele.setSemainesSelectionnees(semainesSelectionnees);
    }

    public void initEmploiEnseignant(int id_enseignant) {
        this.modele.initEmploiEnseignant(id_enseignant);
    }

    public Etudiant getEtudiantbyLogin(String personneAuthentifiee) {
        return this.modele.getEtudiantbyLogin(personneAuthentifiee);
    }

    public List<Salle> getSallesDispo(GregorianCalendar debutH, GregorianCalendar finH) {
        return this.modele.getSallesDispo(debutH,finH);
    }
}
