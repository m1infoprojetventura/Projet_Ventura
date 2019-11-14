package fr.univtln.group_aha;
// Il faudra mettre tout ça dans des packages spécifique du genre fr.univtln.group_aha.model

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    private String login;

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

    public Personne(String nom, String prenom, Date date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.login = generationLogin();
    }

    public String generationLogin() {
        java.util.Date date = date_naissance;
        int x = date.hashCode() % 1000;
        return prenom.substring(0, 1) + nom + x;
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

    public String generationMpd() {
        Calendar calender = new GregorianCalendar();
        int annee  = calender.get(Calendar.YEAR);
        return getNom() + getPrenom() + annee;
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
}
