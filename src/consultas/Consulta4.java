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
public class Consulta4 {
    
    public static void main(String[] args) {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "select actor_id, count(film_id) from film_actor group by actor_id";
        int size = 0;
        
        try {
            Class.forName(MySQLHelper.DRIVER);
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            pstmt = (PreparedStatement) conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            System.out.println("-----------------------------");
            System.out.printf("%5s %-15s\n", "ACTOR", "FIMLS");
            System.out.println("-----------------------------");
            
            while (rs.next()) {
                System.out.printf("%5s %-15s\n", rs.getInt(1), rs.getInt(2));
                size = rs.getRow();
                size++;
            }
            
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("Número de actores: " + size);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta4.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("SQL Exception: " + e.toString());
                }
            }
            
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println("SQL Exception: " + e.toString());
                }
            }
            
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
