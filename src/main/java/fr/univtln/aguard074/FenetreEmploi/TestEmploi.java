package fr.univtln.aguard074.FenetreEmploi;

import com.bulenkov.darcula.DarculaLaf;
import fr.univtln.group_aha.Hachage;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestEmploi
{
    public static void main( String[] args )
    {
        BasicLookAndFeel darcula = new DarculaLaf();
        UIManager.getFont("Label.font");
        try {
            UIManager.setLookAndFeel(darcula);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        try {
            String chaine = Hachage.toHexString(Hachage.getSHA("azerty"));
            System.out.println(chaine);
            System.out.println(chaine.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            String url = "jdbc:mysql://localhost/scolaire";
            String user = "adrien";
            String password = "password";
            Connection connect = DriverManager.getConnection(url, user, password);
            ModeleEmploi modele = new ModeleEmploi(connect);
            //Création du contrôleur
            ControleurEmploi controler = new ControleurEmploi(modele);
            //Création de notre fenêtre avec le contrôleur en paramètre
            //VueEmploi gestionaire = new VueEmploi(controler, modele);
            VueDeLemploi vue = new VueDeLemploi(modele,controler);
            //vue.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
