package fr.univtln.aguard074;

import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Parcours;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VueGestionaire extends JFrame {
    private Imodele modele;
    private Icontroleur controleur;

    private JTextField personneNom;
    private JTextField personnePrenom;
    private JTextField personneAge;
    private JTextField personneStatut;
    private JComboBox listeStatut;


    public VueGestionaire(Icontroleur controleur){
        super();
        Init();//On initialise notre fenêtre
        this.controleur = controleur;

    }

    private void Init() {
        setTitle("Gestionaire emploi du temps"); //On donne un titre à l'application
        setSize(400, 400); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix

        setContentPane(saisieInfoPersonne());
        getContentPane().add(choixStatut());
        this.setVisible(true);
    }

    private JPanel saisieInfoPersonne(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        JLabel nom = new JLabel("NOM");
        panel.add(nom);
        this.personneNom = new JTextField();
        personneNom.setColumns(10);
        panel.add(personneNom);

        JLabel prenom = new JLabel("Prenom");
        panel.add(prenom);
        this.personnePrenom = new JTextField();
        personnePrenom.setColumns(10);
        panel.add(personnePrenom);

        JLabel age = new JLabel("Age");
        panel.add(age);
        this.personneAge= new JTextField();
        personneAge.setColumns(10);
        panel.add(personneAge);


        return panel;
        }

    private JPanel choixStatut(){

        JPanel panel = new JPanel();

        JLabel statut = new JLabel("Statut");
        panel.add(statut);
        panel.setLayout(new FlowLayout());

        Object[] elements = new Object[]{"ETUDIANT", "PROFESSEUR", "RESPONSABLE_FORMATION"};

        listeStatut = new JComboBox(elements);
        panel.add(listeStatut);

        JButton bouton = new JButton("Ajouter");
        Action action = new Action();
        bouton.addActionListener(action);

        panel.add(bouton);



        return panel;
    }


    class Action implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //On affiche le chiffre en plus dans le label
            String str = ((JButton)e.getSource()).getText();
            //controleur.afficherPersonne(new Personne("michel","ll",45, Personne.Statut.ETUDIANT));
            String nom = personneNom.getText();
            String prenom = personnePrenom.getText();
            int age = Integer.parseInt(personneAge.getText());
            int RecupBox = listeStatut.getSelectedIndex();

            switch(RecupBox) {
                case 0:
                    // Parcours "Science" est une valeur temporaire
                    controleur.creerEtudiant(nom, prenom, age, new Parcours("Informatique"));

                    break;
                case 1:
                    // 10 correspont à l'enseignant avec l'identifiant 10
                    controleur.creerEnseignant(nom, prenom, age, new Departement("Sciences", 10));
                    break;
                case 2:
                    break;
            }
        }
    }
}



