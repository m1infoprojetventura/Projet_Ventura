package fr.univtln.aguard074.FenetreEmploi;

import javax.swing.*;

public class VueEmploi extends JFrame {
    private ModeleEmploi modele;
    private ControleurEmploi controleur;

    public VueEmploi(ControleurEmploi controleur, ModeleEmploi modele) {
        super();
        this.controleur = controleur;
        this.modele = modele;
        Init();//On initialise notre fenêtre

    }

    private void Init() {
        setTitle("Emploi du temps"); //On donne un titre à l'application
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        this.setBounds(100, 100, 789, 516);
        this.setVisible(true);
    }
}
