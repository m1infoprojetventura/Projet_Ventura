package fr.univtln.group_aha;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Seance {
    private int id;
    private Salle salle;
    private Enseignant enseignant;
    private Matiere matiere;
    private Calendar  hdebut;
    private Calendar hfin;
    private Formation formation;

    public Seance(Salle salle, Enseignant enseignant, Matiere matiere, Calendar hdebut, Calendar hfin,Formation formation) {
        this.salle = salle;
        this.enseignant = enseignant;
        this.matiere = matiere;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.formation = formation;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Seance(int id, Salle salle, Enseignant enseignant, Matiere matiere, Calendar hdebut, Calendar hfin, Formation formation)
    {
        this.id = id;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.enseignant = enseignant;
        this.salle = salle;
        this.matiere = matiere;
        this.formation = formation;
    }
    public Seance (Salle s,Calendar hdebut){
        this.salle = s;
        this.hdebut=hdebut;
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

        if (this.id != seance.id) return false;

        return true;
    }

    public List getAttributs() {
        List resultat= new ArrayList();
        resultat.add(this.id);
        resultat.add(this.salle);
        resultat.add(this.enseignant);
        resultat.add(this.matiere);
        resultat.add(String.valueOf(this.hdebut.get(Calendar.HOUR_OF_DAY)));
        resultat.add(String.valueOf(this.hfin.get(Calendar.HOUR_OF_DAY)));
        //resultat.add(String.valueOf(this.hfin.get(Calendar.M)));
        resultat.add(this.hdebut.get(Calendar.DAY_OF_MONTH) + "/" + (this.hdebut.get(Calendar.MONTH)+1)+ "/" + this.hdebut.get(Calendar.YEAR));
        return resultat;
    }
}
