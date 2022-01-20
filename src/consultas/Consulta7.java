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
public class Consulta7 {
    
    public static void main(String[] args) {
        
        // Variables necesarias para poder conectarse a la base de datos
        Connection conn = null;             // Clase para conectarse a la base de datos
        PreparedStatement ps = null;        // Clase para preparar y ejecutar la consulta
        ResultSet rs = null;                // Clase para recorrer y almacenar los resultados
        String query = "select c.first_name, sum(amount) from payment p left join customer c on p.customer_id = c.customer_id where p.customer_id = ? group by p.customer_id;";
        
        try {
            
            // Se carga el driver necesario para conectar con una base de datos MySQL
            Class.forName(MySQLHelper.DRIVER);
            
            // Se conecta a la base datos
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            
            // Se prepara la consulta
            ps = conn.prepareStatement(query);
            
            // Se parsea la consulta
            ps.setInt(1, 1);
            
            // Se ejecuta la consulta
            rs = ps.executeQuery();
            
            // Se recorre la consulta
            while (rs.next()) {
                System.out.println("El total pagado al cliente " + rs.getString(1) + " es de " + rs.getInt(2));
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta7.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta7.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(Consulta6.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(Consulta6.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Logger.getLogger(Consulta6.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            
        }
        
    }
    
}
