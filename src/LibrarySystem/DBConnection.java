package LibrarySystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection conn;
    private String user;
    private String pw;
    private String drivertype;
    private String hostname;
    private String port;
    private String sid;
    DBConnection(){
        conn = null;
        user = "";
        pw = "";
        drivertype = "thin";
        hostname = "localhost";
        port = "1521";
        sid = "orcl";
    }
    void setDB(String hostname, String port, String sid){
        this.hostname = hostname;
        this.port = port;
        this.sid = sid;
    }
    void setDriverType(String drivertype){
        this.drivertype = drivertype;
    }
    void login(String user, String pw){
        this.user = user;
        this.pw = pw;
    }
    void connect(){
        if (conn == null) {
            String databaseURL = "jdbc:oracle:"+drivertype+":"+user+"/"+pw+"@"
                    + hostname + ":" + port + ":" + sid;
            System.out.println(databaseURL);
            try {
                conn = DriverManager.getConnection(databaseURL);
                if (conn != null) {
                    System.out.println("Successfully connected to the database");
                }
            } catch (SQLException ex) {
                System.out.println("An error occurred. Maybe user/password is invalid");
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }else{
            System.out.println("There is already an existing connection.");
        }
    }
    void disconnect(){
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Successfully disconnected from the database");
                conn = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    Connection getConnection(){
        return conn;
    }
    void executeStatement(String sql){
        
        
    }
}
