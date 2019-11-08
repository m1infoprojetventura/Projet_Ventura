package fr.univtln.group_aha;

import java.sql.*;
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
            PersonneDAO personneDAO = new PersonneDAO();
            personneDAO.create(obj);
            String query = "INSERT INTO Etudiant (id_personne, formation) VALUES(?, ?)";
            Parcours parcours = obj.getParcours();

            // Cette méthode précompile la requête (query) donc sont exécution sera plus rapide.
            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());
            state.setString(2, parcours.getIntitule());

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
            String query = "DELETE FROM Etudiant WHERE id_personne = ?";

            PreparedStatement state = connect.prepareStatement(query);
            state.setInt(1, obj.getId());

            PersonneDAO personneDAO = new PersonneDAO();
            personneDAO.delete(obj);
            state.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Etudiant obj) {
        try {
            PersonneDAO personneDAO = new PersonneDAO();
            personneDAO.update(obj);

            String query = "UPDATE Etudiant SET id_personne = ?, formation = ?";
            PreparedStatement state = connect.prepareStatement(query);
            Parcours parcours = obj.getParcours();

            state.setInt(1, obj.getId());
            state.setString(2, parcours.getIntitule());

            state.executeUpdate();
        }

        catch (SQLException e) {
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
        Etudiant etudiant = new Etudiant();

        // Très temporaire (le temps d'en apprendre plus sur ces histoires de try... catch)
        try {
            // ResultSet.TYPE_SCROLL_INSENSITIVE indique (en gros) que les changements sur la base de données ne
            // modifieront pas ResultSet (objet stockant le résultat d'une requête) donc l'objet est "statique".
            // ResultSet.CONCUR_READ_ONLY indique que les changements sur le ResultSet n'affecteront pas
            // la base de données

            Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Personne INNER JOIN Etudiant " +
                           "ON Etudiant.id_personne = Personne.id " +
                           "WHERE Personne.id = %d;";

            ResultSet resultat = st.executeQuery(String.format(query, id));

            // Provisoire pour les tests à modifier selon les choix concernant l'existance de la classe Parcours
            Parcours parcours = new Parcours(resultat.getString("formation"));

            // resultat.first() bouge le curseur (oui il y a un curseur) sur la première ligne de <resultat>
            if (resultat.first())
                etudiant = new Etudiant(id, resultat.getString("nom"), resultat.getString("prenom"), parcours);

        }

        catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return etudiant;
        }
    }
}
