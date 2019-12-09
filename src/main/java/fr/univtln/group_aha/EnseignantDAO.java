package fr.univtln.group_aha;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class EnseignantDAO extends DAO<Enseignant> {

    @Override
    public void create(Enseignant obj) {
        try {
            String query = "INSERT INTO Enseignant (nom, prenom, date_naissance, id_departement) VALUES(?, ?, ?, ?)";
            Departement departement = obj.getDepartement();

            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.

            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date d2 = new java.sql.Date(obj.getDate_naissance().getTime());

            state.setString(1, obj.getNom());
            state.setString(2, obj.getPrenom());
            state.setDate(3, d2);
            state.setInt(4, departement.getId());
            state.executeUpdate();
            // Obtenir la clé autogénéré par INSERT
            ResultSet key = state.getGeneratedKeys();
            if(key.first())
                obj.setId(key.getInt(1));

            // J'ai besoin de l'id pour la génération du login donc voila pourquoi cela a été fait séparament.
            query = "UPDATE Enseignant SET login=?, mdp=? WHERE id=?";
            state = connect.prepareStatement(query);
            obj.generationLogin();
            obj.generationMdp();

            state.setString(1, obj.getLogin());
            state.setInt(2, obj.getMdp());
            state.setInt(3, obj.getId());

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
            String query = "UPDATE Enseignant SET nom=?, prenom=?, date_naissance=?," +
                            "id_departement=?, mdp=? WHERE id=?;";
            PreparedStatement state = connect.prepareStatement(query);
            Departement departement = obj.getDepartement();

            java.sql.Date d2 = new java.sql.Date(obj.getDate_naissance().getTime());
            state.setString(1, obj.getNom());
            state.setString(2, obj.getPrenom());
            state.setDate(3,  d2);
            state.setInt(4, departement.getId());
            state.setInt(5, obj.getMdp());
            state.setInt(6, obj.getId());

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
            if (resultat.first()) {
                DepartementDAO departementDAO = new DepartementDAO();
                // Provisoire pour les tests à modifier selon les choix concernant l'existance de la classe Parcours
                Departement departement = departementDAO.find(resultat.getInt("id_departement"));
                enseignant = new Enseignant(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("prenom"), resultat.getDate("date_naissance"), departement);
            }

        }

        catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return enseignant;
        }
    }


    public ArrayList<String> getMatieres(Enseignant obj) {
        ArrayList<String> matieres = new ArrayList<>();
        MatiereDAO matiereDAO = new MatiereDAO();
        try {
            String query = "SELECT * FROM Matiere_Enseignant where id_enseignant =? ";
            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());
            ResultSet result = state.executeQuery();
            while (result.next()){
                //System.out.println(result.getInt("id_matiere"));
                Matiere matiere = matiereDAO.find(result.getInt("id_matiere"));
                matieres.add(matiere.getNom());

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return matieres;
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
                Departement departement = departementDAO.find(result.getInt("id_departement"));
                Enseignant enseignant =new Enseignant(result.getInt("id"), result.getString("nom"),
                        result.getString("prenom"), result.getDate("date_naissance"), departement);

                enseignant.setLogin(result.getString("login"));
                resultat.add(enseignant);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }
    }

    public Boolean verifierAuthResponsable(String login , String password){
        try {
            String query = "SELECT * FROM Responsable where login = ? and password = ?";
            PreparedStatement state = connect.prepareStatement(query);
            state.setString(1,login);
            state.setString(2,password);
            ResultSet result = state.executeQuery();
            while(result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }
    public String getTypeLogin(String login) {
        String resultat="";
        try {
            String query = "SELECT * FROM Responsable where login = ? ";
            PreparedStatement state = connect.prepareStatement(query);
            state.setString(1,login);
            ResultSet result = state.executeQuery();
            while(result.next()) {
                resultat ="Responsable";
            }
            String query2 = "SELECT * FROM Etudiant where login = ? ";
            state = connect.prepareStatement(query2);
            state.setString(1,login);
            result = state.executeQuery();
            while(result.next()) {
                resultat ="Etudiant";
            }
            String query3 = "SELECT * FROM Enseignant where login = ? ";
            state = connect.prepareStatement(query3);
            state.setString(1,login);
            result = state.executeQuery();
            while(result.next()) {
                resultat ="Enseignant";
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;

    }
}
