package fr.univtln.group_aha;

import java.sql.*;
import java.util.ArrayList;

public class DepartementDAO extends DAO<Departement> {
    public DepartementDAO() {
        super(connect);
    }

    @Override
    public void create(Departement obj) {
    }

    @Override
    public void delete(Departement obj) {

    }

    @Override
    public void update(Departement obj) {

    }

    @Override
    public Departement find(int id) {
        Departement departement = new Departement();
        EnseignantDAO enseignantDAO = new EnseignantDAO();

        try {
            Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM Departement WHERE id = %d;";

            ResultSet resultat = st.executeQuery(String.format(query, id));

            if (resultat.first()) {
                Enseignant enseignant = enseignantDAO.find(resultat.getInt("id_responsable"));
                departement = new Departement(resultat.getInt("id"), resultat.getString("nom"), enseignant);

            }
        }

        catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return departement;
        }

    }

    @Override
    public ArrayList<Departement> getData() {
        ArrayList<Departement> resultat = new ArrayList();

        try {
            String query = "SELECT * FROM Departement";
            PreparedStatement state = connect.prepareStatement(query);
            ResultSet result = state.executeQuery();
            EnseignantDAO enseignantDAO = new EnseignantDAO();

            while(result.next()) {
                Enseignant enseignant = enseignantDAO.find(result.getInt("id_responsable"));

                resultat.add(new Departement(result.getInt("id"), result.getString("nom"), enseignant));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }
    }
}
