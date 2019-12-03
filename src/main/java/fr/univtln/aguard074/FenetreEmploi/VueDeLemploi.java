/*
 * Created by JFormDesigner on Wed Nov 27 17:57:55 CET 2019
 */

package fr.univtln.aguard074.FenetreEmploi;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.plaf.*;

import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Matiere;
import fr.univtln.group_aha.Salle;
import fr.univtln.group_aha.Seance;

// Temporaire implements Obververs pour observer le modèle emploi du temps
/**
 * @author unknown
 */
public class VueDeLemploi extends JFrame implements Observer {

    ModeleEmploi modele;
    ControleurEmploi controleur;

    // A separer avec le reste plus tard
    /**
     * La variable jour peut prendre des valeurs allant de 1 à 7 (le 1 correspondant au dimanche: Compatibilité avec
     * Calendar)
     */
    private int jourSemaine;
    private int semaneAnnee;
    private JPanel[] joursSemainePanel;
    private List<Seance>[] emploiDuTemps;

    public VueDeLemploi(ModeleEmploi modele, ControleurEmploi controleur) {
        initComponents();
        this.semaneAnnee = 10;
        this.modele = modele;
        this.controleur = controleur;
        this.emploiDuTemps = modele.getEmploiDuTemps();

        // Temporaire
        this.modele.addObserver(this);

        DefaultComboBoxModel matierecomboBoxModel = new DefaultComboBoxModel(modele.getMatieres().toArray());
        nomMatiere.setModel(matierecomboBoxModel);

        DefaultComboBoxModel sallecomboBoxModel = new DefaultComboBoxModel(modele.getSalles().toArray());
        nomSalle.setModel(sallecomboBoxModel);

        joursSemainePanel = new JPanel[]{lundi, mardi, mercredi, jeudi, vendredi, samedi};
        lundi.setName("lundi");
        mardi.setName("mardi");
        mercredi.setName("mercredi");
        jeudi.setName("jeudi");
        vendredi.setName("vendredi");
        samedi.setName("samedi");

        this.fenetreDebut.setVisible(true);
    }

    private void button1ActionPerformed(ActionEvent e) {
        ArrayList debutCour = new ArrayList();

    }

    private void boutonCreerEmploiActionPerformed(ActionEvent e) {
        this.fenetreDebut.setVisible(false);
        this.setVisible(true);
        this.intituleFormation.setText(choixFormationBox.getSelectedItem().toString());

    }

    /**
     * Insère une séance dans l'emploi du temps (la semaine est fixée).
     * @param seance
     */
    private void affichageSeance(Seance seance) {
        JPanel panelle = new JPanel();
        creationSeance.setVisible(false);

        Calendar debut =seance.getHdebut();
        Calendar fin =seance.getHfin();
        int j = debut.get(Calendar.DAY_OF_WEEK) - 2;

        JPanel jourPanel = joursSemainePanel[j];
        jourPanel.add(panelle);
        System.out.println(jourPanel.getName());

        JLabel matiere = new JLabel(seance.getMatiere().getNom());
        JLabel proffeseur = new JLabel(seance.getEnseignant().getNom());
        JLabel salle = new JLabel(seance.getSalle().getNom());

        int debH = debut.get(Calendar.HOUR_OF_DAY) - 8;
        int finH = fin.get(Calendar.HOUR_OF_DAY) - 8;
        int debM = debut.get(Calendar.MINUTE)/15;
        int finM = fin.get(Calendar.MINUTE)/15;

        System.out.println("seance");
        creationSeance.setVisible(false);
        panelle.setLayout(null);

        System.out.println(debH);
        System.out.println(debM);

        System.out.println(finH);
        System.out.println(finM);

        int d =  (debH)*40 + (debM)*10;
        int f =  (finH)*40 + (finM)*10;

        System.out.println("Debut: " + d);
        System.out.println("Fin: " + f);

        panelle.setBounds(8, d,111,f - d);
        panelle.setBorder(BorderFactory.createLineBorder(Color.black));
        panelle.setBackground(Color.gray);
        matiere.setBounds(15,1,66,10);
        proffeseur.setBounds(15,15,66,10);
        salle.setBounds(15,30,66,15);

        panelle.add(matiere);
        panelle.add(proffeseur);
        panelle.add(salle);
    }

