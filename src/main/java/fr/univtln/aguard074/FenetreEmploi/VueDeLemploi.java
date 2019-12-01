/*
 * Created by JFormDesigner on Wed Nov 27 17:57:55 CET 2019
 */

package fr.univtln.aguard074.FenetreEmploi;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.plaf.*;

import fr.univtln.aguard074.FenetreAdmin.Icontroleur;
import fr.univtln.aguard074.FenetreAdmin.Modele;
import fr.univtln.aguard074.FenetreAdmin.VueGestionaire;
import fr.univtln.group_aha.Enseignant;
import fr.univtln.group_aha.Matiere;
import fr.univtln.group_aha.Salle;
import fr.univtln.group_aha.Seance;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

/**
 * @author unknown
 */
public class VueDeLemploi extends JFrame {

    private JPanel jour;
    private ModeleEmploi modele;
    private ControleurEmploi controleur;
    private VueGestionaire.TmodelEnseignant tmodelEnseignant;






    public VueDeLemploi(ModeleEmploi modele, ControleurEmploi controleur) {
        this.modele = modele;
        this.controleur = controleur;
        initComponents();
        setComponents();
        this.fenetreDebut.setVisible(true);
    }


    private void button1ActionPerformed(ActionEvent e) {
        ArrayList debutCour = new ArrayList();

    }

    private void boutonCreerEmploiActionPerformed(ActionEvent e) {
        this.fenetreDebut.setVisible(false);
        this.fenetreDebut.dispose();
        this.setVisible(true);
        this.intituleFormation.setText(choixFormationBox.getSelectedItem().toString());

    }

    private void validercoursActionPerformed(ActionEvent e) {
        System.out.println("seance");
        creationSeance.setVisible(false);
        JPanel panelle = new JPanel();
        panelle.setLayout(null);
        this.jour.add(panelle);
        JLabel matiere = new JLabel(this.nomMatiere.getSelectedItem().toString());
        JLabel proffeseur = new JLabel(this.nomProffeseur.getSelectedItem().toString());
        JLabel salle = new JLabel(this.nomSalle.getSelectedItem().toString());
        panelle.add(matiere);
        panelle.add(proffeseur);
        panelle.add(salle);
        int debut = (hDebutH.getSelectedIndex())*40 + hDebutM.getSelectedIndex()*10;
        int fin = (hFinH.getSelectedIndex())*40 + hFinM.getSelectedIndex()*10;
        System.out.println(fin);
        panelle.setBounds(8,debut,111,fin - debut);
        panelle.setBorder(BorderFactory.createLineBorder(Color.black));
        panelle.setBackground(Color.gray);
        matiere.setBounds(15,1,66,10);
        proffeseur.setBounds(15,15,66,10);
        salle.setBounds(15,30,66,15);
        Calendar test1 = new GregorianCalendar(1999,7,26);
        Calendar test2 = new GregorianCalendar(1999,7,26);
        Calendar test3 = new GregorianCalendar(1999,7,26);
        controleur.creerSeance( test1, test2, test3);
        //lundi.setBackground(Color.red);


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
        this.jour = lundi;
    }
    private void mardiMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        jour = mardi;
    }

    private void mercrediMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        jour = mercredi;
    }

    private void jeudiMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        jour = jeudi;
    }

    private void vendrediMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        jour = vendredi;
    }

    private void samediMouseClicked(MouseEvent e) {
        creationSeance.setVisible(true);
        jour = samedi;
    }

    private void validerEmploiActionPerformed(ActionEvent e) {
        controleur.creerEmploi();
    }

    private void setComponents(){
        //SALLES
        DefaultComboBoxModel salleModel = new DefaultComboBoxModel();
        nomSalle.setModel(salleModel);
        for(Salle salle: this.modele.getSalles())
            salleModel.addElement(salle.getNom());
        //MATIERES
        DefaultComboBoxModel matiereModel = new DefaultComboBoxModel();
        nomMatiere.setModel(matiereModel);
        nomMatiere2.setModel(matiereModel);// celui du debut ou on associe un prof a une matière
        System.out.println(this.modele.getMatieres());
        for(Matiere matiere: this.modele.getMatieres())
            matiereModel.addElement(matiere);
        //ENSEIGANANTS(association)
        tmodelEnseignant = new VueGestionaire.TmodelEnseignant(modele.getEnseignantsList());
        tableEnseignants.setModel(tmodelEnseignant);
        //ENSEIGNANTS(Creation Seance)
        DefaultComboBoxModel enseignantModel = new DefaultComboBoxModel();
        nomProffeseur.setModel(enseignantModel);

        List<Enseignant> enseignantsDispo = this.controleur.getAssocTeachers((Matiere) nomMatiere.getSelectedItem());
        for(Enseignant enseignant: enseignantsDispo)
            enseignantModel.addElement(enseignant);
    }

    private void associerBoutonActionPerformed(ActionEvent e) {
        Matiere matiere = (Matiere) nomMatiere.getSelectedItem();
        int i = tableEnseignants.getSelectedRow();
        Enseignant enseignant = tmodelEnseignant.getRowValue(i);
        this.controleur.associerMatiereProf(matiere,enseignant);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - adrien guard
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
        nomProffeseur = new JComboBox();
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
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
            swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border
            . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog"
            , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel1. getBorder
            () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
            . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException
            ( ) ;} } );

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
                    .addContainerGap(25, Short.MAX_VALUE))
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
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
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
                                .addGap(45, 213, Short.MAX_VALUE))
                            .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                                    .addComponent(nomMatiere, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomProffeseur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 260, Short.MAX_VALUE))))
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
                            .addComponent(nomProffeseur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
                    panel3.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .
                    EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing
                    . border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,
                    java . awt. Color .red ) ,panel3. getBorder () ) ); panel3. addPropertyChangeListener( new java. beans .PropertyChangeListener ( )
                    { @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )
                    throw new RuntimeException( ) ;} } );

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
                                .addContainerGap(207, Short.MAX_VALUE))
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
                                .addContainerGap(89, Short.MAX_VALUE))
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
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(nomMatiere2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                                .addComponent(associerBouton)
                                .addGap(179, 179, 179))
                    );
                    panel4Layout.setVerticalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
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
    // Generated using JFormDesigner Evaluation license - adrien guard
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
    private JComboBox nomProffeseur;
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
