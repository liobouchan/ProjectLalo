/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.sql.*;

/**
 *
 * @author lio
 */

/**
 *
 * @author lio
 */
public class Conexion {
    public Connection conexion;
    
    public Conexion(){
        this.conexion = null;
    }
    
    public Conexion(Connection conexion){
        this.conexion = conexion;
    }
    
    public Conexion(Conexion unaConexion){
        conexion = unaConexion.conexion;
    }

    public void destruir(){
        this.conexion = null;
        System.gc();
    }
    
    public Connection init(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/escuela", "root", "");
            System.out.println("Conectado a la Base de Datos");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("No se pudo Conectar");
        }
        return conexion;
    }
    
    public Connection obtenerConexion(){
        return conexion;
    }
    
    public void cerrar(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }
            catch(SQLException e){
            }
        }
    }
    
    public void cerrar( java.sql.Statement statement ){
        if(statement != null){
            try{
                statement.close();
            }
            catch(Exception e){}
        }
    }
    
    public void destroy(){
        if(conexion != null){
            try{
                conexion.close();
                System.out.println("Conexión Cerrada");
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
    }
}