package fr.univtln.group_aha;
// Il faudra mettre tout ça dans des packages spécifique du genre fr.univtln.group_aha.model

import java.util.*;

/**
 * Classe représentant une Personne du corps universitaire
 * @author Adrien
 * @author Anouar
 * @author Haribou
 */
public abstract class Personne {
    private int id;
    private String nom;
    private String prenom;
    private Date date_naissance;
    // Hacher du mot de passe (transient sert à rien mais ça indique que c'est une
    // information générée).
    private transient String mdp;
    private transient String login;

    public Personne() {
        id = 0;
        nom = "";
        prenom = "";
        date_naissance = new Date();
    }

    /**
     * Contructeur de la classe Personne
     * @param nom
     *    Nom d'une personne
     * @param prenom
     *    Prénom d'une personne
     */
    public Personne(int id, String nom, String prenom, Date date_naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        generationMdp();
    }

    public Personne( String nom, String prenom, Date date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        generationMdp();
    }


    public void generationLogin() {
        java.util.Date date = date_naissance;

        int x = Math.abs((date.hashCode() + 31*id) % 1000);
        this.login = prenom.substring(0, 1).toLowerCase() + nom.toLowerCase() + x;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String toString()
    {
        return this.nom +" "+ this.prenom ;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void generationMdp() {
        int annee  =  date_naissance.getYear();
        this.mdp = getNom().toLowerCase() + getPrenom().toLowerCase() + annee;
    }


    public String getLogin() {
        return login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List getAttributs(){

        List resultat= new ArrayList();
        resultat.add(this.id);
        resultat.add(this.nom);
        resultat.add(this.prenom);
        resultat.add(this.date_naissance);
        resultat.add(this.login);

        return resultat;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne personne = (Personne) o;

        if (id != personne.id) return false;

        return true;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getMdp() {
        return mdp;
    }
}
