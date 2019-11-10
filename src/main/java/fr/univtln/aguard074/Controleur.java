package fr.univtln.aguard074;
import fr.univtln.group_aha.Personne;
import fr.univtln.group_aha.Professeur;

import java.util.Date;

public class Controleur implements Icontroleur {
    //private VueGestionaire vueGestionaire;
    private Imodele modele;
    private VueGestionaire vue;

    public Controleur(Imodele modele){
        this.modele = modele;
    }

    @Override
    public void creerEtudiant(int id, String nom, String prenom, String intituleParcours, Date dateNaissance){
        this.modele.creerEtudiant(id,nom,prenom,dateNaissance,intituleParcours);
    }

    public void creerEnseignant(int id, String nom, String prenom,Date dateNaissance) {
        Personne enseignant = new Professeur(id,nom,prenom,dateNaissance);
        this.modele.creerEnseignant(id,nom,prenom,dateNaissance);

    }

    /*@Override
    public void afficherPersonne(Personne p) {
        this.modele.afficherPersonne(p);

    }*/

    @Override
    public void suprimerPersonne(Personne p) {

        this.modele.supprimerPersonne(p);

    }

}
