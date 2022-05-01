/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Book;
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
public class RepositoryBook implements DBRepository<Book>{

    private Connection connection;
    
    public RepositoryBook() {
    }
    
    
    
    public List<Book> getAll() throws SQLException{
            List<Book> list=new ArrayList<>();
         try {
            String sql="SELECT * from Book b JOIN BookGenre bg ON b.BookGenreID=bg.BookGenreID JOIN Librarian l ON b.LibrarianID=l.LibrarianID";
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
                list.add(b); 
                
            }
            rs.close();
            s.close();
        } catch (SQLException ex) {
            throw  ex;
        } catch (IOException ex) {
            Logger.getLogger(RepositoryBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
  

    @Override
    public void add(Book param) throws Exception {
       try{
        String sql="INSERT INTO Book(Name, LibrarianID, BookGenreID) VALUES(?,?,?)";
        connection=DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, param.getName());
        ps.setLong(2, param.getLibrarian().getLibrarianID());
        ps.setLong(3, param.getBookGenre().getBookGenreID());
        
        ps.executeUpdate();
        ps.close();
        
        
       }catch(SQLException ex){
        throw ex;
       }    }

    @Override
    public void edit(Book param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Book param) throws Exception {
       
        try{
        String sql="DELETE FROM Book WHERE BookID=?";
        connection=DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setLong(1, param.getBookID());
        
        ps.executeUpdate();
        connection.commit();
        ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public long getMaxID() throws Exception {
        long max=0;
        String sql="SELECT MAX(BookID) as maxID FROM book";
        connection=DBConnectionFactory.getInstance().getConnection();

        try {
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                max=rs.getLong("maxID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }

    public boolean getBook(Book param)throws Exception{
 boolean exist=false;
        try{
        String sql="SELECT * FROM Book WHERE Name='"+ param.getName()+"'";
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
    public List<Book> getAll(Book param) throws Exception {
        List<Book> list=new ArrayList<>();
         try {
            String sql="SELECT * from Book b JOIN BookGenre bg ON b.BookGenreID=bg.BookGenreID JOIN Librarian l ON b.LibrarianID=l.LibrarianID WHERE b.Name LIKE '%"+param.getName()+"%' AND bg.BookGenreID="+param.getBookGenre().getBookGenreID();
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
                list.add(b); 
                
            }
            rs.close();
            s.close();
        } catch (SQLException ex) {
            throw  ex;
        } catch (IOException ex) {
            Logger.getLogger(RepositoryBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
