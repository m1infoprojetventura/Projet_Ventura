package fr.univtln.aguard074.FenetreAdmin;

import com.toedter.calendar.JDateChooser;
import fr.univtln.group_aha.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
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
    //Tmodels
    private TmodelEtudiant tmodelEtudiant;
    private TmodelEnseignant tmodelEnseignant;
    private TmodelSalle tmodelSalle;

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
    private JButton addBoutonFormulaire;
    private JButton cancelBoutonFormulaire;
    private JButton updateBoutonFormulaire;
    // En attendant de trouver autre chose
    private int identifiantPersonne;
    private int identifiantSalle;



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

        tmodelEtudiant = new TmodelEtudiant(modele.getEtudiantsList());
        tmodelEnseignant = new TmodelEnseignant(modele.getEnseignantsList());
        tmodelSalle = new TmodelSalle(modele.getSallesList());


        ArrayList<Etudiant> etudiants = controleur.getEtudiants();
        ArrayList<Enseignant> enseignants = controleur.getEnseignants();

        this.modele.addObserver(tmodelEtudiant);
        this.modele.addObserver(tmodelEnseignant);
        this.modele.addObserver(tmodelSalle);

        this.setContentPane(globalPane());


        //this.setContentPane(panelOnglet);
        this.setVisible(true);
    }

    private JPanel globalPane() {
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

    private JPanel getFormStudentPanel() {
        JPanel container = new JPanel();
        JPanel panelFormAddPersonne = new JPanel();
        JPanel panelBouton = new JPanel();
        panelBouton.setLayout(new BoxLayout(panelBouton, BoxLayout.X_AXIS));

        panelBouton.setBounds(7, 246, 290, 41);
        panelBouton.setBackground(Color.WHITE);
        // ici on rajouter le tabbedPane
        JTabbedPane paneltableStudent = getListTable();
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        container.setBackground(Color.WHITE);
        container.setLayout(null);

        panelFormAddPersonne.setBorder(lineborder);
        panelFormAddPersonne.setBackground(Color.WHITE);
        panelFormAddPersonne.setBounds(46, 60, 304, 304);
        panelFormAddPersonne.setLayout(null);

        JLabel label = new JLabel("Nom :");
        label.setBounds(10, 29, 46, 13);
        panelFormAddPersonne.add(label);

        personneNom = new JTextField();
        personneNom.setColumns(10);
        personneNom.setBounds(120, 26, 132, 19);
        panelFormAddPersonne.add(personneNom);

        JLabel label_1 = new JLabel("Prénom : ");
        label_1.setBounds(10, 58, 57, 13);
        panelFormAddPersonne.add(label_1);
        personnePrenom = new JTextField();
        personnePrenom.setColumns(10);
        personnePrenom.setBounds(120, 55, 132, 19);
        panelFormAddPersonne.add(personnePrenom);


        JLabel label_2 = new JLabel("Statut : ");
        label_2.setBounds(10, 117, 86, 13);
        panelFormAddPersonne.add(label_2);
        listeStatut = new JComboBox(new String[]{ "Étudiant", "Enseignant"});
        listeStatut.setBounds(120, 113, 132, 21);
        panelFormAddPersonne.add(listeStatut);

        JLabel label_4 = new JLabel("Formation:");
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


        addBoutonFormulaire = new JButton("Ajouter");
        // addBouton.setBounds(23, 246, 85, 21);
        panelBouton.add(addBoutonFormulaire);
        panelBouton.add(Box.createHorizontalStrut(10));

        cancelBoutonFormulaire = new JButton("Annuler");
        //cancelBouton.setBounds(69, 246, 85, 38);
        panelBouton.add(cancelBoutonFormulaire);
        panelBouton.add(Box.createHorizontalStrut(10));

        updateBoutonFormulaire = new JButton("Modifier");
        panelBouton.add(updateBoutonFormulaire);
        updateBoutonFormulaire.setEnabled(false);

        panelFormAddPersonne.add(panelBouton);
        AddAction addAction = new AddAction();
        addBoutonFormulaire.addActionListener(addAction);
        ResetAction resetAction = new ResetAction();
        cancelBoutonFormulaire.addActionListener(resetAction);
        UpdAction updAction = new UpdAction();
        updateBoutonFormulaire.addActionListener(updAction);

        // on rajoute la liste des étudiant
        container.add(paneltableStudent);
        //on rajoute le formulaire des étudiant
        container.add(panelFormAddPersonne);

        return container;
    }

    private JPanel getListStudent() {
        JTable tableEtudiants = new JTable(tmodelEtudiant);


        //celui la le panel qui regroupe la JTable et son header
        JPanel panelTableEtudiant = new JPanel();
        //scrollPane pour le JTable
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(tableEtudiants);

        // englobe le Jtable avec son header avec les bouton car le premier est un flowLayout , il va mettre tous mettre a coté
        JPanel panelTabEtudiant = new JPanel();
        //JPanel panelTabEnseignant = new JPanel();

        panelTableEtudiant.setLayout(new BoxLayout(panelTableEtudiant, BoxLayout.Y_AXIS));

        panelTabEtudiant.setBackground(Color.WHITE);
        panelTableEtudiant.setBackground(Color.WHITE);

        panelTabEtudiant.setLayout(null);


        panelTableEtudiant .setBounds(0, 0, 575, 320);
        panelTableEtudiant.add(tableEtudiants.getTableHeader());
        panelTableEtudiant.add(scroll );
        panelTabEtudiant.add(panelTableEtudiant);

        JButton suppBouton = new JButton("Supprimer");
        suppBouton.setBounds(104, 346, 126, 32);
        panelTabEtudiant.add(suppBouton);

        JButton updateBouton = new JButton("Modifier");
        updateBouton.setEnabled(false);
        updateBouton.setBounds(267, 346, 126, 32);
        panelTabEtudiant.add(updateBouton);

        suppBouton.addActionListener(actionEvent -> {
            int ints[] = tableEtudiants.getSelectedRows();

            // C'est pourri, à corriger (fait pour la frime) Marche pas
            //Etudiant etudiants[] = (Etudiant[]) ints.stream().map(tmodelEtudiant::getRowValue).toArray();
            List<Etudiant> etudiants = new ArrayList();

            for(int i: ints) {
                etudiants.add(tmodelEtudiant.getRowValue(i));
            }

            for(Etudiant etudiant: etudiants) {
                controleur.suprimerEtudiant(etudiant);
            }
        });

        updateBouton.addActionListener(actionEvent -> {
            int i = tableEtudiants.getSelectedRow();
            Etudiant etudiant = tmodelEtudiant.getRowValue(i);
            identifiantPersonne = etudiant.getId();
            personneNom.setText(etudiant.getNom());
            personnePrenom.setText(etudiant.getPrenom());
            personneDateNaissance.setDate(etudiant.getDate_naissance());
            listeFormation.setSelectedItem(etudiant.getFormation());
            listeStatut.setSelectedIndex(0);
            addBoutonFormulaire.setEnabled(false);
            updateBoutonFormulaire.setEnabled(true);
            listeStatut.setEnabled(false);
        });

        // ListSlectionModel c'est un modele qui surveille la selectionne sur les listes
        ListSelectionModel model = tableEtudiants.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (model.isSelectionEmpty()){
                    updateBouton.setEnabled(false);
                }else {
                    updateBouton.setEnabled(true);
                }
            }
        });


        return panelTabEtudiant;

    }

    private JPanel getListTeacher() {
        JTable tableEnseignant = new JTable(tmodelEnseignant);
        JScrollPane scroll = new JScrollPane(tableEnseignant);
        //celui la le panel qui regroupe la JTable et son header
        JPanel panelTableEnseignant = new JPanel();
        // englobe le Jtable avec son header avec les bouton car le premier est un flowLayout , il va mettre tous mettre a coté
        JPanel panelTabEnseignant = new JPanel();
        //JPanel panelTabEnseignant = new JPanel();

        panelTableEnseignant.setLayout(new BoxLayout(panelTableEnseignant, BoxLayout.Y_AXIS));

        panelTabEnseignant.setBackground(Color.WHITE);
        panelTableEnseignant.setBackground(Color.WHITE);

        panelTabEnseignant.setLayout(null);


        panelTableEnseignant.setBounds(0, 0, 575, 320);
        panelTableEnseignant.add(tableEnseignant.getTableHeader());
        panelTableEnseignant.add(scroll);
        panelTabEnseignant.add(panelTableEnseignant);

        JButton suppBouton = new JButton("Supprimer");
        suppBouton.setBounds(104, 346, 126, 32);
        panelTabEnseignant.add(suppBouton);

        JButton updateBouton = new JButton("Modifier");
        updateBouton.setEnabled(false);
        updateBouton.setBounds(267, 346, 126, 32);
        panelTabEnseignant.add(updateBouton);

        suppBouton.addActionListener(actionEvent -> {
            int ints[] = tableEnseignant.getSelectedRows();
            List<Enseignant> enseignants = new ArrayList();
            for(int i: ints) {
                enseignants.add(tmodelEnseignant.getRowValue(i));
            }

            for(Enseignant enseignant: enseignants) {
                controleur.suprimerEnseignant(enseignant);
            }
        });

        updateBouton.addActionListener(actionEvent -> {
            int i = tableEnseignant.getSelectedRow();
            Enseignant enseignant = tmodelEnseignant.getRowValue(i);
            identifiantPersonne = enseignant.getId();

            personneNom.setText(enseignant.getNom());
            personnePrenom.setText(enseignant.getPrenom());
            personneDateNaissance.setDate(enseignant.getDate_naissance());
            listeDepartements.setSelectedItem(enseignant.getDepartement());
            listeStatut.setSelectedIndex(1);
            addBoutonFormulaire.setEnabled(false);
            updateBoutonFormulaire.setEnabled(true);
            listeStatut.setEnabled(false);
        });
        ListSelectionModel model = tableEnseignant.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (model.isSelectionEmpty()){
                    updateBouton.setEnabled(false);
                }else {
                    updateBouton.setEnabled(true);
                }
            }
        });

        return panelTabEnseignant;
    }

    private JTabbedPane getListTable() {
        JTabbedPane panelOnglet = new JTabbedPane(JTabbedPane.TOP);

        panelOnglet.setBounds(372, 48, 580, 415);

        panelOnglet.addTab("Enseignants", null, getListTeacher());
        panelOnglet.addTab("Etudiants", null, getListStudent());

        return panelOnglet;
    }

    private JPanel getformSallePanel() {
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

        JButton addSalleBouton = new JButton("Ajouter");
        addSalleBouton.setBounds(3, 285, 85, 21);
        panelFormAddSalle.add(addSalleBouton);

        JButton modificationSalleBouton = new JButton("modifier");
        modificationSalleBouton.setBounds(190, 285, 85, 21);
        modificationSalleBouton.setEnabled(false);
        panelFormAddSalle.add(modificationSalleBouton);



        addSalleBouton.addActionListener(actionEvent -> {
            List<Materiel.TypeMateriel> listMateriel = new ArrayList<>();
            String nom = salleNumero.getText();
            int capacite = Integer.parseInt(salleNbrPlaces.getText());
            if (ordi_Materiel.isSelected())
                listMateriel.add(Materiel.TypeMateriel.ORDINATEUR);
            if (imprimante_Materiel.isSelected())
                listMateriel.add(Materiel.TypeMateriel.IMPRIMANTE);
            if (tableau_Materiel.isSelected())
                listMateriel.add(Materiel.TypeMateriel.TABLEAU_TACTIL);
            if (video_Materiel.isSelected())
                listMateriel.add(Materiel.TypeMateriel.VIDEO_PROJECTEUR);

            System.out.println(nom);
            System.out.println(capacite);
            controleur.creerSalle(nom,capacite,listMateriel);

        });






        JButton button_1 = new JButton("Annuler");
        button_1.setBounds(95, 285, 85, 21);
        panelFormAddSalle.add(button_1);

        JPanel panelListeSalles = new JPanel();
        panelListeSalles.setLayout(null);
        panelListeSalles.setBackground(Color.WHITE);
        panelListeSalles.setBorder(lineborder);

        panelListeSalles.setBounds(325, 39, 620, 361);
        panel2.add(panelListeSalles);

        JButton supSalleBouton = new JButton("Supprimer");
        supSalleBouton.setBounds(0, 330, 240, 31);
        panelListeSalles.add(supSalleBouton);

        JButton modifSalleBouton = new JButton("Modifier");
        modifSalleBouton.setBounds(330, 330, 240, 31);
        panelListeSalles.add(modifSalleBouton);







        JTable tableSalles = new JTable(tmodelSalle);
        //celui la le panel qui regroupe la JTable et son header
        JPanel panelTabSalles = new JPanel();
        //scrollPane pour le JTable
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(tableSalles);




        panelTabSalles.setLayout(new BoxLayout(panelTabSalles, BoxLayout.Y_AXIS));

        panelTabSalles.setBackground(Color.white);
        panelListeSalles.setBackground(Color.white);




        panelTabSalles.setBounds(0, 0, 600, 320);
        panelTabSalles.add(tableSalles.getTableHeader());
        panelTabSalles.add(scroll );
        panelListeSalles.add(panelTabSalles);



        supSalleBouton.addActionListener(actionEvent -> {
            int ints[] = tableSalles.getSelectedRows();
            List<Salle> salles = new ArrayList();
            for(int i: ints) {
                salles.add(tmodelSalle.getRowValue(i));
            }
            for(Salle salle: salles) {
                controleur.suprimerSalle(salle);
            }
        });


        //BOUTON EN DESSOUS DE LA TABLE
        modifSalleBouton.addActionListener(actionEvent -> {
            int i = tableSalles.getSelectedRow();
            Salle salle = tmodelSalle.getRowValue(i);
            for (Materiel.TypeMateriel mat: salle.getMateriels()) {
                if (mat.equals(Materiel.TypeMateriel.IMPRIMANTE)) {imprimante_Materiel.setSelected(true); }
                if (mat.equals(Materiel.TypeMateriel.ORDINATEUR)) {ordi_Materiel.setSelected(true); }
                if (mat.equals(Materiel.TypeMateriel.TABLEAU_TACTIL)) {tableau_Materiel.setSelected(true); }
                if (mat.equals(Materiel.TypeMateriel.VIDEO_PROJECTEUR)) {video_Materiel.setSelected(true); }
            }

            identifiantSalle = salle.getId();
            salleNumero.setText(salle.getNom());
            salleNbrPlaces.setText(Integer.toString(salle.getCapacite()));
            addSalleBouton.setEnabled(false);
            modificationSalleBouton.setEnabled(true);
        });

        modificationSalleBouton.addActionListener(actionEvent -> {
            String nom = salleNumero.getText();
            String capacite = salleNbrPlaces.getText();
            List<Materiel.TypeMateriel> listMateriel = new ArrayList<>();
            if (ordi_Materiel.isSelected())
                listMateriel.add(Materiel.TypeMateriel.ORDINATEUR);
            if (imprimante_Materiel.isSelected())
                listMateriel.add(Materiel.TypeMateriel.IMPRIMANTE);
            if (tableau_Materiel.isSelected())
                listMateriel.add(Materiel.TypeMateriel.TABLEAU_TACTIL);
            if (video_Materiel.isSelected())
                listMateriel.add(Materiel.TypeMateriel.VIDEO_PROJECTEUR);

            salleNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            salleNbrPlaces.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            if (nom.equals("")) {
                salleNumero.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            if (capacite.equals("")) {
                salleNbrPlaces.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            if (!(capacite.equals("") || nom.equals(""))) {
                Salle salle = new Salle(nom,listMateriel,Integer.parseInt(capacite),identifiantSalle);
                controleur.modifierSalle(salle);
            }
            modificationSalleBouton.setEnabled(false);

            addSalleBouton.setEnabled(true);

        });


        return panel2;
    }

    class AddAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //controleur.afficherPersonne(new Personne("michel","ll",45, Personne.Statut.ETUDIANT));
            String nom = personneNom.getText();
            String prenom = personnePrenom.getText();

            personneNom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            personnePrenom.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            if (nom.equals("")) {
                personneNom.setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            if (prenom.equals("")) {
                personnePrenom.setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            if (!(prenom.equals("") || nom.equals(""))) {
                Date dateNaissance = personneDateNaissance.getDate();
                Formation formation = (Formation) listeFormation.getSelectedItem();
                Departement departementEnseignant = (Departement) listeDepartements.getSelectedItem();
                int id = 0;

                int RecupBox = listeStatut.getSelectedIndex();
                switch (RecupBox) {
                    case 0:
                        id = 0;
                        controleur.creerEtudiant(nom, prenom, dateNaissance, formation);
                        break;
                    case 1:
                        try {
                            controleur.creerEnseignant(nom, prenom, dateNaissance, departementEnseignant);
                        } catch (NumberFormatException ez) {
                            System.out.println("Format Nombre invalide( rentrer un nombre)");
                        }
                        break;
                    case 2:
                }
            }

            //test validité d'un nombre
            //controleur.afficherPersonne(new Personne(nom,prenom,age,statut));
        }
    }

    class UpdAction implements ActionListener {
        // Trouver une méthode pour généraliser tout ça (je parle de AddAcrion et UpdAction
        // ils font la même chose à quelques détails près

        public void actionPerformed(ActionEvent e) {
            //controleur.afficherPersonne(new Personne("michel","ll",45, Personne.Statut.ETUDIANT));
            String nom = personneNom.getText();
            String prenom = personnePrenom.getText();

            personneNom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            personnePrenom.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            if (nom.equals("")) {
                personneNom.setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            if (prenom.equals("")) {
                personnePrenom.setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            if (!(prenom.equals("") || nom.equals(""))) {
                Date dateNaissance = personneDateNaissance.getDate();
                Formation formation = (Formation) listeFormation.getSelectedItem();
                Departement departementEnseignant = (Departement) listeDepartements.getSelectedItem();
                int id = 0;

                int RecupBox = listeStatut.getSelectedIndex();
                switch (RecupBox) {
                    case 0:
                        id = 0;
                        Etudiant etudiant = new Etudiant(identifiantPersonne, nom, prenom, dateNaissance, formation);
                        controleur.modifierEtudiant(etudiant);
                        break;
                    case 1:
                        try {
                            Enseignant enseignant = new Enseignant(identifiantPersonne, nom, prenom,
                                                                    dateNaissance, departementEnseignant);

                            controleur.modifierEnseignant(enseignant);
                        } catch (NumberFormatException ez) {
                            System.out.println("Format Nombre invalide( rentrer un nombre)");
                        }
                        break;
                    case 2:
                }
            }

            updateBoutonFormulaire.setEnabled(false);
            listeStatut.setEnabled(true);
            addBoutonFormulaire.setEnabled(true);
            //test validité d'un nombre
            //controleur.afficherPersonne(new Personne(nom,prenom,age,statut));
        }
    }

    class ResetAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            personneNom.setText("");
            personnePrenom.setText("");
            personneId.setText("");
        }
    }

    //Jtabmodel etudiant
    public class TmodelEtudiant extends AbstractTableModel implements Observer{

        private final String[] entetes = {"ID", "Nom", "Prénom", "Date de naissance", "Login","Formation"};
        private final List<Etudiant> etudiants;

        public TmodelEtudiant(List<Etudiant> etudiants) {
            this.etudiants = etudiants;
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

        public Etudiant getRowValue(int i) {
            Etudiant etudiant = etudiants.get(i);

            return etudiant;
        }

        @Override
        public Object getValueAt(int i, int i1) {
            Etudiant etudiant = getRowValue(i);
            Object[] o = etudiant.getAttributs().toArray();
            return o[i1];
        }

}

    public class TmodelEnseignant extends  AbstractTableModel implements Observer {
        private final String[] entetes = {"id", "nom", "prenom", "date naissance", "login", "departement"};
        private final List<Enseignant> enseignants;

        public TmodelEnseignant(List<Enseignant> enseignants) {
            this.enseignants = enseignants;
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

        public Enseignant getRowValue(int i) {
            return enseignants.get(i);
        }

        @Override
        public Object getValueAt(int i, int i1) {
            Enseignant enseignant = getRowValue(i);
            Object[] o = enseignant.getAttributs().toArray();
            return o[i1];
        }
    }
    public class TmodelSalle extends  AbstractTableModel implements Observer {
        private final String[] entetes = {"id","salle", "capacite", "Matos"};
        private final List<Salle> salles;

        public TmodelSalle(List<Salle> salles) {
            this.salles = salles;
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
            if(salles != null)
                return salles.size();
            else
                return 0;
        }

        public Salle getRowValue(int i) {
            Salle salle = salles.get(i);

            return salle;
        }

        @Override
        public Object getValueAt(int i, int i1) {
            Salle salle = getRowValue(i);
            //System.out.println(salle);
            Object[] o = salle.getAttributs().toArray();
            return o[i1];
        }
    }
}

