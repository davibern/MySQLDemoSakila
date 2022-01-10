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
public class Consulta3 {
    
    public static void main(String[] args) {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "select distinct rating from film order by rating";
        int size = 0;
        
        try {
            Class.forName(MySQLHelper.DRIVER).newInstance();
            conn = DriverManager.getConnection(MySQLHelper.URL, MySQLHelper.USERNAME,  MySQLHelper.PASSWORD);
            pstmt = (PreparedStatement) conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            System.out.println("-----------------------------");
            System.out.println("RATINGS");
            System.out.println("-----------------------------");
            System.out.println();
            
            while (rs.next()) {
                System.out.println(rs.getString(1));
                size++;
            }
            
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("Rankings por edades disponibles: " + size);
            System.out.println();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Consulta3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Consulta3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta3.class.getName()).log(Level.SEVERE, null, ex);
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
