package fr.univtln.group_aha;

import java.util.List;

public class Parcours {
    private String intitule;

    // Temporaire: Peut être défini à l'extèrieur de la classe
    // Donne l'ensemble des parcours possible
    private static List<String> ensembleParcours;

    Parcours() {
        intitule = "";
    }

    Parcours(String intitule) {
        this.intitule = intitule;
    }

    public String getIntitule() {
        return intitule;
    }
}
