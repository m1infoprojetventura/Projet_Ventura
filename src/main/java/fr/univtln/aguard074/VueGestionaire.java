package fr.univtln.aguard074;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;


public class VueGestionaire extends JFrame {
    private Modele modele;
    private Icontroleur controleur;

    private JTextField personneNom;
    private JTextField personnePrenom;
    private JTextField personneAge;
    private JTextField personneStatut;
    private JComboBox listeStatut;
    private JList listePersonnes;
    private JScrollPane scrollbar;
    private Jmodel personesEnregistres = new Jmodel();





    public VueGestionaire(Icontroleur controleur, Modele modele){
        super();
        this.modele = modele;
        Init();//On initialise notre fenêtre
        this.controleur = controleur;



    }

    private void Init() {
        setTitle("Gestionaire emploi du temps"); //On donne un titre à l'application
        setSize(600, 600); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix



        this.modele.addObserver(personesEnregistres);

        this.getContentPane().add(saisieInfoPersonne());



        //getContentPane().add();
        this.setVisible(true);
    }



    private JPanel saisieInfoPersonne(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));
        panel.setBackground(Color.white);
        // création des sous containers
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new FlowLayout());
        JPanel panelAffiche = new JPanel(new FlowLayout());

        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.white);
        panel4.setBackground(Color.white);
        panel5.setBackground(Color.white);
        panelAffiche.setBackground(Color.white);


        JLabel nom = new JLabel("NOM :");
        panel1.add(nom);
        this.personneNom = new JTextField();
        this.personneNom.setPreferredSize(new Dimension(150,20));
        panel1.add(personneNom);


        JLabel prenom = new JLabel("Prenom :");
        panel2.add(prenom);
        this.personnePrenom = new JTextField();
        this.personnePrenom.setPreferredSize(new Dimension(150,20));
        panel2.add(personnePrenom);

        JLabel age = new JLabel("Age :");
        panel3.add(age);
        this.personneAge= new JTextField();
        this.personneAge.setPreferredSize(new Dimension(150,20));
        panel3.add(personneAge);

        // ajouter le container qui renvoir le JComboBox de status
        panel4.add(choixStatut());
        // création des deux boutton
        JButton AddBouton = new JButton("Ajouter");
        JButton CancelBouton = new JButton("Annuler");

        AddAction AddAction = new AddAction();
        DeleteAction deleteAction = new DeleteAction();
        AddBouton.addActionListener(AddAction);
        CancelBouton.addActionListener(deleteAction);
        panel5.add(AddBouton);
        panel5.add(CancelBouton);
        //Panel affichage
        panelAffiche.add(affichePersonnes());

        //Ajouter les sous containers au container principale
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panelAffiche);
        return panel;
    }

    private JPanel choixStatut(){

        JPanel panel = new JPanel(new GridLayout(2,1));
        JPanel panel1 = new JPanel(new FlowLayout());
        panel.setBackground(Color.white);
        panel1.setBackground(Color.white);

        JLabel statut = new JLabel("Statut : ");
        panel1.add(statut);
        Object[] elements = new Object[]{"ETUDIANT", "PROFESSEUR", "RESPONSABLE_FORMATION"};

        listeStatut = new JComboBox(elements);
        listeStatut.setPreferredSize(new Dimension(120,20));
        panel1.add(listeStatut);
        panel.add(panel1);
        return panel;
    }

    private JPanel affichePersonnes() {
        JPanel panel = new JPanel(new GridLayout(1,0));
        JPanel panel1 = new JPanel(new FlowLayout());
        panel.setBackground(Color.white);
        panel1.setBackground(Color.white);

        JLabel p = new JLabel("Personnes : ");
        panel.add(p);
        personesEnregistres.add(0,"default");
        listePersonnes = new JList(personesEnregistres);
        listePersonnes.setBackground(Color.white);

        listePersonnes.setPreferredSize(new Dimension(400,40));

        scrollbar = new JScrollPane();
        scrollbar.setViewportView(listePersonnes);

        //listePersonnes.setPreferredSize(new Dimension(440,20));
        panel.add(scrollbar);
        panel.add(panel1);
        return panel;

    }




    class AddAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //On affiche le chiffre en plus dans le label
            String str = ((JButton)e.getSource()).getText();
            //controleur.afficherPersonne(new Personne("michel","ll",45, Personne.Statut.ETUDIANT));
            String nom = personneNom.getText();
            String prenom = personnePrenom.getText();
            int age = Integer.parseInt(personneAge.getText());
            int RecupBox = listeStatut.getSelectedIndex();
            Personne.Statut statut = Personne.Statut.ETUDIANT;
            switch(RecupBox) {
                case 0:
                    statut = Personne.Statut.ETUDIANT;
                    break;
                case 1:
                    statut = Personne.Statut.ENSEIGNANT;
                    break;
                case 2:
                    statut = Personne.Statut.RESPONSABLE_FORMATION;
            }
            controleur.creerPersonne(nom,prenom,age,statut);
            //controleur.afficherPersonne(new Personne(nom,prenom,age,statut));


        }
    }

    class DeleteAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            personneNom.setText("");
            personnePrenom.setText("");
            personneAge.setText("");
        }
    }



    //Modele de liste permetant de manipuler le contenu d'une Jlist
    public class Jmodel extends DefaultListModel implements Observer{

        @Override
        public void update(Observable observable, Object o) {
            System.out.println(o.toString());

            System.out.println(this);
            this.add(0,o.toString());





        }
    }

}
