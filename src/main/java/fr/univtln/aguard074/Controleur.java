package fr.univtln.aguard074;

public class Controleur implements Icontroleur {
    //private VueGestionaire vueGestionaire;
    private Imodele modele;
    private VueGestionaire vue;

    public Controleur(Imodele modele){
        this.modele = modele;
    }

    @Override
    public void creerPersonne(String nom, String prenom, int age, Personne.Statut statut) {

            this.modele.creerPersonne(nom, prenom, 0, statut);


    }

    @Override
    public void afficherPersonne(Personne p) {
        this.modele.afficherPersonne(p);

    }

    @Override
    public void suprimerPersonne(Personne p) {

        this.modele.supprimerPersonne(p);

    }

}
