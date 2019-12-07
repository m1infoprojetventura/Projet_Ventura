package fr.univtln.group_aha;

import java.util.ArrayList;
import java.util.List;

public class Matiere {
    private int id;
    private String nom;
    private List<Enseignant> listeEnseignants;

    public Matiere() {
        this.id = 0;
        this.nom = "";
        this.listeEnseignants = new ArrayList<>();
    }

    public Matiere(int id, String  nom, List<Enseignant> listeEnseignants) {
        this.id = id;
        this.nom = nom;
        this.listeEnseignants = listeEnseignants;
    }

    public Matiere(String  nom, List<Enseignant> listeEnseignants) {
        this.nom = nom;
        this.listeEnseignants = listeEnseignants;
    }

    public List<Enseignant> getListeEnseignants() {
        return listeEnseignants;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int anInt) {
        this.id = anInt;
    }
    public String toString()
    {return this.getNom();

    }
}
