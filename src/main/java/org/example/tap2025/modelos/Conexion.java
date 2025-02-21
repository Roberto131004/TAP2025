package org.example.tap2025.modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "restaurantec";
    static private String USER = "admin2";
    static private String PWD = "Moreno131004";
    static private String  HOST = "localhost";
    static private String  PORT = "3306";
    public static Connection connection;

    public static void createConexion() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DB,USER,PWD);
            System.out.println("Conexión exitosa :)");
        }catch(Exception e){
            e.printStackTrace();

        }
    }
}
