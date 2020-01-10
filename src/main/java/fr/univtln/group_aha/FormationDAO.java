package fr.univtln.group_aha;

import java.sql.*;
import java.util.ArrayList;

public class FormationDAO extends DAO<Formation> {
    PreparedStatement statementFormationFind;

    public FormationDAO(Connection connect) {
        super(connect);

        String query = "SELECT * FROM Formation WHERE id = ?;";
        try {
            statementFormationFind = connect.prepareStatement(query,
                            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        DepartementDAO departementDAO = new DepartementDAO(connect);

        try {
            statementFormationFind.setInt(1, id);

            ResultSet resultat = statementFormationFind.executeQuery();

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
            PreparedStatement state = connect.prepareStatement(query,
                                                               ResultSet.TYPE_SCROLL_SENSITIVE,
                                                               ResultSet.CONCUR_UPDATABLE);

            ResultSet result = state.executeQuery();
            DepartementDAO departementDAO = new DepartementDAO(connect);

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
