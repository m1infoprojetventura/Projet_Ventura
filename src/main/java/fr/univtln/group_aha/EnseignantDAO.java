package fr.univtln.group_aha;

import com.sun.jmx.remote.internal.ArrayQueue;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;

public class EnseignantDAO extends DAO<Enseignant> {
    @Override
    public void create(Enseignant obj) {
        try {
            String query = "INSERT INTO Enseignant (nom, prenom,  date_naissance, mdp, login, departement) VALUES(?, ?, ?, ?, ?, ?)";
            Departement departement = obj.getDepartement();

            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.

            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            String motdepasse = obj.generationMpd();
            java.sql.Date d2 = new java.sql.Date(obj.getDate_naissance().getTime());

            state.setString(1, obj.getNom());
            state.setString(2, obj.getPrenom());
            state.setDate(3, d2);
            state.setInt(4, motdepasse.hashCode());
            state.setString(5, obj.getLogin());
            state.setInt(6, departement.getId());
            state.executeUpdate();
            // Obtenir la clé autogénéré par INSERT
            ResultSet key = state.getGeneratedKeys();
            if(key.first())
                obj.setId(key.getInt(1));

            System.out.println(obj.getId());
        }


        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Enseignant obj) {
        try {
            String query = "DELETE FROM Enseignant WHERE id = ?";

            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());

            state.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Enseignant obj) {
        try {
            String query = "UPDATE Enseignant SET id_personne = ?, departement = ?";
            PreparedStatement state = connect.prepareStatement(query);
            Departement departement = obj.getDepartement();

            // state.setInt(1, obj.getId());
            state.setString(2, departement.getNom());

            state.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Enseignant find(int id) {
        Enseignant enseignant = null;

        // Très temporaire (le temps d'en apprendre plus sur ces histoires de try... catch)
        try {
            // ResultSet.TYPE_SCROLL_INSENSITIVE indique (en gros) que les changements sur la base de données ne
            // modifieront pas ResultSet (objet stockant le résultat d'une requête) donc l'objet est "statique".
            // ResultSet.CONCUR_READ_ONLY indique que les changements sur le ResultSet n'affecteront pas
            // la base de données

            Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM  Enseignant WHERE id = %d;";

            ResultSet resultat = st.executeQuery(String.format(query, id));

            // Provisoire pour les tests à modifier selon les choix concernant l'existance de la classe Parcours
            // Departement departement = new Departement(resultat.getString("departement"),);

            // resultat.first() bouge le curseur (oui il y a un curseur) sur la première ligne de <resultat>
            // new temporaire à remplacer par la ligne au dessus
            if (resultat.first())
                enseignant = new Enseignant(resultat.getString("nom"), resultat.getString("prenom"), resultat.getDate("date_naissance"), new Departement());

        }

        catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return enseignant;
        }
    }

    @Override
      public ArrayList<Enseignant> getData() {

        ArrayList<Enseignant> resultat = new ArrayList();
        try {
            String query = "SELECT * FROM Enseignant";
            PreparedStatement state = connect.prepareStatement(query);
            ResultSet result = state.executeQuery();
            DepartementDAO departementDAO = new DepartementDAO();

            while(result.next()) {
                Departement departement = departementDAO.find(result.getInt("departement"));

                resultat.add(new Enseignant(result.getString("nom"), result.getString("prenom"),
                                            result.getDate("date_naissance"), departement));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }
    }
}
