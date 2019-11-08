package fr.univtln.group_aha;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class EnseignantDAO extends DAO<Enseignant> {
    @Override
    public void create(Enseignant obj) {
        try {
            PersonneDAO personneDAO = new PersonneDAO();
            personneDAO.create(obj);
            String query = "INSERT INTO Enseignant (id_personne, departement) VALUES(?, ?)";
            Departement departement = obj.getDepartement();

            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());
            state.setString(2, departement.getNom());

            state.executeUpdate();
        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Enseignant obj) {
        try {
            String query = "DELETE FROM Enseignant WHERE id_personne = ?";

            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());

            PersonneDAO personneDAO = new PersonneDAO();
            personneDAO.delete(obj);
            state.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Enseignant obj) {
        try {
            PersonneDAO personneDAO = new PersonneDAO();
            personneDAO.update(obj);

            String query = "UPDATE Enseignant SET id_personne = ?, departement = ?";
            PreparedStatement state = connect.prepareStatement(query);
            Departement departement = obj.getDepartement();

            state.setInt(1, obj.getId());
            state.setString(2, departement.getNom());

            state.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Enseignant find(int id) {
        Enseignant enseignant = new Enseignant();

        // Très temporaire (le temps d'en apprendre plus sur ces histoires de try... catch)
        try {
            // ResultSet.TYPE_SCROLL_INSENSITIVE indique (en gros) que les changements sur la base de données ne
            // modifieront pas ResultSet (objet stockant le résultat d'une requête) donc l'objet est "statique".
            // ResultSet.CONCUR_READ_ONLY indique que les changements sur le ResultSet n'affecteront pas
            // la base de données

            Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Personne INNER JOIN Enseignant" +
                    "ON Enseignant.id_personne = Personne.id " +
                    "WHERE Personne.id = %d;";

            ResultSet resultat = st.executeQuery(String.format(query, id));

            // Provisoire pour les tests à modifier selon les choix concernant l'existance de la classe Parcours
            // Departement departement = new Departement(resultat.getString("departement"),);

            // resultat.first() bouge le curseur (oui il y a un curseur) sur la première ligne de <resultat>
            // new temporaire à remplacer par la ligne au dessus
            if (resultat.first())
                enseignant = new Enseignant(id, resultat.getString("nom"), resultat.getString("prenom"), new Departement());

        }

        catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return enseignant;
        }
    }
}
