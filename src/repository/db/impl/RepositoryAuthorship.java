/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Authorship;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;

/**
 *
 * @author LORA
 */
public class RepositoryAuthorship  implements DBRepository<Authorship>{
    private Connection connection;

    public RepositoryAuthorship() {
    }

    
    @Override
    public List<Authorship> getAll() throws Exception {
        List<Authorship> list=new ArrayList<>();
        try{
            String sql="SELECT * FROM Authorship a JOIN Book ON BookID=a.BookID JOIN Author a1 ON a1.AuthorID=a.AuthorID";
            connection=DBConnectionFactory.getInstance().getConnection();
            Statement s=connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                
            }
 
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void add(Authorship param) throws Exception {
     try{
        String sql="INSERT INTO Authorship(BookID,AuthorID,SerialNumber) VALUES(?,?,?)";
        connection=DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setLong(1, param.getBook().getBookID());
        ps.setLong(2, param.getAuthor().getAuthorID());
        ps.setInt(3, param.getSerialNumber());
        
        System.out.println("Uneto:"+param.getAuthor().getFirstName());
        
        ps.executeUpdate();
        ps.close();
        connection.commit();
     }catch(SQLException ex){
         ex.printStackTrace();
         throw  ex;
     }

    }

    @Override
    public List<Authorship> getAll(Authorship param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void edit(Authorship param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Authorship param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
