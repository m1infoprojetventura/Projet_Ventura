/*
 * Created by JFormDesigner on Wed Nov 27 17:57:55 CET 2019
 */

package fr.univtln.aguard074.FenetreEmploi;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
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
    private int semaineAnnee;
    private int etatConsultation;
    private JPanel[] joursSemainePanel;
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
    private Boolean sessionActive = false;

    public VueDeLemploi(ModeleEmploi modele, ControleurEmploi controleur) {
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();

        //nomMatiere3 = nomMatiere;
        this.semaineAnnee = 1;
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

        //  Mettre dans une fonction
        affichageSemaine.setModel(new TmodelSemaine());
        affichageSemaine.setCellSelectionEnabled(true);
        affichageSemaine.setColumnSelectionAllowed(true);
        affichageSemaine.setTableHeader(null);
        scrollPane4.getViewport().add(affichageSemaine);


        joursSemainePanel = new JPanel[]{lundi, mardi, mercredi, jeudi, vendredi, samedi};
        lundi.setName("lundi");
        mardi.setName("mardi");
        mercredi.setName("mercredi");
        jeudi.setName("jeudi");
        vendredi.setName("vendredi");
        samedi.setName("samedi");

        this.fenetreAuthentification.setVisible(true);
    }

    private void gererVueEnseignant(){
        globalpane =parentPanel;
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
        model.addListSelectionListener(e -> {
            if (model.isSelectionEmpty()){
                buttonDemandeReserv.setEnabled(false);
            }else {
                buttonDemandeReserv.setEnabled(true);
            }
        });

    }

    private void gererTabReservation(){
        tmodelReservation = new TmodelReservation(modele.getReservations());
        tableListeReservation.setModel(tmodelReservation);
        scrollPane3.getViewport().add(tableListeReservation);
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

    private void button1ActionPerformed(ActionEvent e) {
        //System.out.println("le jour actuel c" +this.jourSemaine);
        GregorianCalendar debutH = new GregorianCalendar();
        GregorianCalendar finH = new GregorianCalendar();

        debutH.set(GregorianCalendar.WEEK_OF_YEAR, semaineAnnee);
        debutH.set(GregorianCalendar.DAY_OF_WEEK, this.jourSemaine);
        debutH.set(GregorianCalendar.HOUR_OF_DAY, hDebutH2.getSelectedIndex() + 8);
        debutH.set(GregorianCalendar.MINUTE, hDebutM2.getSelectedIndex() * 15);

        finH.set(GregorianCalendar.WEEK_OF_YEAR, semaineAnnee);
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
    private void affichageSeance(Seance seance) {
        JPanel panelle = new JPanel();
        panelle.setName(Integer.toString(nomPanel));

        if(consultationModifiable) {
            panelle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //panel2MouseClicked(e);
                    modifierSeance.setVisible(true);
                    nomMatiere3.setSelectedItem(seance.getMatiere());
                    System.out.println(seance.getMatiere());
                    nomEnseignant2.setSelectedItem(seance.getEnseignant());
                    nomSalle2.setSelectedItem(seance.getSalle());
                    sallesDispoJour();
                    hDebutH2.setSelectedItem(String.valueOf(seance.getHdebut().get(Calendar.HOUR_OF_DAY)));
                    hDebutM2.setSelectedItem(String.valueOf(seance.getHdebut().get(Calendar.MINUTE)));
                    hFinH2.setSelectedItem(String.valueOf(seance.getHfin().get(Calendar.HOUR_OF_DAY)));
                    hFinH2.setSelectedItem(String.valueOf(seance.getHfin().get(Calendar.MINUTE)));
                    idSalle = seance.getId();
                    System.out.println(idSalle);
                    jourSemaine = seance.getHdebut().get(Calendar.DAY_OF_WEEK);
                    
                    //System.out.println("llllllllll" + jourSemaine);


                }
            });
        }
        creationSeance.setVisible(false);

        Calendar debut =seance.getHdebut();
        Calendar fin =seance.getHfin();
        int j = debut.get(Calendar.DAY_OF_WEEK) -2;

        JPanel jourPanel = joursSemainePanel[j];
        jourPanel.add(panelle);
        //System.out.println(jourPanel.getName());

        JLabel matiere = new JLabel(seance.getMatiere().getNom());
        JLabel proffeseur = new JLabel(seance.getEnseignant().getNom());
        JLabel salle = new JLabel(seance.getSalle().getNom());

        int debH = debut.get(Calendar.HOUR_OF_DAY) - 8;
        int finH = fin.get(Calendar.HOUR_OF_DAY) - 8;
        int debM = debut.get(Calendar.MINUTE)/15;
        int finM = fin.get(Calendar.MINUTE)/15;


        creationSeance.setVisible(false);
        panelle.setLayout(null);

        //System.out.println(debH);
        //System.out.println(debM);

        //System.out.println(finH);
        //System.out.println(finM);

        int d =  (debH)*40 + (debM)*10;
        int f =  (finH)*40 + (finM)*10;

        //System.out.println("Debut: " + d);
        //System.out.println("Fin: " + f);

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
        //System.out.println("Mise a jour");
        for(JPanel panel: joursSemainePanel)
            panel.removeAll();
        repaint();
        //System.out.println(emploiDuTemps[10].get(0).getSalle());

        //System.out.println(emploiDuTemps[this.semaneAnnee]);
        // Le problème on a pas gérer la superposition donc à voir par contre si on la gère pas
        // Il faudra changer des choses.(Afficher qu'une semaine à la fois: je ne sais pas comment la choisir parmi toutes
        // celle sélectionnées)

        for (Seance seance : emploiDuTemps[semaineAnnee]) {
            //System.out.println(seance);
            affichageSeance(seance);
        }

        semaineActuelle.setText("Semaine " + semaineAnnee);

    }

    private void validercoursActionPerformed(ActionEvent e) {
        //System.out.println("seance");
        int id = nomPanel;
        nomPanel+=1;
        Matiere matiere = (Matiere) this.nomMatiere.getSelectedItem();
        Enseignant enseignant = (Enseignant) this.nomEnseignant.getSelectedItem();
        Salle salle = (Salle) this.nomSalle.getSelectedItem();

        GregorianCalendar debutH = new GregorianCalendar();
        GregorianCalendar finH = new GregorianCalendar();

        debutH.set(GregorianCalendar.WEEK_OF_YEAR, this.semaineAnnee);
        debutH.set(GregorianCalendar.DAY_OF_WEEK,  this.jourSemaine);
        debutH.set(GregorianCalendar.HOUR_OF_DAY, hDebutH.getSelectedIndex() + 8);
        debutH.set(GregorianCalendar.MINUTE,  hDebutM.getSelectedIndex()*15);

        finH.set(GregorianCalendar.WEEK_OF_YEAR, this.semaineAnnee);
        finH.set(GregorianCalendar.DAY_OF_WEEK,  this.jourSemaine);
        finH.set(GregorianCalendar.HOUR_OF_DAY, hFinH.getSelectedIndex() + 8);
        finH.set(GregorianCalendar.MINUTE, hFinM.getSelectedIndex()*15);
        Formation formation = (Formation) this.choixFormationBox.getSelectedItem();

        if (etatConsultation==0)
            controleur.creerSeance(id,salle, enseignant, matiere, debutH, finH,formation);
        else
            controleur.creerSeanceBDD(salle, enseignant, matiere, debutH,finH,formation);

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
       semaineAnnee -= 1;
       if(semaineAnnee < 1)
           semaineAnnee = 52;

       controleur.changerSemaine();
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
      if (etatConsultation ==0)
             this.controleur.supprimerSeance(idSalle, semaineAnnee);
         else
             this.controleur.supprimerSeanceBDD(idSalle, semaineAnnee);

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

                    break;
                case"Enseignant":
                    consultationModifiable = false;
                    fenetreEnseignant.setVisible(true);
                    ((CardLayout)parentPanel.getLayout()).show(parentPanel,"panelReserverSalle");
                    fenetreAuthentification.dispose();
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
        // TODO add your code here
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
        if(valider){
            int id_enseignant = ((Enseignant)this.modele.getEnseignantByLogin(personneAuthentifiee)).getId();
            this.controleur.creerReservation(id_enseignant,id_Salle,date_reservation);
            JOptionPane.showMessageDialog(panelReserverSalle,"Votre demande à été envoyé au responsable de formation","Confirm", JOptionPane.INFORMATION_MESSAGE);
        }


    }


    private void affichageSemaineMouseClicked(MouseEvent e) {
        semaineAnnee = affichageSemaine.getSelectedColumn() + 1;
        controleur.changerSemaine();
    }

    private void affichageSemaineMouseReleased(MouseEvent e) {
        // TODO add your code here
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

        GregorianCalendar debutH = new GregorianCalendar();
        GregorianCalendar finH = new GregorianCalendar();

        debutH.set(GregorianCalendar.WEEK_OF_YEAR, this.semaineAnnee);
        debutH.set(GregorianCalendar.DAY_OF_WEEK,  this.jourSemaine);
        debutH.set(GregorianCalendar.HOUR_OF_DAY, hDebutH2.getSelectedIndex() + 8);
        debutH.set(GregorianCalendar.MINUTE,  hDebutM2.getSelectedIndex()*15);

        finH.set(GregorianCalendar.WEEK_OF_YEAR, this.semaineAnnee);
        finH.set(GregorianCalendar.DAY_OF_WEEK,  this.jourSemaine);
        finH.set(GregorianCalendar.HOUR_OF_DAY, hFinH2.getSelectedIndex() + 8);
        finH.set(GregorianCalendar.MINUTE, hFinM2.getSelectedIndex()*15);
        List<Salle> sallesDispo = this.controleur.getSallesDispo(debutH,finH);
        // Temporaire: Je sais pas comment changer la liste en paramètre de combobox sans créer un nouveau modele
        DefaultComboBoxModel salleComboModel = new DefaultComboBoxModel(this.controleur.getSallesDispo(debutH,finH).toArray());
        nomSalle.setModel(salleComboModel);
        nomSalle2.setModel(salleComboModel);
    }



    public static class TmodelReservation extends AbstractTableModel implements Observer {
        private final String[] entetes = {"ID","id_enseignant", "id_salle","date" ,"etat"};
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
        private static int nbsemaines = 52;
        private static int[] semaines;

        public TmodelSemaine() {
            semaines = new int[nbsemaines];

            for(int i = 0; i < nbsemaines; i++) {
                semaines[i] = i + 1;
            }
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
            return semaines.length;
        }

        @Override
        public Object getValueAt(int i, int i1) {
            return semaines[i1];

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - adrien guard
        panel1 = new JPanel();
        lundi = new JPanel();
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
        scrollPaneSalle = new JScrollPane();
        tableSalles = new JTable();
        buttonDemandeReserv = new JButton();
        panel6 = new JPanel();
        label35 = new JLabel();
        inputdateReSalle = new JDateChooser();
        button4 = new JButton();
        panelListeReservation = new JPanel();
        panel7 = new JPanel();
        scrollPane3 = new JScrollPane();
        tableListeReservation = new JTable();

        //======== this ========
        setResizable(false);
        setIconImage(new ImageIcon("/home/guard/Documents/M1/Projet/Projet_Ventura/fond_slide1.jpg").getImage());
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(153, 153, 153));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );

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
                        .addGap(0, 127, Short.MAX_VALUE)
                );
                lundiLayout.setVerticalGroup(
                    lundiLayout.createParallelGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
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
                            .addContainerGap(8, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(lundi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                        .addContainerGap(53, Short.MAX_VALUE))
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
        label23.setText("Mardi");

        //---- label24 ----
        label24.setText("Mercredi");

        //---- label25 ----
        label25.setText("Jeudi");

        //---- label26 ----
        label26.setText("Vendredi");

        //---- label27 ----
        label27.setText("Samedi");

        //---- validerEmploi ----
        validerEmploi.setText("Valider");
        validerEmploi.addActionListener(e -> validerEmploiActionPerformed(e));

        //---- annulerEmploi ----
        annulerEmploi.setText("Annuler");
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
            scrollPane4.setViewportView(affichageSemaine);
        }

        //---- vSpacer1 ----
        vSpacer1.setPreferredSize(new Dimension(44, 10));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(305, Short.MAX_VALUE)
                    .addComponent(validerEmploi, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addGap(76, 76, 76)
                    .addComponent(vSpacer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(117, 117, 117)
                    .addComponent(annulerEmploi, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addGap(243, 243, 243))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(semaineActuelle)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(156, 156, 156)
                                    .addComponent(label22)
                                    .addGap(99, 99, 99)
                                    .addComponent(label23)
                                    .addGap(83, 83, 83)
                                    .addComponent(label24)
                                    .addGap(83, 83, 83)
                                    .addComponent(label25)
                                    .addGap(79, 79, 79)
                                    .addComponent(label26)
                                    .addGap(87, 87, 87)
                                    .addComponent(label27))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addComponent(intituleFormation))))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(37, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(intituleFormation)
                    .addGap(41, 41, 41)
                    .addComponent(semaineActuelle)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label22)
                        .addComponent(label23)
                        .addComponent(label24)
                        .addComponent(label25)
                        .addComponent(label26)
                        .addComponent(label27))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(vSpacer1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(validerEmploi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(annulerEmploi, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());

        //======== creationSeance ========
        {
            creationSeance.setTitle("creer");
            creationSeance.setResizable(false);
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
                        .addGap(15, 15, 15)
                        .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                            .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                                .addGroup(creationSeanceContentPaneLayout.createParallelGroup()
                                    .addComponent(nomMatiere, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomEnseignant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 278, Short.MAX_VALUE))
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
                                .addGap(85, 231, Short.MAX_VALUE))))
                    .addGroup(creationSeanceContentPaneLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(validercours2)
                        .addGap(0, 273, Short.MAX_VALUE))
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
                        .addComponent(validercours2))
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
                    panel3.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
                    border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER
                    , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font
                    .BOLD ,12 ), java. awt. Color. red) ,panel3. getBorder( )) ); panel3. addPropertyChangeListener (
                    new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order"
                    .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

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
                    logoutButton.setIcon(new ImageIcon("/home/guard/Documents/M1/Projet/Projet_Ventura/logout.png"));
                    logoutButton.addActionListener(e -> logoutButtonActionPerformed(e));

                    GroupLayout panel3Layout = new GroupLayout(panel3);
                    panel3.setLayout(panel3Layout);
                    panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(boutonCreerEmploi, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(bouttonMofierEmploi, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(90, Short.MAX_VALUE))
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addContainerGap(139, Short.MAX_VALUE)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addComponent(label1)
                                        .addGap(77, 77, 77)
                                        .addComponent(choixFormationBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(161, 161, 161))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44))))
                    );
                    panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(logoutButton)
                                .addGap(72, 72, 72)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(choixFormationBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label1))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(boutonCreerEmploi, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bouttonMofierEmploi, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                                .addGap(99, 99, 99))
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
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
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
                                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
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
                        .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                        .addGap(38, 38, 38))
            );
            fenetreDebut.pack();
            fenetreDebut.setLocationRelativeTo(fenetreDebut.getOwner());
        }

        //======== modifierSeance ========
        {
            modifierSeance.setTitle("modifiercde Creer");
            modifierSeance.setResizable(false);
            Container modifierSeanceContentPane = modifierSeance.getContentPane();

            //---- label21 ----
            label21.setText("nom mati\u00e8re");

            //---- label28 ----
            label28.setText("proffeseur");

            //---- label29 ----
            label29.setText("h debut");

            //---- label30 ----
            label30.setText("h fin");

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
                            .addComponent(label31)
                            .addComponent(label29)
                            .addComponent(label30))
                        .addGap(15, 15, 15)
                        .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                            .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                                    .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                            .addComponent(hFinH2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                            .addGap(34, 34, 34)
                                            .addComponent(hFinM2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                            .addComponent(hDebutH2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(hDebutM2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(nomSalle2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 231, Short.MAX_VALUE))
                            .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                                    .addComponent(nomMatiere3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomEnseignant2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                        .addComponent(button3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button1)
                                        .addGap(18, 18, 18)
                                        .addComponent(suppSeanceBouton)))
                                .addGap(0, 62, Short.MAX_VALUE))))
            );
            modifierSeanceContentPaneLayout.setVerticalGroup(
                modifierSeanceContentPaneLayout.createParallelGroup()
                    .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                        .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                            .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label21)
                                    .addComponent(nomMatiere3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label28)
                                    .addComponent(nomEnseignant2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label31)
                                    .addComponent(nomSalle2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                                    .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(hDebutM2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hDebutH2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(modifierSeanceContentPaneLayout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(label29)))
                                .addGap(18, 18, 18)
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                                    .addComponent(hFinM2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(modifierSeanceContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(hFinH2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label30)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(modifierSeanceContentPaneLayout.createParallelGroup()
                                    .addComponent(button1, GroupLayout.Alignment.TRAILING)
                                    .addComponent(button3, GroupLayout.Alignment.TRAILING)))
                            .addGroup(GroupLayout.Alignment.TRAILING, modifierSeanceContentPaneLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(suppSeanceBouton)))
                        .addContainerGap())
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
                panel5.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
                javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax
                . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
                .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,panel5. getBorder( )) ); panel5. addPropertyChangeListener (new java. beans.
                PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .
                equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

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
                        .addContainerGap(21, Short.MAX_VALUE))
            );
            fenetreAuthentificationContentPaneLayout.setVerticalGroup(
                fenetreAuthentificationContentPaneLayout.createParallelGroup()
                    .addGroup(fenetreAuthentificationContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
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
                parentPanel.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
                ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
                .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
                . Color .red ) ,parentPanel. getBorder () ) ); parentPanel. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
                propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
                ;} } );
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
                            .addGap(0, 462, Short.MAX_VALUE)
                    );
                }
                parentPanel.add(panelEmploi, "card1");

                //======== panelReserverSalle ========
                {

                    //======== panelSalle ========
                    {
                        panelSalle.setBorder(new TitledBorder("R\u00e9server une salle"));

                        //======== scrollPaneSalle ========
                        {
                            scrollPaneSalle.setViewportView(tableSalles);
                        }

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

                            //---- button4 ----
                            button4.setText("Valider");

                            GroupLayout panel6Layout = new GroupLayout(panel6);
                            panel6.setLayout(panel6Layout);
                            panel6Layout.setHorizontalGroup(
                                panel6Layout.createParallelGroup()
                                    .addGroup(panel6Layout.createSequentialGroup()
                                        .addGroup(panel6Layout.createParallelGroup()
                                            .addGroup(panel6Layout.createSequentialGroup()
                                                .addGap(77, 77, 77)
                                                .addComponent(button4, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel6Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(label35)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(inputdateReSalle, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(17, Short.MAX_VALUE))
                            );
                            panel6Layout.setVerticalGroup(
                                panel6Layout.createParallelGroup()
                                    .addGroup(panel6Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label35)
                                            .addComponent(inputdateReSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(80, 80, 80)
                                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(111, Short.MAX_VALUE))
                            );
                        }

                        GroupLayout panelSalleLayout = new GroupLayout(panelSalle);
                        panelSalle.setLayout(panelSalleLayout);
                        panelSalleLayout.setHorizontalGroup(
                            panelSalleLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, panelSalleLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(scrollPaneSalle, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20))
                                .addGroup(panelSalleLayout.createSequentialGroup()
                                    .addGap(278, 278, 278)
                                    .addComponent(buttonDemandeReserv)
                                    .addGap(0, 0, Short.MAX_VALUE))
                        );
                        panelSalleLayout.setVerticalGroup(
                            panelSalleLayout.createParallelGroup()
                                .addGroup(panelSalleLayout.createSequentialGroup()
                                    .addGroup(panelSalleLayout.createParallelGroup()
                                        .addComponent(panel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelSalleLayout.createSequentialGroup()
                                            .addGap(0, 19, Short.MAX_VALUE)
                                            .addComponent(scrollPaneSalle, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buttonDemandeReserv)
                                    .addContainerGap())
                        );
                    }

                    GroupLayout panelReserverSalleLayout = new GroupLayout(panelReserverSalle);
                    panelReserverSalle.setLayout(panelReserverSalleLayout);
                    panelReserverSalleLayout.setHorizontalGroup(
                        panelReserverSalleLayout.createParallelGroup()
                            .addGroup(panelReserverSalleLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    panelReserverSalleLayout.setVerticalGroup(
                        panelReserverSalleLayout.createParallelGroup()
                            .addGroup(panelReserverSalleLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(panelSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(115, Short.MAX_VALUE))
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
                                .addContainerGap(91, Short.MAX_VALUE))
                    );
                }
                parentPanel.add(panelListeReservation, "card3");
            }

            GroupLayout fenetreEnseignantContentPaneLayout = new GroupLayout(fenetreEnseignantContentPane);
            fenetreEnseignantContentPane.setLayout(fenetreEnseignantContentPaneLayout);
            fenetreEnseignantContentPaneLayout.setHorizontalGroup(
                fenetreEnseignantContentPaneLayout.createParallelGroup()
                    .addComponent(parentPanel, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            );
            fenetreEnseignantContentPaneLayout.setVerticalGroup(
                fenetreEnseignantContentPaneLayout.createParallelGroup()
                    .addComponent(parentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            fenetreEnseignant.pack();
            fenetreEnseignant.setLocationRelativeTo(fenetreEnseignant.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - adrien guard
    private JPanel panel1;
    private JPanel lundi;
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
    private JScrollPane scrollPaneSalle;
    private JTable tableSalles;
    private JButton buttonDemandeReserv;
    private JPanel panel6;
    private JLabel label35;
    private JDateChooser inputdateReSalle;
    private JButton button4;
    private JPanel panelListeReservation;
    private JPanel panel7;
    private JScrollPane scrollPane3;
    private JTable tableListeReservation;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
