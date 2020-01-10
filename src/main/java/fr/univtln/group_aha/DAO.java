package fr.univtln.group_aha;
import java.sql.*;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * Classe "d'interface" entre la base de données (couche d'accès aux données et
 * la couche métier: classe manipulant les données)
 * @author Adrien
 * @author Anouar
 * @author Haribou
 * @param <T>
 *     Classe de la couche métier
 */
public abstract class DAO<T> {

    Logger lgr = Logger.getLogger(DAO.class.getName());
    static Connection connect;

    public DAO(Connection connect) {
        this.connect = connect;
    }
    // throws sera à discuter
    /**
     * methode de création
     * @param obj
     *    objet à ajouter à la base de données
     * @throws java.sql.SQLException  echec de la création d'un champ T
     */
    public abstract void create(T obj) throws EchecContrainteException, EchecSeancexception;

    // throws sera à discuter
    /**
     * methode de destruction
     * @param obj
     *  objet à supprimer de la base de données
     * @throws java.sql.SQLException echec de la destruction d'un champ T
     */
    public abstract void delete(T obj);

    // throws sera à discuter
    /**
     * méthode de mise à jour
     * @param obj
     *   objet à modifier dans la base de données T
     * @throws java.sql.SQLException echec de la mise à jour d'un champ T
     */
    public abstract void update(T obj) throws EchecContrainteException;

    // Penser à peut-être surchargé find (dans le cas où la clé primaire ne serait pas un nombre, je sais pas encore
    // comment, je veux dire de façon propre).
    /**
     * méthode de recherche d'un élément T
     * @param id
     */
    public abstract T find(int id);

    /**
     *
     * @return Une liste de tous les éléments de T contenu dans la base de données
     */
    public abstract ArrayList<T> getData();
}
