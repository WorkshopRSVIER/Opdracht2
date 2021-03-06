
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author h_hag
 */
public class KlantDAOImpl implements KlantDAO {
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;
    String dburl = "jdbc:mysql://localhost/workshopdeel1";
    String user = "hallo";
    String password = "doei";

    @Override
    public List<Klant> findAll() throws SQLException {
        List<Klant> klanten = new ArrayList<>();
        Klant klant;
        String SQL_QUERY = "Select * from Klant";
        try {
            connection = DriverManager.getConnection(dburl, user, password);
            statement = connection.prepareStatement(SQL_QUERY);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                //stop klant object in klanten List
                klant = new Klant();
                klant.setKlantID(resultSet.getInt("klant_ID"));
                klant.setVoornaam(resultSet.getString("voornaam"));
                klant.setAchternaam(resultSet.getString("achternaam"));
                klant.setTussenvoegsel("tussenvoegsel");
                klant.setEmail(resultSet.getString("email"));
                klanten.add(klant);
            } 
        } finally {
            // kijk of er verbinding is en zo ja sluit deze
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }   
        }
        return klanten;
    }

    @Override
    public Klant findByID(int klantID) throws SQLException {
        String query = "Select * from Klant where klant_ID = " + klantID;
        Klant klant;
        try {
            connection = DriverManager.getConnection(dburl , user, password);
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ///crieer klant en set de gegevens er in
            klant = new Klant();
            klant.setKlantID(resultSet.getInt("klant_ID"));
            klant.setVoornaam(resultSet.getString("voornaam"));
            klant.setAchternaam(resultSet.getString("achternaam"));
            klant.setTussenvoegsel("tussenvoegsel");
            klant.setEmail(resultSet.getString("email"));
        } finally {
            // kijk of er verbinding is en zo ja sluit deze
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }   
        }
        return klant;
    }

    @Override
    public Klant findByName(String voornaam, String achternaam) throws SQLException {
        String query = "Select * from Klant where voornaam = " + voornaam + " and achternaam = " + achternaam;
        Klant klant;
        try {
            connection = DriverManager.getConnection(dburl , user, password);
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ///crieer klant en set de gegevens er in
            klant = new Klant();
            klant.setKlantID(resultSet.getInt("klant_ID"));
            klant.setVoornaam(resultSet.getString("voornaam"));
            klant.setAchternaam(resultSet.getString("achternaam"));
            klant.setTussenvoegsel("tussenvoegsel");
            klant.setEmail(resultSet.getString("email"));
        } finally {
            // kijk of er verbinding is en zo ja sluit deze
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }   
        }
        return klant;
    }

    @Override
    public Klant FindByName(String voornaam) throws SQLException {
        String query = "Select * from Klant where voornaam = " + voornaam;
        Klant klant;
        try {
            connection = DriverManager.getConnection(dburl , user, password);
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ///crieer klant en set de gegevens er in
            klant = new Klant();
            klant.setKlantID(resultSet.getInt("klant_ID"));
            klant.setVoornaam(resultSet.getString("voornaam"));
            klant.setAchternaam(resultSet.getString("achternaam"));
            klant.setTussenvoegsel("tussenvoegsel");
            klant.setEmail(resultSet.getString("email"));
        } finally {
            // kijk of er verbinding is en zo ja sluit deze
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }   
        }
        return klant;
    } 
    
    @Override
    public boolean create(Klant klant) throws SQLException {
        String query = "Insert into klant(voornaam, achternaam, tussenvoegsel, email) values (?, ? ,?, ?)";
        boolean isCreated = false;
        try {
            connection = DriverManager.getConnection(dburl , user, password);
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, klant.getVoornaam());
            statement.setString(2, klant.getAchternaam());
            statement.setString(3, klant.getTussenvoegsel());
            statement.setString(4, klant.getEmail());
            
            //wijs door database gegenereerde id toe aan klant
            resultSet = statement.getGeneratedKeys();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                klant.setKlantID(resultSet.getInt(1));
            }            
            statement.executeUpdate();            
            isCreated = true;                      
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }            
        }
        return isCreated;
    }

    @Override
    public boolean update(Klant klant) throws SQLException {
        String query = "update klant set voornaam = ?, set achternaam = ?, set tussenvoegsel = ?, email = ? where klant_ID = ?";
        boolean isUpdated = false;
        try {
            connection = DriverManager.getConnection(dburl , user, password);
            statement = connection.prepareStatement(query);
            statement.setString(1, klant.getVoornaam());
            statement.setString(2, klant.getAchternaam());
            statement.setString(3, klant.getTussenvoegsel());
            statement.setString(4, klant.getEmail());
            statement.setInt(5, klant.getKlantID());            
            statement.executeUpdate();            
            isUpdated = true;            
        } finally {
            // kijk of er verbinding is en zo ja sluit deze
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }   
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Klant klant) throws SQLException {
        String query = "Delete from klant where klant_ID = " + klant.getKlantID();
        boolean isDeleted = false;
        try {
            connection = DriverManager.getConnection(dburl , user, password);
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            isDeleted = true;
        } finally {
            // kijk of er verbinding is en zo ja sluit deze
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }   
        }
        return isDeleted;
    }
    
}
