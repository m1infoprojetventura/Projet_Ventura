package fr.univtln.group_aha;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;

public class EtudiantDAO extends DAO<Etudiant> {

    public EtudiantDAO() {
        super();
    }

    /**
     * Constructeur de la classe générique DAO
     *
     * @param connect Objet permettant de se connecter à la base de données
     */
    public EtudiantDAO(Connection connect) {
    }



    @Override
    public void create(Etudiant obj) {
        try {
            String query = "INSERT INTO Etudiant (nom, prenom,  date_naissance, mdp, login, formation) VALUES(?, ?, ?, ?, ?, ?)";
            Formation formation = obj.getFormation();

            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query);
            String motdepasse = obj.generationMpd();
            java.sql.Date d2 = new java.sql.Date(obj.getDate_naissance().getTime());

            state.setString(1, obj.getNom());
            state.setString(2, obj.getPrenom());
            state.setDate(3, d2);
            state.setInt(4, motdepasse.hashCode());
            state.setString(5, obj.getLogin());
            state.setString(6, formation.getIntitule());
            state.executeUpdate();

        }

        catch (SQLException e) {
            lgr.log(Level.WARNING, e.getMessage(), e);
            e.printStackTrace();
        }
    }

    // Pas sûr pour la gestion des exceptions
    @Override
    public void delete(Etudiant obj) {
        try {
            String query = "DELETE FROM Etudiant WHERE login = ?";

            PreparedStatement state = connect.prepareStatement(query);
            state.setString(1, obj.getLogin());

            state.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Etudiant obj) {
      //  try {

      //      String query = "UPDATE Etudiant SET nom = ?, prenom = ?, date_naissance = ?, formation = ? WHERE login = ?";
      //      PreparedStatement state = connect.prepareStatement(query);
      //      Formation formation = obj.getFormation();

      //      state.setString(1, obj.getNom());
      //      state.setString(2, obj.getPrenom());
      //      state.setDate(3, (java.sql.Date) obj.getDate_naissance());
      //      state.setString(4, formation.getIntitule());
      //      state.setString(5,  login);
      //      state.executeUpdate();
      //  }

      //  catch (SQLException e) {
      //      e.printStackTrace();
      //  }
    }

    /**
     *
     * @param id
     *      L'identifiant de l'étudiant recherché
     * @return Un étudiant avec le numero id
     */
    @Override
    public Etudiant find(int id) {
        Etudiant etudiant = new Etudiant();

        // Très temporaire (le temps d'en apprendre plus sur ces histoires de try... catch)
        try {
            // ResultSet.TYPE_SCROLL_INSENSITIVE indique (en gros) que les changements sur la base de données ne
            // modifieront pas ResultSet (objet stockant le résultat d'une requête) donc l'objet est "statique".
            // ResultSet.CONCUR_READ_ONLY indique que les changements sur le ResultSet n'affecteront pas
            // la base de données

            Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Etudiant WHERE id = %d;";

            ResultSet resultat = st.executeQuery(String.format(query, id));

            DepartementDAO departementDAO = new DepartementDAO();
            Departement departement = departementDAO.find(resultat.getInt("departement"));

            // Provisoire pour les tests à modifier selon les choix concernant l'existance de la classe Parcours
            Formation formation = new Formation(resultat.getString("formation"), departement);
            java.util.Date date_naissance = resultat.getDate("date_naissance");

            // resultat.first() bouge le curseur (oui il y a un curseur) sur la première ligne de <resultat>
            if (resultat.first())
                etudiant = new Etudiant(resultat.getString("nom"), resultat.getString("prenom"), date_naissance, formation);

        }

        catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return etudiant;
        }
    }

    @Override
    public ArrayList<Etudiant> getData() {
        return null;
    }
}
