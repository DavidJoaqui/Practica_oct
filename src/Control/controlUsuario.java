/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import Modelo.Persistencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David JP
 */
public class controlUsuario {
    
    Persistencia p = new Persistencia();
    
    public boolean insertarUsuario(int id_rol,String nombre,String es_activo){
        
        boolean inserto = false;

        String sql = "Insert into usuario(id_rol,nombre,activo) "
                + "values("+id_rol+",'"+nombre+"','"+es_activo+"');";

        inserto = p.ejecutarDML(sql);
        return inserto;        
    } 
    
    public boolean actualizarUsuario(int id_user, int id_rol,String nombre,String es_activo) {
        boolean actualizo = false;
        String sql = "Update usuario set nombre = '" + nombre + "', id_rol = '" + id_rol + "',activo="+es_activo+" where id_usuario = " + id_user;
        actualizo = p.ejecutarDML(sql);
        return actualizo;
    }
    
    public Object[][]consultarUsuarios(){
        
        Object data[][]=new Object[this.contarUsuarios()][4];
        ResultSet datos=null;
        
        String sql="select usuario.id_usuario,concat(rol.id_rol,'-',rol.nombre) id_rol_, usuario.nombre,usuario.activo from usuario inner join rol on(usuario.id_rol=rol.id_rol) order by usuario.id_usuario ASC";
        
        datos = p.ejecutarConsulta(sql);
        try {
            int i = 0;
            while(datos.next()){
                data[i][0] = datos.getInt("id_usuario");
                data[i][1] = datos.getString("id_rol_");
                data[i][2] = datos.getString("nombre");
                if(datos.getString("activo").equals("1")){
                    data[i][3] = "1-Activo";
                }else{
                    data[i][3] = "0-Inactivo";
                }
                
                
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public boolean eliminarUsuario(int id_usuario) {
        boolean elimino = false;
        String sql = "Delete from usuario where id_usuario = " + id_usuario+";";
        elimino = p.ejecutarDML(sql);
        return elimino;
    }
    
    public int contarUsuarios(){
        
        int numero = 0;
        String sql = "Select count(id_usuario) num from usuario";
        ResultSet res = p.ejecutarConsulta(sql);
        
        try {
          
            while(res.next()){
                numero = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return numero;
    }
    
    public int obtenerMaxidUsuario(){
        
        int numero = 0;
        String sql = "Select max(id_usuario) num from usuario";
        ResultSet res = p.ejecutarConsulta(sql);
        
        try {
          
            while(res.next()){
                numero = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return numero;
    }
    
    public Object[][] consultarUsuarioNombre(String nombre){
        Object data[][]=new Object[this.contarUsuarios()][4];
        ResultSet datos=null;
        
        String sql="select usuario.id_usuario,concat(rol.id_rol,'-',rol.nombre) id_rol_, usuario.nombre,usuario.activo from usuario inner join rol on(usuario.id_rol=rol.id_rol)"
                +" where usuario.nombre like '%"+nombre+"%' order by usuario.id_usuario ASC";
        
        datos = p.ejecutarConsulta(sql);
        try {
            int i = 0;
            while(datos.next()){
                data[i][0] = datos.getInt("id_usuario");
                data[i][1] = datos.getString("id_rol_");
                data[i][2] = datos.getString("nombre");
                data[i][3] = datos.getString("activo");
                
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public int consultarUsuarioNombre_identico(String nombre){
        //Object data[][]=new Object[this.contarUsuarios()][4];
        ResultSet datos=null;
        
        String sql="select count(*) from usuario"
                +" where usuario.nombre = '"+nombre+"' order by usuario.id_usuario ASC";
        
        datos = p.ejecutarConsulta(sql);
        int numero =0;
        try {
          
            while(datos.next()){
                numero = datos.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return numero;
    }
}
