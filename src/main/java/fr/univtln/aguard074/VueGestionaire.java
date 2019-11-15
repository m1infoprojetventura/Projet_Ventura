package fr.univtln.aguard074;

import com.toedter.calendar.JDateChooser;
import fr.univtln.group_aha.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ArrayList;
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
    private JComboBox listeFormation;
    JComboBox listeDepartements;
    private JList listePersonnes;
    private Jmodel personesEnregistres = new Jmodel();



    public VueGestionaire(Icontroleur controleur, Modele modele){
        super();
        this.modele = modele;
        this.controleur = controleur;
        Init();//On initialise notre fenêtre

    }


    private void Init() {
        setTitle("Gestionaire emploi du temps"); //On donne un titre à l'application
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        this.setBounds(100, 100, 789, 516);

        this.modele.addObserver(personesEnregistres);
        this.setContentPane(saisieInfoPersonne());
        this.setVisible(true);
    }



    private JPanel saisieInfoPersonne(){
        JPanel contentPane;
        JPanel panel2;
        /**
         * @wbp.nonvisual location=-39,254
         */
        JTextField textField = new JTextField();

        textField.setColumns(10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 976, 727);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel parentPanel = new JPanel();
        parentPanel.setBounds(0, 28, 962, 662);
        contentPane.add(parentPanel);
        CardLayout c1 = new CardLayout();
        parentPanel.setLayout(c1);

        JPanel panel1 = new JPanel();
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        panel1.setBackground(Color.WHITE);
        parentPanel.add(panel1, "panel1");
        panel1.setLayout(null);

        JPanel panelFormAddPersonne = new JPanel();
        panelFormAddPersonne.setBorder(lineborder);

        panelFormAddPersonne.setLayout(null);
        panelFormAddPersonne.setBackground(Color.WHITE);
        panelFormAddPersonne.setBounds(46, 60, 304, 304);
        panel1.add(panelFormAddPersonne);

        personneNom = new JTextField();
        personneNom.setColumns(10);
        personneNom.setBounds(120, 26, 132, 19);
        panelFormAddPersonne.add(personneNom);

        personnePrenom = new JTextField();
        personnePrenom.setColumns(10);
        personnePrenom.setBounds(120, 55, 132, 19);
        panelFormAddPersonne.add(personnePrenom);

        listeStatut = new JComboBox(new String[]{ "Étudiant", "Enseignant"});
        listeStatut.setBounds(120, 113, 132, 21);
        panelFormAddPersonne.add(listeStatut);

        JLabel label = new JLabel("Nom :");
        label.setBounds(10, 29, 46, 13);
        panelFormAddPersonne.add(label);

        JLabel label_1 = new JLabel("Prenom : ");
        label_1.setBounds(10, 58, 57, 13);
        panelFormAddPersonne.add(label_1);

        JLabel label_2 = new JLabel("Status : ");
        label_2.setBounds(10, 117, 46, 13);
        panelFormAddPersonne.add(label_2);

        listeFormation = new JComboBox(this.controleur.getFormations().toArray());
        listeFormation.setBounds(120, 144, 132, 21);
        panelFormAddPersonne.add(listeFormation);

        JLabel label_3 = new JLabel("Date de Naissance");
        label_3.setBounds(10, 87, 120, 13);
        panelFormAddPersonne.add(label_3);

        JLabel label_4 = new JLabel("Parcours :");
        label_4.setBounds(10, 148, 86, 13);
        panelFormAddPersonne.add(label_4);

        JLabel label_5 = new JLabel("Departement :");
        label_5.setBounds(10, 185, 86, 13);
        panelFormAddPersonne.add(label_5);

        listeDepartements = new JComboBox(controleur.getDepartements().toArray());
        listeDepartements.setBounds(120, 181, 132, 21);
        panelFormAddPersonne.add(listeDepartements);



        JButton AddBouton = new JButton("Ajouter");
        AddBouton.setBounds(45, 246, 85, 21);
        panelFormAddPersonne.add(AddBouton);

        JButton CancelBouton = new JButton("Annuler");
        CancelBouton.setBounds(152, 246, 85, 21);
        panelFormAddPersonne.add(CancelBouton);

        personneDateNaissance = new JDateChooser();
        personneDateNaissance.setBounds(120, 84, 132, 19);
        panelFormAddPersonne.add(personneDateNaissance);


        JPanel panelListePersonne = new JPanel();
        panelListePersonne.setBackground(Color.WHITE);
        panelListePersonne.setBorder(lineborder);
        panelListePersonne.setLayout(null);
        panelListePersonne.setBounds(570, 60, 240, 361);
        panel1.add(panelListePersonne);

        JButton suppBouton = new JButton("Supprimer");
        suppBouton.setBounds(0, 330, 128, 31);
        panelListePersonne.add(suppBouton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 220, 310);

        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);

        listePersonnes = new JList(personesEnregistres);
        scrollPane.setViewportView(listePersonnes);
        listePersonnes.setBackground(Color.white);
        listePersonnes.setLayoutOrientation(JList.VERTICAL);
        panelListePersonne.add(scrollPane);

        JButton updateBouton = new JButton("Modifier");
        updateBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        updateBouton.setBounds(117, 330, 123, 31);
        panelListePersonne.add(updateBouton);


        panel2 = formSalle();
        panel2.setBackground(Color.white);
        parentPanel.add(panel2, "panel2");

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 962, 22);
        contentPane.add(menuBar);

        JMenu mnNewMenu = new JMenu("Ressources");
        menuBar.add(mnNewMenu);

        JMenuItem itemListPersonne = new JMenuItem("Liste des Personnes");
        itemListPersonne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c1.show(parentPanel, "panel1");
            }
        });
        mnNewMenu.add(itemListPersonne);

        JMenuItem itemListsalles = new JMenuItem("Liste des salles");
        itemListsalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c1.show(parentPanel, "panel2");
            }
        });
        mnNewMenu.add(itemListsalles);
        AddAction AddAction = new AddAction();
        DeleteAction deleteAction = new DeleteAction();
        AddBouton.addActionListener(AddAction);
        CancelBouton.addActionListener(deleteAction);
        return contentPane;

    }
    private JPanel formSalle(){
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        JTextField textField_1;
        JTextField textField_2;
        JPanel panelFormAddSalle = new JPanel();
        panelFormAddSalle.setBorder(lineborder);
        panelFormAddSalle.setBackground(Color.WHITE);
        panelFormAddSalle.setBounds(33, 65, 279, 335);
        panel2.add(panelFormAddSalle);
        panelFormAddSalle.setLayout(null);

        JLabel lblNum = new JLabel("Num :");
        lblNum.setFont(new Font("Dialog", Font.PLAIN, 15));
        lblNum.setBounds(10, 42, 93, 18);
        panelFormAddSalle.add(lblNum);

        JLabel lblNbrDePlaces = new JLabel("nbr de places :");
        lblNbrDePlaces.setFont(new Font("Dialog", Font.PLAIN, 15));
        lblNbrDePlaces.setBounds(10, 87, 114, 18);
        panelFormAddSalle.add(lblNbrDePlaces);

        JLabel lblS = new JLabel("Mat\u00E9riels :");
        lblS.setFont(new Font("Dialog", Font.PLAIN, 15));
        lblS.setBounds(10, 132, 114, 18);
        panelFormAddSalle.add(lblS);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(137, 44, 132, 19);
        panelFormAddSalle.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(137, 89, 132, 19);
        panelFormAddSalle.add(textField_2);

        JCheckBox chckbxVideoProjecteur = new JCheckBox("video projecteur");
        chckbxVideoProjecteur.setBounds(137, 145, 132, 21);
        panelFormAddSalle.add(chckbxVideoProjecteur);

        JCheckBox chckbxPc = new JCheckBox("ordinateurs");
        chckbxPc.setBounds(137, 169, 132, 21);
        panelFormAddSalle.add(chckbxPc);

        JCheckBox chckbxImprimante = new JCheckBox("Tableau tactile");
        chckbxImprimante.setBounds(137, 192, 132, 21);
        panelFormAddSalle.add(chckbxImprimante);

        JCheckBox checkBox = new JCheckBox("imprimante");
        checkBox.setBounds(137, 215, 132, 21);
        panelFormAddSalle.add(checkBox);

        JButton button = new JButton("Ajouter");
        button.setBounds(39, 285, 85, 21);
        panelFormAddSalle.add(button);

        JButton button_1 = new JButton("Annuler");
        button_1.setBounds(155, 285, 85, 21);
        panelFormAddSalle.add(button_1);

        JPanel panelListeSalles = new JPanel();
        panelListeSalles.setLayout(null);
        panelListeSalles.setBackground(Color.WHITE);
        panelListeSalles.setBorder(lineborder);

        panelListeSalles.setBounds(627, 39, 240, 361);
        panel2.add(panelListeSalles);

        JButton button_2 = new JButton("Supprimer");
        button_2.setBounds(0, 330, 240, 31);
        panelListeSalles.add(button_2);
        String[] data = {"salle 101","salle 102","salle 103","salle 104","salle 105","salle 106"};
        JList listeSalles = new JList(data);
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setViewportView(listeSalles);
        listeSalles.setLayoutOrientation(JList.VERTICAL);
        scrollPane_1.setBorder(null);
        scrollPane_1.setBackground(Color.WHITE);
        scrollPane_1.setBounds(10, 10, 220, 310);
        panelListeSalles.add(scrollPane_1);

        return panel2;
    }

    class AddAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //On affiche le chiffre en plus dans le label
            String str = ((JButton)e.getSource()).getText();
            //controleur.afficherPersonne(new Personne("michel","ll",45, Personne.Statut.ETUDIANT));
            String nom = personneNom.getText();
            String prenom = personnePrenom.getText();
            Date dateNaissance = personneDateNaissance.getDate();
            Formation formation = (Formation) listeFormation.getSelectedItem();
            Departement departementEnseignant = (Departement) listeDepartements.getSelectedItem();
            int id = 0;

            int RecupBox = listeStatut.getSelectedIndex();
            System.out.println(RecupBox);
            switch(RecupBox) {
                case 0:
                        id = 0;
                        controleur.creerEtudiant(nom,prenom,dateNaissance, formation);
                    break;
                case 1:
                    try {
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
        public class Jmodel extends DefaultListModel  implements Observer {
        @Override
        public void update(Observable observable, Object o) {
            //System.out.println(o.toString());

            // Vaut mieux utilisuer le polymorphisme ici
            if(o instanceof Etudiant) {
                if(modele.trouverEtudiant(((Etudiant) o).getId())) {
                    addElement( o);
                }
            }

            else if(o instanceof Enseignant) {
                if(modele.trouverEtudiant(((Enseignant) o).getId())) {
                    addElement((Etudiant) o);
                }

                else
                    removeElement(o);
            }

            System.out.println(this);

            //System.out.println(modele.getListPersonnes());
        }
    }
}
