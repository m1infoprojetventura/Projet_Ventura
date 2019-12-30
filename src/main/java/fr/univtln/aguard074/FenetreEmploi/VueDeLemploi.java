/*
 * Created by JFormDesigner on Wed Nov 27 17:57:55 CET 2019
 */

package fr.univtln.aguard074.FenetreEmploi;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.*;
import fr.univtln.aguard074.FenetreAdmin.VueGestionaire;
import fr.univtln.group_aha.*;

// Temporaire implements Obververs pour observer le modèle emploi du temps
/**
 * @author unknown
 */
public class VueDeLemploi extends JFrame implements Observer {

    ModeleEmploi modele;
    ControleurEmploi controleur;

    // A separer avec le reste plus tard
    /**
     * La variable jourSemaine peut prendre des valeurs allant de 1 à 7 (le 1 correspondant au dimanche: Compatibilité avec
     * Calendar)
     */
    private int jourSemaine;
    private int anneeEnCours;
    private List<Integer> semainesAnnee;
    private int etatConsultation;
    private JPanel[] joursSemainePanel;
    private JLabel[] joursSemaineLabel;
    private String[] joursSemaineString;
    private String[] moisAnnee;
    private List<Seance>[] emploiDuTemps;
    private DefaultComboBoxModel matierecomboBoxModel;
    private DefaultComboBoxModel sallecomboBoxModel;
    private DefaultComboBoxModel comboBoxModel;
    private DefaultComboBoxModel formationComboBoxModel;
    private VueGestionaire.TmodelEnseignant tmodelEnseignant;
    private int idSalle;
    private boolean consultationModifiable= true;
    private static int nomPanel = 0;
    private DefaultTableModel matTable = new DefaultTableModel();
    private String typePersonne ="";
    private String personneAuthentifiee ="";
    private CardLayout c1;
    private JPanel globalpane;
    private VueGestionaire vueGestionaire;
    private VueGestionaire.TmodelSalle tmodelSalle;
    private TmodelReservation tmodelReservation;
    private TmodelSeance tmodelSeance;
    private TmodelReservation tmodelTotalReservation;
    private Boolean sessionActive = false;

    public VueDeLemploi(ModeleEmploi modele, ControleurEmploi controleur) {
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();

        //nomMatiere3 = nomMatiere;
        GregorianCalendar dateActuelle = new GregorianCalendar();

        if(dateActuelle.get(Calendar.MONTH) >= Calendar.SEPTEMBER) {
            anneeEnCours = dateActuelle.get(Calendar.YEAR);
        }

        else {
            anneeEnCours = dateActuelle.get(Calendar.YEAR) - 1;
        }

        GregorianCalendar calendar1 = new GregorianCalendar();

        calendar1.set(Calendar.DAY_OF_MONTH, 2);
        calendar1.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar1.set(Calendar.YEAR, anneeEnCours);

        Date input = dateActuelle.getTime();
        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Date input1 = calendar1.getTime();
        LocalDate date1 = input1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long x = ChronoUnit.WEEKS.between(date1, date);
        this.semainesAnnee = Arrays.asList((int)x);


        //nomEnseignant2 = nomEnseignant;
        //nomSalle2 = nomSalle;
        this.modele = modele;
        this.controleur = controleur;
        this.emploiDuTemps = modele.getEmploiDuTemps();

        // Temporaire
        this.modele.addObserver(this);
        formationComboBoxModel = new DefaultComboBoxModel(modele.getFormations().toArray());
        choixFormationBox.setModel(formationComboBoxModel);

        DefaultComboBoxModel matierecomboBoxModel = new DefaultComboBoxModel(modele.getMatieres().toArray());
        nomMatiere.setModel(matierecomboBoxModel);
        nomMatiere2.setModel(matierecomboBoxModel);
        nomMatiere3.setModel(matierecomboBoxModel);

        DefaultComboBoxModel sallecomboBoxModel = new DefaultComboBoxModel(modele.getSalles().toArray());
        nomSalle.setModel(sallecomboBoxModel);
        nomSalle2.setModel(sallecomboBoxModel);
        gererVueEnseignant();

        gererTabEnseignants();
        gererTabSalle();
        gererTabTotalReservation();

        /*tmodelTotalReservation = new TmodelReservation(modele.getTotalReservations());
        tableListeReservation.setModel(tmodelReservation);*/

        //  Mettre dans une fonction
        affichageSemaine.setModel(new TmodelSemaine());
        affichageSemaine.setCellSelectionEnabled(true);
        affichageSemaine.setColumnSelectionAllowed(true);
        affichageSemaine.setTableHeader(null);
        scrollPane4.getViewport().add(affichageSemaine);

        joursSemainePanel = new JPanel[]{lundi, mardi, mercredi, jeudi, vendredi, samedi};
        panel1.setBackground(lundi.getBackground());
        joursSemaineLabel = new JLabel[]{labelLundi, labelMardi, labelMercredi, labelJeudi, labelVendredi, labelSamedi};
        joursSemaineString = new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        moisAnnee = new String[]{"Janvier", "Février", "Mars", "Avril", "Mai", "juin", "Juillet", "Août",
                                 "Septembre", "Octobre", "Novembre", "Décembre"};
        lundi.setName("lundi");
        mardi.setName("mardi");
        mercredi.setName("mercredi");
        jeudi.setName("jeudi");
        vendredi.setName("vendredi");
        samedi.setName("samedi");
        controleur.changerSemaine();
        this.fenetreAuthentification.setVisible(true);
    }

    private void gererVueEnseignant(){
        globalpane = parentPanel;
        c1 = new CardLayout();
        globalpane.setLayout(c1);

        globalpane.add(panelReserverSalle,"panelReserverSalle");
        globalpane.add(panelListeReservation,"panelListeReservation");
        //globalpane.add(panelEmploi,"panelEmploi");

    }

    private void gererTabSalle(){


        tmodelSalle = new VueGestionaire.TmodelSalle(modele.getSalles());
        tableSalles.setModel(tmodelSalle);
        scrollPaneSalle.getViewport().add(tableSalles);


        //évenement pour obliger la sélection d'une salle
        buttonDemandeReserv.setEnabled(false);
        ListSelectionModel model = tableSalles.getSelectionModel();
        ListSelectionModel model2 = tableSeances.getSelectionModel();
        model.addListSelectionListener(e -> {
            if (model.isSelectionEmpty() || model2.isSelectionEmpty() ){
                buttonDemandeReserv.setEnabled(false);
            }else {
                buttonDemandeReserv.setEnabled(true);
            }
        });

    }

    private void gererTabReservation(){
        tmodelReservation = new TmodelReservation(modele.getReservations());
        tmodelTotalReservation = new TmodelReservation(modele.getTotalReservations());
        tableListeReservation.setModel(tmodelReservation);
        tableSalles.getColumnModel().getColumn(0).setMinWidth(0);
        tableSalles.getColumnModel().getColumn(0).setMaxWidth(0);
        tableListeReservation2.setModel(tmodelTotalReservation);
        scrollPane3.getViewport().add(tableListeReservation);
        scrollPane5.getViewport().add(tableListeReservation2);
        bouttonConfirmReservation.setEnabled(false);
        boutonRefuseReservation.setEnabled(false);
        ListSelectionModel model = tableListeReservation2.getSelectionModel();
        model.addListSelectionListener(e -> {
            if (model.isSelectionEmpty()){
                bouttonConfirmReservation.setEnabled(false);
                boutonRefuseReservation.setEnabled(false);
            }else {
                bouttonConfirmReservation.setEnabled(true);
                boutonRefuseReservation.setEnabled(true);
            }
        });
    }
    private void gererTabTotalReservation(){
        tmodelTotalReservation = new TmodelReservation(modele.getTotalReservations());
        tableListeReservation2.setModel(tmodelTotalReservation);
        scrollPane5.getViewport().add(tableListeReservation2);
    }

    private void gererTabEnseignants(){
        tmodelEnseignant = new VueGestionaire.TmodelEnseignant(modele.getEnseignantsList());
        tableEnseignants.setModel(tmodelEnseignant);
        tableEnseignants.getColumnModel().getColumn(0).setMinWidth(0);
        tableEnseignants.getColumnModel().getColumn(0).setMaxWidth(0);
        tableEnseignants.getColumnModel().getColumn(3).setMinWidth(0);
        tableEnseignants.getColumnModel().getColumn(3).setMaxWidth(0);
        tableEnseignants.getColumnModel().getColumn(4).setMinWidth(0);
        tableEnseignants.getColumnModel().getColumn(4).setMaxWidth(0);
        tableMatiere.setModel(matTable);
        matTable.addColumn("Matiere1");
        matTable.addColumn("Matiere2");
        matTable.addColumn("Matiere3");
        for(Enseignant enseignant: modele.getEnseignantsList()){
            matTable.addRow((this.controleur.recupMatieres(enseignant)).toArray());
            //System.out.println(this.controleur.recupMatieres(enseignant).toArray());
            matTable.fireTableDataChanged();
        }
    }

    private void gererTabSeances(){
        int id_enseignant = ((Enseignant)this.modele.getEnseignantByLogin(personneAuthentifiee)).getId();
        tmodelSeance = new TmodelSeance(modele.getSeancesEnseignant(id_enseignant));
        tableSeances.setModel(tmodelSeance);

        tableSeances.getColumnModel().getColumn(0).setMinWidth(0);
        tableSeances.getColumnModel().getColumn(0).setMaxWidth(0);
        tableSeances.getColumnModel().getColumn(2).setMinWidth(0);
        tableSeances.getColumnModel().getColumn(2).setMaxWidth(0);

        ListSelectionModel model = tableSalles.getSelectionModel();
        ListSelectionModel model2 = tableSeances.getSelectionModel();
        model2.addListSelectionListener(e -> {
            int i = tableSeances.getSelectedRow();
            Seance seance = tmodelSeance.getRowValue(i);
            tmodelSalle = new VueGestionaire.TmodelSalle(modele.getSallesDispo((GregorianCalendar) seance.getHdebut(),(GregorianCalendar) seance.getHdebut()));
            tableSalles.setModel(tmodelSalle);
            scrollPaneSalle.getViewport().add(tableSalles);

            if (model.isSelectionEmpty() || model2.isSelectionEmpty() ){
                buttonDemandeReserv.setEnabled(false);
            }else {
                buttonDemandeReserv.setEnabled(true);
            }
        });





    }

