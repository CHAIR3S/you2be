package com.tecnm.you2be.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {
    private static Connection conn = null;
    private static String hostname   = "localhost";
    private static String dbname = "proy_tap";
    private static String dbport = "3306";
<<<<<<< HEAD
    private static String dbuser = "admin_proy";
    private static String dbpass = "123";
=======
    private static String dbuser = "root";
    private static String dbpass = "Luischaip12";
>>>>>>> 20064b2d2ecbb92b33be46dc8b480c71cd0fadc9


    public static void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ hostname +":"+ dbport +"/" + dbname + "?serverTimezone=UTC", dbuser, dbpass);
            System.out.println("Successful database connection.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection()
    {
        if(conn == null) Connect();
        return conn;
    }

    public static void Disconnect() {
        try {
            conn.close();
            System.out.println("Database connection has been terminated.");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
