package fr.univtln.aguard074;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        //Instanciation de notre modèle
        String url = "jdbc:mysql://localhost/scolaire";
        String user = "haribou";
        String password = "password";

        Imodele modele = new Modele();
        //Création du contrôleur
        Icontroleur controler = new Controleur(modele);
        //Création de notre fenêtre avec le contrôleur en paramètre
        VueGestionaire gestionaire = new VueGestionaire(controler);



    }
}
