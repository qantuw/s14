/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class TestConexion {
    public static void main(String[] args) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            System.out.println("Conexión exitosa a la base de datos!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
