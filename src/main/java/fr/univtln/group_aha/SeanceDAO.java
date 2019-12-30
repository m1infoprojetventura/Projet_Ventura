package fr.univtln.group_aha;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Level;

public class SeanceDAO extends DAO<Seance> {
    public SeanceDAO(Connection connect) {
        super(connect);
    }

    // J'ai pensé pour un jour, un jour lointain (le plus possible) où à le create renverrai l'ID.
    @Override
    public void create(Seance obj) {
        try {

            String query = "INSERT INTO Seance (id_salle, id_enseignant, id_matiere, date, debut_seance, fin_seance, id_formation) " +
                            "VALUES(?, ?, ?, ?, ?, ?,?)";
            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int id_salle = obj.getSalle().getId();
            int id_enseignant = obj.getEnseignant().getId();
            int id_matiere = obj.getMatiere().getId();
            int id_formation = obj.getFormation().getId();
            Time debut_seance = new Time(obj.getHdebut().getTimeInMillis());
            Time fin_seance = new Time(obj.getHfin().getTimeInMillis());
            java.sql.Date date = new java.sql.Date(obj.getHdebut().getTime().getTime());

            state.setInt(1,  id_salle);
            state.setInt(2,  id_enseignant);
            state.setInt(3,  id_matiere);
            state.setDate(4,  date);
            state.setTime(5,  debut_seance);
            state.setTime(6,  fin_seance);
            state.setInt(7,  id_formation);

            state.executeUpdate();

            // Obtenir la clé autogénéré par INSERT
            ResultSet key = state.getGeneratedKeys();

            if(key.first())
                obj.setId(key.getInt(1));
        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }

    }
    public void createFakeSeance(Seance obj) {
        try {

            String query = "INSERT INTO Seance(id_salle,date) VALUES(?,?)";
            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int id_salle = obj.getSalle().getId();
            java.sql.Date date = new java.sql.Date(obj.getHdebut().getTime().getTime());
            state.setInt(1,  id_salle);
            state.setDate(2,  date);
            state.executeUpdate();

            // Obtenir la clé autogénéré par INSERT
            ResultSet key = state.getGeneratedKeys();

            if(key.first())
                obj.setId(key.getInt(1));
        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }

    }


