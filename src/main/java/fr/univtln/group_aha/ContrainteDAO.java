package fr.univtln.group_aha;

import java.sql.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;

public class ContrainteDAO  extends DAO<Contrainte> {

    public ContrainteDAO(Connection connect) {
        super(connect);
    }

    /**
     *
     * @param obj
     *    objet à ajouter à la base de données
     * @return retourne faux si la contrainte n'a pas été ajoutée dans la base de données
     */
    @Override
    public void create(Contrainte obj) throws EchecChangementTableException {
        try {
            String query1 = "SELECT COUNT(*) FROM Contrainte WHERE id_enseignant=? AND (date=?) AND NOT(?<=debut_contrainte OR ?>=fin_contrainte)";

            PreparedStatement statement = connect.prepareStatement(query1,
                                                                    ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                    ResultSet.CONCUR_UPDATABLE);

            int id_enseignant = obj.getEnseignant().getId();
            Time debut_seance = new Time(obj.getHdebut().getTimeInMillis());
            Time fin_seance = new Time(obj.getHfin().getTimeInMillis());
            java.sql.Date date = new java.sql.Date(obj.getHdebut().getTime().getTime());
            String motif = obj.getMotif();

            statement.setInt(1,  id_enseignant);
            statement.setDate(2,  date);
            statement.setTime(3,  fin_seance);
            statement.setTime(4,  debut_seance);

            ResultSet resultSet = statement.executeQuery();
            int nombre = -1;

            if(resultSet.next()) {
                System.out.println("Passer");
                nombre = resultSet.getInt(1);
            }

            if(nombre == 0) {
                String query = "INSERT INTO Contrainte (id_enseignant, date, debut_contrainte, fin_contrainte, motif) " + "VALUES(?, ?, ?, ?, ?)";
                // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
                PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS,
                                                                            ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                            ResultSet.CONCUR_UPDATABLE);

                state.setInt(1,  id_enseignant);
                state.setDate(2,  date);
                state.setTime(3,  debut_seance);
                state.setTime(4,  fin_seance);
                state.setString(5, motif);

                state.executeUpdate();

                // Obtenir la clé autogénéré par INSERT
                ResultSet key = state.getGeneratedKeys();

                if(key.next())
                    obj.setId(key.getInt(1));
            }

            else
                throw new EchecChangementTableException("Contrainte");
        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Contrainte obj) {
        try {
            String query = "DELETE FROM Contrainte WHERE id=?";

            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());

            state.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Contrainte obj) throws EchecChangementTableException {
        try {
            String query1 = "SELECT COUNT(*) FROM Contrainte WHERE id_enseignant=? AND (date=?) AND NOT(?<=debut_contrainte OR ?>=fin_contrainte) AND id !=?";

            PreparedStatement statement = connect.prepareStatement(query1,
                                                                    ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                    ResultSet.CONCUR_UPDATABLE);


            int id_enseignant = obj.getEnseignant().getId();
            Time debut_seance = new Time(obj.getHdebut().getTimeInMillis());
            Time fin_seance = new Time(obj.getHfin().getTimeInMillis());
            java.sql.Date date = new java.sql.Date(obj.getHdebut().getTime().getTime());
            String motif = obj.getMotif();


            statement.setInt(1,  id_enseignant);
            statement.setDate(2,  date);
            statement.setTime(3,  fin_seance);
            statement.setTime(4,  debut_seance);
            statement.setInt(5,  obj.getId());

            ResultSet resultSet = statement.executeQuery();
            int nombre = 0;

            if(resultSet.next()) {
                nombre = resultSet.getInt(1);
                System.out.println("On va voir " + nombre);
            }

            if(nombre == 0) {
                String query = "UPDATE Contrainte SET id_enseignant=?,  date=?, debut_contrainte=?, fin_contrainte=?, motif=?" +
                        "WHERE id=?";
                PreparedStatement state = connect.prepareStatement(query);
                state.setInt(1, id_enseignant);
                state.setDate(2, date);
                state.setTime(3, debut_seance);
                state.setTime(4, fin_seance);
                state.setString(5, motif);
                state.setInt(6, obj.getId());

                state.executeUpdate();
            }

            else
                // Etre un peu plus explicite que ça, du style mettre la date et l'heure de la séance
                throw new EchecChangementTableException("Contrainte");
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Contrainte find(int id) {
        return null;
    }

    public List<Contrainte> getContraiteEnseignant(Enseignant enseignant) {
        List<Contrainte> contraintes = new ArrayList<>();

        try {
            String query = "SELECT * from Contrainte WHERE id_enseignant=?";
            PreparedStatement state = connect.prepareStatement(query);

            int id_enseignant = enseignant.getId();

            state.setInt(1, id_enseignant);

            ResultSet result = state.executeQuery();

            String aux;
            while (result.next()) {
                Time debut_seance = result.getTime("debut_contrainte");
                Time fin_seance = result.getTime("fin_contrainte");

                Date date = result.getDate("date");
                String motif = result.getString("motif");
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

                Contrainte contrainte = new Contrainte(result.getInt("id"),
                                                       enseignant,
                                                       debutH,
                                                       finH,
                                                       motif);

               contraintes.add(contrainte);
            }

            return contraintes;

        } catch (SQLException e) {
            e.printStackTrace();

            return contraintes;
        }
    }

    @Override
    public ArrayList getData() {
        return null;
    }
}
