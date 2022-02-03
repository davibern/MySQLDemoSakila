package procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.MySQLHelper;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class Procedimiento3 {
    
    public static void main(String[] args) {
        
        // Variables
        Connection conn = null;
        CallableStatement cs = null;
        
        try {
            // Cargamos el driver
            Class.forName(MySQLHelper.DRIVER);
            
            // Conectamos a la base de datos
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            
            // Cargamos el procedimiento almacenado
            cs = conn.prepareCall("{call film_count_by_actor(?, ?)}");
            
            // Se configura la entrada y salida
            cs.setInt(1, 8);
            cs.registerOutParameter(2, Types.INTEGER);
            
            // Se ejecuta el procedimiento almacenado
            cs.execute();
            
            // Se almacena el valor saliente
            int x = cs.getInt(2);
            
            // Se muestra por salida estandar
            System.out.println("El valor del procedimiento almacenado es: " + x);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Procedimiento3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Procedimiento3.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            // Se cierran los objetos de bases de datos
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Procedimiento3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Procedimiento3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }
    
}
