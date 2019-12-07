package fr.univtln.aguard074.FenetreAdmin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Instanciation de notre modèle
        Modele modele = new Modele();
        //Création du contrôleur
        Icontroleur controler = new Controleur(modele);
        //Création de notre fenêtre avec le contrôleur en paramètre
        VueGestionaire gestionaire = new VueGestionaire(controler, modele);
    }
}
