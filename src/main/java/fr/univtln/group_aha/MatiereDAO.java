package fr.univtln.group_aha;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MatiereDAO extends DAO<Matiere> {
    private static EnseignantDAO enseignantDAO;
    PreparedStatement statementMatiere;
    PreparedStatement statementMatiereEnseignant;

    public MatiereDAO(Connection connect) {
        super(connect);
        enseignantDAO = new EnseignantDAO(connect);
        try {
            String query = "SELECT * FROM Matiere WHERE id = ?";
            statementMatiere = connect.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            query = "SELECT * FROM Matiere_Enseignant WHERE id_matiere = ?";
            statementMatiereEnseignant = connect.prepareStatement(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
    public void associate(Matiere matiere,Enseignant enseignant){
        try {
        String query = "INSERT INTO Matiere_Enseignant(id_matiere,id_enseignant) VALUES(?,?)";
        // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
        PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int id_mat = matiere.getId();
        int id_ense = enseignant.getId();
        //System.out.println(id_ense);
        //System.out.println(id_mat);

        state.setInt(1,id_mat );
        state.setInt(2,id_ense);
        state.executeUpdate();
        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }

    }

    public List<Enseignant>  getAssocTeachers(Matiere matiere){
        List<Enseignant> enseignants= new ArrayList<>();
        try {

            String query = "SELECT * FROM Matiere_Enseignant WHERE id_matiere = ?";
            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int id_mat = matiere.getId();
            state.setInt(1,id_mat );
            ResultSet result = state.executeQuery();
            EnseignantDAO enseignantDAO = new EnseignantDAO(connect);
            while (result.next()) {
                Enseignant e = enseignantDAO.find(result.getInt("id_enseignant"));
                enseignants.add(e);

            }

        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }
        finally {
            return enseignants;
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
        Matiere matiere = null;

        try {
            String query;
            statementMatiere.setInt(1, id);

            ResultSet resultSet = statementMatiere.executeQuery();

            if(resultSet.first()) {
                // Je saus pas si affecter de nouveau une nouvellle variable à ce truc
                // ne va pas le casser
                statementMatiereEnseignant.setInt(1, id);
                ResultSet resultSet1 = statementMatiereEnseignant.executeQuery();
                List<Enseignant> enseignants = new ArrayList<>();

                while (resultSet1.next()) {
                    int id_enseignant = resultSet1.getInt("id_enseignant");
                    enseignants.add(enseignantDAO.find(id_enseignant));
                }

                matiere = new Matiere(id, resultSet.getString("nom"), enseignants);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return matiere;
        }

    }

    @Override
    public ArrayList<Matiere> getData() {
        ArrayList<Matiere> resultat = new ArrayList();

        try {
            String query = "SELECT * FROM Matiere";
            PreparedStatement state = connect.prepareStatement(query);
            ResultSet result = state.executeQuery();

            while(result.next()) {
                // Pour cause de flemme profonde (je sais pas si c'est plus long
                // que de le faire à la main, je pense que si car un truc avec le
                // PreparedStetement
                resultat.add(find(result.getInt("id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }
    }
}

