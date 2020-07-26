/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootathon.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author rahul
 */
public class DBOperations {
    private static Connection conn;

    public static Connection getConn() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rewinddb", "testuser","tester");
        }
        catch(Exception ee)
        {
            System.out.println("Connection Error!"+ee);
        }
        return conn;
    }
    
    
}
