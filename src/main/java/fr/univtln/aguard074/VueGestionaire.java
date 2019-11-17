package fr.univtln.aguard074;

import com.toedter.calendar.JDateChooser;
import fr.univtln.group_aha.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ArrayList;
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
    private JComboBox listeFormation;
    JComboBox listeDepartements;
    private JList listePersonnes;
    private Jmodel personesEnregistres;
    private TmodelEtudiant tmodelEtudiant;
    private TmodelEnseignant tmodelEnseignant;

    private JPanel jp1 = new JPanel();
    private JPanel jp2 = new JPanel();
    private JLabel jl1 = new JLabel("un label pour le panel1");
    private JLabel jl2 = new JLabel("un label pour le panel2");
    JTabbedPane panelOnglet = new JTabbedPane();

    private JTextField salleNumero;
    private JTextField salleNbrPlaces;
    private JCheckBox video_Materiel;
    private JCheckBox ordi_Materiel;
    private JCheckBox tableau_Materiel;
    private JCheckBox imprimante_Materiel;



    public VueGestionaire(Icontroleur controleur, Modele modele){
        super();
        this.controleur = controleur;
        this.modele = modele;
        Init();//On initialise notre fenêtre

    }


    private void Init() {
        setTitle("Gestionaire emploi du temps"); //On donne un titre à l'application
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        this.setBounds(100, 100, 789, 516);

        personesEnregistres = new Jmodel();
        tmodelEtudiant = new TmodelEtudiant(modele.getEtudiantsList());
        tmodelEnseignant = new TmodelEnseignant(modele.getEnseignantsList());


        ArrayList<Etudiant> etudiants = controleur.getEtudiants();
        ArrayList<Enseignant> enseignants = controleur.getEnseignants();

        personesEnregistres.ajouter(controleur.getEtudiants());
        personesEnregistres.ajouter(controleur.getEnseignants());

        //tmodelEtudiant.ajouter(controleur.getEtudiants());
        //tmodelEtudiant.ajouter(controleur.getEnseignants());



        this.modele.addObserver(personesEnregistres);
        this.modele.addObserver(tmodelEtudiant);
        this.modele.addObserver(tmodelEnseignant);

        this.setContentPane(globalPane());


        //this.setContentPane(panelOnglet);
        this.setVisible(true);
    }

    private JPanel globalPane(){
        JPanel globalPane = new JPanel();
        // le panel qui contient le cardlayout
        JPanel parentPane = new JPanel();
        JPanel childPane1 =getFormStudentPanel() ;
        JPanel childPane2 = getformSallePanel();
        JMenuBar menuBar = new JMenuBar();
        childPane2.setBackground(Color.white);
        globalPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(globalPane);
        globalPane.setLayout(null);
        setBounds(100, 100, 976, 727);

        // le parentPane est le panel qui englobe tous les autres , cardlayout
        parentPane.setBounds(0, 28, 962, 662);
        CardLayout c1 = new CardLayout();
        parentPane.setLayout(c1);

        // le Menu bar de navigation
        menuBar.setBounds(0, 0, 962, 22);

        JMenu mnNewMenu = new JMenu("Ressources");
        menuBar.add(mnNewMenu);

        JMenuItem itemListPersonne = new JMenuItem("Liste des Personnes");
        itemListPersonne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c1.show(parentPane, "childPane1");
            }
        });
        mnNewMenu.add(itemListPersonne);

        JMenuItem itemListsalles = new JMenuItem("Liste des salles");
        itemListsalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c1.show(parentPane, "childPane2");
            }
        });
        mnNewMenu.add(itemListsalles);

        // on rajoute les différents containers dans le parent , on associe un nom pour pouvoir les trouver aprés .
        parentPane.add(childPane1, "childPane1");
        parentPane.add(childPane2, "childPane2");
        // le content pane est le panel principale de l'application , qui englobe le parentPane et le Menu de navigation
        globalPane.add(parentPane);
        // on rajoute le menuBar
        globalPane.add(menuBar);

        return globalPane;
    }

    private JPanel getFormStudentPanel(){
        JPanel container = new JPanel();
        JPanel panelFormAddPersonne = new JPanel();
        // ici on rajouter le tabbedPane
        JTabbedPane paneltableStudent = getListStudenttable();
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        container.setBackground(Color.WHITE);
        container.setLayout(null);

        panelFormAddPersonne.setBorder(lineborder);
        panelFormAddPersonne.setLayout(null);
        panelFormAddPersonne.setBackground(Color.WHITE);
        panelFormAddPersonne.setBounds(46, 60, 304, 304);



        JLabel label = new JLabel("Nom :");
        label.setBounds(10, 29, 46, 13);
        panelFormAddPersonne.add(label);

        personneNom = new JTextField();
        personneNom.setColumns(10);
        personneNom.setBounds(120, 26, 132, 19);
        panelFormAddPersonne.add(personneNom);

        JLabel label_1 = new JLabel("Prenom : ");
        label_1.setBounds(10, 58, 57, 13);
        panelFormAddPersonne.add(label_1);
        personnePrenom = new JTextField();
        personnePrenom.setColumns(10);
        personnePrenom.setBounds(120, 55, 132, 19);
        panelFormAddPersonne.add(personnePrenom);


        JLabel label_2 = new JLabel("Status : ");
        label_2.setBounds(10, 117, 46, 13);
        panelFormAddPersonne.add(label_2);
        listeStatut = new JComboBox(new String[]{ "Étudiant", "Enseignant"});
        listeStatut.setBounds(120, 113, 132, 21);
        panelFormAddPersonne.add(listeStatut);

        JLabel label_4 = new JLabel("Parcours :");
        label_4.setBounds(10, 148, 86, 13);
        panelFormAddPersonne.add(label_4);
        listeFormation = new JComboBox(this.controleur.getFormations().toArray());
        listeFormation.setBounds(120, 144, 132, 21);
        panelFormAddPersonne.add(listeFormation);

        JLabel label_3 = new JLabel("Date de Naissance");
        label_3.setBounds(10, 87, 120, 13);
        panelFormAddPersonne.add(label_3);
        personneDateNaissance = new JDateChooser();
        personneDateNaissance.setBounds(120, 84, 132, 19);
        panelFormAddPersonne.add(personneDateNaissance);

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

        AddAction AddAction = new AddAction();
        AddBouton.addActionListener(AddAction);
        ResetAction resetAction = new ResetAction();
        CancelBouton.addActionListener(resetAction);

        // on rajoute la liste des étudiant
        container.add(paneltableStudent);
        //on rajoute le formulaire des étudiant
        container.add(panelFormAddPersonne);

        return container;
    }
    // revoir la liste des étudiants
    /*private JPanel getListStudentPanel(){
        JPanel panelListePersonne = new JPanel();
        JButton updateBouton = new JButton("Modifier");
        JButton suppBouton = new JButton("Supprimer");
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);


        panelListePersonne.setBackground(Color.WHITE);
        panelListePersonne.setBorder(lineborder);
        panelListePersonne.setLayout(null);
        panelListePersonne.setBounds(570, 60, 240, 361);


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

        updateBouton.setBounds(117, 330, 123, 31);
        panelListePersonne.add(updateBouton);

        SupAction supAction = new SupAction();
        suppBouton.addActionListener(supAction);

        updateBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        return  panelListePersonne;
    }*/

    // renvoir un JTabbedPane étudiant pour le moment
    private JTabbedPane getListStudenttable(){
        JTable tableEtudiants = new JTable(tmodelEtudiant);
        //JTable tableEnseignants = new JTable(tmodelEnseignant);
        JTabbedPane panelOnglet = new JTabbedPane(JTabbedPane.TOP);
        //celui la le panel qui regroupe la JTable et son header
        JPanel panelTableEtudiant = new JPanel();
        // englobe le Jtable avec son header avec les bouton car le premier est un flowLayout , il va mettre tous mettre a coté
        JPanel panelTabEtudiant = new JPanel();
        //JPanel panelTabEnseignant = new JPanel();


        panelTabEtudiant.setBackground(Color.WHITE);
        panelTableEtudiant.setBackground(Color.WHITE);
        panelOnglet.setBounds(372, 48, 580, 415);

        panelTabEtudiant.setLayout(null);


        panelTableEtudiant .setBounds(0, 32, 575, 290);
        panelTableEtudiant.add(tableEtudiants.getTableHeader());
        panelTableEtudiant.add(tableEtudiants );
        panelTabEtudiant.add(panelTableEtudiant);

        JButton suppBouton = new JButton("Supprimer");
        suppBouton.setBounds(104, 346, 126, 32);
        panelTabEtudiant.add(suppBouton);

        JButton updateBouton = new JButton("Modifier");
        updateBouton.setBounds(267, 346, 126, 32);
        panelTabEtudiant.add(updateBouton);

        //panelOnglet.addTab("Enseignants", null, panelTabEnseignant);
        panelOnglet.addTab("Etudiants", null, panelTabEtudiant);

        SupAction supAction = new SupAction();
        suppBouton.addActionListener(supAction);

        return panelOnglet;

    }


    private JPanel getformSallePanel(){
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);

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

        salleNumero = new JTextField();
        salleNumero .setColumns(10);
        salleNumero .setBounds(137, 44, 132, 19);
        panelFormAddSalle.add(salleNumero );

        salleNbrPlaces= new JTextField();
        salleNbrPlaces.setColumns(10);
        salleNbrPlaces.setBounds(137, 89, 132, 19);
        panelFormAddSalle.add(salleNbrPlaces);

        video_Materiel = new JCheckBox("video projecteur");
        video_Materiel.setBounds(137, 145, 132, 21);
        panelFormAddSalle.add(video_Materiel);

        ordi_Materiel = new JCheckBox("ordinateurs");
        ordi_Materiel.setBounds(137, 169, 132, 21);
        panelFormAddSalle.add(ordi_Materiel);

        tableau_Materiel = new JCheckBox("Tableau tactile");
        tableau_Materiel.setBounds(137, 192, 132, 21);
        panelFormAddSalle.add(tableau_Materiel);

        imprimante_Materiel = new JCheckBox("imprimante");
        imprimante_Materiel.setBounds(137, 215, 132, 21);
        panelFormAddSalle.add(imprimante_Materiel);

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

    class ResetAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            personneNom.setText("");
            personnePrenom.setText("");
            personneId.setText("");
        }
    }

    //Marche pas pour l'instant
    class SupAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Object object = listePersonnes.getSelectedValue();
            if(object instanceof Etudiant) {
                controleur.suprimerEtudiant((Etudiant) object);
            }

            if(object instanceof Enseignant) {
                controleur.suprimerEnseignant((Enseignant) object);
            }

            // personesEnregistres.remove(listePersonnes.getSelectedIndex());
        }
    }



    //Modele de liste permetant de manipuler le contenu d'une Jlist
        public class Jmodel extends DefaultListModel  implements Observer {

        public void ajouter(List list) {
            for (Object object : list) {
                addElement(object);
            }
        }

        @Override
        public void update(Observable observable, Object o) {
            //System.out.println(o.toString());

            // Vaut mieux utilisuer le polymorphisme ici
            if (o instanceof Etudiant) {
                if (modele.trouverEtudiant(((Etudiant) o).getId())) {
                    System.out.println("Etudiant ajouté dans liste");
                    addElement(o);
                } else {
                    System.out.println("Etudiantsupprimé dans liste");
                    removeElement(o);
                }
            } else if (o instanceof Enseignant) {
                if (modele.trouverEnseignant(((Enseignant) o).getId())) {
                    addElement(o);
                } else {
                    removeElement(o);
                }
            }

            System.out.println(this);

            //System.out.println(modele.getListPersonnes());
        }
    }
        //Jtabmodel etudiant
        public class TmodelEtudiant extends DefaultTableModel implements Observer{

            private final String[] entetes = {"id", "nom", "prenom", "date naissance", "login","Formation"};
            private final List<Etudiant> etudiants;

            public TmodelEtudiant() {
                super();
                this.etudiants = null;
            }

            public TmodelEtudiant(List<Etudiant> etudiants) {
                this.etudiants = etudiants;
            }

            public void ajouter(List<Etudiant> list) {
                for (Etudiant etudiant : list) {
                    addRow(etudiant.getAttributs().toArray());
                }
            }

            @Override
            public void update(Observable observable, Object o) {

                fireTableDataChanged();
            }

            @Override
            public String getColumnName(int i) {
                return entetes[i];
            }

            @Override
            public int getColumnCount() {
                return entetes.length;
            }

            @Override
            public int getRowCount() {
                if(etudiants != null)
                    return etudiants.size();
                else
                    return 0;
            }

            @Override
            public Object getValueAt(int i, int i1) {
                Etudiant etudiant = etudiants.get(i);
                Object[] o = etudiant.getAttributs().toArray();
                return o[i1];
            }
        }


        public class TmodelEnseignant extends DefaultTableModel implements Observer{
            private final String[] entetes = {"id", "nom", "prenom", "date naissance", "login", "departement"};
            private final List<Enseignant> enseignants;

            public TmodelEnseignant() {
                super();
                this.enseignants = null;
            }

            public TmodelEnseignant(List<Enseignant> enseignants) {
                this.enseignants = enseignants;
            }

            public void ajouter(List<Enseignant> list) {
                for (Enseignant enseignant : list) {
                    addRow(enseignant.getAttributs().toArray());
                }
            }

            @Override
            public void update(Observable observable, Object o) {

                fireTableDataChanged();
            }

            @Override
            public String getColumnName(int i) {
                return entetes[i];
            }

            @Override
            public int getColumnCount() {
                return entetes.length;
            }

            @Override
            public int getRowCount() {
                if(enseignants != null)
                    return enseignants.size();
                else
                    return 0;
            }

            @Override
            public Object getValueAt(int i, int i1) {
                Enseignant enseignant = enseignants.get(i);
                Object[] o = enseignant.getAttributs().toArray();
                return o[i1];
            }
        }

    }