    @Override
    public void update(Observable observable, Object o) {
        // Temporaire juste pour tester l'ajout de seance, réecrit toutes les seances:
        // Doit evidemment changer. Comment ? Je sais pas encore.
        System.out.println("Mise a jour");
        for(JPanel panel: joursSemainePanel)
            panel.removeAll();

        for(Seance seance: emploiDuTemps[this.semaneAnnee]) {
            affichageSeance(seance);
        }
    }

    private void validercoursActionPerformed(ActionEvent e) {
        System.out.println("seance");

        Matiere matiere = (Matiere) this.nomMatiere.getSelectedItem();
        Enseignant enseignant = (Enseignant) this.nomEnseignant.getSelectedItem();
        Salle salle = (Salle) this.nomSalle.getSelectedItem();

        GregorianCalendar debutH = new GregorianCalendar();
        GregorianCalendar finH = new GregorianCalendar();

        debutH.set(GregorianCalendar.WEEK_OF_YEAR, this.semaneAnnee);
        debutH.set(GregorianCalendar.DAY_OF_WEEK,  this.jourSemaine);
        debutH.set(GregorianCalendar.HOUR_OF_DAY, hDebutH.getSelectedIndex() + 8);
        debutH.set(GregorianCalendar.MINUTE,  hDebutM.getSelectedIndex()*15);

        finH.set(GregorianCalendar.WEEK_OF_YEAR, this.semaneAnnee);
        finH.set(GregorianCalendar.DAY_OF_WEEK,  this.jourSemaine);
        finH.set(GregorianCalendar.HOUR_OF_DAY, hFinH.getSelectedIndex() + 8);
        finH.set(GregorianCalendar.MINUTE, hFinM.getSelectedIndex()*15);

        controleur.creerSeance(salle, enseignant, matiere, debutH, finH);
    }

    private void annulerEmploiActionPerformed(ActionEvent e) {
        this.dispose();

        lundi.removeAll();
        mardi.removeAll();
        mercredi.removeAll();
        jeudi.removeAll();
        vendredi.removeAll();
        samedi.removeAll();
        fenetreDebut.setVisible(true);
    }

