/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Author;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class RepositoryAuthor implements DBRepository<Author>{
    private Connection connection;

    public RepositoryAuthor() {
    }
    
    public void addAuthor(Author author) throws SQLException{
       try{
        String sql="INSERT INTO Author(FirstName, LastName) VALUES(?,?)";
        connection=DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, author.getFirstName());
        ps.setString(2, author.getLastName());
        ps.executeUpdate();
        ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
            throw ex;
       } catch (IOException ex) {
            Logger.getLogger(RepositoryAuthor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Author> getAll() throws Exception {
         List<Author> list=new ArrayList<>();
        String sql="SELECT * FROM Author";
        connection=DBConnectionFactory.getInstance().getConnection();

        
        try {
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                long authorID=rs.getLong("AuthorID");
                String firstName=rs.getString("FirstName");
                String lastName=rs.getString("LastName");
                Author a=new Author(authorID, firstName, lastName);
                list.add(a);
                
            }
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje liste Autor", ex);
        }
        return list;
    }

    @Override
    public void add(Author param) throws Exception {
         try{
        String sql="INSERT INTO Author(FirstName, LastName) VALUES(?,?)";
        connection=DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, param.getFirstName());
        ps.setString(2, param.getLastName());
        ps.executeUpdate();
        ps.close();
        }catch(SQLException ex){
           throw new Exception("Neuspesno cuvanje Autora", ex);

       }
    }

    @Override
    public void edit(Author param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Author param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getAuthor(Author param) throws Exception {
        boolean exist=false;
        try{
        String sql="SELECT * FROM AUTHOR WHERE firstName='"+ param.getFirstName()+"' AND lastName='"+param.getLastName()+"' ";
        connection=DBConnectionFactory.getInstance().getConnection();
       
        Statement s=connection.createStatement();
        ResultSet rs=s.executeQuery(sql);
        while(rs.next()){
            exist=true;
        }
        }catch(SQLException ex){
           throw new Exception("Author doesn't exist", ex);

        }
        return exist;
    }

    @Override
    public List<Author> getAll(Author param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
