package fr.univtln.aguard074.FenetreEmploi;


import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Matiere;
import fr.univtln.group_aha.Salle;

import java.util.Calendar;
import java.util.List;

public class ControleurEmploi{
    private ModeleEmploi modele;

    public ControleurEmploi(ModeleEmploi modele){
        this.modele = modele;
    }

    public void creerSeance(Salle salle, Enseignant enseignant, Matiere matiere, Calendar debutCours, Calendar finCours) {
        this.modele.creerSeance(salle, enseignant, matiere, debutCours, finCours);
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
}
