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
public class Procedimiento2 {
    
    public static void main(String[] args) {
        
        // Variables
        Connection conn = null;         // Objeto para crear la conexión
        CallableStatement cs = null;    // Objeto para crear el procedimiento almacenado
        
        try {
            
            // Se carga el driver JDBC
            Class.forName(MySQLHelper.DRIVER);
            
            // Se conecta con la base de datos
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            
            // Se prepara el procedimiento almacenado
            cs = conn.prepareCall("{call film_count_by_actor(?, ?)}");
            
            // Se parsea el argumento
            cs.setInt(1, 11);
            cs.registerOutParameter(2, Types.INTEGER);
            
            // Se ejecuta el procedimiento almacenado
            cs.execute();
            
            // Se recoge el valor
            int x = cs.getInt(2);
            
            // Se muestra por pantalla el resultado
            System.out.println("El número de películas del actor es de: " + x);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Procedimiento2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Procedimiento2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Procedimiento2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Procedimiento2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }
    
}
