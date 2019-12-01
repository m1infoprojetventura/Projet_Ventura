package fr.univtln.aguard074.FenetreEmploi;


import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Matiere;

import java.util.Calendar;
import java.util.List;

public class ControleurEmploi{
    private ModeleEmploi modele;

    public ControleurEmploi(ModeleEmploi modele){
        this.modele = modele;
    }

    public void creerSeance(Calendar test1, Calendar test2, Calendar test3) {
        this.modele.creerSeance(test1,test2,test3);
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
