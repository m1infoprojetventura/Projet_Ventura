package fr.univtln.group_aha;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormationDAO extends DAO<Formation> {
    @Override
    public void create(Formation obj) {

    }

    @Override
    public void delete(Formation obj) {

    }

    @Override
    public void update(Formation obj) {

    }

    @Override
    public Formation find(int id) {
        return null;
    }

    @Override
    public ArrayList<Formation> getData() {
        ArrayList<Formation> resultat = new ArrayList();

        try {
            String query = "SELECT * FROM Formation";
            PreparedStatement state = connect.prepareStatement(query);
            ResultSet result = state.executeQuery();
            DepartementDAO departementDAO = new DepartementDAO();

            while(result.next()) {
                Departement departement = departementDAO.find(result.getInt("id_departement"));

                resultat.add(new Formation(result.getInt("id"), result.getString("intitule") , departement));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }

    }
}
