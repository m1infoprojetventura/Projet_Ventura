package fr.univtln.group_aha;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class SalleDAO extends DAO<Salle> {

    PreparedStatement statementFindSalle;
    PreparedStatement statementFindSalleMateriel;
    public SalleDAO(Connection connect) {
        super(connect);
        try {
            String query = "SELECT * FROM Salle WHERE id =?";
            statementFindSalle = connect.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query2 = "SELECT * FROM Materiel WHERE id_salle = ?";
            statementFindSalleMateriel = connect.prepareStatement(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void create(Salle obj) {
        List<String> listemateriel = new ArrayList<>();
        Iterator it = obj.getMateriels().iterator();
        while (it.hasNext()){
            listemateriel.add(it.next().toString());
        }
        try {

            String query = "INSERT INTO Salle (capacite, nom) VALUES(?, ?)";
            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            String nom = obj.getNom();
            int capacite = obj.getCapacite();


            state.setInt(1, capacite);
            state.setString(2,nom );
            state.executeUpdate();
            // Obtenir la clé autogénéré par INSERT
            ResultSet key = state.getGeneratedKeys();
            if(key.next())
                obj.setId(key.getInt(1));
            int id_salle = obj.getId();

            query = "INSERT INTO Materiel (id_salle,type) VALUES(?, ?)";
            state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            state.setInt(1,id_salle);
            for (String item : listemateriel){
                state.setString(2,item);
                state.executeUpdate();
            }
        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Salle obj) {
        try {

            String query = "DELETE FROM Salle WHERE id = ?";


            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());

            state.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Salle obj) {
        try {
            String query = "UPDATE Salle SET capacite=?, nom=? WHERE id=?;";
            PreparedStatement state = connect.prepareStatement(query);

            String nom = obj.getNom();
            int id = obj.getId();
            int capacite = obj.getCapacite();
            state.setInt(1, capacite);
            state.setString(2, nom);
            state.setInt(3, id);
            state.executeUpdate();


            query = "DELETE FROM Materiel WHERE id_salle = ?";
            state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            state.setInt(1, id);
            state.executeUpdate();


            List<String> listemateriel = new ArrayList<>();
            Iterator it = obj.getMateriels().iterator();
            while (it.hasNext()) {
                listemateriel.add(it.next().toString());
            }

            query = "INSERT INTO Materiel (id_salle,type) VALUES(?, ?)";
            state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            state.setInt(1, id);
            for (String item : listemateriel) {
                state.setString(2, item);
                state.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Salle find(int id) {
        Salle salle = new Salle() ;
        try {
            statementFindSalle.setInt(1,  id);
            ResultSet resultat = statementFindSalle.executeQuery();

            if (resultat.first()) {

                ArrayList<Materiel.TypeMateriel> resultat2 = new ArrayList<>();

                statementFindSalleMateriel.setInt(1,resultat.getInt("id"));
                ResultSet result2 = statementFindSalleMateriel.executeQuery();
                while (result2.next()){
                    
                    if(result2.getString("type").equals("IMPRIMANTE")){resultat2.add(Materiel.TypeMateriel.IMPRIMANTE);}
                    if(result2.getString("type").equals("ORDINATEUR")){resultat2.add(Materiel.TypeMateriel.ORDINATEUR);}
                    if(result2.getString("type").equals("VIDEO_PROJECTEUR")){resultat2.add(Materiel.TypeMateriel.VIDEO_PROJECTEUR);}
                    if(result2.getString("type").equals("TABLEAU_TACTIL")){resultat2.add(Materiel.TypeMateriel.TABLEAU_TACTIL);}
                    //System.out.println(result.getInt("id")+result2.getString("type"));

                }
                return salle = new Salle(resultat.getString("nom"),resultat2 ,resultat.getInt("capacite"),resultat.getInt("id"));

            }
        }

        catch (SQLException e) {
            e.printStackTrace();

        }
        return salle;
    }

    @Override
    public ArrayList<Salle> getData() {

        ArrayList<Salle> resultat = new ArrayList();

        try {
            String query = "SELECT * FROM Salle";
            PreparedStatement state = connect.prepareStatement(query);
            ResultSet result = state.executeQuery();

            while(result.next()) {
                ArrayList<Materiel.TypeMateriel> resultat2 = new ArrayList<>();

                String query2 = "SELECT * FROM Materiel WHERE id_salle = ?";
                PreparedStatement state2 = connect.prepareStatement(query2);

                state2.setInt(1,result.getInt("id"));
                ResultSet result2 = state2.executeQuery();
                while (result2.next()){
                    if(result2.getString("type").equals("IMPRIMANTE")){resultat2.add(Materiel.TypeMateriel.IMPRIMANTE);}
                    if(result2.getString("type").equals("ORDINATEUR")){resultat2.add(Materiel.TypeMateriel.ORDINATEUR);}
                    if(result2.getString("type").equals("VIDEO_PROJECTEUR")){resultat2.add(Materiel.TypeMateriel.VIDEO_PROJECTEUR);}
                    if(result2.getString("type").equals("TABLEAU_TACTIL")){resultat2.add(Materiel.TypeMateriel.TABLEAU_TACTIL);}
                    //System.out.println(result.getInt("id")+result2.getString("type"));

                }
                resultat.add(new Salle(result.getString("nom"),resultat2,result.getInt("capacite"),result.getInt("id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }
    }
}
