package fr.univtln.aguard074;

public interface Icontroleur {

    void creerPersonne(String nom, String prenom, int age, Personne.Statut statut);

    void afficherPersonne(Personne p);
    void suprimerPersonne(Personne p);


}