    private void button1ActionPerformed(ActionEvent e) {
        //System.out.println("le jour actuel c" +this.jourSemaine);
        for(int semaineAnnee: semainesAnnee) {

            GregorianCalendar debutH = new GregorianCalendar();
            debutH.set(Calendar.DAY_OF_MONTH, 2);
            debutH.set(Calendar.MONTH, Calendar.SEPTEMBER);
            debutH.set(Calendar.YEAR, anneeEnCours);
            debutH.add(GregorianCalendar.WEEK_OF_YEAR, semaineAnnee);
            debutH.set(GregorianCalendar.DAY_OF_WEEK, this.jourSemaine);
            debutH.set(GregorianCalendar.HOUR_OF_DAY, hDebutH2.getSelectedIndex() + 8);
            debutH.set(GregorianCalendar.MINUTE, hDebutM2.getSelectedIndex() * 15);

            GregorianCalendar finH = new GregorianCalendar();
            finH.set(Calendar.DAY_OF_MONTH, 2);
            finH.set(Calendar.MONTH, Calendar.SEPTEMBER);
            finH.set(Calendar.YEAR, anneeEnCours);
            finH.add(GregorianCalendar.WEEK_OF_YEAR, semaineAnnee);
            finH.set(GregorianCalendar.DAY_OF_WEEK, this.jourSemaine);
            finH.set(GregorianCalendar.HOUR_OF_DAY, hFinH2.getSelectedIndex() + 8);
            finH.set(GregorianCalendar.MINUTE, hFinM2.getSelectedIndex() * 15);


            Seance seance = new Seance( (Salle) nomSalle2.getSelectedItem(), (Enseignant) nomEnseignant2.getSelectedItem(),
                    (Matiere) nomMatiere3.getSelectedItem(), debutH, finH,
                    (Formation) choixFormationBox.getSelectedItem());

            if (etatConsultation == 0)
                this.controleur.modifierSeance(idSalle, (Salle) nomSalle2.getSelectedItem(), (Enseignant) nomEnseignant2.getSelectedItem(), (Matiere) nomMatiere3.getSelectedItem(), debutH, finH, (Formation) choixFormationBox.getSelectedItem());

            else
                this.controleur.modifierSeanceBDD(idSalle, (Salle) nomSalle2.getSelectedItem(), (Enseignant) nomEnseignant2.getSelectedItem(), (Matiere) nomMatiere3.getSelectedItem(), debutH, finH, (Formation) choixFormationBox.getSelectedItem());
        }
    }

    private void boutonCreerEmploiActionPerformed(ActionEvent e) {
        etatConsultation = 0;
        this.fenetreDebut.setVisible(false);
        this.setVisible(true);
        this.intituleFormation.setText(choixFormationBox.getSelectedItem().toString());

    }

