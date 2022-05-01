/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import ps.constant.MyServerConstants;

/**
 *
 * @author LORA
 */
public class DBConnectionFactory {
    private Connection connection;
    private static DBConnectionFactory instance;

    private DBConnectionFactory() {
        
    }

    public static DBConnectionFactory getInstance() {
        if(instance==null){
            instance=new DBConnectionFactory();
        }
        return instance;
    }
    
    
    public Connection getConnection() throws SQLException, IOException{
         if(connection==null || connection.isClosed()){
          try{
            Properties properties=new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            String url=properties.getProperty(MyServerConstants.DB_CONFIG_URL);
            String user=properties.getProperty(MyServerConstants.DB_CONFIG_USERNAME);
            String password=properties.getProperty(MyServerConstants.DB_CONFIG_PASSWORD);
            connection=DriverManager.getConnection(url,user,password);
            connection.setAutoCommit(false);
             }catch(SQLException ex){
                 System.out.println("No connection "+ex.getMessage());
                 throw ex;
             }
        }
         return connection;
    }
}
