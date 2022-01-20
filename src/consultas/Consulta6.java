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
public class Consulta6 {
    
    public static void main(String[] args) {
        
        // Variables necesarias para poder conectar a la base de 
        Connection conn = null;             // Clase Conexión
        PreparedStatement ps = null;        // Clase que ejecuta la consulta
        ResultSet rs = null;                // Clase que recorre los datos y los almacena
        String query = "select sum(amount) from payment where customer_id = ? group by customer_id;";
        
        try {
            
            // Carga y ejecución del Driver para conectar a MySQL
            Class.forName(MySQLHelper.DRIVER);
            
            // Conexión a la base de datos
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            
            // Se prepara la consulta a la base de datos
            ps = (PreparedStatement) conn.prepareStatement(query);
            
            // Se parsea el parámetro de la consulta
            ps.setInt(1, 1);
            
            // Se ejecuta la consulta
            rs = ps.executeQuery();
            
            // Se recorre los resultados
            while (rs.next()) {
                System.out.println("Cantidad total pagado: " + rs.getInt(1));
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta6.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta6.class.getName()).log(Level.SEVERE, null, ex);
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