    @Override
    public void delete(Seance obj) {
        try {
            String query = "DELETE FROM Seance WHERE id = ?";
            System.out.println(obj.getId());
            System.out.println(obj.getEnseignant());

            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());

            state.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Seance obj) {
        try {
            String query = "UPDATE Seance SET id_salle=?, id_enseignant=?, id_matiere=?, date=?, debut_seance=?, fin_seance=?, id_formation=? " +
                    "WHERE id=?";
            PreparedStatement state = connect.prepareStatement(query);
            int id_salle = obj.getSalle().getId();
            int id_enseignant = obj.getEnseignant().getId();
            int id_matiere = obj.getMatiere().getId();
            int id_formation = obj.getFormation().getId();
            Time debut_seance = new Time(obj.getHdebut().getTimeInMillis());
            Time fin_seance = new Time(obj.getHfin().getTimeInMillis());
            java.sql.Date date = new java.sql.Date(obj.getHdebut().getTime().getTime());

            state.setInt(1,  id_salle);
            state.setInt(2,  id_enseignant);
            state.setInt(3,  id_matiere);
            state.setDate(4,  date);
            state.setTime(5,  debut_seance);
            state.setTime(6,  fin_seance);
            state.setInt(7,  id_formation);
            state.setInt(8,  obj.getId());

            state.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Seance find(int id) {
        Seance seance = null;


        try {
            SalleDAO salleDAO = new SalleDAO(this.connect);
            EnseignantDAO enseignantDAO= new EnseignantDAO(this.connect);
            MatiereDAO matiereDAO = new MatiereDAO(this.connect);
            FormationDAO formationDAO = new FormationDAO(this.connect);
            Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM  Seance WHERE id = %d;";

            ResultSet resultat = st.executeQuery(String.format(query, id));


            if (resultat.first()) {

                String aux;

                Formation formation = formationDAO.find(resultat.getInt("id_formation"));
                Time debut_seance = resultat.getTime("debut_seance");
                Time fin_seance = resultat.getTime("fin_seance");
                //System.out.println(debut_seance.toString());
                Date date = resultat.getDate("date");
                Calendar cal = new GregorianCalendar();

                cal.setTime(date);
                int day = cal.get(Calendar.DAY_OF_YEAR);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                GregorianCalendar debutH = new GregorianCalendar();
                GregorianCalendar finH = new GregorianCalendar();

                // debut seance
                aux = debut_seance.toString().substring(0,2);

                debutH.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(aux));
                aux = debut_seance.toString().substring(3,5);
                debutH.set(GregorianCalendar.MINUTE,  Integer.parseInt(aux));
                //semaine , jour
                debutH.set(GregorianCalendar.DAY_OF_YEAR,  day);
                debutH.set(GregorianCalendar.MONTH, month);
                debutH.set(GregorianCalendar.YEAR, year);

                // fin seance
                aux = fin_seance.toString().substring(0,2);
                finH.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(aux));
                aux = fin_seance.toString().substring(0,2);
                finH.set(GregorianCalendar.MINUTE, Integer.parseInt(aux));
                //semaine jour
                finH.set(GregorianCalendar.DAY_OF_YEAR,  day);
                finH.set(GregorianCalendar.MONTH, month);
                finH.set(GregorianCalendar.YEAR, year);

                seance = new Seance(resultat.getInt("id"),salleDAO.find(resultat.getInt("id_salle")), enseignantDAO.find(resultat.getInt("id_enseignant")),
                        matiereDAO.find(resultat.getInt("id_matiere")),debutH,finH, formation);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return seance;
        }
    }

    @Override
    public ArrayList<Seance> getData() {
        return null;
    }

    public ArrayList<Seance>getSeanceFormation(int id){
        ArrayList<Seance> resultat = new ArrayList();

        try {
            String query = "SELECT * FROM Seance where id_formation = ?";
            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, id);
            ResultSet result = state.executeQuery();
            FormationDAO formationDAO = new FormationDAO(this.connect);
            SalleDAO salleDAO = new SalleDAO(this.connect);
            EnseignantDAO enseignantDAO = new EnseignantDAO(this.connect);
            MatiereDAO matiereDAO = new MatiereDAO(this.connect);
            String aux;
            while (result.next()) {
                Formation formation = formationDAO.find(result.getInt("id_formation"));
                Time debut_seance = result.getTime("debut_seance");
                Time fin_seance = result.getTime("fin_seance");
                //System.out.println(debut_seance.toString());
                Date date = result.getDate("date");
                Calendar cal = new GregorianCalendar();

                cal.setTime(date);
                int day = cal.get(Calendar.DAY_OF_YEAR);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                GregorianCalendar debutH = new GregorianCalendar();
                GregorianCalendar finH = new GregorianCalendar();

                // debut seance
                aux = debut_seance.toString().substring(0,2);

                debutH.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(aux));
                aux = debut_seance.toString().substring(3,5);
                debutH.set(GregorianCalendar.MINUTE,  Integer.parseInt(aux));
                //semaine , jour
                debutH.set(GregorianCalendar.DAY_OF_YEAR, day);
                debutH.set(GregorianCalendar.MONTH,  month);
                debutH.set(GregorianCalendar.YEAR, year);
                // fin seance
                aux = fin_seance.toString().substring(0,2);
                finH.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(aux));
                aux = fin_seance.toString().substring(0,2);
                finH.set(GregorianCalendar.MINUTE, Integer.parseInt(aux));
                //semaine jour
                finH.set(GregorianCalendar.DAY_OF_YEAR, day);
                finH.set(GregorianCalendar.MONTH,  month);
                finH.set(GregorianCalendar.YEAR, year);


                //System.out.println(result.getInt("id"));
                Seance seance = new Seance(result.getInt("id"),salleDAO.find(result.getInt("id_salle")), enseignantDAO.find(result.getInt("id_enseignant")),
                        matiereDAO.find(result.getInt("id_matiere")),debutH,finH, formation);


                resultat.add(seance);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }
    }

    public ArrayList<Seance>getSeanceEnseignant(int id){
        ArrayList<Seance> resultat = new ArrayList();

        try {
            String query = "SELECT * FROM Seance where id_enseignant = ?";
            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, id);
            ResultSet result = state.executeQuery();
            FormationDAO formationDAO = new FormationDAO(this.connect);
            SalleDAO salleDAO = new SalleDAO(this.connect);
            EnseignantDAO enseignantDAO = new EnseignantDAO(this.connect);
            MatiereDAO matiereDAO = new MatiereDAO(this.connect);
            String aux;
            while (result.next()) {

                Formation formation = formationDAO.find(result.getInt("id_formation"));
                Time debut_seance = result.getTime("debut_seance");
                Time fin_seance = result.getTime("fin_seance");
                //System.out.println(debut_seance.toString());
                Date date = result.getDate("date");
                Calendar cal = new GregorianCalendar();

                cal.setTime(date);
                int day = cal.get(Calendar.DAY_OF_YEAR);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                GregorianCalendar debutH = new GregorianCalendar();
                GregorianCalendar finH = new GregorianCalendar();

                // debut seance
                aux = debut_seance.toString().substring(0,2);

                debutH.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(aux));
                aux = debut_seance.toString().substring(3,5);
                debutH.set(GregorianCalendar.MINUTE,  Integer.parseInt(aux));
                //semaine , jour
                debutH.set(GregorianCalendar.DAY_OF_YEAR,  day);
                debutH.set(GregorianCalendar.MONTH, month);
                debutH.set(GregorianCalendar.YEAR, year);

                // fin seance
                aux = fin_seance.toString().substring(0,2);
                finH.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(aux));
                aux = fin_seance.toString().substring(0,2);
                finH.set(GregorianCalendar.MINUTE, Integer.parseInt(aux));
                //semaine jour
                finH.set(GregorianCalendar.DAY_OF_YEAR,  day);
                finH.set(GregorianCalendar.MONTH, month);
                finH.set(GregorianCalendar.YEAR, year);

                //System.out.println(result.getInt("id"));
                Seance seance = new Seance(result.getInt("id"),salleDAO.find(result.getInt("id_salle")), enseignantDAO.find(result.getInt("id_enseignant")),
                        matiereDAO.find(result.getInt("id_matiere")),debutH,finH, formation);

                resultat.add(seance);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }
    }

    public ArrayList<Salle> getSalleDispo(GregorianCalendar debutH, GregorianCalendar finH){
        // FinH est inutile on a juste besoin de la date contenu dans debutH ou finH, j'ai choisi debutH
        ArrayList<Salle> resultat = new ArrayList();
        //Time debut_seance = new Time(debutH.getTimeInMillis());
        //Time fin_seance = new Time(finH.getTimeInMillis());
        java.sql.Date date = new java.sql.Date(debutH.getTime().getTime());
        SalleDAO salleDAO = new SalleDAO();


        try {
            String query = "SELECT * FROM Seance where date = ?";

            PreparedStatement state = connect.prepareStatement(query);
            state.setDate(1, date);
            /*state.setTime(2, debut_seance);
            state.setTime(3, fin_seance);
            state.setTime(4, fin_seance);*/

            ResultSet result = state.executeQuery();



            while (result.next()) {
                Salle salle = salleDAO.find(result.getInt("id_salle"));
               /* System.out.println(result.getInt("id_salle"));

                System.out.println("hahaha" + salleDAO.find(result.getInt("id_salle")));
                System.out.println(date);
                System.out.println("daate trouve" +result.getDate("date"));*/
                resultat.add(salle);

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {

            ArrayList<Salle> toutessalles = salleDAO.getData();

            Set set = new HashSet() ;
            set.addAll(resultat) ;
            ArrayList<Salle> distinctList = new ArrayList(set) ;
            for(Salle salle: distinctList) {
                int idx = toutessalles.indexOf(salle);
                toutessalles.remove(idx);

            }

            System.out.println(distinctList);
            System.out.println(toutessalles);
            return toutessalles;
        }

    }
}

