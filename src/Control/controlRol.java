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
public class controlRol {
    Persistencia p = new Persistencia();
    public Object[][] consultarRoles(){
        Object data[][]=new Object[this.contarRoles()][2];
        ResultSet datos=null;
        
        String sql="select id_rol, nombre from rol"
                +" order by id_rol ASC";
        
        datos = p.ejecutarConsulta(sql);
        try {
            int i = 0;
            while(datos.next()){
                
                data[i][0] = datos.getInt("id_rol");
                data[i][1] = datos.getString("nombre");
                
                
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
        public int contarRoles(){
        
        int numero = 0;
        String sql = "Select count(id_rol) num from rol";
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
        
    
}
