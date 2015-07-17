/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lio
 */
public class BaseDeDatos {
    
    private Connection conexion;
    
    public Connection Conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/Sistema", "root", "");
            System.out.println("Conexión a la Base de Datos del Sistema");
        }
        catch(ClassNotFoundException | SQLException error){
            System.out.println("No se pudo Conectar" + error);
        }
        return conexion;
    }
}
