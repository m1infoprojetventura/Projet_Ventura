package fr.univtln.group_aha;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class SeanceDAO extends DAO<Seance> {
    // J'ai pensé pour un jour, un jour lointain (le plus possible) où à le create renverrai l'ID.
    @Override
    public void create(Seance obj) {
        try {

            String query = "INSERT INTO Seance (id_salle, id_enseignant, id_matiere, date, debut_seance, fin_seance) " +
                            "VALUES(?, ?, ?, ?, ?, ?)";
            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int id_salle = obj.getSalle().getId();
            int id_enseignant = obj.getEnseignant().getId();
            int id_matiere = obj.getMatiere().getId();
            Time debut_seance = new Time(obj.getHdebut().getTimeInMillis());
            Time fin_seance = new Time(obj.getHfin().getTimeInMillis());
            java.sql.Date date = new java.sql.Date(obj.getHdebut().getTime().getTime());

            state.setInt(1,  id_salle);
            state.setInt(2,  id_enseignant);
            state.setInt(3,  id_matiere);
            state.setTime(4,  debut_seance);
            state.setTime(5,  fin_seance);
            state.setDate(6,  date);

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

    }

    @Override
    public void update(Seance obj) {

    }

    @Override
    public Seance find(int id) {
        return null;
    }

    @Override
    public ArrayList<Seance> getData() {
        return null;
    }
}
