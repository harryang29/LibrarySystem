package LibrarySystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class LibraryModel {
    LinkedList <Book> books;
    LinkedList <User> users;
    Setting settings;
    Connection dbCon;
    LibraryModel(Connection dbCon){
        books = new LinkedList();
        users = new LinkedList();
        this.dbCon = dbCon;
    }
    String createBookTable(){
        Statement stmnts = null;
        try {
            stmnts = dbCon.createStatement();
            stmnts.executeUpdate("CREATE TABLE books ("
                + "bookid VARCHAR2(10) NOT NULL, "
                + "isbn VARCHAR2(10) NOT NULL, "
                + "title VARCHAR2(50) NOT NULL, "
                + "year VARCHAR2(10) NOT NULL, "
                + "author VARCHAR2(25) NOT NULL, "
                + "place VARCHAR2(50) NOT NULL, "
                + "publisher VARCHAR2(25) NOT NULL, "
                + "available VARCHAR2(10) NOT NULL, "
                + "reserve VARCHAR2(10) NOT NULL, "
                + "copies VARCHAR2(10) NOT NULL, "
                + "CONSTRAINT bookpk PRIMARY KEY (bookid))");
            return "Table succesfully created.";
        } catch (SQLException ex) {
            return ex.getErrorCode() + " " + ex.getMessage();
        } finally{
            if(stmnts != null){
                try {
                    dbCon.clearWarnings();
                    stmnts.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    String createUserTable(){
        Statement stmnts = null;
        try {
            stmnts = dbCon.createStatement();
            stmnts.executeUpdate("CREATE TABLE users ("
                + "userid VARCHAR2(10) NOT NULL, "
                + "username VARCHAR2(20) NOT NULL, "
                + "password VARCHAR2(20) NOT NULL, "
                + "borrowA VARCHAR2(10) , "
                + "borrowB VARCHAR2(10) , "
                + "borrowC VARCHAR2(10) , "
                + "reserveA VARCHAR2(10) , "
                + "reserveB VARCHAR2(10) , "
                + "reserveC VARCHAR2(10) , "
                + "CONSTRAINT userpk PRIMARY KEY (userid))");
            return "Table succesfully created.";
        } catch (SQLException ex) {
            return ex.getErrorCode() + " " + ex.getMessage();
        } finally{
            if(stmnts != null){
                try {
                    dbCon.clearWarnings();
                    stmnts.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    String createSettingTable(){
        Statement stmnts = null;
        try {
            stmnts = dbCon.createStatement();
            stmnts.executeUpdate("CREATE TABLE settings ("
                + "days VARCHAR2(10) NOT NULL, "
                + "books VARCHAR2(10) NOT NULL, "
                + "penalty VARCHAR2(10) NOT NULL, )");
            return "Table succesfully created.";
        } catch (SQLException ex) {
            return ex.getErrorCode() + " " + ex.getMessage();
        } finally{
            if(stmnts != null){
                try {
                    dbCon.clearWarnings();
                    stmnts.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    String addBookRow(Book std){
        String sql = "INSERT INTO books VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = dbCon.prepareStatement(sql);
            ps.setString(1, std.bookid);
            ps.setString(2, std.isbn);
            ps.setString(3, std.title);
            ps.setString(4, std.year);
            ps.setString(5, std.author);
            ps.setString(6, std.place);
            ps.setString(7, std.publisher);
            ps.setString(8, std.available);
            ps.setString(9, std.reserve);
            ps.setString(10, std.copies);
            
            int rows = ps.executeUpdate();
            return rows + " record(s) inserted!";
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1){
                return "ID cannot be duplicated!";
            }else if(ex.getErrorCode()==1400){
                return "Input all information!";
            }else if(ex.getErrorCode()==12899){
                return "Input exceeded maximum length!";
            }else{
                return ex.getErrorCode() + " " + ex.getMessage();
            }
        } finally{
            if(ps != null){
                try {
                    dbCon.clearWarnings();
                    ps.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    
    String addUserRow(User std){
        String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = dbCon.prepareStatement(sql);
            ps.setString(1, std.userid);
            ps.setString(2, std.un);
            ps.setString(3, std.pw);
            ps.setString(4, std.borrowA);
            ps.setString(5, std.borrowB);
            ps.setString(6, std.borrowC);
            ps.setString(7, std.reserveA);
            ps.setString(8, std.reserveB);
            ps.setString(9, std.reserveC);
            
            int rows = ps.executeUpdate();
            return rows + " record(s) inserted!";
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1){
                return "ID cannot be duplicated!";
            }else if(ex.getErrorCode()==1400){
                return "Input all information!";
            }else if(ex.getErrorCode()==12899){
                return "Input exceeded maximum length!";
            }else{
                return ex.getErrorCode() + " " + ex.getMessage();
            }
        } finally{
            if(ps != null){
                try {
                    dbCon.clearWarnings();
                    ps.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    LinkedList <Book> getBooks(){
        books.clear();
        Statement stmnts = null;
        try {
            stmnts = dbCon.createStatement();
            ResultSet rs = stmnts.executeQuery("SELECT * FROM books ORDER BY bookid");
            while(rs.next()){
                books.add(new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                                   rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
        } catch (SQLException ex) {
        } finally{
            if(stmnts != null){
                try {
                    dbCon.clearWarnings();
                    stmnts.close();
                } catch (SQLException ex) {
                }
            }
        }
        return books;
    }
    LinkedList <User> getUsers(){
        users.clear();
        Statement stmnts = null;
        try {
            stmnts = dbCon.createStatement();
            ResultSet rs = stmnts.executeQuery("SELECT * FROM users ORDER BY userid");
            while(rs.next()){
                users.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                                   rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
        } catch (SQLException ex) {
        } finally{
            if(stmnts != null){
                try {
                    dbCon.clearWarnings();
                    stmnts.close();
                } catch (SQLException ex) {
                }
            }
        }
        return users;
    }
    Setting getSettings(){
        Statement stmnts = null;
        try {
            stmnts = dbCon.createStatement();
            ResultSet rs = stmnts.executeQuery("SELECT * FROM settings");
                settings = new Setting(rs.getString(1),rs.getString(2),rs.getString(3));
        } catch (SQLException ex) {
        } finally{
            if(stmnts != null){
                try {
                    dbCon.clearWarnings();
                    stmnts.close();
                } catch (SQLException ex) {
                }
            }
        }
        return settings;
    }
    void execCustomSQL(String sql){
        Statement stmnts = null;
        try {
            stmnts = dbCon.createStatement();
            stmnts.executeUpdate(sql);
        } catch (SQLException ex) {
        } finally{
            if(stmnts != null){
                try {
                    dbCon.clearWarnings();
                    stmnts.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
    String removeBookRow(String col, String row) {
        String sql = "DELETE FROM books WHERE " + col + "=?";
        PreparedStatement ps = null;
        try {
            ps = dbCon.prepareStatement(sql);
            ps.setString(1, row);
            int rows = ps.executeUpdate();
            return rows + " record(s) deleted!";
        } catch (SQLException ex) {
            return ex.getErrorCode() + " " + ex.getMessage();
        } finally{
            if(ps != null){
                try {
                    dbCon.clearWarnings();
                    ps.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    String removeUserRow(String col, String row) {
        String sql = "DELETE FROM users WHERE " + col + "=?";
        PreparedStatement ps = null;
        try {
            ps = dbCon.prepareStatement(sql);
            ps.setString(1, row);
            int rows = ps.executeUpdate();
            return rows + " record(s) deleted!";
        } catch (SQLException ex) {
            return ex.getErrorCode() + " " + ex.getMessage();
        } finally{
            if(ps != null){
                try {
                    dbCon.clearWarnings();
                    ps.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    String editBookRow(String row, Book std) {
        String sql = "UPDATE books "
           + "SET title = ?, year = ?, author = ?, place = ?, publisher = ?, copies = ?"
           + "WHERE bookid = ?";
        PreparedStatement ps = null;
        try {
            ps = dbCon.prepareStatement(sql);
            ps.setString(1, std.title);
            ps.setString(2, std.year);
            ps.setString(3, std.author);
            ps.setString(4, std.place);
            ps.setString(5, std.publisher);
            ps.setString(6, std.copies);
            
            ps.setString(7, row);
            int rows = ps.executeUpdate();
            return rows + " record(s) updated!";
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1400){
                return "Input all information!";
            }else if(ex.getErrorCode()==12899){
                return "Input exceeded maximum length!";
            }else{
                return ex.getErrorCode() + " " + ex.getMessage();
            }
        } finally{
            if(ps != null){
                try {
                    dbCon.clearWarnings();
                    ps.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    String editUserRow(String row, User std) {
        String sql = "UPDATE users "
           + "SET username = ?, password = ?, borrowA = ?, borrowB = ?, borrowC = ?, reserveA = ?, reserveB = ?, reserveC = ?"
           + "WHERE userid = ?";
        PreparedStatement ps = null;
        try {
            ps = dbCon.prepareStatement(sql);
            ps.setString(1, std.un);
            ps.setString(2, std.pw);
            ps.setString(3, std.borrowA);
            ps.setString(4, std.borrowB);
            ps.setString(5, std.borrowC);
            ps.setString(6, std.reserveA);
            ps.setString(7, std.reserveB);
            ps.setString(8, std.reserveC);
            
            ps.setString(9, row);
            int rows = ps.executeUpdate();
            return rows + " record(s) updated!";
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1400){
                return "Input all information!";
            }else if(ex.getErrorCode()==12899){
                return "Input exceeded maximum length!";
            }else{
                return ex.getErrorCode() + " " + ex.getMessage();
            }
        } finally{
            if(ps != null){
                try {
                    dbCon.clearWarnings();
                    ps.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    String editSettingRow(Setting settings) {
        String sql = "UPDATE settings "
           + "SET days = ?, penalty = ?";
        PreparedStatement ps = null;
        try {
            ps = dbCon.prepareStatement(sql);
            ps.setString(1, settings.days);
            ps.setString(2, settings.penalty);
            
            int rows = ps.executeUpdate();
            return rows + " record(s) updated!";
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1400){
                return "Input all information!";
            }else if(ex.getErrorCode()==12899){
                return "Input exceeded maximum length!";
            }else{
                return ex.getErrorCode() + " " + ex.getMessage();
            }
        } finally{
            if(ps != null){
                try {
                    dbCon.clearWarnings();
                    ps.close();
                } catch (SQLException ex) {
                    return ex.getErrorCode() + " " + ex.getMessage();
                }
            }
        }
    }
    String editSettingsRow(Setting settings) {
        return "updated!";
    }
}
