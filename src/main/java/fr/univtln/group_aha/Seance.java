package fr.univtln.group_aha;

import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Salle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


public class Seance {
    private Calendar jour;
    private Calendar  hdebut;
    private Calendar hfin;
    private Salle salle;
    private Enseignant enseignant;
    private Matiere matiere;


    public Seance(Calendar jour, Calendar hdebut, Calendar hfin)
    {
        this.jour = jour;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.enseignant = new Enseignant();
        this.salle = new Salle();
        this.matiere = new Matiere();
    }
}
