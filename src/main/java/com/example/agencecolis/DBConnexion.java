package com.example.agencecolis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnexion {

    // Les identifiants de la connexion
    static  String user = "franck";
    static  String password ="Njie09.";
    // url vers la base de donnees
    static String url = "jdbc:mysql://localhost/AgenceColis";

    // le Driver mysql pour permettre la connexion
    static  String driver = "com.mysql.cj.jdbc.Driver";


    public static Connection getCon(){
        // creer  un object Connexion
        Connection conn = null;
        try {

            Class.forName(driver);
            // creer l'oject Connexion
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}
