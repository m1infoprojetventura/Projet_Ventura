package fr.univtln.group_aha;

import java.sql.*;
import java.util.ArrayList;

public class FormationDAO extends DAO<Formation> {
    public FormationDAO() {
        super(connect);
    }

    public FormationDAO(Connection connect) {
        super(connect);
    }

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
        Formation formation = new Formation();
        DepartementDAO departementDAO = new DepartementDAO();

        try {

            Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Formation WHERE id = %d;";

            ResultSet resultat = st.executeQuery(String.format(query, id));

            if (resultat.first()) {
                Departement departement = departementDAO.find(resultat.getInt("id_departement"));
                formation = new Formation(resultat.getInt("id"), resultat.getString("intitule"), departement);

            }
        }

        catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return formation;
        }

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
