/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Librarian;
import domain.Member;
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
public class RepositoryMember implements DBRepository<Member>{
    
    private Connection connection;


 
    
    public List<Member> getAll(){
        List<Member> list=new ArrayList<>();
        try {
           
            String sql="SELECT * FROM Member m JOIN Librarian l ON l.LibrarianID=m.LibrarianID";
            connection=DBConnectionFactory.getInstance().getConnection();

            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
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
               
                list.add(m);

            }
        } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
        }
        return list;
    }
    public long getMaxID() throws SQLException{
     long id=0;
     String sql="SELECT MAX(MemberID) as maxID FROM Member";
        try {
            connection=DBConnectionFactory.getInstance().getConnection();
        } catch (IOException ex) {
            Logger.getLogger(RepositoryMember.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                id=rs.getLong("maxID");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
     
     return id;
    }

    @Override
    public void add(Member param) throws Exception {
   try{
        String sql="INSERT INTO Member(FirstName, LastName, Contact, LibrarianID) VALUES(?,?,?,?)";
        connection=DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, param.getFirstName());
        ps.setString(2, param.getLastName());
        ps.setString(3, param.getContact());
        ps.setLong(4, param.getLibrarian().getLibrarianID());
        
        ps.executeUpdate();
        ps.close();
        
       }catch(SQLException ex){
           ex.printStackTrace();
           throw ex;
       }    }

    @Override
    public void edit(Member param)  throws Exception{
        String sql="UPDATE Member SET firstName=?, lastName=?, Contact=? WHERE MemberID=?";
        connection=DBConnectionFactory.getInstance().getConnection();
        try {
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, param.getFirstName());
            ps.setString(2, param.getLastName());
            ps.setString(3, param.getContact());
            ps.setLong(4, param.getMemberID());
            
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void delete(Member param) throws Exception {
       try{
        String sql="DELETE FROM Member WHERE MemberID=?";
        connection=DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setLong(1, param.getMemberID());
        
        ps.executeUpdate();
        connection.commit();
       }catch(SQLException ex){
             ex.printStackTrace();
             throw ex;
       }
    }

    public List<Member> getMembers(String firstName)throws Exception{
  List<Member> list=new ArrayList<>();
        try {
           
            String sql="SELECT * FROM Member m JOIN Librarian l ON l.LibrarianID=m.LibrarianID WHERE m.firstName LIKE '%"+firstName+"%'";
            connection=DBConnectionFactory.getInstance().getConnection();

            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                Long memberID=rs.getLong("MemberID");
                String FirstName=rs.getString("m.FirstName");
                String lastName=rs.getString("m.LastName");
                String contact=rs.getString("Contact");
                
                Long libID=rs.getLong("LibrarianID");
                String firstNameL=rs.getString("l.FirstName");
                String lastNameL=rs.getString("l.LastName");
                String username=rs.getString("Username");
                String password=rs.getString("Password");
                
                Librarian lib=new Librarian(libID, firstNameL, lastNameL, username, password);
                
                Member m=new Member(memberID, FirstName, lastName, contact, lib);
               
                list.add(m);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return list;   
    }

    @Override
    public List<Member> getAll(Member param) throws Exception {
        List<Member> list=new ArrayList<>();
        try {
           
            String sql="SELECT * FROM Member m JOIN Librarian l ON l.LibrarianID=m.LibrarianID  WHERE m.FirstName LIKE '%"+param.getFirstName()+"%' AND m.LastName LIKE '%"+param.getLastName()+"%'";
            connection=DBConnectionFactory.getInstance().getConnection();

            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
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
               
                list.add(m);

            }
        } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
        }
        return list;
    }
    
}
