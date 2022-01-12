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
 * @verion 1.0
 */
public class Consulta5 {
    
    public static void main(String[] args) {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "select f.film_id, f.title, y.`name` from film f inner join film_category c on f.film_id = c.film_id inner join category y on c.category_id = y.category_id";
        int size = 0;
        
        try {
            Class.forName(MySQLHelper.DRIVER);
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME, MySQLHelper.PASSWORD);
            pstmt = (PreparedStatement) conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            System.out.println("-----------------------------");
            System.out.printf("%5s %-30s %-30s\n", "ID", "TITLE", "NAME");
            System.out.println("-----------------------------");
            
            while (rs.next()) {
                System.out.printf("%5s %-30s %-30s\n", rs.getInt(1), rs.getString(2), rs.getString(3));
                size = rs.getRow();
                size++;
            }
            
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("Número de películas: " + size);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta5.class.getName()).log(Level.SEVERE, null, ex);
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
