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
public class Procedimiento1 {
    
    public static void main(String[] args) {
        
        // Variables
        Connection conn = null;             // Objeto para conectarnos a la base de datos
        CallableStatement cs = null;        // Objeto para poder ejecutar un procedimiento almacenado
        
        try {
            
            // Cargar el driver JDBC
            Class.forName(MySQLHelper.DRIVER);
            
            // Conectar a la base de datos
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            
            // Ejecutar el procedimiento almacenado
            cs = conn.prepareCall("{call film_in_stock(?, ?, ?)}");
            
            // Parsear los atributos del procedimiento, en este caso, hay 2 de entrada y uno de salida, el último
            cs.setInt(1, 1);
            cs.setInt(2, 1);
            cs.registerOutParameter(3, Types.INTEGER);
            
            // Se ejecuta el procedimiento
            cs.execute();
            
            // Se recoge la salida del procedimiento almacenado
            int x = cs.getInt(3);
            
            // Se muestra la salida estándar
            System.out.println("El resultado es: " + x);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Procedimiento1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Procedimiento1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
