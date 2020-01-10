package fr.univtln.aguard074.FenetreEmploi;

import com.bulenkov.darcula.DarculaLaf;
import fr.univtln.group_aha.Hachage;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            String chaine = Hachage.toHexString(Hachage.getSHA("admin"));
            System.out.println(chaine);
            System.out.println(chaine.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            Connection connect = DriverManager.getConnection("jdbc:hsqldb:file:planning_db;sql.syntax_mys=true", "student", "");
            connect.prepareStatement("CREATE USER student PASSWORD \"\"");

         //  String[] commands = readFile("./planning_db.sql").split(";");

         //  for(int i = 0; i < commands.length - 1; i++) {
         //      System.out.println(commands[i]);
         //      System.out.println("----------------------------------------------");
         //      connect.createStatement().executeUpdate(commands[i].replace("\n", ""));
         //  }

            ModeleEmploi modele = new ModeleEmploi(connect);
            //Création du contrôleur
            ControleurEmploi controler = new ControleurEmploi(modele);
            //Création de notre fenêtre avec le contrôleur en paramètre
            //VueEmploi gestionaire = new VueEmploi(controler, modele);
            VueDeLemploi vue = new VueDeLemploi(modele,controler);
            //vue.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // catch (IOException e) {
        //     e.printStackTrace();
        // }


    }

    public static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
