package fr.univtln.group_aha;

import java.util.Date;

public class Reservation {
    private int id;
    private int id_enseignant;
    private int id_salle;
    private Date date_reservation;
    private String etat_reservation;

    public Reservation(int id_enseignant, int id_salle, Date date_reservation, String etat_reservation) {
        this.id_enseignant = id_enseignant;
        this.id_salle = id_salle;
        this.date_reservation = date_reservation;
        this.etat_reservation = etat_reservation;
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

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getEtat_reservation() {
        return etat_reservation;
    }

    public void setEtat_reservation(String etat_reservation) {
        this.etat_reservation = etat_reservation;
    }
}
