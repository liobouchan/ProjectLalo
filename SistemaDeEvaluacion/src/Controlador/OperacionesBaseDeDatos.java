/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author lio
 */
public class OperacionesBaseDeDatos {
   
   String sql;
   BaseDeDatos conexion = new BaseDeDatos();
   Connection comodin = conexion.Conectar();
   ResultSet resultSet;
   Statement statement;
    
    public int InicioDeSesion(String usuario , String password){
        sql = "SELECT * FROM Usuario WHERE usuario = '" + usuario + "' AND password = '" + password + "'";;
        int i = 0;
        
        try{
            PreparedStatement pstm = comodin.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            while( resultSet.next() ){
                System.out.println(resultSet.getString("usuario") + resultSet.getString("password"));
                i++;
                conexion.Desconectar();
                return i;
            }
            resultSet.close();
        }
        catch(SQLException e){
            System.out.println("Error Metodo InicioDeSesion de la Clase OperacionesBaseDeDatos");
            conexion.Desconectar();
        }
        conexion.Desconectar();
        return 0;
    }
}
