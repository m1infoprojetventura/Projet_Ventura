package fr.univtln.group_aha;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class ReservationDAO extends DAO<Reservation> {
    String personneAuthentifiee ="";

    public ReservationDAO() {
        super(connect);
    }

    public ReservationDAO(Connection connect) {
        super(connect);
    }


    public String getPersonneAuthentifiee() {
        return personneAuthentifiee;
    }

    public void setPersonneAuthentifiee(String personneAuthentifiee) {
        this.personneAuthentifiee = personneAuthentifiee;
    }

    @Override
    public void create(Reservation obj) {
        try {
            String query = "INSERT INTO Reservation (id_enseignant, id_salle,  date_reservation, id_seance) VALUES(?, ?, ?, ?)";

            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS,
                                                                        ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                        ResultSet.CONCUR_UPDATABLE);


            java.sql.Date d2 = new java.sql.Date(obj.getDate_reservation().getTime());

            state.setInt(1, obj.getId_enseignant());
            state.setInt(2, obj.getId_salle());
            state.setDate(3, d2);
            state.setInt(4, obj.getId_seance());
            state.executeUpdate();

            // Obtenir la clé autogénéré par INSERT
            ResultSet key = state.getGeneratedKeys();
            if(key.next())
                obj.setId(key.getInt(1));
        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Reservation obj) {

    }

    @Override
    public void update(Reservation obj) {
        try {
            String query = "UPDATE Reservation SET etat_reservation =? WHERE id = ?";
            PreparedStatement state = connect.prepareStatement(query,
                                                                ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                ResultSet.CONCUR_UPDATABLE);

            state.setString(1, "Confirmé");
            state.setInt(2, obj.getId());
            state.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void refuseupdate(Reservation obj) {
        try {
            String query = "UPDATE Reservation SET etat_reservation =? WHERE id = ?";
            PreparedStatement state = connect.prepareStatement(query,
                                                                ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                ResultSet.CONCUR_UPDATABLE);

            state.setString(1, "Refusé");
            state.setInt(2, obj.getId());
            state.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation find(int id) {
        return null;
    }

    @Override
    public ArrayList<Reservation> getData() {
        ArrayList<Reservation> resultat = new ArrayList();
        EnseignantDAO enseignantDAO = new EnseignantDAO();

        try {

            int id_enseignant =((Enseignant)enseignantDAO.getEnseignantByLogin(personneAuthentifiee)).getId();
            String query = "SELECT * FROM Reservation where id_enseignant=? ";
            PreparedStatement state = connect.prepareStatement(query,
                                                                ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                ResultSet.CONCUR_UPDATABLE);

            state.setInt(1,id_enseignant);
            ResultSet result = state.executeQuery();
            while(result.next()) {

                Reservation reservation = new Reservation(result.getInt("id"),result.getInt("id_enseignant"),result.getInt("id_salle"),
                        result.getDate("date_reservation"),result.getString("etat_reservation"), result.getInt("id_seance"));
                resultat.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }


    }
    public ArrayList<Reservation> getAllData() {
        ArrayList<Reservation> resultat = new ArrayList();
        try {
            String query = "SELECT * FROM Reservation  ";
            PreparedStatement state = connect.prepareStatement(query,
                                                                ResultSet.TYPE_SCROLL_SENSITIVE,
                                                                ResultSet.CONCUR_UPDATABLE);

            ResultSet result = state.executeQuery();
            while(result.next()) {
                Reservation reservation = new Reservation(result.getInt("id"),result.getInt("id_enseignant"),result.getInt("id_salle"),
                        result.getDate("date_reservation"),result.getString("etat_reservation"),result.getInt("id_seance"));
                resultat.add(reservation);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }


    }
}
