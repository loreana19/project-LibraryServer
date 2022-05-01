/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Book;
import domain.BookCopy;
import domain.BookGenre;
import domain.Librarian;
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
public class RepositoryBookCopy implements DBRepository<BookCopy>{

    private Connection connection;

    public RepositoryBookCopy() {
    }

    
    
    @Override
    public List<BookCopy> getAll() throws Exception {
        List<BookCopy> list=new ArrayList<>();
        try{
            String sql="SELECT * FROM BookCopy bc JOIN Book b ON b.BookID=bc.BookID JOIN BookGenre bg ON bg.BookGenreID=b.BookGenreID JOIN Librarian l ON l.LibrarianID=b.LibrarianID";
            connection=DBConnectionFactory.getInstance().getConnection();

            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                Long bookGID=rs.getLong("BookGenreID");
                String bgname=rs.getString("bg.Name");
                BookGenre bookGenre=new BookGenre(bookGID, bgname);
                
                Long libID=rs.getLong("LibrarianID");
                String firstName=rs.getString("FirstName");
                String lastName=rs.getString("LastName");
                String username=rs.getString("Username");
                String password=rs.getString("Password");
                Librarian librarian=new Librarian(libID, firstName, lastName, username, password);
                
                Long bookID=rs.getLong("BookID");
                String name=rs.getString("b.Name");
                Book b=new Book(bookID, name, librarian, bookGenre, null, null);
                
                long bcId=rs.getLong("BookCopyID");
                boolean avaliable=rs.getBoolean("Available");
                int year=rs.getInt("PublicityYear");
                BookCopy bc=new BookCopy(bcId, year, avaliable, b);
                
                list.add(bc);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void add(BookCopy param) throws Exception {
 try{
        String sql="INSERT INTO BookCopy(PublicityYear, BookID, Available) VALUES(?,?,?)";
        connection=DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1, param.getPublicationYear());
        ps.setLong(2, param.getBook().getBookID());
        ps.setBoolean(3, param.isAvailable());
   
        
        ps.executeUpdate();
        ps.close();
        
        
       }catch(SQLException ex){
        throw ex;
       }       }

    @Override
    public void edit(BookCopy param) throws Exception {
        String sql="UPDATE BookCopy SET publicityYear=?, BookID=?, Available=? WHERE BookCopyID=?";
        connection=DBConnectionFactory.getInstance().getConnection();
        try {
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1, param.getPublicationYear());
            ps.setLong(2, param.getBook().getBookID());
            ps.setBoolean(3, param.isAvailable());
            ps.setLong(4, param.getBookCopyID());
            
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void delete(BookCopy param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getMaxID() {
        long id=0;
        try{
            String sql="SELECT MAX(BookCopyID) as maksID FROM BookCopy";
            connection=DBConnectionFactory.getInstance().getConnection();
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                id=rs.getLong("maksID");
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(RepositoryBookCopy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id+1;
    }

    @Override
    public List<BookCopy> getAll(BookCopy param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
