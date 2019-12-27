package fr.univtln.group_aha;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Reservation {
    private int id;
    private int id_enseignant;
    private int id_salle;
    private int id_seance;
    private Date date_reservation;
    private String etat_reservation;

    public Reservation(int id_enseignant, int id_salle, Date date_reservation, String etat_reservation) {
        this.id_enseignant = id_enseignant;
        this.id_salle = id_salle;
        this.date_reservation = date_reservation;
        this.etat_reservation = etat_reservation;
    }

    public Reservation( int id_enseignant, int id_salle, Date date_reservation, int id_seance) {

        this.id_enseignant = id_enseignant;
        this.id_salle = id_salle;
        this.id_seance = id_seance;
        this.date_reservation = date_reservation;
    }

    public Reservation(int id, int id_enseignant, int id_salle, Date date_reservation, String etat_reservation) {
        this.id = id;
        this.id_enseignant = id_enseignant;
        this.id_salle = id_salle;
        this.date_reservation = date_reservation;
        this.etat_reservation = etat_reservation;
    }

    public Reservation(int id_enseignant, int id_salle, Date date_reservation) {
        this.id_enseignant = id_enseignant;
        this.id_salle = id_salle;
        this.date_reservation = date_reservation;
    }

    public Reservation(int id, int id_enseignant, int id_salle, Date date_reservation, String etat_reservation, int id_seance) {
        this.id = id;
        this.id_enseignant = id_enseignant;
        this.id_salle = id_salle;
        this.id_seance = id_seance;
        this.date_reservation = date_reservation;
        this.etat_reservation = etat_reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_enseignant() {
        return id_enseignant;
    }

    public void setId_enseignant(int id_enseignant) {
        this.id_enseignant = id_enseignant;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public int getId_seance() {
        return id_seance;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getEtat_reservation() {
        return etat_reservation;
    }

    public void setEtat_reservation(String etat_reservation) {
        this.etat_reservation = etat_reservation;
    }

    public List getAttributs() {
        List resultat= new ArrayList();
        resultat.add(this.id);
        resultat.add(this.id_salle);
        resultat.add(this.id_enseignant);
        resultat.add(this.date_reservation);
        resultat.add(this.etat_reservation);
        resultat.add(this.id_seance);
        return resultat;
    }
}
