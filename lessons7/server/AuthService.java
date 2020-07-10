package server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String getNikByLoginAndPass(String login, String pass){
        String sql = String.format("select id, nickname, auth from main where login='%s' and password='%s'",login,pass);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                if(rs.getInt("auth")==0) {
                    String sql2 = String.format("UPDATE main set auth='1' where id='%d'",rs.getInt("id"));
                    stmt.executeUpdate(sql2);
                    return rs.getString(1);
                }
                else{
                    return "/dubl";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void disconnect(){
        try {
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
