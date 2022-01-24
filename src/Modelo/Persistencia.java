/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author David JP
 */
public class Persistencia {
   public  ConexionBD cBD = null;
    
    public boolean ejecutarDML(String sql){
        boolean ejecuto = false;
        cBD = new ConexionBD();
        
        try{
            PreparedStatement ps = cBD.getConnection().prepareStatement(sql);
            int recibe = ps.executeUpdate();
            if(recibe > 0){
                ejecuto = true;
            }
        }
        catch(Exception e){
            System.out.println("Error: "+ e.toString());
        }
        
        cBD.desconectar();
        return ejecuto;
    }
    
    public ResultSet ejecutarConsulta(String sql){
        ResultSet datos = null;
        cBD = new ConexionBD();
        
        try{
           PreparedStatement ps = cBD.getConnection().prepareStatement(sql);
           datos = ps.executeQuery();
        }
        catch(Exception e){
            System.out.println("Error: "+ e.toString());
        }
        
        return datos;
    }
}