    /**
     * Insère une séance dans l'emploi du temps (la semaine est fixée).
     * @param seance
     */
    private void affichageSeance(int x, int largeur, Container container, Seance seance) {
        JPanel panelle = new JPanel();
        panelle.setLayout(new BoxLayout(panelle, BoxLayout.Y_AXIS));
        panelle.setName(Integer.toString(nomPanel));
        container.add(panelle);

        if(consultationModifiable) {
            panelle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //panel2MouseClicked(e);
                    modifierSeance.setVisible(true);
                    nomMatiere3.setSelectedItem(seance.getMatiere());
                    nomEnseignant2.setSelectedItem(seance.getEnseignant());
                    nomSalle2.setSelectedItem(seance.getSalle());
                    sallesDispoJour();
                    hDebutH2.setSelectedItem(String.valueOf(seance.getHdebut().get(Calendar.HOUR_OF_DAY)));
                    hDebutM2.setSelectedItem(String.valueOf(seance.getHdebut().get(Calendar.MINUTE)));
                    hFinH2.setSelectedItem(String.valueOf(seance.getHfin().get(Calendar.HOUR_OF_DAY)));
                    hFinH2.setSelectedItem(String.valueOf(seance.getHfin().get(Calendar.MINUTE)));
                    idSalle = seance.getId();
                    jourSemaine = seance.getHdebut().get(Calendar.DAY_OF_WEEK);
                    
                    //System.out.println("llllllllll" + jourSemaine);
                }
            });
        }

        creationSeance.setVisible(false);

        Calendar debut =seance.getHdebut();
        Calendar fin =seance.getHfin();
        int j = debut.get(Calendar.DAY_OF_WEEK) -2;

        //System.out.println(jourPanel.getName());

        JLabel matiere = new JLabel(seance.getMatiere().getNom());
        JLabel proffeseur = new JLabel(seance.getEnseignant().toString());
        JLabel formation = new JLabel(seance.getFormation().getIntitule());
        JLabel salle = new JLabel(seance.getSalle().getNom());

        int debH = debut.get(Calendar.HOUR_OF_DAY) - 8;
        int finH = fin.get(Calendar.HOUR_OF_DAY) - 8;
        int debM = debut.get(Calendar.MINUTE)/15;
        int finM = fin.get(Calendar.MINUTE)/15;

        creationSeance.setVisible(false);

        //System.out.println(debH);
        //System.out.println(debM);

        //System.out.println(finH);
        //System.out.println(finM);

        int d =  18 + (debH)*38 + (debM)*10;
        int f =  18 + (finH)*38 + (finM)*10;

        //System.out.println("Debut: " + d);
        //System.out.println("Fin: " + f);

        panelle.setBounds(0, d - x,largeur,f - d);
        panelle.setBorder(BorderFactory.createLineBorder(Color.black));
        panelle.setBackground(Color.gray);

        JPanel panelInt = new JPanel();
        panelInt.setLayout(new BoxLayout(panelInt, BoxLayout.Y_AXIS));

        matiere.setAlignmentX(Component.CENTER_ALIGNMENT);
        proffeseur.setAlignmentX(Component.CENTER_ALIGNMENT);
        formation.setAlignmentX(Component.CENTER_ALIGNMENT);
        salle.setAlignmentX(Component.CENTER_ALIGNMENT);

        //matiere.setBounds(milieux - 10,milieux - 15,80,10);
        //proffeseur.setBounds(15,15,66,10);
        // salle.setBounds(15,30,66,15);

        panelle.add(matiere);
        panelle.add(proffeseur);
        panelle.add(formation);
        panelle.add(salle);
        // panelle.setAlignmentY(Component.CENTER_ALIGNMENT);;
        // panelle.setBackground(panelInt.getBackground());
        // panelle.add(panelInt);
    }

    @Override
    public void update(Observable observable, Object o) {
        // Temporaire juste pour tester l'ajout de seance, réecrit toutes les seances:
        // Doit evidemment changer. Comment ? Je sais pas encore.
        //System.out.println("Mise a jour");
        for(JPanel panel: joursSemainePanel)
            panel.removeAll();
        repaint();

        if(semainesAnnee.size() == 1)  {
            int semaine = semainesAnnee.get(0);
            SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMMM yyyy");
            GregorianCalendar date = new GregorianCalendar();
            date.set(Calendar.DAY_OF_MONTH, 2);
            date.set(Calendar.MONTH, Calendar.SEPTEMBER);
            date.set(Calendar.YEAR, anneeEnCours);
            date.add(Calendar.WEEK_OF_YEAR, semaine);

            for(int i = 0; i < 6; i++) {
                joursSemaineLabel[i].setText(format.format(date.getTime()));
                date.add(Calendar.DAY_OF_YEAR, 1);
            }
        }

        else {
            for(int i = 0; i < 6; i++) {
                String jour = joursSemaineString[i];
                joursSemaineLabel[i].setText(jour);
            }
        }

        //System.out.println(emploiDuTemps[10].get(0).getSalle());

        //System.out.println(emploiDuTemps[this.semaneAnnee]);
        // Le problème on a pas gérer la superposition donc à voir par contre si on la gère pas
        // Il faudra changer des choses.(Afficher qu'une semaine à la fois: je ne sais pas comment la choisir parmi toutes
        // celle sélectionnées)

        // Creation de fou furieux
        Map<int[], HashSet<Seance>> mapSeances = new HashMap<>();
        boolean estPasse;

        for(int semaineAnnee: semainesAnnee) {
            System.out.println(semaineAnnee);
            for (Seance seance : emploiDuTemps[semaineAnnee]) {
                estPasse = false;
                Calendar debut = seance.getHdebut();
                Calendar fin = seance.getHfin();

                int debH = debut.get(Calendar.HOUR_OF_DAY) - 8;
                int finH = fin.get(Calendar.HOUR_OF_DAY) - 8;
                int debM = debut.get(Calendar.MINUTE)/15;
                int finM = fin.get(Calendar.MINUTE)/15;

                int d =  18 + (debH)*38 + (debM)*10;
                int f =  18 + (finH)*38 + (finM)*10;
                int j = debut.get(Calendar.DAY_OF_WEEK) - 2;

                for(Map.Entry entry: mapSeances.entrySet()) {
                    int[] intervalle = (int[]) entry.getKey();

                    if( (j == intervalle[2]) && (d < intervalle[1]) && (f > intervalle[0]) ) {
                        estPasse = true;
                        HashSet<Seance> ensemble = (HashSet<Seance>) entry.getValue();
                        ensemble.add(seance);

                        if(d < intervalle[0] )
                            intervalle[0] = d;

                        if(f > intervalle[1])
                            intervalle[1] = f;
                    }
                }

                if(!estPasse) {
                    HashSet<Seance> seanceHashSet = new HashSet<>();
                    seanceHashSet.add(seance);
                    mapSeances.put(new int[]{d, f, j}, seanceHashSet);
                }
            }
        }

        for(Map.Entry entry: mapSeances.entrySet()) {
            affichageSeances((int[])entry.getKey(), (HashSet<Seance>)entry.getValue());
        }

        // semaineActuelle.setText("Semaine " + semaineAnnee);
    }

    private void affichageSeances(int[] key, HashSet<Seance> value) {
            JPanel jourPanel = joursSemainePanel[key[2]];
            if(value.size() > 1) {
                JPanel panel = new JPanel();

                JTabbedPane jTabbedPane = new JTabbedPane();
                jTabbedPane.setBounds(0, 0, 200, key[1] - key[0]);
                panel.setBounds(0, key[0], 200, key[1] - key[0]);
                jTabbedPane.setTabPlacement(JTabbedPane.LEFT);
                panel.setLayout(null);
                jTabbedPane.setFont(new Font("Tibetan Machine Uni", Font.PLAIN, 10));
                jTabbedPane.setBackground(jourPanel.getBackground());
                panel.add(jTabbedPane);
                jourPanel.add(panel);

                for(Seance seance: value) {
                    JPanel panel1 = new JPanel();
                    panel1.setBackground(new Color(200, 200, 200));
                    panel1.setLayout(null);

                    affichageSeance(key[0], 180, panel1, seance);
                    int semaine = seance.getHfin().get(Calendar.WEEK_OF_YEAR);
                    jTabbedPane.addTab(String.valueOf(semaine), panel1);
                }
            }

            else {

                Seance seance = value.iterator().next();
                affichageSeance(0, 200, jourPanel, seance);;
            }
    }

    private void validercoursActionPerformed(ActionEvent e) {
        //System.out.println("seance");
        int id = nomPanel;
        nomPanel+=1;
        Matiere matiere = (Matiere) this.nomMatiere.getSelectedItem();
        Enseignant enseignant = (Enseignant) this.nomEnseignant.getSelectedItem();
        Salle salle = (Salle) this.nomSalle.getSelectedItem();

        for(int semaineAnnee: semainesAnnee) {
            GregorianCalendar debutH = new GregorianCalendar();
            debutH.set(Calendar.DAY_OF_MONTH, 2);
            debutH.set(Calendar.MONTH, Calendar.SEPTEMBER);
            debutH.set(Calendar.YEAR, anneeEnCours);
            debutH.add(GregorianCalendar.WEEK_OF_YEAR, semaineAnnee);
            debutH.set(GregorianCalendar.DAY_OF_WEEK, this.jourSemaine);
            debutH.set(GregorianCalendar.HOUR_OF_DAY, hDebutH.getSelectedIndex() + 8);
            debutH.set(GregorianCalendar.MINUTE, hDebutM.getSelectedIndex() * 15);

            GregorianCalendar finH = new GregorianCalendar();
            finH.set(Calendar.DAY_OF_MONTH, 2);
            finH.set(Calendar.MONTH, Calendar.SEPTEMBER);
            finH.set(Calendar.YEAR, anneeEnCours);
            finH.add(GregorianCalendar.WEEK_OF_YEAR, semaineAnnee);
            finH.set(GregorianCalendar.DAY_OF_WEEK, this.jourSemaine);
            finH.set(GregorianCalendar.HOUR_OF_DAY, hFinH.getSelectedIndex() + 8);
            finH.set(GregorianCalendar.MINUTE, hFinM.getSelectedIndex() * 15);

            Formation formation = (Formation) this.choixFormationBox.getSelectedItem();

            if (etatConsultation == 0)
                controleur.creerSeance(id, salle, enseignant, matiere, debutH, finH, formation);
            else
                controleur.creerSeanceBDD(salle, enseignant, matiere, debutH, finH, formation);
        }

    }

    private void annulerEmploiActionPerformed(ActionEvent e) {
        this.dispose();

        lundi.removeAll();
        mardi.removeAll();
        mercredi.removeAll();
        jeudi.removeAll();
        vendredi.removeAll();
        samedi.removeAll();
        if (consultationModifiable)
            fenetreDebut.setVisible(true);
        else
            fenetreEnseignant.setVisible(true);

    }

    private void panel2MouseClicked(MouseEvent e) {
        if (consultationModifiable) {
            creationSeance.setVisible(true);
            this.jourSemaine = 2;
            sallesDispoJour();
        }
    }

    private void mardiMouseClicked(MouseEvent e) {
        if (consultationModifiable) {
            creationSeance.setVisible(true);
            this.jourSemaine = 3;
            sallesDispoJour();
        }
    }

    private void mercrediMouseClicked(MouseEvent e) {
        if (consultationModifiable) {
            creationSeance.setVisible(true);
            this.jourSemaine = 4;
            sallesDispoJour();
        }
    }

    private void jeudiMouseClicked(MouseEvent e) {
        if (consultationModifiable) {
            creationSeance.setVisible(true);
            this.jourSemaine = 5;
            sallesDispoJour();
        }
    }

    private void vendrediMouseClicked(MouseEvent e) {
        if (consultationModifiable) {
            creationSeance.setVisible(true);
            this.jourSemaine = 6;
            sallesDispoJour();
        }
    }

    private void samediMouseClicked(MouseEvent e) {
        if (consultationModifiable) {
            creationSeance.setVisible(true);
            this.jourSemaine = 7;
            sallesDispoJour();
        }
    }

    private void validerEmploiActionPerformed(ActionEvent e) {
        controleur.creerEmploi();
    }

    private void semainePrecedenteActionPerformed(ActionEvent e) {
    //   semaineAnnee -= 1;
    //   if(semaineAnnee < 1)
    //       semaineAnnee = 52;

    //   controleur.changerSemaine();
    }

    private void semaineSuivanteActionPerformed(ActionEvent e) {
      //   semaineAnnee += 1;
      //   if(semaineAnnee > 52)
      //       semaineAnnee = 1;

      //   controleur.changerSemaine();
    }

    private void supprimerSeance(ActionEvent e) {
        int id = ((Formation)choixFormationBox.getSelectedItem()).getId();
        controleur.supprimerEmploi(id);

    }

    private void button3ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void associerBoutonActionPerformed(ActionEvent e) {
        int i = tableEnseignants.getSelectedRow();
        Enseignant enseignant = tmodelEnseignant.getRowValue(i);
        Matiere matiere = (Matiere) nomMatiere2.getSelectedItem();
        controleur.associerMatiereProf(matiere,enseignant);

    }

    private void nomMatiereItemStateChanged(ItemEvent e) {
        Matiere matiere = (Matiere) e.getItem();
        // Temporaire: Je sais pas comment changer la liste en paramètre de combobox sans créer un nouveau modele
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(matiere.getListeEnseignants().toArray());
        nomEnseignant.setModel(comboBoxModel);
        nomEnseignant2.setModel(comboBoxModel);
    }

    private void suppSeanceBoutonActionPerformed(ActionEvent e) {
        for(int semaineAnnee: semainesAnnee) {
            if (etatConsultation == 0)
                this.controleur.supprimerSeance(idSalle, semaineAnnee);
            else
                this.controleur.supprimerSeanceBDD(idSalle, semaineAnnee);
        }

    }

    private void bouttonMofierEmploiActionPerformed(ActionEvent e) {
        etatConsultation = 1;
        this.fenetreDebut.setVisible(false);
        this.setVisible(true);

        this.intituleFormation.setText(choixFormationBox.getSelectedItem().toString());
        int id = ((Formation)choixFormationBox.getSelectedItem()).getId();
        //System.out.println(id);
        this.controleur.initEmploi(id);
    }

    private void choixFormationBoxActionPerformed(ActionEvent e) {
        //Changer de formation masque les autres
        lundi.removeAll();
        mardi.removeAll();
        mercredi.removeAll();
        jeudi.removeAll();
        vendredi.removeAll();
        samedi.removeAll();
    }

    private void button5ActionPerformed(ActionEvent e) {
        inputLogin.setText("");
        inputPassword.setText("");
    }

    private void loginButtonActionPerformed(ActionEvent e) {

        String login = inputLogin.getText();
        String password = String.copyValueOf(inputPassword.getPassword());
        Boolean valider=true;
        inputLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if (login.equals("") ) {
            inputLogin.setBorder(BorderFactory.createLineBorder(Color.RED));
            valider = false;
        }
        if (password.equals("") ) {
            inputPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
            valider = false;
        }
        if (valider){
            typePersonne = this.controleur.getTypeLogin(login);
            personneAuthentifiee = login;
            modele.sendAuthentification(login);
            sessionActive=true;
            switch(typePersonne){
                case"Etudiant":
                    consultationModifiable = false;
                    int id_formation = ((Etudiant)this.controleur.getEtudiantbyLogin(personneAuthentifiee)).getFormation().getId();
                    this.controleur.initEmploi(id_formation);
                    this.setVisible(true);
                    annulerEmploi.setVisible(false);
                    validerEmploi.setVisible(false);

                    validerEmploi.setVisible(false);

                    break;
                case"Enseignant":
                    consultationModifiable = false;
                    fenetreEnseignant.setVisible(true);
                    intituleFormation.setText("Votre emploi du Temps");
                    ((CardLayout)parentPanel.getLayout()).show(parentPanel,"panelReserverSalle");
                    fenetreAuthentification.dispose();
                    gererTabSeances();
                    break;
                case"Responsable":
                    consultationModifiable = true;
                    if (this.controleur.verifierAuthResponsable(login,password)){
                        this.fenetreAuthentification.setVisible(false);
                        this.fenetreAuthentification.dispose();
                        this.fenetreDebut.setVisible(true);

                    }else {
                        JOptionPane.showMessageDialog(panel5,"Veuillez vérifier vos coordonnées","Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:  JOptionPane.showMessageDialog(panel5,"Veuillez vérifier vos coordonnées","Error", JOptionPane.ERROR_MESSAGE);

            }}


    }

    private void authentifier(String login) {
        this.personneAuthentifiee=login;

    }

    private void loginButtonKeyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    private void panel5KeyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    private void inputLoginKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10){
            System.out.println(String.valueOf(inputPassword.getPassword()));
            String login = inputLogin.getText();
            String password = String.copyValueOf(inputPassword.getPassword());
            if (this.controleur.verifierAuthResponsable(login,password)){
                this.fenetreAuthentification.setVisible(false);
                this.fenetreAuthentification.dispose();
                this.fenetreDebut.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(panel5,"Veuillez vérifier vos coordonnées","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        fenetreDebut.dispose();
        fenetreAuthentification.setVisible(true);
        fenetreEnseignant.dispose();
    }

    private void button6ActionPerformed(ActionEvent e) {

    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        c1.show(globalpane,"panelEmploi");

    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        {
            c1.show(globalpane,"panelReserverSalle");    }    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        c1.show(globalpane,"panelListeReservation");
        modele.setListSeances();
        gererTabReservation();
    }

    private void buttonDemandeReservActionPerformed(ActionEvent e) {
        boolean valider =true;
        int i = tableSalles.getSelectedRow();
        Salle salle = tmodelSalle.getRowValue(i);
        Seance seance = tmodelSeance.getRowValue(i);
        int id_seance = seance.getId();
        int id_Salle = salle.getId();
        Date date_reservation = inputdateReSalle.getDate();
        Date dateMax =java.sql.Date.valueOf(LocalDate.now().plusDays(14)) ;
        Date dateNow =java.sql.Date.valueOf(LocalDate.now()) ;
        if (date_reservation.after(dateMax)){
            JOptionPane.showMessageDialog(panelReserverSalle,"Veuillez choisir une date qui déppase pas les 14 jours","erreur", JOptionPane.ERROR_MESSAGE);
            inputdateReSalle.setBorder(BorderFactory.createLineBorder(Color.red));
            valider = false;
        }else if (date_reservation.before(dateNow)){
            JOptionPane.showMessageDialog(panelReserverSalle,"Veuillez choisir une date antérieur","erreur", JOptionPane.ERROR_MESSAGE);
            inputdateReSalle.setBorder(BorderFactory.createLineBorder(Color.red));
            valider = false;
        }

        if(valider) {
            int id_enseignant = ((Enseignant)this.modele.getEnseignantByLogin(personneAuthentifiee)).getId();
            System.out.println("seance" + id_seance);
            this.controleur.creerReservation(id_enseignant,id_Salle,date_reservation, id_seance);
            JOptionPane.showMessageDialog(panelReserverSalle,"Votre demande à été envoyé au responsable de formation","Confirm", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void affichageSemaineMouseReleased(MouseEvent e) {
        List<Integer> temp = new ArrayList<>();

        for(int semaine: affichageSemaine.getSelectedColumns()) {
            TmodelSemaine semaine1 = new TmodelSemaine();
            temp.add(semaine);
        }

        semainesAnnee = temp;
        controleur.changerSemaine();

    }

    private void consulterProfActionPerformed(ActionEvent e) {
        int id_enseignant = ((Enseignant)this.modele.getEnseignantByLogin(personneAuthentifiee)).getId();
        this.controleur.initEmploiEnseignant(id_enseignant);

        validerEmploi.setVisible(false);
        this.fenetreEnseignant.setVisible(false);
        this.setVisible(true);


    }

    private void consulterEnseignantEmploiActionPerformed(ActionEvent e) {
        int id_enseignant = ((Enseignant)this.modele.getEnseignantByLogin(personneAuthentifiee)).getId();
        this.controleur.initEmploiEnseignant(id_enseignant);

        validerEmploi.setVisible(false);
        this.fenetreEnseignant.setVisible(false);
        this.setVisible(true);
    }

    private void logoutButton2ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void sallesDispoJour() {

        for(int semaineAnnee: semainesAnnee) {
            GregorianCalendar finH = new GregorianCalendar();

            GregorianCalendar debutH = new GregorianCalendar();
            debutH.set(GregorianCalendar.WEEK_OF_YEAR, semaineAnnee);
            debutH.set(GregorianCalendar.DAY_OF_WEEK, this.jourSemaine);
            debutH.set(GregorianCalendar.HOUR_OF_DAY, hDebutH2.getSelectedIndex() + 8);
            debutH.set(GregorianCalendar.MINUTE, hDebutM2.getSelectedIndex() * 15);

            finH.set(GregorianCalendar.WEEK_OF_YEAR, semaineAnnee);
            finH.set(GregorianCalendar.DAY_OF_WEEK, this.jourSemaine);
            finH.set(GregorianCalendar.HOUR_OF_DAY, hFinH2.getSelectedIndex() + 8);
            finH.set(GregorianCalendar.MINUTE, hFinM2.getSelectedIndex() * 15);
            List<Salle> sallesDispo = this.controleur.getSallesDispo(debutH, finH);
            // Temporaire: Je sais pas comment changer la liste en paramètre de combobox sans créer un nouveau modele
            DefaultComboBoxModel salleComboModel = new DefaultComboBoxModel(this.controleur.getSallesDispo(debutH, finH).toArray());
            nomSalle.setModel(salleComboModel);
            nomSalle2.setModel(salleComboModel);
        }
    }

    private void bouttonConfirmReservationActionPerformed(ActionEvent e) {
        int i = tableListeReservation2.getSelectedRow();
        Reservation reservation = tmodelTotalReservation.getRowValue(i);
        Salle salle = modele.getSalleById(tmodelTotalReservation.getRowValue(i).getId_salle());
        Seance seance = modele.getSeanceById(tmodelTotalReservation.getRowValue(i).getId_seance());

        Date dateReserv = tmodelTotalReservation.getRowValue(i).getDate_reservation();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateReserv);
        modele.confirmerReservation(reservation);
        JOptionPane.showMessageDialog(panelReserverSalle,"l'enseignant va etre informer de votre décision","Confirm", JOptionPane.INFORMATION_MESSAGE);
        Seance s = new Seance(salle,calendar);
        //modele.createFakeSeance(s);
        this.modele.modifierSeanceBDDsansAffichage(seance.getId(), salle, seance.getEnseignant(), seance.getMatiere(),
                (GregorianCalendar) seance.getHdebut(),(GregorianCalendar) seance.getHfin(), seance.getFormation());

    }

    private void boutonRefuseReservationActionPerformed(ActionEvent e) {
        int i = tableListeReservation2.getSelectedRow();
        Reservation reservation = tmodelTotalReservation.getRowValue(i);
        modele.refuserReservation(reservation);
        JOptionPane.showMessageDialog(panelReserverSalle,"l'enseignant va etre informer de votre décision","Confirm", JOptionPane.INFORMATION_MESSAGE);

    }

    private void affichageSemaineMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void tableListeReservation2MouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            int ligne = tableListeReservation.getSelectedRow();
            Reservation reservation = tmodelReservation.getRowValue(ligne);
            label22.setText("Réservation n° " + reservation.getId());
        }
    }

    private void affichageSemaineKeyReleased(KeyEvent e) {
        if(e.getKeyCode() == 10) {
            affichageSemaine.getCursor();
        }
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    public static class TmodelSeance extends AbstractTableModel implements Observer {
        private final String[] entetes = {"ID","Salle", "enseignant","matiere" ,"h debut", "h fin", "date"};
        private final List<Seance> seances;

        public TmodelSeance (List<Seance> seances) {
            this.seances = seances;
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
            if(seances != null)
                return seances.size();
            else
                return 0;
        }

        public Seance getRowValue(int i) {
            Seance seance = seances.get(i);
            return seance;
        }

        @Override
        public Object getValueAt(int i, int i1) {
            Seance seance = getRowValue(i);
            //System.out.println(salle);
            Object[] o = seance.getAttributs().toArray();
            return o[i1];
        }
    }

    public static class TmodelReservation extends AbstractTableModel implements Observer {
        private final String[] entetes = {"ID","id_salle", "id_enseignant","date" ,"etat", "id seance"};
        private final List<Reservation> reservations;

        public TmodelReservation (List<Reservation> reservations) {
            this.reservations = reservations;
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
            if(reservations != null)
                return reservations.size();
            else
                return 0;
        }

        public Reservation getRowValue(int i) {
            Reservation reservation = reservations.get(i);
            return reservation;
        }

        @Override
        public Object getValueAt(int i, int i1) {
            Reservation reservation = getRowValue(i);
            //System.out.println(salle);
            Object[] o = reservation.getAttributs().toArray();
            return o[i1];
        }
    }

    // Macgyver ou BricoDéco ou Système D
    public static class TmodelSemaine extends AbstractTableModel implements Observer {
        private int nbsemaines;
        private int septembre;

        public TmodelSemaine() {
            GregorianCalendar calendar = new GregorianCalendar();
            int anneeActuelle = calendar.get(Calendar.YEAR);
            int mois = calendar.get(Calendar.MONTH);
            System.out.println(mois);
            System.out.println(anneeActuelle);

            if(mois < Calendar.SEPTEMBER) {
                calendar.set(Calendar.YEAR, anneeActuelle - 1);
            }

            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 2);
            septembre = calendar.get(Calendar.WEEK_OF_YEAR);
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 31);

            int ordinalDay = calendar.get(Calendar.DAY_OF_YEAR);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Sunday = 0
            nbsemaines = (ordinalDay - weekDay + 10) / 7;
        }

        @Override
        public void update(Observable observable, Object o) {
            // S'occupera de souligner les semaines contenant des séances.
        }

        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount() {
            return nbsemaines;
        }

        @Override
        public Object getValueAt(int i, int i1) {
            return (i1 + septembre - 1) % (nbsemaines - 1) + 1;

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Haribou Abdallah
        panel1 = new JPanel();
        panel2 = new JPanel();
        vSpacer2 = new JPanel(null);
        label4 = new JLabel();
        vSpacer14 = new JPanel(null);
        label9 = new JLabel();
        vSpacer3 = new JPanel(null);
        label10 = new JLabel();
        vSpacer4 = new JPanel(null);
        label11 = new JLabel();
        vSpacer5 = new JPanel(null);
        label12 = new JLabel();
        vSpacer6 = new JPanel(null);
        label13 = new JLabel();
        vSpacer7 = new JPanel(null);
        label14 = new JLabel();
        vSpacer8 = new JPanel(null);
        label15 = new JLabel();
        vSpacer9 = new JPanel(null);
        label16 = new JLabel();
        vSpacer10 = new JPanel(null);
        label17 = new JLabel();
        vSpacer11 = new JPanel(null);
        label18 = new JLabel();
        vSpacer12 = new JPanel(null);
        label19 = new JLabel();
        vSpacer13 = new JPanel(null);
        label20 = new JLabel();
        panel10 = new JPanel();
        labelMardi = new JLabel();
        mardi = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x  = 18;
                int y = 38;
                for(int i = 0; i < 13; i++) {
                    g.drawLine(0, x + y*i, 200, x + y*i);
                }
            }
        };
        panel11 = new JPanel();
        labelLundi = new JLabel();
        lundi = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x  = 18;
                int y = 38;
                for(int i = 0; i < 13; i++) {
                    g.drawLine(0, x + y*i, 200, x + y*i);
                }
            }
        };
        panel12 = new JPanel();
        labelMercredi = new JLabel();
        mercredi = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x  = 18;
                int y = 38;
                for(int i = 0; i < 13; i++) {
                    g.drawLine(0, x + y*i, 200, x + y*i);
                }
            }
        };
        panel13 = new JPanel();
        labelJeudi = new JLabel();
        jeudi = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x  = 18;
                int y = 38;
                for(int i = 0; i < 13; i++) {
                    g.drawLine(0, x + y*i, 200, x + y*i);
                }
            }
        };
        panel14 = new JPanel();
        labelVendredi = new JLabel();
        vendredi = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x  = 18;
                int y = 38;
                for(int i = 0; i < 13; i++) {
                    g.drawLine(0, x + y*i, 200, x + y*i);
                }
            }
        };
        panel15 = new JPanel();
        labelSamedi = new JLabel();
        samedi = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x  = 18;
                int y = 38;
                for(int i = 0; i < 13; i++) {
                    g.drawLine(0, x + y*i, 200, x + y*i);
                }
            }
        };
        intituleFormation = new JLabel();
        validerEmploi = new JButton();
        annulerEmploi = new JButton();
        semaineActuelle = new JLabel();
        scrollPane4 = new JScrollPane();
        affichageSemaine = new JTable();
        vSpacer1 = new JPanel(null);
        creationSeance = new JFrame();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        hDebutH = new JComboBox<>();
        hDebutM = new JComboBox<>();
        hFinH = new JComboBox<>();
        hFinM = new JComboBox<>();
        label2 = new JLabel();
        nomSalle = new JComboBox();
        nomMatiere = new JComboBox();
        nomEnseignant = new JComboBox();
        validercours2 = new JButton();
        fenetreDebut = new JFrame();
        tabbedPane1 = new JTabbedPane();
        panel3 = new JPanel();
        label3 = new JLabel();
        label1 = new JLabel();
        choixFormationBox = new JComboBox<>();
        boutonCreerEmploi = new JButton();
        bouttonMofierEmploi = new JButton();
        button2 = new JButton();
        logoutButton = new JButton();
        panel4 = new JPanel();
        nomMatiere2 = new JComboBox();
        scrollPane1 = new JScrollPane();
        tableEnseignants = new JTable();
        associerBouton = new JButton();
        scrollPane2 = new JScrollPane();
        tableMatiere = new JTable();
        panelListeReservation2 = new JPanel();
        panel9 = new JPanel();
        scrollPane5 = new JScrollPane();
        tableListeReservation2 = new JTable();
        bouttonConfirmReservation = new JButton();
        boutonRefuseReservation = new JButton();
        modifierSeance = new JFrame();
        label21 = new JLabel();
        label28 = new JLabel();
        label29 = new JLabel();
        label30 = new JLabel();
        hDebutH2 = new JComboBox<>();
        hDebutM2 = new JComboBox<>();
        hFinH2 = new JComboBox<>();
        hFinM2 = new JComboBox<>();
        label31 = new JLabel();
        nomSalle2 = new JComboBox();
        nomMatiere3 = new JComboBox();
        nomEnseignant2 = new JComboBox();
        button1 = new JButton();
        button3 = new JButton();
        suppSeanceBouton = new JButton();
        fenetreAuthentification = new JFrame();
        panel5 = new JPanel();
        inputLogin = new JTextField();
        inputPassword = new JPasswordField();
        loginButton = new JButton();
        button5 = new JButton();
        label32 = new JLabel();
        label33 = new JLabel();
        label34 = new JLabel();
        fenetreEnseignant = new JFrame();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        consulterEnseignantEmploi = new JMenuItem();
        menu2 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        hSpacer1 = new JPanel(null);
        menu3 = new JMenu();
        menuItem3 = new JMenuItem();
        parentPanel = new JPanel();
        panelEmploi = new JPanel();
        panelReserverSalle = new JPanel();
        panelSalle = new JPanel();
        buttonDemandeReserv = new JButton();
        panel6 = new JPanel();
        label35 = new JLabel();
        button4 = new JButton();
        scrollPane6 = new JScrollPane();
        tableSeances = new JTable();
        scrollPaneSalle = new JScrollPane();
        tableSalles = new JTable();
        label36 = new JLabel();
        label37 = new JLabel();
        inputdateReSalle = new JDateChooser();
        panelListeReservation = new JPanel();
        panel7 = new JPanel();
        scrollPane3 = new JScrollPane();
        tableListeReservation = new JTable();
        descriptionReservation = new JDialog();
        label22 = new JLabel();
        label23 = new JLabel();
        formattedTextField2 = new JFormattedTextField();
        label24 = new JLabel();
        formattedTextField3 = new JFormattedTextField();
        label25 = new JLabel();
        formattedTextField4 = new JFormattedTextField();
        label27 = new JLabel();
        scrollPane7 = new JScrollPane();
        textArea1 = new JTextArea();
        label38 = new JLabel();
        textArea2 = new JTextArea();

        //======== this ========
        setResizable(false);
        setIconImage(new ImageIcon("/home/guard/Documents/M1/Projet/Projet_Ventura/fond_slide1.jpg").getImage());
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(153, 153, 153));
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );

            //======== panel2 ========
            {
                panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

                //---- vSpacer2 ----
                vSpacer2.setPreferredSize(new Dimension(7, 7));
                vSpacer2.setMinimumSize(new Dimension(7, 7));
                vSpacer2.setMaximumSize(new Dimension(32762, 19));
                panel2.add(vSpacer2);

                //---- label4 ----
                label4.setText("8h");
                label4.setMaximumSize(new Dimension(25, 21));
                label4.setMinimumSize(new Dimension(25, 21));
                label4.setPreferredSize(new Dimension(25, 21));
                panel2.add(label4);

                //---- vSpacer14 ----
                vSpacer14.setPreferredSize(new Dimension(7, 17));
                vSpacer14.setMinimumSize(new Dimension(7, 17));
                vSpacer14.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer14);

                //---- label9 ----
                label9.setText("9h");
                label9.setMaximumSize(new Dimension(25, 21));
                label9.setMinimumSize(new Dimension(25, 21));
                label9.setPreferredSize(new Dimension(25, 21));
                panel2.add(label9);

                //---- vSpacer3 ----
                vSpacer3.setPreferredSize(new Dimension(7, 17));
                vSpacer3.setMinimumSize(new Dimension(7, 17));
                vSpacer3.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer3);

                //---- label10 ----
                label10.setText("10h");
                label10.setPreferredSize(new Dimension(25, 21));
                label10.setMinimumSize(new Dimension(25, 21));
                label10.setMaximumSize(new Dimension(25, 21));
                panel2.add(label10);

                //---- vSpacer4 ----
                vSpacer4.setPreferredSize(new Dimension(7, 17));
                vSpacer4.setMinimumSize(new Dimension(7, 17));
                vSpacer4.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer4);

                //---- label11 ----
                label11.setText("11h");
                label11.setPreferredSize(new Dimension(25, 21));
                label11.setMinimumSize(new Dimension(25, 21));
                label11.setMaximumSize(new Dimension(25, 21));
                panel2.add(label11);

                //---- vSpacer5 ----
                vSpacer5.setPreferredSize(new Dimension(7, 17));
                vSpacer5.setMinimumSize(new Dimension(7, 17));
                vSpacer5.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer5);

                //---- label12 ----
                label12.setText("12h");
                label12.setPreferredSize(new Dimension(25, 21));
                label12.setMinimumSize(new Dimension(25, 21));
                label12.setMaximumSize(new Dimension(25, 21));
                panel2.add(label12);

                //---- vSpacer6 ----
                vSpacer6.setPreferredSize(new Dimension(7, 17));
                vSpacer6.setMinimumSize(new Dimension(7, 17));
                vSpacer6.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer6);

                //---- label13 ----
                label13.setText("13h");
                label13.setPreferredSize(new Dimension(25, 21));
                label13.setMinimumSize(new Dimension(25, 21));
                label13.setMaximumSize(new Dimension(25, 21));
                panel2.add(label13);

                //---- vSpacer7 ----
                vSpacer7.setPreferredSize(new Dimension(7, 17));
                vSpacer7.setMinimumSize(new Dimension(7, 17));
                vSpacer7.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer7);

                //---- label14 ----
                label14.setText("14h");
                label14.setPreferredSize(new Dimension(25, 21));
                label14.setMinimumSize(new Dimension(25, 21));
                label14.setMaximumSize(new Dimension(25, 21));
                panel2.add(label14);

                //---- vSpacer8 ----
                vSpacer8.setPreferredSize(new Dimension(7, 17));
                vSpacer8.setMinimumSize(new Dimension(7, 17));
                vSpacer8.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer8);

                //---- label15 ----
                label15.setText("15h");
                label15.setPreferredSize(new Dimension(25, 21));
                label15.setMinimumSize(new Dimension(25, 21));
                label15.setMaximumSize(new Dimension(25, 21));
                panel2.add(label15);

                //---- vSpacer9 ----
                vSpacer9.setPreferredSize(new Dimension(7, 17));
                vSpacer9.setMinimumSize(new Dimension(7, 17));
                vSpacer9.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer9);

                //---- label16 ----
                label16.setText("16h");
                label16.setPreferredSize(new Dimension(25, 21));
                label16.setMinimumSize(new Dimension(25, 21));
                label16.setMaximumSize(new Dimension(25, 21));
                panel2.add(label16);

                //---- vSpacer10 ----
                vSpacer10.setPreferredSize(new Dimension(7, 17));
                vSpacer10.setMinimumSize(new Dimension(7, 17));
                vSpacer10.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer10);

                //---- label17 ----
                label17.setText("17h");
                label17.setPreferredSize(new Dimension(25, 21));
                label17.setMinimumSize(new Dimension(25, 21));
                label17.setMaximumSize(new Dimension(25, 21));
                panel2.add(label17);

                //---- vSpacer11 ----
                vSpacer11.setPreferredSize(new Dimension(7, 17));
                vSpacer11.setMinimumSize(new Dimension(7, 17));
                vSpacer11.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer11);

                //---- label18 ----
                label18.setText("18h");
                label18.setPreferredSize(new Dimension(25, 21));
                label18.setMinimumSize(new Dimension(25, 21));
                label18.setMaximumSize(new Dimension(25, 21));
                panel2.add(label18);

                //---- vSpacer12 ----
                vSpacer12.setPreferredSize(new Dimension(7, 17));
                vSpacer12.setMinimumSize(new Dimension(7, 17));
                vSpacer12.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer12);

                //---- label19 ----
                label19.setText("19h");
                label19.setPreferredSize(new Dimension(25, 21));
                label19.setMinimumSize(new Dimension(25, 21));
                label19.setMaximumSize(new Dimension(25, 21));
                panel2.add(label19);

                //---- vSpacer13 ----
                vSpacer13.setPreferredSize(new Dimension(7, 17));
                vSpacer13.setMinimumSize(new Dimension(7, 17));
                vSpacer13.setMaximumSize(new Dimension(32762, 17));
                panel2.add(vSpacer13);

                //---- label20 ----
                label20.setText("20h");
                label20.setPreferredSize(new Dimension(25, 21));
                label20.setMinimumSize(new Dimension(25, 21));
                label20.setMaximumSize(new Dimension(25, 21));
                panel2.add(label20);
            }

            //======== panel10 ========
            {
                panel10.setLayout(new BoxLayout(panel10, BoxLayout.Y_AXIS));

                //---- labelMardi ----
                labelMardi.setText("Mardi");
                panel10.add(labelMardi);

                //======== mardi ========
                {
                    mardi.setBackground(new Color(238, 238, 238));
                    mardi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            mardiMouseClicked(e);
                        }
                    });

                    GroupLayout mardiLayout = new GroupLayout(mardi);
                    mardi.setLayout(mardiLayout);
                    mardiLayout.setHorizontalGroup(
                        mardiLayout.createParallelGroup()
                            .addGap(0, 200, Short.MAX_VALUE)
                    );
                    mardiLayout.setVerticalGroup(
                        mardiLayout.createParallelGroup()
                            .addGap(0, 484, Short.MAX_VALUE)
                    );
                }
                panel10.add(mardi);
            }

            //======== panel11 ========
            {
                panel11.setLayout(new BoxLayout(panel11, BoxLayout.Y_AXIS));

                //---- labelLundi ----
                labelLundi.setText("Lundi");
                panel11.add(labelLundi);

                //======== lundi ========
                {
                    lundi.setBackground(new Color(238, 238, 238));
                    lundi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            panel2MouseClicked(e);
                        }
                    });

                    GroupLayout lundiLayout = new GroupLayout(lundi);
                    lundi.setLayout(lundiLayout);
                    lundiLayout.setHorizontalGroup(
                        lundiLayout.createParallelGroup()
                            .addGap(0, 200, Short.MAX_VALUE)
                    );
                    lundiLayout.setVerticalGroup(
                        lundiLayout.createParallelGroup()
                            .addGap(0, 484, Short.MAX_VALUE)
                    );
                }
                panel11.add(lundi);
            }

            //======== panel12 ========
            {
                panel12.setLayout(new BoxLayout(panel12, BoxLayout.Y_AXIS));

                //---- labelMercredi ----
                labelMercredi.setText("Mercredi");
                panel12.add(labelMercredi);

                //======== mercredi ========
                {
                    mercredi.setBackground(new Color(238, 238, 238));
                    mercredi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            mercrediMouseClicked(e);
                        }
                    });

                    GroupLayout mercrediLayout = new GroupLayout(mercredi);
                    mercredi.setLayout(mercrediLayout);
                    mercrediLayout.setHorizontalGroup(
                        mercrediLayout.createParallelGroup()
                            .addGap(0, 200, Short.MAX_VALUE)
                    );
                    mercrediLayout.setVerticalGroup(
                        mercrediLayout.createParallelGroup()
                            .addGap(0, 484, Short.MAX_VALUE)
                    );
                }
                panel12.add(mercredi);
            }

            //======== panel13 ========
            {
                panel13.setLayout(new BoxLayout(panel13, BoxLayout.Y_AXIS));

                //---- labelJeudi ----
                labelJeudi.setText("Jeudi");
                panel13.add(labelJeudi);

                //======== jeudi ========
                {
                    jeudi.setBackground(new Color(238, 238, 238));
                    jeudi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            jeudiMouseClicked(e);
                        }
                    });

                    GroupLayout jeudiLayout = new GroupLayout(jeudi);
                    jeudi.setLayout(jeudiLayout);
                    jeudiLayout.setHorizontalGroup(
                        jeudiLayout.createParallelGroup()
                            .addGap(0, 200, Short.MAX_VALUE)
                    );
                    jeudiLayout.setVerticalGroup(
                        jeudiLayout.createParallelGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                    );
                }
                panel13.add(jeudi);
            }

            //======== panel14 ========
            {
                panel14.setLayout(new BoxLayout(panel14, BoxLayout.Y_AXIS));

                //---- labelVendredi ----
                labelVendredi.setText("Vendredi");
                panel14.add(labelVendredi);

                //======== vendredi ========
                {
                    vendredi.setBackground(new Color(238, 238, 238));
                    vendredi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            vendrediMouseClicked(e);
                        }
                    });

                    GroupLayout vendrediLayout = new GroupLayout(vendredi);
                    vendredi.setLayout(vendrediLayout);
                    vendrediLayout.setHorizontalGroup(
                        vendrediLayout.createParallelGroup()
                            .addGap(0, 200, Short.MAX_VALUE)
                    );
                    vendrediLayout.setVerticalGroup(
                        vendrediLayout.createParallelGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                    );
                }
                panel14.add(vendredi);
            }

            //======== panel15 ========
            {
                panel15.setLayout(new BoxLayout(panel15, BoxLayout.Y_AXIS));

                //---- labelSamedi ----
                labelSamedi.setText("Samedi");
                panel15.add(labelSamedi);

                //======== samedi ========
                {
                    samedi.setBackground(new Color(238, 238, 238));
                    samedi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            samediMouseClicked(e);
                        }
                    });

                    GroupLayout samediLayout = new GroupLayout(samedi);
                    samedi.setLayout(samediLayout);
                    samediLayout.setHorizontalGroup(
                        samediLayout.createParallelGroup()
                            .addGap(0, 200, Short.MAX_VALUE)
                    );
                    samediLayout.setVerticalGroup(
                        samediLayout.createParallelGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                    );
                }
                panel15.add(samedi);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel11, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel10, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel12, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel13, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel14, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel15, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(panel15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel12, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
            );
        }

        //---- intituleFormation ----
        intituleFormation.setText("text");

        //---- validerEmploi ----
        validerEmploi.setText("Valider");
        validerEmploi.addActionListener(e -> validerEmploiActionPerformed(e));

        //---- annulerEmploi ----
        annulerEmploi.setText("Retour");
        annulerEmploi.addActionListener(e -> annulerEmploiActionPerformed(e));

        //======== scrollPane4 ========
        {

            //---- affichageSemaine ----
            affichageSemaine.setFont(new Font("Tibetan Machine Uni", Font.PLAIN, 14));
            affichageSemaine.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    affichageSemaineMouseClicked(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    affichageSemaineMouseReleased(e);
                }
            });
            affichageSemaine.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    affichageSemaineKeyReleased(e);
                }
            });
            scrollPane4.setViewportView(affichageSemaine);
        }

        //---- vSpacer1 ----
        vSpacer1.setPreferredSize(new Dimension(44, 10));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(intituleFormation)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(vSpacer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(489, 489, 489))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(141, 141, 141)
                    .addComponent(validerEmploi, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(annulerEmploi, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addGap(243, 243, 243))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane4))
                    .addGap(16, 16, 16)
                    .addComponent(semaineActuelle))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(intituleFormation)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(89, 89, 89)
                            .addComponent(semaineActuelle)
                            .addGap(674, 674, 674))
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(annulerEmploi, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addComponent(validerEmploi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                    .addComponent(vSpacer1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(44, 44, 44))
        );
        setSize(1305, 675);
        setLocationRelativeTo(getOwner());

        //======== creationSeance ========
        {
            creationSeance.setTitle("creer");
            creationSeance.setResizable(false);
            Container creationSeanceContentPane = creationSeance.getContentPane();

            //---- label5 ----
            label5.setText("Nom de la mati\u00e8re");

            //---- label6 ----
            label6.setText("Professeur");

            //---- label7 ----
            label7.setText("Heure de debut");

            //---- label8 ----
            label8.setText("Heure de fin");

            //---- hDebutH ----
            hDebutH.setModel(new DefaultComboBoxModel<>(new String[] {
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20"
            }));

            //---- hDebutM ----
            hDebutM.setModel(new DefaultComboBoxModel<>(new String[] {
                "00",
                "15",
                "30",
                "45"
            }));

            //---- hFinH ----
            hFinH.setModel(new DefaultComboBoxModel<>(new String[] {
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20",
                "21"
            }));

            //---- hFinM ----
            hFinM.setModel(new DefaultComboBoxModel<>(new String[] {
                "00",
                "15",
                "30",
                "45"
            }));

            //---- label2 ----
            label2.setText("Salle");

            //---- nomSalle ----
            nomSalle.setSelectedIndex(-1);

            //---- nomMatiere ----
            nomMatiere.addItemListener(e -> nomMatiereItemStateChanged(e));

            //---- validercours2 ----
            validercours2.setText("valider");
            validercours2.addActionListener(e -> validercoursActionPerformed(e));

            GroupLayout creationSeanceContentPaneLayout = new GroupLayout(creationSeanceContentPane);
            creationSeanceContentPane.setLayout(creationSeanceContentPaneLayout);
            creationSeanceContentPaneLayout.setHorizontalGroup(
                creationSeanceContentPaneLayout.createParallelGroup()
                    .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                            .addComponent(label5)
                            .addComponent(label6)
                            .addComponent(label2)
                            .addComponent(label7)
                            .addComponent(label8))
                        .addGap(12, 12, 12)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                            .addComponent(validercours2)
                            .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(nomSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                    .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(hDebutH, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hFinH, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                                        .addComponent(hDebutM, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hFinM, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
                                .addComponent(nomEnseignant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(nomMatiere, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(103, Short.MAX_VALUE))
            );
            creationSeanceContentPaneLayout.setVerticalGroup(
                creationSeanceContentPaneLayout.createParallelGroup()
                    .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label5)
                            .addComponent(nomMatiere, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label6)
                            .addComponent(nomEnseignant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(nomSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(hDebutM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(hDebutH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label8)
                            .addComponent(hFinM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(hFinH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(validercours2)
                        .addContainerGap(12, Short.MAX_VALUE))
            );
            creationSeance.pack();
            creationSeance.setLocationRelativeTo(creationSeance.getOwner());
        }

        //======== fenetreDebut ========
        {
            fenetreDebut.setResizable(false);
            fenetreDebut.setBackground(Color.black);
            Container fenetreDebutContentPane = fenetreDebut.getContentPane();

            //======== tabbedPane1 ========
            {

                //======== panel3 ========
                {
                    panel3.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                    swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border
                    .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067"
                    ,java.awt.Font.BOLD,12),java.awt.Color.red),panel3. getBorder
                    ()));panel3. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
                    .beans.PropertyChangeEvent e){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException
                    ();}});

                    //---- label3 ----
                    label3.setText("Gestion emploi du temps");
                    label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));

                    //---- label1 ----
                    label1.setText("Choix de formation");

                    //---- choixFormationBox ----
                    choixFormationBox.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Maths",
                        "Physique",
                        "Informatique",
                        "Biologie",
                        "Arts",
                        "Musique",
                        "Staps",
                        "Lettres"
                    }));
                    choixFormationBox.addActionListener(e -> choixFormationBoxActionPerformed(e));

                    //---- boutonCreerEmploi ----
                    boutonCreerEmploi.setText("creer");
                    boutonCreerEmploi.addActionListener(e -> boutonCreerEmploiActionPerformed(e));

                    //---- bouttonMofierEmploi ----
                    bouttonMofierEmploi.setText("Modifier");
                    bouttonMofierEmploi.addActionListener(e -> bouttonMofierEmploiActionPerformed(e));

                    //---- button2 ----
                    button2.setText("Supprimer");
                    button2.addActionListener(e -> supprimerSeance(e));

                    //---- logoutButton ----
                    logoutButton.setIcon(new ImageIcon(getClass().getResource("/logout.png")));
                    logoutButton.addActionListener(e -> logoutButtonActionPerformed(e));

                    GroupLayout panel3Layout = new GroupLayout(panel3);
                    panel3.setLayout(panel3Layout);
                    panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(192, 192, 192)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(208, Short.MAX_VALUE))
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addComponent(label1)
                                .addGap(77, 77, 77)
                                .addComponent(choixFormationBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 161, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addContainerGap(102, Short.MAX_VALUE)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addComponent(boutonCreerEmploi, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(bouttonMofierEmploi, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                                        .addGap(82, 82, 82))))
                    );
                    panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(logoutButton)
                                .addGap(4, 4, 4)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1)
                                    .addComponent(choixFormationBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(boutonCreerEmploi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bouttonMofierEmploi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50))
                    );
                }
                tabbedPane1.addTab("Creation Emploi", panel3);

                //======== panel4 ========
                {

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(tableEnseignants);
                    }

                    //---- associerBouton ----
                    associerBouton.setText("Associer");
                    associerBouton.addActionListener(e -> associerBoutonActionPerformed(e));

                    //======== scrollPane2 ========
                    {

                        //---- tableMatiere ----
                        tableMatiere.setModel(new DefaultTableModel(
                            new Object[][] {
                                {null},
                                {null},
                            },
                            new String[] {
                                null
                            }
                        ));
                        scrollPane2.setViewportView(tableMatiere);
                    }

                    GroupLayout panel4Layout = new GroupLayout(panel4);
                    panel4.setLayout(panel4Layout);
                    panel4Layout.setHorizontalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(nomMatiere2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                                .addComponent(associerBouton)
                                .addGap(179, 179, 179))
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                    panel4Layout.setVerticalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                                .addGap(56, 56, 56)
                                .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(nomMatiere2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(associerBouton))
                                .addContainerGap())
                    );
                }
                tabbedPane1.addTab("Association Matieres/Profs", panel4);

                //======== panelListeReservation2 ========
                {

                    //======== panel9 ========
                    {

                        //======== scrollPane5 ========
                        {

                            //---- tableListeReservation2 ----
                            tableListeReservation2.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    tableListeReservation2MouseClicked(e);
                                }
                            });
                            scrollPane5.setViewportView(tableListeReservation2);
                        }

                        //---- bouttonConfirmReservation ----
                        bouttonConfirmReservation.setText("Confirmer");
                        bouttonConfirmReservation.addActionListener(e -> {
			button6ActionPerformed(e);
			bouttonConfirmReservationActionPerformed(e);
		});

                        //---- boutonRefuseReservation ----
                        boutonRefuseReservation.setText("Refuser");
                        boutonRefuseReservation.addActionListener(e -> boutonRefuseReservationActionPerformed(e));

                        GroupLayout panel9Layout = new GroupLayout(panel9);
                        panel9.setLayout(panel9Layout);
                        panel9Layout.setHorizontalGroup(
                            panel9Layout.createParallelGroup()
                                .addGroup(panel9Layout.createSequentialGroup()
                                    .addGap(124, 124, 124)
                                    .addComponent(bouttonConfirmReservation, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                                    .addComponent(boutonRefuseReservation, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                    .addGap(135, 135, 135))
                                .addGroup(panel9Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane5, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                        panel9Layout.setVerticalGroup(
                            panel9Layout.createParallelGroup()
                                .addGroup(panel9Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panel9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(bouttonConfirmReservation)
                                        .addComponent(boutonRefuseReservation))
                                    .addGap(29, 29, 29))
                        );
                    }

                    GroupLayout panelListeReservation2Layout = new GroupLayout(panelListeReservation2);
                    panelListeReservation2.setLayout(panelListeReservation2Layout);
                    panelListeReservation2Layout.setHorizontalGroup(
                        panelListeReservation2Layout.createParallelGroup()
                            .addGroup(panelListeReservation2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                    panelListeReservation2Layout.setVerticalGroup(
                        panelListeReservation2Layout.createParallelGroup()
                            .addGroup(panelListeReservation2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel9, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                }
                tabbedPane1.addTab("Liste des Demandes", panelListeReservation2);
            }

            GroupLayout fenetreDebutContentPaneLayout = new GroupLayout(fenetreDebutContentPane);
            fenetreDebutContentPane.setLayout(fenetreDebutContentPaneLayout);
            fenetreDebutContentPaneLayout.setHorizontalGroup(
                fenetreDebutContentPaneLayout.createParallelGroup()
                    .addComponent(tabbedPane1, GroupLayout.Alignment.TRAILING)
            );
            fenetreDebutContentPaneLayout.setVerticalGroup(
                fenetreDebutContentPaneLayout.createParallelGroup()
                    .addGroup(fenetreDebutContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            fenetreDebut.setSize(650, 345);
            fenetreDebut.setLocationRelativeTo(fenetreDebut.getOwner());
        }

        //======== modifierSeance ========
        {
            modifierSeance.setTitle("modifiercde Creer");
            modifierSeance.setResizable(false);
            Container modifierSeanceContentPane = modifierSeance.getContentPane();

            //---- label21 ----
            label21.setText("Nom de la mati\u00e8re");

            //---- label28 ----
            label28.setText("Professeur");

            //---- label29 ----
            label29.setText("Heure de d\u00e9but");

            //---- label30 ----
            label30.setText("Heure de fin");

            //---- hDebutH2 ----
            hDebutH2.setModel(new DefaultComboBoxModel<>(new String[] {
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20"
            }));

            //---- hDebutM2 ----
            hDebutM2.setModel(new DefaultComboBoxModel<>(new String[] {
                "00",
                "15",
                "30",
                "45"
            }));

            //---- hFinH2 ----
            hFinH2.setModel(new DefaultComboBoxModel<>(new String[] {
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20",
                "21"
            }));

            //---- hFinM2 ----
            hFinM2.setModel(new DefaultComboBoxModel<>(new String[] {
                "00",
                "15",
                "30",
                "45"
            }));

            //---- label31 ----
            label31.setText("Salle");

            //---- nomSalle2 ----
            nomSalle2.setSelectedIndex(-1);

            //---- nomMatiere3 ----
            nomMatiere3.addItemListener(e -> nomMatiereItemStateChanged(e));

            //---- button1 ----
            button1.setText("modifier");
            button1.addActionListener(e -> button1ActionPerformed(e));

            //---- button3 ----
            button3.setText("annuler");

            //---- suppSeanceBouton ----
            suppSeanceBouton.setText("supprimer");
            suppSeanceBouton.addActionListener(e -> suppSeanceBoutonActionPerformed(e));

            GroupLayout modifierSeanceContentPaneLayout = new GroupLayout(modifierSeanceContentPane);
            modifierSeanceContentPane.setLayout(modifierSeanceContentPaneLayout);
            modifierSeanceContentPaneLayout.setHorizontalGroup(
                modifierSeanceContentPaneLayout.createParallelGroup()
                    .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                            .addComponent(label21)
                            .addComponent(label28)
                            .addComponent(label30)
                            .addComponent(button3)
                            .addComponent(label29)
                            .addComponent(label31))
                        .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                            .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(nomSalle2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                            .addComponent(hFinH2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                            .addGap(34, 34, 34)
                                            .addComponent(hFinM2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                            .addComponent(hDebutH2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(hDebutM2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(nomEnseignant2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomMatiere3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(button1)
                                .addGap(41, 41, 41)
                                .addComponent(suppSeanceBouton)))
                        .addContainerGap(100, Short.MAX_VALUE))
            );
            modifierSeanceContentPaneLayout.setVerticalGroup(
                modifierSeanceContentPaneLayout.createParallelGroup()
                    .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label21)
                            .addComponent(nomMatiere3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label28)
                            .addComponent(nomEnseignant2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                            .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label31)
                                    .addComponent(nomSalle2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(60, 60, 60)
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                                    .addComponent(hFinM2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(hFinH2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label30))))
                            .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label29)
                                    .addComponent(hDebutH2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hDebutM2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                        .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                            .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button3)
                                .addComponent(button1))
                            .addComponent(suppSeanceBouton))
                        .addContainerGap(18, Short.MAX_VALUE))
            );
            modifierSeance.pack();
            modifierSeance.setLocationRelativeTo(modifierSeance.getOwner());
        }

        //======== fenetreAuthentification ========
        {
            Container fenetreAuthentificationContentPane = fenetreAuthentification.getContentPane();

            //======== panel5 ========
            {
                panel5.setBorder(new TitledBorder(null, "Authentification", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.blue));
                panel5.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        panel5KeyPressed(e);
                    }
                });
                panel5.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
                . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing
                . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
                Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
                ) ,panel5. getBorder( )) ); panel5. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
                public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName (
                ) )) throw new RuntimeException( ); }} );

                //---- inputLogin ----
                inputLogin.setText("asayadi246");
                inputLogin.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        inputLoginKeyPressed(e);
                    }
                });

                //---- inputPassword ----
                inputPassword.setText("azerty");

                //---- loginButton ----
                loginButton.setText("Se connecter");
                loginButton.addActionListener(e -> loginButtonActionPerformed(e));
                loginButton.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        loginButtonKeyPressed(e);
                        loginButtonKeyPressed(e);
                    }
                });

                //---- button5 ----
                button5.setText("Annuler");
                button5.addActionListener(e -> button5ActionPerformed(e));

                //---- label32 ----
                label32.setText("login");

                //---- label33 ----
                label33.setText("mot de passe");

                //---- label34 ----
                label34.setText("text");

                GroupLayout panel5Layout = new GroupLayout(panel5);
                panel5.setLayout(panel5Layout);
                panel5Layout.setHorizontalGroup(
                    panel5Layout.createParallelGroup()
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addGap(94, 94, 94)
                            .addGroup(panel5Layout.createParallelGroup()
                                .addComponent(label34)
                                .addGroup(panel5Layout.createSequentialGroup()
                                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(button5, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel5Layout.createSequentialGroup()
                                    .addGroup(panel5Layout.createParallelGroup()
                                        .addComponent(label32)
                                        .addComponent(label33))
                                    .addGap(78, 78, 78)
                                    .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(inputLogin)
                                        .addComponent(inputPassword, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))))
                            .addContainerGap(31, Short.MAX_VALUE))
                );
                panel5Layout.setVerticalGroup(
                    panel5Layout.createParallelGroup()
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(label34)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(inputLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label32))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(inputPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label33))
                            .addGap(42, 42, 42)
                            .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(loginButton)
                                .addComponent(button5))
                            .addContainerGap(45, Short.MAX_VALUE))
                );
            }

            GroupLayout fenetreAuthentificationContentPaneLayout = new GroupLayout(fenetreAuthentificationContentPane);
            fenetreAuthentificationContentPane.setLayout(fenetreAuthentificationContentPaneLayout);
            fenetreAuthentificationContentPaneLayout.setHorizontalGroup(
                fenetreAuthentificationContentPaneLayout.createParallelGroup()
                    .addGroup(fenetreAuthentificationContentPaneLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(36, Short.MAX_VALUE))
            );
            fenetreAuthentificationContentPaneLayout.setVerticalGroup(
                fenetreAuthentificationContentPaneLayout.createParallelGroup()
                    .addGroup(fenetreAuthentificationContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
            );
            fenetreAuthentification.pack();
            fenetreAuthentification.setLocationRelativeTo(fenetreAuthentification.getOwner());
        }

        //======== fenetreEnseignant ========
        {
            Container fenetreEnseignantContentPane = fenetreEnseignant.getContentPane();

            //======== menuBar1 ========
            {

                //======== menu1 ========
                {
                    menu1.setText("Emploi");

                    //---- consulterEnseignantEmploi ----
                    consulterEnseignantEmploi.setText("Consulter emploi");
                    consulterEnseignantEmploi.addActionListener(e -> {
			menuItem3ActionPerformed(e);
			menuItem3ActionPerformed(e);
			menuItem3ActionPerformed(e);
			menuItem3ActionPerformed(e);
			menuItem3ActionPerformed(e);
			consulterEnseignantEmploiActionPerformed(e);
		});
                    menu1.add(consulterEnseignantEmploi);
                }
                menuBar1.add(menu1);

                //======== menu2 ========
                {
                    menu2.setText("R\u00e9servation");

                    //---- menuItem1 ----
                    menuItem1.setText("r\u00e9server une salle");
                    menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                    menu2.add(menuItem1);

                    //---- menuItem2 ----
                    menuItem2.setText("Liste des r\u00e9servations");
                    menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                    menu2.add(menuItem2);
                }
                menuBar1.add(menu2);

                //---- hSpacer1 ----
                hSpacer1.setPreferredSize(new Dimension(500, 10));
                menuBar1.add(hSpacer1);

                //======== menu3 ========
                {
                    menu3.setText("Statut");

                    //---- menuItem3 ----
                    menuItem3.setText("deconnexion");
                    menuItem3.addActionListener(e -> logoutButtonActionPerformed(e));
                    menu3.add(menuItem3);
                }
                menuBar1.add(menu3);
            }
            fenetreEnseignant.setJMenuBar(menuBar1);

            //======== parentPanel ========
            {
                parentPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
                0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
                . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
                red) ,parentPanel. getBorder( )) ); parentPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
                beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                parentPanel.setLayout(new CardLayout());

                //======== panelEmploi ========
                {

                    GroupLayout panelEmploiLayout = new GroupLayout(panelEmploi);
                    panelEmploi.setLayout(panelEmploiLayout);
                    panelEmploiLayout.setHorizontalGroup(
                        panelEmploiLayout.createParallelGroup()
                            .addGap(0, 788, Short.MAX_VALUE)
                    );
                    panelEmploiLayout.setVerticalGroup(
                        panelEmploiLayout.createParallelGroup()
                            .addGap(0, 426, Short.MAX_VALUE)
                    );
                }
                parentPanel.add(panelEmploi, "card1");

                //======== panelReserverSalle ========
                {

                    //======== panelSalle ========
                    {
                        panelSalle.setBorder(new TitledBorder("R\u00e9server une salle"));

                        //---- buttonDemandeReserv ----
                        buttonDemandeReserv.setText("Envoyer demande");
                        buttonDemandeReserv.addActionListener(e -> {
			button6ActionPerformed(e);
			buttonDemandeReservActionPerformed(e);
		});

                        //======== panel6 ========
                        {
                            panel6.setBorder(null);

                            //---- label35 ----
                            label35.setText("Date de r\u00e9servation");
                            label35.setVisible(false);

                            //---- button4 ----
                            button4.setText("Valider");

                            //======== scrollPane6 ========
                            {
                                scrollPane6.setViewportView(tableSeances);
                            }

                            //======== scrollPaneSalle ========
                            {
                                scrollPaneSalle.setViewportView(tableSalles);
                            }

                            //---- label36 ----
                            label36.setText("Vos Seances");

                            //---- label37 ----
                            label37.setText("Salles disponibles");

                            GroupLayout panel6Layout = new GroupLayout(panel6);
                            panel6.setLayout(panel6Layout);
                            panel6Layout.setHorizontalGroup(
                                panel6Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(panel6Layout.createParallelGroup()
                                            .addComponent(label35)
                                            .addGroup(panel6Layout.createSequentialGroup()
                                                .addComponent(scrollPane6, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(scrollPaneSalle, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(35, 35, 35)
                                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panel6Layout.createSequentialGroup()
                                        .addGap(171, 171, 171)
                                        .addComponent(label36)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label37)
                                        .addGap(272, 272, 272))
                            );
                            panel6Layout.setVerticalGroup(
                                panel6Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                                        .addComponent(label35)
                                        .addGroup(panel6Layout.createParallelGroup()
                                            .addGroup(panel6Layout.createSequentialGroup()
                                                .addGap(198, 198, 198)
                                                .addComponent(button4, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 96, Short.MAX_VALUE))
                                            .addGroup(panel6Layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(label36)
                                                    .addComponent(label37))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panel6Layout.createParallelGroup()
                                                    .addComponent(scrollPaneSalle, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(scrollPane6, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))))
                                        .addContainerGap())
                            );
                        }

                        GroupLayout panelSalleLayout = new GroupLayout(panelSalle);
                        panelSalle.setLayout(panelSalleLayout);
                        panelSalleLayout.setHorizontalGroup(
                            panelSalleLayout.createParallelGroup()
                                .addGroup(panelSalleLayout.createSequentialGroup()
                                    .addGap(64, 64, 64)
                                    .addComponent(inputdateReSalle, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                    .addGap(233, 233, 233)
                                    .addComponent(buttonDemandeReserv)
                                    .addGap(0, 315, Short.MAX_VALUE))
                                .addGroup(panelSalleLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panel6, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                        panelSalleLayout.setVerticalGroup(
                            panelSalleLayout.createParallelGroup()
                                .addGroup(panelSalleLayout.createSequentialGroup()
                                    .addComponent(panel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelSalleLayout.createParallelGroup()
                                        .addGroup(panelSalleLayout.createSequentialGroup()
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inputdateReSalle, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap())
                                        .addGroup(panelSalleLayout.createSequentialGroup()
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(buttonDemandeReserv)
                                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        );
                    }

                    GroupLayout panelReserverSalleLayout = new GroupLayout(panelReserverSalle);
                    panelReserverSalle.setLayout(panelReserverSalleLayout);
                    panelReserverSalleLayout.setHorizontalGroup(
                        panelReserverSalleLayout.createParallelGroup()
                            .addGroup(panelReserverSalleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelSalle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                    panelReserverSalleLayout.setVerticalGroup(
                        panelReserverSalleLayout.createParallelGroup()
                            .addGroup(panelReserverSalleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(54, Short.MAX_VALUE))
                    );
                }
                parentPanel.add(panelReserverSalle, "card2");

                //======== panelListeReservation ========
                {

                    //======== panel7 ========
                    {

                        //======== scrollPane3 ========
                        {
                            scrollPane3.setViewportView(tableListeReservation);
                        }

                        GroupLayout panel7Layout = new GroupLayout(panel7);
                        panel7.setLayout(panel7Layout);
                        panel7Layout.setHorizontalGroup(
                            panel7Layout.createParallelGroup()
                                .addGroup(panel7Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                        panel7Layout.setVerticalGroup(
                            panel7Layout.createParallelGroup()
                                .addGroup(panel7Layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                    .addGap(24, 24, 24))
                        );
                    }

                    GroupLayout panelListeReservationLayout = new GroupLayout(panelListeReservation);
                    panelListeReservation.setLayout(panelListeReservationLayout);
                    panelListeReservationLayout.setHorizontalGroup(
                        panelListeReservationLayout.createParallelGroup()
                            .addGroup(panelListeReservationLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(panel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(175, Short.MAX_VALUE))
                    );
                    panelListeReservationLayout.setVerticalGroup(
                        panelListeReservationLayout.createParallelGroup()
                            .addGroup(panelListeReservationLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(panel7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(55, Short.MAX_VALUE))
                    );
                }
                parentPanel.add(panelListeReservation, "card3");
            }

            GroupLayout fenetreEnseignantContentPaneLayout = new GroupLayout(fenetreEnseignantContentPane);
            fenetreEnseignantContentPane.setLayout(fenetreEnseignantContentPaneLayout);
            fenetreEnseignantContentPaneLayout.setHorizontalGroup(
                fenetreEnseignantContentPaneLayout.createParallelGroup()
                    .addComponent(parentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            fenetreEnseignantContentPaneLayout.setVerticalGroup(
                fenetreEnseignantContentPaneLayout.createParallelGroup()
                    .addGroup(fenetreEnseignantContentPaneLayout.createSequentialGroup()
                        .addComponent(parentPanel, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
            );
            fenetreEnseignant.pack();
            fenetreEnseignant.setLocationRelativeTo(fenetreEnseignant.getOwner());
        }

        //======== descriptionReservation ========
        {
            Container descriptionReservationContentPane = descriptionReservation.getContentPane();

            //---- label22 ----
            label22.setText("R\u00e9servation n\u00b0 ");
            label22.setFont(label22.getFont().deriveFont(label22.getFont().getSize() + 13f));

            //---- label23 ----
            label23.setText("Nom de la salle");

            //---- formattedTextField2 ----
            formattedTextField2.setEditable(false);

            //---- label24 ----
            label24.setText("Enseignant");

            //---- label25 ----
            label25.setText("Date de R\u00e9servation");

            //---- label27 ----
            label27.setText("S\u00e9ance");

            //======== scrollPane7 ========
            {
                scrollPane7.setViewportView(textArea1);
            }

            //---- label38 ----
            label38.setText("Justification");

            GroupLayout descriptionReservationContentPaneLayout = new GroupLayout(descriptionReservationContentPane);
            descriptionReservationContentPane.setLayout(descriptionReservationContentPaneLayout);
            descriptionReservationContentPaneLayout.setHorizontalGroup(
                descriptionReservationContentPaneLayout.createParallelGroup()
                    .addGroup(descriptionReservationContentPaneLayout.createSequentialGroup()
                        .addGroup(descriptionReservationContentPaneLayout.createParallelGroup()
                            .addGroup(descriptionReservationContentPaneLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(descriptionReservationContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label22, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(GroupLayout.Alignment.TRAILING, descriptionReservationContentPaneLayout.createSequentialGroup()
                                        .addGroup(descriptionReservationContentPaneLayout.createParallelGroup()
                                            .addComponent(label23)
                                            .addComponent(formattedTextField2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                        .addGroup(descriptionReservationContentPaneLayout.createParallelGroup()
                                            .addComponent(label24)
                                            .addComponent(formattedTextField3, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 22, Short.MAX_VALUE))
                                    .addGroup(descriptionReservationContentPaneLayout.createSequentialGroup()
                                        .addComponent(label25)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(formattedTextField4, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label27)
                                    .addGroup(descriptionReservationContentPaneLayout.createSequentialGroup()
                                        .addComponent(label38)
                                        .addGap(18, 18, 18)
                                        .addComponent(textArea2, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(descriptionReservationContentPaneLayout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(scrollPane7, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(54, Short.MAX_VALUE))
            );
            descriptionReservationContentPaneLayout.setVerticalGroup(
                descriptionReservationContentPaneLayout.createParallelGroup()
                    .addGroup(descriptionReservationContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label22, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(descriptionReservationContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label23)
                            .addComponent(label24))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(descriptionReservationContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(formattedTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(formattedTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(descriptionReservationContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label25)
                            .addComponent(formattedTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(descriptionReservationContentPaneLayout.createParallelGroup()
                            .addComponent(label27)
                            .addComponent(scrollPane7, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(descriptionReservationContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textArea2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label38))
                        .addContainerGap(17, Short.MAX_VALUE))
            );
            descriptionReservation.pack();
            descriptionReservation.setLocationRelativeTo(descriptionReservation.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Haribou Abdallah
    private JPanel panel1;
    private JPanel panel2;
    private JPanel vSpacer2;
    private JLabel label4;
    private JPanel vSpacer14;
    private JLabel label9;
    private JPanel vSpacer3;
    private JLabel label10;
    private JPanel vSpacer4;
    private JLabel label11;
    private JPanel vSpacer5;
    private JLabel label12;
    private JPanel vSpacer6;
    private JLabel label13;
    private JPanel vSpacer7;
    private JLabel label14;
    private JPanel vSpacer8;
    private JLabel label15;
    private JPanel vSpacer9;
    private JLabel label16;
    private JPanel vSpacer10;
    private JLabel label17;
    private JPanel vSpacer11;
    private JLabel label18;
    private JPanel vSpacer12;
    private JLabel label19;
    private JPanel vSpacer13;
    private JLabel label20;
    private JPanel panel10;
    private JLabel labelMardi;
    private JPanel mardi;
    private JPanel panel11;
    private JLabel labelLundi;
    private JPanel lundi;
    private JPanel panel12;
    private JLabel labelMercredi;
    private JPanel mercredi;
    private JPanel panel13;
    private JLabel labelJeudi;
    private JPanel jeudi;
    private JPanel panel14;
    private JLabel labelVendredi;
    private JPanel vendredi;
    private JPanel panel15;
    private JLabel labelSamedi;
    private JPanel samedi;
    private JLabel intituleFormation;
    private JButton validerEmploi;
    private JButton annulerEmploi;
    private JLabel semaineActuelle;
    private JScrollPane scrollPane4;
    private JTable affichageSemaine;
    private JPanel vSpacer1;
    private JFrame creationSeance;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JComboBox<String> hDebutH;
    private JComboBox<String> hDebutM;
    private JComboBox<String> hFinH;
    private JComboBox<String> hFinM;
    private JLabel label2;
    private JComboBox nomSalle;
    private JComboBox nomMatiere;
    private JComboBox nomEnseignant;
    private JButton validercours2;
    private JFrame fenetreDebut;
    private JTabbedPane tabbedPane1;
    private JPanel panel3;
    private JLabel label3;
    private JLabel label1;
    private JComboBox<String> choixFormationBox;
    private JButton boutonCreerEmploi;
    private JButton bouttonMofierEmploi;
    private JButton button2;
    private JButton logoutButton;
    private JPanel panel4;
    private JComboBox nomMatiere2;
    private JScrollPane scrollPane1;
    private JTable tableEnseignants;
    private JButton associerBouton;
    private JScrollPane scrollPane2;
    private JTable tableMatiere;
    private JPanel panelListeReservation2;
    private JPanel panel9;
    private JScrollPane scrollPane5;
    private JTable tableListeReservation2;
    private JButton bouttonConfirmReservation;
    private JButton boutonRefuseReservation;
    private JFrame modifierSeance;
    private JLabel label21;
    private JLabel label28;
    private JLabel label29;
    private JLabel label30;
    private JComboBox<String> hDebutH2;
    private JComboBox<String> hDebutM2;
    private JComboBox<String> hFinH2;
    private JComboBox<String> hFinM2;
    private JLabel label31;
    private JComboBox nomSalle2;
    private JComboBox nomMatiere3;
    private JComboBox nomEnseignant2;
    private JButton button1;
    private JButton button3;
    private JButton suppSeanceBouton;
    private JFrame fenetreAuthentification;
    private JPanel panel5;
    private JTextField inputLogin;
    private JPasswordField inputPassword;
    private JButton loginButton;
    private JButton button5;
    private JLabel label32;
    private JLabel label33;
    private JLabel label34;
    private JFrame fenetreEnseignant;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem consulterEnseignantEmploi;
    private JMenu menu2;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JPanel hSpacer1;
    private JMenu menu3;
    private JMenuItem menuItem3;
    private JPanel parentPanel;
    private JPanel panelEmploi;
    private JPanel panelReserverSalle;
    private JPanel panelSalle;
    private JButton buttonDemandeReserv;
    private JPanel panel6;
    private JLabel label35;
    private JButton button4;
    private JScrollPane scrollPane6;
    private JTable tableSeances;
    private JScrollPane scrollPaneSalle;
    private JTable tableSalles;
    private JLabel label36;
    private JLabel label37;
    private JDateChooser inputdateReSalle;
    private JPanel panelListeReservation;
    private JPanel panel7;
    private JScrollPane scrollPane3;
    private JTable tableListeReservation;
    private JDialog descriptionReservation;
    private JLabel label22;
    private JLabel label23;
    private JFormattedTextField formattedTextField2;
    private JLabel label24;
    private JFormattedTextField formattedTextField3;
    private JLabel label25;
    private JFormattedTextField formattedTextField4;
    private JLabel label27;
    private JScrollPane scrollPane7;
    private JTextArea textArea1;
    private JLabel label38;
    private JTextArea textArea2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
