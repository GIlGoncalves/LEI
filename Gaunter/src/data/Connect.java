/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gil
 */
public class Connect {
    
    
     static {
        try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static  Connection connect()  throws SQLException{
  
           String dbURL = "jdbc:sqlserver://localhost;databaseName=LEI;integratedSecurity=true";
            String user = "";
            String pass = "";
            return DriverManager.getConnection(dbURL, user, pass);
  }
    
}