    private void panel2MouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        this.jourSemaine = 2;
    }
    private void mardiMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        this.jourSemaine = 3;
    }

    private void mercrediMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        this.jourSemaine = 4;
    }

    private void jeudiMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        this.jourSemaine = 5;
    }

    private void vendrediMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        this.jourSemaine = 6;
    }

    private void samediMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        this.jourSemaine = 7;
    }

    private void validerEmploiActionPerformed(ActionEvent e) {
        controleur.creerEmploi();
    }

    private void semainePrecedenteActionPerformed(ActionEvent e) {
    }


    private void semaineSuivanteActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button3ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void associerBoutonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void nomMatiereItemStateChanged(ItemEvent e) {
        Matiere matiere = (Matiere) e.getItem();
        // Temporaire: Je sais pas comment changer la liste en paramètre de combobox sans créer un nouveau modele
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(matiere.getListeEnseignants().toArray());
        nomEnseignant.setModel(comboBoxModel);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Haribou Abdallah
        panel1 = new JPanel();
        lundi = new JPanel();
        lundi2 = new JPanel();
        mardi = new JPanel();
        mercredi = new JPanel();
        jeudi = new JPanel();
        vendredi = new JPanel();
        samedi = new JPanel();
        panel2 = new JPanel();
        label4 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        label19 = new JLabel();
        label20 = new JLabel();
        intituleFormation = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        label24 = new JLabel();
        label25 = new JLabel();
        label26 = new JLabel();
        label27 = new JLabel();
        validerEmploi = new JButton();
        annulerEmploi = new JButton();
        creationSeance = new JFrame();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        hDebutH = new JComboBox<>();
        hDebutM = new JComboBox<>();
        hFinH = new JComboBox<>();
        hFinM = new JComboBox<>();
        validercours = new JButton();
        label2 = new JLabel();
        nomSalle = new JComboBox();
        nomMatiere = new JComboBox();
        nomEnseignant = new JComboBox();
        fenetreDebut = new JFrame();
        tabbedPane1 = new JTabbedPane();
        panel3 = new JPanel();
        label3 = new JLabel();
        label1 = new JLabel();
        choixFormationBox = new JComboBox<>();
        boutonCreerEmploi = new JButton();
        panel4 = new JPanel();
        nomMatiere2 = new JComboBox();
        scrollPane1 = new JScrollPane();
        tableEnseignants = new JTable();
        associerBouton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(153, 153, 153));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //======== lundi ========
            {
                lundi.setBackground(new Color(238, 238, 238));
                lundi.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        panel2MouseClicked(e);
                    }
                });

                //======== lundi2 ========
                {
                    lundi2.setBackground(new Color(238, 238, 238));
                    lundi2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            panel2MouseClicked(e);
                        }
                    });

                    GroupLayout lundi2Layout = new GroupLayout(lundi2);
                    lundi2.setLayout(lundi2Layout);
                    lundi2Layout.setHorizontalGroup(
                        lundi2Layout.createParallelGroup()
                            .addGap(0, 127, Short.MAX_VALUE)
                    );
                    lundi2Layout.setVerticalGroup(
                        lundi2Layout.createParallelGroup()
                            .addGap(0, 521, Short.MAX_VALUE)
                    );
                }

                GroupLayout lundiLayout = new GroupLayout(lundi);
                lundi.setLayout(lundiLayout);
                lundiLayout.setHorizontalGroup(
                    lundiLayout.createParallelGroup()
                        .addGroup(lundiLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lundi2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                lundiLayout.setVerticalGroup(
                    lundiLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, lundiLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(lundi2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                );
            }

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
                        .addGap(0, 127, Short.MAX_VALUE)
                );
                mardiLayout.setVerticalGroup(
                    mardiLayout.createParallelGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                );
            }

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
                        .addGap(0, 127, Short.MAX_VALUE)
                );
                mercrediLayout.setVerticalGroup(
                    mercrediLayout.createParallelGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                );
            }

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
                        .addGap(0, 127, Short.MAX_VALUE)
                );
                jeudiLayout.setVerticalGroup(
                    jeudiLayout.createParallelGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                );
            }

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
                        .addGap(0, 127, Short.MAX_VALUE)
                );
                vendrediLayout.setVerticalGroup(
                    vendrediLayout.createParallelGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                );
            }

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
                        .addGap(0, 127, Short.MAX_VALUE)
                );
                samediLayout.setVerticalGroup(
                    samediLayout.createParallelGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                );
            }

            //======== panel2 ========
            {

                //---- label4 ----
                label4.setText("8h");

                //---- label9 ----
                label9.setText("9h");

                //---- label10 ----
                label10.setText("10h");

                //---- label11 ----
                label11.setText("11h");

                //---- label12 ----
                label12.setText("12h");

                //---- label13 ----
                label13.setText("13h");

                //---- label14 ----
                label14.setText("14h");

                //---- label15 ----
                label15.setText("15h");

                //---- label16 ----
                label16.setText("16h");

                //---- label17 ----
                label17.setText("17h");

                //---- label18 ----
                label18.setText("18h");

                //---- label19 ----
                label19.setText("19h");

                //---- label20 ----
                label20.setText("20h");

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label4)
                                .addComponent(label9)
                                .addComponent(label10)
                                .addComponent(label11)
                                .addComponent(label12)
                                .addComponent(label13))
                            .addContainerGap(7, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                            .addContainerGap(66, Short.MAX_VALUE)
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(label14, GroupLayout.Alignment.TRAILING)
                                .addComponent(label15, GroupLayout.Alignment.TRAILING)
                                .addComponent(label16, GroupLayout.Alignment.TRAILING)
                                .addComponent(label17, GroupLayout.Alignment.TRAILING)
                                .addComponent(label18, GroupLayout.Alignment.TRAILING)
                                .addComponent(label19, GroupLayout.Alignment.TRAILING)
                                .addComponent(label20, GroupLayout.Alignment.TRAILING))
                            .addContainerGap())
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addComponent(label4)
                            .addGap(18, 18, 18)
                            .addComponent(label9)
                            .addGap(18, 18, 18)
                            .addComponent(label10)
                            .addGap(24, 24, 24)
                            .addComponent(label11)
                            .addGap(24, 24, 24)
                            .addComponent(label12)
                            .addGap(30, 30, 30)
                            .addComponent(label13)
                            .addGap(28, 28, 28)
                            .addComponent(label14)
                            .addGap(26, 26, 26)
                            .addComponent(label15)
                            .addGap(26, 26, 26)
                            .addComponent(label16)
                            .addGap(18, 18, 18)
                            .addComponent(label17)
                            .addGap(24, 24, 24)
                            .addComponent(label18)
                            .addGap(26, 26, 26)
                            .addComponent(label19)
                            .addGap(18, 18, 18)
                            .addComponent(label20)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lundi, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mardi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mercredi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jeudi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendredi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(samedi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mardi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lundi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mercredi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jeudi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vendredi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(samedi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(38, Short.MAX_VALUE))
            );
        }

        //---- intituleFormation ----
        intituleFormation.setText("text");

        //---- label22 ----
        label22.setText("Lundi");

        //---- label23 ----
        label23.setText("mardi");

        //---- label24 ----
        label24.setText("mercredi");

        //---- label25 ----
        label25.setText("jeudi");

        //---- label26 ----
        label26.setText("vendredi");

        //---- label27 ----
        label27.setText("samedi");

        //---- validerEmploi ----
        validerEmploi.setText("Valider");
        validerEmploi.addActionListener(e -> validerEmploiActionPerformed(e));

        //---- annulerEmploi ----
        annulerEmploi.setText("Annuler");
        annulerEmploi.addActionListener(e -> annulerEmploiActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(151, 151, 151)
                    .addComponent(validerEmploi, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                    .addComponent(annulerEmploi, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addGap(250, 250, 250))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addComponent(intituleFormation))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(177, 177, 177)
                            .addComponent(label22)
                            .addGap(82, 82, 82)
                            .addComponent(label23)
                            .addGap(95, 95, 95)
                            .addComponent(label24)
                            .addGap(88, 88, 88)
                            .addComponent(label25)
                            .addGap(71, 71, 71)
                            .addComponent(label26)
                            .addGap(73, 73, 73)
                            .addComponent(label27))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(26, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(intituleFormation)
                    .addGap(3, 3, 3)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label22)
                        .addComponent(label23)
                        .addComponent(label24)
                        .addComponent(label25)
                        .addComponent(label26)
                        .addComponent(label27))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(validerEmploi, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                        .addComponent(annulerEmploi, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());

        //======== creationSeance ========
        {
            Container creationSeanceContentPane = creationSeance.getContentPane();

            //---- label5 ----
            label5.setText("nom mati\u00e8re");

            //---- label6 ----
            label6.setText("proffeseur");

            //---- label7 ----
            label7.setText("h debut");

            //---- label8 ----
            label8.setText("h fin");

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

            //---- validercours ----
            validercours.setText("valider");
            validercours.addActionListener(e -> {
			button1ActionPerformed(e);
			validercoursActionPerformed(e);
		});

            //---- label2 ----
            label2.setText("Salle");

            //---- nomSalle ----
            nomSalle.setSelectedIndex(-1);

            //---- nomMatiere ----
            nomMatiere.addItemListener(e -> nomMatiereItemStateChanged(e));

            GroupLayout creationSeanceContentPaneLayout = new GroupLayout(creationSeanceContentPane);
            creationSeanceContentPane.setLayout(creationSeanceContentPaneLayout);
            creationSeanceContentPaneLayout.setHorizontalGroup(
                creationSeanceContentPaneLayout.createParallelGroup()
                    .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                            .addComponent(label5)
                            .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(validercours))
                            .addComponent(label6)
                            .addComponent(label2)
                            .addComponent(label7)
                            .addComponent(label8))
                        .addGap(18, 18, 18)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                            .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                                    .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                            .addComponent(hFinH, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                            .addGap(34, 34, 34)
                                            .addComponent(hFinM, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                            .addComponent(hDebutH, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(hDebutM, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(nomSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 221, Short.MAX_VALUE))
                            .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                                    .addComponent(nomMatiere, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomEnseignant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 265, Short.MAX_VALUE))))
            );
            creationSeanceContentPaneLayout.setVerticalGroup(
                creationSeanceContentPaneLayout.createParallelGroup()
                    .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
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
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                            .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(hDebutM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hDebutH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label7)))
                        .addGap(18, 18, 18)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                            .addComponent(hFinM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(creationSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(hFinH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label8)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(validercours)
                        .addContainerGap())
            );
            creationSeance.pack();
            creationSeance.setLocationRelativeTo(creationSeance.getOwner());
        }

        //======== fenetreDebut ========
        {
            Container fenetreDebutContentPane = fenetreDebut.getContentPane();

            //======== tabbedPane1 ========
            {

                //======== panel3 ========
                {
                    panel3.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
                    0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
                    .BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.
                    red),panel3. getBorder()));panel3. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
                    beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}});

                    //---- label3 ----
                    label3.setText("Gestion emploi du temps");

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

                    //---- boutonCreerEmploi ----
                    boutonCreerEmploi.setText("creer");
                    boutonCreerEmploi.addActionListener(e -> boutonCreerEmploiActionPerformed(e));

                    GroupLayout panel3Layout = new GroupLayout(panel3);
                    panel3.setLayout(panel3Layout);
                    panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(110, 110, 110)
                                        .addComponent(label1)
                                        .addGap(40, 40, 40)
                                        .addComponent(choixFormationBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(193, 193, 193)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(202, 202, 202)
                                        .addComponent(boutonCreerEmploi, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(203, Short.MAX_VALUE))
                    );
                    panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                        .addGap(111, 111, 111))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label1)
                                            .addComponent(choixFormationBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(88, 88, 88)))
                                .addComponent(boutonCreerEmploi, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(94, Short.MAX_VALUE))
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

                    GroupLayout panel4Layout = new GroupLayout(panel4);
                    panel4.setLayout(panel4Layout);
                    panel4Layout.setHorizontalGroup(
                        panel4Layout.createParallelGroup()
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(nomMatiere2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                                .addComponent(associerBouton)
                                .addGap(179, 179, 179))
                    );
                    panel4Layout.setVerticalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(nomMatiere2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(associerBouton))
                                .addContainerGap())
                    );
                }
                tabbedPane1.addTab("Association Matieres/Profs", panel4);
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
                        .addComponent(tabbedPane1)
                        .addGap(38, 38, 38))
            );
            fenetreDebut.pack();
            fenetreDebut.setLocationRelativeTo(fenetreDebut.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Haribou Abdallah
    private JPanel panel1;
    private JPanel lundi;
    private JPanel lundi2;
    private JPanel mardi;
    private JPanel mercredi;
    private JPanel jeudi;
    private JPanel vendredi;
    private JPanel samedi;
    private JPanel panel2;
    private JLabel label4;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;
    private JLabel intituleFormation;
    private JLabel label22;
    private JLabel label23;
    private JLabel label24;
    private JLabel label25;
    private JLabel label26;
    private JLabel label27;
    private JButton validerEmploi;
    private JButton annulerEmploi;
    private JFrame creationSeance;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JComboBox<String> hDebutH;
    private JComboBox<String> hDebutM;
    private JComboBox<String> hFinH;
    private JComboBox<String> hFinM;
    private JButton validercours;
    private JLabel label2;
    private JComboBox nomSalle;
    private JComboBox nomMatiere;
    private JComboBox nomEnseignant;
    private JFrame fenetreDebut;
    private JTabbedPane tabbedPane1;
    private JPanel panel3;
    private JLabel label3;
    private JLabel label1;
    private JComboBox<String> choixFormationBox;
    private JButton boutonCreerEmploi;
    private JPanel panel4;
    private JComboBox nomMatiere2;
    private JScrollPane scrollPane1;
    private JTable tableEnseignants;
    private JButton associerBouton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
