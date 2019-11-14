package fr.univtln.aguard074;

import com.toedter.calendar.JDateChooser;
import fr.univtln.group_aha.Departement;
import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Formation;
import fr.univtln.group_aha.Personne;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;



public class VueGestionaire extends JFrame {
    private Modele modele;
    private Icontroleur controleur;

    private JTextField personneNom;
    private JTextField personneParcours;
    private JTextField personnePrenom;
    JDateChooser personneDateNaissance;
    private JTextField personneId;
    private JTextField personneStatut;
    private JComboBox listeStatut;
    private JComboBox listeParcours;
    JComboBox listeDepartements;
    private JList listePersonnes;
    private Jmodel personesEnregistres = new Jmodel();



    public VueGestionaire(Icontroleur controleur, Modele modele){
        super();
        this.modele = modele;
        Init();//On initialise notre fenêtre
        this.controleur = controleur;

    }


    private void Init() {
        setTitle("Gestionaire emploi du temps"); //On donne un titre à l'application
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        this.setBounds(100, 100, 789, 516);

        this.modele.addObserver(personesEnregistres);
        this.getContentPane().add(saisieInfoPersonne());
        this.setVisible(true);
    }



    private JPanel saisieInfoPersonne(){
        // création de bordure
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        // le conteneur global qui contient le formulaire et la liste
        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(null);

        // conteneur du Formulaire
        JPanel panelFormAddPersonne = new JPanel();
        panelFormAddPersonne.setBackground(Color.WHITE);
        panelFormAddPersonne.setBounds(23, 58, 304, 304);
        panelFormAddPersonne.setBorder(lineborder);
        // ajouter le formulaire au conteneur principale
        container.add(panelFormAddPersonne);
        panelFormAddPersonne.setLayout(null);


        JLabel lblNewLabel = new JLabel("Nom :");
        lblNewLabel.setBounds(10, 29, 46, 13);
        panelFormAddPersonne.add(lblNewLabel);

        personneNom = new JTextField();
        personneNom.setBounds(120, 26, 150, 19);
        panelFormAddPersonne.add(personneNom);
        personneNom.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Prenom : ");
        lblNewLabel_1.setBounds(10, 58, 57, 13);
        panelFormAddPersonne.add(lblNewLabel_1);

        personnePrenom = new JTextField();
        personnePrenom.setBounds(120, 55, 150, 19);
        panelFormAddPersonne.add(personnePrenom);
        personnePrenom.setColumns(10);

        JLabel lblDateDeNaissance = new JLabel("Date de Naissance");
        lblDateDeNaissance.setBounds(10, 87, 120, 13);
        panelFormAddPersonne.add(lblDateDeNaissance);

        personneDateNaissance = new JDateChooser();
        personneDateNaissance.setBounds(120, 84, 150, 19);
        panelFormAddPersonne.add(personneDateNaissance);


        JLabel lblStatus = new JLabel("Status : ");
        lblStatus.setBounds(10, 117, 46, 13);
        panelFormAddPersonne.add(lblStatus);
        // les elements de la liste status

       // construire notre combobox avec le enum status de la classe personne

        listeStatut = new JComboBox(new String[]{"Enseignant", "Étudiant"});
        listeStatut.setBounds(120, 113, 150, 21);
        panelFormAddPersonne.add(listeStatut);


        JLabel lblParcours = new JLabel("Parcours :");
        lblParcours.setBounds(10, 148, 86, 13);
        panelFormAddPersonne.add(lblParcours);
        listeParcours = new JComboBox(controleur.getFormations().toArray());
        listeParcours.setBounds(120, 144, 150, 21);
        panelFormAddPersonne.add(listeParcours);

      /*  personneId = new JTextField();
        personneId.hide();
        personneId.setColumns(10);
        personneId.setBounds(120, 84, 150, 19);
        panelFormAddPersonne.add(personneId);*/



        // construire notre combobox avec le enum Departement de la classe prof
        listeDepartements = new JComboBox(controleur.getDepartements().toArray());
        listeDepartements.setBounds(120, 181, 150, 21);
        panelFormAddPersonne.add(listeDepartements);

        JLabel lblDepartement = new JLabel("Departement :");
        lblDepartement.setBounds(10, 185, 86, 13);
        panelFormAddPersonne.add(lblDepartement);

        JButton AddBouton = new JButton("Ajouter");

        AddBouton.setBounds(45, 246, 85, 21);
        panelFormAddPersonne.add(AddBouton);

        JButton CancelBouton = new JButton("Annuler");
        CancelBouton.setBounds(152, 246, 85, 21);
        panelFormAddPersonne.add(CancelBouton);

        JPanel panelListePersonne = new JPanel();
        panelListePersonne.setBackground(Color.WHITE);
        panelListePersonne.setBorder(lineborder);



        panelListePersonne.setBounds(525, 40, 240, 361);
        container.add(panelListePersonne);
        panelListePersonne.setLayout(null);

        JButton suppBouton = new JButton("Supprimer");
        suppBouton.setBounds(0, 330, 240, 31);
        panelListePersonne.add(suppBouton);



        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 220, 310);
        panelListePersonne.add(scrollPane);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);
        JList listePersonnes = new JList(personesEnregistres);
        scrollPane.setViewportView(listePersonnes);
        listePersonnes.setBackground(Color.white);
        listePersonnes.setLayoutOrientation(JList.VERTICAL);
        AddAction AddAction = new AddAction();
        DeleteAction deleteAction = new DeleteAction();
        AddBouton.addActionListener(AddAction);
        CancelBouton.addActionListener(deleteAction);
        return container;
    }

    class AddAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //On affiche le chiffre en plus dans le label
            String str = ((JButton)e.getSource()).getText();
            //controleur.afficherPersonne(new Personne("michel","ll",45, Personne.Statut.ETUDIANT));
            String nom = personneNom.getText();
            String prenom = personnePrenom.getText();
            Date dateNaissance = personneDateNaissance.getDate();
            String intituleParcours = listeParcours.getSelectedItem().toString();
            Departement departementEnseignant = (Departement) listeDepartements.getSelectedItem();
            int id = 0;

            int RecupBox = listeStatut.getSelectedIndex();
            switch(RecupBox) {
                case 0:

                        id = 0;
                        controleur.creerEtudiant(nom,prenom,dateNaissance,new Formation(intituleParcours));
                    break;
                case 1:
                    try {
                        id = Integer.parseInt(personneId.getText());
                        controleur.creerEnseignant(nom,prenom,dateNaissance, departementEnseignant);
                    } catch (NumberFormatException ez) {
                        System.out.println("Format Nombre invalide( rentrer un nombre)");
                    }
                    break;
                case 2:
            }

            //test validité d'un nombre


            //controleur.afficherPersonne(new Personne(nom,prenom,age,statut));


        }
    }

    class DeleteAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            personneNom.setText("");
            personnePrenom.setText("");
            personneId.setText("");
        }
    }

    //Marche pas pour l'instant
    class SupAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // personesEnregistres.remove(listePersonnes.getSelectedIndex());

        }
    }



    //Modele de liste permetant de manipuler le contenu d'une Jlist
    public class Jmodel extends DefaultListModel implements Observer {

        @Override
        public void update(Observable observable, Object o) {
            //System.out.println(o.toString());
            this.add(0,o.toString());

            System.out.println(this);

            //System.out.println(modele.getListPersonnes());
        }
    }

}
