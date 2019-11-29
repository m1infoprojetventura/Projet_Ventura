package fr.univtln.group_aha;

import java.util.ArrayList;
import java.util.List;

public class Salle {
    private String nom;
    private List<Materiel.TypeMateriel> materiels;
    private int capacite;
    private int id;

    public Salle() {
        nom = "";
        materiels = new ArrayList<>();
        capacite = 0;
        id = 0;
    }

    public Salle(String nom, List<Materiel.TypeMateriel> materiels, int capacite) {
        this.nom = nom;
        this.materiels = materiels;
        this.capacite = capacite;

    }

    public Salle(String nom, List<Materiel.TypeMateriel> materiels, int capacite, int id) {
        this.nom = nom;
        this.materiels = materiels;
        this.capacite = capacite;
        this.id = id;

    }

    public String getNom() {
        return this.nom;
    }

    public List<Materiel.TypeMateriel> getMateriels() {
        return materiels;
    }

    public int getCapacite() {
        return this.capacite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addMateriel(Materiel.TypeMateriel m){
        materiels.add(m);
    }

    public List getAttributs(){

        List resultat= new ArrayList();
        resultat.add(this.id);
        resultat.add(this.nom);
        resultat.add(this.capacite);
        resultat.add(this.materiels);

        return resultat;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salle salle = (Salle)o;

        if (id != salle.id) return false;

        return true;
    }
}
