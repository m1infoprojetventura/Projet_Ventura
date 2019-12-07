package fr.univtln.aguard074.Application;

import fr.univtln.aguard074.FenetreAdmin.Controleur;
import fr.univtln.aguard074.FenetreAdmin.Icontroleur;
import fr.univtln.aguard074.FenetreAdmin.Modele;
import fr.univtln.aguard074.FenetreAdmin.VueGestionaire;
import fr.univtln.aguard074.FenetreEmploi.ControleurEmploi;
import fr.univtln.aguard074.FenetreEmploi.ModeleEmploi;
import fr.univtln.aguard074.FenetreEmploi.VueEmploi;

public class Application {
    public static void main( String[] args )
    {
        // ADMIN
        Modele modele = new Modele();
        Icontroleur controler = new Controleur(modele);
        VueGestionaire gestionaire = new VueGestionaire(controler, modele);


        //EmploiDuTemps
        ModeleEmploi modeleEmploi = new ModeleEmploi();
        ControleurEmploi controleurEmploi = new ControleurEmploi(modeleEmploi);
        VueEmploi gestionaireEmploi = new VueEmploi(controleurEmploi, modeleEmploi);
        gestionaireEmploi.dispose();
    }
}
