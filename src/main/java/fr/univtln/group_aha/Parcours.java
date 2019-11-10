package fr.univtln.group_aha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parcours {
    private String intitule;
    static String [] tab = {"M1 INFORMATIQUE","M2 MULTIMEDIA ","M1 PHYSIQYE APPLIQUE","M1 MATHEMATIQUE","M2 INFORMATIQUE"};
    private static List<String> ensembleParcours = new ArrayList<>(Arrays.asList(tab));

    public Parcours() {

        intitule = "";
    }

    public Parcours(String intitule) {

        this.intitule = intitule;
    }

    public String getIntitule() {

        return intitule;
    }

    public static List<String> getEnsembleParcours() {
        return ensembleParcours;
    }



}
