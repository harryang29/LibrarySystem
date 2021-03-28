package Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TestConnection {
    static String drivertype = "thin";
    static String user = "harry";
    static String pw = "harry";
    static String hostname = "localhost";
    static String port = "1521";
    static String sid = "orcl"; 
    static String db = hostname + ":" + port + ":" + sid;

    public static void main(String[] args) {
        String databaseURL = "jdbc:oracle:"+drivertype+":"+user+"/"+pw+"@"+db;
         System.out.println(databaseURL);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseURL);
            if (conn != null) {
                System.out.println("Successfully connected to the database");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Successfully disconnected from the database");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
