package Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class TestSQLStatements {
    static String drivertype = "thin";
    static String user = "harry";
    static String pw = "harry";
    static String hostname = "localhost";
    static String port = "1521";
    static String sid = "orcl";
    static Connection conn;
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        String input;
        String databaseURL = "jdbc:oracle:"+drivertype+":"+user+"/"+pw+"@"
                    + hostname + ":" + port + ":" + sid;
        conn = null;
        try {
            conn = DriverManager.getConnection(databaseURL);
            if (conn != null) {
                System.out.println("Successfully connected to the database");
            }
            while(inp.hasNext()){
                input = inp.nextLine();
                if(input.equalsIgnoreCase("EXIT")){
                    break;
                }else{
                    execCustomSQL(input);
                }
                
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Successfully disconnected from the database");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    static void execCustomSQL(String sql){
        Statement stmnts = null;
        try {
            stmnts = conn.createStatement();
            System.out.println(stmnts.executeUpdate(sql));
            if(stmnts.getWarnings()!= null){
                System.out.println(stmnts.getWarnings().getMessage());
            }else if (stmnts.getResultSet()!= null){
                stmnts.getResultSet().next();
                do {
                    for(int i = 1; i <= stmnts.getResultSet().getMetaData().getColumnCount(); i ++){
                        System.out.print(stmnts.getResultSet().getMetaData().getColumnName(i)+": "+stmnts.getResultSet().getString(i) +", ");
                    }
                    System.out.println("");
                }while (stmnts.getResultSet().next());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode() + " --- " + ex.getMessage());
        } finally{
            if(stmnts != null){
                try {
                    conn.clearWarnings();
                    stmnts.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getErrorCode() + " --- " + ex.getMessage());
                }
            }
        }
    }
}