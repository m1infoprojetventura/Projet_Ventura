package fr.univtln.group_aha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonneDAO extends DAO<Personne> {

    public PersonneDAO() {
        super();
    }

    public void create(Personne obj) {
        try {
            String query = "INSERT INTO Personne (id, nom, prenom) VALUES(?, ?, ?)";

            PreparedStatement state = connect.prepareStatement(query);

            state.setInt(1, obj.getId());
            state.setString(2, obj.getNom());
            state.setString(3, obj.getPrenom());

            state.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Personne obj) {

        try {
            String query = "DELETE FROM Personne WHERE id = ?";
            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());
            state.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Personne obj) {

        try {
            String query = "UPDATE Personne SET id = ?, nom = ?, prenom = ?";
            PreparedStatement state = connect.prepareStatement(query);

            state.setInt(1, obj.getId());
            state.setString(2, obj.getNom());
            state.setString(3, obj.getPrenom());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Personne find(int id) {
        return null;
    }
}
