package fr.univtln.aguard074.FenetreEmploi;

import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Salle;
import fr.univtln.group_aha.Seance;
import fr.univtln.group_aha.SeanceDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

public class ModeleEmploi extends Observable {

    private List<Seance> listSeances = new ArrayList<>();
    private static SeanceDAO seanceDAO = new SeanceDAO();

    public void creerSeance(Calendar test1, Calendar test2, Calendar test3) {
        Seance seance = new Seance(test1, test2, test3);
        listSeances.add(seance);
    }

    public void creerEmploi() {
        for (Seance seance : listSeances) {
            seanceDAO.create(seance);
        }
    }
}
