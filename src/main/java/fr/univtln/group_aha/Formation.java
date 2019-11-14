package fr.univtln.group_aha;

import java.util.List;

public class Formation {
    private String intitule;
    private Departement departement;
    private int id = 0;

    // Temporaire: Peut être défini à l'extèrieur de la classe
    // Donne l'ensemble des parcours possible
    private static List<String> ensembleParcours;

    public Formation() {
        intitule = "";
        departement = new Departement();
    }

    public Formation(String intitule) {
        this.intitule = intitule;
        this.departement = new Departement();
        // ou this.departement = null;
    }
    public Formation(String intitule, Departement departement) {
        this.intitule = intitule;
        this.departement = departement;
    }

    public Formation(int id, String intitule, Departement departement) {
        this.id = id;
        this.intitule = intitule;
        this.departement = departement;
    }

    @Override
    public String toString() {
        return getIntitule();
    }

    public String getIntitule() {
        return intitule;
    }

    public int getId() {
        return id;
    }
}
