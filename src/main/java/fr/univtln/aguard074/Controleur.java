package fr.univtln.aguard074;
import fr.univtln.group_aha.Parcours;
import fr.univtln.group_aha.Personne;
import fr.univtln.group_aha.Professeur;

public class Controleur implements Icontroleur {
    //private VueGestionaire vueGestionaire;
    private Imodele modele;
    private VueGestionaire vue;

    public Controleur(Imodele modele){
        this.modele = modele;
    }

    @Override
    public void creerEtudiant(int id, String nom, String prenom, Parcours parcours){
        this.modele.creerEtudiant(id,nom,prenom,parcours);
    }

    public void creerEnseignant(int id, String nom, String prenom) {
        Personne enseignant = new Professeur(id,nom,prenom);
        this.modele.creerEnseignant(id,nom,prenom);

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
