/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.BookCopy;
import domain.Librarian;
import domain.LoanNote;
import domain.LoanNoteItem;
import domain.Member;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;

/**
 *
 * @author LORA
 */
public class RepositoryLN implements DBRepository<LoanNote>{

    private Connection connection;

    @Override
    public List<LoanNote> getAll() throws Exception {
        List<LoanNote> list=new ArrayList<>();
        try{
        String sql="SELECT * FROM LoanNote ln JOIN Librarian l ON l.LibrarianID=ln.LibrarianID JOIN Member m ON m.MemberID=ln.MemberID";
        connection=DBConnectionFactory.getInstance().getConnection();
            Statement s=connection.createStatement();
            ResultSet rs  = s.executeQuery(sql);
            while(rs.next()){
                Long memberID=rs.getLong("MemberID");
                String firstName=rs.getString("m.FirstName");
                String lastName=rs.getString("m.LastName");
                String contact=rs.getString("Contact");
                
                Long libID=rs.getLong("LibrarianID");
                String firstNameL=rs.getString("l.FirstName");
                String lastNameL=rs.getString("l.LastName");
                String username=rs.getString("Username");
                String password=rs.getString("Password");
                
                Librarian lib=new Librarian(libID, firstNameL, lastNameL, username, password);
                
                Member m=new Member(memberID, firstName, lastName, contact, lib);
                
                Long lnID=rs.getLong("LoanNoteID");
                Date date=rs.getDate("CreateDate");
             
                LoanNote ln=new LoanNote(lnID, date, lib, m, null);
                list.add(ln);
            }
       
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return list;
        
        }

    @Override
    public void add(LoanNote param) throws Exception {
        try{
            String sql="INSERT INTO LoanNote(CreateDate,LibrarianID,MemberID) VALUES(?,?,?)";
            connection=DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(param.getDate().getTime()));
            ps.setLong(2, param.getLibrarian().getLibrarianID());
            ps.setLong(3, param.getMember().getMemberID());
            
            ps.executeUpdate();
            
            List<LoanNoteItem> list=param.getListOfLoanNoteItems();
            for (LoanNoteItem lni : list) {
            String sql2="INSERT INTO LoanNoteItem(LoanNoteID, DateFrom, DateTo,BookID,BookCopyID) VALUES(?,?,?,?,?)";
  
            ps=connection.prepareStatement(sql2);
            
                ps.setLong(1, param.getLoanNote());
                ps.setDate(2, new java.sql.Date(lni.getDateFrom().getTime()));
                ps.setDate(3, new java.sql.Date(lni.getDateTo().getTime()));
                ps.setLong(4, lni.getBookCopy().getBook().getBookID());
                ps.setLong(5, lni.getBookCopy().getBookCopyID());
            
            
            ps.executeUpdate();
            }
            ps.close();
            connection.commit();
       
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(LoanNote param) throws Exception {
        try{
            String sql="UPDATE LoanNote SET CreateDate=?, LibrarianID=?, MemberID=? WHERE LoanNoteID=?";
            connection=DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(param.getDate().getTime()));
            ps.setLong(2, param.getLibrarian().getLibrarianID());
            ps.setLong(3, param.getMember().getMemberID());
            ps.setLong(4, param.getLoanNote());
            
            ps.executeUpdate();
            //ps.close();
            List<LoanNoteItem> list=param.getListOfLoanNoteItems();
           
            for (LoanNoteItem lni : list) {
            String sql2="UPDATE LoanNoteItem SET DateFrom=?,DateTo=?, BookID=?, BookCopyID=? WHERE LoanNoteID=? AND LoanNoteItemID=?";
            ps=connection.prepareStatement(sql2);
            ps.setDate(1, new java.sql.Date(lni.getDateFrom().getTime()));
            ps.setDate(2, new java.sql.Date(lni.getDateTo().getTime()));
            ps.setLong(3, lni.getBookCopy().getBook().getBookID());
            ps.setLong(4, lni.getBookCopy().getBookCopyID());
            ps.setLong(5, param.getLoanNote());
            ps.setLong(6, lni.getLoanNoteItemID());

            }
           ps.executeUpdate();
           ps.close();
           connection.commit();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(LoanNote param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getMaxID() {
        long id=0;
        try{
            String sql="SELECT MAX(LoanNoteID) as maksID FROM LoanNote";
            connection=DBConnectionFactory.getInstance().getConnection();
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                id=rs.getLong("maksID");
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(RepositoryLN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id+1;      }

    public void add(LoanNoteItem lni, Long loanNote)throws Exception{
            
        try{
            String sql2="INSERT INTO LoanNoteItem(LoanNoteID, DateFrom, DateTo,BookID,BookCopyID) VALUES(?,?,?,?,?)";
        
            connection=DBConnectionFactory.getInstance().getConnection();

            PreparedStatement ps1=connection.prepareStatement(sql2);
            
                ps1.setLong(1, loanNote);
                ps1.setDate(2, new java.sql.Date(lni.getDateFrom().getTime()));
                ps1.setDate(3, new java.sql.Date(lni.getDateTo().getTime()));
                ps1.setLong(4, lni.getBookCopy().getBook().getBookID());
                ps1.setLong(5, lni.getBookCopy().getBookCopyID());
            
            
            ps1.executeUpdate();
            connection.commit();
        }
    catch(Exception ex){
        ex.printStackTrace();
    }
    }

    @Override
    public List<LoanNote> getAll(LoanNote param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
