package fr.univtln.group_aha;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.ArrayList;

public class DepartementDAO extends DAO<Departement> {
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
            // ResultSet.TYPE_SCROLL_INSENSITIVE indique (en gros) que les changements sur la base de données ne
            // modifieront pas ResultSet (objet stockant le résultat d'une requête) donc l'objet est "statique".
            // ResultSet.CONCUR_READ_ONLY indique que les changements sur le ResultSet n'affecteront pas
            // la base de données

            Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Departement WHERE id = %d;";

            ResultSet resultat = st.executeQuery(String.format(query, id));

            // resultat.first() bouge le curseur (oui il y a un curseur) sur la première ligne de <resultat>
            // new temporaire à remplacer par la ligne au dessus
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
