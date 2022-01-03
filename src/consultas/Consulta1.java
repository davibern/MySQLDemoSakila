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
public class Consulta1 {
    
    public static void main(String[] args) throws ClassNotFoundException {
        
        // Variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; 
        String query = "select * from actor";       // Obtener todos los actores de la tabla ACTOR
        int size = 0;
        
        try {
            // Conexión con la base de datos y ejecución de la consulta
            Class.forName(MySQLHelper.DRIVER).newInstance();
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            pstmt = (PreparedStatement) conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            // Encabezado de los datos por la salida estándar
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("%5s %-15s %-15s %-15s\n", "ID", "FIRST NAME", "LAST NAME", "MODIFIED");
            System.out.println("---------------------------------------------------------------------------------");
            
            // Recorrer el ResultSet de principio a fin para luego mostrarlo por pantalla
            while (rs.next()) {
                System.out.printf("%5d %-15s %-15s %-15s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4));
                size = rs.getRow();
            }
            
            // Finalización de la ejecución
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Registros consultados en la tabla ACTOR: " + size);
            System.out.println();
            
        } catch (InstantiationException ex) {
            Logger.getLogger(Consulta1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Consulta1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar el objeto que contiene los resultados
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("SQL Exception: " + e.toString());
                }
            }
            
            // Cerrar el objeto que contiene la sentencia
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println("SQL Exception: " + e.toString());
                }
            }
            
            // Cerrar el objeto que contiene la conexión
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("SQL Exception: " + e.toString());
                }
            }
        }
        
    }
    
}
