/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.BookGenre;
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
public class RepositoryGenre implements DBRepository<BookGenre>{
    private Connection connection;

    public RepositoryGenre() {
    }

    
    
    public List<BookGenre> getAll() throws SQLException, IOException{
     List<BookGenre> list=new ArrayList<>();
     connection=DBConnectionFactory.getInstance().getConnection();
     String sql="SELECT * from BookGenre";
     try {
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
             Long bgID=rs.getLong("BookGenreID");
             String name=rs.getString("Name");
             BookGenre bg=new BookGenre(bgID, name);
             list.add(bg);
            }
            rs.close();
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryGenre.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return list;
    }

    @Override
    public void add(BookGenre param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(BookGenre param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(BookGenre param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BookGenre> getAll(BookGenre param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
