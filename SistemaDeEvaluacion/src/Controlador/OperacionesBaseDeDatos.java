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
import javax.swing.JOptionPane;
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
    
    public int ValidarMatricula(int matricula){
        sql = "SELECT Matricula from Alumno";
        
        try{
            statement = comodin.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                if(resultSet.getString("Matricula").equals(matricula)){
                        return 1;
                }        
            }
        }
        catch(SQLException e){
            System.out.println(e);
            System.out.println("Error en validarMatricula en OperacionesBasesDeDatos");
        }
        return 0;
    }
    
    public int RegistrarAlumno(int matricula, String Nombre, String ApellidoPaterno, String ApellidoMaterno){
        int n;
        try{

            if(ValidarMatricula(matricula) != 1){
                sql = "INSERT INTO Alumno (Matricula,Nombre,ApellidoPaterno,ApellidoMaterno)VALUES (?,?,?,?)";
                PreparedStatement pst = comodin.prepareStatement(sql);
                pst.setInt(1, matricula);
                pst.setString(2, Nombre);
                pst.setString(3, ApellidoPaterno);
                pst.setString(4, ApellidoMaterno);
                n = pst.executeUpdate();
                if( n > 0 ){
                    conexion.Desconectar();
                    return 1;
                }
            }else{
                conexion.Desconectar();
                return 0;
            }
        }
        catch(SQLException e){
            conexion.Desconectar();
            System.out.println(e);
        }
       conexion.Desconectar();
       return 0;
    }    
}
