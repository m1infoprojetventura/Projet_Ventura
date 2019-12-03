package fr.univtln.aguard074.FenetreEmploi;

import javax.swing.*;

public class TestEmploi
{
    public static void main( String[] args )
    {
        // Instanciation de notre modèle
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        ModeleEmploi modele = new ModeleEmploi();
        //Création du contrôleur
        ControleurEmploi controler = new ControleurEmploi(modele);
        //Création de notre fenêtre avec le contrôleur en paramètre
        //VueEmploi gestionaire = new VueEmploi(controler, modele);
        VueDeLemploi vue = new VueDeLemploi(modele,controler);
        //vue.setVisible(true);
    }
}
