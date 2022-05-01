/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Book;
import domain.BookCopy;
import domain.LoanNote;
import domain.LoanNoteItem;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
public class RepositoryLNItem implements DBRepository<LoanNoteItem>{
    
    private Connection connection;

    @Override
    public List<LoanNoteItem> getAll() throws Exception {
        List<LoanNoteItem> list=new ArrayList<>();
        try{
            String sql="SELECT * FROM LOANNOTEITEM lni JOIN BookCopy bc ON bc.BookCopyID=lni.BookCopyID";
            connection=DBConnectionFactory.getInstance().getConnection();
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
             
                long bcID=rs.getLong("BookCopyID");
                int year=rs.getInt("PublicityYear");
                boolean avaliable=rs.getBoolean("Available");
                BookCopy bc=new BookCopy(bcID, year, avaliable, null);
                
                long LNid=rs.getLong("LoanNoteID");
                //long id=rs.getLong("LoanNoteItemID");
                Date dateFrom=rs.getDate("DateFrom");
                Date dateTo=rs.getDate("DateTo");
                
                LoanNoteItem lni=new LoanNoteItem(LNid, LNid,dateFrom, dateTo, bc);
                list.add(lni);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void add(LoanNoteItem param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(LoanNoteItem param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(LoanNoteItem param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getMaxID() {
        long id=0;
        try{
            String sql="SELECT MAX(LoanNoteItemID) as maksID FROM LoanNoteItem";
            connection=DBConnectionFactory.getInstance().getConnection();
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                id=rs.getLong("maksID");
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }   catch (IOException ex) {
                Logger.getLogger(RepositoryLNItem.class.getName()).log(Level.SEVERE, null, ex);
            }
        return id+1;    }

    public void add(LoanNoteItem loanNoteItem, Long loanNote) throws Exception {
               String sql2="INSERT INTO LoanNoteItem(LoanNoteID, DateFrom, DateTo,BookID,BookCopyID) VALUES(?,?,?,?,?)";
               PreparedStatement ps1=connection.prepareStatement(sql2);
            
                ps1.setLong(1, loanNote);
                ps1.setDate(2, new java.sql.Date(loanNoteItem.getDateFrom().getTime()));
                ps1.setDate(3, new java.sql.Date(loanNoteItem.getDateTo().getTime()));
                ps1.setLong(4, loanNoteItem.getBookCopy().getBook().getBookID());
                ps1.setLong(5, loanNoteItem.getBookCopy().getBookCopyID());
            
            
            ps1.executeUpdate();
            connection.commit();
           // ps1.close();
    }

    public List<LoanNoteItem> getAll(long id) throws Exception{
        List<LoanNoteItem> list=new ArrayList<>();
        try{
            String sql="SELECT * FROM LOANNOTEITEM lni JOIN BookCopy bc ON bc.BookCopyID=lni.BookCopyID JOIN Book b ON b.BookID=bc.BookID WHERE LOANNOTEID="+id;
            connection=DBConnectionFactory.getInstance().getConnection();
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
             
                long bID=rs.getLong("BookID");
                String name=rs.getString("Name");
                Book b=new Book(bID, name, null, null, null, null);
                
                long bcID=rs.getLong("BookCopyID");
                int year=rs.getInt("PublicityYear");
                boolean avaliable=rs.getBoolean("Available");
                BookCopy bc=new BookCopy(bcID, year, avaliable, b);
                
                long LNid=rs.getLong("LoanNoteItemID");
                Date dateFrom=rs.getDate("DateFrom");
                Date dateTo=rs.getDate("DateTo");
                
                LoanNoteItem lni=new LoanNoteItem(LNid,LNid, dateFrom, dateTo, bc);
                list.add(lni);
            }    
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return list;
    }

    @Override
    public List<LoanNoteItem> getAll(LoanNoteItem param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        

}
