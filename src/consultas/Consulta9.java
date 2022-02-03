package consultas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.MySQLHelper;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class Consulta9 {
    
    public static void main(String[] args) {
        
        // Variables
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            // Cargamos el driver
            Class.forName(MySQLHelper.DRIVER);
            
            // Creamos la conexión con la base de datos
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            
            // Prepramos la consulta
            ps = (PreparedStatement) conn.prepareStatement("select count(*) from language;");
            
            // Ejecutamos la consulta
            rs = ps.executeQuery();
            
            // Recorremos las filas generadas
            while (rs.next()) {
                System.out.println("Existen " + rs.getInt(1) + " idiomas");
            }
                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta9.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta9.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            // Cerramos los objetos y conexiones
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Consulta9.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Consulta9.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Consulta9.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
