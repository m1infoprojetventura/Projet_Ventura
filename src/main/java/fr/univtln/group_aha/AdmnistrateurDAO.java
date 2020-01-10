package fr.univtln.group_aha;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class AdmnistrateurDAO extends DAO<Administrateur> {
    public AdmnistrateurDAO(Connection connect) {
        super(connect);
    }

    @Override
    public void create(Administrateur obj) throws EchecContrainteException {
    }

    @Override
    public void delete(Administrateur obj) {

    }

    @Override
    public void update(Administrateur obj) throws EchecContrainteException {

    }

    @Override
    public Administrateur find(int id) {
        return null;
    }

    @Override
    public ArrayList<Administrateur> getData() {
        return null;
    }

    public boolean verifierAuthAdministrateur(String login, String password) {
        try {
            String query = "SELECT * FROM Administrateur WHERE login = ? and password= ?";
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
}
