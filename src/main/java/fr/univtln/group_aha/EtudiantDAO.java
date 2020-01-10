package fr.univtln.group_aha;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class EtudiantDAO extends DAO<Etudiant> {

    /**
     * Constructeur de la classe générique DAO
     *
     * @param connect Objet permettant de se connecter à la base de données
     */
    public EtudiantDAO(Connection connect) {
        super(connect);
    }

    public boolean verifierAuthEtudiant(String login, String password) {
        try {
            String query = "SELECT * FROM Etudiant where login = ? and mdp= ?";
            String passwordEncode = Hachage.toHexString(Hachage.getSHA(password));
            PreparedStatement state = connect.prepareStatement(query);

            state.setString(1, login);
            state.setString(2, passwordEncode);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public void create(Etudiant obj) {
        try {
            String query = "INSERT INTO Etudiant (nom, prenom,  date_naissance, id_formation) VALUES(?, ?, ?, ?)";
            Formation formation = obj.getFormation();

            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date d2 = new java.sql.Date(obj.getDate_naissance().getTime());

            state.setString(1, obj.getNom());
            state.setString(2, obj.getPrenom());
            state.setDate(3, d2);
            state.setInt(4, formation.getId());
            state.executeUpdate();

            // Obtenir la clé autogénéré par INSERT
            ResultSet key = state.getGeneratedKeys();
            if(key.next())
                obj.setId(key.getInt(1));

            // J'ai besoin de l'id pour la génération du login donc voila pourquoi l'ajout de le login a été fait séparament.
            // Pour le mot de passe ce n'était pas nécéssaire mais bon, je voulais pas laisser le login seul. Anticipation
            // d'une eventuelle modification du la génération du mot de passe (incluant l'id).

            query = "UPDATE Etudiant SET login=?, mdp=? WHERE id=?";
            state = connect.prepareStatement(query);
            obj.generationLogin();
            obj.generationMdp();


            System.out.println(obj.getMdp());
            String chaine = Hachage.toHexString(Hachage.getSHA(obj.getMdp()));

            state.setString(1, obj.getLogin());
            state.setString(2, chaine);
            state.setInt(3, obj.getId());
            System.out.println("ID " + obj.getId());
            state.executeUpdate();
        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Pas sûr pour la gestion des exceptions
    @Override
    public void delete(Etudiant obj) {
        try {
            String query = "DELETE FROM Etudiant WHERE id = ?";

            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());

            state.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Etudiant obj) {
        try {
            String query = "UPDATE Etudiant SET nom =?, prenom =?, date_naissance =?, id_formation =?," +
                    "mdp = ? WHERE id = ?";
            PreparedStatement state = connect.prepareStatement(query);
            Formation formation = obj.getFormation();
            java.sql.Date d2 = new java.sql.Date(obj.getDate_naissance().getTime());

            obj.generationMdp();
            String chaine = Hachage.toHexString(Hachage.getSHA(obj.getMdp()));

            state.setString(1, obj.getNom());
            state.setString(2, obj.getPrenom());
            state.setDate(3,  d2);
            state.setInt(4, formation.getId());
            state.setString(5, chaine);
            state.setInt(6, obj.getId());
            state.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     *      L'identifiant de l'étudiant recherché
     * @return Un étudiant avec le numero id
     */
    @Override
    public Etudiant find(int id) {
        Etudiant etudiant = null;

        // Très temporaire (le temps d'en apprendre plus sur ces histoires de try... catch)
        try {
            // ResultSet.TYPE_SCROLL_INSENSITIVE indique (en gros) que les changements sur la base de données ne
            // modifieront pas ResultSet (objet stockant le résultat d'une requête) donc l'objet est "statique".
            // ResultSet.CONCUR_READ_ONLY indique que les changements sur le ResultSet n'affecteront pas
            // la base de données

            Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Etudiant WHERE id = %d;";

            ResultSet resultat = st.executeQuery(String.format(query, id));


            // resultat.first() bouge le curseur (oui il y a un curseur) sur la première ligne de <resultat>
            if (resultat.next()) {
                FormationDAO formationDAO = new FormationDAO(connect);
                // Provisoire pour les tests à modifier selon les choix concernant l'existance de la classe Parcours
                Formation formation = formationDAO.find(resultat.getInt("id_formation"));
                java.util.Date date_naissance = resultat.getDate("date_naissance");
                etudiant = new Etudiant(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("prenom"), date_naissance, formation);
            }
        }

        catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return etudiant;
        }
    }

    @Override
    public ArrayList<Etudiant> getData() {

        ArrayList<Etudiant> resultat = new ArrayList();

        try {
            String query = "SELECT * FROM Etudiant";
            PreparedStatement state = connect.prepareStatement(query);
            ResultSet result = state.executeQuery();
            FormationDAO formationDAO = new FormationDAO(connect);

            while(result.next()) {
                Formation formation = formationDAO.find(result.getInt("id_formation"));
                System.out.println(formation);
                Etudiant etudiant = new Etudiant(result.getInt("id"), result.getString("nom"),
                        result.getString("prenom"), result.getDate("date_naissance"), formation);
                etudiant.setLogin(result.getString("login"));

                resultat.add(etudiant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return resultat;
        }
    }

    public void modifierMotDePasse(Etudiant etudiant, String mdp) {
        String query = "UPDATE Etudiant SET mdp = ?, WHERE id=?;";
        try {
            PreparedStatement state = connect.prepareStatement(query);
            String mdpEncode = Hachage.toHexString(Hachage.getSHA(mdp));

            state.setString(1, mdpEncode);
            state.setInt(2, etudiant.getId());

            state.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public Etudiant getEtudiantByLogin(String loginEtudiant) {

        Etudiant etudiant = null;
        try {

            String query = "SELECT * FROM  Etudiant WHERE login = ?;";
            PreparedStatement state = connect.prepareStatement(query);
            state.setString(1, loginEtudiant);
            ResultSet resultat = state.executeQuery();
            if (resultat.next()) {
                etudiant = find(resultat.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }
}
