package fr.univtln.aguard074.FenetreEmploi;

public class TestEmploi
{
    public static void main( String[] args )
    {
        //Instanciation de notre modèle
        ModeleEmploi modele = new ModeleEmploi();
        //Création du contrôleur
        ControleurEmploi controler = new ControleurEmploi(modele);
        //Création de notre fenêtre avec le contrôleur en paramètre
        //VueEmploi gestionaire = new VueEmploi(controler, modele);
        VueDeLemploi vue = new VueDeLemploi();
        //vue.setVisible(true);
    }
}
