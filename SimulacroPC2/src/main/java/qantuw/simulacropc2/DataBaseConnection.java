/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library"; // Cambia 'library' por el nombre de tu base de datos
    private static final String USER = "root"; // Usuario de tu base de datos
    private static final String PASSWORD = "qantuw"; // Contraseña de tu base de datos

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}

