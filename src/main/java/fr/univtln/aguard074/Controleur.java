package fr.univtln.aguard074;

public class Controleur implements Icontroleur {
    //private VueGestionaire vueGestionaire;
    private Imodele modele;

    public Controleur(Imodele modele){
        this.modele = modele;
    }

    @Override
    public void creerEtudiant(String nom, String prenom, int age, Parcours parcours) {
        this.modele.creerEtudiant(nom, prenom, age, parcours);
    }

    @Override
    public void creerEnseignant(String nom, String prenom, int age, Departement departement) {
        this.modele.creerEnseignant(nom, prenom, age, departement);
    }

    @Override
    public void afficherPersonne(Personne p) {
        this.modele.afficherPersonne(p);

    }

}
