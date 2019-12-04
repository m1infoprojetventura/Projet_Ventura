package fr.univtln.group_aha;

import java.util.Calendar;

public class Seance {
    private int id;
    private Salle salle;
    private Enseignant enseignant;
    private Matiere matiere;
    private Calendar  hdebut;
    private Calendar hfin;

    public Seance(Salle salle, Enseignant enseignant, Matiere matiere, Calendar hdebut, Calendar hfin) {
        this.salle = salle;
        this.enseignant = enseignant;
        this.matiere = matiere;
        this.hdebut = hdebut;
        this.hfin = hfin;
    }

    public Seance(Calendar hdebut, Calendar hfin)
    {
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.enseignant = new Enseignant();
        this.salle = new Salle();
        this.matiere = new Matiere();
    }

    public Seance(int idSeance) {
        id= idSeance;
    }

    public Calendar getHdebut() {
        return hdebut;
    }

    public Calendar getHfin() {
        return hfin;
    }

    public Salle getSalle() {
        return salle;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public void setHdebut(Calendar hdebut) {
        this.hdebut = hdebut;
    }

    public void setHfin(Calendar hfin) {
        this.hfin = hfin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seance seance = (Seance) o;

        if (id != seance.id) return false;

        return true;
    }
}
