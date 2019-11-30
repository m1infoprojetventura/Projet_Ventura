package fr.univtln.aguard074.FenetreEmploi;


import java.util.Calendar;

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
}
