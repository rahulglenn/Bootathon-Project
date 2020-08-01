/*
 * Description:The connection for the database is established.
 * Author     :Rahul Glenn
 */
package Bootathon.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBOperations {
    private static Connection conn;
    
    //connection object returned
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
