/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Librarian;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;

/**
 *
 * @author LORA
 */
public class RepositoryLibrarian implements DBRepository<Librarian>{
    
    private Connection connection;
    
    public List<Librarian> getAll() throws SQLException{
     
            List<Librarian> list=new ArrayList<>();
          try {
            String sql="SELECT * FROM Librarian";
            connection=DBConnectionFactory.getInstance().getConnection();
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                Long libID=rs.getLong("LibrarianID");
                String firstName=rs.getString("FirstName");
                String lastName=rs.getString("LastName");
                String username=rs.getString("Username");
                String password=rs.getString("Password");
                
                Librarian lib=new Librarian(libID, firstName, lastName, username, password);
                list.add(lib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryLibrarian.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (IOException ex) {
            Logger.getLogger(RepositoryLibrarian.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void add(Librarian param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Librarian param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Librarian param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Librarian> getAll(Librarian param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
