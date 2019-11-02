package fr.univtln.aguard074;

public class Controleur implements Icontroleur {
    //private VueGestionaire vueGestionaire;
    private Imodele modele;

    public Controleur(Imodele modele){
        this.modele = modele;
    }

    @Override
    public void creerPersonne(String nom, String prenom, int age, Personne.Statut statut) {
        this.modele.creerPersonne(nom,prenom,age,statut);

    }

    @Override
    public void afficherPersonne(Personne p) {
        this.modele.afficherPersonne(p);

    }

}
