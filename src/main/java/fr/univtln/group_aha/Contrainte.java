package fr.univtln.group_aha;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Contrainte {
    private int id;
    private Enseignant enseignant;
    private Calendar hdebut;
    private Calendar hfin;
    private String motif;

    public Contrainte(Enseignant enseignant, Calendar hdebut, Calendar hfin, String motif) {
        this.enseignant = enseignant;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.motif = motif;
    }

    public Contrainte(int id, Enseignant enseignant, Calendar hdebut, Calendar hfin, String motif) {
        this.id = id;
        this.enseignant = enseignant;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.motif = motif;
    }

    public Contrainte(int id) {
        this.id = id;
    }

    public Calendar getHdebut() {
        return hdebut;
    }

    public Calendar getHfin() {
        return hfin;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
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

        Contrainte contrainte = (Contrainte) o;

        if (this.id != contrainte.id) return false;

        return true;
    }

    public List getAttributs() {
        List resultat= new ArrayList();
        resultat.add(this.id);
        resultat.add(this.enseignant);
        resultat.add(String.valueOf(this.hdebut.get(Calendar.HOUR_OF_DAY)));
        resultat.add(String.valueOf(this.hfin.get(Calendar.HOUR_OF_DAY)));

        resultat.add(this.hdebut.get(Calendar.DAY_OF_MONTH) + "/" + (this.hdebut.get(Calendar.MONTH)+1)+ "/" + this.hdebut.get(Calendar.YEAR));
        return resultat;
    }

    public String getMotif() {
        return motif;
    }
}
