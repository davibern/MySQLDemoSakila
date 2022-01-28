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
public class Consulta8 {
    
    public static void main(String[] args) {
        
        // Variables
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select staff_id, first_name, email from staff;";
        
        try {
            // Cargar el driver
            Class.forName(MySQLHelper.DRIVER);
            
            // Conectar con la base de datos
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            
            // Preparar la consulta
            ps = (PreparedStatement) conn.prepareStatement(query);
            
            // Ejecutar consulta
            rs = ps.executeQuery();
            
            // Mostrar los resultados
            System.out.printf("\"%5s %10s %20s\n", "ID", "NOMBRE", "APELLIDO");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                System.out.printf("%5d %10s %20s\n", rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            
            System.out.println("Consulta terminada.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta8.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            // Cerrar objetos de la base de datos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Consulta8.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Consulta8.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Consulta8.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }
    
}
