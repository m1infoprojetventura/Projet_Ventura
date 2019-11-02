package fr.univtln.aguard074;

public class Personne {
    private String nom;
    private String prenom;
    private int age;
    private Statut statut;
    public enum Statut{
        ETUDIANT,
        ENSEIGNANT,
        RESPONSABLE_FORMATION;

    }

    public Personne(String nom, String prenom, int age, Statut statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.statut = statut;
    }

    public String toString()
    {
        return this.nom +" "+ this.prenom + " " + this.age + " " +this.statut;
    }
}
