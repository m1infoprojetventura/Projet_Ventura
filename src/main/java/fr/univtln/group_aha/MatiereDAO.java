package fr.univtln.group_aha;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class MatiereDAO extends DAO<Matiere> {
    @Override
    public void create(Matiere obj) {
        try {

            String query = "INSERT INTO Matiere(nom) VALUES(?)";
            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            String nom = obj.getNom();


            state.setString(1,nom );
            state.executeUpdate();

            // Obtenir la clé autogénéré par INSERT
            ResultSet key = state.getGeneratedKeys();
            if(key.first())
                obj.setId(key.getInt(1));

            int id_matiere = obj.getId();

            query = "INSERT INTO Matiere_Enseignant(id_matiere, id_enseignant) VALUES(?, ?)";
            state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            state.setInt(1, id_matiere);

            for (Enseignant enseignant: obj.getListeEnseignants()){
                state.setInt(2, enseignant.getId());

                state.executeUpdate();

            }

        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Matiere obj) {

    }

    @Override
    public void update(Matiere obj) {

    }

    @Override
    public Matiere find(int id) {
        return null;
    }

    @Override
    public ArrayList<Matiere> getData() {
        return null;
    }
}
