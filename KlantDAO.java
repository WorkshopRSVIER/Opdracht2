
import java.sql.SQLException;
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
public interface KlantDAO {
    public List<Klant> findAll() throws SQLException;
    public Klant findByID(int klantId) throws SQLException;
    public Klant findByName(String voornaam, String achternaam) throws SQLException;
    public Klant FindByName(String voornaam) throws SQLException;
    public boolean create(Klant klant) throws SQLException;
    public boolean update(Klant klant) throws SQLException;
    public boolean delete(Klant klant) throws SQLException;    
}
