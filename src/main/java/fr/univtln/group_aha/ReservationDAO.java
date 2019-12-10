package fr.univtln.group_aha;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

public class ReservationDAO extends DAO<Reservation> {
    @Override
    public void create(Reservation obj) {
        try {
            String query = "INSERT INTO Reservation (id_enseignant, id_salle,  date_reservation) VALUES(?, ?, ?)";

            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date d2 = new java.sql.Date(obj.getDate_reservation().getTime());

            state.setInt(1, obj.getId_enseignant());
            state.setInt(2, obj.getId_salle());
            state.setDate(3, d2);
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
    public void delete(Reservation obj) {

    }

    @Override
    public void update(Reservation obj) {

    }

    @Override
    public Reservation find(int id) {
        return null;
    }

    @Override
    public ArrayList<Reservation> getData() {
        return null;
    }
}
